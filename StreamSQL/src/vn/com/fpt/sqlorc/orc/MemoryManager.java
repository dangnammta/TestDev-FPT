/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.fpt.sqlorc.orc;

/**
 *
 * @author cuongnc
 */
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.fs.Path;

public class MemoryManager implements org.apache.orc.MemoryManager{

	//private static final Log LOG = LogFactory.getLog(MemoryManager.class);
	private static final int ROWS_BETWEEN_CHECKS = 5000;
	private long totalMemoryPool = 1;
	private long totalAllocation = 0;
	private double currentScale = 1;
	private int rowsAddedSinceCheck = 0;

	private final Map<Path, WriterInfo> writerList
			= new HashMap<Path, WriterInfo>();

	
	private static class WriterInfo {

		long allocation;
		Callback callback;

		WriterInfo(long allocation, Callback callback) {
			this.allocation = allocation;
			this.callback = callback;
		}
	}

	/*public interface Callback {
		boolean checkMemory(double newScale) throws IOException;
	}*/

	MemoryManager(Configuration conf) {
		//HiveConf.ConfVars poolVar = HiveConf.ConfVars.HIVE_ORC_FILE_MEMORY_POOL;
		//double maxLoad = conf.getFloat(poolVar.varname, poolVar.defaultFloatVal);
		//load from configuration
		double maxLoad = 10000;
		totalMemoryPool = Math.round(ManagementFactory.getMemoryMXBean().
				getHeapMemoryUsage().getMax() * maxLoad);
	}
	
	@Override
	public synchronized void addWriter(Path path, long requestedAllocation, Callback callback) throws IOException {
		WriterInfo oldVal = writerList.get(path);
		// this should always be null, but we handle the case where the memory
		// manager wasn't told that a writer wasn't still in use and the task
		// starts writing to the same path.
		if (oldVal == null) {
			oldVal = new WriterInfo(requestedAllocation, callback);
			writerList.put(path, oldVal);
			totalAllocation += requestedAllocation;
		}
		else {
			// handle a new writer that is writing to the same path
			totalAllocation += requestedAllocation - oldVal.allocation;
			oldVal.allocation = requestedAllocation;
			oldVal.callback = callback;
		}
		updateScale(true);
	}

	/**
	 * Remove the given writer from the pool.
	 *
	 * @param path the file that has been closed
	 */
	@Override
	public synchronized void removeWriter(Path path) throws IOException {
		WriterInfo val = writerList.get(path);
		if (val != null) {
			writerList.remove(path);
			totalAllocation -= val.allocation;
			if (writerList.isEmpty()) {
				rowsAddedSinceCheck = 0;
			}
			updateScale(false);
		}
		if (writerList.isEmpty()) {
			rowsAddedSinceCheck = 0;
		}
	}

	/**
	 * Get the total pool size that is available for ORC writers.
	 *
	 * @return the number of bytes in the pool
	 */
	long getTotalMemoryPool() {
		return totalMemoryPool;
	}

	/**
	 * The scaling factor for each allocation to ensure that the pool isn't
	 * oversubscribed.
	 *
	 * @return a fraction between 0.0 and 1.0 of the requested size that is
	 * available for each writer.
	 */
	synchronized double getAllocationScale() {
		return currentScale;
	}

	/**
	 * Give the memory manager an opportunity for doing a memory check.
	 *
	 * @throws IOException
	 */
	@Override
	public synchronized void addedRow(int i) throws IOException {
		System.out.println("Memory addedRow " + totalAllocation + " " + totalMemoryPool);
		if (++rowsAddedSinceCheck >= ROWS_BETWEEN_CHECKS) {
			notifyWriters();
		}
	}

	/**
	 * Notify all of the writers that they should check their memory usage.
	 *
	 * @throws IOException
	 */
	void notifyWriters() throws IOException {
		System.out.println("Notifying writers after " + rowsAddedSinceCheck);
		for (WriterInfo writer : writerList.values()) {
			boolean flushed = writer.callback.checkMemory(currentScale);
			//if (LOG.isDebugEnabled() && flushed) {
			System.out.println("flushed " + writer.toString());
			//}
		}
		rowsAddedSinceCheck = 0;
	}

	/**
	 * Update the currentScale based on the current allocation and pool size.
	 * This also updates the notificationTrigger.
	 *
	 * @param isAllocate is this an allocation?
	 */
	private void updateScale(boolean isAllocate) throws IOException {
		System.out.println("Memory updateScale " + totalAllocation);
		if (totalAllocation <= totalMemoryPool) {
			currentScale = 1;
		}
		else {
			currentScale = (double) totalMemoryPool / totalAllocation;
		}
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.queue.app;

import crdhn.queue.data.TAgentChangelogQueue;
import crdhn.queue.model.ProducerQueue;
import org.apache.thrift.TException;

/**
 *
 * @author ddtech
 */
public class ServiceHandler implements Types.ThriftIfaceT {

	@Override
	public boolean addQueue(TAgentChangelogQueue changelogInfo) throws TException {
		try {
			ProducerQueue.getInstance().add(changelogInfo);
			return true;

		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public TAgentChangelogQueue deQueue() throws TException {
		try {
			return ProducerQueue.getInstance().poll();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}

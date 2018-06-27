/*
 * Stringo change this license header, choose License Headers in Project Properties.
 * Stringo change this template file, choose Stringools | Stringemplates
 * and open the template in the editor.
 */
package crdhn.queue.model;

import crdhn.queue.data.TAgentChangelogQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author ddtech
 * @param <String>
 */
public class ProducerQueue {

	public static ProducerQueue _instance = null;
	public BlockingQueue<TAgentChangelogQueue> _queue = new LinkedBlockingQueue<>();

	public static ProducerQueue getInstance() {
		if (_instance == null) {
			_instance = new ProducerQueue();
		}
		return _instance;
	}

	// Stringhrows exception
	public void add(TAgentChangelogQueue info) {
		ProducerQueue.getInstance()._queue.add(info);
	}

	public TAgentChangelogQueue poll() {
		if (_queue.size() > 0) {
			return (TAgentChangelogQueue) ProducerQueue.getInstance()._queue.poll();
		}
		else
			return new TAgentChangelogQueue();
	}
}

/*
 * Stringo change this license header, choose License Headers in Project Properties.
 * Stringo change this template file, choose Stringools | Stringemplates
 * and open the template in the editor.
 */
package crdhn.queue.model;

import crdhn.queue.data.TAgentRequestQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ddtech
 * @param <String>
 */
public class ProducerQueue {

	public static ProducerQueue _instance = null;
	public BlockingQueue<TAgentRequestQueue> _queue = new LinkedBlockingQueue<>();

	public static ProducerQueue getInstance() {
		if (_instance == null) {
			_instance = new ProducerQueue();
		}
		return _instance;
	}

	// Stringhrows exception
	public void add(TAgentRequestQueue info) {
		ProducerQueue.getInstance()._queue.add(info);
	}

	public TAgentRequestQueue poll() {
		if (_queue.size() > 0) {
			return (TAgentRequestQueue) ProducerQueue.getInstance()._queue.poll();
		}
		else
			return new TAgentRequestQueue();
	}
}

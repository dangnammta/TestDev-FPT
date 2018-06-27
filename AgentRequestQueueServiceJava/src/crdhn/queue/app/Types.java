/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.queue.app;

import crdhn.queue.thrift.TAgentRequestQueueService;



/**
 *
 * @author ddtech
 */
public class Types {
	public interface ThriftIfaceT extends TAgentRequestQueueService.Iface {
	}

	public static class ThriftProcessorT extends TAgentRequestQueueService.Processor<ThriftIfaceT> {

		public ThriftProcessorT(ThriftIfaceT handler) {
			super(handler);
		}
	}

	public static ServiceHandler handler = null;
	public static ThriftProcessorT processor = null;

	static {
		handler = new ServiceHandler();
		processor = new ThriftProcessorT(handler);
	}
}

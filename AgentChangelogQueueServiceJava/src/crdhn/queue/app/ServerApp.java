/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.queue.app;

import configuration.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ddtech
 */
public class ServerApp {
	
	public static void main(String[] args) {
		try {
			FCore.Thrift.TNonblockingServer server = new FCore.Thrift.TNonblockingServer(Configuration._servicePort, Configuration._serviceWorkerCount, Types.handler, Types.processor);
			server.initialize();
			System.out.println("Successful start " + Configuration._serviceName);
		}
		catch (Exception ex) {
			Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

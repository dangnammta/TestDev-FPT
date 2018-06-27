/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.globalstate;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author hoaronal
 */
public class ServletFlag {

	private static AtomicBoolean isRunningFromServlet = new AtomicBoolean(false);

	public static void runFromServlet() {
		isRunningFromServlet.set(true);
	}

	public static boolean isRunningFromServlet() {
		return isRunningFromServlet.get();
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import java.util.HashMap;

/**
 *
 * @author hoaronal
 */
public class CustomErrorPages {

	public static final String NOT_FOUND = "<html><body><h2>404 Not found</h2></body></html>";
	public static final String INTERNAL_ERROR = "<html><body><h2>500 Internal Error</h2></body></html>";

	public static boolean existsFor(int status) {
		return CustomErrorPages.getInstance().customPages.containsKey(status);
	}

	public static Object getFor(int status, Request request, Response response) {

		Object customRenderer = CustomErrorPages.getInstance().customPages.get(status);
		Object customPage;

		customPage = status == 404 ? NOT_FOUND : INTERNAL_ERROR;

		if (customRenderer instanceof String) {
			customPage = customRenderer;
		}
		else if (customRenderer instanceof Route) {
			try {
				customPage = ((Route) customRenderer).handle(request, response);
			}
			catch (Exception e) {
			}
		}

		return customPage;
	}

	static void add(int status, String page) {
		CustomErrorPages.getInstance().customPages.put(status, page);
	}

	static void add(int status, Route route) {
		CustomErrorPages.getInstance().customPages.put(status, route);
	}

	private final HashMap<Integer, Object> customPages;

	private CustomErrorPages() {
		customPages = new HashMap<>();
	}

	private static class SingletonHolder {

		private static final CustomErrorPages INSTANCE = new CustomErrorPages();
	}

	private static CustomErrorPages getInstance() {
		return SingletonHolder.INSTANCE;
	}

}

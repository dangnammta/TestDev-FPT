/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.resource;

import java.net.MalformedURLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hoaronal
 */
public abstract class AbstractResourceHandler {

	protected static final String SLASH = "/";

	public AbstractFileResolvingResource getResource(HttpServletRequest request) throws MalformedURLException {
		String servletPath;
		String pathInfo;
		boolean included = request.getAttribute(RequestDispatcher.INCLUDE_REQUEST_URI) != null;

		if (included) {
			servletPath = (String) request.getAttribute(RequestDispatcher.INCLUDE_SERVLET_PATH);
			pathInfo = (String) request.getAttribute(RequestDispatcher.INCLUDE_PATH_INFO);

			if (servletPath == null && pathInfo == null) {
				servletPath = request.getServletPath();
				pathInfo = request.getPathInfo();
			}
		}
		else {
			servletPath = request.getServletPath();
			pathInfo = request.getPathInfo();
		}

		String pathInContext = addPaths(servletPath, pathInfo);
		return getResource(pathInContext);
	}

	protected abstract AbstractFileResolvingResource getResource(String path) throws MalformedURLException;

	public static String addPaths(String segment1, String segment2) {
		if (segment1 == null || segment1.length() == 0) {
			if (segment1 != null && segment2 == null) {
				return segment1;
			}
			return segment2;
		}
		if (segment2 == null || segment2.length() == 0) {
			return segment1;
		}

		int split = segment1.indexOf(';');
		if (split < 0) {
			split = segment1.indexOf('?');
		}
		if (split == 0) {
			return segment2 + segment1;
		}
		if (split < 0) {
			split = segment1.length();
		}

		StringBuilder buf = new StringBuilder(segment1.length() + segment2.length() + 2);
		buf.append(segment1);

		if (buf.charAt(split - 1) == '/') {
			if (segment2.startsWith(SLASH)) {
				buf.deleteCharAt(split - 1);
				buf.insert(split - 1, segment2);
			}
			else {
				buf.insert(split, segment2);
			}
		}
		else if (segment2.startsWith(SLASH)) {
			buf.insert(split, segment2);
		}
		else {
			buf.insert(split, '/');
			buf.insert(split + 1, segment2);
		}

		return buf.toString();
	}
}

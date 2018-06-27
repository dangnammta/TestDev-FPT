/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import firo.utils.Wrapper;

/**
 *
 * @author hoaronal
 */
public abstract class RouteImpl implements Route, Wrapper {

	static final String DEFAULT_ACCEPT_TYPE = "*/*";
	private String path;
	private String acceptType;
	private Object delegate;

	static RouteImpl create(final String path, final Route route) {
		return create(path, DEFAULT_ACCEPT_TYPE, route);
	}

	static RouteImpl create(final String path, String acceptType, final Route route) {
		if (acceptType == null) {
			acceptType = DEFAULT_ACCEPT_TYPE;
		}
		return new RouteImpl(path, acceptType, route) {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				return route.handle(request, response);
			}
		};
	}

	protected RouteImpl(String path) {
		this(path, DEFAULT_ACCEPT_TYPE);
	}

	protected RouteImpl(String path, String acceptType) {
		this.path = path;
		this.acceptType = acceptType;
	}

	protected RouteImpl(String path, String acceptType, Object route) {
		this(path, acceptType);
		this.delegate = route;
	}

	public abstract Object handle(Request request, Response response) throws Exception;

	public Object render(Object element) throws Exception {
		if (element != null) {
			return element;
		}
		else {
			return null;
		}
	}

	public String getAcceptType() {
		return acceptType;
	}

	String getPath() {
		return this.path;
	}

	@Override
	public Object delegate() {
		return this.delegate;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.http.matching;

import javax.servlet.http.HttpServletRequest;

import firo.Response;
import firo.route.*;
import firo.route.Routes;

/**
 *
 * @author hoaronal
 */
final class RouteContext {

	static RouteContext create() {
		return new RouteContext();
	}

	private Routes routeMatcher;
	private HttpServletRequest httpRequest;
	private String uri;
	private String acceptType;
	private Body body;
	private RequestWrapper requestWrapper;
	private ResponseWrapper responseWrapper;
	private Response response;
	private HttpMethod httpMethod;

	private RouteContext() {
	}

	public Routes routeMatcher() {
		return routeMatcher;
	}

	public RouteContext withMatcher(firo.route.Routes routeMatcher) {
		this.routeMatcher = routeMatcher;
		return this;
	}

	public RouteContext withHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
		return this;
	}

	public RouteContext withAcceptType(String acceptType) {
		this.acceptType = acceptType;
		return this;
	}

	public RouteContext withBody(Body body) {
		this.body = body;
		return this;
	}

	public RouteContext withRequestWrapper(RequestWrapper requestWrapper) {
		this.requestWrapper = requestWrapper;
		return this;
	}

	public RouteContext withUri(String uri) {
		this.uri = uri;
		return this;
	}

	public RouteContext withResponseWrapper(ResponseWrapper responseWrapper) {
		this.responseWrapper = responseWrapper;
		return this;
	}

	public RouteContext withResponse(Response response) {
		this.response = response;
		return this;
	}

	public RouteContext withHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
		return this;
	}

	public HttpServletRequest httpRequest() {
		return httpRequest;
	}

	public String uri() {
		return uri;
	}

	public String acceptType() {
		return acceptType;
	}

	public Body body() {
		return body;
	}

	public RequestWrapper requestWrapper() {
		return requestWrapper;
	}

	public ResponseWrapper responseWrapper() {
		return responseWrapper;
	}

	public Response response() {
		return response;
	}

	public HttpMethod httpMethod() {
		return httpMethod;
	}

}

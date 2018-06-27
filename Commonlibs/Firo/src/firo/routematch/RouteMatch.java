/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.routematch;

/**
 *
 * @author hoaronal
 */
public class RouteMatch {

	private Object target;
	private String matchUri;
	private String requestURI;
	private String acceptType;

	public RouteMatch(Object target, String matchUri, String requestUri, String acceptType) {
		super();
		this.target = target;
		this.matchUri = matchUri;
		this.requestURI = requestUri;
		this.acceptType = acceptType;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public String getMatchUri() {
		return matchUri;
	}

	public void setMatchUri(String matchUri) {
		this.matchUri = matchUri;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}
}

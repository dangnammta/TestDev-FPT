/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import firo.routematch.RouteMatch;
import firo.utils.FiroUtils;
import firo.utils.IOUtils;
import firo.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoaronal
 */
public class Request {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Request.class);

	private static final String USER_AGENT = "user-agent";

	private Map<String, String> params;
	private List<String> splat;
	private QueryParamsMap queryMap;

	private HttpServletRequest servletRequest;

	private Session session = null;
	private boolean validSession = false;
	private String body = null;
	private byte[] bodyAsBytes = null;
	private Set<String> headers = null;

	protected Request() {

	}

	Request(RouteMatch match, HttpServletRequest request) {
		this.servletRequest = request;
		changeMatch(match);
	}

	Request(HttpServletRequest request) {
		this.servletRequest = request;

		params = new HashMap<>();
		splat = new ArrayList<>();
	}

	protected void changeMatch(RouteMatch match) {
		List<String> requestList = FiroUtils.convertRouteToList(match.getRequestURI());
		List<String> matchedList = FiroUtils.convertRouteToList(match.getMatchUri());

		params = getParams(requestList, matchedList);
		splat = getSplat(requestList, matchedList);
	}

	public Map<String, String> params() {
		return Collections.unmodifiableMap(params);
	}

	public String params(String param) {
		if (param == null) {
			return null;
		}
		return params.get(param.toLowerCase());
//		if (param.startsWith(":")) {
//			return params.get(param.toLowerCase());
//		}
//		else {
//			return params.get(":" + param.toLowerCase());
//		}
	}

	public String[] splat() {
		return splat.toArray(new String[splat.size()]);
	}

	public String requestMethod() {
		return servletRequest.getMethod();
	}

	public String scheme() {
		return servletRequest.getScheme();
	}

	public String host() {
		return servletRequest.getHeader("host");
	}

	public String userAgent() {
		return servletRequest.getHeader(USER_AGENT);
	}

	public int port() {
		return servletRequest.getServerPort();
	}

	public String pathInfo() {
		return servletRequest.getPathInfo();
	}

	public String servletPath() {
		return servletRequest.getServletPath();
	}

	public String contextPath() {
		return servletRequest.getContextPath();
	}

	public String url() {
		return servletRequest.getRequestURL().toString();
	}

	public String contentType() {
		return servletRequest.getContentType();
	}

	public String ip() {
		return servletRequest.getRemoteAddr();
	}

	public String body() {

		if (body == null) {
			body = StringUtils.toString(bodyAsBytes(), servletRequest.getCharacterEncoding());
		}

		return body;
	}

	public byte[] bodyAsBytes() {
		if (bodyAsBytes == null) {
			readBodyAsBytes();
		}
		return bodyAsBytes;
	}

	private void readBodyAsBytes() {
		try {
			bodyAsBytes = IOUtils.toByteArray(servletRequest.getInputStream());
		}
		catch (Exception e) {
			LOG.warn("Exception when reading body", e);
		}
	}

	public int contentLength() {
		return servletRequest.getContentLength();
	}

	public String queryParams(String queryParam) {
		return servletRequest.getParameter(queryParam);
	}

	public String[] queryParamsValues(String queryParam) {
		return servletRequest.getParameterValues(queryParam);
	}

	public String headers(String header) {
		return servletRequest.getHeader(header);
	}

	public Set<String> queryParams() {
		return servletRequest.getParameterMap().keySet();
	}

	public Set<String> headers() {
		if (headers == null) {
			headers = new TreeSet<>();
			Enumeration<String> enumeration = servletRequest.getHeaderNames();
			while (enumeration.hasMoreElements()) {
				headers.add(enumeration.nextElement());
			}
		}
		return headers;
	}

	public String queryString() {
		return servletRequest.getQueryString();
	}

	public void attribute(String attribute, Object value) {
		servletRequest.setAttribute(attribute, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T attribute(String attribute) {
		return (T) servletRequest.getAttribute(attribute);
	}

	public Set<String> attributes() {
		Set<String> attrList = new HashSet<>();
		Enumeration<String> attributes = (Enumeration<String>) servletRequest.getAttributeNames();
		while (attributes.hasMoreElements()) {
			attrList.add(attributes.nextElement());
		}
		return attrList;
	}

	public HttpServletRequest raw() {
		return servletRequest;
	}

	public QueryParamsMap queryMap() {
		initQueryMap();

		return queryMap;
	}

	public QueryParamsMap queryMap(String key) {
		return queryMap().get(key);
	}

	private void initQueryMap() {
		if (queryMap == null) {
			queryMap = new QueryParamsMap(raw());
		}
	}

	public Session session() {
		if (session == null || !validSession) {
			validSession(true);
			session = new Session(servletRequest.getSession(), this);
		}
		return session;
	}

	public Session session(boolean create) {
		if (session == null || !validSession) {
			HttpSession httpSession = servletRequest.getSession(create);
			if (httpSession != null) {
				validSession(true);
				session = new Session(httpSession, this);
			}
			else {
				session = null;
			}
		}
		return session;
	}

	public Map<String, String> cookies() {
		Map<String, String> result = new HashMap<>();
		Cookie[] cookies = servletRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				result.put(cookie.getName(), cookie.getValue());
			}
		}
		return result;
	}

	public String cookie(String name) {
		Cookie[] cookies = servletRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public String uri() {
		return servletRequest.getRequestURI();
	}

	public String protocol() {
		return servletRequest.getProtocol();
	}

	private static Map<String, String> getParams(List<String> request, List<String> matched) {
		Map<String, String> params = new HashMap<>();

		for (int i = 0; (i < request.size()) && (i < matched.size()); i++) {
			String matchedPart = matched.get(i);
			if (FiroUtils.isParam(matchedPart)) {
				try {
					String decodedReq = URLDecoder.decode(request.get(i), "UTF-8");
					LOG.debug("matchedPart: "
							+ matchedPart
							+ " = "
							+ decodedReq);
					params.put(matchedPart.toLowerCase(), decodedReq);

				}
				catch (UnsupportedEncodingException e) {

				}
			}
		}
		return Collections.unmodifiableMap(params);
	}

	private static List<String> getSplat(List<String> request, List<String> matched) {
		int nbrOfRequestParts = request.size();
		int nbrOfMatchedParts = matched.size();

		boolean sameLength = (nbrOfRequestParts == nbrOfMatchedParts);

		List<String> splat = new ArrayList<>();

		for (int i = 0; (i < nbrOfRequestParts) && (i < nbrOfMatchedParts); i++) {

			String matchedPart = matched.get(i);

			if (FiroUtils.isSplat(matchedPart)) {

				StringBuilder splatParam = new StringBuilder(request.get(i));

				if (!sameLength && (i == (nbrOfMatchedParts - 1))) {
					for (int j = i + 1; j < nbrOfRequestParts; j++) {
						splatParam.append("/");
						splatParam.append(request.get(j));
					}
				}
				try {
					String decodedSplat = URLDecoder.decode(splatParam.toString(), "UTF-8");
					splat.add(decodedSplat);
				}
				catch (UnsupportedEncodingException e) {
				}
			}
		}

		return Collections.unmodifiableList(splat);
	}

	void validSession(boolean validSession) {
		this.validSession = validSession;
	}
}

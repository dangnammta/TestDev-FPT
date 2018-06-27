/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hoaronal
 */
public class QueryParamsMap {

	private static final QueryParamsMap NULL = new NullQueryParamsMap();

	private static final Pattern KEY_PATTERN = Pattern.compile("\\A[\\[\\]]*([^\\[\\]]+)\\]*");

	private Map<String, QueryParamsMap> queryMap = new HashMap<>();

	private String[] values;

	public QueryParamsMap(HttpServletRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("HttpServletRequest cannot be null.");
		}
		loadQueryString(request.getParameterMap());
	}

	protected QueryParamsMap() {
	}

	protected QueryParamsMap(String key, String... values) {
		loadKeys(key, values);
	}

	protected QueryParamsMap(Map<String, String[]> params) {
		loadQueryString(params);
	}

	protected final void loadQueryString(Map<String, String[]> params) {
		for (Map.Entry<String, String[]> param : params.entrySet()) {
			loadKeys(param.getKey(), param.getValue());
		}
	}

	protected final void loadKeys(String key, String[] value) {
		String[] parsed = parseKey(key);

		if (parsed == null) {
			return;
		}

		if (!queryMap.containsKey(parsed[0])) {
			queryMap.put(parsed[0], new QueryParamsMap());
		}
		if (!parsed[1].isEmpty()) {
			queryMap.get(parsed[0]).loadKeys(parsed[1], value);
		}
		else {
			queryMap.get(parsed[0]).values = value.clone();
		}
	}

	protected final String[] parseKey(String key) {
		Matcher m = KEY_PATTERN.matcher(key);
		if (m.find()) {
			return new String[]{cleanKey(m.group()), key.substring(m.end())};
		}
		else {
			return null;
		}
	}

	protected static final String cleanKey(String group) {
		if (group.startsWith("[")) {
			return group.substring(1, group.length() - 1);
		}
		else {
			return group;
		}
	}

	public QueryParamsMap get(String... keys) {
		QueryParamsMap ret = this;
		for (String key : keys) {
			if (ret.queryMap.containsKey(key)) {
				ret = ret.queryMap.get(key);
			}
			else {
				ret = NULL;
			}
		}
		return ret;
	}

	public String value() {
		if (hasValue()) {
			return values[0];
		}
		else {
			return null;
		}
	}

	public String value(String... keys) {
		return get(keys).value();
	}

	public boolean hasKeys() {
		return !this.queryMap.isEmpty();
	}

	public boolean hasKey(String key) {
		return this.queryMap.containsKey(key);
	}

	public boolean hasValue() {
		return this.values != null && this.values.length > 0;
	}

	public Boolean booleanValue() {
		return hasValue() ? Boolean.valueOf(value()) : null;
	}

	public Integer integerValue() {
		return hasValue() ? Integer.valueOf(value()) : null;
	}

	public Long longValue() {
		return hasValue() ? Long.valueOf(value()) : null;
	}

	public Float floatValue() {
		return hasValue() ? Float.valueOf(value()) : null;
	}

	public Double doubleValue() {
		return hasValue() ? Double.valueOf(value()) : null;
	}

	public String[] values() {
		return this.values.clone();
	}

	Map<String, QueryParamsMap> getQueryMap() {
		return queryMap;
	}

	String[] getValues() {
		return values;
	}

	private static class NullQueryParamsMap extends QueryParamsMap {

		public NullQueryParamsMap() {
			super();
		}
	}

	public Map<String, String[]> toMap() {
		Map<String, String[]> map = new HashMap<>();
		for (Map.Entry<String, QueryParamsMap> key : this.queryMap.entrySet()) {
			map.put(key.getKey(), key.getValue().values);
		}
		return map;
	}
}

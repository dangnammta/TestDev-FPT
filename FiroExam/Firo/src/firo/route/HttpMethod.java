
package firo.route;

import java.util.HashMap;

public enum HttpMethod {
    get, post, put, patch, delete, head, trace, connect, options, before, after, unsupported;

    private static HashMap<String, HttpMethod> methods = new HashMap<>();

    static {
        for (HttpMethod method : values()) {
            methods.put(method.toString(), method);
        }
    }

    public static HttpMethod get(String methodStr) {
        HttpMethod method = methods.get(methodStr);
        return method != null ? method : unsupported;
    }
}

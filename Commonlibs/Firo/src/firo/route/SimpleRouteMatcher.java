
package firo.route;

import java.util.List;

import firo.routematch.RouteMatch;

public class SimpleRouteMatcher extends Routes {

    public void parseValidateAddRoute(String route, String acceptType, Object target) {
        add(route, acceptType, target);
    }

    public RouteMatch findTargetForRequestedRoute(HttpMethod httpMethod, String path, String acceptType) {
        return find(httpMethod, path, acceptType);
    }

    public List<RouteMatch> findTargetsForRequestedRoute(HttpMethod httpMethod, String path, String acceptType) {
        return findMultiple(httpMethod, path, acceptType);
    }

    public void clearRoutes() {
        clear();
    }

    public boolean removeRoute(String path, String httpMethod) {
        return remove(path, httpMethod);
    }

    public boolean removeRoute(String path) {
        return remove(path);
    }

}

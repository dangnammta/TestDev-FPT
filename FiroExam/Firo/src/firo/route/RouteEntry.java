package firo.route;

import java.util.List;

import firo.utils.FiroUtils;

class RouteEntry {

    HttpMethod httpMethod;
    String path;
    String acceptedType;
    Object target;

    RouteEntry() {
    }

    RouteEntry(RouteEntry entry) {
        this.httpMethod = entry.httpMethod;
        this.path = entry.path;
        this.acceptedType = entry.acceptedType;
        this.target = entry.target;
    }

    boolean matches(HttpMethod httpMethod, String path) {
        if ((httpMethod == HttpMethod.before || httpMethod == HttpMethod.after)
                && (this.httpMethod == httpMethod)
                && this.path.equals(FiroUtils.ALL_PATHS)) {
            return true;
        }
        boolean match = false;
        if (this.httpMethod == httpMethod) {
            match = matchPath(path);
        }
        return match;
    }

    private boolean matchPath(String path) {
        if (!this.path.endsWith("*") && ((path.endsWith("/") && !this.path.endsWith("/"))
                || (this.path.endsWith("/") && !path.endsWith("/")))) {
            return false;
        }
        if (this.path.equals(path)) {
            return true;
        }

        List<String> thisPathList = FiroUtils.convertRouteToList(this.path);
        List<String> pathList = FiroUtils.convertRouteToList(path);

        int thisPathSize = thisPathList.size();
        int pathSize = pathList.size();

        if (thisPathSize == pathSize) {
            for (int i = 0; i < thisPathSize; i++) {
                String thisPathPart = thisPathList.get(i);
                String pathPart = pathList.get(i);

                if ((i == thisPathSize - 1) && (thisPathPart.equals("*") && this.path.endsWith("*"))) {

                    return true;
                }

                if ((!thisPathPart.startsWith(":"))
                        && !thisPathPart.equals(pathPart)
                        && !thisPathPart.equals("*")) {
                    return false;
                }
            }
            return true;
        } else {
            if (this.path.endsWith("*")) {
                if (pathSize == (thisPathSize - 1) && (path.endsWith("/"))) {
                    pathList.add("");
                    pathList.add("");
                    pathSize += 2;
                }

                if (thisPathSize < pathSize) {
                    for (int i = 0; i < thisPathSize; i++) {
                        String thisPathPart = thisPathList.get(i);
                        String pathPart = pathList.get(i);
                        if (thisPathPart.equals("*") && (i == thisPathSize - 1) && this.path.endsWith("*")) {
                            return true;
                        }
                        if (!thisPathPart.startsWith(":")
                                && !thisPathPart.equals(pathPart)
                                && !thisPathPart.equals("*")) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public String toString() {
        return httpMethod.name() + ", " + path + ", " + target;
    }
}

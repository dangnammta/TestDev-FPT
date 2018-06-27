package firo.route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import firo.routematch.RouteMatch;
import firo.utils.MimeParse;
import firo.utils.StringUtils;
import firo.utils.Wrapper;

public class Routes {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Routes.class);
	private static final char SINGLE_QUOTE = '\'';

	private List<RouteEntry> routes;
	private List<RouteEntry> routeEntrys;

	public static Routes create() {
		return new Routes();
	}

	protected Routes() {
		routeEntrys = new ArrayList<>();
		routes = new ArrayList<>();
	}

	public void add(String route, String acceptType, Object target) {
		try {
			int singleQuoteIndex = route.indexOf(SINGLE_QUOTE);
			String httpMethod = route.substring(0, singleQuoteIndex).trim().toLowerCase(); // NOSONAR
			String url = route.substring(singleQuoteIndex + 1, route.length() - 1).trim(); // NOSONAR

			// Use special enum stuff to get from value
			HttpMethod method;
			try {
				method = HttpMethod.valueOf(httpMethod);
			}
			catch (IllegalArgumentException e) {
				LOG.error("The @Route value: "
						+ route
						+ " has an invalid HTTP method part: "
						+ httpMethod
						+ ".");
				return;
			}
			addRoute(method, url, acceptType, target);
		}
		catch (Exception e) {
			LOG.error("The @Route value: " + route + " is not in the correct format", e);
		}
	}

	public RouteMatch find(HttpMethod httpMethod, String path, String acceptType) {
		List<RouteEntry> routeEntries = this.findTargetsForRequestedRoute(httpMethod, path);
		RouteEntry entry = findTargetWithGivenAcceptType(routeEntries, acceptType);
		return entry != null ? new RouteMatch(entry.target, entry.path, path, acceptType) : null;
	}

	public List<RouteMatch> findMultiple(HttpMethod httpMethod, String path, String acceptType) {
		List<RouteMatch> matchSet = new ArrayList<>();
		List<RouteEntry> routeEntries = findTargetsForRequestedRoute(httpMethod, path);

		for (RouteEntry routeEntry : routeEntries) {
			if (acceptType != null) {
				String bestMatch = MimeParse.bestMatch(Arrays.asList(routeEntry.acceptedType), acceptType);

				if (routeWithGivenAcceptType(bestMatch)) {
					matchSet.add(new RouteMatch(routeEntry.target, routeEntry.path, path, acceptType));
				}
			}
			else {
				matchSet.add(new RouteMatch(routeEntry.target, routeEntry.path, path, acceptType));
			}
		}

		return matchSet;
	}

	public void clear() {
		routes.clear();
		RouteOverview.routes.clear();
	}

	public boolean remove(String path, String httpMethod) {
		if (StringUtils.isEmpty(path)) {
			throw new IllegalArgumentException("path cannot be null or blank");
		}

		if (StringUtils.isEmpty(httpMethod)) {
			throw new IllegalArgumentException("httpMethod cannot be null or blank");
		}

		HttpMethod method = HttpMethod.valueOf(httpMethod);

		return removeRoute(method, path);
	}

	public boolean remove(String path) {
		if (StringUtils.isEmpty(path)) {
			throw new IllegalArgumentException("path cannot be null or blank");
		}

		return removeRoute((HttpMethod) null, path);
	}

	private void addRoute(HttpMethod method, String url, String acceptedType, Object target) {
		RouteEntry entry = new RouteEntry();
		entry.httpMethod = method;
		entry.path = url;
		entry.target = target;
		entry.acceptedType = acceptedType;
		LOG.debug("Adds route: " + entry);
		routes.add(entry);
//		RouteOverview.add(new RouteEntry(entry), target);
		add(new RouteEntry(entry), target);
	}
	
	private void add(RouteEntry entry, Object wrapped) {

        if (wrapped instanceof Wrapper) {
            entry.target = ((Wrapper) wrapped).delegate();
        }

        routeEntrys.add(entry);
    }

	private Map<String, RouteEntry> getAcceptedMimeTypes(List<RouteEntry> routes) {
		Map<String, RouteEntry> acceptedTypes = new HashMap<>();

		for (RouteEntry routeEntry : routes) {
			if (!acceptedTypes.containsKey(routeEntry.acceptedType)) {
				acceptedTypes.put(routeEntry.acceptedType, routeEntry);
			}
		}

		return acceptedTypes;
	}

	private boolean routeWithGivenAcceptType(String bestMatch) {
		return !MimeParse.NO_MIME_TYPE.equals(bestMatch);
	}

	private List<RouteEntry> findTargetsForRequestedRoute(HttpMethod httpMethod, String path) {
		List<RouteEntry> matchSet = new ArrayList<RouteEntry>();
		for (RouteEntry entry : routes) {
			if (entry.matches(httpMethod, path)) {
				matchSet.add(entry);
			}
		}
		return matchSet;
	}

	private RouteEntry findTargetWithGivenAcceptType(List<RouteEntry> routeMatches, String acceptType) {
		if (acceptType != null && routeMatches.size() > 0) {
			Map<String, RouteEntry> acceptedMimeTypes = getAcceptedMimeTypes(routeMatches);
			String bestMatch = MimeParse.bestMatch(acceptedMimeTypes.keySet(), acceptType);

			if (routeWithGivenAcceptType(bestMatch)) {
				return acceptedMimeTypes.get(bestMatch);
			}
			else {
				return null;
			}
		}
		else if (routeMatches.size() > 0) {
			return routeMatches.get(0);
		}

		return null;
	}

	private boolean removeRoute(HttpMethod httpMethod, String path) {
		List<RouteEntry> forRemoval = new ArrayList<>();

		for (RouteEntry routeEntry : routes) {
			HttpMethod httpMethodToMatch = httpMethod;

			if (httpMethod == null) {
				httpMethodToMatch = routeEntry.httpMethod;
			}

			if (routeEntry.matches(httpMethodToMatch, path)) {
				LOG.debug("Removing path {}", path, httpMethod == null ? "" : " with HTTP method " + httpMethod);

				forRemoval.add(routeEntry);
			}
		}

		return routes.removeAll(forRemoval);
	}
}

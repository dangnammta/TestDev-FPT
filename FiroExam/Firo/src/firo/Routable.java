/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import firo.route.HttpMethod;
import firo.utils.FiroUtils;

/**
 *
 * @author hoaronal
 */
abstract class Routable {

	protected abstract void addRoute(String httpMethod, RouteImpl route);

	protected abstract void addFilter(String httpMethod, FilterImpl filter);

	public void get(final String path, final Route route) {
		addRoute(HttpMethod.get.name(), RouteImpl.create(path, route));
	}

	public void post(String path, Route route) {
		addRoute(HttpMethod.post.name(), RouteImpl.create(path, route));
	}

	public void put(String path, Route route) {
		addRoute(HttpMethod.put.name(), RouteImpl.create(path, route));
	}

	public void patch(String path, Route route) {
		addRoute(HttpMethod.patch.name(), RouteImpl.create(path, route));
	}

	public void delete(String path, Route route) {
		addRoute(HttpMethod.delete.name(), RouteImpl.create(path, route));
	}

	public void head(String path, Route route) {
		addRoute(HttpMethod.head.name(), RouteImpl.create(path, route));
	}

	public void trace(String path, Route route) {
		addRoute(HttpMethod.trace.name(), RouteImpl.create(path, route));
	}

	public void connect(String path, Route route) {
		addRoute(HttpMethod.connect.name(), RouteImpl.create(path, route));
	}

	public void options(String path, Route route) {
		addRoute(HttpMethod.options.name(), RouteImpl.create(path, route));
	}

	public void before(String path, Filter filter) {
		addFilter(HttpMethod.before.name(), FilterImpl.create(path, filter));
	}

	public void after(String path, Filter filter) {
		addFilter(HttpMethod.after.name(), FilterImpl.create(path, filter));
	}

	public void get(String path, String acceptType, Route route) {
		addRoute(HttpMethod.get.name(), RouteImpl.create(path, acceptType, route));
	}

	public void post(String path, String acceptType, Route route) {
		addRoute(HttpMethod.post.name(), RouteImpl.create(path, acceptType, route));
	}

	public void put(String path, String acceptType, Route route) {
		addRoute(HttpMethod.put.name(), RouteImpl.create(path, acceptType, route));
	}

	public void patch(String path, String acceptType, Route route) {
		addRoute(HttpMethod.patch.name(), RouteImpl.create(path, acceptType, route));
	}

	public void delete(String path, String acceptType, Route route) {
		addRoute(HttpMethod.delete.name(), RouteImpl.create(path, acceptType, route));
	}

	public void head(String path, String acceptType, Route route) {
		addRoute(HttpMethod.head.name(), RouteImpl.create(path, acceptType, route));
	}

	public void trace(String path, String acceptType, Route route) {
		addRoute(HttpMethod.trace.name(), RouteImpl.create(path, acceptType, route));
	}

	public void connect(String path, String acceptType, Route route) {
		addRoute(HttpMethod.connect.name(), RouteImpl.create(path, acceptType, route));
	}

	public void options(String path, String acceptType, Route route) {
		addRoute(HttpMethod.options.name(), RouteImpl.create(path, acceptType, route));
	}

	public void before(Filter filter) {
		addFilter(HttpMethod.before.name(), FilterImpl.create(FiroUtils.ALL_PATHS, filter));
	}

	public void after(Filter filter) {
		addFilter(HttpMethod.after.name(), FilterImpl.create(FiroUtils.ALL_PATHS, filter));
	}

	public void before(String path, String acceptType, Filter filter) {
		addFilter(HttpMethod.before.name(), FilterImpl.create(path, acceptType, filter));
	}

	public void after(String path, String acceptType, Filter filter) {
		addFilter(HttpMethod.after.name(), FilterImpl.create(path, acceptType, filter));
	}

	public void get(String path, TemplateViewRoute route, TemplateEngine engine) {
		addRoute(HttpMethod.get.name(), TemplateViewRouteImpl.create(path, route, engine));
	}

	public void get(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		addRoute(HttpMethod.get.name(), TemplateViewRouteImpl.create(path, acceptType, route, engine));
	}

	public void post(String path, TemplateViewRoute route, TemplateEngine engine) {
		addRoute(HttpMethod.post.name(), TemplateViewRouteImpl.create(path, route, engine));
	}

	public void post(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		addRoute(HttpMethod.post.name(), TemplateViewRouteImpl.create(path, acceptType, route, engine));
	}

	public void put(String path, TemplateViewRoute route, TemplateEngine engine) {
		addRoute(HttpMethod.put.name(), TemplateViewRouteImpl.create(path, route, engine));
	}

	public void put(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		addRoute(HttpMethod.put.name(), TemplateViewRouteImpl.create(path, acceptType, route, engine));
	}

	public void delete(String path, TemplateViewRoute route, TemplateEngine engine) {
		addRoute(HttpMethod.delete.name(), TemplateViewRouteImpl.create(path, route, engine));
	}

	public void delete(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		addRoute(HttpMethod.delete.name(), TemplateViewRouteImpl.create(path, acceptType, route, engine));
	}

	public void patch(String path, TemplateViewRoute route, TemplateEngine engine) {
		addRoute(HttpMethod.patch.name(), TemplateViewRouteImpl.create(path, route, engine));
	}

	public void patch(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		addRoute(HttpMethod.patch.name(), TemplateViewRouteImpl.create(path, acceptType, route, engine));
	}

	public void head(String path, TemplateViewRoute route, TemplateEngine engine) {
		addRoute(HttpMethod.head.name(), TemplateViewRouteImpl.create(path, route, engine));
	}

	public void head(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		addRoute(HttpMethod.head.name(), TemplateViewRouteImpl.create(path, acceptType, route, engine));
	}

	public void trace(String path, TemplateViewRoute route, TemplateEngine engine) {
		addRoute(HttpMethod.trace.name(), TemplateViewRouteImpl.create(path, route, engine));
	}

	public void trace(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		addRoute(HttpMethod.trace.name(), TemplateViewRouteImpl.create(path, acceptType, route, engine));
	}

	public void connect(String path, TemplateViewRoute route, TemplateEngine engine) {
		addRoute(HttpMethod.connect.name(), TemplateViewRouteImpl.create(path, route, engine));
	}

	public void connect(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		addRoute(HttpMethod.connect.name(), TemplateViewRouteImpl.create(path, acceptType, route, engine));
	}

	public void options(String path, TemplateViewRoute route, TemplateEngine engine) {
		addRoute(HttpMethod.options.name(), TemplateViewRouteImpl.create(path, route, engine));
	}

	public void options(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		addRoute(HttpMethod.options.name(), TemplateViewRouteImpl.create(path, acceptType, route, engine));
	}

	public void get(String path, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.get.name(), ResponseTransformerRouteImpl.create(path, route, transformer));
	}

	public void get(String path, String acceptType, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.get.name(), ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
	}

	public void post(String path, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.post.name(), ResponseTransformerRouteImpl.create(path, route, transformer));
	}

	public void post(String path, String acceptType, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.post.name(), ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
	}

	public void put(String path, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.put.name(), ResponseTransformerRouteImpl.create(path, route, transformer));
	}

	public void put(String path, String acceptType, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.put.name(), ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
	}

	public void delete(String path, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.delete.name(), ResponseTransformerRouteImpl.create(path, route, transformer));
	}

	public void delete(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		addRoute(HttpMethod.delete.name(), ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
	}

	public void head(String path, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.head.name(), ResponseTransformerRouteImpl.create(path, route, transformer));
	}

	public void head(String path, String acceptType, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.head.name(), ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
	}

	public void connect(String path, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.connect.name(), ResponseTransformerRouteImpl.create(path, route, transformer));
	}

	public void connect(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		addRoute(HttpMethod.connect.name(), ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
	}

	public void trace(String path, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.trace.name(), ResponseTransformerRouteImpl.create(path, route, transformer));
	}

	public void trace(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		addRoute(HttpMethod.trace.name(), ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
	}

	public void options(String path, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.options.name(), ResponseTransformerRouteImpl.create(path, route, transformer));
	}

	public void options(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		addRoute(HttpMethod.options.name(), ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
	}

	public void patch(String path, Route route, ResponseTransformer transformer) {
		addRoute(HttpMethod.patch.name(), ResponseTransformerRouteImpl.create(path, route, transformer));
	}

	public void patch(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		addRoute(HttpMethod.patch.name(), ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
	}

}

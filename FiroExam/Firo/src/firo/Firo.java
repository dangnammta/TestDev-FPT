/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import static firo.Service.ignite;
import firo.Controller;
import java.io.File;
import java.net.URL;

/**
 *
 * @author hoaronal
 */
public class Firo {

	protected Firo() {
	}

	private static class SingletonHolder {

		private static final Service INSTANCE = ignite();
	}

	public static Service getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public static final Redirect redirect = getInstance().redirect;

	public static final Service.StaticFiles staticFiles = getInstance().staticFiles;
	
	
	public static void path(String path, RouteGroup routeGroup) {
		getInstance().path(path, routeGroup);
	}
	
	public static void addRoute(final String httpMethod, final String path, final Route route) {
		getInstance().addRoute(httpMethod,RouteImpl.create(path, route));
	}
	
	public static void get(final String path, final Route route) {
		getInstance().get(path, route);
	}

	public static void post(String path, Route route) {
		getInstance().post(path, route);
	}

	public static void put(String path, Route route) {
		getInstance().put(path, route);
	}

	public static void patch(String path, Route route) {
		getInstance().patch(path, route);
	}

	public static void delete(String path, Route route) {
		getInstance().delete(path, route);
	}

	public static void head(String path, Route route) {
		getInstance().head(path, route);
	}

	public static void trace(String path, Route route) {
		getInstance().trace(path, route);
	}

	public static void connect(String path, Route route) {
		getInstance().connect(path, route);
	}

	public static void options(String path, Route route) {
		getInstance().options(path, route);
	}

	public static void before(String path, Filter filter) {
		getInstance().before(path, filter);
	}

	public static void before(String path, Filter... filters) {
		for (Filter filter : filters) {
			getInstance().before(path, filter);
		}
	}

	public static void after(String path, Filter filter) {
		getInstance().after(path, filter);
	}

	public static void after(String path, Filter... filters) {
		for (Filter filter : filters) {
			getInstance().after(path, filter);
		}
	}

	public static void get(String path, String acceptType, Route route) {
		getInstance().get(path, acceptType, route);
	}

	public static void post(String path, String acceptType, Route route) {
		getInstance().post(path, acceptType, route);
	}

	public static void put(String path, String acceptType, Route route) {
		getInstance().put(path, acceptType, route);
	}

	public static void patch(String path, String acceptType, Route route) {
		getInstance().patch(path, acceptType, route);
	}

	public static void delete(String path, String acceptType, Route route) {
		getInstance().delete(path, acceptType, route);
	}

	public static void head(String path, String acceptType, Route route) {
		getInstance().head(path, acceptType, route);
	}

	public static void trace(String path, String acceptType, Route route) {
		getInstance().trace(path, acceptType, route);
	}

	public static void connect(String path, String acceptType, Route route) {
		getInstance().connect(path, acceptType, route);
	}

	public static void options(String path, String acceptType, Route route) {
		getInstance().options(path, acceptType, route);
	}

	public static void before(Filter... filters) {
		for (Filter filter : filters) {
			getInstance().before(filter);
		}
	}

	public static void after(Filter... filters) {
		for (Filter filter : filters) {
			getInstance().after(filter);
		}
	}

	public static void before(String path, String acceptType, Filter... filters) {
		for (Filter filter : filters) {
			getInstance().before(path, acceptType, filter);
		}
	}

	public static void after(String path, String acceptType, Filter... filters) {
		for (Filter filter : filters) {
			getInstance().after(path, acceptType, filter);
		}
	}

	public static void get(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().get(path, route, engine);
	}

	public static void get(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		getInstance().get(path, acceptType, route, engine);
	}

	public static void post(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().post(path, route, engine);
	}

	public static void post(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		getInstance().post(path, acceptType, route, engine);
	}

	public static void put(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().put(path, route, engine);
	}

	public static void put(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		getInstance().put(path, acceptType, route, engine);
	}

	public static void delete(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().delete(path, route, engine);
	}

	public static void delete(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		getInstance().delete(path, acceptType, route, engine);
	}

	public static void patch(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().patch(path, route, engine);
	}

	public static void patch(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		getInstance().patch(path, acceptType, route, engine);
	}

	public static void head(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().head(path, route, engine);
	}

	public static void head(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		getInstance().head(path, acceptType, route, engine);
	}

	public static void trace(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().trace(path, route, engine);
	}

	public static void trace(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		getInstance().trace(path, acceptType, route, engine);
	}

	public static void connect(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().connect(path, route, engine);
	}

	public static void connect(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		getInstance().connect(path, acceptType, route, engine);
	}

	public static void options(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().options(path, route, engine);
	}

	public static void options(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {
		getInstance().options(path, acceptType, route, engine);
	}

	public static void get(String path, Route route, ResponseTransformer transformer) {
		getInstance().get(path, route, transformer);
	}

	public static void get(String path, String acceptType, Route route, ResponseTransformer transformer) {
		getInstance().get(path, acceptType, route, transformer);
	}

	public static void post(String path, Route route, ResponseTransformer transformer) {
		getInstance().post(path, route, transformer);
	}

	public static void post(String path, String acceptType, Route route, ResponseTransformer transformer) {
		getInstance().post(path, acceptType, route, transformer);
	}

	public static void put(String path, Route route, ResponseTransformer transformer) {
		getInstance().put(path, route, transformer);
	}

	public static void put(String path, String acceptType, Route route, ResponseTransformer transformer) {
		getInstance().put(path, acceptType, route, transformer);
	}

	public static void delete(String path, Route route, ResponseTransformer transformer) {
		getInstance().delete(path, route, transformer);
	}

	public static void delete(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		getInstance().delete(path, acceptType, route, transformer);
	}

	public static void head(String path, Route route, ResponseTransformer transformer) {
		getInstance().head(path, route, transformer);
	}

	public static void head(String path, String acceptType, Route route, ResponseTransformer transformer) {
		getInstance().head(path, acceptType, route, transformer);
	}

	public static void connect(String path, Route route, ResponseTransformer transformer) {
		getInstance().connect(path, route, transformer);
	}

	public static void connect(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		getInstance().connect(path, acceptType, route, transformer);
	}

	public static void trace(String path, Route route, ResponseTransformer transformer) {
		getInstance().trace(path, route, transformer);
	}

	public static void trace(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		getInstance().trace(path, acceptType, route, transformer);
	}

	public static void options(String path, Route route, ResponseTransformer transformer) {
		getInstance().options(path, route, transformer);
	}

	public static void options(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		getInstance().options(path, acceptType, route, transformer);
	}

	public static void patch(String path, Route route, ResponseTransformer transformer) {
		getInstance().patch(path, route, transformer);
	}

	public static void patch(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		getInstance().patch(path, acceptType, route, transformer);
	}

	public static void exception(Class<? extends Exception> exceptionClass, ExceptionHandler handler) {
		getInstance().exception(exceptionClass, handler);
	}

	public static void halt() {
		getInstance().halt();
	}

	public static void halt(int status) {
		getInstance().halt(status);
	}

	public static void halt(String body) {
		getInstance().halt(body);
	}

	public static void halt(int status, String body) {
		getInstance().halt(status, body);
	}

	public static void setIpAddress(String ipAddress) {
		getInstance().ipAddress(ipAddress);
	}

	public static void ipAddress(String ipAddress) {
		getInstance().ipAddress(ipAddress);
	}

	public static void setPort(int port) {
		getInstance().port(port);
	}

	public static void port(int port) {
		getInstance().port(port);
	}

	public static int port() {
		return getInstance().port();
	}

	public static void setSecure(String keystoreFile,
			String keystorePassword,
			String truststoreFile,
			String truststorePassword) {
		getInstance().secure(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
	}

	public static void secure(String keystoreFile,
			String keystorePassword,
			String truststoreFile,
			String truststorePassword) {
		getInstance().secure(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
	}

	public static void threadPool(int maxThreads) {
		getInstance().threadPool(maxThreads);
	}

	public static void threadPool(int maxThreads, int minThreads, int idleTimeoutMillis) {
		getInstance().threadPool(maxThreads, minThreads, idleTimeoutMillis);
	}

	public static void staticFileLocation(String folder) {
		getInstance().staticFileLocation(folder);
	}

	public static void externalStaticFileLocation(String externalFolder) {
		getInstance().externalStaticFileLocation(externalFolder);
	}

	public static void awaitInitialization() {
		getInstance().awaitInitialization();
	}

    public static void stop() {
        getInstance().stop();
    }
    public static void webSocket(String path, Class<?> handler) {
        getInstance().webSocket(path, handler);
    }
    public static void webSocket(String path, Object handler) {
        getInstance().webSocket(path, handler);
    }
	public static void webSocketIdleTimeoutMillis(int timeoutMillis) {
		getInstance().webSocketIdleTimeoutMillis(timeoutMillis);
	}

	public static void notFound(String page) {
		getInstance().notFound(page);
	}

	public static void internalServerError(String page) {
		getInstance().internalServerError(page);
	}

	public static void notFound(Route route) {
		getInstance().notFound(route);
	}

	public static void internalServerError(Route route) {
		getInstance().internalServerError(route);
	}

	public static void init() {
		getInstance().init();
	}
	
	public static void init(String host, int port) {
		getInstance().init(host, port);
	}
	
	public static void initializeControllerFromPackage(String pkgName, Class clazz) {
		getInstance().initializeControllerFromPackage(pkgName, clazz);
	}
	
	public static void initController(Controller controller) {
		getInstance().initController(controller);
	}

	public static ModelAndView modelAndView(Object model, String viewName) {
		return new ModelAndView(model, viewName);
	}

}

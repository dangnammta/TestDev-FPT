/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import firo.embeddedserver.EmbeddedServer;
import firo.embeddedserver.EmbeddedServers;
import firo.embeddedserver.jetty.websocket.WebSocketHandlerClassWrapper;
import firo.embeddedserver.jetty.websocket.WebSocketHandlerInstanceWrapper;
import firo.embeddedserver.jetty.websocket.WebSocketHandlerWrapper;
import static firo.globalstate.ServletFlag.isRunningFromServlet;
import firo.route.Routes;
import firo.route.ServletRoutes;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import firo.ssl.SslStores;
import firo.staticfiles.MimeType;
import firo.staticfiles.StaticFilesConfiguration;
import firo.utils.ClassFinder;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import static java.util.Objects.requireNonNull;
import java.util.stream.Collectors;

/**
 *
 * @author hoaronal
 */
public final class Service extends Routable {

	private static final Logger LOG = LoggerFactory.getLogger("firo.Firo");

	public static final int FIRO_DEFAULT_PORT = 4567;
	protected static final String DEFAULT_ACCEPT_TYPE = "*/*";

	protected boolean initialized = false;

	protected int port = FIRO_DEFAULT_PORT;
	protected String ipAddress = "0.0.0.0";

	protected SslStores sslStores;

	protected String staticFileFolder = null;
	protected String externalStaticFileFolder = null;

	protected Map<String, WebSocketHandlerWrapper> webSocketHandlers = null;

	protected int maxThreads = -1;
	protected int minThreads = -1;
	protected int threadIdleTimeoutMillis = -1;
	protected Optional<Integer> webSocketIdleTimeoutMillis = Optional.empty();

	protected EmbeddedServer server;
	protected Deque<String> pathDeque = new ArrayDeque<>();
	protected Routes routes;

	private boolean servletStaticLocationSet;
	private boolean servletExternalStaticLocationSet;

	private CountDownLatch latch = new CountDownLatch(1);

	private Object embeddedServerIdentifier = null;

	public final Redirect redirect;
	public final StaticFiles staticFiles;

	private final StaticFilesConfiguration staticFilesConfiguration;

	public static Service ignite() {
		return new Service();
	}

	private Service() {
		redirect = Redirect.create(this);
		staticFiles = new StaticFiles();

		if (isRunningFromServlet()) {
			staticFilesConfiguration = StaticFilesConfiguration.servletInstance;
		}
		else {
			staticFilesConfiguration = StaticFilesConfiguration.create();
		}
	}

	public synchronized Service ipAddress(String ipAddress) {
		if (initialized) {
			throwBeforeRouteMappingException();
		}
		this.ipAddress = ipAddress;

		return this;
	}

	public synchronized Service port(int port) {
		if (initialized) {
			throwBeforeRouteMappingException();
		}
		this.port = port;
		return this;
	}

	public synchronized int port() {
		if (initialized) {
			return port;
		}
		else {
			throw new IllegalStateException("This must be done after route mapping has begun");
		}
	}

	public synchronized Service secure(String keystoreFile,
			String keystorePassword,
			String truststoreFile,
			String truststorePassword) {
		if (initialized) {
			throwBeforeRouteMappingException();
		}

		if (keystoreFile == null) {
			throw new IllegalArgumentException(
					"Must provide a keystore file to run secured");
		}

		sslStores = SslStores.create(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
		return this;
	}

	public synchronized Service threadPool(int maxThreads) {
		return threadPool(maxThreads, -1, -1);
	}

	public synchronized Service threadPool(int maxThreads, int minThreads, int idleTimeoutMillis) {
		if (initialized) {
			throwBeforeRouteMappingException();
		}

		this.maxThreads = maxThreads;
		this.minThreads = minThreads;
		this.threadIdleTimeoutMillis = idleTimeoutMillis;

		return this;
	}

	public synchronized Service staticFileLocation(String folder) {
		if (initialized && !isRunningFromServlet()) {
			throwBeforeRouteMappingException();
		}

		staticFileFolder = folder;

		if (!servletStaticLocationSet) {
			staticFilesConfiguration.configure(staticFileFolder);
			servletStaticLocationSet = true;
		}
		else {
			LOG.warn("Static file location has already been set");
		}
		return this;
	}

	public synchronized Service externalStaticFileLocation(String externalFolder) {
		if (initialized && !isRunningFromServlet()) {
			throwBeforeRouteMappingException();
		}

		externalStaticFileFolder = externalFolder;

		if (!servletExternalStaticLocationSet) {
			staticFilesConfiguration.configureExternal(externalStaticFileFolder);
			servletExternalStaticLocationSet = true;
		}
		else {
			LOG.warn("External static file location has already been set");
		}
		return this;
	}

	public void webSocket(String path, Class<?> handlerClass) {
		addWebSocketHandler(path, new WebSocketHandlerClassWrapper(handlerClass));
	}

	public void webSocket(String path, Object handler) {
		addWebSocketHandler(path, new WebSocketHandlerInstanceWrapper(handler));
	}

	private synchronized void addWebSocketHandler(String path, WebSocketHandlerWrapper handlerWrapper) {
		if (initialized) {
			throwBeforeRouteMappingException();
		}
		if (isRunningFromServlet()) {
			throw new IllegalStateException("WebSockets are only supported in the embedded server");
		}
		requireNonNull(path, "WebSocket path cannot be null");
		if (webSocketHandlers == null) {
			webSocketHandlers = new HashMap<>();
		}

		webSocketHandlers.put(path, handlerWrapper);
	}

	public synchronized Service webSocketIdleTimeoutMillis(int timeoutMillis) {
		if (initialized) {
			throwBeforeRouteMappingException();
		}
		if (isRunningFromServlet()) {
			throw new IllegalStateException("WebSockets are only supported in the embedded server");
		}
		webSocketIdleTimeoutMillis = Optional.of(timeoutMillis);
		return this;
	}

	public synchronized void notFound(String page) {
		CustomErrorPages.add(404, page);
	}

	public synchronized void internalServerError(String page) {
		CustomErrorPages.add(500, page);
	}

	public synchronized void notFound(Route route) {
		CustomErrorPages.add(404, route);
	}

	public synchronized void internalServerError(Route route) {
		CustomErrorPages.add(500, route);
	}

	public void awaitInitialization() {
		try {
			latch.await();
		}
		catch (InterruptedException e) {
			LOG.info("Interrupted by another thread");
		}
	}

	private void throwBeforeRouteMappingException() {
		throw new IllegalStateException(
				"This must be done before route mapping has begun");
	}

	private boolean hasMultipleHandlers() {
		return webSocketHandlers != null;
	}

	public synchronized void stop() {
		new Thread(() -> {
			if (server != null) {
				routes.clear();
				server.extinguish();
				latch = new CountDownLatch(1);
			}

			staticFilesConfiguration.clear();
			initialized = false;
		}).start();
	}

	public void path(String path, RouteGroup routeGroup) {
		pathDeque.addLast(path);
		routeGroup.addRoutes();
		pathDeque.removeLast();
	}

	public String getPaths() {
		return pathDeque.stream().collect(Collectors.joining(""));
	}
	
	@Override
	public void addRoute(String httpMethod, RouteImpl route) {
		init();
		routes.add(httpMethod + " '" + getPaths() + route.getPath() + "'", route.getAcceptType(), route);
	}

	@Override
	public void addFilter(String httpMethod, FilterImpl filter) {
		init();
		routes.add(httpMethod + " '" + getPaths() + filter.getPath() + "'", filter.getAcceptType(), filter);
	}
	
	public synchronized void init() {
		if (!initialized) {
			initializeRouteMatcher();

			if (!isRunningFromServlet()) {
				new Thread(() -> {
					EmbeddedServers.initialize();

					if (embeddedServerIdentifier == null) {
						embeddedServerIdentifier = EmbeddedServers.defaultIdentifier();
					}

					server = EmbeddedServers.create(embeddedServerIdentifier,
							routes,
							staticFilesConfiguration,
							hasMultipleHandlers());

					server.configureWebSockets(webSocketHandlers, webSocketIdleTimeoutMillis);

					port = server.ignite(
							ipAddress,
							port,
							sslStores,
							latch,
							maxThreads,
							minThreads,
							threadIdleTimeoutMillis);
				}).start();
			}

			initialized = true;
		}
	}

	public synchronized void init(String p_host, int p_port) {
		if(p_port > 0){
			this.port = p_port;
		}
		if(! p_host.isEmpty()){
			this.ipAddress = p_host;
		}
		init();
	}

	private void initializeRouteMatcher() {
		if (isRunningFromServlet()) {
			routes = ServletRoutes.get();
		}
		else {
			routes = Routes.create();
		}
	}
	
	
	public void initializeControllerFromPackage(String pkgName, Class clazz) {
		init();
		List<Class<?>> classes;
		
		String mainFile = clazz.getProtectionDomain().getCodeSource()
                 .getLocation().getFile();
		//System.out.println("Launched from " + mainFile);
		
		if ((mainFile != null) && ( mainFile.endsWith(".jar") )) {
			//System.out.println("Launched from Jar ");
			File jarFile = new File(mainFile);
			classes = ClassFinder.getAllClassesInPackage(pkgName, jarFile);
		}else{
			//System.out.println("Launched NOT from Jar :P ");
			classes = ClassFinder.getAllClassesInPackage(pkgName);
		}
		
		for (int i = 0; i < classes.size(); i++) {
			try {
				Controller controller = (Controller)Class.forName(classes.get(i).getName()).newInstance();
				controller.initRoutes();
			}
			catch (Exception ex) {
				LOG.error(ex.getMessage());
			}
		}
	}
        
	public void initController(Controller controller) {
		init();
		try {
			controller.initRoutes();
		}
		catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
	};

	public synchronized void exception(Class<? extends Exception> exceptionClass, ExceptionHandler handler) {
		// wrap
		ExceptionHandlerImpl wrapper = new ExceptionHandlerImpl(exceptionClass) {
			@Override
			public void handle(Exception exception, Request request, Response response) {
				handler.handle(exception, request, response);
			}
		};

		ExceptionMapper.getInstance().map(exceptionClass, wrapper);
	}

	public HaltException halt() {
		throw new HaltException();
	}

	public HaltException halt(int status) {
		throw new HaltException(status);
	}

	public HaltException halt(String body) {
		throw new HaltException(body);
	}

	public HaltException halt(int status, String body) {
		throw new HaltException(status, body);
	}

	
	public final class StaticFiles {

		public void location(String folder) {
			staticFileLocation(folder);
		}

		public void externalLocation(String externalFolder) {
			externalStaticFileLocation(externalFolder);
		}

		public void headers(Map<String, String> headers) {
			staticFilesConfiguration.putCustomHeaders(headers);
		}

		public void header(String key, String value) {
			staticFilesConfiguration.putCustomHeader(key, value);
		}

		@Experimental("Functionality will not be removed. The API might change")
		public void expireTime(long seconds) {
			staticFilesConfiguration.setExpireTimeSeconds(seconds);
		}

		public void registerMimeType(String extension, String mimeType) {
			MimeType.register(extension, mimeType);
		}

		public void disableMimeTypeGuessing() {
			MimeType.disableGuessing();
		}

	}
}

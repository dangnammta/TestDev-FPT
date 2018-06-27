package firo.embeddedserver.jetty;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import firo.embeddedserver.EmbeddedServer;
import firo.embeddedserver.jetty.websocket.WebSocketHandlerWrapper;
import firo.embeddedserver.jetty.websocket.WebSocketServletContextHandlerFactory;
import firo.ssl.SslStores;

public class EmbeddedJettyServer implements EmbeddedServer {

	private static final int FIRO_DEFAULT_PORT = 4567;
	private static final String NAME = "FIRO";

	private Handler handler;
	private Server server;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Map<String, WebSocketHandlerWrapper> webSocketHandlers;
	private Optional<Integer> webSocketIdleTimeoutMillis;

	public EmbeddedJettyServer(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void configureWebSockets(Map<String, WebSocketHandlerWrapper> webSocketHandlers,
			Optional<Integer> webSocketIdleTimeoutMillis) {

		this.webSocketHandlers = webSocketHandlers;
		this.webSocketIdleTimeoutMillis = webSocketIdleTimeoutMillis;
	}

	@Override
	public int ignite(String host,
			int port,
			SslStores sslStores,
			CountDownLatch latch,
			int maxThreads,
			int minThreads,
			int threadIdleTimeoutMillis) {

		if (port == 0) {
			try (ServerSocket s = new ServerSocket(0)) {
				port = s.getLocalPort();
			}
			catch (IOException e) {
				logger.error("Could not get first available port (port set to 0), using default: {}", FIRO_DEFAULT_PORT);
				port = FIRO_DEFAULT_PORT;
			}
		}

		server = JettyServer.create(maxThreads, minThreads, threadIdleTimeoutMillis);

		ServerConnector connector;

		if (sslStores == null) {
			connector = SocketConnectorFactory.createSocketConnector(server, host, port);
		}
		else {
			connector = SocketConnectorFactory.createSecureSocketConnector(server, host, port, sslStores);
		}

		server = connector.getServer();
		server.setConnectors(new Connector[]{connector});

		ServletContextHandler webSocketServletContextHandler
				= WebSocketServletContextHandlerFactory.create(webSocketHandlers, webSocketIdleTimeoutMillis);

		if (webSocketServletContextHandler == null) {
			server.setHandler(handler);
		}
		else {
			List<Handler> handlersInList = new ArrayList<>();
			handlersInList.add(handler);

			if (webSocketServletContextHandler != null) {
				handlersInList.add(webSocketServletContextHandler);
			}

			HandlerList handlers = new HandlerList();
			handlers.setHandlers(handlersInList.toArray(new Handler[handlersInList.size()]));
			server.setHandler(handlers);
		}

		try {
			logger.info("== {} has ignited ...", NAME);
			logger.info(">> Listening on {}:{}", host, port);

			server.start();
			latch.countDown();
			server.join();
		}
		catch (Exception e) {
			logger.error("ignite failed", e);
			System.exit(100);
		}

		return port;
	}

	@Override
	public void extinguish() {
		logger.info(">>> {} shutting down ...", NAME);
		try {
			if (server != null) {
				server.stop();
			}
		}
		catch (Exception e) {
			logger.error("stop failed", e);
			System.exit(100);
		}
		logger.info("done");
	}

}

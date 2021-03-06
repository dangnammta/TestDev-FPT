package firo.embeddedserver.jetty.websocket;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import static java.util.Objects.requireNonNull;

public class WebSocketCreatorFactory {

	public static WebSocketCreator create(WebSocketHandlerWrapper handlerWrapper) {
		return new SparkWebSocketCreator(handlerWrapper.getHandler());
	}

	static class SparkWebSocketCreator implements WebSocketCreator {

		private final Object handler;

		private SparkWebSocketCreator(Object handler) {
			this.handler = requireNonNull(handler, "handler cannot be null");
		}

		@Override
		public Object createWebSocket(ServletUpgradeRequest request,
				ServletUpgradeResponse response) {
			return handler;
		}

		Object getHandler() {
			return handler;
		}
	}
}

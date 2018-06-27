
package firo.embeddedserver;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import firo.embeddedserver.jetty.websocket.WebSocketHandlerWrapper;
import firo.ssl.SslStores;

public interface EmbeddedServer {

	int ignite(String host,
			int port,
			SslStores sslStores,
			CountDownLatch latch,
			int maxThreads,
			int minThreads,
			int threadIdleTimeoutMillis);

	default void configureWebSockets(Map<String, WebSocketHandlerWrapper> webSocketHandlers,
			Optional<Integer> webSocketIdleTimeoutMillis) {

		NotSupportedException.raise(getClass().getSimpleName(), "Web Sockets");
	}

	void extinguish();
}

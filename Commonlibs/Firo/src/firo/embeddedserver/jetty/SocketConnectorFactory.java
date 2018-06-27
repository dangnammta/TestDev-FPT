package firo.embeddedserver.jetty;

import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import firo.ssl.SslStores;
import firo.utils.Assert;

public class SocketConnectorFactory {

	public static ServerConnector createSocketConnector(Server server, String host, int port) {
		Assert.notNull(server, "'server' must not be null");
		Assert.notNull(host, "'host' must not be null");

		ServerConnector connector = new ServerConnector(server);
		initializeConnector(connector, host, port);
		return connector;
	}

	public static ServerConnector createSecureSocketConnector(Server server,
			String host,
			int port,
			SslStores sslStores) {
		Assert.notNull(server, "'server' must not be null");
		Assert.notNull(host, "'host' must not be null");
		Assert.notNull(sslStores, "'sslStores' must not be null");

		SslContextFactory sslContextFactory = new SslContextFactory(sslStores.keystoreFile());

		if (sslStores.keystorePassword() != null) {
			sslContextFactory.setKeyStorePassword(sslStores.keystorePassword());
		}

		if (sslStores.trustStoreFile() != null) {
			sslContextFactory.setTrustStorePath(sslStores.trustStoreFile());
		}

		if (sslStores.trustStorePassword() != null) {
			sslContextFactory.setTrustStorePassword(sslStores.trustStorePassword());
		}

		ServerConnector connector = new ServerConnector(server, sslContextFactory);
		initializeConnector(connector, host, port);
		return connector;
	}

	private static void initializeConnector(ServerConnector connector, String host, int port) {
		connector.setIdleTimeout(TimeUnit.HOURS.toMillis(1));
		connector.setSoLingerTime(-1);
		connector.setHost(host);
		connector.setPort(port);
	}

}

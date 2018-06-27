package firo.embeddedserver;

import java.util.HashMap;
import java.util.Map;

import firo.embeddedserver.jetty.EmbeddedJettyFactory;
import firo.route.Routes;
import firo.staticfiles.StaticFilesConfiguration;

public class EmbeddedServers {

	public enum Identifiers {
		JETTY
	}

	private static Map<Object, EmbeddedServerFactory> factories = new HashMap<>();

	public static void initialize() {
		add(Identifiers.JETTY, new EmbeddedJettyFactory());
	}

	public static Identifiers defaultIdentifier() {
		return Identifiers.JETTY;
	}

	public static EmbeddedServer create(Object identifier,
			Routes routeMatcher,
			StaticFilesConfiguration staticFilesConfiguration,
			boolean multipleHandlers) {

		EmbeddedServerFactory factory = factories.get(identifier);

		if (factory != null) {
			return factory.create(routeMatcher, staticFilesConfiguration, multipleHandlers);
		}
		else {
			throw new RuntimeException("No embedded server matching the identifier");
		}
	}

	public static void add(Object identifier, EmbeddedServerFactory factory) {
		factories.put(identifier, factory);
	}

}

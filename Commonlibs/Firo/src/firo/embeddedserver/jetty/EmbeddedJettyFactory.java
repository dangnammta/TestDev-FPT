package firo.embeddedserver.jetty;

import firo.embeddedserver.EmbeddedServer;
import firo.embeddedserver.EmbeddedServerFactory;
import firo.http.matching.MatcherFilter;
import firo.route.Routes;
import firo.staticfiles.StaticFilesConfiguration;

public class EmbeddedJettyFactory implements EmbeddedServerFactory {

	@Override
	public EmbeddedServer create(Routes routeMatcher, StaticFilesConfiguration staticFilesConfiguration, boolean hasMultipleHandler) {
		MatcherFilter matcherFilter = new MatcherFilter(routeMatcher, staticFilesConfiguration, false, hasMultipleHandler);
		matcherFilter.init(null);

		JettyHandler handler = new JettyHandler(matcherFilter);
		return new EmbeddedJettyServer(handler);
	}

}

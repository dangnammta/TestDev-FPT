
package firo.embeddedserver;

import firo.route.Routes;
import firo.staticfiles.StaticFilesConfiguration;

public interface EmbeddedServerFactory {

    public EmbeddedServer create(Routes routeMatcher, StaticFilesConfiguration staticFilesConfiguration, boolean hasMultipleHandler);
}

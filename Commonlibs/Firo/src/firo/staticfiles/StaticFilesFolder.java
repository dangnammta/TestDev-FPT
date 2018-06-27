/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.staticfiles;

import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static firo.utils.StringUtils.removeLeadingAndTrailingSlashesFrom;
/**
 *
 * @author hoaronal
 */
public class StaticFilesFolder {
	private static final Logger LOG = LoggerFactory.getLogger(StaticFilesFolder.class);
    
    private static volatile String local;
    private static volatile String external;

    public static final void localConfiguredTo(String folder) {

        local = removeLeadingAndTrailingSlashesFrom(folder);
    }

    public static final void externalConfiguredTo(String folder) {

        String unixLikeFolder = Paths.get(folder).toAbsolutePath().toString().replace("\\", "/");
        LOG.warn("Registering external static files folder [{}] as [{}].", folder, unixLikeFolder);
        external = removeLeadingAndTrailingSlashesFrom(unixLikeFolder);
    }

    public static final String local() {
        return local;
    }

    public static final String external() {
        return external;
    }
}

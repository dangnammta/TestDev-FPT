/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.staticfiles;

import static firo.utils.StringUtils.removeLeadingAndTrailingSlashesFrom;
import java.nio.file.Paths;

/**
 *
 * @author hoaronal
 */
public class DirectoryTraversal {

	public static void protectAgainstInClassPath(String path) {
		if (!removeLeadingAndTrailingSlashesFrom(path).startsWith(StaticFilesFolder.local())) {
			throw new DirectoryTraversalDetection("classpath");
		}
	}

	public static void protectAgainstForExternal(String path) {
		String nixLikePath = Paths.get(path).toAbsolutePath().toString().replace("\\", "/");
		if (!removeLeadingAndTrailingSlashesFrom(nixLikePath).startsWith(StaticFilesFolder.external())) {
			throw new DirectoryTraversalDetection("external");
		}
	}

	public static final class DirectoryTraversalDetection extends RuntimeException {

		public DirectoryTraversalDetection(String msg) {
			super(msg);
		}

	}

}

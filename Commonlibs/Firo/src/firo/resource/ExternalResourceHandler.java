/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.resource;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import firo.staticfiles.DirectoryTraversal;
import firo.utils.Assert;

/**
 *
 * @author hoaronal
 */
public class ExternalResourceHandler extends AbstractResourceHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ExternalResourceHandler.class);

	private final String baseResource;
	private String welcomeFile;

	public ExternalResourceHandler(String baseResource) {
		this(baseResource, null);
	}

	public ExternalResourceHandler(String baseResource, String welcomeFile) {
		Assert.notNull(baseResource);
		this.baseResource = baseResource;
		this.welcomeFile = welcomeFile;
	}

	@Override
	protected AbstractFileResolvingResource getResource(String path) throws MalformedURLException {
		if (path == null || !path.startsWith("/")) {
			throw new MalformedURLException(path);
		}

		try {
			path = UriPath.canonical(path);

			final String addedPath = addPaths(baseResource, path);

			ExternalResource resource = new ExternalResource(addedPath);

			if (resource.exists() && resource.isDirectory()) {
				if (welcomeFile != null) {
					resource = new ExternalResource(addPaths(resource.getPath(), welcomeFile));
				}
				else {
					resource = null;
				}
			}

			if (resource != null && resource.exists()) {
				DirectoryTraversal.protectAgainstForExternal(resource.getPath());
				return resource;
			}
			else {
				return null;
			}

		}
		catch (DirectoryTraversal.DirectoryTraversalDetection directoryTraversalDetection) {
			throw directoryTraversalDetection;
		}
		catch (Exception e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getClass().getSimpleName() + " when trying to get resource. " + e.getMessage());
			}
		}
		return null;
	}

}

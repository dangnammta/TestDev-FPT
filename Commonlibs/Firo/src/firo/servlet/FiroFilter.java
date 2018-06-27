/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import firo.globalstate.ServletFlag;
import firo.http.matching.MatcherFilter;
import firo.route.ServletRoutes;
import firo.staticfiles.StaticFilesConfiguration;
import firo.utils.StringUtils;

/**
 *
 * @author hoaronal
 */
public class FiroFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(FiroFilter.class);

	public static final String APPLICATION_CLASS_PARAM = "applicationClass";

	private String filterPath;

	private MatcherFilter matcherFilter;

	private FiroApplication[] applications;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletFlag.runFromServlet();

		applications = getApplications(filterConfig);

		for (FiroApplication application : applications) {
			application.init();
		}

		filterPath = FilterTools.getFilterPath(filterConfig);

		matcherFilter = new MatcherFilter(ServletRoutes.get(), StaticFilesConfiguration.servletInstance, true, false);
	}

	@Deprecated
	protected FiroApplication getApplication(FilterConfig filterConfig) throws ServletException {
		return getApplication(filterConfig.getInitParameter(APPLICATION_CLASS_PARAM));
	}

	protected FiroApplication getApplication(String applicationClassName) throws ServletException {
		try {
			Class<?> applicationClass = Class.forName(applicationClassName);
			return (FiroApplication) applicationClass.newInstance();
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected FiroApplication[] getApplications(final FilterConfig filterConfig) throws ServletException {

		String applications = filterConfig.getInitParameter(APPLICATION_CLASS_PARAM);
		FiroApplication[] solvedApplications = null;

		if (StringUtils.isNotBlank(applications)) {
			final String[] sparkApplications = applications.split(",");

			if (sparkApplications != null && sparkApplications.length > 0) {
				solvedApplications = new FiroApplication[sparkApplications.length];

				for (int index = 0; index < sparkApplications.length; index++) {
					solvedApplications[index] = getApplication(sparkApplications[index].trim());
				}
			}
			else {
				throw new ServletException("There are no Spark applications configured in the filter.");
			}
		}

		return solvedApplications;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
			IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		final String relativePath = FilterTools.getRelativePath(httpRequest, filterPath);

		if (LOG.isDebugEnabled()) {
			LOG.debug(relativePath);
		}

		HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {
			@Override
			public String getPathInfo() {
				return relativePath;
			}

			@Override
			public String getRequestURI() {
				return relativePath;
			}
		};

		boolean consumed = StaticFilesConfiguration.servletInstance.consume(httpRequest, httpResponse);
		if (consumed) {
			return;
		}
		matcherFilter.doFilter(requestWrapper, response, chain);
	}

	@Override
	public void destroy() {
		if (applications != null) {
			for (FiroApplication sparkApplication : applications) {
				sparkApplication.destroy();
			}
		}
	}

}

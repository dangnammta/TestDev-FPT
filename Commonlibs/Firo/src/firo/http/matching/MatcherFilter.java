/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.http.matching;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import firo.CustomErrorPages;
import firo.HaltException;
import firo.RequestResponseFactory;
import firo.Response;
import firo.embeddedserver.jetty.HttpRequestWrapper;
import firo.route.HttpMethod;
import firo.serialization.SerializerChain;
import firo.staticfiles.StaticFilesConfiguration;

/**
 *
 * @author hoaronal
 */
public class MatcherFilter implements Filter {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(MatcherFilter.class);

    private static final String ACCEPT_TYPE_REQUEST_MIME_HEADER = "Accept";
    private static final String HTTP_METHOD_OVERRIDE_HEADER = "X-HTTP-Method-Override";

    private final StaticFilesConfiguration staticFiles;

    private firo.route.Routes routeMatcher;
    private SerializerChain serializerChain;

    private boolean externalContainer;
    private boolean hasOtherHandlers;

    public MatcherFilter(firo.route.Routes routeMatcher,
                         StaticFilesConfiguration staticFiles,
                         boolean externalContainer,
                         boolean hasOtherHandlers) {

        this.routeMatcher = routeMatcher;
        this.staticFiles = staticFiles;
        this.externalContainer = externalContainer;
        this.hasOtherHandlers = hasOtherHandlers;
        this.serializerChain = new SerializerChain();
    }

    public void init(FilterConfig config) {
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        boolean consumedByStaticFile = staticFiles.consume(httpRequest, httpResponse);

        if (consumedByStaticFile) {
            return;
        }

        String method = getHttpMethodFrom(httpRequest);

        String httpMethodStr = method.toLowerCase();
        String uri = httpRequest.getRequestURI();
        String acceptType = httpRequest.getHeader(ACCEPT_TYPE_REQUEST_MIME_HEADER);

        Body body = Body.create();

        RequestWrapper requestWrapper = RequestWrapper.create();
        ResponseWrapper responseWrapper = ResponseWrapper.create();

        Response response = RequestResponseFactory.create(httpResponse);

        HttpMethod httpMethod = HttpMethod.get(httpMethodStr);

        RouteContext context = RouteContext.create()
                .withMatcher(routeMatcher)
                .withHttpRequest(httpRequest)
                .withUri(uri)
                .withAcceptType(acceptType)
                .withBody(body)
                .withRequestWrapper(requestWrapper)
                .withResponseWrapper(responseWrapper)
                .withResponse(response)
                .withHttpMethod(httpMethod);

        try {

            BeforeFilters.execute(context);
            Routes.execute(context);
            AfterFilters.execute(context);

        } catch (HaltException halt) {

            Halt.modify(httpResponse, body, halt);

        } catch (Exception generalException) {

            GeneralError.modify(
                    httpRequest,
                    httpResponse,
                    body,
                    requestWrapper,
                    responseWrapper,
                    generalException);

        }

        if (body.notSet() && responseWrapper.isRedirected()) {
            body.set("");
        }

        if (body.notSet() && hasOtherHandlers) {
            if (servletRequest instanceof HttpRequestWrapper) {
                ((HttpRequestWrapper) servletRequest).notConsumed(true);
                return;
            }
        }

        if (body.notSet() && !externalContainer) {
            LOG.info("The requested route [{}] has not been mapped in Firo for {}: [{}]",
                     uri, ACCEPT_TYPE_REQUEST_MIME_HEADER, acceptType);
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);

            if (CustomErrorPages.existsFor(404)) {
                requestWrapper.setDelegate(RequestResponseFactory.create(httpRequest));
                responseWrapper.setDelegate(RequestResponseFactory.create(httpResponse));
                body.set(CustomErrorPages.getFor(404, requestWrapper, responseWrapper));
            } else {
                body.set(String.format(CustomErrorPages.NOT_FOUND));
            }
        }

        if (body.isSet()) {
            body.serializeTo(httpResponse, serializerChain, httpRequest);

        } else if (chain != null) {
            chain.doFilter(httpRequest, httpResponse);
        }
    }

    private String getHttpMethodFrom(HttpServletRequest httpRequest) {
        String method = httpRequest.getHeader(HTTP_METHOD_OVERRIDE_HEADER);

        if (method == null) {
            method = httpRequest.getMethod();
        }
        return method;
    }

    public void destroy() {
    }


}

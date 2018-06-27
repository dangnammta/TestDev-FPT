/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hoaronal
 */
public class Response {

    private static final Logger LOG = LoggerFactory.getLogger(Response.class);

    private HttpServletResponse response;
    private String body;

    protected Response() {
    }

    Response(HttpServletResponse response) {
        this.response = response;
    }

    public void status(int statusCode) {
        this.response.setStatus(statusCode);
    }

    public int status() {
        return response.getStatus();
    }

    public void type(String contentType) {
        this.response.setContentType(contentType);
    }

    public String type() {
        return this.response.getContentType();
    }
    
    public void setContentLength(long size){
        this.response.setContentLengthLong(size);
    }
    
    public void body(String body) {
        this.body = body;
    }

    public String body() {
        return this.body;
    }

    public HttpServletResponse raw() {
        return this.response;
    }

    public void redirect(String location) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Redirecting ({} {} to {}", "Found", HttpServletResponse.SC_FOUND, location);
        }
        try {
            this.response.sendRedirect(location);
        } catch (IOException ioException) {
            LOG.warn("Redirect failure", ioException);
        }
    }

    public void redirect(String location, int httpStatusCode) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Redirecting ({} to {}", httpStatusCode, location);
        }
        this.response.setStatus(httpStatusCode);
        this.response.setHeader("Location", location);
        this.response.setHeader("Connection", "close");
        try {
            this.response.sendError(httpStatusCode);
        } catch (IOException e) {
            LOG.warn("Exception when trying to redirect permanently", e);
        }
    }

    public void header(String header, String value) {
        this.response.addHeader(header, value);
    }

    public void cookie(String name, String value) {
        cookie(name, value, -1, false);
    }

    public void cookie(String name, String value, int maxAge) {
        cookie(name, value, maxAge, false);
    }

    public void cookie(String name, String value, int maxAge, boolean secured) {
        cookie(name, value, maxAge, secured, false);
    }

    public void cookie(String name, String value, int maxAge, boolean secured, boolean httpOnly) {
        cookie("", name, value, maxAge, secured, httpOnly);
    }

    public void cookie(String path, String name, String value, int maxAge, boolean secured) {
        cookie(path, name, value, maxAge, secured, false);
    }

    public void cookie(String path, String name, String value, int maxAge, boolean secured, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(secured);
        cookie.setHttpOnly(httpOnly);
        this.response.addCookie(cookie);
    }

    public void removeCookie(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        this.response.addCookie(cookie);
    }
}
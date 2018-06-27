/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import firo.routematch.RouteMatch;
/**
 *
 * @author hoaronal
 */
public final class RequestResponseFactory {

    private RequestResponseFactory() {
    }

    public static Request create(HttpServletRequest request) {
        return new Request(request);
    }

    public static Request create(RouteMatch match, HttpServletRequest request) {
        return new Request(match, request);
    }

    public static Response create(HttpServletResponse response) {
        return new Response(response);
    }

}

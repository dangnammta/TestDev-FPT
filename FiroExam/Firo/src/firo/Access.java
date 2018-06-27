/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import firo.routematch.RouteMatch;
/**
 *
 * @author hoaronal
 */
public final class Access {

    private Access() {
    }

    public static void changeMatch(Request request, RouteMatch match) {
        request.changeMatch(match);
    }

}
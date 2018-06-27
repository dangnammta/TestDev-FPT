/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.http.matching;

import javax.servlet.http.HttpServletResponse;

import firo.HaltException;
/**
 *
 * @author hoaronal
 */
public class Halt {

    public static void modify(HttpServletResponse httpResponse, Body body, HaltException halt) {

        httpResponse.setStatus(halt.statusCode());

        if (halt.body() != null) {
            body.set(halt.body());
        } else {
            body.set("");
        }
    }
}

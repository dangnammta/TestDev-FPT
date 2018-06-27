/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

/**
 *
 * @author hoaronal
 */
@FunctionalInterface
public interface TemplateViewRoute {

	ModelAndView handle(Request request, Response response) throws Exception;
}

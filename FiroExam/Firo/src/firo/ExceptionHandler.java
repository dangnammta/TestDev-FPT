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
public interface ExceptionHandler {

    void handle(Exception exception, Request request, Response response);
}
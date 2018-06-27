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
public abstract class ExceptionHandlerImpl implements ExceptionHandler {

    protected Class<? extends Exception> exceptionClass;

    public ExceptionHandlerImpl(Class<? extends Exception> exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public Class<? extends Exception> exceptionClass() {
        return this.exceptionClass;
    }

    public void exceptionClass(Class<? extends Exception> exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public abstract void handle(Exception exception, Request request, Response response);
}
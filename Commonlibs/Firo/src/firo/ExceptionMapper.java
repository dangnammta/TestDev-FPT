/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hoaronal
 */
public class ExceptionMapper {

	private static ExceptionMapper defaultInstance;

	public synchronized static ExceptionMapper getInstance() {
		if (defaultInstance == null) {
			defaultInstance = new ExceptionMapper();
		}
		return defaultInstance;
	}

	private Map<Class<? extends Exception>, ExceptionHandlerImpl> exceptionMap;

	public ExceptionMapper() {
		this.exceptionMap = new HashMap<>();
	}

	public void map(Class<? extends Exception> exceptionClass, ExceptionHandlerImpl handler) {
		this.exceptionMap.put(exceptionClass, handler);
	}

	public ExceptionHandlerImpl getHandler(Class<? extends Exception> exceptionClass) {

		if (!this.exceptionMap.containsKey(exceptionClass)) {

			Class<?> superclass = exceptionClass.getSuperclass();
			do {

				if (this.exceptionMap.containsKey(superclass)) {

					ExceptionHandlerImpl handler = this.exceptionMap.get(superclass);
					this.exceptionMap.put(exceptionClass, handler);
					return handler;
				}

				superclass = superclass.getSuperclass();
			}
			while (superclass != null);

			this.exceptionMap.put(exceptionClass, null);
			return null;
		}

		return this.exceptionMap.get(exceptionClass);
	}

	public ExceptionHandlerImpl getHandler(Exception exception) {
		return this.getHandler(exception.getClass());
	}
}

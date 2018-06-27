/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoaronal
 */
public class HaltException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int statusCode = HttpServletResponse.SC_OK;
	private String body = null;

	HaltException() {
		super();
	}

	HaltException(int statusCode) {
		this.statusCode = statusCode;
	}

	HaltException(String body) {
		this.body = body;
	}

	HaltException(int statusCode, String body) {
		this.statusCode = statusCode;
		this.body = body;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public int statusCode() {
		return statusCode;
	}

	public String getBody() {
		return body;
	}

	public String body() {
		return body;
	}

}

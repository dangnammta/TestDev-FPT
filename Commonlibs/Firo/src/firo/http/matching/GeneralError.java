/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.http.matching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import firo.CustomErrorPages;
import firo.ExceptionHandlerImpl;
import firo.ExceptionMapper;
import firo.RequestResponseFactory;

/**
 *
 * @author hoaronal
 */
final class GeneralError {

	static void modify(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse,
			Body body,
			RequestWrapper requestWrapper,
			ResponseWrapper responseWrapper,
			Exception e) {

		ExceptionHandlerImpl handler = ExceptionMapper.getInstance().getHandler(e);

		if (handler != null) {
			handler.handle(e, requestWrapper, responseWrapper);
			String bodyAfterFilter = responseWrapper.getDelegate().body();

			if (bodyAfterFilter != null) {
				body.set(bodyAfterFilter);
			}
		}
		else {
			httpResponse.setStatus(500);

			if (CustomErrorPages.existsFor(500)) {
				requestWrapper.setDelegate(RequestResponseFactory.create(httpRequest));
				responseWrapper.setDelegate(RequestResponseFactory.create(httpResponse));
				body.set(CustomErrorPages.getFor(500, requestWrapper, responseWrapper));
			}
			else {
				body.set(CustomErrorPages.INTERNAL_ERROR);
			}

		}
	}

}

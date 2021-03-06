/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.http.matching;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import firo.utils.GzipUtils;
import firo.serialization.SerializerChain;

/**
 *
 * @author hoaronal
 */
final class Body {

	private Object content;

	public static Body create() {
		return new Body();
	}

	private Body() {

	}

	public boolean notSet() {
		return content == null;
	}

	public boolean isSet() {
		return content != null;
	}

	public Object get() {
		return content;
	}

	public void set(Object content) {
		this.content = content;
	}

	public void serializeTo(HttpServletResponse httpResponse,
			SerializerChain serializerChain,
			HttpServletRequest httpRequest) throws IOException {

		if (!httpResponse.isCommitted()) {
			if (httpResponse.getContentType() == null) {
				httpResponse.setContentType("text/html; charset=utf-8");
			}

			OutputStream responseStream = GzipUtils.checkAndWrap(httpRequest, httpResponse, true);

			serializerChain.process(responseStream, content);

//			responseStream.flush();
			responseStream.close();
		}
	}

}

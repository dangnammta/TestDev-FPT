/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoaronal
 */
public class GzipUtils {

	private static final String ACCEPT_ENCODING = "Accept-Encoding";
	private static final String CONTENT_ENCODING = "Content-Encoding";

	private static final String GZIP = "gzip";
	private static final StringMatch STRING_MATCH = new StringMatch();

	private GzipUtils() {

	}

	public static OutputStream checkAndWrap(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse,
			boolean requireWantsHeader) throws
			IOException {
		OutputStream responseStream = httpResponse.getOutputStream();
		boolean acceptsGzip = Collections.list(httpRequest.getHeaders(ACCEPT_ENCODING)).stream().anyMatch(STRING_MATCH);
		boolean wantGzip = httpResponse.getHeaders(CONTENT_ENCODING).contains(GZIP);

		if (acceptsGzip) {
			if (!requireWantsHeader || wantGzip) {
				responseStream = new GZIPOutputStream(responseStream, true);
				addContentEncodingHeaderIfMissing(httpResponse, wantGzip);
			}
		}

		return responseStream;
	}

	private static void addContentEncodingHeaderIfMissing(HttpServletResponse response, boolean wantsGzip) {
		if (!wantsGzip) {
			response.setHeader(CONTENT_ENCODING, GZIP);
		}
	}

	private static class StringMatch implements Predicate<String> {

		@Override
		public boolean test(String s) {
			if (s == null) {
				return false;
			}
			return s.contains(GZIP);
		}
	}
}

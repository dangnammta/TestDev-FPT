package firo.embeddedserver.jetty;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import firo.utils.IOUtils;

public class HttpRequestWrapper extends HttpServletRequestWrapper {

	private byte[] cachedBytes;
	private boolean notConsumed = false;

	public HttpRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public boolean notConsumed() {
		return notConsumed;
	}

	public void notConsumed(boolean consumed) {
		notConsumed = consumed;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (cachedBytes == null) {
			cacheInputStream();
		}
		return new CachedServletInputStream();
	}

	private void cacheInputStream() throws IOException {
		cachedBytes = IOUtils.toByteArray(super.getInputStream());
	}

	private class CachedServletInputStream extends ServletInputStream {

		private ByteArrayInputStream byteArrayInputStream;

		public CachedServletInputStream() {
			byteArrayInputStream = new ByteArrayInputStream(cachedBytes);
		}

		@Override
		public int read() {
			return byteArrayInputStream.read();
		}

		@Override
		public int available() {
			return byteArrayInputStream.available();
		}

		@Override
		public boolean isFinished() {
			return available() <= 0;
		}

		@Override
		public boolean isReady() {
			return available() >= 0;
		}

		@Override
		public void setReadListener(ReadListener readListener) {
		}
	}
}

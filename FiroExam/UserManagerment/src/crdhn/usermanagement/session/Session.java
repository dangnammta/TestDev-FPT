/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.usermanagement.session;

import firo.Request;
import firo.utils.Assert;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/**
 *
 * @author hoaronal
 */
public class Session implements HttpSession {

	private final HttpSession session;

	Session(HttpSession session) {
		this.session = session;
	}

	@Override
	public long getCreationTime() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getId() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public long getLastAccessedTime() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ServletContext getServletContext() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setMaxInactiveInterval(int i) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int getMaxInactiveInterval() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public HttpSessionContext getSessionContext() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Object getAttribute(String string) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Object getValue(String string) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String[] getValueNames() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setAttribute(String string, Object o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void putValue(String string, Object o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void removeAttribute(String string) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void removeValue(String string) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void invalidate() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isNew() {
		return session.isNew();
	}

}

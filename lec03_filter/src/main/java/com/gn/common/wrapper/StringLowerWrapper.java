package com.gn.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class StringLowerWrapper extends HttpServletRequestWrapper {
	public StringLowerWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		return super.getParameter(name).toLowerCase();
	}
}

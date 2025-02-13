package com.gn.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PassWordEncodingWrapper extends HttpServletRequestWrapper{
	public PassWordEncodingWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		if(name.contains("member_pw")) {
			String ori = super.getParameter(name);
			System.out.println("암호화 전 : " + ori);
		}
		return super.getParameter(name);
	}
}

package com.gn.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.gn.common.wrapper.StringLowerWrapper;

@WebFilter("/*")
public class LowerCaseFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;

	public LowerCaseFilter() {
		super();
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		StringLowerWrapper slw = new StringLowerWrapper((HttpServletRequest)request);
		chain.doFilter(slw, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
}

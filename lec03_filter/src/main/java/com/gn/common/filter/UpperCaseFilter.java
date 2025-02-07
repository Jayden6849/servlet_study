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

import com.gn.common.wrapper.StringUpperWrapper;

@WebFilter("/*")
public class UpperCaseFilter extends HttpServlet implements Filter{
	private static final long serialVersionUID = 1L;

	public UpperCaseFilter() {
		super();
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("요청 필터링");
		StringUpperWrapper suw = new StringUpperWrapper((HttpServletRequest)request);
		chain.doFilter(suw, response);
		System.out.println("응답 필터링");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
}

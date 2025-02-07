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

//@WebFilter(servletNames="receiveDataServlet")
@WebFilter("/receive/data")
public class DataFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;
	
	public DataFilter() {
		super();
		System.out.println("[DataFilter] 생성 시점");
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("[DataFilter] 초기화 시점");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 요청을 가로채는 코드
		System.out.println("[DataFilter] 요청을 가로챔");
		// 기준점
		chain.doFilter(request, response);
		// 응답을 가로채는 코드
		System.out.println("[DataFilter] 응답을 가로챔");
	}
	
	@Override
	public void destroy() {
		System.out.println("[DataFilter] 소멸 시점");
	}
}

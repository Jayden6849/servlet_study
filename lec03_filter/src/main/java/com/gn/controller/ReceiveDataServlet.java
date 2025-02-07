package com.gn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="receiveDataServlet", urlPatterns="/receive/data")
public class ReceiveDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReceiveDataServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("=== 확인 ===");
		String testData = req.getParameter("test_data");
		System.out.println("데이터 : " + testData);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8"); // 필터에 박아놨기때문에 여기서 써줄 필요 없음
		doGet(req, resp);
	}
}

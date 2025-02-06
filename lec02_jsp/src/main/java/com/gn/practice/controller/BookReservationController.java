package com.gn.practice.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/borrow")
public class BookReservationController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public BookReservationController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String userPhone = req.getParameter("userPhone");
		String userEmail = req.getParameter("userEmail");
		String book = req.getParameter("book");
		String days = req.getParameter("days");
		String price = "";
		
		switch(book) {
		case "1":
			book = "자바 프로그래밍 입문";
			price = "1500";
			break;
		case "2":
			book = "웹 개발의 기초";
			price = "1800";
			break;
		case "3":
			book = "데이터베이스 시스템";
			price = "2000";
			break;
		}
		
		
		RequestDispatcher view = req.getRequestDispatcher("views/bookConfirmation.jsp");
		req.setAttribute("name", userName);
		req.setAttribute("phone", userPhone);
		req.setAttribute("email", userEmail);
		req.setAttribute("book", book);
		req.setAttribute("days", days);
		req.setAttribute("price", price);
		
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req,resp);
	}

}

package com.gn.practice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order")
public class PizzaController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public PizzaController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String userName = req.getParameter("user_name");
		String userPhone = req.getParameter("user_phone");
		String userEmail = req.getParameter("user_email");
		String size = req.getParameter("size");
		String[] adds = req.getParameterValues("add");
		String deliveryTime = req.getParameter("delivery_time");
		
		int price = 0;
		// 1. size 기준으로 가격이 다름
		switch(size) {
		case "1":
			price = 15900;
			break;
		case "2":
			price = 21000;
			break;
		case "3":
			price = 27900;
			break;
		}
		// 2. 토핑이 추가
		for(int i=0; i<adds.length; i++) {
			switch(adds[i]) {
			case "1":
				price += 2000;
				break;
			case "2": case "3": case "4":
				price += 1000;
				break;
			}
		}
//		System.out.println(price);
//		System.out.println(Arrays.toString(adds));
		
		RequestDispatcher view = req.getRequestDispatcher("views/pizzaPayment.jsp");
		req.setAttribute("name", userName);
		req.setAttribute("phone", userPhone);
		req.setAttribute("email", userEmail);
		req.setAttribute("size", size);
		req.setAttribute("toppings", adds);
		req.setAttribute("price", price);
		req.setAttribute("time", deliveryTime);
		view.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}

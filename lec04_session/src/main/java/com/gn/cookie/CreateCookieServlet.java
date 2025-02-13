package com.gn.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createCookie")
public class CreateCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateCookieServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Cookie 객체를 생성하고 유지시간 설정 후 response에 싣기
		System.out.println("=== 쿠키 생성 - 연결 확인 ===");
		Cookie cookie = new Cookie("user_id","user01");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		
		// 2. Root Path 로 이동하는 코드를 임의로 설정해봄
		response.sendRedirect("/");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

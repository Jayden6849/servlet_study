package com.gn.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/removeCookie")
public class RemoveCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RemoveCookieServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=== 쿠키 제거 - 연결 확인 ===");
		Cookie cookie = new Cookie("user_id", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		response.sendRedirect("/");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

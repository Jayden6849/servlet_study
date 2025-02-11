package com.gn.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/createSession")
public class CreateSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateSessionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.isNew() || session.getAttribute("member_id") == null) {
			// 객체가 원래 존재하지 않았거나, 존재했지만 "member_id"라는 키값을 지닌 세션이 없었던 경우
			session.setAttribute("member_id", "user01");
			session.setMaxInactiveInterval(60*30);
		} else {
			// "member_id"라는 키값을 지닌 세션이 본디 존재했던 경우
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

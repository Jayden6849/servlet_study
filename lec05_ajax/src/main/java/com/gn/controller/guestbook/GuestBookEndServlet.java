package com.gn.controller.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/guestBookEnd")
public class GuestBookEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestBookEndServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("guest_name");
		String msg = request.getParameter("guest_message");
//		System.out.println(name);
//		System.out.println(msg);
		
		JSONObject o1 = new JSONObject();
		o1.put("name", name);
		o1.put("msg", msg);
		
		response.setContentType("application/json; charset=UTF-8");
		
		response.getWriter().print(o1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

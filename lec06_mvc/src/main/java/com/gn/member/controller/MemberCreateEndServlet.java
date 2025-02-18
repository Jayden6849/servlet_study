package com.gn.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.gn.member.service.MemberService;
import com.gn.member.vo.Member;

@WebServlet(name="memberCreateEndServlet", urlPatterns="/memberCreateEnd")
public class MemberCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberCreateEndServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("member_no");
		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");
		String name = request.getParameter("member_name");
		
		Member m = new Member();
		m.setMemberId(no);
		m.setMemberId(id);
		m.setMemberPw(pw);
		m.setMemberName(name);
		
		// service에 데이터를 전달
		int result = new MemberService().createMember(m);

//		RequestDispatcher view = request.getRequestDispatcher("/views/member/create_fail.jsp");
//		
//		if(result > 0) {
//			view = request.getRequestDispatcher("/views/member/create_success.jsp");
//			view.forward(request, response);
//		} else {
//			view.forward(request, response);
//		}
		
		JSONObject obj = new JSONObject();
		obj.put("res_code", "500");
		obj.put("res_msg", "회원가입 중 오류가 발생하였습니다.");
		
		if(result > 0) {
			obj.put("res_code", "200");
			obj.put("res_msg", "회원가입이 완료되었습니다.");
		}
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(obj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

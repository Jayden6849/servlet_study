package com.gn.study.controller.send;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostSendServlet
 */
@WebServlet("/loginMember")
public class PostSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		System.out.println(id + pw);
		
		// 1. 출력 할 문서 형태 선언
		response.setContentType("text/html; charset=UTF-8");
		// 2. 터널(스트림)
		PrintWriter out = response.getWriter();
		// 3. 스트림을 통해 HTML 구문을 한줄씩 출력
		out.println("<!DOCTYPE html>");
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.println("<title>로그인 결과화면</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>반가워요 "+id+"님</h1>");
		out.println("<h2>비밀번호가 "+pw+"이시네요~</h2>");
		out.println("<a href=\"/\">홈페이지로 이동</a>");
		out.println("</body>");
		out.println("</html>");
		// 4. 터널 문닫기
		out.flush();

	}

}

package com.gn.study.controller.send;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSendServlet
 */
@WebServlet("/joinMember")
public class GetSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 1. 문자열 형태 데이터
		String userName = request.getParameter("userName");
		// 2. radio 타입 데이터
		String userGender = request.getParameter("userGender");
		switch(userGender) {
		case "1":
			userGender = "남성";
			break;
		case "2":
			userGender = "여성";
			break;
		}
		// 3. number 타입 데이터
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		// 4. checkbox 타입 데이터
		String[] hobby = request.getParameterValues("hobby");
		String hobbies = "";
		
		if(hobby == null) {
			hobbies += "없음";
		} else {
			for(int i=0; i<hobby.length; i++) {
				switch(hobby[i]) {
				case "1":
					hobby[i] = "야구";
					break;
				case "2":
					hobby[i] = "농구";
					break;
				case "3":
					hobby[i] = "축구";
					break;
				}
				if(i==hobby.length-1) {
					hobbies += hobby[i];
				} else {
					hobbies += hobby[i] + ", ";
				}
			}				
		}
		
		System.out.println("이름 : " + userName);
		System.out.println("성별 : " + userGender);
		System.out.println("나이 : " + userAge);
		System.out.println("취미 : " + hobbies);
		
		// 1. 출력 할 문서 형태 선언
		response.setContentType("text/html; charset=UTF-8");
		// 2. 터널(스트림)
		PrintWriter out = response.getWriter();
		// 3. 스트림을 통해 HTML 구문을 한줄씩 출력
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.println("<title>회원가입 결과화면</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>" + userName + "님, 환영합니다</h1>");
		out.println("<h2>앞으로도 자주 와주실꺼죠?</h2>");
		out.println("<a href='/'>홈페이지로 이동</a>");
		out.println("</body>");
		out.println("</html>");
		// 4. 터널 문닫기
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

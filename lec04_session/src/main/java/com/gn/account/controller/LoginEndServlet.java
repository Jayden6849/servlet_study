package com.gn.account.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gn.account.vo.Account;

@WebServlet("/loginEnd")
public class LoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginEndServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = request.getParameter("account_id");
		String accountPw = request.getParameter("account_pw");
		String rememberId = request.getParameter("remember_id");
//		System.out.println(accountId);
//		System.out.println(accountPw);
//		System.out.println(rememberId);
		
		// 1. 아이디, 비밀번호 일치하는 데이터가 있는지 확인
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Account account = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/login_project";
			String id = "scott";
			String pw = "tiger";
			
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "SELECT * FROM `account` WHERE `account_id` = ? AND `account_pw` = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, accountId);
			pstmt.setString(2, accountPw);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				account = new Account();
				account.setAccountNo(rs.getInt("account_no"));
				account.setAccountId(rs.getString("account_id"));
				account.setAccountPw(rs.getString("account_pw"));
				account.setAccountName(rs.getString("account_name"));
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		// 2-1 있다면? 사용자의 정보(번호, 아이디, 비밀번호, 이름) 담고있는 객체를 Session에 저장
		if(account != null) {
//			System.out.println(account);
			HttpSession session = request.getSession(true);
			if(session.isNew() || session.getAttribute("account") == null) {
				session.setAttribute("account", account);
				session.setMaxInactiveInterval(10);
			}
			// 아이디 정보 저장 o : Cookie에 아이디를 저장
			// 홈화면 이동 : 로그인한 사용자 정보 노출
			Cookie cookie = null;
			if(rememberId != null) {
				cookie = new Cookie("remember_id", account.getAccountId());
				cookie.setMaxAge(60*60*24*7);	// 일반적으로 id 저장은 7일간 쿠키로 남겨두는게 일반적
				response.addCookie(cookie);
			} else {
				cookie = new Cookie("remember_id", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		// 2-2 없다면? 로그인 페이지를 다시 요청할 것(sendRedirect();)
		} else {
//			System.out.println("로그인 실패");
			response.sendRedirect("/login");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}

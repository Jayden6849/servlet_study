package com.gn.member.service;

import java.sql.Connection;

import com.gn.member.dao.MemberDao;
import com.gn.member.vo.Member;

import static com.gn.common.sql.JDBCTemplate.getConnection;
import static com.gn.common.sql.JDBCTemplate.close;

public class MemberService {
	MemberDao md = new MemberDao();

	public int createMember(Member member) {
		Connection conn = getConnection();
		
		int result = md.createMember(conn, member);
		close(conn);
		
		return result;
	}
	
	public Member loginMember(String id, String pw) {
		Connection conn = getConnection();
		
		Member m = md.loginMember(conn, id, pw);
		close(conn);
		
		return m;
	}
	
	public int updateMember(int no, String pw, String name) {
		Connection conn = getConnection();
		
		int result = md.updateMember(conn, no, pw, name);
		close(conn);
		
		return result;
	}
	
	public Member selectMemberByNo(int no) {
		Connection conn = getConnection();
		
		Member m = md.selectMemberByNo(conn, no);
		close(conn);
		
		return m;
	}
}

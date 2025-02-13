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
}

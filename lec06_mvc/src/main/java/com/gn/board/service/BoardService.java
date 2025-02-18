package com.gn.board.service;

import static com.gn.common.sql.JDBCTemplate.close;
import static com.gn.common.sql.JDBCTemplate.commit;
import static com.gn.common.sql.JDBCTemplate.getConnection;
import static com.gn.common.sql.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.gn.board.dao.BoardDao;
import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;

public class BoardService {
	public int createBoard(Board b, Attach a) {
		Connection conn = getConnection();
		
		int result = 0;
		
		try {
			conn.setAutoCommit(false);
			
			int boardNo = new BoardDao().insertBoard(conn, b);
			a.setBoardNo(boardNo);
			
			int attachNo = new BoardDao().insertAttach(conn, a);
			
			if(boardNo != 0 && attachNo != 0) {
				// boardNo, attachNo의 insert 동작이 모두 이루어졌을 경우
				result = 1;
				commit(conn);
			} else {
				// 예외가 발생하진 않았지만 insert 동작이 이루어지지 않은 경우
				rollback(conn);
			}
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		}
		close(conn);
		
		return result;
	}
	
	public List<Board> selectBoardList(Board option) {
		Connection conn = getConnection();
		
		List<Board> resultList = new BoardDao().selectBoardList(conn, option);
		close(conn);
		
		return resultList;
	}
}

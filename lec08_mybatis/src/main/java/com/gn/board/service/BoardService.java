package com.gn.board.service;

import static com.gn.common.sql.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.gn.board.dao.BoardDao;
import com.gn.board.vo.Board;

public class BoardService {
	public List<Board> selectBoardList() {
		SqlSession session = getSqlSession(true);
		
		List<Board> resultList = new BoardDao().selectBoardList(session);
		session.close();
		/* 
		 * 만약 getSqlSession(false) 로 트랜잭션을 사용할 경우에는 조건을 걸어서 커밋을 하거나 롤백을 하면 된다.
		 * session.commit();
		 * session.rollback();
		*/
		
		return resultList; 
	}
	
	public Board selectBoardOne(int boardNo) {
		SqlSession session = getSqlSession(true);
		
		Board board = new BoardDao().selectBoardOne(session, boardNo);
		session.close();
		
		return board;
	}
	
	public Board selectBoardTwo(Map<String, String> paramMap) {
		SqlSession session = getSqlSession(true);
		
		Board board = new BoardDao().selectBoardTwo(session, paramMap);
		session.close();
		
		return board;
	}
	
	public Board selectBoardThree(Board option) {
		SqlSession session = getSqlSession(true);
		
		Board board = new BoardDao().selectBoardThree(session, option);
		session.close();
		
		return board;
	}
	
	public int updateBoard(Board board) {
		SqlSession session = getSqlSession(true);
		
		int result = new BoardDao().updateBoard(session, board);
		session.close();
		
		return result;
	}
	
	public int deleteBoard(int boardNo) {
		SqlSession session = getSqlSession(true);
		
		int result = new BoardDao().deleteBoard(session, boardNo);
		session.close();
		
		return result;
	}
	
	public int insertBoard(Board board) {
		SqlSession session = getSqlSession(true);
		
		int result = new BoardDao().insertBoard(session, board);
		session.close();
		
		return result;
	}
}

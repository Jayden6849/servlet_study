package com.gn.board.service;

import static com.gn.common.sql.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.gn.board.dao.BoardDao;
import com.gn.board.vo.Board;

public class BoardService {
	public List<Board> selectBoardList() {
		SqlSession session = getSqlSession();
		
		List<Board> resultList = new BoardDao().selectBoardList(session);
		session.close();
		
		return resultList; 
	}
	
	public Board selectBoardOne(int boardNo) {
		SqlSession session = getSqlSession();
		
		Board board = new BoardDao().selectBoardOne(session, boardNo);
		session.close();
		
		return board;
	}
	
	public Board selectBoardTwo(Map<String, String> paramMap) {
		SqlSession session = getSqlSession();
		
		Board board = new BoardDao().selectBoardTwo(session, paramMap);
		session.close();
		
		return board;
	}
	
	public Board selectBoardThree(Board option) {
		SqlSession session = getSqlSession();
		
		Board board = new BoardDao().selectBoardThree(session, option);
		session.close();
		
		return board;
	}
}

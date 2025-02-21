package com.gn.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Board;

@WebServlet("/boardDetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 넘기고싶은 매개변수가 하나일 때 : Board 객체에 담아서 다회용으로 보냄 */
		String temp = request.getParameter("board_no");
		
		int boardNo = 0;
		if(temp != null) {
			boardNo = Integer.parseInt(temp);
		}
		
		Board b1 = new BoardService().selectBoardOne(boardNo);
		System.out.println(b1);
		
		/* 넘기고 싶은 매개변수가 여러개일 때 : 1. 맵에 담아서 일회용으로 보냄 */
		
		String boardTitle = request.getParameter("board_title");
		String boardContent = request.getParameter("board_content");
		
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("board_title", boardTitle);
		paramMap.put("board_content", boardContent);
		
		Board b2 = new BoardService().selectBoardTwo(paramMap);
		System.out.println(b2);
		
		/* 넘기고 싶은 매개변수가 여러개일 때 : 2. 객체에 담아서 다회용으로 보냄 */
		Board option = new Board();
		option.setBoardTitle(boardTitle);
		option.setBoardContent(boardContent);
		
		option = new BoardService().selectBoardThree(option);
		System.out.println(option);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

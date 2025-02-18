package com.gn.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Board;

@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public BoardListServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardTitle = request.getParameter("board_title");
		String nowPage = request.getParameter("nowPage");
		
		// 1. 여러 검색 기준을 담을 수 있도록 바구니에 담음
		Board option = new Board();
		option.setBoardTitle(boardTitle);
		
		if(nowPage != null) {
			option.setNowPage(Integer.parseInt(nowPage));
		}
		
		// 2. 조회를 위해 페이징을 위한 데이터를 카운팅해서 option 객체에 담아줌
		int totalData = new BoardService().selectBoardCount(option);
		option.setTotalData(totalData);		 		// 내부적으로 calcPage()가 동작하게 구성해둠
		
		// 3. 해당 데이터까지 담아서 조회를 위해 서비스로 넘김
		List<Board> resultList = new BoardService().selectBoardList(option);
		
		// 4. DB에서 받아온 정보를 지닌 채로 view 단으로 넘김
		RequestDispatcher view = request.getRequestDispatcher("/views/board/list.jsp");
		request.setAttribute("resultList", resultList);
		request.setAttribute("paging", option);
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

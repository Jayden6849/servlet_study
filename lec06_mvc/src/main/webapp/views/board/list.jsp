<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.format.*" %>
<%@ page import="com.gn.board.vo.Board" %>

<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href='<%=request.getContextPath()%>/resources/css/board/list.css' rel="stylesheet" type="text/css">
<link href='<%=request.getContextPath()%>/resources/css/include/paging.css' rel="stylesheet" type="text/css">
<script src='<%=request.getContextPath()%>/resources/js/jquery-3.7.1.js'></script>
</head>
<body>
	<%@ include file="/views/include/header.jsp" %>
	<%@ include file="/views/include/nav.jsp" %>
	<% Board paging = (Board)request.getAttribute("paging"); %>
	<section>
		<div id="section_wrap">
			<div class="search">
				<form action="/boardList" name="search_board_form" method="get">
					<input type="text" name="board_title" placeholder="검색하고자 하는 게시글 제목을 입력하세요." value="<%= paging.getBoardTitle() == null ? "" : paging.getBoardTitle() %>">
					<input type="submit" value="검색">
				</form>
			</div>
			<div class="word">
				<h3>게시글 목록</h3>
			</div><br>
			<div class="board_list">
				<table>
					<colgroup>
						<col width="10%">
						<col width="50%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일시</th>
						</tr>
					</thead>
					<tbody>
						<% List<Board> boardList = (List<Board>)request.getAttribute("resultList"); %>
						<% DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm"); %>
						<% for(int i=0; i<boardList.size(); i++) { %>
							<tr data-board-no="<%= boardList.get(i).getBoardNo() %>">
								<td><%= (i+1) + (paging.getNowPage() - 1) * paging.getNumPerPage() %></td>
								<td><%= boardList.get(i).getBoardTitle() %></td>
								<td><%= boardList.get(i).getMemberName() %></td>
								<td><%= boardList.get(i).getRegDate().format(dtf) %></td>
							</tr>
						<% } %>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<% if(paging != null) { %>
		<div class="center">
			<div class="pagination">
				<% if(paging.isPrev()) { %>
					<a href="/boardList?nowPage=<%= (paging.getPageBarStart()-1) %>&board_title=<%= paging.getBoardTitle() == null ? "" : paging.getBoardTitle() %>">&laquo;</a>
				<% } %>
				<% for(int i=paging.getPageBarStart(); i<=paging.getPageBarEnd(); i++) { %>
					<a href="/boardList?nowPage=<%= i %>&board_title=<%= paging.getBoardTitle() == null ? "" : paging.getBoardTitle() %>"><%= i %></a>
				<% } %>
				<% if(paging.isNext()) { %>
					<a href="/boardList?nowPage=<%= (paging.getPageBarEnd()+1) %>&board_title=<%= paging.getBoardTitle() == null ? "" : paging.getBoardTitle() %>">&raquo;</a>
				<% } %>
			</div>
		</div>
	<% } %>
	<script>
		$('.board_list tbody tr').on('click', function(){
			const boardNo = $(this).data('board-no');
			location.href='/boardDetail?board_no='+boardNo;
		});
	</script>
</body>
</html>
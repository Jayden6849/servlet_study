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
</head>
<body>
	<%@ include file="/views/include/header.jsp" %>
	<%@ include file="/views/include/nav.jsp" %>
	<section>
		<div id="section_wrap">
			<div class="search">
				<form action="/boardList" name="search_board_form" method="get">
					<input type="text" name="board_title" placeholder="검색하고자 하는 게시글 제목을 입력하세요.">
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
							<tr>
								<td><%= boardList.get(i).getBoardNo() %></td>
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
</body>
</html>
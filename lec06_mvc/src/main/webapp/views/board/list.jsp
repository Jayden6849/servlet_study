<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach var="b" items="${resultList}" varStatus="vs">
									<tr data-board-no="${b.boardNo}">
										<td>${(vs.index + 1) + ((paging.nowPage - 1) * paging.numPerPage)}</td>
										<td>${b.boardTitle}</td>
										<td>${b.memberName}</td>
										<td>${b.regDate}</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					
					
						<%-- <% List<Board> boardList = (List<Board>)request.getAttribute("resultList"); %>
						<% DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"); %>
						<% for(int i=0; i<boardList.size(); i++) { %>
							<tr data-board-no="<%= boardList.get(i).getBoardNo() %>">
								<td><%= (i+1) + (paging.getNowPage() - 1) * paging.getNumPerPage() %></td>
								<td><%= boardList.get(i).getBoardTitle() %></td>
								<td><%= boardList.get(i).getMemberName() %></td>
								<td><%= boardList.get(i).getRegDate().format(dtf) %></td>
							</tr>
						<% } %> --%>
						
						<%--<c:forEach var="result" items="${resultList}" varStatus="status">
							<tr data-board-no="${result.boardNo}">
								<td>${(status.index + 1) + ((paging.nowPage - 1) * paging.numPerPage)}</td>
								<td>${result.boardTitle}</td>
								<td>${result.memberName}</td>
								<td>${result.regDate}</td>
							</tr>
						</c:forEach> --%>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<%-- <% if(paging != null) { 	%> --%>
	<c:if test="${not empty paging}">
		<div class="center">
			<div class="pagination">
				<%-- <% if(paging.isPrev()) { %>
					<a href="/boardList?nowPage=<%= (paging.getPageBarStart()-1) %>&board_title=<%= paging.getBoardTitle() == null ? "" : paging.getBoardTitle() %>">&laquo;</a>
				<% } %> --%>
				<%-- <% for(int i=paging.getPageBarStart(); i<=paging.getPageBarEnd(); i++) { %>
					<a href="/boardList?nowPage=<%= i %>&board_title=<%= paging.getBoardTitle() == null ? "" : paging.getBoardTitle() %>"><%= i %></a>
				<% } %> --%>
				<%-- <% if(paging.isNext()) { %>
					<a href="/boardList?nowPage=<%= (paging.getPageBarEnd()+1) %>&board_title=<%= paging.getBoardTitle() == null ? "" : paging.getBoardTitle() %>">&raquo;</a>
				<% } %> --%>
				<c:if test="${paging.prev}">
					<a href="/boardList?nowPage=${paging.pageBarStart-1}&board_title=${(paging.boardTitle eq null) ? '' : paging.boardTitle}">&laquo;</a>
				</c:if>
				<c:forEach var="i" begin="${paging.pageBarStart}" end="${paging.pageBarEnd}" step="1" varStatus="vs">
					<a href="/boardList?nowPage=${vs.index}&board_title=${(paging.boardTitle eq null) ? '' : paging.boardTitle}">${vs.index}</a>
				</c:forEach>
				<c:if test="${paging.next}">
					<a href="/boardList?nowPage=${paging.pageBarEnd+1}&board_title=${(paging.boardTitle eq null) ? '' : paging.boardTitle}">&raquo;</a>
				</c:if>
			</div>
		</div>
	</c:if>
	<%-- <% } %> --%>
	
	<script>
		$('.board_list tbody tr').on('click', function(){
			const boardNo = $(this).data('board-no');
			location.href='/boardDetail?board_no='+boardNo;
		});
	</script>
</body>
</html>
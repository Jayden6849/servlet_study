<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<ol>
		<li>
			<a href="/boardList">목록 조회</a>
		</li>
		<li>
			<c:url value="/boardDetail" var="url">
				<c:param name="board_no" value="7"/>
			</c:url>
			<a href="${url}">상세 조회 1 (조건이 한 개)</a>
		</li>
		<%-- <li>
			<c:url value="/boardDetail" var="detailUrl">
				<c:param name="board_title" value="제목"/>
				<c:param name="board_content" value="내용"/>
			</c:url>
			<a href="${detailUrl}">상세 조회 2(조건이 여러개)</a>
		</li>
		<li>
			<a href="${detailUrl}">상세 조회 3(조건이 여러개)</a>
		</li> --%>
		<li>수정</li>
		<li>삭제</li>
		<li>등록</li>
	</ol>
	<form action="<c:url value='/boardCreate'/>" method="post">
		<fieldset>
			<legend>게시글</legend>
			<input type="text" name="board_title"><br>
			<input type="text" name="board_content"><br>
			<input type="number" name="board_writer"><br>
			<input type="submit" value="등록">
		</fieldset>
	</form>
</body>
</html>
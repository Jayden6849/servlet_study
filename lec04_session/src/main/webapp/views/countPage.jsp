<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! int visitCount = 0; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조회수 카운트 페이지</title>
</head>
<body>
	<%
		Cookie cookie = new Cookie("visit_count", String.valueOf(visitCount++));
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
	%>
	<p>
		당신은 이 페이지를 
		<strong><%=visitCount%></strong>번 방문했습니다.
	</p>
</body>
</html>
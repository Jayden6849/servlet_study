<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 태그 연습</title>
</head>
<body>
	<% 
		List<String> list = new ArrayList<>();
		list.add("사과");
		list.add("바나나");
		list.add("포도");
	%>
	<h2>크기 : <%= list.size() %> </h2>
	<ul>
		<li><%= list.get(0) %></li>
		<li><%= list.get(1) %></li>
		<li><%= list.get(2) %></li>
	</ul>
	<ul>
		<%
			for(int i=0; i<list.size(); i++) {
				out.println("<li>"+list.get(i)+"</li>");
			}
		%>
	</ul>
	<ul>
		<% for(int i=0; i<list.size(); i++) { %>
			<li><%= list.get(i) %></li>
		<% } %>
	</ul>
</body>
</html>
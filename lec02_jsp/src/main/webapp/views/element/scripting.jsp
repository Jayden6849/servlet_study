<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립팅 요소 연습</title>
</head>
<body>
	<%-- Scriptlet : 일반적인 자바 코드--%>
	<%
		int sum = 0;
		for(int i=1; i<=10; i++) {
			sum += i;
		}
		out.println("총합(1) : "+sum);
	%>
	<br>
	<%-- Expression : 브라우저에 출력하기 위한 용도 --%>
	<%= "총합(2) : "+sum %>
	
	<%-- Declaration : 선언--%>
	<%!
		// iv 선언
		int visitCount = 0;
		// im 선언
		public int sumNum(int a, int b){
			return a+b;
		}
	%>
	<br>
	<% visitCount++; %>
	<%= "조회수 : "+visitCount %>
	<br>
	<%= "더하기 : "+sumNum(3,4) %>
</body>
</html>
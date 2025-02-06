<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userName = (String)request.getAttribute("name");
	String userPhone = (String)request.getAttribute("phone");
	String userEmail = (String)request.getAttribute("email");
	String book = (String)request.getAttribute("book");
	int price = Integer.parseInt((String)request.getAttribute("price"));
	int days = Integer.parseInt((String)request.getAttribute("days"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 대출 내역</title>
</head>
<body>
	<h1>도서 대출 내역</h1>
	<h3>[고객 정보]</h3>
	<ul>
		<li>고객명: <%= userName %></li>
		<li>전화번호: <%= userPhone %></li>
		<li>이메일: <%= userEmail %></li>
	</ul>
	<h3>[대출 정보]</h3>
	<ul>
		<li>도서 제목: <%= book %></li>
		<li>대출 기간: <%= days %>일</li>
	</ul>
	<h2>대출 금액: <%= price+(days-1)*500 %>원</h2>
	<h3>도서를 즐겁게 읽으세요!</h3>
</body>
</html>
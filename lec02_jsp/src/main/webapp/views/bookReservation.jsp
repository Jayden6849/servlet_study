<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 대출</title>
</head>
<body>
	<h1>도서 대출</h1>
	<form method="post" action="/borrow">
		<fieldset>
			<legend>고객 정보</legend>
			<label for="userName">고객명 : </label>
			<input type="text" id="userName" name="userName">
			<br>
			<label for="userPhone">전화번호 : </label>
			<input type="text" id="userPhone" name="userPhone">
			<br>
			<label for="userEmail">E-mail : </label>
			<input type="email" id="userEmail" name="userEmail">
		</fieldset>
		<fieldset>
			<legend>도서 선택</legend>
			<label for="book">도서 : </label>
			<select id="book" name="book">
				<option value="1">자바 프로그래밍 입문</option>
				<option value="2">웹 개발의 기초</option>
				<option value="3">데이터베이스 시스템</option>
			</select>
		</fieldset>
		<fieldset>
			<legend>대출 기간</legend>
			<label for="days">대출 기간 (일) :</label>
			<input type="number" id="days" name="days" style="width:50px;">
		</fieldset>
		<button>대출하기</button>
	</form>
</body>
</html>
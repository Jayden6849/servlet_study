<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaScript 방식</title>
</head>
<body>
	<input type="text" id="user_name">
	<input type="button" value="제출" onclick="jsGetTest();">
	<input type="button" value="제출(2)" onclick="jsPostTest();">
	<div id="result_div">
		
	</div>
	<script>
		const jsGetTest = function() {
			// 1. XMLHttpRequest 객체 생성
			const xhr = new XMLHttpRequest();
			// 2. open() 호출 - 방식, 주소, 동기/비동기
			const userName = document.getElementById("user_name").value;
			xhr.open("get","/jsAjaxGet?userName="+userName);
			// 3. onreadystatechange 서버 응답 처리 함수 생성
			xhr.onreadystatechange = function() {
				if(xhr.readyState == 4 && xhr.status == 200) {
					const result = xhr.responseText;
					document.getElementById("result_div").innerHTML += result;
				}
			};
			// 4. 요청보내기
			xhr.send();
		};
		const jsPostTest = function() {
			// 1. XMLHttpRequest 객체 생성
			const xhr = new XMLHttpRequest();
			// 2. open() 호출 - 방식, 주소, 동기/비동기
			xhr.open("post", "/jsAjaxPost");
			// 3. onreadystatechange 서버 응답 처리 함수 생성
			xhr.onreadystatechange = function() {
				if(xhr.readyState == 4 && xhr.status == 200) {
					document.getElementById("result_div").innerHTML += xhr.responseText;
				}
			};
			// 4. Content-type 설정
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
			// 5. send() 함수 설정
			const userName = document.getElementById("user_name").value;
			xhr.send("userName="+userName);
		};
	</script>
</body>
</html>
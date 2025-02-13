<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link href="/resources/css/member/create.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<%@ include file="../include/nav.jsp" %>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>회원가입</h3>
				<br>
				<span>
				※아이디는 추후에 수정할 수 없습니다. 
				<br>
				신중하게 선택해주세요.
				</span>
				<div class="create_member_form">
					<form action="/memberCreateEnd" method="post" name="create_member_form">
						<input type="text" name="member_id" placeholder="아이디"><br>
						<input type="password" name="member_pw" placeholder="비밀번호"><br>
						<input type="password" name="member_pw_check" placeholder="비밀번호 확인"><br>
						<input type="text" name="member_name" placeholder="닉네임"><br>
						<input type="button" value="회원가입" onclick="createMemberForm();">
					</form>
				</div>
				<div class="login">
					<a href="#">로그인</a>
				</div>
				
			</div>
		</div>
	</section>
	<script>
		const createMemberForm = function(){
			// 유효성검사를 한 후 아이디, 비밀번호, 비밀번호확인 모두 입력되어야 함
			// 비밀번호와 비밀번호 확인이 일치해야 데이터를 servlet으로 보낼 것임
			const form = document.create_member_form;
			if(!form.member_id.value) {
				alert('아이디를 입력하세요.');
				form.member_id.focus();	// 커서가 포커스되도록 만들 것임
			} else if(!form.member_pw.value) {
				alert('비밀번호를 입력하세요.');
				form.member_pw.focus();
			} else if(!form.member_pw_check.value) {
				alert('비밀번호 확인을 입력하세요.');
				form.member_pw_check.focus();
			} else if(form.member_pw.value != form.member_pw_check.value) {
				alert('비밀번호가 일치하지 않습니다.');
				form.member_pw_check.focus();
			} else if(!form.member_name.value) {
				alert('이름을 입력하세요.');
				form.member_name.focus();
			} else {
				// 유효성 검사를 모두 통과한 시점에 submit() 함수를 호출함
				// action 속성, method 속성대로 데이터를 전송함
				form.submit();
			}
		};
	</script>
</body>
</html>
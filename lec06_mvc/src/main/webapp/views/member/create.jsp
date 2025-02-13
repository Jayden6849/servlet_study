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
				<div class="create_member_form">
					<form action="" method="post">
						<input type="text" name="member_id" placeholder="아이디"><br>
						<input type="password" name="member_pw" placeholder="비밀번호"><br>
						<input type="password" name="member_pw_check" placeholder="비밀번호 확인"><br>
						<input type="text" name="member_name" placeholder="이름"><br>
						<input type="button" value="회원가입" onclick="createMemberForm();">
					</form>
				</div>
				<div class="login">
					<a href="#">로그인</a>
				</div>
				<h3>회원가입</h3>
				<br>
				<span>
				※아이디는 추후에 수정할 수 없습니다. 
				<br>
				신중하게 선택해주세요.
				</span>
			</div>
		</div>
	</section>
	<script>
		const createMemberForm = function(){
			alert('test');
		};
	</script>
</body>
</html>
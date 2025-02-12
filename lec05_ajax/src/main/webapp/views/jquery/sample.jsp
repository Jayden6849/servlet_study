<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery Ajax</title>
<!-- <script src="../../resources/jquery-3.7.1.js"></script> -->
<script src="<%=request.getContextPath()%>/resources/jquery-3.7.1.js"></script>
</head>
<body>
	<input type="text" id="userId" name="user_id">
	<button type="button" id="get_btn">Get방식</button>
	<div id="result_div">
		<!-- 통신의 결과를 보여줄 공간 -->
	</div>
	<script>
		$(function(){
			$('#get_btn').click(function(){
				const userId = $('#userId').val();
				$.ajax({
					url : "/jQueryAjaxGet",
					
				});
			});
		});
	</script>
</body>
</html>
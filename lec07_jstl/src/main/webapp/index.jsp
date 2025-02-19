<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL, JSTL</title>
</head>
<body>
	<h1>EL</h1>
	<h2>내장객체</h2>
	<% 
		// 1. pageScope :: 해당 페이지 안에서만 활동 가능한 범주
		pageContext.setAttribute("test", "페이지 스코프");
	
		// 2. requestScope :: 해당 요청이 응답되었을 때까지 활동 가능한 범주
		request.setAttribute("test", "리퀘스트 스코프");
		
		// 3. sessionScope :: 해당 브라우저에서 계속 활동 가능한 범주
		session.setAttribute("test", "세션 스코프");
		
		// 4. applicationScope :: 해당 애플리케이션 내에서 계속 활동 가능한 범주
		application.setAttribute("test", "애플리케이션 스코프");
	%>
	<h3>1. JSP 방식</h3>
	<ul>
		<li><%=pageContext.getAttribute("test") %></li>
		<li><%=request.getAttribute("test") %></li>
		<li><%=session.getAttribute("test") %></li>
		<li><%=application.getAttribute("test") %></li>
	</ul>
	<h3>2. EL 방식</h3>
	<p>${test}</p>
	<p>${pageScope.test}</p>
	<p>${requestScope.test}</p>
	<p>${sessionScope.test}</p>
	<p>${applicationScope.test}</p>
	<h3>3. 객체 꺼내오기</h3>
	<%@ page import="com.gn.vo.Person" %>
	<% request.setAttribute("person", new Person("철수", 77)); %>
	<ol>
		<li>
			<% Person p = (Person)request.getAttribute("person"); %>
			JSP 방식 : <%= p.getName() + " (" + p.getAge() + ")" %>
		</li>
		<li>
			EL 방식 : ${person.name} (${person.age})
		</li>
	</ol>
	<h2>EL의 연산자</h2>
	<%
		// 1. 숫자
		request.setAttribute("num1", 10);
		request.setAttribute("num2", 3);
		
		// 2. 문자
		request.setAttribute("str1", "사과");
		request.setAttribute("str2", "바나나");
		
		// 3. 객체
		request.setAttribute("p1", new Person("영희", 23));
		request.setAttribute("p2", null);
		
		// 4. 리스트
		request.setAttribute("list1", new ArrayList<>());
		List<String> list2 = new ArrayList<>();
		list2.add("오늘 날씨가 춥네요.");
		request.setAttribute("list2", list2);
	%>
	<h3>1. 산술 연산</h3>
	<p>
		10 + 3 = ${num1+num2}<br>
		10 - 3 = ${num1-num2}<br>
		10 * 3 = ${num1*num2}<br>
		10 / 3 = ${num1 div num2}<br>
		10 % 3 = ${num1 mod num2}<br>
	</p>
	<h3>2. 문자열 연결</h3>
	<p>
		${str1}, ${str2}
	</p>
	<h3>3. 대소 비교</h3>
	<p>
		num1이 num2보다 큰가요? ${num1 gt num2}<br>
		num1이 num2보다 작거나 같은가요? ${num1 le num2}<br>
	</p>
	<h3>4. 동등 비교</h3>
	<p>
		숫자 일치 : num1 == 10 ? ${num1 eq 10}<br>
		숫자 불일치 : num2 != 3 ? ${num2 ne 3}<br>
		문자 일치 : str1, str2 서로 같은가요? ${str1 eq str2}<br>
		문자 불일치 : str1, str2 서로 다른가요? ${str1 ne str2}<br>
	</p>
	<h3>5. 비어있는지 확인</h3>
	<ol>
		<li>문자 : ${empty str1}</li>
		<li>객체 : ${empty p2}</li>
		<li>컬렉션 : ${empty list1}</li>
	</ol>
	<ol>
		<li>문자 : ${not empty str1}</li>
		<li>객체 : ${not empty p2}</li>
		<li>컬렉션 : ${not empty list1}</li>
	</ol>
	<h3>5. 논리 연산</h3>
	<p>
		${true and true}<br>
		${num1 eq 10 and num2 eq 3}<br>
		${false or true}<br>
	</p>
	<hr>
	<h1>JSTL</h1>
	<h2>1. 변수</h2>
	<c:set var="n1" value="15"/>
	<c:set var="n2" value="20"/>
	<c:set var="result" value="${n1+n2}"/>
	<c:out value="n1+n2=${result}"/><br>
	
	<c:set var="hello" value="<b>안녕하세요</b>"/>
	<c:out value="${hello}"/><br>
	<c:out value="${hello}" escapeXml="false"/>
	
	<h2>2. 조건문(c:if)</h2>
	<c:if test="${num1 le num2}">
		<p>num1이 num2보다 작거나 같습니다.</p>
	</c:if>
	
	<h3>3. 조건문(c:choose)</h3>
	<c:choose>
		<c:when test="${num1 gt 20}">
			<p>num1이 20보다 큽니다.</p>
		</c:when>
		<c:when test="${num1 ge 10}">
			<p>num1이 10보다 크거나 같으면서, 20보다는 작거나 같습니다.</p>
		</c:when>
		<c:otherwise>
			<p>num1은 10보다 작습니다.</p>
		</c:otherwise>
	</c:choose>
</body>
</html>
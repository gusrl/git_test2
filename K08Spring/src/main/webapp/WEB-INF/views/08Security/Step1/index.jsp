<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />

<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<script>

</script>
<title>타이틀은 여기에</title>
</head>
<body>
	<div class="container">
		<h2>스프링 시큐리티 step1</h2>
		<h3>기본 설정으로 구현하기</h3>
		
		<form:form method="post" action="${pageContext.request.contextPath }/logout">
			<input type="submit" value="로그아웃" />
		</form:form>
		
		<!--
		logout 요청멍을 찾아가므로 사용불가 
		 <li>
			<a href="./logout">로그아웃링크</a>
		</li> 
		-->
	</div>
</body>
</html>
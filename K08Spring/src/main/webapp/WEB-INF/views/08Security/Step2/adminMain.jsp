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
<title>admin Main</title>
</head>
<body>
	<div class="container"> 
		<h2>스프링 시큐리티 Step2</h2>
		<h2>로그인 화면</h2>
		<h4>필요 권한 : ADMIN 만 접근 가능</h4>
		<form:form method="post" action="${pageContext.request.contextPath }/security2/logout">
			<input type="submit" value="로그아웃" />
		</form:form>
		<jsp:include page="/resources/common_link.jsp"/>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	

		<h2>스프링 시큐리티 Step2</h2>
		<h3>로그인 화면</h3>
		<h4>누구나 권한 없이 접근가능</h4>
		
	<c:url value="/login" var="loginUrl"> </c:url>
		<form:form  name="loginFrm" method="post" action="${loginUrl }">
			<c:if test="${param.error !=null }">
				<p>아이디와 패스워드가 잘못되었습니다.</p>
			</c:if>
			<c:if test="${param.lonin !=null }">
				<p>로그아웃 하였습니다.</p>
			</c:if>
			
		<!-- 이름 폼은 security context-xml 에서 속성명 지정한거랑 같이 name 따라 붙어야해 -->	
			<p> 아이디: <input type="text" name="id" value="kosmo_" /> </p>
			<p> 패스워드 : <input type="password" name="pass" /> </p>
			<button type="submit" class="btn btn-danger">로그인하기</button>
		</form:form>
		<jsp:include page="/resources/common_link.jsp" />

	</div>



</body>
</html>
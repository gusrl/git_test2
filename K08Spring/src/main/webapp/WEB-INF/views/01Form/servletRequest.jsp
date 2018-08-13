<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
<head>
<title>servletRequest.jsp</title>
</head>
<body>
<div class="container">

	<h2>Form 데이터 받기(Get방식)</h2>
	
	<ul>
		<li>아이디 :${id }</li>
		<li>패스워드 : ${pw }</li>
		<li>관리자메세지: ${message }</li>
	</ul>
</div>
</body>
</html>
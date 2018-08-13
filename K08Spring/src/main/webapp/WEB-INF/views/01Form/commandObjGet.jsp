<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>타이틀은 여기에</title>
</head>
<body> 
<div class="container">
	<h2>Form 데이터 받기</h2>
	
	<h3>커멘드객체를 이용하여 폼값 한번에 받기</h3>
	
	<ul>
		<li>이름 :${memberDTO.name }</li>
		<li>아이디 :${memberDTO.id }</li>
		<li>패스워드 :${memberDTO.pw }</li>
		<li>이메일 :${memberDTO.email }</li>
<%-- 	
		<li>이메일 :${memberDTO.mobile }</li>
		<li>이메일 :${memberDTO.addr }</li> 
											--%>
	</ul>
</div>
</body>
</html>
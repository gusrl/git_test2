<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>main1.jsp</title>
</head>
<body>
	<div class="container">
		<h2>Environment 객체를 이용한 외부 파일 사용하기 </h2>
		<h3>EnvAdmin.properties 파일에서 읽어온 Oracle 의 kosmo 계정 정보</h3>
		<!--
		admin.id=kosmo
		admin.pw=1234
		  키값    = 벨류값
		  -->
		<ul>
			<li>아이디 : ${adminID }</li>
			<li>비번 : ${adminPW }</li>
		</ul>
		
		<h2>EnvironmentAware 인터페이스 를 이용한 외부 파일 사용하기 </h2>
		<h3>EnvAdmin.properties 파일에서 읽어온 Oracle 의 kosmo 계정 정보</h3>

		<ul>
			<li>아이디 : ${adminID2 }</li>
			<li>비번 : ${adminPW2 }</li>
		</ul>
	</div>
</body>
</html>
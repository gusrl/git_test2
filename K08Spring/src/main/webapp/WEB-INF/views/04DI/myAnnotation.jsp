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
		<h2>어노테이션을 통한 JAVA 파일에서 DI 활용하기</h2>
		
		<ul>
			<li>홍길동 회원님 정보 : </li>
			<li>${memberInfo1 }</li>
		</ul>
		<ul>
			<li>[본인이름] 회원님의 정보: </li>
			<li>${memberInfo2 }</li>
		</ul>
	</div>
</body>
</html>
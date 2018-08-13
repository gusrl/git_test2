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
		<h2>XML 파일에 프로퍼티 파일을 명시한 후 외부파일 읽어오기</h2>
		
		<h3>메인괸리자 정보</h3>
		
		<ul>
			<li>아이디:${mainUserId }</li>
			<li>패스워드:${mainUserPw }</li>
		</ul>
		<h3>서브관리자 정보</h3>
		<ul>
			<li>아이디:${subUserId }</li>
			<li>패스워드:${subUserPw }</li>
		</ul>
	</div>
</body>
</html>
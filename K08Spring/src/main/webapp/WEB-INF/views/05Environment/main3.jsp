<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet"
	href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>타이틀은 여기에</title>
</head>
<body>
	<div class="container">
		<h2>어노테이션을 이용한 외부파일 참조하기</h2>

		<h3>게시판 설정값 읽어오기</h3>

		<ul>
			<li>아이디 : ${id }</li>
			<li>패스워드 : ${pass }</li>
			<li>드라이버 : ${driver }</li>
			<li>커넥트 URL : ${url }</li>
			
		</ul>


	</div>
</body>
</html>
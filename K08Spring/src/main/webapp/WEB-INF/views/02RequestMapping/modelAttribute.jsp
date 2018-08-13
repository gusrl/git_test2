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
			<h2>@RequestMapping 어노테이션</h2>
			<h3>@ModelAttribute 어노테이션으로 커맨드객체의 이름을 변경한후 뷰로 전달</h3>
		<ul>
			<li>이름 : ${si.name }</li>
			<li>${si.age }</li>
			<li>${si.gradeNum }</li>
			<li>${si.classNum }</li>
		</ul>
	</div>
</body>
</html>
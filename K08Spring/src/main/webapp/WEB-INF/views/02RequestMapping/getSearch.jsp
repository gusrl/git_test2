<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h2>@RequestMapping 어노테이션</h2>
		
		<h3>GET 방식으로 전송된 검색 파라미터</h3>
		
		<ul>
			<li>검색필드 : ${sColumn }</li>
			<li>검색단어 : ${sWord }</li>
		</ul>
		
	</div>
</body>
</html>
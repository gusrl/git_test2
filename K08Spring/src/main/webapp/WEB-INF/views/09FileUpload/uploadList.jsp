<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet"
	href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
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
		<h2>업로드 폴더의 파일 목록 보기</h2>

		<ul>
			<c:forEach items="${fileMap }" var = "file">
				<li>
					파일명 : ${file.key } &nbsp;&nbsp;
					파일크기 : ${file.value } kb
					<a href="download.do?fileName=${file.key }&oriFileName=임시파일명.jpg">
					
						다운로드
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
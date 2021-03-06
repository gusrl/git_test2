<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
	<h2>파일 업로드 결과보기</h2>
	
	<a href="./uploadForm.do">
		파일 업로드 폼 바로가기
	</a>
	
	<c:forEach begin="0" end="${returnObj.files.size()-1 }" var="i">
		 <ul>
		 	<li>제목${i+1 } : 
		 		${returnObj.files[i].title }</li>
		 	<li>제목${i+1 } : 
		 		${returnObj.files[i].originalName }</li>
		 	<li>제목${i+1 } : 
		 		${returnObj.files[i].saveFileName }</li>
		 	<li>전체경로${i+1 }:
		 		${returnObj.files[i].serverFullName }</li>
		 	<li>
		 		<img src="../resources/upload/${returnObj.files[i].saveFileName }" width="200"/>
		 	</li>				 	
		 </ul>
	</c:forEach>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/K08Spring/common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="/K08Spring/common/bootstrap3.3.7/css/bootstrap.min.css" />
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head> 
<title>PathVariable.jsp</title>
</head>
<body>
<div class="container">
	<h2>Form 데이터 받기</h2>
	
	<h3>@PathVariable 어노테이션으로 폼값 받기</h3>
	
	<ul>
		<li>회원아이디 : ${memberId }</li>
		<li>회원이름 : ${memberName }</li>
	</ul>
	
	<p style ="font-weight:bold" color:red; font-size:1.5em; >
	해당 파일의 경우 01form 하위에 다른 파일들과 동일한 경로이나 
	요청명이 "./form/gildong/길동" 의 형태로 1Depth 가 더 들어가서 js , css 파일의 링크는 ../ 를 한번더 붙여주거나 
	/컨텍스트루트와 같이 절대경로를 설정해야 한다.
	웹브라우저는 요청명을 단순 경로로 인식하기 때문이다.
	</p>
</div>
</body>
</html>
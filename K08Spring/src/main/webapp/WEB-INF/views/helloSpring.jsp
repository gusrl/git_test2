<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
<script>

</script>
<title>타이틀은 여기에</title>
</head>
<body>
		<div class="container">
			<h2>Hello SPRING</h2>
			<h3>첫번째 SPRING MVC 컨트롤러 만들기</h3>
			<!-- 이건 스프링에서 그대로 만들어 놓은건데 모델영역에서 가져오는거야 
			 model and view 영역 과 model 영역에서 이 두개밖에 없어 아직배운걸로는. -->
		<h4>컨트롤러에서 저장한 값 : ${firstMessage }</h4> 
		</div>
</body>
</html>
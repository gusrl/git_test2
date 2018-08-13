<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" ></link>
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
		<h2>트랜잭션(Transaction)</h2>
		
		<h3>트랜잭션 템플릿 사용한 구매 결과 보기</h3>
		
		<span style="color:red;font-size:1.5em">${successOrFail } </span>
		<ul>
			<li>고객아이디 : ${ticketInfo.customerId }</li>
			<li>티켓구매수 : ${ticketInfo.amount }</li>
		</ul>
		<p>
			<a href="./buyTicketTpl.do">
				티켓구매 페이지 바로가기
			</a>
		</p>
	</div>
</body>
</html>
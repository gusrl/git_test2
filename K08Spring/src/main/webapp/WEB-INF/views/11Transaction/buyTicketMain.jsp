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
	<h2>트랜젝션 (Transaction)</h2>
	
	<h3>트랜잭션 처리 없이 티켓 구매하기</h3>
	
	<h4>제약조건 :check(count<5): 티켓구매는 4장 까지만 가능함 . 5장 이상은 오류발생됨</h4>
	
	<form action="buyTicketAction.do" method="post" name="ticketFrm">
	
		<table>
			<tr>
				<td>고객 아이디</td>
				<td> <input type="text" name="customerId" />	</td>
			</tr>
			<tr>
				<td>티켓 구매수</td>
				<td> <select name="amount" >
					<% for(int i=1; i<=10; i++){ %>
					<option value="<%=i%>"><%=i %>장</option>
					<%} %>
				</select> </td>
			</tr>
			<tr>
				<td colspan="2">
				<button type="submit" class="btn btn-warning">구매하기</button>
				</td>	 
			</tr>
		</table>
		
	</form>
	</div>
</body>
</html>
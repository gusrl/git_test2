<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>index.jsp</title>
</head>
<body>
	
<div class="container">
	<h2>@RequestMapping 어노테이션</h2>
	
	<h3>GET 방식으로 전송하기 [검색폼]</h3>
	
	<script>
		function searchCheck(f){
			if(!f.searchWord.value){
				alert('검색어를 입력하세요');
				f.searchWord.focus();
				return false;
			}
		}
	</script>
	<form action="../reqeustMapping/getSearch" method="get" name ="searchFrm" onsubmit="return searchCheck(this);">
	
	<select name="searchColumn">
		<option value="title">제목</option>
		<option value="name">작성자</option>
		<option value="content">내용</option>
	</select>
		
		<input type="text" name="searchWord"/>
		
		<input type="submit" value="검색하기" />
	</form>	
	
	<h3>POST 방식으로 전송 [로그인폼]</h3>
	<%
		String ctxPath = request.getContextPath(); //request 를 이용해서 context 루트를 구해오는 녀석이야 
												   //이게 좋은 경우는 경로의 depth 에 따라 꼬일이유가 있어.
	%>
	<script>
		function loginCheck(f){
			
		}
	</script>
	<form action="<%=ctxPath%>/reqeusetMapping/postLogin" method="post"name="loginFrm" onsubmit="return loginCheck(this);">
		<table class="table table-bordered" style="width:400px;">
			<tr>
				<td>아이디</td>
				<td> <input type="text" name="user_id" />   </td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td> <input type="text" name="user_pw" />   </td>
			</tr>
			<tr>
				<td colspan="2"> 
					<input type="submit" value="로그인" />  
				</td>
				 
			</tr>
		
		</table>
	</form>
	
	<h3>@ModelAttribute 어노테이션 사용하여 커맨드 객체 이름 변경하기 </h3>
	<script>
	var stuCheck = function(){}
	</script>
	<form action="<%=ctxPath%>/requestMapping/modelAttribute"
		method="post" name="studentFrm" onsubmit="return stuCheck();">
	<table class="table table-bordered" style="width:400px;">
		<tr>
			<td>이름</td>
			<td>
				<input type="text" name="name" />
			</td>
		</tr>
		<tr>
			<td>나이</td>
			<td>
				<input type="text" name="age" />
			</td>
		</tr>
		<tr>
			<td>학년</td>
			<td>
				<select name="gradeNum">
					<option value="1학년">1학년</option>
					<option value="2학년">2학년</option>
					<option value="3학년">3학년</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>학반</td>
			<td>
				<input type="radio" name="classNum" value="1반" />1반
				<input type="radio" name="classNum" value="2반" />2반
				<input type="radio" name="classNum" value="3반" />3반
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
				<button type="submit">전송하기</button>
			</td>
		</tr>
	</table>
	
	</form>
	
	
</div>
	
	
	
	
	
	
	
	
</body>
</html>
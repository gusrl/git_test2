<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width,initial-scale=1" >
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js"></script>				
<script src="../common/bootstrap3.3.7/js/bootstrap.min.js"></script>

<title>view.jsp</title>
</head>
<body>	
<div class="container-fluid">
	<!-- Top영역 -->
	<div class="row">	
	<%@ include file="/resources/BoardSkin/commonTop.jsp" %>
	</div>
	<!-- Body영역 -->
	<div class="row">
		<!-- LNB영역 -->
		<div class="col-xs-3 col-md-3">
		<%@ include file="/resources/BoardSkin/commonLeft.jsp" %>
		</div>
		<!-- Contents영역 -->
		<div class="col-xs-9 col-md-9">
			<h3>게시판 상세보기 - 
				<small>Spring Framework 게시판입니다.</small></h3>
 
<form enctype="multipart/form-data">
<table class="table table-bordered">
<colgroup>
	<col width="20%"/>
	<col width="30%"/>
	<col width="20%"/>
	<col width="*"/>
</colgroup>
<tbody>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">번호</th>
		<td>
			${viewRow.idx }
		</td>
		<th class="text-center" 
			style="vertical-align:middle;">작성자</th>
		<td>
			${viewRow.name }
		</td>
	</tr>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">작성일</th>
		<td>
			${viewRow.postdate }
		</td>
		<th class="text-center" 
			style="vertical-align:middle;">조회수</th>
		<td>
			${viewRow.hits }
		</td>
	</tr>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">제목</th>
		<td colspan="3">
			${viewRow.title }
		</td>
	</tr>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">내용</th>
		<td colspan="3">
			${viewRow.contents }
		</td>
	</tr>
	<!-- <tr>
		<th class="text-center" 
			style="vertical-align:middle;">첨부파일</th>
		<td colspan="3">
			파일명.jpg
		</td>
	</tr> -->
</tbody>
</table>

<div class="row text-center" style="">
	<!-- 각종 버튼 부분 -->
	<button type="button" class="btn btn-info"
	onclick="location.href='reply.do?idx=${viewRow.idx}&nowPage=${nowPage }';"
	>답글쓰기</button>	
	<button type="button" class="btn btn-primary"
	onclick="location.href='password.do?idx=${viewRow.idx}&mode=modify&nowPage=${nowPage }';"
	>수정하기</button>
	<button type="button" class="btn btn-success"
	onclick="location.href='password.do?idx=${viewRow.idx}&mode=delete&nowPage=${nowPage }';"
	>삭제하기</button>		
	
	<button type="button" class="btn btn-warning" 
		onclick="location.href='list.do?nowPage=${param.nowPage}';">리스트보기</button>
</div>
</form> 
		</div>
	</div>
	<!-- Bottom영역 -->
	<div class="row" 
		style="border-top:1px solid #bbbbbb; 
			padding-top:20px; margin-top:20px;">
	<%@include file="/resources/BoardSkin/commonBottom.jsp" %>
	</div>
</div>
</body>
</html>
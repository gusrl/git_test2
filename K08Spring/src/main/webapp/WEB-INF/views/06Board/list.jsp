<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width,initial-scale=1" >
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js"></script>
<script src="../common/bootstrap3.3.7/js/bootstrap.min.js"></script>

<title>Spring 답변형 게시판</title>
</head>
<body>	
<div class="container-fluid">
	<!-- Top영역 -->
	<div class="row">	
		<jsp:include page="/resources/BoardSkin/commonTop.jsp" />	
	</div>
	<!-- Body영역 -->
	<div class="row">
		<!-- LNB영역 -->
		<div class="col-xs-3 col-md-3">
			<jsp:include page="/resources/BoardSkin/commonLeft.jsp" />
		</div>
		<!-- Contents영역 -->
		<div class="col-xs-9 col-md-9">
<h3>게시판 - 
	<small>이런저런 기능이 있는 게시판입니다.</small></h3>

<div class="row text-right" style="margin-bottom:20px;
		padding-right:50px;">
<!-- 검색부분 -->
<form class="form-inline">	
	<div class="form-group">
		<select name="keyField" class="form-control">
			<option value="title">제목</option>
			<option value="name">작성자</option>
			<option value="contents">내용</option>
		</select>
	</div>
	<div class="input-group">
		<input type="text" name="keyString"  class="form-control"/>
		<div class="input-group-btn">
			<button type="submit" class="btn btn-default">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</div>
	</div>
</form>	
</div>
<div class="row">
	<!-- 게시판리스트부분 -->	
	<h4>전체페이지:${totalPage }(현제페이지:${nowPage })</h4>
	
	<table class="table table-bordered table-hover">
	<colgroup>
		<col width="50px"/>
		<col width="*"/>
		<col width="100px"/>
		<col width="100px"/>
		<col width="70px"/>
		<%-- <col width="50px"/> --%>
	</colgroup>
	
	<thead>
	<tr class="success">
		<th class="text-center">번호</th>
		<th class="text-left">제목</th>
		<th class="text-center">작성자</th>
		<th class="text-center">작성일</th>
		<th class="text-center">조회수</th>
		<!-- <th class="text-center">첨부</th> -->
	</tr>
	</thead>
	
	<tbody>
<c:choose>
	<c:when test="${empty listRows }">
		<tr>
			<td colspan="6" class="text-center">
				등록된 게시물이 없습니다 ^^*
			</td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${listRows }" var="row" varStatus="loop">
			<!-- 리스트반복시작 -->
			<tr>
				<td class="text-center">${row.virtualNum }</td>
				<td class="text-left">
					<a href="./view.do?idx=${row.idx}
						&nowPage=${nowPage}">${row.title}</a>
				</td>
				<td class="text-center">${row.name }</td>
				<td class="text-center">${row.postdate }</td>
				<td class="text-center">${row.hits }</td>
				<!-- <td class="text-center">--</td> -->
			</tr>
			<!-- 리스트반복끝 -->
		</c:forEach>
	</c:otherwise>
</c:choose>
	
	</tbody>
	</table>
</div>
<div class="row text-right" style="padding-right:50px;">
	<!-- 각종 버튼 부분 -->
	<!-- <button type="reset" class="btn">Reset</button> -->
		
	<button type="button" class="btn btn-default" 
		onclick="location.href='./write.do';">글쓰기</button>
				
	<!-- <button type="button" class="btn btn-primary">수정하기</button>
	<button type="button" class="btn btn-success">삭제하기</button>
	<button type="button" class="btn btn-info">답글쓰기</button>
	<button type="button" class="btn btn-warning">리스트보기</button>
	<button type="submit" class="btn btn-danger">전송하기</button> -->
</div>
<div class="row text-center">
	<!-- 페이지번호 부분 -->
	<ul class="pagination">
		${pagingImg }
	</ul>	
</div>
		</div>
	</div>
	<!-- Bottom영역 -->
	<div class="row" 
		style="border-top:1px solid #bbbbbb; 
			padding-top:20px; margin-top:20px;">
		<jsp:include page="/resources/BoardSkin/commonBottom.jsp" />
	</div>
</div>
</body>
</html>





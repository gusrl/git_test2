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

<title>password.jsp</title>
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
			<h3>게시판 패스워드 확인 - 
				<small>Spring Framework로 제작한 게시판입니다.</small></h3>
<script>
function writeValidate(f){
	if(f.pass.value==""){
		alert("패스워드를 입력하세요");
		f.pass.focus();
		return false;
	}
}
</script>
<form name="writeFrm" method="post" 
	onsubmit="return writeValidate(this);"
	action="<c:url value="/board/passwordAction.do" />"
	>
<!-- 게시물 확인과 선택한액션(수정,삭제)을 구분하기
	위해 hidden폼이 필요함 -->
<input type="hidden" name="idx" value=${idx } />
<input type="hidden" name="mode" value=${param.mode } />
<input type="hidden" name="nowPage" 
						value=${param.nowPage } />	

<!-- 패스워드 검증에 실패했을때 에러메시지 출력용 -->
<span style="color:red; font-size:1.8em;">
	${isCorrMsg }
</span>
	
<table class="table table-bordered">
<colgroup>
	<col width="20%"/>
	<col width="*"/>
</colgroup>
<tbody>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">패스워드</th>
		<td>
			<input type="password" class="form-control" 
				style="width:200px;" name="pass" />
		</td>
	</tr>
</tbody>
</table>

<div class="row text-center" style="">
	<!-- 각종 버튼 부분 -->
	
	<button type="submit" class="btn btn-danger">전송하기</button>
	<button type="reset" class="btn">Reset</button>
	<button type="button" class="btn btn-warning" 
		onclick="location.href='./list.do?nowPage=${param.nowPage}';">리스트보기</button>
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





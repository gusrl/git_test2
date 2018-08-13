<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../resources/bootstrap3.3.7/jquery/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="../resources/bootstrap3.3.7/css/bootstrap.min.css" />
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>board.jsp</title>
<script>
	$(function(){		
		
		//페이지 로딩 이미지 삽입
		$('#boardHTML').html('<div style="text-align:center;padding-top:50px;"><img src="../common/images/loading02.gif"></div>');
		
		//리스트 페이지 가져오기 
		$.ajax({
			url :"./aList.do" , //요청경로 
			type : "get", //전송방식 
			contentType : "text/html; charset:utf-8", 
			data  : {}, //파라미터
			dataType : "html",//응답 데이터 형식 
			success  : function(d){//성공시 콜백메서드
				//alert("성공"+d);
				$('#boardHTML').html(d); //aList.do 의 출력내용을 HTML 형태로 아래 삽입 
			},
			error : function(e){//실패시 콜백메서드
				alert("실패"+e);
			}
			
		});
	});
</script> 
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
				<h3>
					Ajax&Json 활용한 방명록 <small>Ajax 와 JSON</small>
				</h3>
			
			<div class="row" style="padding:0 10px;" id="boardHTML">
				<h2 style="text-align:center;">
					여기에 게시판의 모든 폼이 출력됩니다.
				</h2>
			</div>





			</div>
		</div>
	 
	<!-- Bottom영역 -->
	<div class="row"
		style="border-top: 1px solid #bbbbbb; padding-top: 20px; margin-top: 20px;">
		<jsp:include page="/resources/BoardSkin/commonBottom.jsp" />
	</div>
	</div>
</body>
</html>
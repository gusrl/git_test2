<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
	function paging(pNum){
	 
		//리스트 페이지 가져오기 
		$.ajax({
			url :"./aList.do" , //요청경로 
			type : "get", //전송방식 
			contentType : "text/html; charset:utf-8", 
			data  : { nowPage : pNum}, //파라미터
			dataType : "html",//응답 데이터 형식 
			success  : function(d){//성공시 콜백메서드
				//alert("성공"+d);
				$('#boardHTML').html(d); //aList.do 의 출력내용을 HTML 형태로 아래 삽입 
			},
			error : function(e){//실패시 콜백메서드
				alert("실패"+e);
			}
			
		});
	}
	//삭제처리 
	function deleteRow(g_idx){
		$.ajax({
			url :"./deleteAction.do" , //요청경로 
			type : "get", //전송방식 
			contentType : "text/html; charset:utf-8", 
			data  : { idx : g_idx }, //파라미터
			dataType : "json",//응답 데이터 형식  (파싱)
			success  : function(d){//성공시 콜백메서드
				//alert("성공"+d);
				 if(d.statusCode==0){
					 //삭제 실패시 
					 alert("게시물 삭제를 실패했습니다.");
				 }
				 else if(d.statusCode==1){
				 	//로그인 전 요청 
				 	alert("로그인 후 삭제시도해주세요");
				 	location.href="../mybatis/login.do";
				 }
				 else if(d.statusCode==2){
					 //삭제 성공
					 alert("게시물이 삭제되었습니다");
					 $("#guest_"+g_idx).hide(1000);
				 }
			},
			error : function(e){//실패시 콜백메서드
				alert("실패"+e.status+":"+e.statusText);
			}
			
		});
	}
</script>
<div class="row" style="text-align: right; border-bottom: 1px solid #dddddd; padding: 10px;">
					<button class="btn btn-success" onclick="location.href='write.do';">
						방명록쓰기</button>
				</div>
				<!-- 방명록 반복부분 S -->
				<c:forEach items="${lists }" var="row">
					<div class="media" id="guest_${row.idx }">
					<div class="media-left">
						<img src="../common/img_avatar3.png" class="media-object" style="width: 60px">
					</div>
					<div class="media-body">
						<h4 class="media-heading">작성자: ${row.name }(${row.id })</h4>
						<p>
							 ${row.contents }
						</p>
					</div>
					
					<!-- 삭제 버튼  -->
					<div class="media-right">				 
					 <button class="btn btn-danger"onclick="javascript:deleteRow(${row.idx});">삭제</button>
					</div>
				</div>
				</c:forEach>
				
				<!-- 방명록 끝부분 E -->
				<!-- 페이지번호 -->
				<div class="row text-center">
				<!-- 페이지 번호 부분 -->
					<ul class="pagination">${pagingImg }
					</ul>
				</div>
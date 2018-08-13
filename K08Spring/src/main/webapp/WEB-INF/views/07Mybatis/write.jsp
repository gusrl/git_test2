<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js"></script>
<script src="../common/bootstrap3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function writeValidate(f){
		if(f.name.value==""){
			alert("작성자 이름 를 입력하세요");
			f.name.focus();
			return false;
		}
		if(f.contents.value==""){
			alert("내용을  입력하세요");
			f.contents.focus();
			return false;
		}
	}
</script>

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
				<h3>
					방명록 -(글쓰기) <small>Mybatis로 제작한 방명록 입니다.</small>
				</h3>
				
				 <form name="writeFrm" method="post" onsubmit="return writeValidate(this);"
  				 action="<c:url value="/mybatis/writeAction.do" />" >
   
<table class="table table-bordered">
<colgroup>
   <col width="20%"/>
   <col width="*"/>
</colgroup>
<tbody>
   <tr>
      <th class="text-center" 
         style="vertical-align:middle;">작성자</th>
      <td>
         <input type="text" class="form-control" 
            style="width:100px;" name="name" 
               value="${sessionScope.siteUserInfo.name }" />
      </td>
   </tr>
   <tr>
      <th class="text-center" 
         style="vertical-align:middle;">내용</th>
      <td>
         <textarea rows="10" class="form-control" name="contents"></textarea>
      </td>
   </tr>   
</tbody>
</table>

<div class="row text-center" style="">
   <!-- 각종 버튼 부분 -->
   
   <button type="submit" class="btn btn-danger">전송하기</button>
   <button type="reset" class="btn">Reset</button>
   <button type="button" class="btn btn-warning" 
      onclick="location.href='list.do';">리스트보기</button>
</div>
</form> 
 
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





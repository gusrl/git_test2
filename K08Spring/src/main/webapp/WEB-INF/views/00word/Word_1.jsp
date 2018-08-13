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
								
				<h3>13. Database에서 Index란?</h3> <hr /> <br />
				  인덱스는 데이터베이스 분야에 있어서 테이블에 대한 동작의 속도를 높여주는 자료 구조를 일컫는다. <br />
				  인덱스는 테이블 내의 1개의 컬럼, 혹은 여러 개의 컬럼을 이용하여 생성될 수 있다.<br />
				  고속의 검색 동작뿐만 아니라 레코드 접근과 관련 효율적인 순서 매김 동작에 대한 기초를 제공한다.<br />
				  인덱스를 저장하는 데 필요한 디스크 공간은 보통 테이블을 저장하는 데 필요한 디스크 공간보다 작다.<br />
				
				  데이터베이스에서 테이블과 클러스터에 연관되어 독립적인 저장 공간을 보유하고 있는 객체(object)이다. <br />
				  사용자는 데이터베이스에 저장된 자료를 더욱 빠르게 조회하기 위하여 인덱스를 생성하고 사용한다. <br />
				
				  DB에서 자료를 검색하는 두 가지 방법<br />
				  FTS(Full Table Scan) : 테이블을 처음 부터 끝까지 검색하는 방법<br />
				  Index Scan : 인덱스를 검색하여 해당 자료의 테이블을 액세스 하는 방법.<br />
				
				<!-- 방명록 끝부분 E -->

			</div>

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
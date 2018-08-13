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

<title>reply.jsp</title>
</head>
<body>
	<div class="container-fluid">
		<!-- Top영역 --> 
		<div class="row">
			<%@ include file="/resources/BoardSkin/commonTop.jsp"%>
		</div>
		<!-- Body영역 -->
		<div class="row">
			<!-- LNB영역 -->
			<div class="col-xs-3 col-md-3">
				<%@ include file="/resources/BoardSkin/commonLeft.jsp"%>
			</div>
			<!-- Contents영역 -->
			<div class="col-xs-9 col-md-9">
				<h3>
					게시판 글쓰기 - <small>Spring Framework로 제작한 게시판입니다.</small>
				</h3>
				<script>
					function writeValidate(f) {
						if (f.name.value == "") {
							alert("이름을 입력하세요");
							f.name.focus();
							return false;
						}
						if (f.title.value == "") {
							alert("제목을 입력하세요");
							f.title.focus();
							return false;
						}
						if (f.contents.value == "") {
							alert("내용을 입력하세요");
							f.contents.focus();
							return false;
						}
						if (f.pass.value == "") {
							alert("비밀번호를 입력하세요");
							f.pass.focus();
							return false;
						}
					}
				</script>
				<form name="writeFrm" method="post"
					onsubmit="return writeValidate(this);"
					action="<c:url value="/board/replyAction.do" />">

					<input type="hidden" name="idx" value="${idx}" /> <input
						type="hid den" name="bgroup" value="${replyRow.bgroup}" /> <input
						type="hid den" name="bstep" value="${replyRow.bstep}" /> <input
						type="hid den" name="bindent" value="${replyRow.bindent}" /> <input
						type="hid den" name="nowPage" value="${param.nowPage}" />

					<table class="table table-bordered">
						<colgroup>
							<col width="20%" />
							<col width="*" />
						</colgroup>
						<tbody>
							<tr>
								<th class="text-center" style="vertical-align: middle;">작성자</th>
								<td><input type="text" class="form-control"
									style="width: 100px;" name="name" value=${replyRow.name } /></td>
							</tr>
							<tr>
								<th class="text-center" style="vertical-align: middle;">패스워드</th>
								<td><input type="password" class="form-control"
									style="width: 200px;" name="pass" /></td>
							</tr>
							<tr>
								<th class="text-center" style="vertical-align: middle;">제목</th>
								<td><input type="text" class="form-control" name="title"
									value="${replyRow.title}" /></td>
							</tr>
							<tr>
								<th class="text-center" style="vertical-align: middle;">내용</th>
								<td><textarea rows="5" class="form-control" name="contents">${replyRow.contents}</textarea>
								</td>
							</tr>
							<!-- <tr>
		<th class="text-center" 
			style="vertical-align:middle;">첨부파일</th>
		<td>
			<input type="file" class="form-control" />
		</td>
	</tr> -->
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
			style="border-top: 1px solid #bbbbbb; padding-top: 20px; margin-top: 20px;">
			<%@include file="/resources/BoardSkin/commonBottom.jsp"%>
		</div>
	</div>
</body>
</html>





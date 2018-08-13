<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../common/jquery/jquery-3.2.1.js"></script>
<link rel="stylesheet"
	href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
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
		<h2>파일 업로드 폼</h2>

		<form name="fileFrm" method="post" action="uploadAction.do"
			enctype="multipart/form-data">

			<table class="table table-bordered" style="width: 500px;">
				<colgroup>
					<col width="20%" />
					<col width="*" />
				</colgroup>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="제목입니다." /></td>
				</tr>
				<tr>
					<th>첨부파일 1</th>
					<td><input type="file" name="userfile1" /></td>
				</tr>
				<tr>
					<th>첨부파일2</th>
					<td><input type="file" name="userfile2" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
						<button type="submit" class="btn btn-danger">
							전송하기
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
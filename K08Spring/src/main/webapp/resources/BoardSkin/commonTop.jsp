<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<!-- 좌상단 로고 영역 -->
		<div class="navbar-header">
			<a href="../" class="navbar-brand">JAVA37기</a>

		</div>

		<!-- 메뉴부분 -->
		<ul class="nav navbar-nav">
			<li class="active"><a href="../">홈으로</a></li>

			<li><a href="../board/list.do">답변형 게시판</a></li>
			<li><a href="../mybatis/list.do">방명록</a></li>
			<li><a href="../jsonUse/board.do">Ajax게시판</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> 웹표준 <span class="caret"></span>
			</a>
				<ul class="dropdown-menu">
					<li><a href="#">HTML</a></li>
					<li><a href="#">CSS</a></li>
					<li><a href="#">JavaScript</a></li>
				</ul></li>
			<li><a href="#">Oracle</a></li>
			<li><a href="#">JSP</a></li>
		</ul>

		<!-- 검색폼 -->
		<form class="navbar-form navbar-left">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="검색" />
				<div class="input-group-btn">
					<button type="submit" class="btn btn-default">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
		</form>

		<!-- 우측메뉴 -->
		<ul class="nav navbar-nav navbar-right">
			<%
				if (session.getAttribute("siteUserInfo") == null) {
			%>
			<li><a href="javascript:alert('준비중1');"> <span
					class="glyphicon glyphicon-user"></span>회원가입
			</a></li>
			<li><a href="../mybatis/login.do;"><span
					class="glyphicon glyphicon-log-in"></span>로그인 </a></li>
			<%
				} else {
			%>
			<!-- 로그인후 -->
			<li><a href="javascript:alert('준비중1');"> <span
					class="glyphicon glyphicon-cog"></span>회원가입 수정
			</a></li>
			<li><a href="../mybatis/logout.do;"><span
					class="glyphicon glyphicon-log-out"></span>로그아웃 </a></li>
			<%
				}
			%>
		</ul>
	</div>
</nav>
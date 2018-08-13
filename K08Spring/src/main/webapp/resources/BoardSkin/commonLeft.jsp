<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<ul class="nav nav-pills nav-stacked">
	<li
		style="height: 100px; padding-top: 20px; text-align =center; color: #ffffff; background-color: #000066;">
		<h3>SPRING 수업</h3>
	</li>
	<li><a href="../board/list.do">답변형 게시판</a></li>
	<li><a href="../mybatis/list.do">방명록</a></li>
	<li><a href="../jsonUse/board.do">Ajax게시판</a></li>
	<!-- <li><a href="../">자료실게시판</a></li> -->
	<li class="dropdown"><a href="#" class="dropdown-toggle"
		data-toggle="dropdown"> 드롭다운메뉴 <span class="caret"></span>
	</a>
		<ul class="dropdown-menu">
			<li><a href="#">드롭1</a></li>
			<li><a href="#">드롭2</a></li>
			<li><a href="#">드롭3</a></li>
		</ul></li>
</ul>
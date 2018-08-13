<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
	ul{
		list-style-type:none;
	}
	ul li{
		float:left;
		border:1px solid #888888;
		padding-right:11px;
	}
</style>

<ul>
	<li> <a href="${pageContext.request.contextPath}/">홈바로가기</a> </li>
	<li> <a href="${pageContext.request.contextPath}/security2/index.do">프론트 페이지 바로가기</a> </li>
	<li> <a href="${pageContext.request.contextPath}/security2/admin/main.do">관리자페이지 바로가기</a> </li>
	<li> <a href="${pageContext.request.contextPath}/security2/accessDenied.do">접근불가 페이지 바로가기</a> </li>
</ul>
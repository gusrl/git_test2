<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.net.URLEncoder"%>
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
	function loginValidate(f){
		if(f.id.value==""){
			alert("아이디를 입력하세요");
			f.id.focus();
			return false;
		}
		if(f.pass.value==""){
			alert("패스워드 를 입력하세요");
			f.pass.focus();
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
					방명록 -(로그인) <small>Mybatis로 제작한 방명록 입니다.</small>
				</h3>
				
					<c:choose>
					<c:when test="${not empty sessionScope.siteUserInfo }">
						<div class="row" style="border:2px solid #cccccc; padding:10px;">
							<h4>아이디 : ${sessionScope.siteUserInfo.id }</h4>
							<h4>이름 : ${sessionScope.siteUserInfo.name }</h4>
							<br /> <br />
							
							<button onclick="location.href='logout.do';">로그아웃</button>
							<button height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/>
						</div>
					</c:when>
					
					<c:otherwise>
					<span style="font-size:1.5em; color:red;">${LoginNG }</span>
				         <form name="loginForm" method="post" action="./loginAction.do" onsubmit="return loginValidate(this);">
				         <div class="row" style="border:1px solid #dddddd; padding:10px;">
				          <input type="hidden" name = "backUrl" value="${param.backUrl }" />
				            <div class="col-sm-3"></div>
				            <div class="col-sm-5">
				               <div class="input-group">
				                  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
				                  <input type="text" class="form-control" name="id" placeholder="아이디">
				               </div>
				               <div class="input-group" style="margin-top:10px;">
				                  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
				                  <input type="password" class="form-control" name="pass" placeholder="패스워드">
				               </div>
				            </div>
				            <div class="col-sm-1">
				            	
				               <button type="submit" class="btn btn-primary" style="height:77px; width:77px;">로그인</button>
				             
				            </div>
				            
				            <div class="col-sm-3"></div> 
				            
          
				         </div>   

				            	 <%
								     String clientId = "0nntnYs3hIse2x_a7b7o";//애플리케이션 클라이언트 아이디값";
								     String redirectURI = URLEncoder.encode("/mybatis/callback.do", "UTF-8");
								     SecureRandom random = new SecureRandom(); 
								     String state = new BigInteger(130, random).toString();
								     String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
								     apiURL += "&client_id=" + clientId;
								    apiURL += "&redirect_uri=" + redirectURI;
								    apiURL += "&state=" + state;
								    session.setAttribute("state", state);
								 %>
								  <br />
								  
								<div style="text-align:right; margin-right:200px;">
	  								<a href="<%=apiURL%>"><font color = "green"><h4>네이버 아이디로 로그인 </h4> </font>
	  									<img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/>
	  								
	  								</a>
  								</div>
				         </form>
					</c:otherwise>
				</c:choose>
 
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





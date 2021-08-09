<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 보기</title>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
 });//end ready
</script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">	
<style type="text/css">
	* {
		font-family: 'Nanum Gothic', sans-serif;
		font-size: 15px;
		font-weight : 400;
	} 
</style>
</head>
<body>

<div class="container">
    <div class="row profile">
		<div class="col-md-3">
			<div class="profile-sidebar">
				<!-- SIDEBAR USERPIC -->
				<div class="profile-userpic">
					<img src="/Dong-Dong/images/profile/${userImage}" class="img-responsive" alt="">
				</div>
		
				</div>
			
				<div class="profile-usermenu">
					<ul class="nav">
					
						<li>
							<a href="#">
							<i class="glyphicon glyphicon-user"></i>
							${nickName}</a>
						</li>
						<li>
							<a href="#" >
							<i class="glyphicon glyphicon-ok"></i>
							거래량  ${saleCount}개 </a>
						</li>
					
					</ul>
				</div>
				<!-- END MENU -->
			</div>
		</div>
		<div class="col-md-9">
            <div class="profile-content">
            <i class="glyphicon glyphicon-comment"></i><br>
			   안녕하세요.
            </div>
		</div>
		<c:if test="${!empty login && login.userid!=userid}">
			<div style="text-align : right">
				<a class="btn btn-danger" href="loginCheck/complaintDetail?userid=${userid}&coType=1" id="complaintUser">신고</a>
			</div>
		</c:if>
	</div>
</body>
</html>
<%@page import="com.dto.MemberDTO"%>
<%@page import="sun.net.www.content.image.png"%>
<%@page import="java.util.List"%>
<%@page import="com.dto.MyOrderSheetDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>메세지 더보기</title>
<style type="text/css">
.form-group{
	margin: 10px;
}

</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
    	//상대프로필보기
    	$("#user").click(function() {
			location.href="../userprofile?userid=${message[0].sUserid}";
		})
    	
    	$("#chat").click(function() {
			location.href="/chat?pNum=${message[0].pNum}&bUserid=${message[0].bUserid}&sUserid=${message[0].sUserid}";
		})
		//삭제버튼
		$("#delete").on("click", function() {
			var num = $(this).attr("data-xxx");
			var popup = "1";
			console.log(num);
			location.href="/loginCheck/PopupOrderDel?oNum="+num+"&popup="+popup; 
		});//end delBtn
		
		//구매확정 버튼
		$("#confirm").click(function() {
			location.href="/loginCheck/Sale?oNum="+"${message[0].oNum}"+"&pNum="+"${message[0].pNum}"+"&bUserid="+"${message[0].bUserid}"+"&sUserid="+"${message[0].sUserid}";
		})
		
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
<br><br>
<div class="container">
    <div class="row profile">
		<div class="col-md-3">
				<div class="form-group">
					<label for="주문 메세지">주문 메세지</label>
					<textarea class="form-control rounded-0"
						 rows="10" readonly> ${message[0].oMessage}</textarea>
				</div>
				<div class="profile-usermenu">
					<ul class="nav">
					
						<li id="user">
							<a href="#">
							<i class="glyphicon glyphicon-user"></i>
							${message[0].bUserid}</a>
						</li>
						
					
					</ul>
				</div>
				<!-- END MENU -->
			</div>
		</div>
		<br>
		<div style="text-align: center;">
		<button id="confirm">구매확정</button>&nbsp;<button id="chat">채팅</button>&nbsp;<button id="delete" data-xxx="${message[0].oNum}">메세지 삭제</button>
		</div>
	</div>
</body>
</html>
<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		var postWriter = "${param.sUserid}";
		
		$("form").submit(function() {
			var oPrice = $("#oPrice").val();
			var oAddr = $("#oAddr").val();
			var oMessage = $("#oMessage").val();
			
			
			if (oPrice.length == 0) {
				alert("희망가격을 입력하세요")
				$("#oPrice").focus();
				return false;
			}
			if (oAddr.length == 0) {
				alert("희망 장소를 입력하세요");
				$("#oAddr").focus();
				return false;
			}

			if (oMessage.length < 10) {
				alert("하고싶은 내용을 10자 이상 작성해주세요");
				$("#oMessage").focus();
				return false;
			}
			
			opener.parent.sendMessage(postWriter, 'o'); // 부모창에있는 sendMessage함수 호출
		}) //form end
		
	})
</script>

<body>
	<form action="/AddOrderSheet">
		<input type="hidden" name="bUserid" value="${login.userid}"> 
		<input type="hidden" name="sUserid" value="${param.sUserid}"> 
		<input type="hidden" name="pNum" value="${param.pNum}">
		<div class="container">
			<div class="row">
				<div class="col-md-offset-5 col-md-3">
					<div class="form-login">
						<h4 style ="text-align:center">주문서 작성</h4>
						희망 가격 
						<input type="text" name="oPrice" id="oPrice" value="${param.pPrice}" class="form-control input-sm chat-input" />
						</br> 
						희망 거래 장소 
						<input type="text" name="oAddr" id="oAddr" class="form-control input-sm chat-input" placeholder="희망거래장소" />
						</br> 
						전달하고 싶은 내용 (10자 이상 작성해주세요) 
						<input type="text" name="oMessage" id="oMessage" class="form-control input-sm chat-input" placeholder="거래 날짜, 방법 등" /> 
						</br>
						<div class="wrapper">
							<span class="group-btn"> 
								<input type="submit" class="btn btn-primary btn-md" value="전송">
							</span>
						</div>
					</div>

				</div>
			</div>
		</div>
	</form>
</body>
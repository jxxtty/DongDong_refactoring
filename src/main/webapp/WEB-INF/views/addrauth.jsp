<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>        
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<style type="text/css">
* {
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 15px;
	font-weight : 400;
}
.auth {
	text-align: center;
	border: solid 3px gray;
	margin: auto;
	padding: auto;
}
</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	$("#auth1").click(function() {
    		 var addr11 = $("#addrA1").text();
    		console.log(addr11);
			opener.$("#addr").val(addr11); 
			window.close(); 
		});
    	$("#auth2").click(function() {
    		var addr22 = $("#addrA2").text();  
    		console.log(addr22);
			opener.$("#addr").val(addr22);
			window.close();
		});
 });//end ready
</script>
</head>
<body>
<c:choose>
<c:when test="${auth1 != null}">
<div class="auth">
<h4>${auth1}&nbsp;인증성공!<br>우리 동네는&nbsp;<span id="addrA1">${auth1}</span>!!</h4>
<button id="auth1" >확인</button>
</div>
</c:when>
<c:when test="${auth1 == null}">
<div class="auth">
<h4>현재 위치로 인증성공!<br>우리 동네는&nbsp;<span id="addrA2">${auth2}</span>!! </h4>
<button id="auth2">확인</button>
</div>
</c:when>
</c:choose>




</body>
</html>
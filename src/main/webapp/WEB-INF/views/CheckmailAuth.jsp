<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		console.log("${email1}");

		$("button").click(function() {
			var authnum = $("#Authnum").val();
			var mailAuth = "인증완료";
			
	    	if ( authnum != "${Authcode}") {
				alert("인증 실패");
				event.preventDefault();	
				window.close(); 
			}else {
				alert("인증되었습니다.")
				opener.$("#result4").text(mailAuth); 
				opener.$("#email1").val("${email1}"); /* 다 넘어왔는데 이부분이 안넘어옴  */
				opener.$("#email2").val("${email2}"); 		
				window.close();  
			}//end else
		});//end click
    });//end doc 
 
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

<h4><input id="Authnum" name="Authnum" type="text" placeholder="인증번호 입력"></input></h4>
<button>확인</button>


</body>
</html>
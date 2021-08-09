<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
 MemberDTO dto = (MemberDTO)request.getAttribute("nickDto"); 
 String nickName = dto.getNickName(); 
%> --%>
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

	div{
		text-align: center;
		border: solid 3px gray;
		margin: auto;
		padding: auto;
	}
</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
		$("button").click(function() {
			var nickName = $("#nick").text();
			opener.$("#resultNick").val(nickName);
			window.close();
		});
 });
</script>
</head>
<body>
<div>
<h4><span id="nick">${nickDTO}</span> 으로 변경되었습니다</h4>
<button>확인</button>
</div>
</body>
</html>
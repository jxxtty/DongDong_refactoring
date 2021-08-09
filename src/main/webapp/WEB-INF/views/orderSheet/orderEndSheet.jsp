<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type = "text/javascript">
$(function(){
 $("#close").click(function(){
		 window.close();
	 })
	 
})

</script>

<body>
<div style ="text-align:center">
	<h3>주문서가 정상적으로 전송되었습니다.</h3>
	<button  id = "close">닫기</button>
</div>
</body>
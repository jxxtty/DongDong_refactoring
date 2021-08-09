<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dongdong market-Home</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">

<%-- <c:if test= "${!empty success}">
<script>
console.log("${success}");
alert("${success}")</script>
</c:if> --%>

<c:set var="mesg" value="${success}"></c:set>
<c:if test="${!empty success}"> 
<script>
alert("${success}")
</script>
<c:remove var="success" scope="session" />
</c:if>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">


	$(document).ready(function() {
		$("#main").click(function() {
			location.href="/";
		})
	})//end ready
</script>
<style type="text/css">
	
	
	* {
		font-family: 'Nanum Gothic', sans-serif;
		font-size: 13px;
		font-weight : 400;
	}
	
	main{
		height:auto;
		padding-top : 10px;
		z-index : 2;
	}
	
	
	header {
		margin : 0;
		padding : 0;
		position : fixed;
		top : 0;
		left : 0;
		right : 0;
		z-index : 1;
		text-align : center;
		background-color : white;
	}
	
	#main_img{
		margin-top : 100px;
		padding : 0;
		background-image : url('/Dong-Dong/images/util/main.jpg');
		background-repeat : no-repeat;
		background-size : cover;
		background-position: center;
		height: 480px;
		z-index : 1;

	}
	@media (min-width:500px) and (max-width: 991.98px){
	 main{
		height:auto;
		padding-top : 10px;
		z-index : 2;
	}
	#main_img{
		height : 380px;
		margin-bottom:-35px;
	}
}
	
	@media (min-width:992px) and (max-width: 1199.98px){
	 main{
		height:auto;
		padding-top : 10px;
		z-index : 2;
	}
	#main_img{
		height : 380px;
		margin-bottom:60px;
	}
}
	 @media (min-width:1200px) and (max-width: 1560px){
	 main{
		height:auto;
		padding-top : 10px;
		z-index : 2;
	}
	#main_img{
		height : 380px;
		margin-bottom:60px;
	}
</style>
</head>
<body>
<%
	String withdrawal = (String)session.getAttribute("withdrawal");
	if(withdrawal != null){
%>
	<script type = "text/javascript">
		alert('<%=withdrawal %>');
	</script>
<%
	//
	session.removeAttribute("withdrawal");
	session.removeAttribute("login");
	}
%>


<%
	String mesg = (String)session.getAttribute("mesg");
	if(mesg != null){
%>
	<script type = "text/javascript">
		alert('<%=mesg %>');
	</script>
<%
	//
	session.removeAttribute("mesg");
	}
%>

<header>
	<jsp:include page="common/top.jsp" flush="true"></jsp:include>
</header>
<div id="main_img"></div>
<main>
	<jsp:include page="post/postList.jsp" flush="true"></jsp:include>
</main>
<footer>
	<jsp:include page="layout/bottom.jsp" flush="true"></jsp:include>
	</footer>
</body>
</html>
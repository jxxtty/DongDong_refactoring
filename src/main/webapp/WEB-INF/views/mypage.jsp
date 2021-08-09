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
	$(document).ready(function() {
		$("#main").click(function() {
			location.href="main";
		})
		
	})//end ready
	
</script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">	
<style type="text/css">
	* {
		font-family: 'Nanum Gothic', sans-serif;
		font-size: 15px;
		font-weight : 400;
	} 

	main{
		padding-top : 130px;
		/*z-index : 2;*/
	}
	
	
	header {
		/* background-image : url('/Dong-Dong/images/util/main.jpg');
		background-repeat : no-repeat;
		background-size : cover; */
		margin : 0;
		padding : 0;
		position : fixed;
		top : 0;
		left : 0;
		right : 0;
		/*z-index : 2;*/
		text-align : center;
		height : 114px;
		background-color : white;
	}
</style>
</head>
<body>
<c:set var="mesg" value="${mesg}"></c:set>
<c:if test="${!empty mesg}"> 
<script>
// var mesg = ${mesg};
/* alert(mesg); */ 
alert("${mesg}")
</script>
<c:remove var="mesg" scope="session" />
</c:if>  


<header>

<!-- <h3>MyPage</h3> -->

<jsp:include page="common/top.jsp" flush="true"></jsp:include><br>

</header>
<main>
<jsp:include page="member/mypage.jsp" flush="true"></jsp:include>
</main>
</body>
</html>
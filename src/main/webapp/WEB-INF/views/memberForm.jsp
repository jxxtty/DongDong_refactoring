<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	
	main{
		padding-top : 68px;
		z-index : 2;
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
		z-index : 1;
		text-align : center;
		background-color : white;
	}
</style>
</head>
<body>

<header>
<jsp:include page="common/top.jsp" flush="true"></jsp:include>
</header>

<main>
<jsp:include page="member/memberForm.jsp"  flush="true"></jsp:include>
</main>
</body>
</html>
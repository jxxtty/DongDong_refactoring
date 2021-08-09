<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/LoginFormMain.css">
<link rel="stylesheet" type="text/css" href="css/Util.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">	
<style type="text/css">
	* {
		font-family: 'Nanum Gothic', sans-serif;
		font-size: 15px;
		font-weight : 400;
	}
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<c:set var="mesg" value="${mesg}"></c:set>
<c:set var="mesg1" value="${mesg1}"></c:set>
<c:if test="${!empty mesg}"> 
<script>
alert("${mesg} \n ${mesg1}")
</script>
<c:remove var="mesg" scope="session" />
</c:if>
<jsp:include page = "member/loginForm.jsp" flush ="true" />
</body>
</html>
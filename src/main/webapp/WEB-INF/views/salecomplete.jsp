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
		text-align : center;
	} 
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("button").click(function() {
			opener.parent.location.reload();
			window.close();
		})
	});//end ready
</script>
</head>
<body>
<c:if test="${sale != 0}">
	<h3>판매가 완료 되었습니다.</h3>
	<button>확인</button>
</c:if>
<c:if test="${sale == 0}">
	<h3>거래 오류가 발생했습니다.</h3>
	<button>확인</button>
</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	//여기에 인증 버튼 클릭시에 -> 이메일1,이메일 2를 이메일 처리하는 서블릿으로 넘겨줘야 하는 걸 만들어야함.
	//확인버튼 눌렀을때 보내졌다고 알림 나옴 
	$(document).ready(function() {
		$("#emailAuth").click(function() {
			if(($("#email1").val().length) && ($("#email2").val().length) != 0){
				alert("인증번호 전송");
			}
			if ($("#email1").val().length == 0) {
				alert("이메일을 입력해주세요");
				return false;
			}
			if ($("#email2").val().length == 0) {
				alert("이메일을 입력해주세요.");
				return false;
			}

		});// end 인증클릭

	});//end ready
</script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">	
<style type="text/css">
* {
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 15px;
	font-weight : 400;
} 

form {
	text-align: center;
	margin: auto;
	padding: auto;
}
</style>
</head>
<body>
	<form action="/emailAuthC" method="post">
		<!-- <form action="#"> -->
		<h3>이메일 인증</h3>
		<input type="text" id="email1" name="email1" placeholder="이메일 입력">@
		<input type="text" id="email2" name="email2">

		<button id="emailAuth">인증하기</button>
	</form>


</body>
</html>
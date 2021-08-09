<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="898782990200-vm6qpl53536ad2r2i8h65q7jme7ig993.apps.googleusercontent.com">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type = "text/javascript">
$(function(){
 $("form").submit(function(){
		 
		 var userid = $("#userid").val();
		 var passwd = $("#passwd").val(); 
		 
		 if(userid.length ==0){
			 alert("아이디를 입력하세요")
			 $("#userid").focus();
			 return false;
		 }
		 if(passwd.length ==0){
			 alert("비밀번호를 입력하세요");
			 $("#passwd").focus();
			 return false;
		 }
	 }) //form end
	
})
</script>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="login" method="post">
					<span class="login100-form-title p-b-26">
						Log-in
					</span>
					<span class="login100-form-title p-b-30">
						<img src = "/Dong-Dong/images/util/DongDonglogo2.png" height = "100" width = "100" />
						
					</span>
					
					<div class="wrap-input100 validate-input" data-validate = "Valid email is: a@b.c">
						<input class="input100" type="text" name="userid" id="userid" placeholder="아이디">
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
						<input class="input100" type="password" name="passwd" id = "passwd" placeholder="패스워드">
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn" id="loginbtn">
								Login
							</button>
						</div>
					</div>
					
					<div class="text-center p-t-25">
						<a href="https://kauth.kakao.com/oauth/authorize?client_id=9516c7a8850f5616c0e63b831800b6a9&redirect_uri=http://localhost:8079/kakao&response_type=code">
						<img height="35px" src="/Dong-Dong/images/login/kakao_login_button.png" /></a>
						
						<a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=898782990200-vm6qpl53536ad2r2i8h65q7jme7ig993.apps.googleusercontent.com&redirect_uri=http://localhost:8079/google&response_type=code&scope=email%20profile%20openid&access_type=offline">
						<img width="142.328px" height="35px" style="border-radius: 4.5px;" src="/Dong-Dong/images/login/google.png" /></a>
						
						<a href="${url}"><img width="142.328px" height="35px" src="/Dong-Dong/images/login/naver.png"/> </a>
					</div>
				
					<div class="text-center p-t-25">
						<span class="txt1">
							Don’t have an account?
						</span><br>
						<a href = "memberForm">회원가입</a><br>
						<a href = "idSearch">아이디 찾기 </a><br>
						<a href = "pwSearch">비밀번호 찾기 </a>
					</div>
				</form>
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	

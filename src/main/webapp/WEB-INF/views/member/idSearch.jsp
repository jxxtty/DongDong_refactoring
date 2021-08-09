<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%
   String mesg = (String)request.getAttribute("mesg");
  if(mesg!=null){
%>    
   <script>
     alert('<%=mesg %>');
   </script>
<%
  }
%>
<!--     
<form action="MemberIdSearchServlet" method="get">
  이름<input type="text" name="username"><br>
 휴대폰    <input type="text" name="phone"><br>
이메일<input type="text"  name="email1">@
     <input type="text"  name="email2" id="email2" placeholder="직접 입력">
   <input type="submit" value="메일 보내기">  
</form>
 -->

<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="idSearch" method="get">
					<span class="login100-form-title p-b-26">
						아이디 찾기
					</span>
					<span class="login100-form-title p-b-30">
						<img src = "/Dong-Dong/images/util/DongDonglogo2.png" height = "100" width = "100" />
						
					</span>
					
					<div class="wrap-input100 validate-input" >
						<input class="input100" type="text" name="username" id="username" placeholder="이름">
					</div>

					<div class="wrap-input100 validate-input">
						<input class="input100" type="text" name="phone" id = "phone" placeholder="휴대폰 번호 (01012345678)">
					</div>
					<div class="wrap-input100 validate-input">
						<input class="input100" type="text" name="email" id = "email" placeholder="dongdong@naver.com">
					</div>
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn" id="loginbtn">
								이메일 전송
							</button>
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div>

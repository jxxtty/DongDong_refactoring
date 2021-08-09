<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dto.PostDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript">
    		function pullPostY(){
				document.pullForm_Y.target = opener.window.name;
				document.pullForm_Y.action = "../loginCheck/postPull";
				document.pullForm_Y.method = "post";
				document.pullForm_Y.submit();
				self.close();
			}
    			
    		function goWritePost(){
    			document.pullForm_N.target = opener.window.name;
    			document.pullForm_N.action = "../loginCheck/postWrite";
    			document.pullForm_N.submit();
    			self.close();
    		}
    			
		</script>
		 <link rel="preconnect" href="https://fonts.gstatic.com">
		<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">	
		<style type="text/css">
			* {
				font-family: 'Nanum Gothic', sans-serif;
				font-size: 15px;
				font-weight : 400;
			} 
			
			#popup {
				text-align: center;
				border : 3px solid #8db0d7;
				margin: auto;
				padding: 10px;
			}
			
			#post img{
				vertical-align : middle;
			}
			
			#post {
				text-align: center;
				border : 2px solid #8db0d7;
				border-radius : 5px;
				margin: 5px 50px;
				padding: auto;
			}
			
			.btn {
				border : 1px solid #8db0d7;
				background-color : #8db0d7;
				color : white;
				border-radius : 3px;
			}
			
		</style>
	</head>
	<body>
		
		<c:if test="${pullAvailable eq 'T' }">
			<c:if test="${pDto.pPull+0 == 3 }">
			<!-- 끌올 가능(끌올 한번도 안해본사람) -->
				<div id="popup">
					<div id="post">
						<p>
						<img src="/Dong-Dong/images/${pDto.pImage}" width="70" height="70">
						<b>${pDto.pTitle}</b>
						</p>
					</div>
					<form name="pullForm_Y">
						<input type="hidden" value="${pDto.pNum}" name="pNum">
						끌올 기능은 <b>3일 간격 3회</b>로 제한됩니다.<br>
						<br>
						현재 해당 글에대한 끌올 가능 횟수는 ${pDto.pPull}회 입니다.<br>
						<br>
						<input type="button" id="pullT" class="btn" value="끌올하기" style="cursor: pointer;" onclick="pullPostY()">
						<button class="btn" onclick="window.close()" style="cursor: pointer;">취소</button>
					</form>
				</div>
			</c:if>
	
			<c:if test="${pDto.pPull+0 < 3}">
			<!-- 끌올 가능(끌올 해본사람) -->
				<div id="popup">
					<div id="post">
						<p>
						<img src="/Dong-Dong/images/${pDto.pImage}" width="70" height="70">
						<b>${pDto.pTitle}</b>
						</p>
					</div>
					<form name="pullForm_Y">
						<input type="hidden" value="${pDto.pNum}" name="pNum">
						끌올 기능은 <b>3일 간격 3회</b>로 제한됩니다.<br>
						<br>
						현재 해당 글에대한 끌올 가능 횟수는 ${pDto.pPull}회 입니다.<br>
						최근 끌올 날짜 : ${pDto.pDate}(${calDateDay}일전)<br>
						<input type="button" id="pullT" class="btn" value="끌올하기" style="cursor: pointer;" onclick="pullPostY()">
						<button class="btn" onclick="window.close()" style="cursor: pointer;">취소</button>
					</form>
				</div>
			</c:if>
		</c:if><!-- pullAvailable T인경우 끝 -->
		
		<c:if test="${pullAvailable eq 'F'}">
			<c:if test="${pDto.pPull+0 > 0}">
			<!-- 끌올 기회는 남아있으나, 3일이 지나지 않아서 끌올불가능 -->
				<div id="popup">
					<div id="post">
						<p>
						<img src="/Dong-Dong/images/${pDto.pImage}" width="70" height="70">
						<b>${pDto.pTitle}</b>
						</p>
					</div>
					끌올 기능은 <b>3일 간격 3회</b>로 제한됩니다.<br>
					<br>
					최근 끌올 날짜 : ${pDto.pDate}(${calDateDay}일전)<br>
					끌올이 <b>불가능</b>합니다!<br>
					<br>
					<button class="btn" onclick="window.close()" style="cursor: pointer;">확인</button>
				</div>
			</c:if>
				
			<c:if test="${pDto.pPull+0 == 0}">
			<!-- 끌올 불가능(끌올 횟수가 안남음) -->
				<div id="popup">
					<div id="post">
						<p>
						<img src="/Dong-Dong/images/${pDto.pImage}" width="70" height="70">
						<b>${pDto.pTitle}</b>
						</p>
					</div>
					끌올 기능은 <b>3일 간격 3회</b>로 제한됩니다.<br>
					<br>
					해당 글에대한 끌올 횟수를 모두 사용하셨습니다.<br>
					<br>
					새로 글을 작성하고 싶으시다면 글쓰기 버튼을 클릭해주세요<br>
					<form name="pullForm_N">
						<input type="button" class="btn" id="writeBtn" value="글쓰기" style="cursor: pointer;" onclick="goWritePost()">
						<button class="btn" onclick="window.close()" style="cursor: pointer;">확인</button>
					</form>
				</div>
			</c:if>
		</c:if><!-- pullAvailable F인 경우 끝 -->
		
	</body>
</html>
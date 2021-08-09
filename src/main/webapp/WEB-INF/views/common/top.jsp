<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script src="/webjars/stomp-websocket/2.3.4/stomp.js"
	type="text/javascript"></script>
<script src="/webjars/sockjs-client/1.1.2/sockjs.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function chatLink(chatid){
		window.open('chatList/chat?chatId='+chatid, '_blank' ,'width=550, height=620');
	}

	var stompClient = null; // 전역
	$(document).ready(function() {
		
		$("#main").click(function() {
			location.href = "/";
		})

		$("#back").on("click", function() {
			location.href = "/";
		});

		$("#search").on("click", function() {
			location.href = "categoryList";
		});

		$("#keyword").keydown(function(e) {
			if (e.keyCode == 13) {
				$('form').submit();
			}
		});
		
		$("#chatList").click(function(){
			var userid = $("#id").val();
			
			$.ajax({
				url : 'chatList',
				type : 'get',
				dataType:"json",//데이터타입 밑으로 들어옴
				success : function(data, status, xhr) {
					$('#chatListGetFive').html('');
					
					var result = "";
                    for(var i = 0 ; i < data.chat.length ; i++){
                    	var chatid = data.chat[i].chatid;
                    	result += "<a href ='javascript:chatLink("+chatid+")'";
                    	result += "class='list-group-item' ><b>";
                    	if(data.chat[i].sUserid == userid){
                    		result += data.chat[i].bUserid+"</b>님과의 채팅</a>";
                    	} else{
                    		result += data.chat[i].sUserid+"</b>님과의 채팅</a>";
                    	}
                    }
                    
                    if(data.chat.length < 1){
                    	result = "<li class = 'list-group-item'>채팅이 없습니다.</li>"
                    }
                    
					$('#chatListGetFive').html(result);
				},
				error : function(xhr, status, error) {
					console.log("error");
				}
			});
			$('.my-chat').toggleClass('d-none');
		});
		
		$('#alarmList').click(function() {
			if (window.innerWidth < 992) {
				location.href = "loginCheck/myAlarm";
				return;
			}
			$.ajax({
				url : 'myAlarmList',
				type : 'get',
				data : {
					id : $("#id").val(),
				}, 
				dataType:"json",
				success : function(data, status, xhr) {
					$('#alarmListGetFive').html('');
					var first = "<li class='list-group-item' id='alarmList_link'>";
					var result = "";

                    if(data.alarm.length == 0){
                    	result += first + "알림이 없습니다.</li>";
                    } else {
                    	for(var i = 0 ; i < data.alarm.length ; i++){
                        	if(data.alarm[i].type == "c"){
	                    		result += first;
	                        	result += "<a href='postDetail?pNum="+data.alarm[i].info+"'>["+data.alarm[i].detail+"]글에 댓글이 작성되었습니다.</a><br>";
	                        	result += "<p id='alarmList_Sub'>by <b>"+data.alarm[i].sender+"</b> "+data.alarm[i].date+"</p></li>";
	                        } else if(data.alarm[i].type == "o"){
	                        	result += first;
	                        	result += "<a href='loginCheck/OrdersheetList'>["+data.alarm[i].detail+"]글의 주문서가 도착했습니다.</a><br>";
	                        	result += "<p id='alarmList_Sub'> by<b>"+data.alarm[i].sender+"</b> "+data.alarm[i].date+"</p></li>";
	                        } else {
	                        	result += first;
	                        	result += "<a href='postDetail?pNum="+data.alarm[i].info+"'>["+data.alarm[i].detail+"]글에 작성한 댓글에 대댓글이 작성되었습니다.</a><br>";
	                        	result += "<p id='alarmList_Sub'>by <b>"+data.alarm[i].sender+"</b> "+data.alarm[i].date+"</p></li>";
	                        }
                        }
                    	result += first+"<a href='loginCheck/myAlarm'><img src='/Dong-Dong/images/util/plus.png' width='20px'></a></li>";

                    }
					$('#alarmListGetFive').html(result);
				},
				error : function(xhr, status, error) {
					console.log("error");
				}
			});
			$('.my-alarm').toggleClass('d-none');
		});

		var isLogin = $("#id").val();
		if (isLogin && isLogin.length != 0) {
			var sockJS = new SockJS('http://localhost:8079/sockJS');
			var urlSubscribe = '/subscribe/alarm/' + isLogin; // 세션에 저장되어있는login정보에서 userid를 추출해서 연결
			stompClient = Stomp.over(sockJS);
			console.log("socket에 연결됐음", stompClient);
			stompClient.connect({}, function() {
				stompClient.subscribe(urlSubscribe, function(alarm) {
					// 구독해서 넘어오는 데이터(alarm)를 JSON으로 파싱해서 content에 저장
					var content = JSON.parse(alarm.body);
					createToast(content);
				});
			});
		}

		window.addEventListener('resize', function() {
			if (window.innerWidth < 992) {
				$('#navbarSupportedContent').removeClass('show');
				$('.my-alarm').addClass('d-none');
			}
		});
	}); // end-documentready

	function createToast(alarmObj) { // 받아온 데이터를 파싱해서 화면에 뿌려줄 코드를 작성한다.
		var print = "";
		if (alarmObj.type == "c") { // 댓글알림
			print = '<div class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-autohide="true">'
					+ '<div class="toast-body" id="toast_body">'
					+ '<p><b>'
					+ alarmObj.sender + '님</b>이 ['
					+ alarmObj.detail +']에 <br> <b>댓글</b>을 작성했습니다.</p>'
					+ '<div class="mt-2 pt-2 border-top">'
					+ '<button type="button" class="btn btn-primary btn-sm" id="goPage">확인하기</button>'
					+ '<button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="toast">닫기</button>'
					+ '</div>' + '</div>' + '</div>';
		} else if (alarmObj.type == "rc"){ // 대댓글알림
			print = '<div class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-autohide="true">'
				+ '<div class="toast-body" id="toast_body">'
				+ '<p>['
				+ alarmObj.detail +']에 작성한 댓글에<br><b>'
				+ alarmObj.sender + '</b>님이 <b>대댓글</b>을 작성했습니다.</p>'
				+ '<div class="mt-2 pt-2 border-top">'
				+ '<button type="button" class="btn btn-primary btn-sm" id="goPage">확인하기</button>'
				+ '<button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="toast">닫기</button>'
				+ '</div>' + '</div>' + '</div>';
		} else { // 주문서알림
			print = '<div class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-autohide="true">'
				+ '<div class="toast-body" id="toast_body">'
				+ '<p><b>'
				+ alarmObj.sender + '님</b>이 ['
				+ alarmObj.detail +']에 <br><b>주문서</b>를 보냈습니다.</p>'
				+ '<div class="mt-2 pt-2 border-top">'
				+ '<button type="button" class="btn btn-primary btn-sm" id="goPage">확인하기</button>'
				+ '<button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="toast">닫기</button>'
				+ '</div>' + '</div>' + '</div>';
		}
		$(".toast-container").append(print);
		$(".toast").toast('show');

		$("#goPage").click(function() { // 버튼클릭시 해당 글로 이동하게!
			if (alarmObj.type == "o"){
				location.href="loginCheck/OrdersheetList";
			} else {
				location.href = "postDetail?pNum=" + alarmObj.info;
			}
		});
		
		var myToastEl = document.querySelector('.toast');
		myToastEl.addEventListener('hidden.bs.toast', function() {
			$('.toast-container').html('');
		});

	}



</script>
<!-- Bootstrap css -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap"
	rel="stylesheet">
<style type="text/css">
.my-alarm {
	top: 68px;
	right: 12px;
}
.my-chat {
	top: 68px;
	right: 12px;
}

.searchInput {
	width: 46vw;
	margin-left: auto;
	margin-right: auto;
}

@media screen and (max-width: 991px) {
	.searchInput {
		width: 100%;
		margin-top: 20px;
	}
}

.nav-link {
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 20px;
	font-weight: 700;
	color: #8db0d7 !important;
}

#alarmList_Sub{
	font-size:13px;
	color : gray;
}

#alarmList_link a{
	text-decoration : none;
	color : black;
}

#keyword{
	border: 2px solid #8db0d7;
}

</style>
</head>
<body>
	<input type="hidden" id="id" value="${login.userid}">
	<nav class="navbar navbar-expand-lg navbar-light bg-white">
		<div class="container-fluid">
			<img id="main" src="/Dong-Dong/images/util/DongDonglogo.png"
				width="222" height="52" style="cursor: pointer;" />
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<form class="searchInput d-flex align-items-center"
					action="keywordSearch" method="get">
					<input type="text" name="keyword" id="keyword"
						class="form-control mx-2" placeholder="검색할 상품명"> <img
						src="/Dong-Dong/images/util/search_category.png" id="search"
						width="110" height="30" style="cursor: pointer;">
				</form>
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<c:if test="${!empty login}">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/mypage">마이페이지</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/postWrite">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link active"  id = "chatList" 
							aria-current="page" href="#none">채팅</a></li>
						<li class="nav-item" id="alarmList"><a
							class="nav-link active" aria-current="page" href="#none">알림</a></li>
					</c:if>
					<c:if test="${empty login}">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="loginForm">로그인</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="memberForm">회원가입</a></li>
					</c:if>
					<li class="nav-item dropdown">
						<!-- <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            Dropdown
		          </a> -->
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<c:if test="${!empty login}">
								<li><a class="dropdown-item" href="/mypage">마이페이지</a></li>
								<li><a class="dropdown-item" href="/logout">로그아웃</a></li>
								<li><a class="dropdown-item" href="/postWrite">글쓰기</a></li>
								<li><a class="dropdown-item" href="chatRoom">채팅</a></li>
								<li><a class="dropdown-item" id="alarmList" href="#none">알림</a></li>
							</c:if>
							<c:if test="${empty login}">
								<li><a class="dropdown-item" href="loginForm">로그인</a></li>
								<li><a class="dropdown-item" href="memberForm">회원가입</a></li>
							</c:if>
						</ul>
					</li>
				</ul>
				<ul class="list-group position-absolute my-chat text-start d-none" id="chatListGetFive">
				
				</ul>
				
				<ul class="list-group position-absolute my-alarm text-start d-none" id="alarmListGetFive">
					<!-- 비동기 -->
				</ul>
			</div>
		</div>
	</nav>
	<div aria-live="polite" aria-atomic="true" class="position-relative">
  		<div class="toast-container position-absolute top-0 end-0 p-3">
		</div>
	</div>
</body>
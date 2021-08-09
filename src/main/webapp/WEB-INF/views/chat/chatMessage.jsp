<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chatting</title>
<style>
* {
	margin: 0;
	padding: 0;
}

.container {
	width: 500px;
	margin: 0 auto;
	padding: 25px
}

.container h1 {
	text-align: left;
	padding: 5px 5px 5px 15px;
	color: #8db0d7;
	margin-bottom: 20px;
}

.chatbox {
	border: 5pt groove #8db0d7;
	width: 500px;
	height: 500px;
	overflow: auto;
}

input {
	width: 330px;
	height: 25px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/webjars/stomp-websocket/2.3.4/stomp.js"
	type="text/javascript"></script>
<script src="/webjars/sockjs-client/1.1.2/sockjs.js"
	type="text/javascript"></script>
</head>

<body>
	<div id="container" class="container">
		<h1>채팅방</h1>
		<div id="chatbox" class="chatbox">
			<!-- chatHistory 출력후 추가적으로 출력 -->
			<c:forEach var="line" items="${lines}">
				<p>
					<span id="chatLine">${line}</span><br>
				</p>
			</c:forEach>
		</div>

		<div id="Msg">
			<table class="inputTable">
				<tr>
					<th>메시지</th>
					<th><input id="message" placeholder="보내실 메시지를 입력하세요."></th>
					<th><button id="send">보내기</button> <input type="hidden"
						value="${login.userid}" id="userId" /> <input type="hidden"
						value="${chatId}" id="chatId" /></th>
				</tr>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(connect);
		var stompClient = null;
		var userId = $('#userId').val();
		var chatId = $("#chatId").val();

		function connect() {
			console.log("connected");
			var sockJS = new SockJS('http://localhost:8079/sockJS');
			var urlSubscribe = '/subscribe/' + chatId;
			stompClient = Stomp.over(sockJS);
			stompClient.connect({}, function(frame) {
				console.log(frame);
				stompClient.subscribe(urlSubscribe, function(msg) {
					printMessage(createText(JSON.parse(msg.body)));
				});

			}, function(err) {
				alert('error' + err);
			});
		};

		function sendMessage() {
			var content = $('#message').val();
			var sendtime = getTime();
			var json = {
				'userId' : userId,
				'message' : content,
				'sendTime' : sendtime,
				'chatId' : chatId
			};
			$('#message').val("");
			stompClient.send('/send/chatMessage', {}, JSON.stringify(json));
		}

		$("#send").click(function() {
			sendMessage();
		});

		function getTime() {
			var today = new Date();
			var date = today.getFullYear() + '년 ' + (today.getMonth() + 1)
					+ '월 ' + today.getDate() + '일';
			var minutes = today.getMinutes() + "";
			if (minutes.length == 1)
				minutes = '0' + minutes;
			var time = today.getHours() + ":" + minutes;
			var sendtime = date + ' ' + time;
			return sendtime;
		}

		var inputMessage = document.getElementById('message');

		inputMessage.addEventListener('keyup', function enterSend(event) {
			if (event.keyCode === null) {
				event.preventDefault();
			}
			if (event.keyCode === 13) {
				sendMessage();
			}
		});
		function createText(messageObj) {
			return '<p><div class="row alert alert-info"><div class="col_8">'
					+ messageObj.userId + ' : ' + messageObj.message
					+ ',' + messageObj.sendTime + '</div></p>';
		}

		function printMessage(message) {
			$("#chatbox").html($("#chatbox").html() + message);
		}
		
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- Bootstrap css -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<!-- Bootstrap js -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<style type="text/css">
#comments_div{
	text-align : left;
}
.comment form{
	display: none;
}
#profileImage{
	vertical-align: middle;
}
.footerfix{
padding-bottom: 300px;
}
</style>


<c:if test="${!empty login}">
<script type="text/javascript">
	var postWriter = "${userid}"; // 글쓴사람의 userid
	var sender = ${login.userid}; // 현재 로그인 되어있다(댓글이나 대댓글을 작성하는애)	
	
	$(document).ready(function() {
		$(".update_comment").on("click", function() {
			$(this).parent().parent().parent().find(".comment-update-form").toggle(100);
			if($(this).parent().parent().parent().find(".comment-reply-form").attr("style")=="display: block;"){
				$(this).parent().parent().parent().find(".comment-reply-form").toggle(100);
			}
		});//on
		
		$(".reply_comment").on("click", function() {
			$(this).parent().parent().parent().find(".comment-reply-form").toggle(100);
			if($(this).parent().parent().parent().find(".comment-update-form").attr("style")=="display: block;"){
				$(this).parent().parent().parent().find(".comment-update-form").toggle(100);
			}
		});//on
		
		$("form").on("submit",function(event){
			var cContent = $(this).find("textarea").val();
			if(cContent.length == 0){
				alert("댓글을 입력하세요.");
				$(this).find("textarea").focus();
				event.preventDefault();	
			}  
		});
		
		function complaintComment(id) {
			var popupWidth = 300;
			var popupHeight = 500;
			var popupX = (window.screen.width/2)-(popupWidth/2);
			var popupY= (window.screen.height/2)-(popupHeight/2);
			url = "loginCheck/complaintDetail?cNum="+id+"&coType=3";
			open(url,"complaintComment", 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
		}//end complaintComment
		$(".complaintComment").click(function() {
			complaintComment(this.id);
		});
		
		
		// 댓글작성 알림
		$("#comments_submit").click(function(){ 
			if(sender != postWriter){ // 현재 로그인 되어있는 유저와 글작성자가 다른 경우에만 알림전송 -> 글작성자에게 알림전송
				sendMessage(postWriter, 'c'); 
			}
		});
		
		// 대댓글 작성 알림
		$(".comments_reply_submit").click(function(){
			var commentsWriter = $(this).attr("data-xxx");
			if(sender != commentsWriter){ // 현재 로그인 되어있는 유저와 댓글작성자가 다른 경우에만 알림 전송
				sendMessage(commentsWriter, 'rc');
			}
		});
	});
	
	function sendMessage(receiver, type){
		var pNum = $("#pNum").val();
		var json = {
			'sender' : sender,
			'receiver' : receiver,
			'type' : type,
			'info' : pNum,
			'detail' : pTitle,
			'isRead' : '0'
		};
		console.log("sendMessage호출됐음 : ", JSON.stringify(json));
		stompClient.send('/send/reply/'+receiver, {}, JSON.stringify(json));
	}
</script>
</c:if>
    <!-- 댓글 기능 표시 시작 지점 --------------------------------------- -->
    <div id="comments_div" class="container footerfix" style="max-width: 1100px"> <!-- 댓글 전체 div -->
       
       <div style="text-align : left; font-weight: bolder; margin-left: 20px"><h2>댓글 (${comments.size()})</h2></div>
       <hr>
       <c:forEach var="cDTO" items="${comments}" varStatus="status">
      	<c:if test="${cDTO.cLevel==1}">
      		<div class="comment my-3 p-2 bg-white rounded shadow-sm">
      	</c:if>
      	<c:if test="${cDTO.cLevel!=1}">
      		<div class="comment my-3 p-2 alert-secondary rounded shadow" style="padding-top: 10px; margin-left: ${40*(cDTO.cLevel-1)}px">
      	</c:if>
	      	<div id="userInfo_div"> <!-- 상단 유저 정보 div -->
	      	  <table>
	      	    <tr>
	      		  <td>
	      			<img id="profileImage" class="img-fluid rounded mb-4 mb-lg-0" src="/Dong-Dong/images/profile/${cDTO.userimage}" width="70px" height="">
	      		  </td>
	      		  <td >
	      			<div style="margin-left: 10px;">
	      				<h5 style="display: inline;">${cDTO.nickName}
	      				  <c:if test="${cDTO.userid==userid}">
	      					<h5 class="text-primary" style="display: inline;"> (글쓴이)</h5></c:if>
	      				</h5>
	      			</div>
	      			<div style="margin-left: 10px;">
					 <c:if test="${empty cDTO.updateDate}">
	      				${cDTO.createDate}
	      			 </c:if>
	      			<c:if test="${!empty cDTO.updateDate}">
	      				${cDTO.updateDate}(수정됨)
	      			 </c:if>
					</div>
	      		  </td>
	      		</tr>
	      	  </table>
	      	</div>
	      	
	      	
	      	<div id="comment_div"> <!-- 하단 내용 & 버튼 div -->
	      		<div style="margin-top:10px; margin-left:5px">
	      			<h5>
      				  ${cDTO.cContent}
      				</h5>
	      		</div>
	      		
	      		<div style="text-align : right">
	      		<c:if test="${!empty login&&pStatus!='1'}">
      				<a class="btn reply_comment btn-outline-primary btn-sm" href="javascript:" id="${cDTO.cNum}">답글</a>
      			</c:if>
	      		<c:if test="${!empty login&&pStatus!='1'&&cDTO.userid==login.userid}">
      				<a class="btn update_comment btn-outline-primary btn-sm" href="javascript:" id="${cDTO.cNum}">수정</a>
      			</c:if>
	      		<c:if test="${!empty login&&pStatus!='1'&&cDTO.userid==login.userid||!empty login && userid==login.userid}">
      				<a class="btn btn-outline-danger btn-sm" href="loginCheck/commentsDelete?pNum=${pNum}&cNum=${cDTO.cNum}">삭제</a>
      			</c:if>
	      		<c:if test="${!empty login&&cDTO.userid!=login.userid}">
     				<a class="btn complaintComment btn-outline-danger btn-sm" href="javascript:" id="${cDTO.cNum}">신고</a>
     			</c:if>
	      		</div>
	      		<c:if test="${pStatus!='1'}">
	      		<div>
	      		  <div class="comment-form well"><!-- 대댓글 -->
	      		  	<!-- <input type="hidden" id="receiverReplyComments" value="${cDTO.userid }"> 대댓글이 달리는 원댓글 작성자의 userid -->
     			    <form class="comment-reply-form" action="loginCheck/commentsWrite" method="post">
      			  	  <label for="contactComment"></label> 
      				  <input type="hidden" name="pNum"  value="${pNum}"/>
      				  <input type="hidden" name="parentNum" value="${cDTO.cNum}"/>
    			  	  <textarea rows="3" class="form-control" name="cContent" style="resize: none;"></textarea> 
      			  	  <div style="text-align : right" >
      			  	  	<input type="submit" class="btn btn-outline-primary btn-block btn-sm comments_reply_submit" 
      			  	  	 	value="답글" data-xxx="${cDTO.userid}"/>
      			      </div>
      			    </form>
   			      </div>
   			      <c:if test="${!empty login&& login.userid==cDTO.userid}">
      			  <div class="comment-form well">
     			    <form class="comment-update-form" action="loginCheck/commentsUpdate" method="post">
      			  	  <label for="contactComment"></label> 
      				  <input type="hidden" name="pNum" value="${pNum}"/>
      				  <input type="hidden" name="cNum" value="${cDTO.cNum}"/>
    			  	  <textarea rows="3" class="form-control" name="cContent" style="resize: none;"></textarea> 
      			  	  <div style="text-align : right" >
      			  	  	<input type="submit" class="btn btn-outline-primary btn-block btn-sm" value="수정"/>
      			   	  </div>
      			    </form>
   			      </div>
   			      </c:if>
	      		</div>
	      		</c:if>
	      	</div>
      	</div>
     </c:forEach>
     
     <input type="hidden" id="receiverPost" value="${userid}"> <!-- 글 작성자의 userid -->
     <input type="hidden" id="sender" value="${login.userid}"> <!-- 댓글작성자의 userid -->
	    
      <c:if test="${pStatus!='1' && empty login}">
      <div style="margin-bottom: 10px">
    	<a class="btn btn-primary" href="loginForm">로그인 후 댓글 작성이 가능합니다.</a>
      </c:if>
	  <c:if test="${pStatus=='1' || !empty login}">
	  	<div class="comment-form well" style="margin-bottom: 10px">
	     <form action="loginCheck/commentsWrite" method="post">
	      	<label for="contactComment"></label> 
	      	<input type="hidden" name="pNum" id="pNum" value="${pNum}"/>
	    	<textarea rows="3" class="form-control" name="cContent" style="resize: none;"></textarea>
	    	<div style="text-align : right" >
	    		<input type="submit" class="btn btn-outline-primary btn-block btn-sm" id="comments_submit" value="댓글"/>
	    	</div>
	      </form>
	    </div>
	    </c:if>
	  </div>
    </div>
    
    <jsp:include page="../layout/bottom.jsp" flush="true"></jsp:include>
    

    
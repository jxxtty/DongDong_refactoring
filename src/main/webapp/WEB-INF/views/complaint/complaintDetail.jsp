<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<%
	MemberDTO dto = (MemberDTO)session.getAttribute("login");
 	String pNum = request.getParameter("pNum");
 	String cNum = request.getParameter("cNum");
 	String userid = request.getParameter("userid");
 	String coType = request.getParameter("coType");
 	
 	String [] coTypeName = {"회원","게시글","댓글"};
%>

<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript">

$(function() {
	$("#ComplaintForm").on("submit", function(event){
		var cContent = $(this).find("textarea").val();
		if(cContent.length == 0){
			event.preventDefault();	
			opener.alert("내용을 입력하세요.");
			window.close();
		} else {
			event.preventDefault();
			var coType = <%=coType%>;
			if(coType == "1"){
				var coTarget = <%=userid%>;
			} else if(coType == "2") {
				var coTarget = <%=pNum%>;
			} else if(coType == "3") {
				var coTarget = <%=cNum%>;
			}
			$.ajax({
				type: "post",
				url: "complaintAccept",
				data: {
					coTarget: coTarget,
					userid : <%=dto.getUserid()%>,
					coType : <%=coType%>,
					coContent : $("#coContent").val()
				}, //data
				dataType: "text",
				success: function(data, status, xhr) {
					if(data=="true"){
						opener.alert("<%=coTypeName[Integer.parseInt(coType)-1]%> 신고가 성공적으로 완료되었습니다.");
						window.close();
					} else if(data=="false") {
						opener.alert("<%=coTypeName[Integer.parseInt(coType)-1]%> 신고가 실패하였습니다.");
						window.close();
					}else if(data=="dup") {
						opener.alert("이미 신고한 <%=coTypeName[Integer.parseInt(coType)-1]%>입니다.");
						window.close();
					}//if_elseif_elseif
				}, //success
				error: function(xhr, status, error) {
					$("#result").append(error);
					$("#result").append(status);
				} //error
			});//ajax
		}//if_else
	});//on
});//end ready
</script>
<body>
	<form id="ComplaintForm">
		<div class="row" style="margin-left: 5px; margin-right: 5px">
			<div class="col-md-offset-5 col-md-3">
				<!-- 
				신고 분류<br>
				<select class="complaint-select" name="category" id="category" >
			  					<option value="C">광고/홍보 게시글입니다.</option>
								<option value="L">실제로 판매하지 않는 허위매물입니다.</option>
								<option value="A">부적절한 사진 또는 글내용이 포함되어 있습니다.</option>
								<option value="E">기타</option>
							</select><br>
				-->
				<h4 style ="text-align:center">세부 신고 내용</h4>
				<textarea rows="20" cols="20" name="coContent" id="coContent" class="form-control input-sm chat-input" style="resize: none;"></textarea><br>
				<input  class="btn btn-danger btn-md" type = "submit" value = "신고">
			</div>
		</div>
	</form>
</body>
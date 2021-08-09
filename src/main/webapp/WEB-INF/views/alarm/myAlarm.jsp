<%@page import="com.dto.PostDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../layout/toplayout.jsp" flush="true"></jsp:include>
<!--부트스트랩 css cdn  -->
<style>
.footerfix{
	padding-bottom: 300px;
}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//전체 체크
		$("#allCheck").click(function() {
			var result = this.checked;
			var num = [];
			$(".check").each(function(idx, data) {
				//this.checked = result;
				data.checked = result;
			});
		});//end allCheck
		
		//개별 삭제
		$(".delBtn").on("click", function() {
			var num = $(this).attr("data-xxx");
			location.href="loginCheck/alarmDeleteOne?aNum="+num; 
		});//end delBtn
		
		//개별 읽음
		$(".readBtn").on("click", function() {
			var num = $(this).attr("data-xxx");
			location.href="loginCheck/alarmReadOne?aNum="+num; 
		});//end readBtn
		
		$("#delAllAlarm").click(function() {
			var num = [];
			$(".check:checked").each(function(idx, data) {
				num[idx] = $(this).val();
			});
			
			location.href="/loginCheck/deleteAlarmAll?data="+num;
		});
		
		$("#readAllAlarm").click(function() {
			var num = [];
			$(".check:checked").each(function(idx, data) {
				num[idx] = $(this).val();
			});
			
			location.href="/loginCheck/readAlarmAll?data="+num;
		});
		
		$(".goPost").click(function(){
			var num = $(this).attr("data-info");
			location.href="postDetail?pNum="+num;
			// 글 눌러서 이동하면 alarm의 isRead값 수정하기
		});
		
		$(".goOrderSheet").click(function(){
			location.href="loginCheck/OrdersheetList";
		});
		
	})//end ready
	
</script>
<style>
h2{
	margin: 30px;
	margin-left: 10px;
}

.alarm.read td {
	color: #8E8E93; 
}

.goPost:hover{
	cursor : pointer;
	text-decoration : underline;
}

.goOrderSheet:hover{
	cursor : pointer;
	text-decoration : underline;
}
</style>


<div class="container footerfix">
<h2>내 알림함</h2>
<table class="table table-hover" >

	<thead>
		<tr>
			<th scope="col" class="text-center" >
			<input type="checkbox" name="allCheck" id="allCheck">전체선택</th>
			<th scope="col"  class="text-center">알림종류</th>
			<th scope="col" class="text-center">보낸사람</th>
			<th scope="col" class="text-center">상세정보</th>
			<th scope="col" class="text-center"></th><!-- 확인, 삭제 -->
		</tr>
	</thead>
	<tbody>
	<c:forEach var="a" items="${myAlarmList}">
		<tr class="alarm${a.isRead == 1 ? ' read'  : ' unread'}">
			<td style="width: 10%;" class="text-center" width="1">
				<input type="checkbox" name="check" id="check" class="check"  value="${a.aNum}">
			</td>
			<td class="text-center" width="1">${typeMap[a.type]}</td><!-- 알림종류 -->
			<td class="text-center" width="80">${a.sender}</td> <!-- 보낸사람 -->
			<td class="text-center" width="120">
				<c:if test="${a.type != 'o' }">
					<div class="goPost" data-info="${a.info}">${a.detail}</div>
				</c:if>
				<c:if test="${a.type == 'o' }">
					<div class="goOrderSheet">${a.detail}</div>
				</c:if>
			</td><!-- 상세정보 -->
			<td class="text-center" align="center" width="30" style='padding-left: 10px'>
				<c:if test="${a.isRead == 0 }">
					<input type="button" value="확인"  class="readBtn" data-xxx="${a.aNum}">
				</c:if>
				<input type="button" value="삭제" class="delBtn" data-xxx="${a.aNum}">
			</td>	
		</tr>
	</c:forEach>
	</tbody>
	
</table>
<button type="button" class="btn btn-outline-info" id="delAllAlarm">알림 삭제하기</button>&nbsp;&nbsp;
<button type="button" class="btn btn-outline-info" id="readAllAlarm">알림 확인하기</button>&nbsp;&nbsp;


</div> <!--컨테이너  -->

<jsp:include page="../layout/bottomLayout.jsp" flush="true"></jsp:include>


<%@page import="com.dto.MyOrderSheetDTO"%>
<%@page import="com.dto.OrderSheetDTO"%>
<%@page import="com.dto.PostDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../layout/toplayout.jsp" flush="true"></jsp:include>
<!--부트스트랩 css cdn  -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
function oMessage(oNum) {
	var popupWidth = 540;
	var popupHeight = 600;
	var popupX = (window.screen.width / 2) - (popupWidth / 2);
	var popupY= (window.screen.height / 2) - (popupHeight / 2);
	url = "/loginCheck/PopupMessage?oNum="+oNum;
	console.log(oNum);
	open(url,"Message", 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
} 

	
	$(document).ready(function() {
		//전체 체크
		$("#allCheck").click(function() {
			var result = this.checked;
			var num = [];
			$(".check").each(function(idx, data) {
				data.checked = result;
			});
		});//end allCheck
		
		//개별 삭제
		$(".delBtn").on("click", function() {
			var num = $(this).attr("data-xxx");
			console.log(num);
			location.href="/loginCheck/OrderDel?oNum="+num; 
		});//end delBtn
		
		
	
		//체크한 게시글 삭제
		$("#delAllorder").click(function() {
			var num = [];
			$(".check:checked").each(function(idx, data) {
				num[idx] = $(this).val();
			});
			location.href="/loginCheck/OrderDelAll?data="+num; 
		});//end delAllorder 
		
	/* 	//클릭시  이동할 페이지와 자식창 크기 조정 함수
		function oMessage() {
			var popupWidth = 300;
			var popupHeight = 400;
			var popupX = (window.screen.width / 2) - (popupWidth / 2);
			var popupY= (window.screen.height / 2) - (popupHeight / 2);
			var oNum = $("#message").attr("data-oNum");
			url = "PopupMessage?oNum="+oNum;
			open(url,"Message", 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
		}
		//메세지창열기
		$("#message").click(function() {
			oMessage();
		}) */
		
		
	})//end ready
	
</script>
<style>
h2{
	margin: 30px;
}

.footerfix{
	padding-bottom: 500px;
}


</style>

<h2>수신함</h2>

<div class="container footerfix">

<table class="table table-hover" >

	<thead>
		<tr>
			<th scope="col" class="text-center" >
			<input type="checkbox" name="allCheck" id="allCheck">전체선택</th>
			<th scope="col"  class="text-center">상품번호</th>
			<th scope="col" class="text-center">상품정보</th><!--pImage 랑 pTitle 같이 출력  -->
			<th scope="col" class="text-center">메세지</th><!--pHit  -->
			<th scope="col" class="text-center">제시가격</th>
			<th scope="col" class="text-center">작성일</th><!--pDate  -->
			<th scope="col" class="text-center"></th>
		</tr>
	</thead>
	<tbody>
<c:forEach var="o" items="${ordersheetList}">	
	
	<tr>
	
		<td style="width: 10%;" class="text-center" width="1"><input type="checkbox"
			name="check" id="check" class="check"  value="${o.oNum}"></td><!--주문서번호  -->
		<td class="text-center" width="1">${o.pNum}</td><!--상품번호  -->	
		<td class="text-center" width="120"><a
			href="/postDetail?pNum=${o.pNum}">
				 <img src="/Dong-Dong/images/${o.pImage}"
				border="0"  width="80" /></a>
				<div>
				${o.pTitle}
				</div></td>
		<td class="text-center" width="20">
		<div>
		<font size="2">[아이디 :${o.bUserid}]</font><br>
		<font size="2">${o.oMessage}</font><br>
		<button id="message" data-oNum="${o.oNum}" class="btn btn-outline-primary btn-sm" onclick = "oMessage(${o.oNum});">더보기</button>
		</div>
		</td><!--채팅메세지  --><!--미리보기 조금만  -->	
		
		<td class="text-center" width="80">${o.oPrice}</td><!--제시가격  -->
		<td class="text-center" width="40">${o.oDate}</td><!--주문서 보낸 날짜  -->
		<td class="text-center" align="center" width="30" 
			style='padding-left: 10px'>
			<input type="button" value="삭제"  class="delBtn" data-xxx="${o.oNum}" ><!--주문서 삭제시 오더번호로  -->
		</td>
		
	</tr>

</c:forEach>
	<hr>
	</tbody>
	<tr class="button">
		<td colspan="3" >
			<a class="text-center" href="#" id="delAllorder"> 주문서 삭제하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	
</table>
</div> <!--컨테이너  --> 

<jsp:include page="../layout/bottomLayout.jsp" flush="true"></jsp:include>



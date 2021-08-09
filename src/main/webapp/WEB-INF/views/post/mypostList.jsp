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
	window.name = "parent";
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
			location.href="/loginCheck/mypostDelete?pNum="+num; 
		});//end delBtn
		
		// 끌올
		// 끌올 버튼시 이동할 페이지와 자식창 크기 조정 함수
		function pullPost(num) {
			// 자식창 중앙 정렬
			var popupWidth = 500;
			var popupHeight = 300;
			//오류 주의...
			var popupX = (window.screen.width / 2) - (popupWidth / 2);
			// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼줌
			var popupY= (window.screen.height / 2) - (popupHeight / 2);
			// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼줌
			
			// 해당 버튼에 저장되어있는 pNum값을 뽑아서 PostPullUIServlet에 같이 넘겨준다.
			
			url = "/loginCheck/postPull?pNum="+num;
			open(url,"child_pull", 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
		}
		// 끌올 버튼 클릭 시 자식창(pullPost.jsp)가 뜨게한다
		$(".pullBtn").click(function() {
			var num = $(this).attr("data-xxx");
			pullPost(num);
		})
		
		//체크한 게시글 삭제
		$("#delAllpost").click(function() {
			var num = [];
			$(".check:checked").each(function(idx, data) {
				num[idx] = $(this).val();
			});
			
			location.href="/loginCheck/postDelAll?data="+num;
		});//end delAllpost
		
		
	})//end ready
	
</script>
<style>
h2{
	margin: 30px;
	margin-left: 10px;
}


</style>


<div class="container footerfix">
<h2>내 게시물 보기</h2>
<table class="table table-hover" >

	<thead>
		<tr>
			<th scope="col" class="text-center" >
			<input type="checkbox" name="allCheck" id="allCheck">전체선택</th>
			<th scope="col"  class="text-center">상품번호</th>
			<th scope="col" class="text-center">상품정보</th><!--pImage 랑 pTitle 같이 출력  -->
			<th scope="col" class="text-center">가격</th>
			<th scope="col" class="text-center">작성일</th><!--pDate  -->
			<th scope="col" class="text-center">조회수</th><!--pHit  -->
			<th scope="col" class="text-center"></th>
		</tr>
	</thead>
	<tbody>
<c:forEach var="p" items="${mypostList}">

	<tr>
		<td style="width: 10%;" class="text-center" width="1"><input type="checkbox"
			name="check" id="check" class="check"  value="${p.pNum}"></td>
		<td class="text-center" width="1">${p.pNum}</td>	
		<td class="text-center" width="120"><a
			href="/postDetail?pNum=${p.pNum}">
				<img src="/Dong-Dong/images/${p.pImage}"
				border="0"  width="80" /></a>
				<div>
				${p.pTitle}
				</div></td>
		<td class="text-center" width="80">${p.pPrice}</td>
		<td class="text-center" width="40">${p.pDate}</td>
		<td class="text-center" width="20">${p.pHit}</td>
		
		<td class="text-center" align="center" width="30"
			style='padding-left: 10px'>
			<input type="button" value="삭제"  class="delBtn" data-xxx="${p.pNum}">
			<input type="button" value="끌올" class="pullBtn" data-xxx="${p.pNum}">
		</td>
		
		
	</tr>

	</c:forEach>
	<!-- <hr> -->
	</tbody>
	<tr class="button">
		<td colspan="3" >
			<a class="text-center" href="#" id="delAllpost"> 게시글 삭제하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="main"> 계속 둘러보기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	
</table>
</div> <!--컨테이너  -->

<jsp:include page="../layout/bottomLayout.jsp" flush="true"></jsp:include>



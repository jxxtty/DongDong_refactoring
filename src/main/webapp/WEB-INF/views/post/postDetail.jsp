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
#mainImgDiv{
	max-height: 700px;
	overflow: hidden;
}
#mainImage{
	max-height: initial;
	margin-top: -10%;
}
#multiImgCenter{
	width : 100%;
	height : 100%;
	display : flex;
	justify-content : center;
}
</style>

<script type="text/javascript">
	var pTitle = "${pTitle}";
	var favorite = ${favorite};
	var pNum = ${pNum};
	$(function() {
		
		$("#favorite").on("click", function(){
			$.ajax({
				type: "get",
				url: "/loginCheck/favorateSwitch",
				data: {
					pNum: ${pNum},
					favorite : favorite
				}, //data
				dataType: "text",
				success: function(data, status, xhr) {
					if(data=="true"){
						var favoriteCountTemp = $("#favoriteCountSpan").text();
						$("#favoriteImg").attr("src","/Dong-Dong/images/util/favorite1.png");
						$("#favoriteCountSpan").text(Number(favoriteCountTemp)+1);
						favorite = true;
					} else {
						var favoriteCountTemp = $("#favoriteCountSpan").text();
						$("#favoriteImg").attr("src","/Dong-Dong/images/util/favorite2.png");
						$("#favoriteCountSpan").text(favoriteCountTemp-1);
						favorite = false;
					}//if_else
				}, //success
				error: function(xhr, status, error) {
					$("#result").append(error);
					$("#result").append(status);
				} //error
			});//ajax
		});//on
			
		function complaintPost() {
			var popupWidth = 300;
			var popupHeight = 500;
			var popupX = (window.screen.width/2)-(popupWidth/2);
			var popupY= (window.screen.height/2)-(popupHeight/2);
			url = "loginCheck/complaintDetail?pNum="+"${pNum}"+"&coType=2";
			open(url,"complaintPost", 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
		}//end compliantPost
		
		$("#complaintPost").click(function() {
			complaintPost();
		});
		
		function userprofile() {
			var popupWidth = 300;
			var popupHeight = 500;
			var popupX = (window.screen.width / 2) - (popupWidth / 2);
			var popupY= (window.screen.height / 2) - (popupHeight / 2);
			url = "userprofile?userid="+"${userid}";
			open(url,"userprofile", 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
		}//end userprofile
			
		$("#userprofile").click(function() {
			userprofile();
		});
		
		
		$(".multiImageOne").on("click",function(){
			var path = $(this).attr('src');
			$("#mainImage").attr("src", path);
			
		});
		
	});//end ready
	
</script>

<!-- --------------------------------페이지 표시 시작 지점--------------------------------- -->  
  <div class="container" style="max-width: 1100px">
    <div class="row align-items-center my-0">
      <div id="mainImgDiv" class="col-lg-7">
      	<c:if test="${isMultiFile eq 'N'}">
      		<img id="mainImage" class="img-fluid rounded mb-4 mb-lg-0" src="/Dong-Dong/images/${imageDetail[0]}" width="700px" height="">
      	</c:if>
        <c:if test="${isMultiFile eq 'Y'}">
        	<img id="mainImage" class="rounded mb-4 mb-lg-0" src="/Dong-Dong/images/${imageDetail[0]}" width="100%" height="600px">
      		<div id="multiImgCenter">
  				<div class="card text-dark my-0 py-0" id="multiImgDiv" style="border: none; ">
      				<div class="align-items-center" id="multiImage">
      					<c:forEach var="originImage" items="${imageDetail}">
							<div style="display: inline-flex; padding-top: 10px; padding-right: 10px;">
								<li style="list-style:none;">
									<img src="/Dong-Dong/images/${originImage}" class="multiImageOne" width="100" height="100" /><br>
								</li>
							</div>
						</c:forEach>
					</div>
    			</div>
    		</div>
  		</c:if>
      </div>
      <div class="col-lg-5 font-weight-bold" style="text-align: left">
        <br>
        <h7 class="font-weight-light text-secondary">>${category}</h7>
        <h2 class="font-weight-light" style="line-height: 1.5;">${pTitle}</h2>
        <c:if test="${pStatus!='1'}">
        <h3 class="font-weight-light">${price}원</h3><br>
        </c:if>
        <c:if test="${pStatus=='1'}">
        <h3 class="font-weight-light">판매완료</h3><br>
        </c:if>
        <div class="text-secondary font-weight-bold flex-nowrap" style="text-align : left;">

					<span style="margin-left: 2px"><img src="/Dong-Dong/images/util/heart.png" width="20"/></span>
					<span style="margin-left: 2px" id="favoriteCountSpan">${favoriteCount}</span>
					<span style="margin-left: 15px"><img src="/Dong-Dong/images/util/eye.png" width="20"/></span>
					<span style="margin-left: 2px">${pHit}</span>
					<span style="margin-left: 15px"><img src="/Dong-Dong/images/util/time.png" width="20"/></span>
					<span style="margin-left: 2px">${pDate.substring(0, pDate.length()-3)}</span>

	    </div>
        <hr>
		<table class="table table-borderless">
		   <tr>
		      <th>유저</th>
		      <td>${nickName}</td>
		      <td><div style="width: 70px"></div><td>
		   </tr>
		   <tr>
		      <th>거래지역</th>
		      <td>${addr}</td>
		      <td></td>
		   </tr>
		</table>
		<div>
		<!-- 버튼 표시 시작 -->
		<c:if test="${empty login}">
			<a class="btn btn-primary" href="loginForm">&nbsp;구매시 로그인이 필요합니다.&nbsp;</a>&nbsp;
		</c:if>
		<c:if test="${!empty login && pStatus!='1'&&login.userid==userid}">
			<a class="btn btn-primary" href="loginCheck/postUpdate?pNum=${pNum}">&nbsp;상품 정보 수정&nbsp;</a>&nbsp;
			<a class="btn btn-primary" href="loginCheck/postDelete?pNum=${pNum}">&nbsp;상품 삭제&nbsp;</a>&nbsp;
		</c:if>
		<c:if test="${!empty login && pStatus!='1'&&login.userid!=userid}">
			<a class="btn btn-primary" onclick="window.open('orderSheet?sUserid=${userid }&pNum=${pNum }&pPrice=${pPrice }','window_name','width=400,height=300,location=no,status=no,scrollbars=yes,left='+((window.screen.width/2)-200)+',top='+((window.screen.height/2)-250))">&nbsp;주문서작성&nbsp;</a>&nbsp;
		</c:if>
		
			<a id="userprofile" class="btn btn-primary">&nbsp;프로필&nbsp;</a>&nbsp;
		<c:if test="${!empty login &&login.userid!=userid}">
			<a id="complaintPost" class="btn btn-danger">&nbsp;신고&nbsp;</a>
		</c:if>
		<c:if test="${!empty login&&pStatus!='1'&&login.userid!=userid}">
			<a id="favorite"  class="btn">
			<c:if test="${favorite==true}">
	    	    <img id="favoriteImg" src="/Dong-Dong/images/util/favorite1.png"  width="50" height="50"/>
	    	</c:if>
	    	<c:if test="${favorite==false}">
	    	    <img id="favoriteImg" src="/Dong-Dong/images/util/favorite2.png"  width="50" height="50"/>
	    	</c:if>
			</a> 
		</c:if>
		</div>
      </div>
    </div>
	
	<!-- 다중파일일 경우 mainImg아래 넣는다. -->
<%-- 	<c:if test="${isMultiFile eq 'Y'}">
  		<div class="card text-dark my-0 py-0" id="multiImgDiv" style="border: none; ">
      		<div class="align-items-center" id="multiImage">
      			<c:forEach var="originImage" items="${imageDetail}">
					<div style="display: inline-flex; padding-top: 10px; padding-right: 10px;">
						<li style="list-style:none;">
							<img src="/Dong-Dong/images/${originImage}" class="multiImageOne" width="100" height="100" /><br>
						</li>
					</div>
				</c:forEach>
			</div>
    	</div>
  	</c:if> --%>
	
    <div class="card text-dark my-5 py-4" style="border: none; ">
      <div style="text-align : left; font-weight: bolder; margin-left: 20px"><h2>상세 설명</h2></div>
      <hr>
      <div class="card-body" style="text-align : left; min-height : 200px; margin-left: 20px">
        <h5 style="line-height: 1.7;">${pContent}</h5>
      </div>
    </div>
    
    
  
    
    
    
 </div>
  
  	

    
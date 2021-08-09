<%@page import="com.dto.FavoriteDTO"%>
<%@page import="com.dto.PostDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <jsp:include page="../layout/toplayout.jsp" flush="true"></jsp:include>
<!--부트스트랩 css cdn  -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<style>


ul {
    list-style:none;
    margin:10px;
    padding:0;
}

li {
	display:inline;
	float: left;
	width: 30%;
    margin: auto;
    padding: 0 0 0 0;
    border: 0;
} 
h6 {
	text-align: center;
}
.footerfix{
	padding-bottom: 500px;
}

</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//개별 삭제
		$(".delBtn").on("click", function() {
			var num = $(this).attr("data-xxx");
			console.log(num);
			location.href="/loginCheck/favoriteDel?num="+num; 
		});//end delBtn
		
	})//end ready
	
</script>	
<body id="bb">
<h2>&nbsp;찜</h2>   
<br>
<div class="container footerfix">
   
<c:forEach var="f" items="${favoriteList}">
<ul>
<li>
   	 <div class="card" style="margin: 5px; "> 
      <a href="/postDetail?pNum=${f.pNum}"><!--클릭시 이동 기동이형한테 물어보기  -->
      <img class="card-img-top" src="/Dong-Dong/images/${f.pImage}" alt="Responsive image" style="max-width:250px; height:205px; display: block; margin: auto;" ></a>
      <div class="card-body">
        <h6 class="card-title" style="height:25px;">${f.pTitle}</h6>
        <h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${f.pPrice}원<button data-xxx="${f.pNum}" class="btn btn-outline-danger delBtn"  style="float: right;">삭제</button></h6>
      </div>
   </div>
</li>
</ul>  	

</c:forEach>
</div><!--컨테이너  -->


</body>
<jsp:include page="../layout/bottomLayout.jsp" flush="true"></jsp:include>
			

    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../layout/toplayout.jsp" flush="true"></jsp:include>

<style>
.info1 {
	border: solid 0.5px;;
	margin-top: 30px;
	padding: 20px;
}
#withdrawal {
	font-weight: bold;
}

#info1-1 {
	color: #dc143c;
	font-weight: bold;
}

#info-2 {
	font-weight: bold;
}

#checkbox {
	margin-top: 30px;
}


</style>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
	
		//회원탈퇴 버튼
	 	$("#myForm").on("submit", function() {	
	 		var chk = $('input:checkbox[id="check"]').is(":checked");
	 		if(chk == true){
	 			$("#myForm").attr("action","/loginCheck/Withdrawal");
	 		}else{
	 			alert("약관에 동의해주세요.");
	 			return false;
	 		};
		});
		
		
 });//end ready
</script>
 

<form id="myForm" action="#" method="get">
<div class="container">
<div>
<h2 id="withdrawal">회원탈퇴</h2>
</div>
<div class="info1">
<h5 id="info1-1">회원탈퇴 시 개인정보 및 Dong-Dong에서 만들어진 모든 데이터는 삭제됩니다.<br>
(단, 아래 항목은 표기된 법률에 따라 특정 기간 동안 보관됩니다.)</h5>
&nbsp;&nbsp;1. 계약 또는 청약철회 등에 관한 기록 보존 이유: 전자상거래 등에서의 소비자보호에 관한 법률 / 보존 기간:5년<br>
&nbsp;&nbsp;2. 결제 및 공급에 관한 기록 보존 이유: 전자상거래 등에서의 소비자보호에 관한 법률 / 보존 기간:5년<br>
&nbsp;&nbsp;3. 전자금융 거래에 관한 기록 보존 이유: 전자금융거래법 보존 기간 / 5년<br>
&nbsp;&nbsp;4. 해당 회원의 관한 신고처리 기록 보존 이유: 전자상거래 등에서의 소비자보호에 관한 법률 / 보존 기간:3년<br> 
</div>

<div id=info1-2>
<br>
<ul><li><h5 id="info-2">유의사항</h5></li></ul>
</div>

<div class="info1">
&nbsp;- 회원탈퇴 처리 후에는 회원님의 개인정보를 복원할 수 없으며, 탈퇴 시 해당 아이디는 영구적으로 삭제되어 재가입이 불가합니다.<br>
&nbsp;- 진행 중인 거래 건이 있는 경우 탈퇴 신청이 불가합니다.
</div>

<div id=info1-2>
<br>
<ul><li><h5 id="info-2">탈퇴사유</h5></li></ul>
</div>
<div>
	<select style="width: 100%; height: 30px;" id="reason">
        <option selected="selected">더 이상 이용하지 않음</option>
        <option>아이디변경 / 재가입 목적</option>
        <option>서비스가 마음에 들지 않음</option>
        <option>타 중고거래 사이트 이용</option>
	</select>
</div>

<div id="checkbox">
<input type="checkbox" name="check" id="check"  >&nbsp;해당 내용을 모두 확인했으며, 회원탈퇴에 동의합니다.
</div>

<div style="text-align: center;">
<br>
<button  class="btn btn-outline-danger btn-lg" id="withdrawal">회원탈퇴</button>
</div>
</div><!--container  -->
</form>



<jsp:include page="../layout/bottomLayout.jsp" flush="true"></jsp:include>

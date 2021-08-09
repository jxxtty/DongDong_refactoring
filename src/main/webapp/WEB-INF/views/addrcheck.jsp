<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>우리동네 Dong-Dong</title>
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
	<style type="text/css">
	 * {
		font-family: 'Nanum Gothic', sans-serif;
		font-size: 15px;
		font-weight : 400;
	}
	 
	 .title {
	 	font-weight:bold;
	 	display:block;
	 }
	</style>
</head>
<body>
	<div id="map" style="width:500px;height:400px;"></div>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7fb0b36aa207b6f570e9463049bd9682"></script>
	<script>
	

	$(document).ready(function() {
		$("#addrcheck").click(function() {
			console.log("이벤트"+$("#dong").text());
			var dong = $("#dong").text();
			console.log(dong);
			location.href ="/loginCheck/addrCheckC?dong="+dong;
		});
	})//end ready
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 10 // 지도의 확대 레벨 
    }; 

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
        
        var locPosition = new kakao.maps.LatLng(lat, lon) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
        //var message = '<div style="padding:5px;">여기가 우리집!!!!</div>'; // 인포윈도우에 표시될 내용입니다
        var message =""; // 인포윈도우에 표시될 내용입니다
   
        
        
            console.log(locPosition);
            
            // 마커와 인포윈도우를 표시합니다
        displayMarker(locPosition, message, lat, lon);
        //console.log(message);
           // displayMarker(locPosition, function() {
           
                  
		//});
        
      });
    
} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
    
    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
        message = 'geolocation을 사용할수 없어요..'
        
    displayMarker(locPosition, message);
}

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message, lat, lon) {
	
	
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    }); 
    
    $.ajax
	({
		type:"get",
		url:"https://dapi.kakao.com/v2/local/geo/coord2address.json?x="+lon+"&y="+lat,
		headers: {"Authorization": "KakaoAK 44cab8379502678da5179dc3b2ba3ba7"},	
		async:false,//false하면 전역변수로 사용가능
		
		/*  dataType:"json", 
		   data:{
			x: lat,
			y: lon
		},    */
		success: function(data, status, xhr) {
			//console.log(data);
			//console.log(status);
			var address =data.documents[0].address;
			//console.log(data.documents[0]);
			message = '<div id="addr1"><span class="title">지번 주소 :</span> ' + address.address_name +
			'</div>'+'<div id="dong" style="display:block">'+address.region_3depth_name+'</div>';
			
		},
		error: function(xhr, status, error) {
			var message = "주소를 불러오지 못했습니다."
		}
	})//end ajax
    
    var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;
    
    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });
    
    // 인포윈도우를 마커위에 표시합니다 
    infowindow.open(map, marker);
    
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);
    console.log(lat);
    console.log(lon);

}    
	</script>
	<br>
<button style="width: 100%;" class="btn btn-outline-primary" id="addrcheck" >동네 인증하기</button>
</body>
</html>
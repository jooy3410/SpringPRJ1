<%@page import="java.util.ArrayList"%>
<%@page import="poly.dto.RestDTO"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
    
<%
   /* Controller로부터 넘겨받은 rList 선언 */
   List<RestDTO> rList = (List<RestDTO>) request.getAttribute("rList");
/*    String SS_ADDR = nvl((String)session.getAttribute("REFINE_ROADNM_ADDR"));
   String addr[] = new String[10];
   for(int i =0; i<10; i++) {
         addr[i] = nvl((String)session.getAttribute("REFINE_ROADNM_ADDR"+i));
   } */
      
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마커 표시하기</title>
</head>
<body>
<p style="margin-top:-12px">
    <em class="link">
       <!-- <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
            혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요.
        </a>  --> 
    </em>
</p>
<div id="map" style="width:100%;height:650px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=364f61f6129e3a90252ccc45e181031b&libraries=services"></script>
<script>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(37.154401, 127.014467), // 지도의 중심좌표
    level: 7 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

<%for (RestDTO eDTO : rList) {%>
//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();


//주소로 좌표를 검색합니다
geocoder.addressSearch('<%=nvl(eDTO.getRefine_lotno_addr())%>', function(result, status) {

	alert(result);
	
// 정상적으로 검색이 완료됐으면 
 if (status === kakao.maps.services.Status.OK) {

    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    
    
    	var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });	
 
   
    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;" id="marker" onclick="doMarker();"><%=nvl(eDTO.getBizplc_nm())%></div>'
    });
    infowindow.open(map, marker);

    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
} 
});
<%}%>

</script>
</body>
</html>
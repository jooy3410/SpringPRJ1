<%@page import="poly.dto.RestDTO"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	RestDTO rDTO = (RestDTO) request.getAttribute("rDTO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보 페이지</title>

</head>
<body>

	<div style="margin: auto; width: 800px;">
		<table border="1" style="width: 100%;">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td><%=nvl(rDTO.getSafety_restrnt_no())%></td>
					<td><%=nvl(rDTO.getBizplc_nm())%></td>
					<td><%=nvl(rDTO.getRefine_lotno_addr())%></td>
					<td><%=nvl(rDTO.getRefine_roadnm_addr())%></td>
					<td><%=nvl(rDTO.getDetail_addr())%></td>
					<td><%=nvl(rDTO.getSigngu_nm())%></td>
					<td><%=nvl(rDTO.getSido_nm())%></td>
					<td><%=nvl(rDTO.getIndutype_nm())%></td>
					<td><%=nvl(rDTO.getIndutype_detail_nm())%></td>
				</tr>

			</tbody>
		</table>
	</div>

	<p style="margin-top: -12px">
		<em class="link"> <!-- <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
            혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요.
        </a>  -->
		</em>
	</p>
	<div id="map" style="width: 100%; height: 650px;"></div>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=364f61f6129e3a90252ccc45e181031b&libraries=services"></script>
	<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

//주소로 좌표를 검색합니다
geocoder.addressSearch('<%=nvl(rDTO.getRefine_lotno_addr())%>', function(result, status) {
	
// 정상적으로 검색이 완료됐으면 
 if (status === kakao.maps.services.Status.OK) {

    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
   
    var marker = new kakao.maps.Marker({
        map: map,
        position: coords
    });	
    
 // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
    var iwContent = '<div style="padding:5px;"><%=nvl(rDTO.getBizplc_nm())%></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
          // 마커 위에 인포윈도우를 표시합니다
          infowindow.open(map, marker);  
    });
								
//지도의 중심을 결과값으로 받은 위치로 이동시킵니다					
 map.setCenter(coords);
 }
						});

		// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
		// marker.setMap(null);
	</script>
<table border="2">
		<tbody class="center">
			<tr>
				<th>점검항목</th>
				<th colspan="6">점검결과 (세가지 중 해당에 체크)</th>
			</tr>
			<tr>
				<th>(밀폐도) 건물 외부 공기로<br>자연환기가 가능한가?
				</th>
				<td>창문 출입문 통해<br>상시 환기 가능
				</td>
				<td><input type="radio" name="air" value="low"></td>
				<td>창문 출입문 통해<br>일 2회 이상<br>환기 가능
				</td>
				<td><input type="radio" name="air" value="middle"></td>
				<td>창문 출입문 통해<br>환기 불가<br>(지하에 위치 등)
				</td>
				<td><input type="radio" name="air" value="high"></td>
			</tr>
			<tr>
				<th>(밀집도) 이용자간 거리두기가<br>가능한가?
				</th>
				<td>이용자간 항상<br>2m 유지 가능
				</td>
				<td><input type="radio" name="distance" value="low"></td>
				<td>이용자간 1m 이상<br>유지 가능<br>(테이블간, 좌석간)
				</td>
				<td><input type="radio" name="distance" value="middle"></td>
				<td>이용자간 1m 거리<br>유지 불가<br>(테이블간, 좌석간)
				</td>
				<td><input type="radio" name="distance" value="high"></td>
			</tr>
			<tr style="height:50px;">
				<th>(지속도) 이용자의 평균 체류시간은?</th>
				<td>15분 이내</td>
				<td><input type="radio" name="time" value="low"></td>
				<td>1시간 이내<br>
				<td><input type="radio" name="time" value="middle"></td>
				<td>1시간 이상</td>
				<td><input type="radio" name="time" value="high"></td>
			</tr>
			<tr>
				<th>(군집도) 시설 내 동시 이용<br>인원의 규모는?</th>
				<td>10명 미만</td>
				<td><input type="radio" name="scale" value="low"></td>
				<td>10명 이상<br>100명 미만</td>
				<td><input type="radio" name="scale" value="middle"></td>
				<td>100명 이상</td>
				<td><input type="radio" name="scale" value="high"></td>
			</tr>
			<tr>
				<th>(활동도) 침방울(비말) 발생정도는<br>어떠한가?</th>
				<td>침방울 발생<br>정도 거의<br>없음</td>
				<td><input type="radio" name="activity" value="low"></td>
				<td>일상적 대화<br>수준의 침방울<br>발생</td>
				<td><input type="radio" name="activity" value="middle"></td>
				<td>적극적 침방울 발생<br>(노래, 춤, 격한<br>운동, 응원 등)</td>
				<td><input type="radio" name="activity" value="high"></td>
			</tr>
			<tr>
				<th colspan="7">(관리도) 방열 수칙 준수 정도는? (아래 항목에 체크)</th>
			</tr>
			<tr>
				<td colspan="7">
					<table border="1" style="width:100%">
						<tr>
							<th class="center" style="width:100px">준수</th>
							<th class="center" style="width:100px">미준수</th>
							<th class="center">방역 수칙 항목</th>
						</tr>
						<tr>
							<td><input type="radio" name="manager" value="a"></td>
							<td><input type="radio" name="manager" value="an"></td>
							<th>방역관리자를 지정하고, 방역지침을 마련하였는가?<span class=font-style>(유증상자 확진자 발생시 대응체계 등 포함)</span></th>
						</tr>
						<tr>
							<td><input type="radio" name="hdisinfectant" value="a"></td>
							<td><input type="radio" name="hdisinfectant" value="an"></td>
							<th>화장실 외, 손위생 시설<span class=font-style>(세수대의 비누)</span> 또는 손 소독제가 비치되어 있는가?</th>
						</tr>
						<tr>
							<td><input type="radio" name="mask" value="a"></td>
							<td><input type="radio" name="mask" value="an"></td>
							<td>직원은 마스크를 지속 착용하는가?</td>
						</tr>
						<tr>
							<td><input type="radio" name="announcement" value="a"></td>
							<td><input type="radio" name="announcement" value="an"></td>
							<th>이용자가 마스크를 지속 착용하도록 안내하고 독려하는가? <span class=font-style>(안내문 게시 등)</span></th>
						</tr>
						<tr>
							<td><input type="radio" name="disinfection" value="a"></td>
							<td><input type="radio" name="disinfection" value="an"></td>
							<th>자주 손이 닿는 곳<span class=font-style>(손잡이, 문고리, 팔걸이 등)</span>과 화장실의 표면은 매일 1회 이상 소독하는가?</th>
						</tr>
						<tr>
							<td><input type="radio" name="namecheck" value="a"></td>
							<td><input type="radio" name="namecheck" value="an"></td>
							<th><span class=font-style>(행정명령 해당 시설만)</span>이용자 명부작성 및 코로나19 증상을 확인하고 있는가?</th>
						</tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.center{
		text-align:center;
	}
	.font-style{
		font-weight:100;
		font-size:80%;
	}
</style>
</head>
<body>
<script type="text/javascript">
	var queryString = $('#submit').serialize();
	
	&.ajax({
		url:"rest/selfCheck.do",
		type:'post',
		data: {"f" : queryString},
		success:function(data){
			alert("완료!");
		}
	});
			</script>
	<form name="f" method="post" action="/rest/selfCheck.do" id="submit">
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
	<br>
	<input type="submit" value="제출">
	</form>
</body>
</html>
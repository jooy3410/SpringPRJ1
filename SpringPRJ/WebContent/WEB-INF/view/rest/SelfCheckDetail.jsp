<%@page import="poly.dto.RestDTO"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%
	RestDTO rDTO = (RestDTO) request.getAttribute("rDTO");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>안심식당 상세정보</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">

    <link rel="stylesheet" href="../css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="../css/animate.css">
    
    <link rel="stylesheet" href="../css/owl.carousel.min.css">
    <link rel="stylesheet" href="../css/owl.theme.default.min.css">
    <link rel="stylesheet" href="../css/magnific-popup.css">

    <link rel="stylesheet" href="../css/aos.css">

    <link rel="stylesheet" href="../css/ionicons.min.css">

    <link rel="stylesheet" href="../css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="../css/jquery.timepicker.css">

    
    <link rel="stylesheet" href="../css/flaticon.css">
    <link rel="stylesheet" href="../css/icomoon.css">
    <link rel="stylesheet" href="../css/style.css">
    <script src="https://kit.fontawesome.com/54d6336788.js" crossorigin="anonymous"></script>
    <style>
    	.navbar-icon {
    	color: black;
  font-weight: 700;
  font-size: 22px;
  text-transform: uppercase;
  letter-spacing: 1px;
  line-height: 1; }
    .navbar-icon i {
    color: #167ce9;
    font-size: 30px; }
    
    form { 
        margin: 0 auto; 
        width:700px;
    }    
  
    </style>
  </head>
  <body>
    
  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
      <a class="navbar-icon" href="/index.do"><i class="fas fa-utensils"></i>&nbsp;안심식당 <br></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="oi oi-menu"></span> Menu
      </button>

      <div class="collapse navbar-collapse" id="ftco-nav">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item"><a href="/index.do" class="nav-link">홈</a></li>
          <li class="nav-item"><a href="/rest/notice.do" class="nav-link">공지사항</a></li>
          <li class="nav-item"><a href="/rest/about.do" class="nav-link">소개</a></li>
          <li class="nav-item"><a href="/rest/restPaging.do" class="nav-link">안심식당</a></li>
          <li class="nav-item"><a href="/rest/selfCheckList.do" class="nav-link">자가점검표</a></li>
          <li class="nav-item"><a href="event.html" class="nav-link">마이페이지</a></li>
          <li class="nav-item cta"><a href="/safe/Login.do" class="nav-link"><span>Login</span></a></li>
        </ul>
      </div>
    </div>
  </nav>
    <!-- END nav -->
    
    <div class="hero-wrap hero-wrap-2" style="background-image: url('/images/mask_pos.jpg'); background-attachment:fixed;">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center" data-scrollax-parent="true">
          <div class="col-md-8 ftco-animate text-center">
            <p class="breadcrumbs"><span class="mr-2">Safe</a>
            <h1 class="mb-3 bread">자가 점검표</h1>
          </div>
        </div>
      </div>
    </div>

    <section class="ftco-section contact-section ftco-degree-bg">
      <div class="container">
        <div class="row block-9">
        	<div class="col-12">
            <h2 class="h4 text-center">안심식당 자가점검표</h2>
            </div>
        	<div class="col-12 text-center">
            <p><span>안심식당 번호 : </span> <%=nvl(rDTO.getSafety_restrnt_no())%> &nbsp;&nbsp;&nbsp; <span>상호명 : </span> <%=nvl(rDTO.getBizplc_nm())%></p>
          	</div>
        <div class="col-12 text-center">
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
	</form>
        </div>
      </div>
    </section>
    <footer class="ftco-footer ftco-bg-dark ftco-section img" >
    </footer>
  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
  <script src="../js/jquery.min.js"></script>
  <script src="../js/jquery-migrate-3.0.1.min.js"></script>
  <script src="../js/popper.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  <script src="../js/jquery.easing.1.3.js"></script>
  <script src="../js/jquery.waypoints.min.js"></script>
  <script src="../js/jquery.stellar.min.js"></script>
  <script src="../js/owl.carousel.min.js"></script>
  <script src="../js/jquery.magnific-popup.min.js"></script>
  <script src="../js/aos.js"></script>
  <script src="../js/jquery.animateNumber.min.js"></script>
  <script src="../js/bootstrap-datepicker.js"></script>
  <script src="../js/jquery.timepicker.min.js"></script>
  <script src="../js/scrollax.min.js"></script>
  <script src="../js/main.js"></script>
    
  </body>
</html>
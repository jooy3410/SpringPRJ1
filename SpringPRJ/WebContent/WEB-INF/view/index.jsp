<%@page import="poly.dto.RestDTO"%>
<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String res = CmmUtil.nvl((String)request.getAttribute("res")); 	

	String num = CmmUtil.nvl((String)request.getAttribute("num"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"
	rel="stylesheet">

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

<script type="text/javascript">
	//로그인을 위한 입력정보와 유효성 체크하기
	function doLoginSafeUserCheck(f) {

		if (f.user_id.value == "") {
			alert("아이디를 입력하세요.");
			f.user_id.focus();
			return false;
		}

		if (f.password.value == "") {
			alert("비밀번호를 입력하세요.");
			f.password.focus();
			return false;
		}
	}
</script>
<script src="https://kit.fontawesome.com/54d6336788.js"
	crossorigin="anonymous"></script>
<!-- 왼쪽상단 안심식당 아이콘 스타일 적용 -->
<style>
.navbar-icon {
	color: black;
	font-weight: 700;
	font-size: 22px;
	text-transform: uppercase;
	letter-spacing: 1px;
	line-height: 1;
}

.navbar-icon i {
	color: #167ce9;
	font-size: 30px;
}
</style>

</head>
<body>

	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">

			<!--메인 로고 자리-->
			<a class="navbar-icon" href="/index.do"><i
				class="fas fa-utensils"></i>&nbsp;안심식당 <br></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a href="/index.do" class="nav-link">홈</a></li>
					<li class="nav-item"><a href="/rest/notice.do"
						class="nav-link">공지사항</a></li>
					<li class="nav-item"><a href="/rest/about.do" class="nav-link">소개</a></li>
					<li class="nav-item"><a href="/rest/restPaging.do"
						class="nav-link">안심식당</a></li>
					<li class="nav-item"><a href="/rest/selfCheckList.do"
						class="nav-link">자가점검표</a></li>
					<li class="nav-item"><a href="event.html" class="nav-link">마이페이지</a></li>
					<li class="nav-item cta"><a href="/safe/Login.do"
						class="nav-link"><span><%if(session.getAttribute("SS_USER_ID") == null){
							out.println("<a href='login.do'>로그인</a>");
							} else{
								String user_id = (String)session.getAttribute("SS_USER_ID");
								out.println(user_id + "님 반갑습니다.<br>");
							}%></span></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END nav -->

	<div class="hero-wrap"
		style="background-image: url(''); background-attachment: fixed;">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-center justify-content-center"
				data-scrollax-parent="true">
				<div class="col-md-8 ftco-animate text-center">
					<h1 class="mb-4">안심식당 for Gyeonggi-do</h1>
					<p>
						<a href="#" class="btn btn-primary px-4 py-3">Apply Now</a> <a
							href="#" class="btn btn-secondary px-4 py-3">View Courses</a>
					</p>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-counter bg-light" id="section-counter">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-10">
					<div class="row">
						<div
							class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
							<div class="block-18 text-center">
								<div class="text">
									<strong class="number" data-number="<%=res %>"></strong> <span>안심식당</span>
								</div>
							</div>
						</div>
						<div
							class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
							<div class="block-18 text-center">
								<div class="text"></div>
							</div>
						</div>
						<div
							class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
							<div class="block-18 text-center">
								<div class="text"></div>
							</div>
						</div>
						<div
							class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
							<div class="block-18 text-center">
								<div class="text">
									<strong class="number" data-number="<%=num %>"></strong> <span>자가점검표
										업데이트</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="ftco-section testimony-section"></section>







	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>


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
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script src="../js/google-map.js"></script>
	<script src="../js/main.js"></script>




</body>
</html>
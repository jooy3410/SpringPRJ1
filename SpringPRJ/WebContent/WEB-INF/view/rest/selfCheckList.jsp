<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>안심식당 정보</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
</head>
<script src="https://kit.fontawesome.com/54d6336788.js" crossorigin="anonymous"></script>
<style>
h2 {
	text-align: center;
}

table {
	width: 100%;
}

#outter {
	display: block;
	width: 60%;
	margin: auto;
}

a {
	text-decoration: none;
}

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
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	function search() {
		document.getElementById("paging").style.visibility = "hidden";
		var name = {
			"name" : $('#bizplc_nm').val()
		};
		if ($('#bizplc_nm').val() == "") {
			$('#bizplc_nm').focus();
			return false;
		}
		console.log("name : " + name);
		$
				.ajax({
					url : "/rest/restSearch.do",
					type : "post",
					data : name,
					success : function(data) {

						console.log(data);

						var resHTML = '';
						resHTML += '<tr>';
						resHTML += '<td>No.</td>';
						resHTML += '<td>상호명</td>';
						resHTML += '<td>주소</td>';
						resHTML += '</tr>';

						if (data.length == 0) {
							resHTML += '<tr>';
							resHTML += '<td>-</td>';
							resHTML += '<td>-</td>';
							resHTML += '<td>-</td>';
							resHTML += '</tr>';
							alert("찾을 수 없는 상호명입니다.");
						} else {
							var cnt = parseInt('0');
							for (var i = 0; i < data.length; i++) {

								resHTML += '<tr>';
								resHTML += '<td>' + ++cnt;
								+'</td>';
								resHTML += '<td><a href="/rest/SelfCheckDetail.do?no='
										+ data[i].safety_restrnt_no
										+ '">'
										+ data[i].bizplc_nm + '</a></td>';
								resHTML += '<td>' + data[i].refine_lotno_addr
										+ '</td>';
								resHTML += '</tr>';
							}
						}
						$("#searchResult").html(resHTML);
					}

				})
	}
</script>
<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href = "/rest/selfCheckList.do?nowPage=${paging.nowPage}&cntPerPage="
				+ sel;
	}
</script>

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

	<div class="hero-wrap1 hero-wrap-2"
		style="background-image: url('../images/course-4.jpg'); background-repeat: no-repeat; background-position: center; background-size: 50% 75%;">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-center justify-content-center"
				data-scrollax-parent="true">
				<div class="col-md-8 ftco-animate text-center">
					<p class="breadcrumbs">
					</p>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section pt-0">
		<div class="container">
			<div class="row">
				<div>
				<select id="cntPerPage" name="sel" onchange="selChange()">
					<option value="20"
						<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄
						보기</option>
					<option value="50"
						<c:if test="${paging.cntPerPage == 50}">selected</c:if>>50줄
						보기</option>
					<option value="100"
						<c:if test="${paging.cntPerPage == 100}">selected</c:if>>100줄
						보기</option>
					<option value="150"
						<c:if test="${paging.cntPerPage == 150}">selected</c:if>>150줄
						보기</option>
				</select>
				</div>
				<table class="tb" border="1" id="searchResult" >
					<tr>
						<th>No.</th>
						<th width="50%">상호명</th>
						<th>주소</th>
					</tr>
					<c:set var="num" value="1" />
					<c:forEach items="${viewAll }" var="list">
						<tr>
							<td><c:out value="${num}" /> <c:set var="num"
									value="${num + 1}" /></td>
							<td><a
								href="/rest/SelfCheckDetail.do?no=${list.safety_restrnt_no }">${list.bizplc_nm }</a></td>
							<td>${list.refine_lotno_addr }<script>
								console.log();
							</script></td>
						</tr>
					</c:forEach>
				</table>
				</div>
				</div>
			</div>
			<div class="row mt-5">
				<div class="col text-center">
					<div class="block-27">
						<div id="paging">
							<ul>
								<c:if test="${paging.startPage != 1 }">
									<li><a
										href="/rest/selfCheckList.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
								</c:if>
								<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
									var="p">
									<c:choose>
										<c:when test="${p == paging.nowPage }">
											<li class="active"><span>${p }</span></li>
										</c:when>
										<c:when test="${p != paging.nowPage }">
											<li><a
												href="/rest/selfCheckList.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>
										</c:when>
									</c:choose>
								</c:forEach>
								<c:if test="${paging.endPage != paging.lastPage}">
									<li><a
										href="/rest/selfCheckList.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
								</c:if>
								<div style="display: inline-block; margin:0;">
							</ul>
<div style="display: inline-block; margin:0;">
<div class="input-group">
<input type="text" placeholder="상호명" name="bizplc_nm" id="bizplc_nm" style="height: 40px !important;"/>
<input type="button" onClick="JavaScript:search();" value="검색"/>
</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>

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
	<script src="../js/main.js"></script>
	
</body>
</html>
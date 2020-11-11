<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil" %>

<%@ page import="poly.dto.NUserInfoDTO" %>
<%
//Controller로부터 전달받은 데이터
String msg = CmmUtil.nvl((String)request.getAttribute("msg"));

//사업자 회원 결과

//Controller로부터 전달받은 웹(회원정보 입력화면)으로부터 입력받은 데이터(아이디, 이름, 이메일, 주소 등)


//개인회원결과 

NUserInfoDTO NDTO = (NUserInfoDTO)request.getAttribute("pDTO");

if(NDTO == null){
	NDTO = new NUserInfoDTO();
}



%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입을 축하드립니다.</title>
<script type="text/javascript">
	alert("<%=msg%>");
</script>
</head>
<body>
<%=CmmUtil.nvl(NDTO.getUser_name()) %>님의 회원가입을 축하드립니다.

</body>
</html>
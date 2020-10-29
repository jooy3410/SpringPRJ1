<%@page import="java.util.HashSet"%>
<%@page import="poly.dto.RestDTO"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/* Controller로부터 넘겨받은 rList 선언 */
	List<RestDTO> rList = (List<RestDTO>) request.getAttribute("rList");
	//HashSet<RestDTO> rList= (HashSet<RestDTO>) request.getAttribute("rList");
	int i=1;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rest 목록</title>
	
</head>
<body>
	
	
	<div style="margin:auto;width:800px;">
	<table border="1" style="width:100%;">
		<thead>
		<tr>
			<td>번호</td>
			<td>상호명</td>
			<td>주소</td>
		</tr>
		</thead>
		<tbody>
		<% for (RestDTO eDTO : rList) { %>
		<tr>
			<td><%=i++ %></td>
			<td><%=nvl(eDTO.getBizplc_nm())%></td>
			<td><%=nvl(eDTO.getRefine_lotno_addr())%></td>
		</tr>
		<%} %>
		</tbody>
	</table>
	
	</div>
</body>
</html>
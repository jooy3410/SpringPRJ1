<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	
	//로그인을 위한 입력정보와 유효성 체크하기
	function doLoginSafeUserCheck(f){
		
		if(f.user_id.value==""){
			alert("아이디를 입력하세요.");
			f.user_id.focus();
			return false;
		}
		
		if(f.password.value==""){
			alert("비밀번호를 입력하세요.");
			f.password.focus();
			return false;
		}
	}
</script>


</head>
<body>
  <h1>홈페이지 첫 화면</h1>
   <br><br>
    
   
     
     <button type="button" onclick="location.href='/safe/choice.do'">회원가입</button>
      
      
      <br><br>
      

      	<form name="f" method="post" action="/safe/getUserSafeLoginCheck.do" onsubmit="return doLoginSafeUserCheck(this);">
	<div>
		아이디
		<input type="text" name="user_id" value="아이디" style="width:150px"/>
		비밀번호
		<input type="password" name="password" style="width:150px"/>
	</div>

	<input type="submit" value="로그인">
	</form>
   

</body>
</html>
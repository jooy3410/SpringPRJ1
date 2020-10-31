<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 회원가입화면</title>

<script type="text/javascript">
	// 회원가입 유효성 체크
	function doRegUserCheck(f){
		
		if(f.user_id.value==""){
			alert("아이디를 입력하세요.");
			f.user_id.focus();
			return false;
		}
		
		if(f.user_name.value==""){
			alert("이름을 입력하세요.");
			f.user_name.focus();
			return false;
		}
		
		if(f.password.value==""){
			alert("비밀번호를 입력하세요.");
			f.password.focus();
			return false;
		}
		
		
		if(f.password2.value==""){
			alert("비밀번호확인을 입력하세요.");
			f.password2.focus();
			return false;
		}
		
		
		if(f.email.value==""){
			alert("이메일을 입력하세요.");
			f.email.focus();
			return false;
		}
		
		if(f.addr1.value==""){
			alert("주소를 입력하세요.");
			f.addr1.focus();
			return false;
		}
		
		if(f.addr2.value==""){
			alert("상세주소를 입력하세요.");
			f.addr2.focus();
			return false;
		}
		
		if(f.user_name.value==""){
			alert("상호명을 입력하세요.");
			f.user_name.focus();
			return false;
		}
		
		if(f.user_name.value==""){
			alert("사업자번호을 입력하세요.");
			f.user_name.focus();
			return false;
		}

		

		
		if(f.pnumber.value==""){
			alert("핸드폰 번호를 입력하세요.");
			f.user_id.focus();
			return false;
		}
		

		
	}
</script>

	
	
	
</head>
<body>
 <h1>사업자 회원가입 화면</h1>
  
  <br><br>
  
   <form name="f" action="/safe/insertSafeUserInfo.do" method="post" onsubmit="return doRegUserCheck(this);">
   <!-- 사용자가 입력하는 입력 양식 -->
    <fieldset>
            <legend>필수정보</legend>
            
            <br>
            
            아이디* : <input type="text" name="user_id" size="20" maxlength="20" autofocus><br><br>
            
           
            이름* : <input type="text" name="user_name"><br><br>
          
                        
            비밀번호* : <input type="password" name="password" size="20" maxlength="10"><br><br>
    
            비밀번호 확인* : <input type="password" name="password2" size="20"><br><br>
            
	   
	  email*<input type="email"><br><br>
	  
	  
	             <fieldset>
            주소* : <input type="text" name="addr1">
           <br><br>
            주소상세 : <input type="text" name="addr2">
            <br>
            </fieldset> 
            <br>
            
            
            상호명* : <input type="text" name="storename"><br><br>
            
            
            사업자번호* : <input type="text" name="bnumber"><br><br> 
            
           
            핸드폰번호* : <input type="text" name="pnumber"><br><br>
       
            
            
            

	
	 
      
    
            <br>
       </fieldset>
        <input type="submit" value="제출하기">
        <input type="reset" value="다시하기">
        
        </form>

</body>
</html>
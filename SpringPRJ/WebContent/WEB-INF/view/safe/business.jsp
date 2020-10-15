<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 회원가입화면</title>
</head>
<body>
 <h1>사업자 회원가입 화면</h1>
  
  <br><br>
  
   <form name="login" action="/safe/main.do" method="post">
   <!-- 사용자가 입력하는 입력 양식 -->
    <fieldset>
            <legend>필수정보</legend>
            
            <br>
            
            아이디* : <input type="text" name="id" size="20" maxlength="10" autofocus><br><br>
            
            비밀번호* : <input type="password" name="c_pwd" size="20" maxlength="10"><br><br>
    
            비밀번호 확인* : <input type="password" name="c_pwd" size="20"><br><br>
            
            이름* : <input type="text" name="name"><br><br>
            
            상호명* : <input type="text" name="businessname"><br><br>
            
            사업자번호* : <input type="text" name="businessnumber"><br><br>
           <fieldset>
            주소* : <input type="text" name="addr1">
           <br><br>
            주소상세 : <input type="text" name="addr2">
            <br>
            </fieldset> 
            <br>
            
            
            휴대전화* : <select>
                     <option value="sk">SKT</option>
                     <option value="lg">LG</option>
                     <option value="kt">KT</option>
                     <option value="알뜰폰">알뜰폰</option>
                    </select><input type="text" name="cellphone" size="11" maxlength="11"><br><br>
                        
            email*<input type="email"><br><br>
    
            <br>
       </fieldset>
        <input type="submit" value="제출하기">
        <input type="reset" value="다시하기">
        
        </form>

</body>
</html>
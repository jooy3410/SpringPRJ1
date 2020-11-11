<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="../fonts1/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="../css1/style.css">
</head>
<body>

    <div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        <h2 class="form-title">로그인</h2>
                        
                        <div class="form-group">
                            <input type="text" class="form-input" name="user_id" id="user_id" placeholder="아이디"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-input" name="password" id="password" placeholder="비밀번호"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
                        
                        
                        <div class="form-group">
                            <input type="submit" name="submit" id="submit" class="form-submit" value="LOGIN"/>
                        </div>
                    </form>
                    <p class="loginhere">
                        
                        <a href="#" class="loginhere-link">회원가입
                         
                        <a href="#" class="loginhere-link">비밀번호 찾기
                    </p>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="../vendor1/jquery/jquery.min.js"></script>
    <script src="../js1/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
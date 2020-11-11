<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts1/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css1/style.css">
</head>
<body>

    <div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        <h2 class="form-title">회원가입</h2>
                        
                        <div class="form-group">
                            <input type="text" class="form-input" name="user_id" id="user_id" placeholder="아이디"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="text" class="form-input" name="password" id="password" placeholder="비밀번호"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
                        
                           
                        <div class="form-group">
                            <input type="password" class="form-input" name="re_password" id="re_password" placeholder="비밀번호 확인"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="text" class="form-input" name="name" id="name" placeholder="이름"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="text" class="form-input" name="stor" id="stor" placeholder="상호명"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="text" class="form-input" name="number" id="number" placeholder="사업자번호"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="text" class="form-input" name="addr1" id="addr1" placeholder="주소"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="text" class="form-input" name="addr2" id="addr2" placeholder="상세주소"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="text" class="form-input" name="pnum" id="pnum" placeholder="휴대전화"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="email" class="form-input" name="email" id="email" placeholder="Email"/>
                        </div>
                        
                        
                           
                        <div class="form-group">
                            <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                            <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                        </div>
                        
                           
                        <div class="form-group">
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Sign up"/>
                        </div>
                    </form>
                    
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="vendor1/jquery/jquery.min.js"></script>
    <script src="js1/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
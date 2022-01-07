<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="zh">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人事管理系统</title>
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />

<!-- 标题图标 -->
<link href="${ctx}/public/logo.ico" rel="shortcut icon" type="image/x-icon" />

<link href="${ctx}/js/metronic/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/js/metronic/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/js/metronic/css/login.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/js/metronic/css/jquery.slider.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/ajax_js/register_check.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.slider.js"></script>

<!-- 用户注册错误提示，获取id对象，显示5秒钟后被隐藏，用none值隐藏id元素-->
<script type="text/javascript">
setTimeout(function(){document.getElementById("regist_message").style.display="none";},5000);
</script>

<!-- 禁止鼠标右击以及文本选择 -->
<script type="text/javascript">
document.onselectstart=function(){return false;};
document.oncontextmenu=function(){return false;}
</script>

<script type="text/javascript">
//调用testSlider，实现轮播图切换
window.onload = function(){
	var currentIndex = 0;
	$('.testSlider').slider({
		//圆点显示
		originType:'circle'
	});
}
</script>

<!-- 设置内容水平和垂直上的滚动条 -->
<style type="text/css">
body{
       overflow-x:scroll;
       overflow-y:scroll;
    }
</style>

<!-- hover选择器，鼠标指针浮动在字体上面改变字体颜色 -->   
<style type="text/css">
.li a{
	color:white;
}
.li a:hover{
	color:red;
}
</style>
</head>

<body>

<!-- 顶部开始 -->
<div style="width:1349px;height:50px;background-color:rgb(19,19,70);">
      <ul style="margin-left:60px;padding-top:10px">
        <li class="li" style="display: inline-block;"><h1><a href="loginForm.html" style="font-family:SimHei;font-size:32px;">人 事 管 理 系 统</a></h1></li>
        <li style="display: inline-block; margin-left: 80px;"><a  style="font-size:24px;color:white;" >用户注册</a></li>
        <li class="li" style="display: inline-block; margin-left: 80px;"><a href="loginForm.html" style="font-size:24px;;">首页</a></li>
        <li class="li" style="display: inline-block; margin-left: 80px;"><a href="#" onclick="location.href='./recruitment';"style="font-size:24px;; ">招聘页面</a></li>
        <li class="li" style="display: inline-block; margin-left: 80px;"><a href="javascript:alert('本系统只提供公司管理员和相关人员登录系统！！！如果要注册此系统，请联系部门管理员下发注册码 ！！！');"	  
        style="font-size:24px;">帮助</a></li>
        <li style="display: inline-block; margin-left: 80px;"><p  style="font-size:24px;color:white;">客服电话：400800800</p></li>
      </ul>    
</div>
<!-- 顶部结束 -->

<!-- 中间内容 -->
	<!-- div标签对象浮动居左靠左 -->
    <div id="main" style="width:1349px;height:628px;float:left;">
      <!-- 幻灯片 -->    
      <div class="container" style="background-color:white;width: 1349px;height:628px;float:left;">
	   <!-- 轮播图jquery.slider.css,jquery.slider.js --> 
       <div class="testSlider"></div>
         
		 <div id="register"  class="register" style="height:460px;margin-top:50px;margin-left:200px;opacity:0.9;">
            <div class="title">
                <span style="color: rgb(32,35,64);">用户注册</span>
                 <div style="height: 10px;margin-top: 15px;">
                     <input id="register_check_message" disabled="disabled" size="50" value=""style= "background-color:transparent;border:0;color: red"/> 
                 </div>
            </div>
            
            <!-- 注册输入表单 -->  
            <form  method="post" action="register" name="check-form" id="toLoginForm">   
                <div class="default" style="margin-top:20px;">
                    <input 	data-form="uname" type="text" placeholder="请输入登录名  登录名不小于5个字母"  id="loginname" name="loginname" value="${user.loginname}" onchange="check_register_loginname()"/>
                </div>
                <div style="height: 10px;margin-top: -10px;">
                     <input id="loginname_message" disabled="disabled"  value=""style= "background-color:transparent;border:0;color: red"/>          
                </div>
                <div class="default">
                    <input 	data-form="uname" type="text" placeholder="请输入用户名  用户名是你的姓名"  id="username" name="username" value="${user.username}" onchange="check_register_username()"/>
                </div>
                 <div style="height: 10px;margin-top: -10px;">
                     <input id="username_message" disabled="disabled"  value=""style= "background-color:transparent;border:0;color: red"/>          
                </div>
                <div class="default">
                    <input 	data-form="uname" type="text" placeholder="请输入邮箱  "  id="email" name="email" value="${user.email}" onchange="check_register_email()"/>
                </div>
                 <div style="height: 10px;margin-top: -10px;">
                     <input id="email_message" disabled="disabled"  value=""style= "background-color:transparent;border:0;color: red"/>          
                </div>
                <div class="default">
                    <input id="password" name="password"
					value="${password}" data-form="upwd" type="password" placeholder="请输入密码   密码 6-12位数字和字母组合" onchange="check_register_password()"/>
                </div>
                <div style="height: 10px;margin-top: -10px;">
                    <input id="password_message" disabled="disabled"  value=""style= "background-color:transparent;border:0;color: red"/>          
                </div>
                
                <div class="default">
                    <input id="repassword" name="repassword"
					value="${repassword}" data-form="upwd" type="password" placeholder="请确认密码 " onchange="check_register_repassword()"/>
                </div>
                 <div style="height: 10px;margin-top: -10px;">
                     <input id="repassword_message" disabled="disabled"  value=""style= "background-color:transparent;border:0;color: red"/>          
                </div>
                <div class="submit" style="margin-top: 16px;"> 
                    <button id="login-submit-btn" class="s_hover" type="button" onclick="checkMessage()">注册</button>
                </div>
            </form> 
            <div id="regist_message" style="color: red;margin-left:50px;margin-top:-30px;">${message}</div>
        </div>
       </div>
      </div>

<!-- 底部开始 -->
    <div style="width:1349px;height:50px;background-color:rgb(19,19,70);margin-top:628px;">
    	<div align="center" style="padding-top:15px;color:#F0F8FF;">Copyright ©2021 人事管理系统</div>
    </div>
<!-- 底部结束 -->
   
</body>
</html>
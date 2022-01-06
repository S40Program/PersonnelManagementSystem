<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="zh">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>人事管理系统</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
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
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.slider.js"></script>
<script type="text/javascript" src="${ctx}/ajax_js/findPassword.js"></script>

<!-- 找回密码错误提示，获取id对象，显示5秒钟后被隐藏，用none值隐藏id元素-->
<script type="text/javascript">
		setTimeout(function(){document.getElementById("password_message").style.display="none";},5000);
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
        <li style="display: inline-block; margin-left: 80px;"><a  style="font-size:24px;color:white;" >重置密码</a></li>
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
      <div class="container" style="background-color:white;width:1349px;height:628px;">
	   <!-- 轮播图jquery.slider.css,jquery.slider.js -->     
       <div class="testSlider"></div>
       
        <!-- 重置密码输入表单 -->
		<div class="register" style="height:350px;margin-top:100px;margin-left:200px; opacity:0.9;" >
            <div class="title">
                <span>重置密码</span>
                <div style="height: 10px;margin-top: 15px;">
                    <input id="updata_message" disabled="disabled" size="50px;" value=""style= "background-color:transparent;border:0;color: red"/> 
                </div>
            </div>
            <form action="rePassword?loginname=${user.loginname}" method="post" id="rePasswordForm"> 
            <div style="height: 10px"></div>
             <div class="default">
                    <input id="loginname" name="loginname"   data-form="upwd" type="text" placeholder="请输入您的登录名 "  value="${user.loginname}" disabled="disabled"/>
             </div>
             
             <div class="default" style="margin-top: 16px;">
                    <input id="password" name="password" data-form="uname" type="password" placeholder="新密码  6-16位数字和字母的组合" onchange="check_register_password1()" value=""/>
             </div>
             
             <div style="height: 10px;margin-top: -10px;">
                    <input id="psd_message" disabled="disabled"  value="" style= "background-color:transparent;border:0;color: red"/>          
             </div>
             
             <div class="default" style="margin-top: 16px;">
                    <input id="repassword" name="repassword" data-form="uname" type="password" placeholder="请再次输入你的新密码" onchange="check_register_repassword1()" value=""/>
             </div>
             <div style="height: 10px;margin-top: -10px;">
                    <input id="repassword_message" disabled="disabled"  value=""style= "background-color:transparent;border:0;color: red"/>          
             </div>
             <div style="height: 10px"></div>
             <div class="submit">
                    <button class="s_hover" type="button" onclick="toUpdata()">重置密码</button>
             </div>
             <div style="margin-top: -35px;">
             <div style="height: 10px;margin-top: -10px;">
                    <input id="find_message" disabled="disabled" size="50px;" value=""style= "background-color:transparent;border:0;color: red"/> 
             </div>
            </div>
            </form>
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
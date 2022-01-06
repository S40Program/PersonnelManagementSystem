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


<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/ajax_js/check.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.slider.js"></script>

<!-- 注册码错误提示，获取id对象，显示5秒钟后被隐藏，用none值隐藏id元素-->
<script type="text/javascript">
setTimeout(function(){document.getElementById("code_message").style.display="none";},5000);
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

<!-- 禁止鼠标右击以及文本选择 -->
<script type="text/javascript">
document.onselectstart=function(){return false;};
document.oncontextmenu=function(){return false;}
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
<div style="width: 1349px;height:50px; background-color:rgb(19,19,70)">
      <ul style="margin-left: 60px;padding-top:10px">
        <li class="li" style="display: inline-block;"><h1><a href="loginForm.html" style="font-family:SimHei;font-size:32px;">人 事 管 理 系 统</a></h1></li>
        <li style="display: inline-block; margin-left: 80px;"><a  style="font-size:24px;color:white;">注册码</a></li>
        <li class="li" style="display: inline-block; margin-left: 80px;"><a href="loginForm.html" style="font-size:24px;">首页</a></li>
        <li class="li" style="display: inline-block; margin-left: 80px;"><a href="#" onclick="location.href='./recruitment';"style="font-size:24px;">招聘页面</a></li>
        <li class="li" style="display: inline-block; margin-left: 80px;"><a href="javascript:alert('本系统只提供管理员和公司管理人员登录系统！！！如果需要注册此系统，请联系管理员下发注册码 ！！！');"	  
        style="font-size:24px;">帮助</a></li>
        <li style="display: inline-block; margin-left: 80px;"><p  style="font-size:24px;color:white;">客服电话：400800800</p></li>
      </ul>    
</div>
<!-- 顶部结束 -->

<!-- 中间内容 -->
	<!-- div标签对象浮动居左靠左 --> 
    <div id="main" style="width:1349px;height:628px;float:left;">
          
      <div class="container" style="background-color:white;width:1349px;height:628px;"> 
       <!-- 轮播图jquery.slider.css,jquery.slider.js -->    
       <div class="testSlider"></div>
       
         <!-- 注册码输入表单 -->
		 <div id="register"  class="register" style="height:300px;margin-top:100px;margin-left:200px;opacity:0.9;">
            <div class="title">
            </div>
            <form  method="post" action="toregistCode" name="check-form" id="toLoginForm"> 
                <div style="color: red">注册码由管理员下发，有问题请联系管理员！</div>
                <div class="default" style="margin-top:20px;">
                    <input 	data-form="uname" type="text" placeholder="请输入注册码"  id="registCode" name="registCode" value="${user.registCode}"/>
                </div>
                <div class="submit" style="margin-top: 20px;"> 
                    <button  id="login-submit-btn" class="s_hover" type="button" onclick="toregistCode()">去注册</button> 
                </div>
            </form>
            
            <!-- 注册码提示 --> 
            <input id="span_id" disabled="disabled"  value="" style= "margin-left:90px;background-color:transparent;border:0;color:red" size="45"/>
               
            <div id="code_message" style="color:red;margin-left:90px;margin-top:-15px;">${message}</div>
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
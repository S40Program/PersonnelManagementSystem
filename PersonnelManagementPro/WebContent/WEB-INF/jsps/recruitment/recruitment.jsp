<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>人事管理系统</title>
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link href="${ctx}/public/logo.ico" rel="shortcut icon"  type="image/x-icon" />
<link href="${ctx}/js/metronic/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/js/metronic/css/base.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/campusnew3/css/base.css"/>
<link rel="stylesheet" href="${ctx}/campusnew3/css/flexslider.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/campusnew3/css/home.css"/>
	 
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
	
<script src="${ctx}/campus/js/libs/jquery.js"></script>
<script src="${ctx}/campus/js/footer.js"></script>
<script src="${ctx}/campusnew/js/libs/jquery.js"></script>
<script src="${ctx}/campusnew/js/libs/jquery.flexslider-min.js"></script>
<script src="${ctx}/campusnew/js/main.js"></script>
<script src="${ctx}/campus/js/footer.js"></script>
<script src="${ctx}/campus/js/home.js"></script>

<!-- hover选择器，鼠标指针浮动在字体上面改变字体颜色 -->   
<style type="text/css">
.li a{
	color:white;
}
.li a:hover{
	color:red;
	text-decoration:none;
}
</style>
</head>
<body style="background-color:rgb(73,74,95);">

<!-- 顶部开始 -->
<div style="width:1366px;height:50px;background-color:rgb(19,19,70);">
      <ul style="margin-left:60px;padding-top:10px">
        <li class="li" style="display: inline-block;"><h1><a href="loginForm.html" style="font-family:SimHei;font-size:32px;">人 事 管 理 系 统</a></h1></li>
        <li style="display: inline-block; margin-left: 80px;"><p  style="font-size:24px;color:white;" >简历投递</p></li>
        <li class="li" style="display: inline-block; margin-left: 80px;"><a href="loginForm.html" style="font-size:24px;;">首页</a></li>
        <li class="li" style="display: inline-block; margin-left: 80px;"><a href="#" onclick="location.href='./recruitment';"style="font-size:24px;; ">招聘页面</a></li>
        <li class="li" style="display: inline-block; margin-left: 80px;"><a href="javascript:alert('本系统只提供公司管理员和相关人员登录系统！！！如果要注册此系统，请联系部门管理员下发注册码 ！！！');"	  
        style="font-size:24px;">帮助</a></li>
        <li style="display: inline-block; margin-left: 80px;"><p  style="font-size:24px;color:white;">客服电话：400800800</p></li>
      </ul>    
</div>
<!-- 顶部结束 -->

<!-- 中间内容 --> 
<div style="height:56px;background-color:rgb(176,196,222);width:1366px;"></div>
<div id="J-index-wrap" class="index-wrap animation" style="background-color:rgb(176,196,222);width:1366px;">
	<div class="wrap" style="background-color:rgb(176,196,222);width:1366x;">
		<div class="main-burn-content" style="background-color:rgb(176,196,222);width:1000px;">
			<div class="index_cont">
                        	<div class="applystatus" style="display:none" >
            					<h3 class="applystatus_title">申请状态</h3>
				<input type="hidden" id="jobObjFlag" value="false" />
									<a href="/web/resume/resume_index" class="index_cont_btn applystatus_myresume">我的简历</a>
				</div>
		        	<div class="postlist" style="display:block" >
        					<div class="postlist_cont">
						<div class="postlist_item">
							<div class="postlist_sub postlist_xproj">
								<div class="postlist_sub_valign">
									<div class="valign_wrap">
										<div class="postlist_sub_icon"></div>
										<div class="postlist_sub_name">人才项目</div>
									</div>
								</div>
								<ul class="postlist_sub_wrap">	
									<li class="postlist_sub_item"><a onclick="" href="#">管培生</a></li>    
								</ul>
							</div>
						</div>
						<div class="postlist_item">
							<div class="postlist_sub postlist_xtech">
								<div class="postlist_sub_valign">
									<div class="valign_wrap">
										<div class="postlist_sub_icon"></div>
										<div class="postlist_sub_name">技术方向</div>
									</div>
								</div>
								<ul class="postlist_sub_wrap">
									<li class="postlist_sub_item"><a onclick="" href="#">软件开发类</a></li>
									<li class="postlist_sub_item"><a onclick="" href="#">数据与算法类</a></li>
									<li class="postlist_sub_item"><a onclick="" href="#">测试类</a></li>
								</ul>
							</div>
							<div class="postlist_sub postlist_xop">
								<div class="postlist_sub_valign">
									<div class="valign_wrap">
										<div class="postlist_sub_icon"></div>
										<div class="postlist_sub_name">客户服务</div>
									</div>
								</div>
								<ul class="postlist_sub_wrap">
									<li class="postlist_sub_item"><a onclick="" href="#">客户服务类</a></li>
								</ul>
							</div>
						</div>
						<div class="postlist_item">
							<div class="postlist_sub postlist_xux">
								<div class="postlist_sub_valign">
									<div class="valign_wrap">
										<div class="postlist_sub_icon"></div>
										<div class="postlist_sub_name">设计方向</div>
									</div>
								</div>
								<ul class="postlist_sub_wrap">
									<li class="postlist_sub_item"><a onclick="" href="#">设计类</a></li>
								</ul>
							</div>
							<div class="postlist_sub postlist_xmarket">
								<div class="postlist_sub_valign">
									<div class="valign_wrap">
										<div class="postlist_sub_icon"></div>
										<div class="postlist_sub_name">金融方向</div>
									</div>
								</div>
								<ul class="postlist_sub_wrap">
									<li class="postlist_sub_item"><a onclick="" href="#">金融产品类</a></li>
									<li class="postlist_sub_item"><a onclick="" href="#">保险类</a></li>
								</ul>
							</div>
						</div>
						<div class="postlist_item">
							<div class="postlist_sub postlist_xpm">
								<div class="postlist_sub_valign">
									<div class="valign_wrap">
										<div class="postlist_sub_icon"></div>
										<div class="postlist_sub_name">产品方向</div>
									</div>
								</div>
								<ul class="postlist_sub_wrap">
									<li class="postlist_sub_item"><a onclick="" href="#">项目类</a></li>
									<li class="postlist_sub_item"><a onclick="" href="#">产品运营类</a></li>
								</ul>
							</div>
							<div class="postlist_sub postlist_xfn">
								<div class="postlist_sub_valign">
									<div class="valign_wrap">
										<div class="postlist_sub_icon"></div>
										<div class="postlist_sub_name">市场与职能方向</div>
									</div>
								</div>
								<ul class="postlist_sub_wrap">
									<li class="postlist_sub_item" style="height:25px;line-height: 15px"><a onclick="" href="#">人力资源类</a></li>
									<li class="postlist_sub_item" style="height:25px;line-height: 15px"><a onclick="" href="#">行政类</a></li>
								</ul>
							</div>
						</div>
					</div>
					<a href="#" onclick="location.href='./recruitmentlist';" class="index_cont_btn postlist_sendresume"  style="margin-left: 390px;">投递简历</a>
				</div> 

			</div>
		</div>
	</div>
	</div>
<div style="height:56px;width:1366px;background-color:rgb(176,196,222);"></div>
<!-- 底部开始 -->
    <div style="width:1366px;height:50px;background-color:rgb(19,19,70);">
    	<div align="center" style="padding-top:15px;color:#F0F8FF;">Copyright ©2021 人事管理系统</div>
    </div>
<!-- 底部结束 -->
</body>
</html>
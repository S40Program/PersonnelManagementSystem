<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>人事管理系统</title>
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta http-equiv="Content-Type" content="text/html; charset=GBK">

<link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
<link href="${ctx}/js/metronic/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/js/metronic/css/base.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/public/css/font.css">
<link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
<link rel="stylesheet" href="${ctx}/campusnew3/css/base.css"/>
<link rel="stylesheet" href="${ctx}/campusnew3/css/flexslider.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/campusnew3/css/home.css"/>
	 
<script src="${ctx}/campus/js/libs/jquery.js"></script>
<script src="${ctx}/campus/js/footer.js"></script>
<script src="${ctx}/campusnew/js/libs/jquery.js"></script>
<script src="${ctx}/campusnew/js/libs/jquery.flexslider-min.js"></script>
<script src="${ctx}/campusnew/js/main.js"></script>
<script src="${ctx}/campus/js/footer.js"></script>
<script src="${ctx}/campus/js/home.js"></script>

<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>

<script type="text/javascript">
    function openVideo(id) {
    	var $id=$(id).parent();
        var id1 = $id.children("input").eq(0).val();  //  关键代码，获取id
        var index = layer.open({
            type: 2,
            content: '${ctx}/resume/add?id='+id1,  //  点击某条招聘信息，将发条招聘信息的id传给后台
            area: ['600px', '600px'],
            offset:'t',
            maxmin: true,
            end: function () {
            	 
            }
        });
    } 
</script>

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

<body style="background-color:rgb(176,196,222);">
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
	<div class="wrap" style="background-color:rgb(176,196,222);width:1366px;padding-left:200px">
		<div class="main-burn-content" style="background-color:rgb(176,196,222);width:1366px;">
		
			
     <div class="x-body" style="margin-top: -30px;"> 
      <table class="layui-table" style="margin-left: -200px;">
        <thead>
          <tr>
              <th>岗位类别</th>
			  <th>岗位信息</th>
			  <th>在招人数</th>
			  <th>招聘状态</th>
			  <th>截止日期</th>
			  <th>我要投递</th>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.recruitment_list}" var="recruitment" varStatus="stat">
     <tr>
            
             <td>${recruitment.jobtype.name}</td>
             <td>${recruitment.content}</td>
             <td>${recruitment.peoplenum}</td>
			 <td>
				<c:choose>
					 <c:when test="${recruitment.status.id == 1}"><font style="color: rgb(0,150,136);">${recruitment.status.name}</font></c:when>
					 <c:otherwise><font color="red">${recruitment.status.name}</font></c:otherwise>
				</c:choose>
			</td>	  
			 <td>${recruitment.enddate}</td>
            <td class="td-manage" style="width: 30px;">
            
              <c:choose>
					 <c:when test="${recruitment.status.id == 1}"><input id="trueid" type="hidden" value="${recruitment.id}"/><button class="layui-btn layui-btn-xs" style="margin-left: 5px;" onclick="openVideo(this)">立即投递</button>  </c:when>
					 <c:otherwise><input id="trueid" type="hidden" value="${recruitment.id}"/><button class="layui-btn layui-btn-xs" style="margin-left: 5px;" >通道关闭</button></c:otherwise>
				</c:choose>
              
            
            </td>
          </tr>
         </c:forEach>
        </tbody>
      
      </table>
	  	 
	 <div style="margin-left: 500px; display: none;" id="count2">
                <p style="color: rgb(0,97,222)">共查询到<font color="red">${count}</font>条数据</p>
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
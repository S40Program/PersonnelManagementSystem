<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>

<html lang="zh">

    <head>
        <meta charset="UTF-8">
        <title>欢迎页面</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        
        <!-- 标题图标 -->
        <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
        
        <link rel="stylesheet" href="${ctx}/public/css/font.css"> 
        <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
        <!-- 导入jquery插件 -->
		<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
		<script type="text/javascript" src="${ctx}/js/fkjava_timer.js"></script>
		<script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>

        <script type="text/javascript">
         $(function(){
             $("#id1").css("display","none");
             $("#id2").css("display","none");
             var n1 ="admin";//超级管理员
             var n2 ="manager";//管理员
             var n = "${sessionScope.user_session.loginname}";//获取用户名
             if(n1==n||n2==n) {
                 $(".sign").css("display", "none");
                 $(".offwork").css("display", "none");
             }
             //上班时候的是否已经打卡的状态
             if (${sessionScope.sgin_status == 2} ){
                 $(".sign").css("display","none");
                 $("#id1").css("display","black");
             }
             //下班时候的是否已经打卡的状态
             if (${sessionScope.offwork_status == 2} ){
                 $(".offwork").css("display","none");
                 $("#id2").css("display","black");
             }
    	    $("#nowTime").runTimer();
        });
        </script>
        <!-- 禁止鼠标右击以及文本选择 -->
		<script type="text/javascript">
			document.onselectstart=function(){return false;};
			document.oncontextmenu=function(){return false;}
		</script>
        <script>
          var _hmt = _hmt || [];
          (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
            var s = document.getElementsByTagName("script")[0]; 
            s.parentNode.insertBefore(hm, s);
        })();
        </script>
    	<script>
        $(function(){
            $(".sign").click(function() {
                //使用ajax加载数据
                $.ajax({
                    method:'get',
                    url:'${ctx}/sign',
                    success:function(data){
                        if (data==1){
                            alert("签到成功");
                            $(".sign").css("display","none");
                            $("#id1").css("display","black");
                        } else {
                            alert("迟到，请到个人考勤填写迟到原因");
                            $(".sign").css("display","none");
                            $("#id1").css("display","black");
                        }

                    }
                });
            });
            $(".offwork").click(function() {

                if (${sessionScope.sgin_status != 2} ){
                    alert("你还没完成上班打卡");
                    return false;
                }
                //使用ajax加载数据
                $.ajax({
                    method:'get',
                    url:'${ctx}/offwork',
                    success:function(data){
                        if (data==1){
                            alert("早退，请到个人考勤填写早退原因");
                            $(".offwork").css("display","none");
                            $("#id2").css("display","black");
                        } else {
                            alert("下班打卡成功");
                            $(".offwork").css("display","none");
                            $("#id2").css("display","black");
                        }

                    }
                });
            });
        });
    	</script>


<!--员工信息完善消息提示 -->
<script type="text/javascript">

</script>

    </head>
    <body>
    <div class="x-body layui-anim layui-anim-up">
        <blockquote class="layui-elem-quote">欢迎
        	<span class="x-red">${sessionScope.user_session.username}</span> 登录系统！！！当前时间 ：<span id="nowTime"></span>
            <button  style="margin-left: 300px;" type="button" class="layui-btn sign">上班打卡</button>
            <button  id="id1" hidden="hidden" style="margin-left: 300px;" type="button" class="layui-btn layui-btn-disabled">上班已打卡</button>
            <button  style="margin-left: 40px;" type="button" class="layui-btn offwork">下班打卡</button>
            <button  id="id2" hidden="hidden" style="margin-left: 40px;" type="button" class="layui-btn layui-btn-disabled">下班已打卡</button>
        </blockquote>

        <fieldset class="layui-elem-field">
            <legend>系统通知</legend>
            <div class="layui-field-box">
                <table class="layui-table" lay-skin="line">
                    <tbody>
                        <tr>
                            <td >
                                <a class="x-a" href="./index?token=${sessionScope.token}&lang=zh_CN" target="_blank">人事管理系统上线了！！！</a>
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>系统信息</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        
                        <tr>
                            <th>系统版本</th>
                            <td>2021.01.01</td></tr>
                        <tr>
                            <th>服务器地址</th>
                            <td>Localhost</td></tr>
                        <tr>
                            <th>操作系统</th>
                            <td>Windows 10</td></tr>
                        <tr>
                            <th>运行环境</th>
                            <td>Apache Tomcat 8.5(Win64) JDK1.8.0_131</td></tr>
                        <tr>
                            <th>MYSQL版本</th>
                            <td>5.7.21</td></tr>
                        <tr>
                            <th>上传附件限制</th>
                            <td>50 M</td></tr>
                        <tr>
                            <th>执行时间限制</th>
                            <td>30 S</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
      <blockquote class="layui-elem-quote layui-quote-nm">欢迎进入人事管理系统首页！！！</blockquote>
	</div>
    </body>
</html>
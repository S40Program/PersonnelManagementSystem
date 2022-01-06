<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>

<html lang="en">

<head>
<meta charset="UTF-8">
<title>人事管理系统</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />

<!-- 标题图标 -->
<link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />

<link rel="stylesheet" href="${ctx}/public/css/font.css"> 
<link rel="stylesheet" href="${ctx}/public/css/xadmin.css">

<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>

<!-- 导入jquery插件 -->
<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${ctx}/js/fkjava_timer.js"></script>

<script type="text/javascript">
	//这个事件不能一直点击
		  $(function() { // 页面装载执行
				$("#bt").click(function(){ // 为ID为bt的标签绑定click事件
					$.ajax({
					    method:'get',
					    url:'${ctx}/toCreateCode',
					    dataType:'json',
					    success:function(data){
					       alert("您只有十次获取注册码的机会，请务必复制此注册码   "+data.code);
					    }
					}); 
				});
			});
</script>

<!-- 禁止鼠标右击以及文本选择 -->
<script type="text/javascript">
document.onselectstart=function(){return false;};
document.oncontextmenu=function(){return false;}
</script>
	
<script type="text/javascript">
    /** 文档加载完成后立即执行的方法 */
    // var weeks = new Array(); 
    $(function(){
    	$("#nowTime").runTimer();
    	var calendar = showCal(); 
    	$("#currentDate").text("公历" + calendar);
    	var n1 ="admin";
    	var n2 = "manager";
    	var n = "${sessionScope.user_session.loginname}";
    	if(n1==n||n2==n){
    		$("#uservisit").css("display", "block"); //显示
    		$("#train").css("display", "none"); //隐藏
    		$("#admintrain").css("display", "block");
    		$("#personal").css("display", "none");
    		$("#pcheckwork").css("display", "none");
    		$("#psalary").css("display", "none");
    		$("#pcontract").css("display", "none");
    		$("#pinsurance").css("display", "none");
    		$("#checkwork").css("display", "block");
    		$("#salary").css("display", "block");
    		$("#contract").css("display", "block");
    		$("#insurance").css("display", "block");
    		$("#dept").css("display", "block"); 
    		$("#job").css("display", "block"); 
    		$("#employee").css("display", "block"); 
    		$("#peremployee").css("display", "none"); 
    		$("#notice").css("display", "block"); 
    		$("#document").css("display", "block"); 
    		$("#resume").css("display", "block");
    		$("#bt").css("display", "block"); 	
    	}else{
    		$("#train").css("display", "block"); 
    		$("#admintrain").css("display", "none");
    		$("#uservisit").css("display", "none"); 
    		$("#personal").css("display", "block"); 
    		$("#pcheckwork").css("display", "block");
    		$("#psalary").css("display", "block");
    		$("#pcontract").css("display", "block");
    		$("#pinsurance").css("display", "block");
    		$("#checkwork").css("display", "none");
    		$("#salary").css("display", "none");
    		$("#contract").css("display", "none");
    		$("#insurance").css("display", "none");
    		$("#dept").css("display", "none"); 
    		$("#job").css("display", "none"); 
    		$("#employee").css("display", "none"); 
    		$("#peremployee").css("display", "block"); 
    		$("#resume").css("display", "none");
    		$("#notice").css("display", "none"); 
    		$("#document").css("display", "none");
    		$("#bt").css("display", "none");
    	}; 	
    	$("#exit").click(function(){
         layer.confirm("确定要退出当前用户吗？",function(index){
        	 parent.location = "${ctx}/exit"; 
         });
    	});
    	$("#main").click(function(){	
    		parent.location = "${ctx}/login?loginname=${sessionScope.user_session.loginname}&password=${sessionScope.user_session.password}";
    	})
	});

    
</script>
</head>

<body>
<!-- 顶部开始，修饰类xadmin.css，layui.css-->
    <div class="container">
        <div class="logo">
        	<a href="./index?token=${sessionScope.token}&lang=zh_CN" style="width:200px;" >人 事 管 理 系 统</a></div>
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
        </div>
        <div class="left_open">
            <a href="./index?token=${sessionScope.token}&lang=zh_CN" style="margin-left: 80px;">系统首页</a>
        </div>
        <div class="left_open">
           	<p  style="color:white;margin-left:80px;">当前时间 ：<span id="nowTime"></span></p>
        </div>
        <div class="left_open">
           	<p  style="color:white;margin-left:10px;"><span id="currentDate"></span></p>
        </div>
        <div class="left_open">
           <a id="bt" href="#" style="margin-left: 80px;">获取注册码</a>   
        </div>
        <ul class="layui-nav right">
          <li class="layui-nav-item">
           <a href="javascript:;">${sessionScope.user_session.username}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a href="#" id="exit">注销退出</a></dd>
            </dl>
          </li>
        </ul> 
    </div>
<!-- 顶部结束 -->
    
    <!-- 中部开始 -->
    <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">     
            <!-- 招聘管理 -->
            <li id ="resume">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>招聘管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/recruitment/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>员工招聘</cite>
                        </a>
                    </li >
                    <!-- 新增详细信息，管理员，操作管理员工培训详细信息 -->
                    <li>
                        <a _href="${ctx}/resume/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>简历管理</cite>
                        </a>
                    </li >
                    
                </ul>
           </li>
 <!--************************************************************************************* -->             
           <!-- 考勤系统 -->  
           <li id="checkwork">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>考勤系统</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/checkwork/punchtime">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>打卡时间</cite>
                        </a>
                    </li >
                </ul>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/checkwork/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>考勤查询</cite>
                        </a>
                    </li >
                </ul>
          </li>  
 <!--用户请假功能  部门经理进行审批-->  
 		  <li>          
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/checkwork/adminleavelist">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>请假申请</cite>
                        </a>
                    </li >
                </ul>            
          </li>
          <!--个人考勤查看  -->
          <li id="pcheckwork">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>个人考勤</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/checkwork/punchlist">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看考勤</cite>
                        </a>
                    </li >
                </ul>
<!--用户请假功能  填写请假信息，做提交给数据库，为请假详情列表，-->
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/checkwork/leaveadd">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>我要请假</cite>
                        </a>
                    </li >
                </ul>

<!--我的请假直接通过id去查询请假详情列表，简单的查询功能，用户可以删除自己的请假信息   -->                
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/checkwork/leavelist?id=${sessionScope.user_session.id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>我的请假</cite>
                        </a>
                    </li >
               </ul>
          </li>
<!--************************************************************************************* -->        
          <!-- 公告管理 -->
          <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6ce;</i>
                    <cite>公告管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/notice/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>公告查询</cite>
                        </a>
                    </li >
                    <li id="notice">
                        <a _href="${ctx}/notice/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加公告</cite>
                        </a>
                    </li >
                    
                </ul>
          </li>
          <!--登录用户信息管理 查看修改 查看按id查找-->
          <li id="personal">
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>个人管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/user/pedit?id=${sessionScope.user_session.id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>个人用户信息</cite>
                        </a>
                    </li >
                   
                </ul>
          </li>
<!--************************************************************************************* -->  
          <!-- 晋升管理 -->   
          <li >
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>晋升管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/job/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看职位</cite>
                        </a>
                    </li >
                     <li id="job">
                        <a _href="${ctx}/job/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加职位</cite>
                        </a>
                    </li >
                </ul>
          </li>
<!--************************************************************************************* -->  
          <!-- 社保管理 -->             
  		  <li id="insurance">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>社保管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a  href="${ctx}/insurance/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>社保管理</cite>
                        </a>
                    </li >
                </ul>
            </li>  
            <!-- 个人社保信息查看，不提供修改 -->
            <li id="pinsurance">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>社保管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a  href="${ctx}/insurance/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看社保</cite>
                        </a>
                    </li>
                </ul>
            </li>
<!--************************************************************************************* -->  
          <!-- 合同管理 -->             
  		  <li id="contract">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>合同管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/contract/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>合同管理</cite>
                        </a>
                    </li >
                </ul>
            </li>  
            <!-- 个人合同信息查看，不提供修改 -->
            <li id="pcontract">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>个人合同</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/contract/pedit?id=${sessionScope.user_session.con_id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看合同</cite>
                        </a>
                    </li >
                </ul>
            </li>
<!--************************************************************************************* -->  
		  <!-- 工资管理 -->  
          <li id="salary">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>工资管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/salary/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>工资管理</cite>
                        </a>
                    </li >
                </ul>
          </li>  
          <!-- 个人工资管理，不提供工资修改 -->
          <li id="psalary">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>个人工资</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/salary/pedit?id=${sessionScope.user_session.sal_id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看工资</cite>
                        </a>
                    </li >
                </ul>
          </li>
<!--************************************************************************************* -->             
          <!-- 档案管理 -->  
          <!-- 管理员可以查看所有员工个人档案 -->         
          <li id="employee">
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>档案管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/employee/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>员工档案</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${ctx}/employee/association">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加员工</cite>
                        </a>
                    </li >
                </ul>
          </li>      
          <!-- 我的员工信息 -->
          <li id="peremployee">
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>个人档案</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <!-- 需要和登录信息表关联，获取到员工id -->
                    <!-- 第一次注册打开之后，这里是空内容，需要用户去完善个人信息  -->
                    <!-- 这里展示员工信息界面这个界面不允许修改 -->
                    <li >
                        <!-- 这里首先需要默认一个员工的id，因为第一次注册登录，没有员工信息，所以这个id如何设置 -->
                        <a _href="${ctx}/employee/plist?id=${sessionScope.user_session.emp_id}"><!--session中获取员工信息的id  -->
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>个人档案信息</cite>
                        </a>
                    </li >
                    <!--这个是用来添加的 后面不需要参数，直接跳转添加页面-->
                    <li>
                        <!-- 这里首先需要默认一个员工的id，因为第一次注册登录，没有员工信息，所以这个id如何设置 -->
                        <a _href="${ctx}/employee/padd?id=${sessionScope.user_session.emp_id}"><!--session中获取员工信息的id  -->
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>完善个人档案</cite>
                        </a>
                    </li >
                    <!--这个是用来修改的，需要参数，跳转修改界面-->
                    <li >
                        <!-- 这里首先需要默认一个员工的id，因为第一次注册登录，没有员工信息，所以这个id如何设置 -->
                        <a _href="${ctx}/employee/pedit?id=${sessionScope.user_session.emp_id}"><!--session中获取员工信息的id  -->
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>修改个人档案</cite>
                        </a>
                    </li >
                </ul>
          </li>
<!--************************************************************************************* -->  
		  <!-- 组织架构管理 -->  
          <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>组织架构管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/dept/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看部门</cite>
                        </a>
                    </li >
                    <li id="dept">
                        <a _href="${ctx}/dept/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加部门</cite>
                        </a>
                    </li >
                </ul>
          </li>
<!--************************************************************************************* -->  
       <!-- 用户审核管理 -->
            <li id="uservisit">
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>用户审核管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                   <li >
                        <a _href="${ctx}/uservisit/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户访问信息</cite>     
                        </a>
                    </li > 
                    <li>
                        <a _href="${ctx}/user/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户审核列表</cite>
                        </a>
                    </li >
                    <li>
                        <a  href="${ctx}/user/toadd">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加用户</cite>    
                        </a>
                    </li>
                   
                </ul>
            </li>
<!--************************************************************************************* -->                 
		  <!-- 下载管理 -->              
          <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b4;</i>
                    <cite>下载管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/document/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>文档查询</cite>
                        </a>
                    </li>
                    <li id="document">
                        <a _href="${ctx}/document/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>上传文档</cite>
                        </a>
                    </li>
                </ul>
          </li>
<!--************************************************************************************* -->  
		  <!-- 培训管理 -->            
          <li id="train">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>培训管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/train">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>培训计划</cite>
                        </a>
                    </li >
                    <!-- 新增培训评价及详细信息 -->
                    <li>
                        <a _href="${ctx}/train/trainlist?id=${sessionScope.user_session.id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>我的培训</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${ctx}/traindata/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>培训资料</cite>
                        </a>
                    </li >
                </ul>
          </li>           
          <!-- 管理员培训管理显示内容 -->  
          <li id ="admintrain">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>培训管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/train">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>培训计划</cite>
                        </a>
                    </li >
                    <!-- 新增详细信息，管理员，操作管理员工培训详细信息 -->
                    <li>
                        <a _href="${ctx}/train/admintrainlist">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>培训详情</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${ctx}/train/trainadd">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加培训信息</cite>
                        </a>
                    </li >
                     <li>
                        <a _href="${ctx}/traindata/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>培训资料管理</cite>
                        </a>
                    </li >
                </ul>
          </li>
            
<!--******************************************************************  -->        
          <!-- 数据报表 -->     
          <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>数据报表</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/statistics">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>数据报表</cite>
                        </a>
                    </li >
                </ul>
            </li> 
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${ctx}/welcome' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <div class="copyright" align="center"style="background-color:rgb(19,19,70)">Copyright ©2021 人事管理系统</div>
    </div>
    <!-- 底部结束 -->
 
</body>
</html>
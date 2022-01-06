<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>欢迎页面</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
         <link rel="stylesheet" href="${ctx}/public/css/font.css"> 
        <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
        <!-- 导入jquery插件 -->
		<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
		<script type="text/javascript" src="${ctx }/js/fkjava_timer.js"></script>
        <script type="text/javascript">
		function fontZoomA(){
	     document.getElementById('fontzoom').style.fontSize='12px';
	     document.getElementById('fontzoom').style.lineHeight='26px';
	     allChanges("12px","26px");
		}
		function fontZoomB(){
	     document.getElementById('fontzoom').style.fontSize='14px';
	     document.getElementById('fontzoom').style.lineHeight='28px';
	     allChanges("16px","28px");
		}
		function fontZoomC(){
	     document.getElementById('fontzoom').style.fontSize='16px';
	     document.getElementById('fontzoom').style.lineHeight='30px';
	     allChanges("20px","30px");
		}
		function allChanges(_fontSize,_lineHeight){
		 var allSpan=document.getElementById('fontzoom').getElementsByTagName('span');
		 var allDiv=document.getElementById('fontzoom').getElementsByTagName('div');
		 for(var i=0;i<allSpan.length;i++){
			 allSpan.item(i).style.fontSize=_fontSize;
			 allSpan.item(i).style.lineHeight=_lineHeight;
		 }
		 for(var i=0;i<allDiv.length;i++){
			 allDiv.item(i).style.fontSize=_fontSize;
			 allDiv.item(i).style.lineHeight=_lineHeight;
		 }
	 }
	
</script>
        
        <script type="text/javascript">
         $(function(){
    	    $("#nowTime").runTimer();
        })
    </script>
    
    
        
    </head>
    <body>
    <div class="x-body layui-anim layui-anim-up" style="font-family'宋体'">
       
        <h1 style="font-size: 36px;color: rgb(26,160,147);margin-left: 450px;">培训计划</h1>
	     <p style="margin-left:80px">2021-1-1&nbsp;&nbsp;&nbsp;&nbsp;人事管理系统</p>    
         <div style="margin-left:800px;margin-top:-15px;">【字体：<a href="javascript:fontZoomC();">大</a> <a href="javascript:fontZoomB();">中</a> <a  href="javascript:fontZoomA();">小</a>】 </div>
	     <hr>
	    
	    <div id="fontzoom">
		<p><font size="4px">一、培训计划的制定</font></p>
		<p>1.1&nbsp;培训分类</p>
		<p>&nbsp;&nbsp;培训计划分为公司级培训计划、部门内部培训计划、外派学习培训计划及各单项专业性计划培训。</p>
		<p>1.2&nbsp;培训安排</p>
		<p>&nbsp;&nbsp;公司级培训计划由行政部统一制定，由相关责任部门落实；部门内部培训计划由部门自行制定学习培训计划，自行组织培训实施；外派学习计划由各部门根据工作需要，制定学习计划和培训申请，由行政部组织实施。</p>
		<p>1.3&nbsp;培训内容</p>
		<p>&nbsp;&nbsp;公司统一制定的学习计划，根据工作情况实行全员培训，让全体员工能充分了解公司各项工作流程、制度和相关知识。行政部并定期制定计划，聘请相关专业老师组织专业知识培训或组织相关人员参加相关课程培训。</p>
		<p>1.4&nbsp;培训要求</p>
		<p>&nbsp;&nbsp;根据公司总的学习培训计划，行政部每月排定培训计划，按照年度学习计划推进各项学习培训工作。部门内部培训方面，部门内部根据各自工作情况，制定部门内部培训计划，并组织实施。行政部根据各部门的培训计划进行各部门培训学习的跟踪和监督，确保部门内部培训的落实。</p>
		<p><font size="4px">二、培训的组织实施</font></p>
		<p>2.1&nbsp;公司级内部培训的实施</p>
		<p>&nbsp;&nbsp;由行政部根据公司年度培训计划负责具体组织实施，每月排定培训计划，培训的责任部门制定培训教程及方案，具体实施。</p>
		<p>2.2&nbsp;部门内部培训的实施</p>
		<p>&nbsp;&nbsp;由各部门制定月度培训计划并组织实施。具体培训内容及形式、考核等由部门自行组织，行政部负责监督实施。</p>
		<p>2.3&nbsp;外派培训的实施</p>
		<p>&nbsp;&nbsp;外派培训审批程序：拟外派培训者提出申请→部门审核→行政部审核→分管副总审核→总经理批准。拟外派培训者应填写《员工外派培训申请表》。培训结束后，须将培训申请表交行政部存档。</p>
		<p>2.4&nbsp;单项专业培训的实施</p>
		<p>&nbsp;&nbsp;由行政部及相关责任部门负责制定培训计划，并组织实施;相关责任部门具体负责组织实施。</p>
		<p><font size="4px">三、培训的考核</font></p>
		<p>3.1&nbsp;培训考核部门</p>
		<p>&nbsp;&nbsp;培训考核由行政部统一进行。</p>
		<p>3.2&nbsp;培训课时要求</p>
		<p>&nbsp;&nbsp;公司级内部培训考核按培训计划进行考核。公司相关管理人员年度参加公司组织的培训课时不低于10课时；普通职工不低于8课时。各部门内部年度培训不低于12课时。员工如低于公司级统一培训要求的培训课时，取消全年先进的评比资格和年度公资调整的资格。</p>
		<p>3.3&nbsp;培训考核计划</p>
		<p>&nbsp;&nbsp;公司级内部培训考核分为年度考核和月度考核，月度考核按照公司统一安排，责行政部门制定的学习培训进行。原则上要求行政部门针对专项培训做到有计划、有组织、有考核、有结果，每缺少一项当月扣罚行政部门工资。部门级内部培训，各部门进行月度分解计划，每月报行政部培训计划，行政部按照部门内部的培训计划进行检查，低于计划培训课时或未组织，视情况给予扣罚部门当月工资。年底未完成年度培训课时，扣罚部门年度工资。</p>
		</div>
      </div>
    </body>
</html>
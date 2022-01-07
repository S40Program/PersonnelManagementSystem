<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>岗位公布</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css">
    <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
      <script type="text/javascript">
    $(function(){
    	/** 下载文档功能 */
    $("a[id^='down_']").click(function(){
    		/** 得到需要下载的文档的id */
    		var id = this.id.replace("down_","");
    		/** 下载该文档 */
    		window.location = "${ctx}/resume/downLoad?id="+id;
    	})
    })
    </script>
    
    <script type="text/javascript">
    $(function(){
    	if(${count}!=0){
    		$("#count1").hide();
    		$("#count2").show();
    	}
    	var username = "${sessionScope.user_session.loginname}";
    	if(username=="admin"||username=="manager"){
    		$("#aaa").show(); 
    		$("#bbb").show(); 
    		$("#do").css("display", "block"); 
    		$("#ID").show(); 
    		
    		$('tr').find('td:eq(11)').show();
    	}else{
    		$("#aaa").hide();
    		$("#bbb").hide(); 
    		$("#do").css("display", "none"); 
    		$("#ID").hide();
    		$('tr').find('td:eq(11)').hide();
    	};
    }) 
  </script>
  </head>
  
  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a>
          <cite>申请列表</cite></a>
      </span>
      <a id="bbb" class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${ctx }/resume/list" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row" style="" align="center">
        <form class="layui-form layui-col-md12 x-so" method="get" action="${ctx }/resume/list">
          
          <input type="text" name="content" style="width:50%;"  placeholder="请输入查找内容" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
              <th>岗位类别</th>
			  <th>岗位信息</th>
			  <th>姓名</th>
			  <th>邮箱</th>
			  <th>手机</th>
			  <th>性别</th>
			  <th>学历</th>
			  <th>申请时间</th>
			  <th>审核状态</th>
         	  <th>查看简历</th>
         	  <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.resume_list}" var="resume" varStatus="stat">
     <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>${resume.jobtype}</td>
             <td>${resume.content}</td>
             <td>${resume.name}</td>  
             <td>${resume.email}</td> 
             <td>${resume.phone}</td> 
             <td>${resume.sex.name}</td>  
			 <td>${resume.education.name}</td>
			 <td>${resume.creatTimeStr}</td> 
			 <td>
			    <c:choose>
					      <c:when test="${resume.status.id == 1}"><font style="color: rgb(0,150,136);">${resume.status.name}</font></c:when>
					      <c:otherwise><font color="red">${resume.status.name}</font></c:otherwise>
					 </c:choose>
			 </td>
			 <td align="center"  width="40px;"><a href="#" id="down_${resume.id }">
				<img width="20" height="20" title="下载" src="${ctx }/public/images/downLoad.png"/></a>
			 </td>
              <td class="td-manage">
              <input id="trueid" type="hidden" value="${resume.id}"/><button class="layui-btn layui-btn-xs" style="margin-left: 5px;" onclick="openVideo(this)">马上审核</button>  
            </td>
          </tr>
			</c:forEach>
          
        </tbody>
      </table>
         <!-- 分页标签 -->
     <div style="margin-left: 400px;" id="count1">
         <fkjava:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${ctx}/resume/list?pageIndex={0}"/>
     </div>
     
       <div style="margin-left: 500px; display: none;" id="count2">
                <p style="color: rgb(0,97,222)">共查询到<font color="red">${count}</font>条数据</p>
       </div>
    </div>
    
       <script type="text/javascript">
    function openVideo(id) {
    	var $id=$(id).parent();
        var id1 = $id.children("input").eq(0).val();  //  关键代码，获取id
        var index = layer.open({
            type: 2,
            content: '${ctx}/resume/edit?id='+id1,  //  点击某条招聘信息，将发条招聘信息的id传给后台
            area: ['400px', '300px'],
            offset:'m',
            maxmin: true,
            end: function () {
            	 
            }
        });
    }
</script>
  </body>

</html>
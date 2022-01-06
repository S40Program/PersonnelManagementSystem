<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>招聘信息添加页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css">
    <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
    
    <script type="text/javascript">
    $(function(){
    	$("#deptForm").submit(function(){
    		var peoplenum = $("#peoplenum"); //
    		var msg = "";
    		if (!/^[0-9]*$/.test($.trim(peoplenum.val()))){
    			msg = "人数格式不正确！！！";
    			peoplenum.focus(); 
    	    }
    		if (msg != ""){
    			 alert(msg);
    			return false;
    		}else{
    			$("#deptForm").submit();
    			return true;
    		}
    	});
    });
    </script>
  </head>      
  
  <body>
    <div class="x-body">
        <form class="layui-form" method="POST" id="deptForm"  action="${ctx}/recruitment/edit">
        <input type="hidden" name="id" value="${recruitment.id}"/>
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>岗位类别
              </label>
              <div class="layui-input-inline">
                  <select id="jobtype" name="jobtype" class="valid" >
                    <c:forEach items="${requestScope.jobType_list}" var="line" varStatus="stat">
                    <option value="${line.id}" <c:if test="${recruitment.jobtype.id == line.id }">selected</c:if>>${line.name}</option>
                    </c:forEach>
                  </select>
              </div>
          </div>
          <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>岗位信息
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="content" name="content" required="" lay-verify="required" 
                  autocomplete="off" class="layui-input" value="${recruitment.content}">
              </div>
          </div>
           <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>截止日期
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="enddate" name="enddate" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${recruitment.enddate}">
              </div>
          </div> 
          <div class="layui-form-item"  >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>在招人数
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="peoplenum" name="peoplenum" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${recruitment.peoplenum}">
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>招聘状态
              </label>
              <div class="layui-input-inline">
                  <select id="status" name="status" class="valid" >
                    <c:forEach items="${requestScope.recruitmentStatus_list}" var="line" varStatus="stat">
                    <option value="${line.id}" <c:if test="${recruitment.status.id == line.id }">selected</c:if>>${line.name}</option>
                    </c:forEach>
                  </select>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <input type="submit" value="发布" class="layui-btn" lay-filter="add" lay-submit=""/>
                 
          </div>	
      </form>	
    </div>
    
    <script>
		layui.use('laydate', function(){
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
			elem: '#enddate' //指定元素
			});
		});
	</script>
    
  </body>
</html>
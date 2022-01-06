<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>社保编辑页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css">
    <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
    <script type="text/javascript">
$(function(){
	$("#insuranceForm").submit(function(){
		var employee_name = $("#employee_name");
		var msg = "";
		if (!/^[\u4E00-\u9FA5]{2,5}$/.test($.trim(employee_name.val()))){
			msg = "姓名格式不正确！！！";
			employee_name.focus(); 
	    }
		if (msg != ""){
			 alert(msg);
			return false;
		}else{
			return true;
			$("#insuranceForm").submit();
		}
	});
});
</script>
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form" method="POST" id="insuranceForm"  action="${ctx}/insurance/add">
        <input type="hidden" name="id" id="id" value="${insurance.id}" >
          <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="employee_name" name="employee_name" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${insurance.employee.name}">
                  <p class="x-red">请联系管理员修改姓名等信息</p>
              </div>
             
          </div>
  			<div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>职位
              </label>
              <div class="layui-input-inline">
                  <select id="job_id" name="job_id" class="valid" disabled="disabled">
                    <option value="0">--部门选择--</option>
						   <c:forEach items="${requestScope.job_list}" var="job">
						   		<c:choose>
			    					<c:when test="${insurance.job.id == job.id }">
			    						<option value="${job.id }" selected="selected">${job.name }</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${job.id }">${job.name}</option>
			    					</c:otherwise>
			    				</c:choose>
			    			</c:forEach>
                  </select>
              </div>
          </div>
            <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>部门
              </label>
              <div class="layui-input-inline">
                  <select  name="dept_id" class="valid" disabled="disabled">
                      <option value="0">--部门选择--</option>
						   <c:forEach items="${requestScope.dept_list}" var="dept">
						   		<c:choose>
			    					<c:when test="${insurance.dept.id == dept.id }">
			    						<option value="${dept.id }" selected="selected">${dept.name }</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${dept.id }">${dept.name}</option>
			    					</c:otherwise>
			    				</c:choose>
			    			</c:forEach>
                  </select>
              </div>
          </div>
          
          
               
          <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>医疗保险
              </label>
              <div class="layui-input-inline">
               <select id="medicareInsurance_id" name="medicareInsurance_id" class="valid">
                  			<c:forEach items="${requestScope.medicareinsurance_list}" var="job">
						   		<c:choose>
			    					<c:when test="${insurance.medicareInsurance.id == job.id }">
			    						<option value="${job.id }" selected="selected">${job.name }</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${job.id }">${job.name}</option>
			    					</c:otherwise>
			    				</c:choose>
			    			</c:forEach>
				</select>
              </div>
             
          </div>
          
          
           <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>养老保险
              </label>
              <div class="layui-input-inline">
               <select id="endowmentInsurance_id" name="endowmentInsurance_id" class="valid">
                             <c:forEach items="${requestScope.endowmentinsurance_list}" var="job">
						   		<c:choose>
			    					<c:when test="${insurance.endowmentInsurance.id == job.id }">
			    						<option value="${job.id }" selected="selected">${job.name }</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${job.id }">${job.name}</option>
			    					</c:otherwise>
			    				</c:choose>
			    			</c:forEach>
				</select>
              </div>
             
          </div>
          <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>意外保险
              </label>
              <div class="layui-input-inline">
               <select id="accidentInsurance_id" name="accidentInsurance_id" class="valid">
                             <c:forEach items="${requestScope.accidentinsurance_list}" var="job">
						   		<c:choose>
			    					<c:when test="${insurance.accidentInsurance.id == job.id }">
			    						<option value="${job.id }" selected="selected">${job.name }</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${job.id }">${job.name}</option>
			    					</c:otherwise>
			    				</c:choose>
			    			</c:forEach>
				</select>
              </div>
          </div> 
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <input type="submit" value=" 提交" class="layui-btn" lay-filter="add" lay-submit=""/>
          </div>
      </form>
    </div>
    <script>
          //监听提交
          form.on('submit(add)', function(data){
            console.log(data);
            //发异步，把数据提交给php
            layer.alert("修改成功", {icon: 6},function () {
            	document.getElementById('insuranceForm').submit();
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
               
            });
            return false;
          });
    </script>
    
  </body>

</html>
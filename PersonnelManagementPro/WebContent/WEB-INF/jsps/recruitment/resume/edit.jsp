<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>简历投递审核页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css"> 
    <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
   
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form" method="POST" id="deptForm"  action="${ctx}/resume/edit">
        <font size="3px;" style="color: rgb(0,150,136);margin-left: -10px;">提交审核前确保已经下载用户的简历，并完成审核</font>
        <input type="hidden" name="id" id="id" value="${resume.id }" >
        <input type="hidden" name="email" id="email" value="${resume.email }" >
        
         <div class="layui-form-item" style="margin-top: 20px;">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>申请用户
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="name" name="name" required="" lay-verify="required"  readonly="readonly"
                  autocomplete="off" class="layui-input" value="${resume.name }">
              </div>
             
          </div>
          
            <div class="layui-form-item" id="status">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>审核状态
              </label>
              <div class="layui-input-inline">
                  <select  name="status_id" class="valid">
						   <c:forEach items="${requestScope.status_list}" var="dept">
						   		<c:choose>
			    					<c:when test="${resume.status.id == dept.id }">
			    						<option value="${dept.id}" selected="selected">${dept.name }</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${dept.id}">${dept.name}</option>
			    					</c:otherwise>
			    				</c:choose>
			    			</c:forEach>
                  </select>
              </div>
          </div>
        
         
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <input type="submit" value="提交审核结果" class="layui-btn" lay-filter="add" lay-submit=""/>
                 
          </div>
      </form>
    </div>
  </body>
</html>
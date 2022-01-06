<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>个人招聘信息</title>
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
    		var name = $("#name");//姓名
    		var email = $("#email");//邮箱
    		var phone= $("#phone");	//手机
    		var fileInput = $('#file').get(0).files[0];
    		var msg = "";
    		if (!/^[\u4E00-\u9FA5]{2,4}$/.test($.trim(name.val()))){
    			msg = "姓名格式不正确！！！";
    			name.focus(); 
    	    }
    		else if (!/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/.test($.trim(email.val()))){
    			msg = "邮箱格式不正确！！！";
				email.focus(); 
    		}
    		else if (!/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/.test($.trim(phone.val()))){
				msg = "手机格式不正确！！！";
				phone.focus(); 
		    }
    		else if(!fileInput){
    	    	msg = "请选择上传文件！文件不能为空";
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
        <form class="layui-form" method="POST" id="deptForm" enctype="multipart/form-data"  action="${ctx}/resume/add">
        <font size="3px;" style="color: rgb(0,150,136);margin-left: 25px;">请认真填写个人信息</font>
        
          <div class="layui-form-item"  style="margin-top: 20px;" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>类别
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="jobtype" name="jobtype" required="" lay-verify="required" readonly="readonly"
                  autocomplete="off" class="layui-input" value="${recruitment.jobtype.name}">
              </div>
          </div>
         
         
          <div class="layui-form-item"  style="margin-top: 20px;" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>岗位
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="content" name="content" required="" lay-verify="required"  readonly="readonly"
                  autocomplete="off" class="layui-input" value="${recruitment.content}">
              </div>
          </div>
         
          <div class="layui-form-item"  style="margin-top: 20px;" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="name" name="name" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="">
              </div>
          </div>
          <div class="layui-form-item" >
              <label for="phone" class="layui-form-label">
                  <span class="x-red">*</span>邮箱
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="email" name="email" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="">
              </div>
          </div>
            <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>性别
              </label>
              <div class="layui-input-inline">
               <select id="sex_id" name="sex_id" class="valid">
               <option value="0" >选择性别</option>
                  			<c:forEach items="${requestScope.sex_list}" var="job">
						   		<c:choose>
			    					<c:when test="${employee.sex.id== job.id }">
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
                  <span class="x-red">*</span>学历
              </label>
              <div class="layui-input-inline">
               <select id="education_id" name="education_id" class="valid">
                        <option value="0" >选择学历</option>
                  			<c:forEach items="${requestScope.education_list}" var="job">
						   		<c:choose>
			    					<c:when test="${employee.education.id== job.id }">
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
              <label for="phone" class="layui-form-label">
                  <span class="x-red">*</span>手机
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="phone" name="phone" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="">
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>简历
              </label>
              <div class="layui-input-inline">
                  <input type="file" id="file" name="file">
              </div>
          </div>
             
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <input type="submit" value=" 提交简历" class="layui-btn" lay-filter="add" lay-submit=""/>
                 
          </div>	
      </form>	
    </div>
    
  </body>
</html>
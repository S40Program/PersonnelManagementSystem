<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>填写备注</title>
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
        <form class="layui-form" method="POST" id="deptForm"  action="${ctx}/checkwork/topunchtime">
        <!-- id为员工信息登录名-->
        <input type="hidden" name="id" id="id" value="${list.id}" >

          <div class="layui-form-item" >
              <label for="am" class="layui-form-label">
                  <span class="x-red">*</span>上班时间
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="am" name="am" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${list.am}">
              </div>
          </div>

            <div class="layui-form-item" >
                <label for="pm" class="layui-form-label">
                    <span class="x-red">*</span>下班时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="pm" name="pm" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" value="${list.pm}">
                </div>

            </div>
          <div class="layui-form-item">
              <label class="layui-form-label">
              </label>
              <input type="submit" value=" 提交" class="layui-btn" lay-filter="add" lay-submit=""/>
          </div>
      </form>
    </div>

    <script>
        layui.use('laydate', function() {
                var laydate = layui.laydate;
            //时间选择器
            laydate.render({
                elem: '#am'
                ,type: 'time'
                ,format: 'HH:mm' //可任意组合
            });
            laydate.render({
                elem: '#pm'
                ,type: 'time'
                ,format: 'HH:mm' //可任意组合
            });
            })
    </script>
  </body>

</html>
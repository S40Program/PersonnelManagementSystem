<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>请假信息列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css">
    <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <link rel="stylesheet" href="${ctx}/public/lib/layui/css/layui.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
      <script>
          $(function () {
              $("button").click(function () {
                  var date = $("#date").val();
                  window.location='${ctx}/checkwork/statistics?date='+date;
              })
          })
      </script>
  </head>
  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a>
          <cite>打卡记录</cite></a>
      </span>
      <a id="aaa" class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${ctx}/checkwork/punchlist" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>

    <div class="x-body">
        <div>
            <form class="layui-form" id="select">
                <div class="layui-inline">
                    <label class="layui-form-label">按月查询</label>
                    <div class="layui-input-inline">
                        <select name="date" lay-verify="required" lay-search="" id="date">
                            <option value="">请选择需要统计的月份</option>
                            <option value="2020-1">1月</option>
                            <option value="2020-2">2月</option>
                            <option value="2020-3">3月</option>
                            <option value="2020-4">4月</option>
                            <option value="2020-5">5月</option>
                            <option value="2020-6">6月</option>
                            <option value="2020-7">7月</option>
                            <option value="2020-8">8月</option>
                            <option value="2020-9">9月</option>
                            <option value="2020-10">10月</option>
                            <option value="2020-11">11月</option>
                            <option value="2020-12">12月</option>
                        </select>
                    </div>
                </div>
                <button type="button" value="查询" class="layui-btn">查询</button>
            </form>
            <label class="layui-form-label" style="margin-left: 440px;margin-top: -38px;color: #1AA093">迟到天数：${late}</label>
            <label class="layui-form-label" style="margin-left: 540px;margin-top: -38px;color: #1AA093">早退天数：${leave_early}</label>
        </div>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox"  lay-skin="primary" ><i class="layui-icon">&#xe605;</i></div>
            </th>
              <td>姓名</td>
              <th>打卡日期</th>
			  <th>上班打卡时间</th>
			  <th>下班打卡时间</th>
			  <th>上班状态</th>
			  <th>下班状态</th>
			  <th>备注</th>
			  <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.punch}" var="punch" varStatus="stat">
     <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
             <td>${name}</td>
             <td>${punch.date}</td>
             <td>${punch.sgin_time}</td>
             <td>${punch.offwork_time}</td>
         <td>
             <c:choose>
                <c:when test="${punch.sgin_status_id != 2}">正常</c:when>
                <c:otherwise><a><font color="red">迟到</font></a></c:otherwise>
             </c:choose>
         </td>
         <td>
             <c:choose>
                <c:when test="${punch.offwork_status_id != 2}">正常</c:when>
                <c:otherwise><a><font color="red">早退</font></a></c:otherwise>
             </c:choose>
         </td>
			 <td>${punch.content}</td>
            <td class="td-manage">
             <a title="编辑"  href='${ctx}/checkwork/updatePunchContent?id=${punch.id}'>
                <i class="layui-icon">&#xe642;</i>
              </a>
            </td>
          </tr>
         </c:forEach>
        </tbody>
      
      </table>
    </div>
    <script>
      
      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              //等以后再使用异步，这里先使用
              $.get("${ctx}/checkwork/delete?id="+id);
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }
    </script>

    <script>
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
            var form = layui.form
                ,layer = layui.layer;
            //自定义验证规则
            form.verify({
                nikename: function(value){
                    if(value.length < 5){
                        return '昵称至少得5个字符啊';
                    }
                }
                ,pass: [/(.+){6,12}$/, '密码必须6到12位']
                ,repass: function(value){
                    if($('#L_pass').val()!=$('#L_repass').val()){
                        return '两次密码不一致';
                    }
                }
            });
        });
    </script>
    
  </body>

</html>
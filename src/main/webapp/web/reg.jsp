<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<%@ include file="/web/header.jsp" %>
</head>
<body style="background: #f2f2f2">

http://127.0.0.1:8080//demo0111/web/reg.jsp
<div class="window">
<fieldset class="layui-elem-field" style="margin:20px">
	<legend>注册</legend>
  <div class="layui-field-box">
    <form class="layui-form layui-form-pane">
    	<div class="layui-form-item">
	   		 <label class="layui-form-label">账号</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="code" required  lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input">
	    	</div>
        </div>
        <div class="layui-form-item">
	   		 <label class="layui-form-label">密码</label>
	   		 <div class="layui-input-inline">
	     	 <input type="password" name="pass" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
	    	</div>
        </div>
        <div class="layui-form-item">
	   		 <label class="layui-form-label">姓名</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="name" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
	    	</div>
        </div>
        <div class="layui-form-item">
	   		 <div class="layui-input-inline">
	     	 <input type="button" value="注册" class="layui-btn" lay-submit lay-filter="reg">
	     	 <input type="reset" value="重置" class="layui-btn layui-btn-primary">
	    	</div>
	     <input type="button" value="返回登陆" class="layui-btn" onclick="toHref('/web/login.jsp')">
	    <input type="hidden"  name="action" value="reg" />
        </div>
    </form>
  </div>
</fieldset>
</div>

<script type="text/javascript">
/* var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
form.on("submit(reg)",function(data){
	console.log(data.field)
	console.log(base.app)
	$.ajax({
		url: base.app+"/EmployeeServlet",
		data:data.field, //a=b&c=d /{a:b,c:d}
		type:"post",
		dataType:"text", //text / json (返回数据的类型)
		success:function(data){
			console.log(data)
			if(data==1){
				layer.msg('注册成功');
			}else if(data=="repeat"){
				layer.msg('注册失败,账号重复');
			}else{
				layer.msg('注册失败');
			}
		}
	});
}); */

formOnSubmit('reg', '/EmployeeServlet', 'text',function(data){
	console.log(data)
	if(data==1){
		layer.msg('注册成功');
	}else if(data=="repeat"){
		layer.msg('注册失败,账号重复');
	}else{
		layer.msg('注册失败');
	}
})

</script>

</body>
</html>












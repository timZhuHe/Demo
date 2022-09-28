<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆</title>
<%@ include file = "/web/header.jsp" %>
</head>
<body style="background: #f2f2f2">

http://127.0.0.1:8080//demo0111/web/login.jsp
<div class="window">
<fieldset class="layui-elem-field" style="margin:20px">
	<legend>登陆</legend>
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
	   		 <div class="layui-input-inline">
	     	 <input type="button" value="登陆" class="layui-btn" lay-submit lay-filter="login">
	     	 <input type="reset" value="重置" class="layui-btn layui-btn-primary">
	    	</div>
	     <input type="button" value="前往注册" class="layui-btn" onclick="toHref('/web/reg.jsp')">
	    <input type="hidden"  name="action" value="login" />
        </div>
    </form>
  </div>
</fieldset>
</div>

<script type="text/javascript">
formOnSubmit('login', '/EmployeeServlet', 'text',function(data){
	console.log(data)
	if(data == "1"){
		layer.msg("登陆成功",{time:1000},function(){
			toHref('/web/page/main.jsp');
			});
		
	}else if(data =="2"){
		layer.msg('登陆失败,密码错误');
	}else if(data == "0"){
		layer.msg('登陆失败，账号不存在');
	}
})


</script>

</body>
</html>
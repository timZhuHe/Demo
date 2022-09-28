<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "/web/header.jsp" %>
</head>
<body>
<fieldset class="layui-elem-field" style="margin:20px">
	<legend>员工修改</legend>
  <div class="layui-field-box">
    <form class="layui-form layui-form-pane" lay-filter='formA'>
    	<div class="layui-form-item">
	   		 <label class="layui-form-label">员工编号</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="code" required  lay-verify="required" readonly placeholder="请输入员工编号" autocomplete="off" class="layui-input">
	    	</div>
        </div>
        <div class="layui-form-item">
	   		 <label class="layui-form-label">员工姓名</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="name" required  lay-verify="required" placeholder="请输入员工姓名" autocomplete="off" class="layui-input">
	    	</div>
        </div>
        <div class="layui-form-item">
	   		 <div class="layui-input-inline">
	     	 <input type="button" value="确定" class="layui-btn" lay-submit lay-filter="upd">
	     	 <input type="reset" value="重置" class="layui-btn layui-btn-primary">
	    	</div>
	     <input type="button" value="关闭" class="layui-btn" onclick="layerClose()" >
	    <input type="hidden"  name="action" value="upd" />
        </div>
    </form>
  </div>
</fieldset>

<script type="text/javascript">
var code = '<%=request.getParameter("code") %>';
function init(){
	 ajax('/EmployeeServlet', {code:code, action:'get'}, 'json',function(data){
		console.log(data);
		/*  $("input[name='code']").val(data.code)
		 $("input[name='name']").val(data.name)
		 $("input[name='tel']").val(data.tel) */
		 form.val('formA', {code:data.code, name:data.name});
		 
	 })
}
init();
formOnSubmit('upd', '/EmployeeServlet', 'text',function(data){
	console.log(data)
	if(data==1){
	layer.msg('修改成功',{time:1000}, layerClose);
	}else{
		layer.msg('修改失败');
	}
})

</script>

</body>
</html>
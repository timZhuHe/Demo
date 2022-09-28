<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改</title>
<%@ include file = "/web/header.jsp" %>
</head>
<body>
<fieldset class="layui-elem-field" style="margin:20px">
	<legend>绩效修改</legend>
  <div class="layui-field-box">
    <form class="layui-form layui-form-pane" lay-filter='formA'>
    	<div class="layui-form-item">
	   		 <label class="layui-form-label">员工姓名</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="codeEmp" required  lay-verify="required" readonly placeholder="请输入员工姓名" autocomplete="off" class="layui-input">
	    	</div>
        </div>
        <div class="layui-form-item">
	   		 <label class="layui-form-label">项目名称</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="codePro" required  lay-verify="required" readonly placeholder="请输入项目名称" autocomplete="off" class="layui-input">
	    	</div>
        </div>
        <div class="layui-form-item">
	   		 <label class="layui-form-label">绩效</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="score" required  lay-verify="required"  placeholder="请输入绩效" autocomplete="off" class="layui-input">
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
var codeEmp = '<%=request.getParameter("codeEmp") %>';
var codePro = '<%=request.getParameter("codePro") %>';

function init(){
	ajax("/ScoreServlet", {codeEmp:codeEmp, codePro:codePro, action:'get'}, 'json', function(data){
		form.val('formA',data);
	})
}

init();
formSubmit('upd','/ScoreServlet','text',function(data){
	if(data == 1){
		layer.msg("修改成功", {time:1000}, layerClose);
	}else{
		layer.msg("修改失败");
	}
})

</script>
</body>
</html>















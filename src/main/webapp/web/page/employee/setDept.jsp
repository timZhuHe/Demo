<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>设置部门</title>
<%@ include file = "/web/header.jsp" %>
</head>
<body>
<fieldset class="layui-elem-field" style="margin: 20px;padding:15px">
	<legend>设置部门</legend>
	<form class="layui-form" lay-filter="formA" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">编号</label>
			<div class="layui-input-inline">
			<input type="text" name="code" readonly lay-verify="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">部门</label>
			<div class="layui-input-inline">
				<select name="codeDept"></select>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="button" class="layui-btn" lay-submit lay-filter="setDept" value="确定">
				<input type="button" class="layui-btn" onclick="layerClose()" value="关闭">
			</div>
		</div>
		<input type="hidden" name="action" value="setDept">
		
	</form>
</fieldset>

<script type="text/javascript">
initSel();
function initSel(){//从数据库中初始化下拉框的数据
	ajax('/DepartmentServlet',{action:'list'},'json',function(data){
		var opt="<option value=''>请选择部门</option>"
		$.each(data,function(){
			opt += "<option value= '"+this.code+"'>"+this.name+"</option>"
		})
		$("select[name='codeDept']").html(opt);
		form.render();
		init();
	})
}

var code = '<%=request.getParameter("code")%>';
 //console.log(code);
function init(){
	ajax('/EmployeeServlet',{action:'get',code:code},'json',function(data){
		form.val("formA",{code:data.code, codeDept:data.codeDept})
	});
}

formOnSubmit('setDept','/EmployeeServlet','text',function(data){
	if(data==1){
		layer.msg("设置成功",{time:1000},layerClose)
	}else{
		layer.msg("设置失败");
	}
});

</script>
</body>
</html>













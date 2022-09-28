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
	<legend>员工添加</legend>
  <div class="layui-field-box">
    <form class="layui-form layui-form-pane">
    	<div class="layui-form-item">
	   		 <label class="layui-form-label">员工</label>
	   		 <div class="layui-input-inline">
	     	  <select name='codeEmp' requires lay-verify="required"></select>
	    	</div>
        </div>
        <div class="layui-form-item">
	   		 <label class="layui-form-label">项目</label>
	   		 <div class="layui-input-inline">
	     	 <select name='codePro' requires lay-verify="required"></select>
	    	</div>
	    </div>
	     <div class="layui-form-item">
	   		 <label class="layui-form-label">绩效</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="score" required  lay-verify="required" placeholder="请输入员工绩效" autocomplete="off" class="layui-input">
	    	</div>
        </div>
        <div class="layui-form-item">
	   		 <div class="layui-input-inline">
	     	 <input type="button" value="确定" class="layui-btn" lay-submit lay-filter="add">
	     	 <input type="reset" value="重置" class="layui-btn layui-btn-primary">
	    	</div>
	     <input type="button" value="关闭" class="layui-btn" onclick="layerClose()" >
	    <input type="hidden"  name="action" value="add" />
        </div>
    </form>
  </div>
</fieldset>
<script type="text/javascript">
initEmp();
function initEmp(){
	 ajax('/EmployeeServlet', {action:'list'}, 'json', function(data){
		 var opt ="<option value=''> 请选择员工 </option>"
		 $.each(data,function(){
			 opt += "<option value='"+this.code+"'>"+this.name+"</option>"
		 })
		 $("select[name='codeEmp']").html(opt);
		 form.render();
	 })
}

initPro();
function initPro(){
	 ajax('/ProjectServlet',{action:'list'},'json',function(data){
		 var opt ="<option value=''>请选择项目</option>"
		 $.each(data,function(){
			 opt += "<option value='"+this.code+"'>"+this.name+"</option>"
		 })
		 $("select[name='codePro']").html(opt);
		 form.render();
	 })
}


formOnSubmit('add','/ScoreServlet','text',function(data){
	console.log(data);
	if(data==1){
		layer.msg("添加成功",{time:1000}, layerClose);
	}else if(data == "repeat"){
		layer.msg("添加重复 请核对信息");
	}else{
		layer.msg("添加失败");
	}
	
});

</script>
</body>
</html>





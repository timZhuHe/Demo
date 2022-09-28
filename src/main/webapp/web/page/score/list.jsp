<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>绩效</title>
<%@ include file = "/web/header.jsp" %>
</head>
<body>
<div class="layui-collapse">
  <div class="layui-colla-item">
    <h2 class="layui-colla-title">绩效信息</h2>
    <div class="layui-colla-content layui-show">
    <fieldset class="layui-elem-field" style="margin:20px">
	<legend>绩效信息-查询条件</legend>
  <div class="layui-field-box">
    <form class="layui-form" >
    	<div class="layui-form-item">
	   		 <label class="layui-form-label">员工编号</label>
	   		 <div class="layui-input-inline">
	     	 <select name='codeEmp'></select>
	    	</div>
	   		 <label class="layui-form-label">项目名称</label>
	   		 <div class="layui-input-inline">
	     	 <select name='codePro'></select>
	    	</div>
	    	<span>
	    		<input type="button" value="查询" class="layui-btn layui-btn-sm" lay-submit lay-filter="search" />
	    		 <input type="reset" value="重置" class="layui-btn layui-btn-sm layui-btn-primary">
	    		 <input type="button" value="添加" class="layui-btn layui-btn-sm" onclick='openAdd()' />
	    	</span>
	    	<input type='hidden' value='list' name='action'>
	    	
        </div>

    </form>
  </div>
</fieldset>
    </div>
  </div>
 </div>
  
  <table class="layui-table">
  	<colgroup>
  		<col width="10%"><col width="10%"><col width="10%"><col width="10%"><col width="10%"><col width="10%"><col width="50%">
  	</colgroup>
  		<thead>
  			<tr>
  				<th>序号</th><th>员工编号</th><th>员工姓名</th>
  				<th>项目编号</th><th>项目名称</th><th>绩效</th><th>操作</th>
  			</tr>
  		</thead>
  		<tbody>
  			
  		</tbody>
  
  </table>
 
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
 
 function refresh(){
	  $("input[value='reset']").click();
	  $("input[value='查询']").click();
} 
 
 formOnSubmit('search','/ScoreServlet','json',function(data){
	 //console.log(data);
	 var k = 1;
	 var html ="";
	 var tpl = $("#tradd").html();
	// var nameDept = dom.nameDept ? dom.nameDpet : '';
	  $.each(data, function(i,dom){
		  var d = {id:k++ , codeEmp: dom.codeEmp, nameEmp:dom.nameEmp, codePro:dom.codePro, namePro:dom.namePro, score:dom.score}
		  html += laytpl(tpl).render(d);
	  })
	  $("tbody").html(html);
})

refresh();

function openAdd(){
	 layerOpen('/web/page/score/add.jsp',refresh);
 }
 
 function del(codeEmp,codePro){
	 layerConfirm(function(index){
		ajax("/ScoreServlet",{codeEmp:codeEmp, codePro:codePro, action:'del'}, 'text', function(data){
			if(data == 1){
				layer.msg("删除成功", {time:1000}, refresh);
			}else{
				layer.msg("删除失败");
			}
		}) 
	 })
 }
 
 function openUpd(codeEmp,codePro){
	 layerOpen('/web/page/score/upd.jsp?codeEmp='+codeEmp+'&codePro='+codePro+'',refresh);
 }
 
 
 </script>
  
   <script type="text/html" id="tradd">
	<tr>
		<td>{{d.id}}</td><td>{{d.codeEmp}}</td><td>{{d.nameEmp}}</td><td>{{d.codePro}}</td><td>{{d.namePro}}</td><td>{{d.score}}</td>
		<td>
			<a href="javascript:del('{{d.codeEmp}}','{{d.codePro}}')" class="layui-btn layui-btn-xs layui-btn-danger"> 删除 </a>
			<a href="javascript:openUpd('{{d.codeEmp}}','{{d.codePro}}')" class="layui-btn layui-btn-xs"> 修改 </a>
		</td>	
	</tr>
</script>
</body>
</html>












<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>项目信息</title>
<%@ include file = "/web/header.jsp" %>
</head>
<body>
<div class="layui-collapse">
  <div class="layui-colla-item">
    <h2 class="layui-colla-title">项目信息</h2>
    <div class="layui-colla-content layui-show">
    <fieldset class="layui-elem-field" style="margin:20px">
	<legend>项目信息-查询条件</legend>
  <div class="layui-field-box">
    <form class="layui-form" >
    	<div class="layui-form-item">
	   		 <label class="layui-form-label">项目编号</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="code"  placeholder="请输入项目编号" autocomplete="off" class="layui-input">
	    	</div>
	   		 <label class="layui-form-label">部门名称</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="name"  placeholder="请输入项目名称" autocomplete="off" class="layui-input">
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
  		<col width="10%"><col width="10%"><col width="10%"><col width="20%"><col width="50%">
  	</colgroup>
  		<thead>
  			<tr>
  				<th>序号</th><th>编号</th><th>项目名称</th>
  				<th>启动时间</th><th>操作</th>
  			</tr>
  		</thead>
  		<tbody>
  			
  		</tbody>
  
  </table>
  
 <script type="text/javascript">
 function refresh(){
	  $("input[value='reset']").click();
	  $("input[value='查询']").click();
} 
 function openAdd(){
		layerOpen('/web/page/project/add.jsp',refresh);
	}
 
 formOnSubmit('search','/ProjectServlet','json',function(data){
	 //console.log(data);
	 var k = 1;
	var html ="";
	 var tpl = $("#tradd").html();
	// var nameDept = dom.nameDept ? dom.nameDpet : '';
	  $.each(data, function(i,dom){
		  var d = {id:k++ , code: dom.code, name:dom.name, time:dom.time}
		  html += laytpl(tpl).render(d);
	  })
	  $("tbody").html(html);
	 
})
refresh();
 
 function del(code){
	 layerConfirm(function(index){
		 ajax('/ProjectServlet', {code:code, action:"del"}, 'text', function(data){
			 if(data==1){
				 layer.msg("删除成功", {time:1000}, refresh);
			 }else{
				 layer.msg("删除失败");
			 }
		 })
	 })
 }

 </script>
 
 <script type="text/html" id="tradd">
	<tr>
		<td>{{d.id}}</td><td>{{d.code}}</td><td>{{d.name}}</td><td>{{d.time}}</td>
		<td>
			<a href="javascript:del('{{d.code}}')" class="layui-btn layui-btn-xs layui-btn-danger"> 删除 </a>
			<a href="javascript:openUpd('{{d.code}}')" class="layui-btn layui-btn-xs"> 修改 </a>
		</td>	
	</tr>
</script>
</body>
</html>






<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Department List</title>
<%@ include file = "/web/header.jsp" %>
</head>
<body>
/demo0111/src/main/webapp/web/page/department/list.jsp
http://127.0.0.1:8080//demo0111/web/page/department/list.jsp
<div class="layui-collapse">
  <div class="layui-colla-item">
    <h2 class="layui-colla-title">部门信息</h2>
    <div class="layui-colla-content layui-show">
    <fieldset class="layui-elem-field" style="margin:20px">
	<legend>部门信息-查询条件</legend>
  <div class="layui-field-box">
    <form class="layui-form" >
    	<div class="layui-form-item">
	   		 <label class="layui-form-label">部门编号</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="code"  placeholder="请输入部门编号" autocomplete="off" class="layui-input">
	    	</div>
	   		 <label class="layui-form-label">部门名称</label>
	   		 <div class="layui-input-inline">
	     	 <input type="text" name="name"  placeholder="请输入部门名称" autocomplete="off" class="layui-input">
	    	</div>
	    	<span>
	    		<input type="button" value="查询" class="layui-btn layui-btn-sm" lay-submit lay-filter="search" />
	    		 <input type="reset" value="重置" class="layui-btn layui-btn-sm layui-btn-primary">
	    		 <input type="button" value="添加" class="layui-btn layui-btn-sm" onclick='openAdd()' />
	    	</span>
	    	<input type='hidden' value='page' name='action'>
	    	<input type='hidden' name='pageIndex'  value='1'>
	    	<input type='hidden' name='pageLimit' value='10'>
	    	
        </div>

    </form>
  </div>
</fieldset>
    </div>
  </div>
  
  <table class="layui-table">
  	<colgroup>
  		<col width="10%"><col width="10%"><col width="10%">
  		<col width="10%"><col width="10%">
  	</colgroup>
  		<thead>
  			<tr>
  				<th>序号</th><th>编号</th><th>名称</th>
  				<th>电话</th><th>人数</th><th>操作</th>
  			</tr>
  		</thead>
  		<tbody>
  			
  		</tbody>
  
  </table>
  
  <div id="pageInfo" style="text-align:right;padding-right:30px"></div>
  
  
  <script type="text/javascript">
  formOnSubmit('search','/DepartmentServlet','json', function(data){
	  var list = data.list;
	  var count = data.count;
	  var curr = $("input[name = 'pageIndex']").val();
	  var limit = $("input[name = 'pageLimit']").val();
	  console.log(list);
	  console.log(count);
	  pageInfo('pageInfo',data.count, curr,limit,function(obj,first){
		  $("input[name = 'pageIndex']").val(obj.curr);
		  $("input[name = 'pageLimit']").val(obj.limit);
		  if(!first){refresh();}//首次不执行
	  })
	  
	  //分页空间的渲染=表格中全部数据的条数
	  
	  //展示当前页面的数据=表格中的当前页面的数据
	  
	  //console.log(data)
	  var html ="";
	  var tpl = $("#tradd").html();
	  $.each(list , function(i,dom){
		  var d = {id:(i+1+(curr-1)*limit) , code: dom.code, name:dom.name, tel:dom.tel , count:dom.count}
		  html += laytpl(tpl).render(d);
	  })
	  $("tbody").html(html);
	  layer.msg("查询成功");
  })
  
  function refresh(){
	  $("input[value='reset']").click();
	  $("input[value='查询']").click();
  }
  refresh();
  
  function openAdd(){
	  //layer.msg('123');
	  layerOpen('/web/page/department/add.jsp', refresh);
  }
  
  function del (code){
	  layerConfirm(function(index){
		  ajax('/DepartmentServlet',{code:code, action:'del'}, 'text',function(data){
			  console.log(data);
			  if(data == 1){
				  layer.msg("删除成功",refresh);
			  }else{
				  layer.msg("删除失败")
			  }
		  })
	  });
  } 
  
  function openUpd(code){
	  //layer.msg(code);
	 //layerOpen('/web/page/department/upd.jsp?code='+code, refresh);
	  layerOpen('/DepartmentServlet?action=get2&code='+code, refresh);
  }
  
  </script>
  
  <script type="text/html" id="tradd">
	<tr>
		<td>{{d.id}}</td><td>{{d.code}}</td><td>{{d.name}}</td><td>{{d.tel}}</td><td>{{d.count}}</td>
		<td>
			<a href="javascript:del('{{d.code}}')" class="layui-btn layui-btn-sm layui-btn-danger"> 删除 </a>
			<a href="javascript:openUpd('{{d.code}}')" class="layui-btn layui-btn-sm"> 修改 </a>
		</td>	
	</tr>
</script>
</body>
</html>















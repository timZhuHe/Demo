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

<fieldset class="layui-elem-field " style="margin:10px; padding:15px; ">
  <legend>头像图片</legend>
  <div class="layui-field-box">
    <button type="button" class="layui-btn" id="test1">
  		<i class="layui-icon">&#xe67c;</i>上传图片
	</button>
	
	<table class="layui-table">
		<thead>
			<tr>
				<td>文件名</td><td>图片</td><td>操作</td>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	
	</table>
	
  </div>
</fieldset>


<script type="text/javascript">
var code = '<%=request.getParameter("code")%>';
renderUpload("test1", "/EmployeeUploadServlet", {code:code}, function(res){
	//console.log(res);
	if(res.code==1){
		layer.msg("上传成功");
		showPic(res.image);
	}
	})
	
function init(){
	ajax('/EmployeeServlet',{action:'get', code:code},'json',function(data){
		//console.log(data);
		var image = data.image;
		if(image) showPic(image);
	})
}
init();
	
function showPic(image){
	var html = "<tr><td>"+image
	+"</td><td><img src='/temp11/"+image+"' class='layui-upload-img'>"
	+"</td><td><input type = 'button' onclick='delPic()' class='layui-btn' value='删除'>"
	+"</td>"
	+"</tr>"
	$("tbody").html(html);
}

function delPic(){
	layerConfirm(function(){
	ajax('/EmployeeServlet',{action:'delPic',code:code},'text',function(data){
			//console.log(data);
			if(data == 1){
				layer.msg("删除成功");
				$("tbody").html('');
			}else{
				layer.msg("删除失败");
			}
		})
	})
} 

</script>
</body>
</html>
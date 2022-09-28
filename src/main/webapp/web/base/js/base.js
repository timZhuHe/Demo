/**
 * 
 */

var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
var laytpl = layui.laytpl;
var laypage = layui.laypage;
var upload = layui.upload;

function formOnSubmit(event,url,dataType,func){
	form.on("submit("+event+")", function(data) {
		ajax(url, data.field, dataType,func)
	})
}

function ajax(url,field, dataType,func){
	$.ajax({
		url: base.app+ url,
		data:field, //a=b&c=d /{a:b,c:d}
		type:"post",
		dataType:dataType, //text / json (返回数据的类型)
		success:func
	});
}

function toHref(url){
	location.href=base.app+url;
}


function layerConfirm(func,title ) {
	 layer.confirm(title ? title : "确定执行该操作?", {
		icon:3,
		title:"提示",
	},func)
}


function layerOpen(url,end){
	layer.open({
		type:2,
		content: base.app + url,
		area: ['750px','500px'],
		fixed: false,
		maxmin:true,
		shade:0.6,
		closeBtn:1,
		shadeClose : false,
		//success:function(){},
		end: end,
	})
}

function layerClose(){
	//当你在iframe页面关闭自身时
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index);
}

function pageInfo(elem,count,curr,limit,jump){
	laypage.render({
		elem:elem,
		count:count,
		curr: curr,
		limit:limit,
		limits:[10,20,30,40], //每页显示几个数据
		layout:['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
		jump:jump
	})
}


function renderUpload(id,url,data,done){//将按钮渲染成可进行上传操作的按钮
	var uploadInst = upload.render({ //类似ajax请求
    elem: '#' + id, //绑定元素
    url: base.app+url, //上传接口
	data:data,//非文件域参数（上传过程中额外的参数）
    done: done,//success //上传完毕回调
  });
}



//

//

//

//

//










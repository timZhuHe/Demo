<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主界面</title>
<%@ include file = "/web/header.jsp" %>
</head>
<body>
http://127.0.0.1:8080//demo0111/web/page/main.jsp
主界面
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo layui-hide-xs layui-bg-black">JAVA-ZHUHE</div>
    <!-- 头部区域（可配合layui 已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <!-- 移动端显示 -->
      <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
        <i class="layui-icon layui-icon-spread-left"></i>
      </li>
      
      <li class="layui-nav-item layui-hide-xs"><a href="">nav 1</a></li>
      <li class="layui-nav-item layui-hide-xs"><a href="">nav 2</a></li>
      <li class="layui-nav-item layui-hide-xs"><a href="">nav 3</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">nav groups</a>
        <dl class="layui-nav-child">
          <dd><a href="">menu 11</a></dd>
          <dd><a href="">menu 22</a></dd>
          <dd><a href="">menu 33</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item layui-hide layui-show-md-inline-block">
        <a href="javascript:;">
          <img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" class="layui-nav-img">
          用户信息[${user.name}]
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">修改资料</a></dd>
          <dd><a href="">修改密码</a></dd>
        </dl>
      </li>
      
      <li class="layui-nav-item">
       <a href="javascript:toLogout();">退出</a>
      </li>
      
      <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
       
       <i class = "layui-icon layui-icon-more-vertical"></i>
      </li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">信息维护</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;" data-url="/web/page/department/list.jsp" class='zhuhe-demo-active'>部门信息维护</a></dd>
            <dd><a href="javascript:;" data-url="/web/page/employee/list.jsp" class='zhuhe-demo-active'>员工信息维护</a></dd>
            <dd><a href="javascript:;" data-url="/web/page/project/list.jsp" class='zhuhe-demo-active'>项目信息维护</a></dd>
            <dd><a href="javascript:;" data-url="/web/page/score/list.jsp" class='zhuhe-demo-active'>绩效维护</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">menu group 2</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:openUrl('/web/page/department/list.jsp');">list 1</a></dd>
            <dd><a href="javascript:;">list 2</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;">click menu item</a></li>
        <li class="layui-nav-item"><a href="">the links</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
   <iframe name = 'frameA' width='90%' height='97%'></iframe>
  </div>
  
  <div class="layui-footer">@JAVA_ZHUHE</div>
</div>
<script type="text/javascript">
$('.zhuhe-demo-active').click(function(){
	window.open(base.app + $(this).data("url"),'frameA')
})

function openUrl(url){
	window.open(base.app + url,'frameA')
}

function toLogout(){
	layerConfirm(function(){
		toHref('/EmployeeServlet?action=logout');
	}, "是否退出？");
}

</script>
</body>
</html>














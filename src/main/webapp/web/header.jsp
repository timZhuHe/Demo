

<% String app = request.getContextPath(); %>
<meta charset="UTF-8">

<link rel='stylesheet' href='<%=app %>/web/base/css/bass.css'>
<link rel='stylesheet' href='<%=app %>/web/base/layui/css/layui.css'>
<script type="text/javascript" src="<%=app %>/web/base/layui/layui.js"></script>         

<script type="text/javascript">
var base = {
		app: "${pageContext.request.contextPath}"
}
</script>

<script type="text/javascript" src='<%= app%>/web/base/js/base.js'></script>
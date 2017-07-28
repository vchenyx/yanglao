<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	basePath = "<%=basePath %>";
</script>
</head>
<body>
	<div class="layui-field-box">
		<table class="site-table table-hover">
			<thead>
				<tr>
					<th>服务项目</th>
					<th>服务内容</th>
					<th>服务价格</th>
					<th>服务单位</th>
					<th>服务标准</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="prov_tbd">
			</tbody>
		</table>

	</div>

	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	<script type="text/javascript">
	</script>
</body>
</html>
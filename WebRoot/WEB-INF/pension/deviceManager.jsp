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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构-设备管理-设备管理</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	var basePath = "<%=basePath %>";
	var guardianId = "${user.id}";
</script>
</head>
<body>
<div class="admin-main">
	<blockquote class="layui-elem-quote" style="border-left:5px solid #3983e1;">
			<a href="javascript:;" class="layui-btn layui-btn-small" onclick="addMESS();" style="background: #3983e1;">
				<i class="layui-icon">&#xe608;</i> 添加设备
			</a>
	</blockquote>
	<fieldset class="layui-elem-field">
		<legend>设备列表</legend>
		<div class="layui-field-box">
			<table class="site-table table-hover">
				<thead>
					<tr>
						<th>设备编号</th>
						<th>设备类型</th>
						<th>所属老人</th>
						<th>登记日期</th>
						<th>设备状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="show_tbd">
				</tbody>
			</table>
		</div>
	</fieldset>
	<!-- <div class="admin-table-page">
		<div id="page" class="page">
		</div>
	</div> -->
</div>
<script type="text/javascript" src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath %>back/map_js/hashmap.js"></script>
<script type="text/javascript" src="<%=basePath %>back/js/pension/deviceManager.js"></script>
</body>
</html>
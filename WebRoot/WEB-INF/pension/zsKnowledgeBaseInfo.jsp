<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>知识库信息</title>
<link rel="stylesheet"
	href="<%=basePath%>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath%>back/css/global.css"
	media="all">
<link rel="stylesheet" href="<%=basePath%>back/css/table.css" />
<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var pensionId = "${user.id }";
</script>
</head>
<body>
	<div class="admin-main">
		<blockquote class="layui-elem-quote"
			style="border-left:5px solid #3983e1;">
			<a href="javascript:;" class="layui-btn layui-btn-small" id="add"
				style="background: #3983e1;"> <i class="layui-icon">&#xe608;</i>
				添加 </a> <a href="javascript:;" class="layui-btn layui-btn-small"
				id="search" style="background: #3983e1;"> <i class="layui-icon">&#xe615;</i>
				查询 </a>
			<div style="height:0; overflow: hidden;" class="layui-form hidden">
				<div class="layui-form-item" style="margin-top:20px;">
					<label class="layui-form-label">类型名称：</label>
					<div class="layui-input-inline" style="width:200px;">
						<input id="name" name="name" type="text" value=""
							autocomplete="off" class="layui-input" style="width:200px;">
					</div>
					<div class="layui-input-inline" style="margin-left:40px;">
						<button class="layui-btn" lay-submit lay-filter="searchInfo"
							onclick="javascript:searchInfo();">查 询</button>
					</div>
				</div>
			</div>
		</blockquote>
		<fieldset class="layui-elem-field">
			<legend>信息列表</legend>
			<div class="layui-field-box">
				<table class="site-table table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" id="selected-all" />
							</th>
							<th>级别</th>
							<th>类型名称</th>
							<th>添加时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="content_list">
					</tbody>
				</table>

			</div>
		</fieldset>
		<div class="admin-table-page">
			<div id="page" class="page"></div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>back/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>back/plugins/layui/layui.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>back/js/pension/zsKnowledgeInfo.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>back/js/java-script.js"></script>
</body>
</html>
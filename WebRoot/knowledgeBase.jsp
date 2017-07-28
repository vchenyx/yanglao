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
<title>服务管理</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	var basePath = "${basePath}";
	var pensionId = "${user.id }";
</script>
</head>
<body>
<div class="admin-main">
	<blockquote class="layui-elem-quote" style="border-left:5px solid #3983e1;">
		<a href="javascript:;" class="layui-btn layui-btn-small" onclick="addMESS();" style="background: #3983e1;">
			<i class="layui-icon">&#xe608;</i> 添加
		</a>
		<a href="javascript:;" class="layui-btn layui-btn-small" id="search" style="background: #3983e1;"	>
			<i class="layui-icon">&#xe615;</i> 查询
		</a>
		<div style="height:0; overflow: hidden;" class="layui-form hidden">
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label">名称：</label>
				<div class="layui-input-inline" style="width:990px;">
					
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="searchInfo" onclick="javascript:searchInfo();">查 询</button>
				</div>
			</div>
		</div>
	</blockquote>
	<fieldset class="layui-elem-field">
		<legend>管理列表</legend>
		<div class="layui-field-box">
			<table class="site-table table-hover">
				<thead>
					<tr>
						<th>编号</th>
						<th>名称</th>
						<th>所属街区</th>
						<th>所属社区</th>
						<th>服务项目</th>
						<th>联系人</th>
						<th>电话</th>
						<th>地址</th>
						<th>星级</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="prov_tbd">
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
<script type="text/javascript" src="<%=basePath %>back/js/pension/oldProvider.js"></script>
</body>
</html>
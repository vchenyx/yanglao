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
	var basePath = "<%=basePath %>";
	var pensionId = "${user.id }";
</script>
</head>
	<body style="min-width:1395px;">
		<div class="admin-main">
		<blockquote class="layui-elem-quote" style="border-left:5px solid #3983e1;">
			<a href="javascript:;" class="layui-btn layui-btn-small" onclick="javascript:addMESS();">
				<i id="addButton" class="layui-icon">&#xe608;</i> 添加人员</a>
			<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
				<i class="layui-icon">&#xe615;</i> 按条件查询</a>
			<div style="height:0; overflow: hidden;" class="layui-form hidden">
				<div class="layui-form-item" style="margin-top:20px;">
				</div>
				<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label">人员姓名：</label>
				<div class="layui-input-inline" style="width:200px;">
					<input id="people_name" name="people_name" type="text" value="" autocomplete="off" class="layui-input" style="width:200px;  margin-right:500px;display:inline;">
				</div>
				<label class="layui-form-label">手机号：</label>
				<div class="layui-input-inline" style="width:200px;">
					<input id="cellphone" name="cellphone" type="text" value="" autocomplete="off" class="layui-input" style="width:200px;">
				</div>
				<div class="layui-input-inline" style="margin-left:40px;">
					<button class="layui-btn" lay-submit lay-filter="searchInfo" onclick="javascript:searchInfo();">查 询</button>
				</div>
				</div>
			</div>
		</blockquote>
		<fieldset class="layui-elem-field">
	<div class="operation2">
			</div>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>工单号</th>
								<th>下单人姓名</th>
								<th>联系电话</th>
								<th>受理部门</th>
								<th>接单人</th>
								<th>工单状态</th>
								<th>服务方式</th>
								<th>服务内容</th>
								<th>需求时间</th>
								<th>紧急程度</th>
								<th>付款方式</th>
								<th>工单备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="content_list">
						</tbody>
					</table>

				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="page" class="page">
				</div>
			</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="<%=basePath %>back/js/pension/undoneOrder.js"></script>
	</body>
</html>
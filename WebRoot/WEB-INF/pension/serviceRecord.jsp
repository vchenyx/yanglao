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
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员管理</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>

</head>
<body>
	<div class="admin-main">
		<blockquote class="layui-elem-quote"
			style="border-left:5px solid #3983e1;">
			<div class="admin-main">
				<a href="javascript:;" class="layui-btn layui-btn-small"
					onclick="importServiceRecords();" style="background: #3983e1;">
					<i class="layui-icon">&#xe608;</i> 导入
				</a>
				<div class="site-demo-upbar" style="display:none;"
					id="importServiceRecords">
					<input type="file" name="excel" lay-type="file"
						class="layui-upload-file">
				</div>
				<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 按条件查询
				</a>
				<div style="height:0; overflow: hidden;" class="layui-form hidden">
					<div class="layui-form-item" style="margin-top:20px;">
	
						<label class="layui-form-label">手机号：</label>
						<div class="layui-input-inline" style="width:180px;">
							<input id="cellphone" name="cellphone" type="text" value=""
								autocomplete="off" class="layui-input" style="width:180px;">
						</div>
						<label class="layui-form-label">姓名：</label>
						<div class="layui-input-inline" style="width:180px;">
							<input id="name" name="name" type="text" value=""
								autocomplete="off" class="layui-input" style="width:180px;">
						</div>
						<label class="layui-form-label">社区名称：</label>
						<div class="layui-input-inline" style="width:180px;">
							<input id="community" name="name" type="text" value=""
								autocomplete="off" class="layui-input" style="width:180px;">
						</div>
						<label class="layui-form-label">住址：</label>
						<div class="layui-input-inline" style="width:180px;">
							<input id="serviceAddress" name="name" type="text" value=""
								autocomplete="off" class="layui-input" style="width:180px;">
						</div>
						<div class="layui-input-inline" style="margin-left:40px;">
							<button class="layui-btn" lay-submit lay-filter="searchInfo"
								onclick="javascript:searchInfo();">查 询</button>
						</div>
					</div>
				</div>
			</div>
		</blockquote>
		<fieldset class="layui-elem-field">
			<!-- <div class="operation2"></div> -->
			<table class="site-table table-hover">
				<thead>
					<tr>
						<th>姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>联系电话</th>
						<th>社区</th>
						<th>托底/扶助</th>
						<th>服务商名称</th>
						<th>服务地址</th>
						<th>服务日期</th>
						<th>起止时间</th>
						<th>服务内容</th>
						<!-- <th>价格</th>
						<th>时长/数量</th> -->
						<th>消费合计</th>
						<th>服务反馈情况</th>
						<th>服务类型</th>
					</tr>
				</thead>
				<tbody id="content_list">
					<tr>

					</tr>
				</tbody>
			</table>
		</fieldset>
		<div class="admin-table-page">
			<div id="page" class="page" style="float: left;"></div>
			<div style="float:left; margin: 12px 0 0 10px;" class="totalPage">
				<p>共有<span class="totalCount"></span>条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有<span class="pageCount"></span>页</p >
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath %>back/js/myDate.js"></script>
	<script type="text/javascript"
		src="<%=basePath %>back/plugins/layui/layui.js"></script>
	<script type="text/javascript"
		src="<%=basePath %>back/js/pension/serviceRecord.js"></script>
</body>
</html>
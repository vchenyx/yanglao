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
<title></title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>
<style>
			ul li{margin-bottom: 5px;}
		</style>
</head>
<body>

<div class="admin-main">
			<fieldset class="layui-elem-field">
				<legend>医疗机构列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>机构名称</th>
								<th>机构地址</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>天创医疗机构</td>
								<td>海淀</td>
								<td>2015-02-03</td>
								<td>
									<a href="javascript:;" onclick="javascript:join(this);" class="layui-btn layui-btn-mini">申请加入</a>
								</td>
							</tr>
							<tr>
								<td>微软医疗机构</td>
								<td>海淀</td>
								<td>2015-06-08</td>
								<td>
									<a href="javascript:;" onclick="javascript:join(this);" class="layui-btn layui-btn-mini">申请加入</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</fieldset>
		</div>
</body>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/bindMedical.js"></script>
</html>
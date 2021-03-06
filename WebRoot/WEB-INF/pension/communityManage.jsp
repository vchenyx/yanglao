<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>人员管理</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>
</head>
	<body style="min-width:1395px;">
		<div class="admin-main">
		<blockquote class="layui-elem-quote" style="border-left:5px solid #3983e1;">
			<c:if test="${userType == 51}">
			<a href="javascript:;" class="layui-btn layui-btn-small" onclick="javascript:addMESS();">
				<i id="addButton" class="layui-icon">&#xe608;</i> 添加社区</a>
			</c:if>
		</blockquote>
		<fieldset class="layui-elem-field">
	<div class="operation2">
			</div>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>名称</th>
								<th>地址</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="content_list">
							<tr>
							</tr>
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
	<script type="text/javascript" src="<%=basePath %>back/js/pension/communityManager.js"></script>
	</body>
</html>
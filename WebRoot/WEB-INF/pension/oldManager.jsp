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
<title>机构--老人管理</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	var basePath = "<%=basePath %>";
	var pensionId = "${user.id }";
</script>
</head>
<body>
<div class="admin-main">
	<blockquote class="layui-elem-quote" style="border-left:5px solid #3983e1;">
		<c:if test="${userType == 51 || userType == 510 }">
		<a href="javascript:;" class="layui-btn layui-btn-small" onclick="addMESS();" style="background: #3983e1;">
			<i class="layui-icon">&#xe608;</i> 添加老人
		</a>
		</c:if>
		<a href="javascript:;" class="layui-btn layui-btn-small" id="search" style="background: #3983e1;"	>
			<i class="layui-icon">&#xe615;</i> 查询
		</a>
		<div style="height:0; overflow: hidden;" class="layui-form hidden">
			<div class="layui-form-item" style="margin-top:20px;">
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label">老人姓名：</label>
				<div class="layui-input-inline" style="width:200px;">
					<input id="name" type="text" value="" autocomplete="off" class="layui-input" style="width:200px;  margin-right:500px;display:inline;">
				</div>
				<label class="layui-form-label">身份证号：</label>
				<div class="layui-input-inline" style="width:200px;">
					<input id="idCard" type="text" value="" autocomplete="off" class="layui-input" style="width:200px;">
				</div>
				<label class="layui-form-label">联系电话：</label>
				<div class="layui-input-inline" style="width:200px;">
					<input id="phone" type="text" value="" autocomplete="off" class="layui-input" style="width:200px;">
				</div>
				<div class="layui-input-inline" style="margin-left:40px;">
					<button class="layui-btn" lay-submit lay-filter="searchInfo" onclick="javascript:searchInfo();">查 询</button>
				</div>
			</div>
		</div>
	</blockquote>
	<fieldset class="layui-elem-field">
		<legend>老人列表</legend>
		<div class="layui-field-box">
			<table class="site-table table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>身份证号</th>
						<th>手机号</th>
						<th>监护人姓名</th>
						<th>监护人电话</th>
						<th>住址</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="show_tbd">
				</tbody>
			</table>

		</div>
	</fieldset>
	<div class="admin-table-page">
		<div id="page" class="page" style="float:left;">
		</div>
		
		<div style="float:left; margin: 12px 0 0 10px;" class="totalPage">
			<p>共有<span class="totalCount"></span>条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有<span class="pageCount"></span>页</p >
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>back/js/myDate.js"></script>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath %>back/js/pension/oldManager.js"></script>
</body>
</html>
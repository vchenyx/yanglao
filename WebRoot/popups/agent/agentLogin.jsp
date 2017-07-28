<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
</head>
<body>
	<div class="layui-form-item" style="margin-top:20px;">
		<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>坐席号：</label>
		<div class="layui-input-block">
			<input type="text" id="agentNo" placeholder="请输入坐席号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
		</div>
	</div>
	<div class="layui-form-item" style="margin-top:20px;">
		<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>分机号：</label>
		<div class="layui-input-block">
			<input type="text" id="extensionNo" placeholder="请输入分机号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
		</div>
	</div>
</body>
</html>
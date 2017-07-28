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
<title>服务管理</title>
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
		<a href="javascript:;" class="layui-btn layui-btn-small" onclick="addPaim();" style="background: #3983e1;">
			<i class="layui-icon">&#xe608;</i> 添加
		</a>
		<a href="javascript:;" class="layui-btn layui-btn-small" onclick="importPaim();" style="background: #3983e1;">
			<i class="layui-icon">&#xe608;</i> 导入
		</a>
		</c:if>
		<div class="site-demo-upbar" style="display:none;" id="importPaim">
			<input type="file" name="excel" lay-type="file" class="layui-upload-file">
		</div>
		<a href="javascript:;" class="layui-btn layui-btn-small" id="search" style="background: #3983e1;"	>
			<i class="layui-icon">&#xe615;</i> 查询
		</a>
		<div style="height:0; overflow: hidden;" class="layui-form hidden">
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label">老人姓名：</label>
				<div class="layui-input-inline" style="width:80px;">
					<input id="name" type="text" value="" autocomplete="off" class="layui-input" style="width:80px;  margin-right:500px;display:inline;">
				</div>
				<label class="layui-form-label">老人年龄：</label>
				<div class="layui-input-inline" style="width:80px;">
					<input id="age" type="text" value="" autocomplete="off" class="layui-input" style="width:80px;  margin-right:500px;display:inline;">
				</div>
				<!-- <label class="layui-form-label">性别：</label>
				<div class="layui-input-inline" style="width:80px;">
					<input type="radio" value="0" id="sex"  name="sex" title="男"/>
					<input type="radio" value="1" id="sex1" name="sex" title="女"/>
				</div> -->
				<label class="layui-form-label">手机号：</label>
				<div class="layui-input-inline" style="width:80px;">
					<input id="phone" type="text" value="" autocomplete="off" class="layui-input" style="width:80px;">
				</div>
			<!-- </div>
			<div class="layui-form-item" style="margin-top:5px;"> -->
				<label class="layui-form-label">所属社区：</label>
				<div class="layui-input-inline" style="width:80px;">
					<input id="communitys" type="text" value="" autocomplete="off" class="layui-input" style="width:80px;">
				</div>
				<label class="layui-form-label">婚姻状况：</label>
				<div class="layui-input-inline" style="width:80px;">
					<input id="maritalStatus" type="text" value="" autocomplete="off" class="layui-input" style="width:80px;">
				</div>
				<label class="layui-form-label">收入：</label>
				<div class="layui-input-inline" style="width:80px;">
					<input id="income" type="text" value="" autocomplete="off" class="layui-input" style="width:80px;">
				</div>
				<!-- <label class="layui-form-label">类别：</label>
				<div class="layui-input-inline" style="width:80px;">
					<input type="radio" value="0" id="managerClass"  name="managerClass" title="托底"/>
					<input type="radio" value="1" id="managerClass1" name="managerClass" title="扶助"/>
				</div> -->
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="searchInfo" onclick="javascript:searchInfo();">查 询</button>
				</div>
			</div>
		</div>
	</blockquote>
	<fieldset class="layui-elem-field">
		<legend></legend>
		<div class="layui-field-box">
			<table class="site-table table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>身份证号</th>
						<th>出生年月</th>
						<th>年龄</th>
						<th>电话</th>
						<th>老人类别</th>
						<th>所属社区</th>
						<th>居住地址</th>
						<th>紧急联系人</th>
						<th>紧急联系人电话</th>
						<th>婚姻状况</th>
						<th>收入</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="pahe_tbd">
				</tbody>
			</table>

		</div>
	</fieldset>
	<div class="admin-table-page">
		<div id="page" class="page" style="float: left;"></div>
		<div style="float:left; margin: 12px 0 0 10px;" class="totalPage">
			<p>共有<span class="totalCount"></span>条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有<span class="pageCount"></span>页</p >
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath %>back/js/pension/PaimHelp.js"></script>
<script type="text/javascript" src="<%=basePath %>back/js/myDate.js"></script>
</body>
</html>
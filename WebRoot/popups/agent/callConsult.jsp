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
</head>
<body>
	<form class="layui-form">
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>工单号：</label>
				<div class="layui-input-block">
					<input type="text" id="orderNo" disabled="disabled" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>受理部门：</label>
				<div class="layui-input-block">
					<input type="text" disabled="disabled" id="handleDept" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>接单人：</label>
				<div class="layui-input-block">
					<input type="text" id="acceptPerson" lay-verify="pass" placeholder="请输入接单人" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;">接单时间：</label>
				<div class="layui-input-block">
					<input class="layui-input" id="acceptTime" placeholder="接单时间" onclick="layui.laydate({elem:this,istime:true,format:'YYYY-MM-DD hh:mm:ss'})" style="width:400px;"/>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>来电人姓名：</label>
				<div class="layui-input-block">
					<input type="text" id="linkName" placeholder="请输入来电人姓名" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>来电人电话：</label>
				<div class="layui-input-block">
					<input type="text" id="linkPhone" placeholder="请输入来电人电话" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>咨询内容：</label>
				<div class="layui-input-block">
					<textarea id="consultContent" style="width:400px; height:100px; margin-right:30px;"></textarea>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;">处理状态：</label>
				<div class="layui-input-block"  style="width:400px;">
					<select id="state">
	        			<option value="1">已处理</option>
	       				<option value="0">未处理</option>
	       			</select>
				</div>
			</div>
		
	</form>
	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	
	<script type="text/javascript">
		layui.use(['element', 'layer','form','laydate'], function() {
			var element = layui.element(),
				$ = layui.jquery,
				layer = layui.layer,
				laydate = layui.laydate,
				form = layui.form();
		});
		
	</script>
</body>
</html>
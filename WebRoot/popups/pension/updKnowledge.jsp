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
<link rel="stylesheet" href="<%=basePath%>back/plugins/layui/css/layui.css" media="all" />
<script type="text/javascript" src="<%=basePath%>back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>back/plugins/layui/layui.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>web/js/ProvinceAndCityJson.js"></script> --%>
<style>
.img {
	width: auto;
	height: 200px;
	float: left;
	margin-right: 10px;
	position: relative;
	overflow: hidden;
}

.img .face {
	display: none;
	width: 100%;
	height: 200px;
	position: absolute;
	top: 0;
	left: 0;
	background: #000;
	opacity: 0.4;
	filter: alpha(opacity = 40)
}

.img .del {
	display: none;
	width: 70px;
	height: 30px;
	position: absolute;
	top: 90px;
	left: 50%;
	margin-left: -35px;
	background: #009688;
	z-index: 2;
	text-align: center;
	color: #fff;
	line-height: 30px;
}

.img img {
	height: 200px;
}
</style>
<script type="text/javascript">
	basePath = "<%=basePath%>";
</script>
</head>
<body>
	<form class="layui-form">
		<input type="hidden" id="id">
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font
				color="red" style="size:2px">*</font>标题：</label>
			<div class="layui-input-block">
				<input type="text" id="name" placeholder="请输入标题"
					autocomplete="off" class="layui-input"
					style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font
				color="red" style="size:2px">*</font>类型名称：</label>
			<div class="layui-input-inline" style="width:400px;">
				<select lay-filter="yanglao" id="knowId" >
					<option value="0"></option>
				</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font
				color="red" style="size:2px">*</font>内容：</label>
			<div class="layui-input-block">
				<input type="text" id="content" placeholder="请输入内容"
					autocomplete="off" class="layui-input"
					style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font
				color="red" style="size:2px">*</font>阅读次数：</label>
			<div class="layui-input-block">
				<input type="text" id="readTimes" placeholder="请输入阅读次数"
					autocomplete="off" class="layui-input"
					style="width:400px; margin-right:30px;">
			</div>
		</div>
	</form>
 	<input type="hidden" id="inp">
	<script type="text/javascript"	src="<%=basePath%>back/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"	src="<%=basePath%>back/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		$(function(){
			layui.use([ 'element', 'layer', 'form' ],function() {
				var element = layui.element(),
					$ = layui.jquery, 
					form = layui.form(),
					layer = parent.layer === undefined ? layui.layer : parent.layer; 
				
				$("#inp").click(function() {
					form.render();
				});
			});
			
		});
	</script>
</body>
</html>
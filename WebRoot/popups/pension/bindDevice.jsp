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
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<style>
	.img{width:auto; height:200px; float:left; margin-right:10px; position: relative; overflow: hidden;}
	.img .face{display:none; width:100%; height:200px; position: absolute; top:0; left:0; background: #000; opacity: 0.4; filter:alpha(opacity=40)}
	.img .del{display:none; width:70px; height:30px; position: absolute; top:90px; left:50%; margin-left:-35px; background: #009688; z-index: 2; text-align: center; color:#fff; line-height: 30px;}
	.img img{height:200px;}
</style>
<script type="text/javascript">
	basePath = "<%=basePath %>";
</script>
</head>
<body>
	<form class="layui-form">
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>设备名称：</label>
			<div class="layui-input-block"  style="width:400px;">
				<select id="deviceName" >
        			<option value="">--请选择--</option>
        			<option value="0">--请选择--</option>
        			<option value="1">室外定位卡</option>
       				<option value="2">离床报警器</option>
       				<option value="3">体温设备</option>
       				<option value="4">呼吸设备</option>
       			</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>设备编号：</label>
			<div class="layui-input-block">
				<input type="text" id="deviceNo" placeholder="请输入设备编号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"></label>
			<div id="showDevice" class="layui-input-block">
			</div>
		</div>
		<div class="layui-form-item" style="margin:0 0 0 0;">
			<label class="layui-form-label" style="width:80px;"></label>
			<div class="layui-input-block">
				<a href="javascript:;"  style="margin:0 0 0 145px;" data-id="1" data-opt="del" onclick="addDevice()" class="layui-btn layui-btn-danger layui-btn-mini">添加设备</a>
			</div>
		</div>
		
	</form>


	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	<script type="text/javascript">
	</script>
	<script type="text/javascript">
		var form;
		layui.use(['element', 'layer','form'], function() {
			form = layui.form();
			var element = layui.element(),
				$ = layui.jquery,
				layer = layui.layer;
		});
		
		
		function addDevice() {
			var deviceName = $("#deviceName").val();
			var deviceNo = $("#deviceNo").val();
			if (deviceName == "" || deviceName == 0 || deviceNo == "" || deviceNo == "") {
				return;
			}
			var showDevice = $("#showDevice").html();
			showDevice += deviceName + "：" + deviceNo + "<br>";
			$("#showDevice").html(showDevice);
			$("#deviceNo").val("");
			$("#deviceName").val("0");
			form.render();
		}
	</script>
</body>
</html>
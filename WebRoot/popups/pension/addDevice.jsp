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
			<div class="layui-input-block"   style="width:450px;">
				<select name="deviceType" id="deviceType">
					<option>--请选择--</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>设备编号：</label>
			<div class="layui-input-block">
				<input type="text" id="deviceNo" placeholder="请输入设备编号" autocomplete="off" class="layui-input" style="width:450px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>所属老人：</label>
			<div class="layui-input-block"   style="width:450px;">
				<select name="oldId" id="oldId">
					<option>--请选择--</option>
				</select>
			</div>
		</div>
	</form>
	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	
	<script type="text/javascript">
		var layer;
		var form;
		layui.config({
			base: '<%=basePath %>manager/js/'
		}).use(['element', 'layer','form','upload'], function() {
			layer = parent.layer === undefined ? layui.layer : parent.layer;
			form = layui.form();
			var element = layui.element(),
				$ = layui.jquery;
		});
		
		$(function() {
			getAllDeviceType();
			getAllOldList();
		})
		
		function getAllDeviceType() {
			$.ajax({
				url: basePath + "device/getAllDeviceType.do",
				type: 'get',
				dataType: 'json',
				success: function(data) {
					if (data.length > 0) {
						var optionStr = '<option value="0">--请选择--</option>';
						for (var i = 0; i < data.length; i++) {
							var obj = data[i];
							optionStr += '<option value="' + obj.id + '">' + obj.deviceType + '</option>';
						}
						$("#deviceType").html(optionStr);
						form.render();
					} else {
						layer.alert("暂无设备类型，请先添加设备类型。", function() {
							layer.closeAll();
						});
					}
				}
			})
		}
		function getAllOldList() {
			$.ajax({
				url: basePath + "pension/getAllOldList.do",
				type: 'get',
				dataType: 'json',
				success: function(data) {
					if (data.length > 0) {
						var optionStr = '<option value="0">--请选择--</option>';
						for (var i = 0; i < data.length; i++) {
							var obj = data[i];
							optionStr += '<option value="' + obj.id + '">' + obj.name + '</option>';
						}
						$("#oldId").html(optionStr);
						form.render();
					} else {
						/* layer.alert("暂无设备类型，请先添加设备类型。", function() {
							layer.closeAll();
						}); */
					}
				}
			})
		}
	</script>
</body>
</html>
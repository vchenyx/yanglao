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
<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath %>web/js/ProvinceAndCityJson.js"></script>
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
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务类别：</label>
			<div class="layui-input-inline" style="width:470px;">
				<select lay-filter="yanglao" id="typeId">
					<option value="0">--请选择--</option>
       			</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>名称：</label>
			<div class="layui-input-block">
				<input type="text" id="name" placeholder="请输入服务商名称" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>地址：</label>
			<div class="layui-input-block">
				<input type="text" id="address" placeholder="请输入服务地址" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>联系人：</label>
			<div class="layui-input-block">
				<input type="text" id="linkName" placeholder="请输入联系人姓名" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>联系电话：</label>
			<div class="layui-input-block">
				<input type="text" id="linkPhone" placeholder="请输入联系人电话" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务范围：</label>
			<div class="layui-input-block">
				<input type="text" id="serviceScope" placeholder="请输入服务范围" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
	</form>
		<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
		<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
		<script type="text/javascript">
			var form;
			layui.config({
				base: '<%=basePath %>manager/js/'
			}).use(['element', 'layer','form','upload'], function() {
				form = layui.form();
				var element = layui.element(),
					$ = layui.jquery,
					layer = layui.layer,
					upload = layui.upload;
			});
			
			$(function() {
				getAllProviderType();
			})
			
			function getAllProviderType() {
				$.ajax({
					url: basePath + "provider/getAllProviderType.do",
					type: "post",
					dataType: "json",
					success: function(data) {
						var typeOption = "<option>--请选择--</option>";
						for (var i = 0; i < data.length; i++) {
							var obj = data[i];
							typeOption += "<option value='" + obj.id + "'>" + obj.name + "</option>";
						}
						$("#typeId").html(typeOption);
						form.render();
					}
				});
			}
			
		</script>
</body>
</html>
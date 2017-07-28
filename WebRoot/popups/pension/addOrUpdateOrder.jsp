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
	#aaa{display:none;}
</style>
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>
</head>
<body>
	<form class="layui-form">
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务单号：</label>
			<div class="layui-input-inline">
				<input type="text" disabled="disabled" id="orderId" autocomplete="off" class="layui-input" style="width:300px;">
			</div>
			
			<label class="layui-form-label" style="width:80px; margin-left:100px;"><font color="red" style="size:2px" >*</font>服务站点：</label>
			<div class="layui-input-inline">
				<input type="text" disabled="disabled" id="stationId" autocomplete="off" class="layui-input" style="width:300px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>老人姓名：</label>
			<div class="layui-input-inline">
				<input type="text"  disabled="disabled"  id="name" autocomplete="off" class="layui-input" style="width:300px;">
			</div>
			
			<label class="layui-form-label" style="width:80px; margin-left:100px;"><font color="red" style="size:2px" >*</font>联系电话：</label>
			<div class="layui-input-inline">
				<input type="text" disabled="disabled" id="phone" autocomplete="off" class="layui-input" style="width:300px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务地址：</label>
			<div class="layui-input-inline">
				<input type="text"  disabled="disabled"  id="address" placeholder="请输入服务地址" autocomplete="off" class="layui-input" style="width:300px;">
			</div>
			<label class="layui-form-label" style="width:80px; margin-left:100px;"><font color="red" style="size:2px" >*</font>服务日期：</label>
			<div class="layui-input-inline">
				<input type="text" disabled="disabled" id="dates" autocomplete="off" class="layui-input" style="width: 300px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务项目：</label>
			<div class="layui-input-inline">
				<input type="text"   disabled="disabled"  id="project" placeholder="请输入服务内容" autocomplete="off" class="layui-input" style="width:300px;">
			</div>
			
			<label class="layui-form-label" style="width:80px; margin-left:100px;"><font color="red" style="size:2px" >*</font>服务内容：</label>
			<div class="layui-input-inline">
				<input type="text" disabled="disabled" id="content" autocomplete="off" class="layui-input" style="width: 300px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务单位：</label>
			<div class="layui-input-inline">
				<input type="text" disabled="disabled" id="providerName" placeholder="请输入服务单位" autocomplete="off" class="layui-input" style="width:300px;">
			</div>
			
			<label class="layui-form-label" style="width:80px; margin-left:100px;"><font color="red" style="size:2px" >*</font>服务人：</label>
			<div class="layui-input-inline">
				<input type="text"  id="staffName"  placeholder="请输入服务人姓名" autocomplete="off" class="layui-input" style="width:300px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务方式：</label>
			<div class="layui-input-inline">
				<input type="text" id="serviceType" placeholder="请输入服务方式" autocomplete="off" class="layui-input" style="width: 300px;">
			</div>
			
			<label class="layui-form-label" style="width:80px; margin-left:100px;"><font color="red" style="size:2px" >*</font>起止时间：</label>
			<div class="layui-input-inline" style="width: 300px;">
				<input type="text"  id="startEndTime"  placeholder="请输入起止时间" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		
		<!-- <div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;">服务单价：</label>
			<div class="layui-input-inline">
				<input type="text" id="singlePrice" placeholder="请输入单价" autocomplete="off" class="layui-input" style="width:300px;">
			</div>
			
			<label class="layui-form-label" style="width:80px; margin-left:100px;">服务数量 ：</label>
			<div class="layui-input-inline">
				<input type="text"  id="serviceUnit" value="1" placeholder="请输入数量" autocomplete="off" class="layui-input" style="width:300px;">
			</div>
		</div> -->
		<div id="aaa" style="display：none;">
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>是否规范：</label>
				<div class="layui-input-inline" style="width:300px;">
					<select lay-filter="yanglao" id="standard" style="width:300px;">
							<option value="0">规范</option>
		        			<option value="1">不规范</option>
		       		</select>
				</div>
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>是否满意：</label>
				<div class="layui-input-inline" style="width:300px;">
					<select lay-filter="yanglao" id="state" style="width:300px;">
							<option value="0">很满意</option>
		        			<option value="1">基本满意</option>
		       				<option value="2">不满意</option>
		       		</select>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>意见：</label>
				<div class="layui-input-inline">
					<textarea  id="opinion" style="width:300px; min-height:100px;"></textarea>
				</div>
			</div>
		</div>
	</form>
	<input type="hidden" id="inp">
	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	
	<script type="text/javascript">
		var layer;
		var form;
		layui.config({
			base: '<%=basePath %>manager/js/'
		}).use(['element', 'layer','form','upload','laydate'], function() {
			layer = parent.layer === undefined ? layui.layer : parent.layer;
			form = layui.form();
			var element = layui.element(),
				$ = layui.jquery,
				laydate = layui.laydate,
				upload = layui.upload;
				
				
			$("#inp").click(function(){
				form.render();
			});
			
			
		});
	</script>
</body>
</html>
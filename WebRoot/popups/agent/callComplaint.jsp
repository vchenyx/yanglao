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
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>
</head>
<body>
	<form class="layui-form">
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>投诉单号：</label>
				<div class="layui-input-block">
					<input type="text" id="complaintNumbers" disabled="disabled" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:10px; margin-bottom: 0px">
				<label class="layui-form-label" style="width:80px;margin-top: 0px; padding-top: 0px"><font color="red" style="size:2px" >*</font>所属站点：</label>
				<div class="layui-input-inline"style="width:470px;">
					<select id="stationId">
	        			<option value="">--请选择--</option>
	       			</select>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;">接单时间：</label>
				<div class="layui-input-block">
					<input class="layui-input" id="acceptTime" placeholder="接单时间" onclick="layui.laydate({elem:this,istime:true,format:'YYYY-MM-DD hh:mm:ss'})" style="width:400px;"/>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>老人姓名：</label>
				<div class="layui-input-block">
					<input type="text" id="complaintPeople" placeholder="请输入来电人姓名" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>联系电话：</label>
				<div class="layui-input-block">
					<input type="text" id="cellphone" disabled="disabled" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:10px; margin-bottom: 0px">
				<label class="layui-form-label" style="width:80px;margin-top: 0px; padding-top: 0px"><font color="red" style="size:2px" >*</font>关联工单：</label>
				<div class="layui-input-inline"style="width:470px;">
					<select id="linkOrderNo">
	        			<option value="">--请选择--</option>
	        			<option value="0">--不关联--</option>
	       			</select>
				</div>
			</div>
			<!-- <div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务商名称：</label>
				<div class="layui-input-block">
					<input type="text" id="fuWuS" placeholder="请输入服务商名称" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务商电话：</label>
				<div class="layui-input-block">
					<input type="text" id="fuWuSCellphone" placeholder="请输入服务商电话" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div> -->
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>投诉内容：</label>
				<div class="layui-input-block">
					<textarea id="complaintContent" style="width:400px; height:100px; margin-right:30px;"></textarea>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;">处理状态：</label>
				<div class="layui-input-block"  style="width:400px;">
					<select id="manageState">
	        			<option value="0">未处理</option>
	        			<option value="1">正在处理</option>
	       				<option value="2">已处理</option>
	       			</select>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>处理办法：</label>
				<div class="layui-input-block">
					<textarea id="manageContent" style="width:400px; height:100px; margin-right:30px;"></textarea>
				</div>
			</div>
	</form>
	<input type="hidden" id="inp">
	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	
	<script type="text/javascript">
		var form;
		layui.use(['element', 'layer','form','laydate'], function() {
			form = layui.form();
			var element = layui.element(),
				$ = layui.jquery,
				layer = layui.layer,
				laydate = layui.laydate();
		});
		
		$(function() {
			getAllStation();
			//getLinkOrderContent();
		});
		
		$("#inp").click(function(){
			form.render();
		})
		
		
		//stationId
		function getAllStation() {
			$.ajax({
				url: basePath + "call/getAllStation.do",
				type: "post",
				data: {
					"random": Math.random()
				},
				async: false,
				dataType: "json",
				success: function(data) {
					var str = '<option value="">--请选择--</option>';
					for (var i = 0; i < data.length; i++) {
						var obj = data[i];
						str += '<option value="' + obj.id + '">' + obj.name + '</option>';
					}
					$("#stationId").html(str);
				}
			});
		}
		
		function getLinkOrderContent() {
			$.ajax({
				url: basePath + "order/getLinkOrder.do",
				type: "post",
				data: {
					"linkPhone": $("#cellphone").val(),
					"random": Math.random()
				},
				async: false,
				dataType: "json",
				success: function(data) {
					var str = '<option value="">--请选择--</option>';
					for (var i = 0; i < data.length; i++) {
						var obj = data[i];
						var proType = (obj.project == 0 ? '助餐' : obj.project == 1 ? '助医' : obj.project == 2 ? '助洁' : obj.project == 3 ? '助浴' : '');
						
						str += '<option value="' + obj.orderId + '">' + proType + obj.content + '</option>';
					}
					alert(str);
					$("#linkOrderNo").html(str);
					form.render();
				}
			});
		}
	</script>
</body>
</html>
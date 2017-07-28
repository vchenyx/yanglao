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
				<label class="layui-form-label" style="width:90px;"><font color="red" style="size:2px" >*</font>工单号：</label>
				<div class="layui-input-inline">
					<input type="text" id="complaintNumbers" disabled="disabled" placeholder="请输入工单号" autocomplete="off" class="layui-input" style="width:200px; ">
				</div>
				<label class="layui-form-label" style="width:90px; "><font color="red" style="size:2px" >*</font>受理部门：</label>
				<div class="layui-input-inline">
					<input type="text" id="handleDept" disabled="disabled" placeholder="请输入受理部门" autocomplete="off" class="layui-input" style="width:200px; ">
				</div>
				<label class="layui-form-label" style="width:90px; "><font color="red" style="size:2px" >*</font>接单人：</label>
				<div class="layui-input-inline">
					<input type="text" id="acceptPerson" disabled="disabled" lay-verify="pass" placeholder="请输入接单人" autocomplete="off" class="layui-input" style="width: 200px; ">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:90px;">接单时间：</label>
				<div class="layui-input-inline">
					<input  class="layui-input" disabled="disabled" id="acceptTime" placeholder="接单时间"  style="width:200px;"/>
				</div>
				<label class="layui-form-label" style="width:90px; "><font color="red" style="size:2px" >*</font>来电人姓名：</label>
				<div class="layui-input-inline">
					<input  type="text" id="complaintPeople" disabled="disabled" placeholder="请输入来电人姓名" autocomplete="off" class="layui-input" style="width: 200px;">
				</div>
				<label class="layui-form-label" style="width:90px; "><font color="red" style="size:2px" >*</font>来电人电话：</label>
				<div class="layui-input-inline">
					<input type="text" id="cellphone" disabled="disabled" placeholder="请输入来电人电话" autocomplete="off" class="layui-input" style="width: 200px; ">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:90px;"><font color="red" style="size:2px" >*</font>关联工单：</label>
				<div class="layui-input-inline">
					<input type="text" disabled="disabled" placeholder="请输入关联工单" autocomplete="off" class="layui-input" style="width: 200px; ">
				</div>
				<label class="layui-form-label" style="width:90px; "><font color="red" style="size:2px" >*</font>服务商名称：</label>
				<div class="layui-input-inline">
					<input type="text" id="fuWuS" disabled="disabled" placeholder="请输入服务商名称" autocomplete="off" class="layui-input" style="width: 200px; ">
				</div>
				<label class="layui-form-label" style="width:90px; "><font color="red" style="size:2px" >*</font>服务商电话：</label>
				<div class="layui-input-inline">
					<input type="text" id="fuWuSCellphone" disabled="disabled" placeholder="请输入服务商电话" autocomplete="off" class="layui-input" style="width: 200px; ">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:90px; "><font color="red" style="size:2px" >*</font>回访人：</label>
				<div class="layui-input-inline">
					<input type="text" id="returnVisitPeople" disabled="disabled" placeholder="请输入服务商名称" autocomplete="off" class="layui-input" style="width: 200px; ">
				</div>
				<label class="layui-form-label" style="width:90px; "><font color="red" style="size:2px" >*</font>回访时间：</label>
				<div class="layui-input-inline">
					<input type="text" id="returnVisitDate" disabled="disabled" placeholder="请输入服务商电话" autocomplete="off" class="layui-input" style="width: 200px; ">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:90px;"><font color="red" style="size:2px" >*</font>回访内容：</label>
				<div class="layui-input-block">
					<textarea name="" id="returnVisitContent" style="width: 838px; height:100px; "></textarea>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:90px;">回访状态：</label>
				<div class="layui-input-block"  style="width: 200px; margin-left:120px;">
					<select id="selectId">
	        			<option value="0" id="upd_edu1">未回访</option>
	        			<option value="1" id="upd_edu2">已回访</option>
	       			</select>
				</div>
			</div>
			
		
	</form>
	<input type="hidden" id="inp">
	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	
	<script type="text/javascript">
		layui.use(['element', 'layer','form','laydate'], function() {
			var element = layui.element(),
				$ = layui.jquery,
				layer = layui.layer,
				laydate = layui.laydate(),
				form = layui.form();
			$("#inp").click(function(){
				form.render();
			});
		});
		
	</script>
</body>
</html>
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
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>名称：</label>
				<div class="layui-input-block">
					<input type="text" id="name" placeholder="请输入姓名" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>性别：</label>
				<div class="layui-input-block">
					<input type="radio" value="0" id="sex"  name="sex" title="男" checked/>
					<input type="radio" value="1" id="sex1" name="sex" title="女"/>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>身份证号：</label>
				<div class="layui-input-block">
					<input type="text" id="idCrad" placeholder="请输入身份证号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>出生日期：</label>
				<div class="layui-input-block">
					<input class="layui-input" id="stateBirthday" placeholder="出生日期" onclick="layui.laydate({elem:this,istime:true})" style="width:470px;"/>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>年龄：</label>
				<div class="layui-input-block">
					<input type="text" id="age" placeholder="请输入年龄" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>电话：</label>
				<div class="layui-input-block">
					<input type="text" id="phone" placeholder="请输入电话" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>老人类别：</label>
				<div class="layui-input-block">
					<input type="radio" value="0" id="managerClass"  name="managerClass" title="托底" checked/>
					<input type="radio" value="1" id="managerClass1" name="managerClass" title="扶助"/>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>所属社区：</label>
				<div class="layui-input-inline" style="width:470px;">
					<input type="text" id="communitys" placeholder="请输入小区名称" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>居住地址：</label>
				<div class="layui-input-block">
					<input type="text" id="address" placeholder="请输入详细地址" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>紧急联系人：</label>
				<div class="layui-input-block">
					<input type="text" id="urgencyName" placeholder="请输入紧急联系人" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>紧急联系人电话：</label>
				<div class="layui-input-block">
					<input type="text" id="urgencyPhone" placeholder="请输入紧急联系人电话" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>婚姻状况：</label>
				<div class="layui-input-block">
					<input type="text" id="maritalStatus" placeholder="请输入婚姻状况" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>收入：</label>
				<div class="layui-input-block">
					<input type="text" id="income" placeholder="请输入收入状况" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
	</form>
		<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
		<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
		<script type="text/javascript">
			layui.config({
				base: '<%=basePath %>manager/js/'
			}).use(['element', 'layer','form','upload','laydate'], function() {
				var element = layui.element(),
					$ = layui.jquery,
					layer = layui.layer,
					upload = layui.upload,
					laydate = layui.laydate,
					form = layui.form();
			});
			
		</script>
</body>
</html>
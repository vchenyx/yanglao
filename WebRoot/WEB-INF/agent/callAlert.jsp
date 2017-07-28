<%@page import="com.common.util.global.ConfigureFile"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构--老人管理</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script src="http://maps.google.cn/maps?file=api&v=2&sensor=false&key=<%=ConfigureFile.google_key %>" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>back/js/agent/sight_spot.js"></script>

<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>
</head>
<body style="min-width:1360px;" onload="GoogleInitialize();" onunload="GUnload();">
<div class="admin-main">
	<blockquote class="layui-elem-quote" style="border-left:5px solid #3983e1; height: 50px">
		<div class="layui-form-item" style="margin-top:5px;">
			<div style="width: 49%; float: left;">
				<label class="layui-form-label">老人姓名：</label>
				<div class="layui-input-inline" style="width:150px;">
					<input type="text" value="张大爷" disabled="disabled" id="name" autocomplete="off" class="layui-input" style="width:150px;">
				</div>
				<label class="layui-form-label">来电号码：</label>
				<div class="layui-input-inline" style="width:150px;">
					<input type="text" value="13767667356" disabled="disabled" id="phone" autocomplete="off" class="layui-input" style="width:150px;">
				</div>
			</div>
			<div style="width: 49%; float: right;">
				<a href="javascript:;" class="layui-btn layui-btn-small" onclick="serviceOrder();" style="background: #3983e1;">
					<i class="layui-icon">&#xe608;</i> 服务工单
				</a>
				<!-- <a href="javascript:;" class="layui-btn layui-btn-small" onclick="serviceForward();" style="background: #3983e1;">
					<i class="layui-icon">&#xe608;</i> 服务预定
				</a> -->
				<!-- <a href="javascript:;" class="layui-btn layui-btn-small" id="search" onclick="serviceConsult();" style="background: #3983e1;"	>
					<i class="layui-icon">&#xe615;</i> 咨询
				</a> -->
				<a href="javascript:;" class="layui-btn layui-btn-small" id="search" onclick="complaint();" style="background: #3983e1;"	>
					<i class="layui-icon">&#xe615;</i> 投诉
				</a>
				<!-- <a href="javascript:;" class="layui-btn layui-btn-small" id="search" onclick="forwardRegist();" style="background: #3983e1;"	>
					<i class="layui-icon">&#xe615;</i> 预约挂号
				</a> -->
				<!-- <a href="javascript:;" class="layui-btn layui-btn-small" id="search" onclick="newSOS();" style="background: #3983e1;"	>
					<i class="layui-icon">&#xe615;</i> 紧急求助
				</a> -->
			</div>
		</div>
	</blockquote>
	<div style="float:left; width:55%;">	
		<fieldset class="layui-elem-field">
			<legend>老人信息</legend>
			<div style="margin: 10px 0 10px 10px;">
				<div style="width: 110px; float:left;">
					<div style="height: 160px">
						<img alt="" src="<%=basePath %>back/images/doctor1.png" width="100px">
					</div>
					<div>
						<a href="javascript:;" class="layui-btn layui-btn-small" id="search" onclick="forwardRegist();" style="background: #3983e1;"	>
							<i class="layui-icon">&#xe615;</i> 查看老人档案
						</a><br><br><br>
						<a href="javascript:;" class="layui-btn layui-btn-small" id="search" onclick="newSOS();" style="background: #3983e1;"	>
							<i class="layui-icon">&#xe615;</i> 查看呼入历史
						</a><br><br><br>
						<a href="javascript:;" class="layui-btn layui-btn-small" id="search" onclick="newSOS();" style="background: #3983e1;"	>
							<i class="layui-icon">&#xe615;</i> 查看服务记录
						</a>
					</div>
				</div>
				<div style="float:left;">
					<div class="layui-form-item" style="margin-top:20px; width: 600px">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>性别：</label>
						<div class="layui-input-inline" style="width:170px;">
							<input disabled="disabled" value="男" type="text" id="sex" autocomplete="off" class="layui-input" style="width:170px;">
						</div>
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>年龄：</label>
						<div class="layui-input-inline" style="width:170px;">
							<input disabled="disabled" value="89" type="text" id="age" autocomplete="off" class="layui-input" style="width:170px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px; width: 600px">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>居住情况：</label>
						<div class="layui-input-inline" style="width:170px;">
							<input disabled="disabled" value="两位老人" type="text" id="maritalStatus" autocomplete="off" class="layui-input" style="width:170px;">
						</div>
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>收入情况：</label>
						<div class="layui-input-inline" style="width:170px;">
							<input disabled="disabled" value="无" type="text" id="income" autocomplete="off" class="layui-input" style="width:170px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px; width: 600px">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>身份证：</label>
						<div class="layui-input-inline" style="width:460px;">
							<input disabled="disabled" value="110101192810112122" type="text" id="idCard" autocomplete="off" class="layui-input" style="width:460px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px; width: 600px">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>家庭住址：</label>
						<div class="layui-input-inline" style="width:460px;">
							<input disabled="disabled"  value="北京市东城区龙潭街道幸福北里5-1-12" type="text" id="address" autocomplete="off" class="layui-input" style="width:460px;">
						</div>
					</div>
					<!-- <div class="layui-form-item" style="margin-top:20px; width: 600px">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>失能情况：</label>
						<div class="layui-input-inline" style="width:170px;">
							<input disabled="disabled" type="text" id="name" placeholder="无" autocomplete="off" class="layui-input" style="width:170px;">
						</div>
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>残疾情况：</label>
						<div class="layui-input-inline" style="width:170px;">
							<input disabled="disabled" type="text" id="age" placeholder="听力残" autocomplete="off" class="layui-input" style="width:170px;">
						</div>
					</div> -->
					<div class="layui-form-item" style="margin-top:20px; width: 600px">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>监护人：</label>
						<div class="layui-input-inline" style="width:170px;">
							<input disabled="disabled"  value="张春华（弟弟）" type="text" id="linkName" autocomplete="off" class="layui-input" style="width:170px;">
						</div>
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>电话：</label>
						<div class="layui-input-inline" style="width:170px;">
							<input disabled="disabled" value="13779078935" type="text" id="linkPhone" autocomplete="off" class="layui-input" style="width:170px;">
						</div>
					</div>
				</div>
			</div>
		</fieldset>
	</div>
	<div style="float:right; width:43%;">
		<fieldset class="layui-elem-field" style="width: 100%; height: 400px">
			<legend>定位地图</legend>
			<div id="container" style="width:100%; height:400px; border:1px solid #ccc;">
				
           	</div>
		</fieldset>
	</div>
</div>
<script type="text/javascript" src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath %>back/js/agent/callAlert.js"></script>
</body>
</html>
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
<title></title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>
<style type="text/css">
			li{font-family: "微软雅黑"; margin-bottom: 5px;}
		</style>
</head>
<body>

<div class="admin-main">
			<fieldset class="layui-elem-field">
				<legend>服务列表</legend>
				<div class="layui-field-box">
					<div class="older" style="border:1px solid #ccc; float:left; width:350px; height:200px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/older1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li style="font-size: 16px; font-weight: bold;">助餐服务进家</li>
							<li>专业配送、保温流转、准时到家</li>
							<li>服务内容:现仅提供午餐</li>
							<li>收费标准：18元/20元/25元</li>
							<li>注：配送费2元</li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:350px; height:200px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/older1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li style="font-size: 16px; font-weight: bold;">助洁服务进家</li>
							<li>服务到位、洁净到家</li>
							<li>服务内容：深度保洁、拆洗空调、拆洗油烟机</li>
							<li>收费标准：100元</li>
							<li>注：根据保洁工作量合理调整价位</li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:350px; height:200px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/older1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li style="font-size: 16px; font-weight: bold;">助浴服务进家</li>
							<li>专业评估、专业养老护理员、自带专业设备</li>
							<li>服务内容:适用高龄、失能、办失能、卧床老人等人群</li>
							<li>收费标准：150元/次起</li>
							<li>注：根据老人失能程度合理调整价位</li>
						</ul>
					</div>
				</div>
			</fieldset>
		</div>
</body>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
</html>
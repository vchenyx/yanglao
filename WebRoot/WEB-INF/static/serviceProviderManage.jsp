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
</head>
<body>

<div class="admin-main">
			<blockquote class="layui-elem-quote" style="border-left:5px solid #3983e1;">
				<a href="javascript:;" class="layui-btn layui-btn-small" id="add" style="background: #3983e1;">
					<i class="layui-icon">&#xe615;</i> 添加服务
				</a>
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>服务列表</legend>
				<div class="layui-field-box" id="service">
					<div class="older" style="border:1px solid #ccc; float:left; width:350px; min-height:220px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/older1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li style="font-size: 16px; font-weight: bold;">助餐服务进家</li>
							<li>专业配送、保温流转、准时到家</li>
							<li>服务内容:现仅提供午餐</li>
							<li>收费标准：18元/20元/25元</li>
							<li>注：配送费2元</li>
							<li style="position: absolute; bottom:20px; left:112px;">
								<a href="javascript:;" class="layui-btn layui-btn-small" onclick=editService("助餐服务进家","专业配送、保温流转、准时到家","现仅提供午餐","18元/20元/25元","配送费2元","0") style="background: #3983e1;">
									编 辑
								</a>
								<a href="javascript:;" class="layui-btn layui-btn-small delate" style="background: #3983e1;">
									删 除
								</a>
							</li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:350px; min-height:220px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/older1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li class="serviceName" style="font-size: 16px; font-weight: bold;">助洁服务进家</li>
							<li class="serviceBiaoyu">服务到位、洁净到家</li>
							<li class="serviceContent">服务内容：深度保洁、拆洗空调、拆洗油烟机</li>
							<li class="serviceMoney">收费标准：100元</li>
							<li class="serviceCare">注：根据保洁工作量合理调整价位</li>
							<li style="position: absolute; bottom:20px; left:112px;">
								<a href="javascript:;" class="layui-btn layui-btn-small" onclick=editService("助洁服务进家","服务到位、洁净到家","深度保洁、拆洗空调、拆洗油烟机","100元","根据保洁工作量合理调整价位","1") style="background: #3983e1;">
									编 辑
								</a>
								<a href="javascript:;" class="layui-btn layui-btn-small delate" style="background: #3983e1;">
									删 除
								</a>
							</li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:350px; min-height:220px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/older1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li style="font-size: 16px; font-weight: bold;">助浴服务进家</li>
							<li>专业评估、专业养老护理员、自带专业设备</li>
							<li>服务内容:适用高龄、失能、办失能、卧床老人等人群</li>
							<li>收费标准：150元/次起</li>
							<li>注：根据老人失能程度合理调整价位</li>
							<li style="position: absolute; bottom:20px; left:112px;">
								<a href="javascript:;" class="layui-btn layui-btn-small" onclick=editService("助浴服务进家","专业评估、专业养老护理员、自带专业设备","150元/次起","根据老人失能程度合理调整价位","2") style="background: #3983e1;">
									编 辑
								</a>
								<a href="javascript:;" class="layui-btn layui-btn-small delate" style="background: #3983e1;">
									删 除
								</a>
							</li>
						</ul>
					</div>
				</div>
			</fieldset>
		</div>
</body>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/serviceProviderManage.js"></script>
</html>
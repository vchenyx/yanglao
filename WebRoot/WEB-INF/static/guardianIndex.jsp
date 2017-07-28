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
<style>
	ul li{margin-bottom: 5px;}
</style>
</head>
<body>

<div class="admin-main">
			<fieldset class="layui-elem-field">
				<legend>老人列表</legend>
				<div class="layui-field-box">
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:220px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/older1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li>姓 名：高淑敏</li>
							<li>年 龄：65</li>
							<li>病 症：高血压、糖尿病</li>
							<li>心 率：78次/分钟</li>
							<li>血 压：140/70mmhg</li>
							<li>呼 吸：15次</li>
							<li>
								<a href="javascript:;" class="layui-btn layui-btn-small" id="begin" style="background: #1AA094; position: absolute; bottom:10px; left:80px;">
									查看详情
								</a>
							</li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:220px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/older1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li>姓 名：张三丰</li>
							<li>年 龄：72</li>
							<li>病 症：心脏病、糖尿病</li>
							<li>心 率：82次/分钟</li>
							<li>血 压：150/80mmhg</li>
							<li>呼 吸：20次</li>
							<li>
								<a href="javascript:;" class="layui-btn layui-btn-small" id="begin" style="background: #1AA094; position: absolute; bottom:10px; left:80px;">
									查看详情
								</a>
							</li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:220px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/older1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li>姓 名：李国华</li>
							<li>年 龄：70</li>
							<li>病 症：无</li>
							<li>心 率：78次/分钟</li>
							<li>血 压：140/70mmhg</li>
							<li>呼 吸：15次</li>
							<li>
								<a href="javascript:;" class="layui-btn layui-btn-small" id="begin" style="background: #1AA094; position: absolute; bottom:10px; left:80px;">
									查看详情
								</a>
							</li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:220px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/older1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li>姓 名：张国焘</li>
							<li>年 龄：65</li>
							<li>病 症：颈椎病	</li>
							<li>心 率：78次/分钟</li>
							<li>血 压：140/70mmhg</li>
							<li>呼 吸：15次</li>
							<li>
								<a href="javascript:;" class="layui-btn layui-btn-small" id="begin" style="background: #1AA094; position: absolute; bottom:10px; left:80px;">
									查看详情
								</a>
							</li>
						</ul>
					</div>
				</div>
			</fieldset>
			<fieldset class="layui-elem-field">
				<legend>家庭列表</legend>
				<div class="layui-field-box">
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:190px; margin:0 20px 20px 0;">
						<ul style="margin:20px 0 10px 8px;">
							<li>单 元：二单元</li>
							<li>房 间：103</li>
							<li>水 浸：正常	</li>
							<li>煤 气：正常</li>
							<li>入 侵：正常</li>
							<li>烟 雾：正常</li>
							<li></li>
							<li></li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:190px; margin:0 20px 20px 0;">
						<ul style="margin:20px 0 10px 8px;">
							<li>单 元：三单元</li>
							<li>房 间：203</li>
							<li>水 浸：正常	</li>
							<li>煤 气：正常</li>
							<li>入 侵：正常</li>
							<li>烟 雾：正常</li>
							<li></li>
							<li></li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:190px; margin:0 20px 20px 0;">
						<ul style="margin:20px 0 10px 8px;">
							<li>单 元：五单元</li>
							<li>房 间：402</li>
							<li>水 浸：正常	</li>
							<li>煤 气：正常</li>
							<li>入 侵：正常</li>
							<li>烟 雾：正常</li>
							<li></li>
							<li></li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:190px; margin:0 20px 20px 0;">
						<ul style="margin:20px 0 10px 8px;">
							<li>单 元：一单元</li>
							<li>房 间：401</li>
							<li style="color:red;">水 浸：报警</li>
							<li>煤 气：正常</li>
							<li>入 侵：正常</li>
							<li>烟 雾：正常</li>
							<li></li>
							<li></li>
						</ul>
					</div>
				</div>
			</fieldset>
		</div>
</body>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/guardianIndex.js"></script>
</html>
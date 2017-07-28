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
<style>
ul li{margin-bottom: 5px;}
</style>
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>
</head>
<body>
<div class="admin-main">
			<fieldset class="layui-elem-field">
				<legend>医生列表</legend>
				<div class="layui-field-box">
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:190px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/doctor1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li>姓 名：李医生</li>
							<li>年 龄：65</li>
							<li>学 历：博士</li>
							<li>工作年限：40年</li>
							<li>主治病症：高血压、糖尿病</li>
							<li>
								<a href="javascript:;" class="layui-btn layui-btn-small" id="begin" onclick="javascript:bind(this);" style="position: absolute; bottom:10px; left:80px;">
									绑定
								</a>
							</li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:190px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/doctor1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li>姓 名：张医生</li>
							<li>年 龄：60</li>
							<li>学 历：博士</li>
							<li>工作年限：30年</li>
							<li>主治病症：心脏病、颈椎病</li>
							<li>
								<a href="javascript:;" class="layui-btn layui-btn-small" id="begin" onclick="javascript:bind(this);" style="position: absolute; bottom:10px; left:80px;">
									绑定
								</a>
							</li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:190px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/doctor1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li>姓 名：王医生</li>
							<li>年 龄：55</li>
							<li>学 历：博士</li>
							<li>工作年限：30年</li>
							<li>主治病症：高血压、糖尿病</li>
							<li>
								<a href="javascript:;" class="layui-btn layui-btn-small" id="begin" onclick="javascript:bind(this);" style="position: absolute; bottom:10px; left:80px;">
									绑定
								</a>
							</li>
						</ul>
					</div>
					<div class="older" style="border:1px solid #ccc; float:left; width:250px; height:190px; margin:0 20px 20px 0; position: relative;">
						<img style="width:100px; height:100px; float:right; margin:5px 5px 0 0" src="<%=basePath %>static/images/doctor1.png"/>
						<ul style="margin:20px 0 10px 8px;">
							<li>姓 名：陈医生</li>
							<li>年 龄：65</li>
							<li>学 历：博士</li>
							<li>工作年限：40年</li>
							<li>主治病症：心脏病</li>
							<li>
								<a href="javascript:;" class="layui-btn layui-btn-small" id="begin" onclick="javascript:bind(this);" style="position: absolute; bottom:10px; left:80px;">
									绑定
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
<script>
			function bind(dt){
				layui.use(['layer','form'], function() {
					$ = layui.jquery,
						form = layui.form,
						layer = parent.layer === undefined ? layui.layer : parent.layer;
					
					layer.confirm("确定绑定吗?",function(){
						layer.alert("审核中,请等待...",function(){
							$(dt).addClass("layui-btn-disabled");
							layer.closeAll();
						})
					})
					
					
				});
			}
		</script>
</html>
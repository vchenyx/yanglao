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
</style>
<script type="text/javascript">
	basePath = "<%=basePath %>";
</script>
<style>
	    	#edit1 p{word-wrap:break-word;}
	    	/* 线路图图片  */
	    	#line_img .big{width:514px; height:259px; position:relative; float:left; margin-right:2px;}
	    	#line_img .big .face{width:100%; height:259px; position:absolute; top:0; bottom:0; background:#000; opacity:0.5; z-index:1; display:none;}
	    	/* 景点图片  */
	    	#jing_dian_img .big{width:230px; height:110px; position:relative; float:left; margin-right:2px;}
	    	#jing_dian_img .big img{width:230px;}
	    	#jing_dian_img .big .face{width:100%; height:110px; position:absolute; top:0; bottom:0; background:#000; opacity:0.3; filter:alpha(opacity=40); z-index:1; display:none;}
	    </style>
</head>
<body>
	<form class="layui-form">
			<input type="hidden" id="id" name="id"  value=""/>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>所属部门：</label>
			<div class="layui-input-block">
				<input type="radio" value="站点" id="userDuty"  name="userDuty" title="站点" checked/>
				<input type="radio" value="客服" id="userDuty1" name="userDuty" title="客服"/>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
		 <input type="hidden" value="" id="peopleId"/>
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>类型名称：</label>
			<div class="layui-input-block">
				<input type="text" id="name"  lay-verify="pass" placeholder="请输入类型名称" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
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
		}).use(['element', 'layer','form','upload'], function() {
			layer = parent.layer === undefined ? layui.layer : parent.layer,
			upload = layui.upload,
			form = layui.form();
			var element = layui.element(),
				$ = layui.jquery;
			
			$("#inp").click(function(){
				form.render();
			});
		});
		
	</script>
</body>
</html>
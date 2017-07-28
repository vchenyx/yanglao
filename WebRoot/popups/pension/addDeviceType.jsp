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
</head>
<body>
	<form class="layui-form">
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>设备名称：</label>
			<div class="layui-input-block">
				<input type="text" id="deviceType" placeholder="请输入设备名称" autocomplete="off" class="layui-input" style="width:450px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>设备描述：</label>
			<div class="layui-input-block">
				<textarea id="desc" lay-verify="pass" placeholder="请输入内容" autocomplete="off" class="layui-textarea" style="width:450px; margin-right:30px;"></textarea>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>供应商：</label>
			<div class="layui-input-block">
				<input type="text" id="proFactory" placeholder="请输入供应商名称" autocomplete="off" class="layui-input" style="width:450px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>产品功能：</label>
			<div class="layui-input-block">
				<textarea id="proFunction" lay-verify="pass" placeholder="请输入产品功能" autocomplete="off" class="layui-textarea" style="width:450px; margin-right:30px;"></textarea>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;">设备照片：</label>
			<div class="layui-input-block">
				<input type="file" id="file" name="file" lay-type="images" class="layui-upload-file">
				<font color="red" style="size:2px" >支持png&nbsp;|&nbsp;jpg&nbsp;|&nbsp;jpeg格式</font>
				<div id="imageShow">
				
				</div>
			</div>
		</div>
		<input id="deviceImg" type="hidden">
	</form>
		<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
		<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
		
		<script type="text/javascript">
			layui.config({
				base: '<%=basePath %>manager/js/'
			}).use(['element', 'layer','form','upload'], function() {
				var element = layui.element(),
					$ = layui.jquery,
					layer = layui.layer,
					upload = layui.upload,
					form = layui.form();
					
				layui.upload({
					url: basePath + 'register/uploadFile.do',
					success: function(res){
						var showStr = '<a href="javascript:;" class="del">删 除</a><div class="face"></div><img src="'
						+ basePath + res.data + '" alt="" />';
						$("#imageShow").html(showStr);
						$("#deviceImg").val(res.data);
					}
				});
				
				//滑过图片
				$(".older_img").delegate('.img','mouseover',function(){
					$(this).find(".face").css("display","block")
					$(this).find(".del").css("display","block")
				});
				$(".older_img").delegate('.img','mouseout',function(){
					$(this).find(".face").css("display","none")
					$(this).find(".del").css("display","none")
				});
				
				//删除图片
				$(".older_img").delegate('.del','click',function(){
					$(this).parent().remove();
					$("#deviceImg").val("");
				});
			});
			
		</script>
</body>
</html>
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
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>姓名：</label>
				<div class="layui-input-block">
					<input type="text" id="peopleName" placeholder="请输入人员姓名" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>性别：</label>
				<div class="layui-input-block">
					<input type="radio" value="1" id="sex"  name="sex" title="男" checked/>
					<input type="radio" value="2" id="sex1" name="sex" title="女"/>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>年龄：</label>
				<div class="layui-input-block">
					<input type="text" id="age" placeholder="请输入人员年龄" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;">身份证号：</label>
				<div class="layui-input-block">
					<input type="text" id="idCard" placeholder="请输入人员身份证号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>手机号：</label>
				<div class="layui-input-block">
					<input type="text" id="cellphone" placeholder="请输入人员手机号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>家庭住址：</label>
				<div class="layui-input-block">
					<input type="text" id="address" placeholder="请输入家庭住址" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;">学历：</label>
				<div class="layui-input-block"  style="width:400px;">
					<select id="eduBackground" >
	        			<option value="1" id="upd_edu1">初中及以下</option>
	       				<option value="2" id="upd_edu2">高中</option>
	       				<option value="3" id="upd_edu3">大专</option>
	       				<option value="4" id="upd_edu4" selected="selected">本科</option>
	       				<option value="5" id="upd_edu5">研究生及以上</option>
	       			</select>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>上传图片：</label>
				<div class="site-demo-upload" style="width:150px; height:30px; float:left;">
					<div class="site-demo-upbar">
					  <input type="file" name="file" class="layui-upload-file" id="test">
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"></label>
				<div class="older_img" style="width:150px; height:150px; float:left;">
					<div class="img">
            			<!-- <a href="javascript:;" class="del">删 除</a>
            			<div class="face"></div>
                    	<img src="../images/login_bg.png" alt="" /> -->
            		</div>
				</div>
			</div>
		<input type="hidden" id="headImg" value="">
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
						$(".img").html(showStr);
						$("#headImg").val(res.data);
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
				});
				
				
			});
			
		</script>
</body>
</html>
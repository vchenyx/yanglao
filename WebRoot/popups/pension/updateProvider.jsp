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
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>编号：</label>
				<div class="layui-input-block">
					<input type="text" id="providerId" placeholder="请输入服务商编号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>名称：</label>
				<div class="layui-input-block">
					<input type="text" id="name" placeholder="请输入服务商名称" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>所属街道：</label>
				<div class="layui-input-block">
					<input type="text" id="block" placeholder="请输入街道" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>所属社区：</label>
				<div class="layui-input-block">
					<input type="text" id="community" placeholder="请输入社区" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务项目：</label>
				<div class="layui-input-inline" style="width:470px;">
					<select lay-filter="yanglao" id="project">
						<option value="0">--请选择服务--</option>
	        			<option value="1">生活照料服务</option>
	       				<option value="2">医疗服务</option>
	       				<option value="3">精神文化服务</option>
	       				<option value="4">安全类服务</option>
	       			</select>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>联系人：</label>
				<div class="layui-input-block">
					<input type="text" id="contact" placeholder="请输入联系人" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>电话：</label>
				<div class="layui-input-block">
					<input type="text" id="phone" placeholder="请输入电话" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>地址：</label>
				<div class="layui-input-inline place"  style="width:150px;">
		        	<select id="SelectProvince" name="SelectProvince" lay-filter="demo1" style="width:150px;">
		        		<option value=""></option>
		            </select>
				</div>
				<div class="layui-input-inline place"  style="width:150px;">
		            <select id="SelectCity" name="SelectCity" lay-filter="demo2" style="width:150px;">
		            	<option value=""></option>
		            </select>
				</div>
				<div class="layui-input-inline place"  style="width:150px;">
		            <select id="SelectArea" name="SelectArea" lay-filter="demo3" style="width:150px;">
		            	<option value=""></option>
		            </select>
				</div>
	    	</div>
	    	<div class="layui-form-item" style="margin-top:-10px;">
				<label class="layui-form-label" style="width:80px;"></label>
				<div class="layui-input-inline place"  style="width:150px;">
		        	<span><font color="red" style="size:2px" >* 地址请选择至旗县</font></span>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>星级：</label>
				<div class="layui-input-inline" style="width:470px;">
					<select lay-filter="yanglao" id="grade">
						<option value="0">--请选择等级--</option>
	        			<option value="1">一星级</option>
	       				<option value="2">二星级</option>
	       				<option value="3">三星级</option>
	       			</select>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>状态：</label>
				<div class="layui-input-block">
					<input type="radio" value="1" id="state"  name="state" title="已开启" checked/>
					<input type="radio" value="2" id="state" name="state" title="未开启"/>
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
				
				
			});
			
		</script>
</body>
</html>
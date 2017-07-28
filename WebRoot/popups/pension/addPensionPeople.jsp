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
		<!-- <div class="layui-form-item" style="margin-top:20px;">					
			<label class="layui-form-label" style="width:80px;">部门：</label>
			<div class="layui-input-block" style="width:400px;">
					<select id="department2" style="width:400px;">
						<option>--请选择--</option>
        			</select>
			</div>
		</div> -->
		<div class="layui-form-item" style="margin-top:20px;">
		 <input type="hidden" value="" id="peopleId"/>
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>姓名：</label>
			<div class="layui-input-block">
				<input type="text" id="peopleName" lay-verify="pass" placeholder="请输入人员姓名" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
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
			<label class="layui-form-label" style="width:80px;">年龄：</label>
			<div class="layui-input-block">
				<input type="text" id="age" lay-verify="pass" placeholder="请输入数字" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;">身份证号：</label>
			<div class="layui-input-block">
				<input type="text" id="idCard" lay-verify="pass" placeholder="请输入人员身份证号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>手机号：</label>
			<div class="layui-input-block">
				<input type="text" id="cellphone" lay-verify="pass" placeholder="请输入人员手机号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>职务描述：</label>
			<div class="layui-input-block" >
				<input type="text" id="duty" lay-verify="pass" placeholder="请输入人员职务" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:85px;">上传头像：</label>
			<input type="file" name="uploadFile" lay-type="image" class="layui-upload-file qwqw1" />
		</div>
		<div class="layui-form-item" style="margin-left:115px;" id="jing_dian_img">
			
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
		<div class="layui-input-block">
			<input type="radio" value="1" id="userDuty"  name="isAccount" title="生成账户" checked/>
			<input type="radio" value="2" id="userDuty1" name="isAccount" title="不生成账户"/>
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
			
			var html2 = "";
			layui.upload({
				ext:'jpg|png|gif',
				elem:'.qwqw1',
			    url:"<%=basePath %>pensionPeople/uploadImg.do",
				/* dataType:"json", */
			    before: function(input){
			    	
				},
			    success: function(json){ //上传成功后的回调
			    	var big = $("#jing_dian_img").find('.big').length;
			    	if(big >= 2){
			    		layer.alert("最多上传一张图片",{icon:5});
			    		return false;
			    	};
			    	if(json.state  =="exist"){
			    		layer.alert("上传图片不符合标准",{icon:5});
			    	}else if(json.state  =="error"){
			    		layer.alert("上传失败",{icon:5});
			    	}else{
			    		html2 = "<div class='big'>"
							+"<img src='<%=basePath %>"+json.imageUrl+"' />"
							+"<div class='face'></div>"
							+"<a href='javascript:;' onclick='del_jing_dian(this);' style='position:absolute; z-index:2; top:45%; left:45%; display:none;' class='layui-btn layui-btn-normal layui-btn-mini'>删除</a>"
							+"</div>"
						$("#jing_dian_img").html(html2); 	
			    	}
		    	}
			});
		});
		/* 删除景点图  */
		$("#jing_dian_img").delegate('a','click',function(){
			$(this).parent().remove();
		});
		/* 划过景点图片显示遮罩层、删除按钮  */
		$("#jing_dian_img").delegate('.big','mouseover',function(){
			$(this).find(".face").css("display","block");
			$(this).find("a").css("display","block");
		});
		$("#jing_dian_img").delegate('.big','mouseout',function(){
			$(this).find(".face").css("display","none");
			$(this).find("a").css("display","none");
		});
		
	</script>
</body>
</html>
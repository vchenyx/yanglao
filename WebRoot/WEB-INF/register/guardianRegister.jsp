<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="UTF-8">
	<title>监护人注册中心</title>
	<link rel="stylesheet" href="${basePath }web/css/reset.css" />
	<link rel="stylesheet" href="${basePath }web/js/plugins/layui/css/layui.css" />
	<link rel="stylesheet" href="${basePath }web/css/keeperRegist.css" />
	<link rel="stylesheet" href="${basePath }web/css/public.css" />
	
	<script type="text/javascript" src="${basePath }web/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${basePath }web/js/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="${basePath }web/js/ProvinceAndCityJson.js"></script>
	<script type="text/javascript" src="${basePath }web/keeperRegist.js"></script>
	<script type="text/javascript">
		var basePath = "${basePath}";
	</script>
</head>
<body>
<div class="container">
	<!--内容-->
	<div class="content">
		<!-- ****注册****-->
           <div class="register">
               <div class="left">
                   <p>-- 监护人注册信息  --</p>
                   <span id="error"></span>
                   <div class="inp_out">
                  	<span class="title">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
                  	<input id="name" class="inp" type="text" placeholder="请输入姓名"/>
                   </div>
                   <div class="inp_out">
                  	<span class="title">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span>
                  	<div class="sex">
	                  	<input type="radio" name="sex" value="1" />&nbsp;&nbsp;男
	                  	<input class="women" type="radio" name="sex" value="2" checked />&nbsp;&nbsp;女
                  	</div>
                   </div>
                   <div class="inp_out">
                   	<span class="title">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</span>
                    <input id="age" class="inp" type="number" min="1" placeholder="请输入年龄"/>
                   </div>
                   <div class="inp_out">
                   	<span class="title">身份证号：</span>
                    <input id="idCard" class="inp" type="text" placeholder="请输入身份证号"/>
                   </div>
                   <div class="inp_out">
                   	<span class="title">家庭住址：</span>
                   	<div id="place">
                    	<select id="SelectProvince" name="SelectProvince">
			            </select>
			            <select id="SelectCity" name="SelectCity">
			            </select>
			            <select id="SelectArea" name="SelectArea">
			            </select>
                   	</div>
                   </div>
                   <div class="inp_out">
                    <textarea id="address" class="textarea" name="place" placeholder="请输入详细地址"></textarea>
                   </div>
                   <div class="inp_out">
                   	<span class="title">联系电话：</span>
                    <input id="phone" class="inp" type="text" placeholder="请输入联系电话"/>
                   </div>
                   <div class="inp_out">
                   	<span class="title">密码：</span>
                    <input id="pwd" class="inp" type="password" placeholder="请输入密码"/>
                   </div>
                   <div class="inp_out">
                   	<span class="title">重复密码：</span>
                    <input id="pwd1" class="inp" type="password" placeholder="请重复密码"/>
                   </div>
                   <div class="inp_out">
                   	<span class="title">头像上传：</span>
                   	<div class="site-demo-upload" style="width:150px; height:30px; float:left; margin:10px 0 0 20px;">
						<div class="site-demo-upbar">
						  <input type="file" name="file" class="layui-upload-file" id="test">
						</div>
					</div>
                   </div>
                   <div class="inp_out add_img">
                   	<div class="img_out">
                   		<!-- <div class="img">
                   			<a href="javascript:;" class="del">删 除</a>
                   			<div class="face"></div>
	                    	<img src="images/doctor1.png" alt="" />
                   		</div> -->
                   	</div>
                   </div>
                   <input type="button" id="oBtn1" onclick="submit_info()" value="确认提交" />
               </div>
           </div>
           <input type="hidden" id="headImg" value="">
	</div>
</div>
</body>
<script>
	var layer;
	layui.use(['element', 'layer','upload'], function() {
		var element = layui.element(),
			layer = layui.layer;
			$ = layui.jquery,
			upload = layui.upload;
		layui.upload({
			url: basePath + 'register/uploadFile.do',
			success: function(res){
				var showStr = '<div class="img"><a href="javascript:;" class="del">删 除</a><div class="face"></div><img src="'
				+ basePath + res.data + '" alt="" /></div>';
				$(".img_out").html(showStr);
				$("#headImg").val(res.data);
			}
		}); 
	});
</script>
</html>

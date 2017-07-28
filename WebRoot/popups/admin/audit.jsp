<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../back/plugins/layui/css/layui.css" />
<link rel="stylesheet" href="../../back/css/through_open.css" />

<script type="text/javascript" src="../../back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../back/plugins/layui/layui.js"></script>
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
	                   <p>--机构申请信息--</p>
	                   <div class="inp_out">
	                  	<span class="title">机构名称：</span>
	                  	<input id="name" class="inp" type="text" disabled="disabled" />
	                   </div>
	                   <div class="inp_out">
	                   	<span class="title">机构地址：</span>
	                   	<textarea id="address" class="textarea" name="place" disabled="disabled"></textarea>
	                   </div>
	                   <div class="inp_out">
	                   	<span class="title">联系人姓名：</span>
	                    <input id="linkName" class="inp" type="text" disabled="disabled"/>
	                   </div>
	                   <div class="inp_out">
	                   	<span class="title">联系人电话：</span>
	                    <input id="linkPhone" class="inp" type="text" disabled="disabled"/>
	                   </div>
	                   <div class="inp_out">
	                   	<span class="title">机构创建时间：</span>
	                    <input id="creationTime" class="inp" type="text" disabled="disabled"/>
	                   </div>
	                   <div class="inp_out">
	                   	<span class="title">邮箱：</span>
	                    <input id="email" class="inp" style="float:left; margin-right:40px;" type="text" disabled="disabled"/>
	                   </div>
	                   <div class="inp_out add_img">
	                   	<span class="title">xx资质上传：</span>
	                   	<div id="str1" class="img_out">
	                   		<!-- <div class="img">
		                    	<img src="../images/doctor1.png" alt="" />
	                   		</div>
	                   		<div class="img">
		                    	<img src="../images/doctor1.png" alt="" />
	                   		</div> -->
	                   	</div>
	                   </div>
	                   <div class="inp_out add_img">
	                   	<span class="title">xx资质上传：</span>
	                   	<div id="str2" class="img_out">
	                   		<!-- <div class="img">
		                    	<img src="../images/doctor1.png" alt="" />
	                   		</div>
	                   		<div class="img">
		                    	<img src="../images/doctor1.png" alt="" />
	                   		</div> -->
	                   	</div>
	                   </div>
	                   <div class="inp_out add_img">
	                   	<span class="title">xx资质上传：</span>
	                   	<div id="str3" class="img_out">
	                   		<!-- <div class="img">
		                    	<img src="../../back/images/doctor1.png" alt="" />
	                   		</div>
	                   		<div class="img">
		                    	<img src="../../back/images/doctor1.png" alt="" />
	                   		</div> -->
	                   	</div>
	                   </div>
	                   <div class="inp_out">
	                   	<div id="through">
	                    	<input class="radio_through" type="radio" name="through" value="1" checked /> 信息齐全，审核通过
	                    	<input class="radio_through through_no" type="radio" name="through" value="2" /> 信息有误，退回
	                   	</div>
	                   </div>
	                   <div class="inp_out reason" style="display: none;">
	                   	<span class="title">填写退回原因：</span>
	                   	<textarea id="msg" class="textarea" name="place" placeholder="请填写退回原因"></textarea>
	                   </div>
	               </div>
	           </div>
		</div>
	</div>
	<input type="hidden" id="requestId" value="">
</body>
<script>
	layui.use(['element', 'layer','upload'], function() {
		var element = layui.element(),
			$ = layui.jquery,
			layer = layui.layer,
			upload = layui.upload;
		layui.upload({
			url: '上传接口url',
			success: function(res){
			  console.log(res); //上传成功返回值，必须为json格式
			}
		});
		
		//审核部通过时
		$("input[name='through']").eq(0).click(function(){
			$(".reason").css("display","none")
		})
		$("input[name='through']").eq(1).click(function(){
			$(".reason").css("display","block")
		})
	});
</script>
</html>
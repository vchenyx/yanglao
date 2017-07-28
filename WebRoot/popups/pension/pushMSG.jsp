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
<title>机构--老人管理</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	var basePath = "${basePath}";
	var pensionId = "${user.id}";
</script>
</head>
<body>
	<div class="admin-main">
		<form class="layui-form">
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;">选择人员：</label>
				<div class="layui-input-block"  style="width:400px;">
					<select id="nurseId" >
	        			<option value="0">--请选择--</option>
	       			</select>
				</div>
			</div>
		</form>
		
		<div class="layui-field-box">
			<textarea id="message" style="width:400px; margin-left:95px;" lay-verify="required" placeholder="请输入发送内容" class="layui-textarea"></textarea>
		</div>
	</div>
	<input type="hidden" id="inp">
	<script type="text/javascript" src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use(['element', 'layer','form'], function() {
			var element = layui.element(),
				layer = layui.layer,
				form = layui.form();
				$ = layui.jquery;
				
			$("#inp").click(function(){
				form.render();
			})
			
		});
		$(function() {
			getAllOnlineNurse();
		});
		
		function getAllOnlineNurse() {
			$.ajax({
				url: "<%=basePath %>informs/getOnlineNurse.do",
				type: 'post',
				dataType: 'json',
				success: function(data) {
					
					if (data.length > 0) {
						var str = "<option value=''>--请选择--</option>";
						for (var i = 0; i < data.length; i++) {
							var obj = data[i];
							var userId = obj.userId;
							var name = obj.name;
							str += "<option value='"+userId+"'>"+name+"</option>";
						}
						$("#nurseId").html(str);
						$("#inp").click();
					}
				}
			});
		}
		
	</script>
</body>
</html>
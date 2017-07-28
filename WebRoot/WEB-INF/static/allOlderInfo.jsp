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
</head>
<body>
<div class="admin-main">
			<fieldset class="layui-elem-field">
				<legend>所有老人信息</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>姓名</th>
								<th>性别</th>
								<th>年龄</th>
								<th>身份证号</th>
								<th>手机号</th>
								<th>监护人姓名</th>
								<th>监护人电话</th>
								<th>住址</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>魏淑芬</td>
								<td>女</td>
								<td>65</td>
								<td>123456789987645312</td>
								<td>15689475869</td>
								<td>张三</td>
								<td>15847859658</td>
								<td>海淀五路居</td>
							</tr>
							<tr>
								<td>张强</td>
								<td>男</td>
								<td>75</td>
								<td>548578789987645312</td>
								<td>156152486869</td>
								<td>张桂英</td>
								<td>15851248657</td>
								<td>昌平西山口</td>
							</tr>
						</tbody>
					</table>

				</div>
			</fieldset>
			<!--<div class="admin-table-page">
				<div id="page" class="page">
				</div>
				
			</div>-->
		</div>

</body>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
<script>
			layui.config({
				base: 'plugins/layui/modules/'
			});

			layui.use(['icheck', 'laypage','layer'], function() {
				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});
				
				//page
//				laypage({
//					cont: 'page',
//					pages: 25 //总页数
//						,
//					groups: 5 //连续显示分页数
//						,
//					jump: function(obj, first) {
//						//得到了当前页，用于向服务端请求对应数据
//						var curr = obj.curr;
//						if(!first) {
////							layer.msg('第 '+ obj.curr +' 页');
//						}
//					}
//				});

			});
		</script>
</html>
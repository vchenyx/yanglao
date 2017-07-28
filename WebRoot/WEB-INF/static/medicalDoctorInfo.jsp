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
			<blockquote class="layui-elem-quote" style="border-left:5px solid #3983e1;">
				<a href="javascript:;" class="layui-btn layui-btn-small" id="search" style="background: #3983e1;">
					<i class="layui-icon">&#xe615;</i> 查询
				</a>
				
				<div style="height:0; overflow: hidden;" class="layui-form hidden">
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label">姓名：</label>
						<div class="layui-input-inline" style="width:200px;">
							<input id="people_name" name="people_name" type="text" value="" autocomplete="off" class="layui-input" style="width:200px;  margin-right:500px;display:inline;">
						</div>
						
						<label class="layui-form-label">手机号：</label>
						<div class="layui-input-inline" style="width:200px;">
							<input id="cellphone" name="cellphone" type="text" value="" autocomplete="off" class="layui-input" style="width:200px;">
						</div>
						<label class="layui-form-label">身份证号：</label>
						<div class="layui-input-inline" style="width:200px; margin-right: 20px;">
							<input id="cellphone" name="cellphone" type="text" value="" autocomplete="off" class="layui-input" style="width:200px;">
						</div>
						<div class="layui-input-inline">
							<button class="layui-btn" lay-submit lay-filter="searchInfo" onclick="javascript:searchInfo();">查 询</button>
						</div>
					</div>
				</div>
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>医生审核列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>姓名</th>
								<th>性别</th>
								<th>年龄</th>
								<th>身份证号</th>
								<th>手机号</th>
								<th>学历</th>
								<th>从业年限</th>
								<th>主治</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>李晓明</td>
								<td>男</td>
								<td>68</td>
								<td>124562489754265897</td>
								<td>15555555555</td>
								<td>博士</td>
								<td>30年</td>
								<td>颈椎病、高血压</td>
								<td>
									<a href="javascript:;" onclick="javascript:check();" class="layui-btn layui-btn-mini">查看所属老人</a>
									<a href="javascript:;" onclick="javascript:del(this);" class="layui-btn layui-btn-mini">删除</a>
								</td>
							</tr>
							<tr>
								<td>高淑敏</td>
								<td>女</td>
								<td>65</td>
								<td>125421548954875125</td>
								<td>15624847852</td>
								<td>博士</td>
								<td>35年</td>
								<td>心脏病</td>
								<td>
									<a href="javascript:;" onclick="javascript:check();" class="layui-btn layui-btn-mini">查看所属老人</a>
									<a href="javascript:;" onclick="javascript:del(this);" class="layui-btn layui-btn-mini">删除</a>
								</td>
							</tr>
							<tr>
								<td>张光辉</td>
								<td>男</td>
								<td>66</td>
								<td>85425145245865856</td>
								<td>13956485248</td>
								<td>博士</td>
								<td>28年</td>
								<td>糖尿病、高血压</td>
								<td>
									<a href="javascript:;" onclick="javascript:check();" class="layui-btn layui-btn-mini">查看所属老人</a>
									<a href="javascript:;" onclick="javascript:del(this);" class="layui-btn layui-btn-mini">删除</a>
								</td>
							</tr>
						</tbody>
					</table>

				</div>
			</fieldset>
		</div>

</body>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/medicalDoctorInfo.js"></script>
</html>
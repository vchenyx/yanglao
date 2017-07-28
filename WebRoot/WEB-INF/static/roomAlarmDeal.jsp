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
				<a href="javascript:;" class="layui-btn layui-btn-small" id="search" style="background: #3983e1; margin-left:12px;"	>
					<i class="layui-icon">&#xe615;</i> 查询
				</a>
				<div style="height:0; overflow: hidden; border:1px solid transparent" class="layui-form hidden">
					<div class="layui-form-item" style="margin-top:40px;">
						<div class="layui-input-inline" style="width:350px; margin-left:20px;">
							<input id="people_name" placeholder="请输入需要查询的单元号" name="people_name" type="text" value="" autocomplete="off" class="layui-input" style="width:350px;display:inline;">
						</div>
						<div class="layui-input-inline" style="width:350px; margin-left:20px;">
							<input id="people_name" placeholder="请输入需要查询的房间号" name="people_name" type="text" value="" autocomplete="off" class="layui-input" style="width:350px;display:inline;">
						</div>
						<div class="layui-input-inline">
							<button style="height:34px; line-height: 34px; margin-top:2px;" class="layui-btn" lay-submit lay-filter="searchInfo" onclick="javascript:searchInfo();">查 询</button>
						</div>
					</div>
				</div>
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>床位列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>单元号</th>
								<th>房间号</th>
								<th>户主</th>
								<th>联系电话</th>
								<th>报警类型</th>
								<th>报警时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>三单元</td>
								<td>203</td>
								<td>张三丰</td>
								<td>13754869547</td>
								<td>烟雾报警</td>
								<td>2017-07-03 14:50</td>
								<td>
									<a href="javascript:;" class="layui-btn layui-btn-mini" onclick="javascript:deal(this);">处 理</a>
								</td>
							</tr>
							<tr>
								<td>一单元</td>
								<td>502</td>
								<td>高淑敏</td>
								<td>15645218569</td>
								<td>煤气报警</td>
								<td>2017-07-01 14:50</td>
								<td>
									<a href="javascript:;" class="layui-btn layui-btn-mini" onclick="javascript:deal(this);">处 理</a>
								</td>
							</tr>
							<tr>
								<td>二单元</td>
								<td>303</td>
								<td>李定国</td>
								<td>18745215689</td>
								<td>烟雾报警</td>
								<td>2017-07-02 14:50</td>
								<td>
									<a href="javascript:;" class="layui-btn layui-btn-mini" onclick="javascript:deal(this);">处 理</a>
								</td>
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
<script type="text/javascript" src="<%=basePath %>static/js/roomAlarmDeal.js"></script>
</html>
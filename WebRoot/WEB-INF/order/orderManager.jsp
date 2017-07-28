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
<title>工单管理</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>

</head>
	<body style="min-width:1395px;">
		<div class="admin-main">
		<blockquote class="layui-elem-quote" style="border-left:5px solid #3983e1;">
			<a href="javascript:;" class="layui-btn layui-btn-small" onclick="serviceOrder();" style="background: #3983e1;">
				<i class="layui-icon">&#xe608;</i> 服务工单
			</a>
			<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
				<i class="layui-icon">&#xe615;</i> 按条件查询</a>
			<a href="javascript:;" class="layui-btn layui-btn-small" onclick="getCount();" id="search">
				<i class="layui-icon">&#xe615;</i> 查询总数</a>
			<div style="height:0; overflow: hidden;" class="layui-form hidden">
				<div class="layui-form-item" style="margin-top:20px;">
					<label class="layui-form-label">老人姓名：</label>
					<div class="layui-input-inline" style="width:200px;">
						<input id="name" type="text" value="" autocomplete="off" class="layui-input" style="width:200px;  margin-right:500px;display:inline;">
					</div>
					<label class="layui-form-label">来电号码：</label>
					<div class="layui-input-inline" style="width:200px;">
						<input id="phone" type="text" value="" autocomplete="off" class="layui-input" style="width:200px;">
					</div>
				</div>
				<div class="layui-form-item" style="margin-top:20px;">
					<label class="layui-form-label">日期区间：</label>
					<div class="layui-input-inline" style="width:200px; margin-right:0;">
				    	<input class="layui-input" id="startDate" placeholder="请选择日期" onclick="layui.laydate({elem:this,istime:true,format:'YYYY-MM-DD'})" style="width:200px;"/>
					</div>
					<div class="layui-input-inline" style="width:200px; margin-right:0;">
				    	<input class="layui-input" id="endDate" placeholder="请选择日期" onclick="layui.laydate({elem:this,istime:true,format:'YYYY-MM-DD'})" style="width:200px;"/>
					</div>
					<div class="layui-input-inline" style="margin-left:40px;">
						<button class="layui-btn" lay-submit lay-filter="searchInfo" onclick="javascript:searchInfo();">查 询</button>
					</div>
				</div>
			</div>
		</blockquote>
		<fieldset class="layui-elem-field">
	<div class="operation2">
			</div>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>所属站点</th>
								<th>老人姓名</th>
								<th>联系电话</th>
								<th>服务内容</th>
								<th>总价</th>
								<th>配送费</th>
								<th>工单状态</th>
								<th>服务地址</th>
								<th>服务日期</th>
								<th>服务单位</th>
								<th>起止时间</th>
								<th>服务人姓名</th>
								<th>是否规范</th>
								<th>是否满意</th>
								<th>意见</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="content_list">
							<tr>
							</tr>
						</tbody>
					</table>

				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="page" class="page" style="float: left;"></div>
				<div style="float:left; margin: 12px 0 0 10px;" class="totalPage">
					<p>共有<span class="totalCount"></span>条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有<span class="pageCount"></span>页</p >
				</div>
			</div>
	</div>
	
	<script type="text/javascript" src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>back/js/myDate.js"></script>
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="<%=basePath %>back/js/order/orderManager.js"></script>
	<%-- <script type="text/javascript" src="<%=basePath %>back/js/agent/callAlert.js"></script> --%>
	</body>
</html>
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
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	basePath = "<%=basePath %>";
</script>
</head>
<body>
	<div class="layui-field-box">
		<div>共有&nbsp;<span id="totalCount"></span>&nbsp;条</div>
		<table class="site-table table-hover">
			<thead>
				<tr>
						<th>姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>联系电话</th>
						<th>社区</th>
						<th>托底/扶助</th>
						<th>服务商名称</th>
						<th>服务地址</th>
						<th>服务日期</th>
						<th>起止时间</th>
						<th>服务内容</th>
						<!-- <th>价格</th>
						<th>时长/数量</th> -->
						<th>消费合计</th>
						<th>服务反馈情况</th>
						<th>服务类型</th>
				</tr>
			</thead>
			<tbody id="prov_tbd">
			</tbody>
		</table>

	</div>

	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	<script type="text/javascript">
	</script>
</body>
</html>
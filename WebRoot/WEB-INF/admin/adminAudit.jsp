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
<link rel="stylesheet" href="${basePath }back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${basePath }back/css/global.css" media="all">
<link rel="stylesheet" href="${basePath }back/css/table.css" />
<script type="text/javascript">
	var basePath = "${basePath}";
</script>
</head>
<body>
<div class="admin-main">
			<fieldset class="layui-elem-field">
				<legend>审核列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>机构名称</th>
								<th>机构地址</th>
								<th>联系人姓名</th>
								<th>联系人电话</th>
								<th>邮箱</th>
								<th>申请时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="table_show">
						</tbody>
					</table>

				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="page" class="page">
				</div>
			</div>
		</div>
		<script src="${basePath }back/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${basePath }back/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="${basePath }back/js/admin/audit.js"></script>	
</body>
</html>

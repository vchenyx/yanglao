
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>bottom</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
		<div class="footContent">
			<p class="p2">联系我们</p><br />
			<p class="p2">服务热线：010-62698752-888 &nbsp;&nbsp;&nbsp;&nbsp;移动电话：13811398136&nbsp;&nbsp;&nbsp;传真：010-62698752-800</p>
			<p class="p2">Email：mafujun@jinkun-innovation.com &nbsp;&nbsp;&nbsp;&nbsp; 地址：北京市海淀区中关村彩和坊路8号天创科技大厦1207A-E</p>
			<p class="p1">北京金坤科创技术有限公司  版权所有  Copyright2013 京ICP备14033636号</p>
		</div>
  </body>
</html>

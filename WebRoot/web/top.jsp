<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>top</title>
    
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
    <div class="top">
		<div class="logo">
			<a href="<%=basePath%>"><img src="<%=basePath%>web/images/LOGO.png" alt="" /></a>
		</div>
		<div class="title">
			<p>居家养老平台</p>
		</div>
		<%-- <span><a href="${basePath }login/login.do">登录</a></span> --%>
		<div class="nav" id="menu">
	        <ul>
	            <li class="first"><a href="<%=basePath%>">首页</a></li>
	            <li><a href="http://www.jkzg.ren" target="_bank">健康中国</a></li>
	            <li><a href="<%=basePath %>web/safe2.jsp">安全中国</a></li>
	            <li><a href="http://www.jinkun-innovation.com/zh-cn" target="_bank">公司介绍</a></li>
	            <li><a href="<%=basePath %>web/about.jsp">关于我们</a></li>
	        </ul>
	    </div>
		
	</div>
  </body>
</html>

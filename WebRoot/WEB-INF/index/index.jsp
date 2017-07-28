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
<title>居家养老系统</title>
	<link rel="stylesheet" href="<%=basePath %>web/css/reset.css" />
	<link rel="stylesheet" href="<%=basePath %>web/css/index.css" />
</head>
<body>
<div class="container">
	<div class="content">
		
		<img id="logo" src="<%=basePath %>web/images/LOGO.png"/>
		<div class="logo">
		</div>
		<div class="banner">
			<img src="<%=basePath %>web/images/new_banner.png" alt="" />
		</div>
		<div class="login">
			<div class="d1">
				<div class="face"></div>
				<div class="font">
					<a href="<%=basePath %>login/toLogin.do?flag=5">服务站 ></a>
				</div>
			</div>
			<div class="d2 common">
				<div class="face"></div>
				<div class="font">
					<a href="<%=basePath %>login/toLogin.do?flag=1">监护人 ></a>
				</div>
			</div>
			<div class="d3 common">
				<div class="face"></div>
				<div class="font">
					<a href="<%=basePath %>login/toLogin.do?flag=3">医生 ></a>
				</div>
			</div>
			<div class="d4 common">
				<div class="face"></div>
				<div class="font">
					<a href="<%=basePath %>login/toLogin.do?flag=7">医疗机构 ></a>
				</div>
			</div>
			<div class="d5 common">
				<div class="face"></div>
				<div class="font">
					<a href="<%=basePath %>login/toLogin.do?flag=10">供应商 ></a>
				</div>
			</div>
			<div class="d6 common">
				<div class="face"></div>
				<div class="font">
					<a href="<%=basePath %>login/toLogin.do?flag=6">物业 ></a>
				</div>
			</div>
			<div class="d7 common">
				<div class="face"></div>
				<div class="font">
					<a href="<%=basePath %>login/toLogin.do?flag=8">民政局登录 ></a>
				</div>
			</div>
		</div>
	</div>
</div>		

<script src="<%=basePath %>web/js/jquery-1.9.1.min.js"></script>

<script>
	$(".font").hover(function(){
		$(this).css("background","#fff");
		$(this).find("a").css("color","#000");
	},function(){
		$(this).css("background","");
		$(this).find("a").css("color","#fff");
	})
</script>
</body>
</html>
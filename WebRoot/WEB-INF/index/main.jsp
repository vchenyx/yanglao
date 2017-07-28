<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>养老机构后台</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" />
<link rel="stylesheet" href="<%=basePath %>back/css/index.css" />
<script type="text/javascript">
	basePath = "<%=basePath %>";
	userFlag = "${userFlag}";
</script>
</head>
<body>
	<input type="hidden" id="callInPhone" value="">
	<div class="container">
			<div class="header">
				<div class="header_top">
					<div class="logo">
						<img style="display: inline;" src="<%=basePath %>back/images/logo1.png" alt="" />
						<img style="display: inline;" height="62px" src="<%=basePath %>back/images/xmalogo.png" alt="" />
					</div>
					<ul class="layui-nav admin-header-item" style="float:right; z-index: 9999999; background: transparent; margin-right:20px; margin-top:18px;">
						<li class="layui-nav-item">
							<a href="javascript:;" class="admin-header-user" id="username">
								<span>个人信息</span>
							</a>
							<dl class="layui-nav-child" id="infoList" style="width:260px; padding:10px;">
								<dd style="white-space: normal; color:#000; line-height: 16px;">
									<b>名称：</b><span>${user.name}</span>
								</dd>
								<hr />
								<dd style="white-space: normal; color:#000; line-height: 16px;">
									<b>类别：</b><span>${userType == 0 ? "老人" :  userType == 1 ? "监护人" :userType == 5 ? "养老中心" : userType == 9 ? "管理员" : ""}</span>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" class="admin-header-user" id="change">
								<i class="layui-icon">&#xe642;</i>
								<span>修改密码</span>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" class="admin-header-user" id="exit">
								<i class="layui-icon">&#xe64d;</i>
								<span>注销</span>
							</a>
						</li>
					</ul>
					<ul class="layui-nav admin-header-item-mobile">
						<li class="layui-nav-item">
							<a href="javascript:;"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
						</li>
					</ul>
				</div>
				<div class="header_bottom">
					<div style="float:right; margin-right:50px; position: relative; z-index: 999999;" id="admin-navbar-side" lay-filter="side"></div>
				</div>
			</div>
			<div class="content" style="background: #fff;">
				<div class="layui-body" style="position: absolute; left:0; top:0; bottom: 0;" id="admin-body">
					<div class="layui-tab admin-nav-card layui-tab-brief"  lay-allowClose="true" lay-filter="admin-tab">
						
						<div class="admin-side-toggle" title="侧边栏切换" id="show" style="width：150px; height:auto; float:left; margin:5px 10px 0 10px; position: relative; z-index: 999; cursor:pointer;">
							<i class="layui-icon" style="font-size: 25px;">
								&#xe619;
							</i>
						</div>
						
						<ul class="layui-tab-title" id="tabTitle">
							<li class="layui-this">
								<%-- <cite style="padding-right:10px;"> --%>
								首页
								<%-- </cite> --%>
								<i class="layui-icon layui-unselect layui-tab-close" data-id="0"></i>
							</li>
							<%-- <li class="">
								<cite style="padding-right:10px;">来电弹屏</cite>
							</li> --%>
						</ul>
						<div class="layui-tab-content" id="tabContent" style="min-height: 150px; padding: 5px 0 0 0;">
							<div class="layui-tab-item layui-show">
								<iframe src="<%=basePath %>turn/chooseMain.do"></iframe>
							</div>
							<%-- <div class="layui-tab-item">
								<iframe src="<%=basePath %>turn/callAlert.do"></iframe>
							</div> --%>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="<%=basePath %>back/datas/nav.js"></script>
		<script type="text/javascript" src="<%=basePath %>back/js/main.js"></script>
</bod>
</html>
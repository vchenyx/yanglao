<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>物业注册中心</title>
		<link rel="stylesheet" href="${basePath}/web/css/reset.css" />
		<link rel="stylesheet" href="${basePath}/web/css/propertyRegist.css" />
		<link rel="stylesheet" href="${basePath}/web/css/public.css" />
		
		<script src="${basePath}js/jquery-1.9.1.min.js"></script>
		<script src="${basePath}/web/js/register/communityRegist.js"></script>
	</head>
	<body>
		<div class="container">
			
			<!--头部-->
			<div class="header">
				<jsp:include page="../../../web/top.jsp" />
			</div>
			
			<!--内容-->
			<div class="content">
				
				<!-- ****注册****-->
				<form action="${basePath}register/registerCommunity.do" method="post" name="communityForm">
		        <div class="register">
		            <div class="register1">
		                <div class="left">
		                    <p>--物业注册--</p>
		                    <span id="error"style="display:block;"></span>
		                    <input type="text" id="communityName" placeholder="请输入机构名称" name="communityName" onblur="javascript:communityNameTest();"/>
		                    <input type="text" id="communityAddr" placeholder="请输入地址" name="communityAddr" onblur="javascript:communityAddrTest();"/>
		                    <input type="text" id="communityPhoneName" placeholder="请输入联系人姓名" name="communityPhoneName" onblur="javascript:communityPhoneNameTest();"/>
		                    <input type="text" id="communityPhone" maxlength="11" placeholder="请输入联系人电话" name="communityPhone" onblur="javascript:communityPhoneTest();"/>
		                    <input type="password" id="password" placeholder="请设置登录密码" name="password" onblur="javascript:passwordTest();"/>
		                    <input type="password" id="passwords" placeholder="确认登录密码" name="passwords" onblur="javascript:passwordsTest();"/>
		                    <input type="button" id="oBtn1" onclick="registerCommunity()" value="确认提交" />
		                </div>
		            </div>
		        </div>
		        </form>
				
				<!--底部-->
				<div class="footer">
					<jsp:include page="../../../web/bottom.jsp" />	
				</div>
				
			</div>
		</div>		
	</body>
</html>

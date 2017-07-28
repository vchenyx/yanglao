<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>服务商注册中心</title>
		<link rel="stylesheet" href="${basePath}/web/css/reset.css" />
		<link rel="stylesheet" href="${basePath}/web/css/serviceRegist.css" />
		<link rel="stylesheet" href="${basePath}/web/css/public.css" />
		
		<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script src="${basePath}/web/js/register/providerRegist.js"></script>
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
				<form action="${basePath}register/registerProvider.do" method="post" name="providerForm">
		        <div class="register">
		            <div class="register1">
		                <div class="left">
		                    <p>--服务商注册信息--</p>
		                    <span id="error" style="display:block;"></span>
		                    <input type="text" id="providerName" placeholder="请输入机构名称" name="providerName" onblur="javascript:providerNameTest();"/>
		                    <input type="text" id="providerAddr" placeholder="请输入地址" name="providerAddr" onblur="javascript:providerAddrTest();"/>
		                    <input type="text" id="providerPhoneName" placeholder="请输入联系人姓名" name="providerPhoneName" onblur="javascript:providerPhoneNameTest();"/>
		                    <input type="text" id="providerPhone" maxlength="11" placeholder="请输入联系人电话" name="providerPhone" onblur="javascript:providerPhoneTest();"/>
		                    <input type="password" id="password" placeholder="请设置登录密码" name="password" onblur="javascript:passwordTest();"/>
		                    <input type="password" id="passwords" placeholder="确认登录密码" name="passwords" onblur="javascript:passwordsTest();"/>
		                    <input type="button" id="oBtn1" onclick="registerProvider()" value="确认提交" />
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


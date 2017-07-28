<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>医疗机构注册中心</title>
		<link rel="stylesheet" href="${basePath}/web/css/reset.css" />
		<link rel="stylesheet" href="${basePath}/web/css/doctorRegist.css" />
		<link rel="stylesheet" href="${basePath}/web/css/public.css" />
		
		<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script src="${basePath}/web/js/register/medicalRegist.js"></script>
		<script type="text/javascript">
		if("${MESS}" != ""){
			alert("${MESS}");
		}
		</script>
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
				<form action="${basePath}register/registerMedical.do" method="post" name="medicalForm">
		        <div class="register">
		            <div class="register1">
		                <div class="left">
		                    <p>--医疗机构注册--</p>
		                    <span id="error" style="display:block;"></span>
		                    <input type="text" placeholder="请输入机构名称" name="medicalName" id="medicalName" onblur="javascript:medicalNameTest();"/>
		                    <input type="text" placeholder="请输入地址" name="medicalAddr" id="medicalAddr" onblur="javascript:medicalAddrTest();"/>
		                    <input type="text" placeholder="请输入联系人姓名" name="medicalPhoneName" id="medicalPhoneName" onblur="javascript:medicalPhoneNameTest();"/>
		                    <input type="text" placeholder="请输入联系人电话" maxlength="11" name="medicalPhone" id="medicalPhone" onblur="javascript:medicalPhoneTest();"/>
		                    <input type="password" placeholder="请设置登录密码" name="password" id="password" onblur="javascript:passwordTest();"/>
		                    <input type="password" placeholder="确认登录密码" name="passwords" id="passwords" onblur="javascript:passwordsTest();"/>
		                    <input type="button" id="oBtn1" onclick="registerMedical()" value="确认提交" />
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
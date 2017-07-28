<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8" />
		<title>老人注册中心</title>
		<link rel="stylesheet" href="${basePath}web/css/reset.css" />
		<link rel="stylesheet" href="${basePath}web/css/olderRegist.css" />
		<link rel="stylesheet" href="${basePath}web/css/public.css" />
		
		<script src="${basePath}js/jquery-1.9.1.min.js"></script>
		<script src="${basePath}web/js/register/elderlyRegist.js"></script>
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
				<jsp:include page="../../web/top.jsp" />
			</div>
			
			<!--内容-->
			<div class="content">
				
				<!-- ****注册****-->
				<form action="${basePath}register/registerElderly.do" name="elderlyForm" method="post">
			        <div class="register">
			            <div class="register1">
			                <div class="left">
			                    <p>--老人注册--</p>
			                    <span id="error" style="display:block;"></span>
			                    <input type="text" id="realname" placeholder="请输入姓名" name="realname" onblur="javascript:realnameTest();"/>
			                    <input type="text" id="telephone" maxlength="11" placeholder="请输入手机号" name="telephone" onblur="javascript:telephoneTest();"/>
			                    <input type="text" id="age" placeholder="请输入年龄" name="age" onblur="javascript:ageTest();"/>
			                    <input type="password" id="password" placeholder="请设置登录密码" name="password" onblur="javascript:passwordTest();"/>
			                    <input type="password" id="passwords" placeholder="确认登录密码" name="passwords" onblur="javascript:passwordsTest();"/>
			                    <input type="button" id="oBtn1" onclick="registerELderly()" value="确认提交" />
			                </div>
			            </div>
			        </div>
				</form>
				
				<!--底部-->
				<div class="footer">
					<jsp:include page="../../web/bottom.jsp" />	
				</div>
				
			</div>
		</div>		
	</body>
</html>

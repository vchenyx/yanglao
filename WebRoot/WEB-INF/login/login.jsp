<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
    <title>用户登录</title>

    <link rel="stylesheet" href="<%=basePath %>web/css/login.css"/>
    <link rel="stylesheet" href="<%=basePath %>web/css/public.css" />

    <script src="<%=basePath %>web/js/jquery-1.9.1.min.js"></script>
    <%-- <script src="<%=basePath %>web/js/login.js"></script> --%>

</head>
<body>
<div class="container" id="login">

    <!-- ****头部********-->
   <%--  <div class="header">
        <jsp:include page="../../web/top.jsp" />
    </div> --%>

    <!-- ***********内容区************-->
    <div class="content">
		<div class="center">
			<div class="center_1">
				<div class="left">
					<img src="<%=basePath %>web/images/LOGIN_BG.png" alt="" />
				</div>
				<div class="right">
					<div class="login_infor">
						<p>管理员登录</p>
						<div class="form">
							<span class="sp1">请输入账号密码</span>
           	 				<span class="sp2" >${MESS }</span>
							<input class="inp1" type="text" maxlength="11" placeholder="请输入账号" id="account"/>
							<input class="inp2" type="password" placeholder="请输入密码" id="pwd"/>
							<input class="oBtn" type="button" onclick="userLogin()" value="登 录" />
							<!-- <input class="oBtn" style='margin-top:-15px; background:#fff; height:15px; color:#666; cursor:pointer;' type="button" value="立即注册" onclick="javascript:toRegister();"/> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ****底部********-->
		<%-- <div class="footer" style="font-size: 14px;">
			<jsp:include page="../../web/bottom.jsp" />
		</div> --%>
	</div>

</div>
<script type="text/javascript">
	flag = "${flag}";
	function toRegister() {
		location.href = "<%=basePath %>register/toRegister.do?flag=" + flag;
	}
	
	function userLogin() {
		$.ajax({
			url: "<%=basePath %>login/login.do",
			data: {
				"account": $("#account").val(),
				"pwd": $("#pwd").val(),
				"userType": flag
			},
			type: "post",
			dataType: "json",
			success: function(data) {
				var user = data.user;
				var token = data.token;
				var userType = user.userType;
				//document.cookie = "proof=" + token;
				location.href = "<%=basePath %>turn/loginSuccess.do?flag=" + userType;
			}
		});
	}
	
</script>

</body>
</html>























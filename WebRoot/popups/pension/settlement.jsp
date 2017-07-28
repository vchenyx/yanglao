<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		*{font-size:8px;}
		table {
			width: 100%;
			border-collapse: collapse; /*border:none*/
		}

		table tr td {
			border: 1px solid #000;
			height: 25px;
			width: 20%; /*color:transparent; border:none;*/
		}
		table tr td span{
			display: block;
			margin-bottom: 8px;
		}
	</style>
	
  </head>
  
  <body onload="initBody()">
  
  <div style="width: 100%">
  	<table width="100%" cellpadding="0" cellspacing="0" border="1px solid black">
  		<tr height="30px">
  			<td colspan="4" style="text-align: center;">
  				<img width="28px" style="font-size: 0; vertical-align: middle;"  src="<%=basePath %>back/images/xmalogo.jpg">
  				<span style="font-size: 20px; display: inline; font-weight: bold; vertical-align: middle;">小棉袄爱老居家养老服务记录表</span>
  			</td>
  		</tr>
  		<tr>
  			<td width="15%" align="center">老人姓名</td><td width="35%" class="name"></td><td width="15%" align="center">联系电话</td><td width="35%" class="phone"></td>
  		</tr>
  		<tr>
  			<td align="center">服务内容</td><td colspan="3" class="content"></td>
  		</tr>
  		<tr>
  			<td align="center">服务地址</td><td colspan="3" class="address"></td>
  		</tr>
  		<tr>
  			<td align="center">服务日期</td><td class="dates"></td><td align="center">起止时间</td><td valign="bottom">___时___分&nbsp;至&nbsp;___时___分</td>
  		</tr>
  		<tr height="100px">
  			<td colspan="4">
  				<span>以下内容由服务对象填写（√）：</span>
  				<span>服务人员提供的服务是否规范：□&nbsp;是&nbsp;□&nbsp;不是</span>
  				<span>您对服务是否满意：□&nbsp;很满意&nbsp;□&nbsp;基本满意&nbsp;□&nbsp;不满意</span>
  				<span>请您留下您的宝贵意见：___________________________________________________________________</span>
  				<span style="float:right; margin-right: 10px">老人（监护人）签字：___________</span>
  			</td>
  		</tr>
  	</table>
  	<span style="display: block; margin-top: 8px;">
  		小棉袄爱老热线：010-67717699&nbsp;&nbsp;&nbsp;&nbsp;服务单位：<span class="providerName"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务人员姓名：
  	</span>
  </div>
  
  <br>
  <br>
  <!--虚线-->
  <div style="width:100%; border-bottom:1px dashed #000;"></div>
  <br>
  <br>
  
  <div style="width: 100%">
  	<table width="100%" cellpadding="0" cellspacing="0" border="1px solid black">
  		<tr height="30px">
  			<td colspan="4"  style="text-align: center;">
  				<img width="28px" style="font-size: 0; vertical-align: middle;"  src="<%=basePath %>back/images/xmalogo.jpg">
  				<span style="font-size: 20px; display: inline; font-weight: bold; vertical-align: middle;">小棉袄爱老居家养老服务记录表</span>
  			</td>
  		</tr>
  		<tr>
  			<td width="15%" align="center">老人姓名</td><td width="35%" class="name"></td><td width="15%" align="center">联系电话</td><td width="35%" class="phone"></td>
  		</tr>
  		<tr>
  			<td align="center">服务内容</td><td colspan="3" class="content"></td>
  		</tr>
  		<tr>
  			<td align="center">服务地址</td><td colspan="3" class="address"></td>
  		</tr>
  		<tr>
  			<td align="center">服务日期</td><td class="dates"></td><td align="center">起止时间</td><td valign="bottom">___时___分&nbsp;至&nbsp;___时___分</td>
  		</tr>
  		<tr height="100px">
  			<td colspan="4">
  				<span>以下内容由服务对象填写（√）：</span>
  				<span>服务人员提供的服务是否规范：□&nbsp;是&nbsp;□&nbsp;不是</span>
  				<span>您对服务是否满意：□&nbsp;很满意&nbsp;□&nbsp;基本满意&nbsp;□&nbsp;不满意</span>
  				<span>请您留下您的宝贵意见：___________________________________________________________________</span>
  				<span style="float:right; margin-right: 10px">老人（监护人）签字：___________</span>
  			</td>
  		</tr>
  	</table>
  	<span style="display: block; margin-top: 8px;">
  		小棉袄爱老热线：010-67717699&nbsp;&nbsp;&nbsp;&nbsp;服务单位：<span class="providerName"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务人员姓名：
  	</span>
  </div>
  
  <br>
  <br>
  <!--虚线-->
  <div style="width:100%; border-bottom:1px dashed #000;"></div>
  <br>
  <br>
  
  <div style="width: 100%">
  	<table width="100%" cellpadding="0" cellspacing="0" border="1px solid black">
  		<tr height="30px">
  			<td colspan="4" style="text-align: center;">
  				<img width="28px" style="font-size: 0; vertical-align: middle;"  src="<%=basePath %>back/images/xmalogo.jpg">
  				<span style="font-size: 20px; display: inline; font-weight: bold; vertical-align: middle;">小棉袄爱老居家养老服务记录表</span>
  			</td>
  		</tr>
  		<tr>
  			<td width="15%" align="center">老人姓名</td><td width="35%" class="name"></td><td width="15%" align="center">联系电话</td><td width="35%" class="phone"></td>
  		</tr>
  		<tr>
  			<td align="center">服务内容</td><td colspan="3" class="content"></td>
  		</tr>
  		<tr>
  			<td align="center">服务地址</td><td colspan="3" class="address"></td>
  		</tr>
  		<tr>
  			<td align="center">服务日期</td><td class="dates"></td><td align="center">起止时间</td><td valign="bottom">___时___分&nbsp;至&nbsp;___时___分</td>
  		</tr>
  		<tr height="100px">
  			<td colspan="4">
  				<span>以下内容由服务对象填写（√）：</span>
  				<span>服务人员提供的服务是否规范：□&nbsp;是&nbsp;□&nbsp;不是</span>
  				<span>您对服务是否满意：□&nbsp;很满意&nbsp;□&nbsp;基本满意&nbsp;□&nbsp;不满意</span>
  				<span>请您留下您的宝贵意见：___________________________________________________________________</span>
  				<span style="float:right; margin-right: 10px">老人（监护人）签字：___________</span>
  			</td>
  		</tr>
  	</table>
  	<span style="display: block; margin-top: 8px;">
  		小棉袄爱老热线：010-67717699&nbsp;&nbsp;&nbsp;&nbsp;服务单位：<span class="providerName"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务人员姓名：
  	</span>
  </div>
  
  </body>
<script type="text/javascript" src="<%=basePath%>back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		window.print();
	});
</script>
</html>

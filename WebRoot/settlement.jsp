<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	</style>
	
  </head>
  
  <body onload="initBody()">
  
  <div style="width: 100%">
  	<table width="100%" cellpadding="0" cellspacing="0" border="1px solid black">
  		<tr>
  			<td colspan="4">
  				<img width="30px" src="<%=basePath %>back/images/xmalogo.jpg">
  				<font size="12px"><strong>东城区小棉袄爱老居家养老服务记录表</strong></font>
  			</td>
  		</tr>
  		<tr>
  			<td width="15%" align="center">老人姓名</td><td width="35%"></td><td width="15%" align="center">联系电话</td><td width="35%"></td>
  		</tr>
  		<tr>
  			<td align="center">服务内容</td><td colspan="3"></td>
  		</tr>
  		<tr>
  			<td align="center">服务地址</td><td colspan="3"></td>
  		</tr>
  		<tr>
  			<td align="center">服务日期</td><td></td><td align="center">起止时间</td><td></td>
  		</tr>
  		<tr height="100px">
  			<td colspan="4">
  				以下内容由服务对象填写（√）：<br />
  				服务人员提供的服务是否规范：□&nbsp;是&nbsp;□&nbsp;不是<br />
  				您对服务是否满意：□&nbsp;很满意&nbsp;□&nbsp;基本满意&nbsp;□&nbsp;不满意<br/><br/>
  				请您留下您的宝贵意见：________________________________________________________________________________<br/><br/>
  				老人（监护人）签字：___________
  			</td>
  		</tr>
  	</table>
  	小棉袄爱老热线：010-87817199&nbsp;&nbsp;010-87817699&nbsp;&nbsp;&nbsp;&nbsp;服务单位：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务人员姓名：
  </div>
  
  <br>
  <br>
  <hr>
  <br>
  <br>
  
  <div style="width: 100%">
  	<table width="100%" cellpadding="0" cellspacing="0" border="1px solid black">
  		<tr>
  			<td colspan="4">
  				<img width="30px" src="<%=basePath %>back/images/xmalogo.jpg">
  				<font size="12px"><strong>东城区小棉袄爱老居家养老服务记录表</strong></font>
  			</td>
  		</tr>
  		<tr>
  			<td width="15%" align="center">老人姓名</td><td width="35%"></td><td width="15%" align="center">联系电话</td><td width="35%"></td>
  		</tr>
  		<tr>
  			<td align="center">服务内容</td><td colspan="3"></td>
  		</tr>
  		<tr>
  			<td align="center">服务地址</td><td colspan="3"></td>
  		</tr>
  		<tr>
  			<td align="center">服务日期</td><td></td><td align="center">起止时间</td><td></td>
  		</tr>
  		<tr height="100px">
  			<td colspan="4">
  				以下内容由服务对象填写（√）：<br />
  				服务人员提供的服务是否规范：□&nbsp;是&nbsp;□&nbsp;不是<br />
  				您对服务是否满意：□&nbsp;很满意&nbsp;□&nbsp;基本满意&nbsp;□&nbsp;不满意<br/><br/>
  				请您留下您的宝贵意见：________________________________________________________________________________<br/><br/>
  				老人（监护人）签字：___________
  			</td>
  		</tr>
  	</table>
  	小棉袄爱老热线：010-87817199&nbsp;&nbsp;010-87817699&nbsp;&nbsp;&nbsp;&nbsp;服务单位：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务人员姓名：
  </div>
  
  
  <br>
  <br>
  <hr>
  <br>
  <br>
  
  <div style="width: 100%">
  	<table width="100%" cellpadding="0" cellspacing="0" border="1px solid black">
  		<tr>
  			<td colspan="4">
  				<img width="30px" src="<%=basePath %>back/images/xmalogo.jpg">
  				<font size="12px"><strong>东城区小棉袄爱老居家养老服务记录表</strong></font>
  			</td>
  		</tr>
  		<tr>
  			<td width="15%" align="center">老人姓名</td><td width="35%"></td><td width="15%" align="center">联系电话</td><td width="35%"></td>
  		</tr>
  		<tr>
  			<td align="center">服务内容</td><td colspan="3"></td>
  		</tr>
  		<tr>
  			<td align="center">服务地址</td><td colspan="3"></td>
  		</tr>
  		<tr>
  			<td align="center">服务日期</td><td></td><td align="center">起止时间</td><td></td>
  		</tr>
  		<tr height="100px">
  			<td colspan="4">
  				以下内容由服务对象填写（√）：<br />
  				服务人员提供的服务是否规范：□&nbsp;是&nbsp;□&nbsp;不是<br />
  				您对服务是否满意：□&nbsp;很满意&nbsp;□&nbsp;基本满意&nbsp;□&nbsp;不满意<br/><br/>
  				请您留下您的宝贵意见：________________________________________________________________________________<br/><br/>
  				老人（监护人）签字：___________
  			</td>
  		</tr>
  	</table>
  	小棉袄爱老热线：010-87817199&nbsp;&nbsp;010-87817699&nbsp;&nbsp;&nbsp;&nbsp;服务单位：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务人员姓名：
  </div>
  
  
  </body>
<script type="text/javascript" src="<%=basePath%>back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		window.print();
	})
</script>
</html>

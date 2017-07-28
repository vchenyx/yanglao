<%@page import="com.common.util.global.ConfigureFile"%>
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
<title>Insert title here</title>
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" />
<link rel="stylesheet" href="<%=basePath %>back/css/map_indoor.css" />

<!-- 谷歌地图api -->
<!--<script type="text/javascript" src="http://maps.google.cn/maps/api/js?v=3.exp&sensor=true"></script>-->
<script src="http://maps.google.cn/maps?file=api&v=2&sensor=false&key=<%=ConfigureFile.google_key %>" type="text/javascript"></script>
<!-- 定位工具js -->
<script type="text/javascript" src="<%=basePath %>back/map_js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath %>back/map_js/hashmap.js"></script>
<script type="text/javascript" src="<%=basePath %>back/map_js/bgmap/google_map_person_position.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body style=" min-width:1300px;" onload="loadScript(0,0,10);" onunload="GUnload();">
	<div class="admin-main">
		<div class="header_top">
			<div class="layui-input-block" style="margin-left: 0;">
				<div class="layui-input-inline" style="width:260px; height:35px; padding-top:15px; margin-right:20px;">
					<!--报警人数-->
					<span style="margin-right:30px;"><img style="width:28px; height:18px; margin-bottom:5px;" src="<%=basePath %>back/images/alarm.gif"/>： <span id="alarmpeoplenum">0</span></span>
					<!--定位人数-->
					<span style="margin-right:30px;"><img style="width:18px; height:18px; margin-bottom:4px;" src="<%=basePath %>back/images/position.png"/>： <span  id="locationpeoplenum">0</span></span>
					
				</div>
				<!--查找老人-->
				<%-- <div class="layui-input-inline" style="width:220px; height:50px; margin: 0 28px 0 20px; position: absolute; right:0;">
					<input id="username" type="text" placeholder="输入姓号/手机号/身份证号" autocomplete="off" class="layui-input" style="width:100%; margin-top:6px; display:inline-block;">
					<img src="<%=basePath %>back/images/search.png" style="position: absolute; top:15px; right:10px; cursor: pointer;" alt="" />
				</div> --%>
			</div>
		</div>
		<div class="content">
			<!--左侧地图-->
			<div id="container">
				
			</div>
			<!--右侧人员信息-->
			<div class="info">
				<div class="info_top">
					<input type="hidden" id="older_position" value="402880315c1b0b8b01sd1b0b8da80013" />
					<p>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<span class="old_name"></span></p>
					<p>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：<span class="old_sex"></span></p>
					<p>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：<span class="old_age"></span></p>
					<p>手&nbsp;&nbsp;机&nbsp;&nbsp;号：<span class="old_phone"></span></p>
					<p>身份证号：<span class="old_peopleId"></span></p>
					<div class="info_bottom">
						<a href="javascript:;" id="speedlocationbox" onclick="javascript:speedTracker();" class="layui-btn layui-btn-small" style="background: #1AA094; margin:10px 5px 0 50px;">
							开始跟踪
						</a>
						<a href="javascript:;" onclick="javascript:cancelSpeedTracker();" class="layui-btn layui-btn-small" style="background: #FF5722; margin:10px 0 0 0">
							取消跟踪
						</a>
					</div>
				</div>
				<div class="info_center">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<!--<th>编号</th>-->
								<th>老人姓名</th>
							</tr>
						</thead>
						<tbody id="peopleShow">
							<tr>
								<!--<td>ZJ22138</td>-->
								<td>胡大海</td>
							</tr>
						</tbody>
					</table>
				</div>
				
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	<%-- <script type="text/javascript" src="<%=basePath %>back/js/map_indoor.js"></script> --%>
</body>
</html>
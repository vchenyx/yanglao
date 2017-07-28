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
<title></title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<link rel="stylesheet" href="<%=basePath %>static/css/viewRoom.css" />
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>
</head>
<body>
<div class="admin-main">
			<div class="header_top">
				<div class="layui-input-block" style="margin-left: 0;">
					<!--报警总数-->
					<div style="width:100px; height:50px; float:left; font-size: 0;">
						<img style="width:28px; height:18px; float:left; margin-top:16px" src="<%=basePath %>static/images/alarm.gif"/>
						<span style="display: inline-block; font-size: 14px; float: left; margin:18px 5px 0 5px">:</span>
						<span id="alarm_num" style="display: inline-block; font-size: 14px; float: left; margin-top:18px">0</span>
					</div>
					<div class="layui-input-inline" style="width:247px; height:50px; position: absolute; right:0">
						<select style="width:100%; min-width: 170px; display: inline-block; margin:10px 0 0 0; height:35px; border:1px solid #ccc; background: #fff;" onchange="selected()">
							<option value="0">--选择查看项目工点--</option>
							<option value="1">天创小区</option>
							<option value="2">中关村小区</option>
							<option value="3">微软小区</option>
						</select>
					</div>
				</div>
			</div>
			<div class="content">
				<!--左侧地图-->
				<div id="container">
					<!--<img src="images/map.png" alt="" />-->
					<img src="<%=basePath %>static/images/map.png" alt="" />
					<!--小区的单元楼  开始-->
					<div class="one" style="position: absolute; top:62%; left:40%; width:19%; height:33%">
						<a href="javascript:;" style="font-size: 20px; color:red; font-weight: bold; position: absolute; left:30%">一单元</a>
					</div>
					<div class="one" style="position: absolute; top:48%; left:67%; width:19%; height:33%">
						<a href="javascript:;" style="font-size: 20px; color:red; font-weight: bold; position: absolute; left:30%">二单元</a>
					</div>
					<div class="one" style="position: absolute; top:38%; left:29%; width:19%; height:23%">
						<a href="javascript:;" style="font-size: 20px; color:red; font-weight: bold; position: absolute; left:30%">三单元</a>
					</div>
					<div class="one" style="position: absolute; top:29%; left:52%; width:16%; height:23%">
						<a href="javascript:;" style="font-size: 20px; color:red; font-weight: bold; position: absolute; left:30%">四单元</a>
					</div>
					<div class="one" style="position: absolute; top:16%; left:20%; width:16%; height:22%">
						<a href="javascript:;" style="font-size: 20px; color:red; font-weight: bold; position: absolute; left:30%">五单元</a>
					</div>
					<div class="one" style="position: absolute; top:10%; left:38%; width:13%; height:28%">
						<a href="javascript:;" style="font-size: 20px; color:red; font-weight: bold; position: absolute; left:30%">六单元</a>
					</div>
					
					<!--小区的单元楼  结束-->
					
					<!--每一栋楼的房间  开始-->
					<div class="floor f501" style="position: absolute; top:8%; left:8%; width:15%; height:14%;"></div>
					<div class="floor f502" style="position: absolute; top:8%; left:31%; width:15%; height:14%;"></div>
					<div class="floor f503" style="position: absolute; top:8%; left:53%; width:15%; height:14%;"></div>
					<div class="floor f504" style="position: absolute; top:8%; left:76%; width:15%; height:14%;"></div>
					<div class="floor f401" style="position: absolute; top:25%; left:8%; width:15%; height:14%;"></div>
					<div class="floor f402" style="position: absolute; top:25%; left:31%; width:15%; height:14%;"></div>
					<div class="floor f403" style="position: absolute; top:25%; left:53%; width:15%; height:14%;"></div>
					<div class="floor f404" style="position: absolute; top:25%; left:76%; width:15%; height:14%;"></div>
					<div class="floor f301" style="position: absolute; top:44%; left:8%; width:15%; height:14%;"></div>
					<div class="floor f302" style="position: absolute; top:44%; left:31%; width:15%; height:14%;"></div>
					<div class="floor f303" style="position: absolute; top:44%; left:53%; width:15%; height:14%;"></div>
					<div class="floor f304" style="position: absolute; top:44%; left:76%; width:15%; height:14%;"></div>
					<div class="floor f201" style="position: absolute; top:61%; left:8%; width:15%; height:14%;"></div>
					<div class="floor f202" style="position: absolute; top:61%; left:31%; width:15%; height:14%;"></div>
					<div class="floor f203" style="position: absolute; top:61%; left:53%; width:15%; height:14%;"></div>
					<div class="floor f204" style="position: absolute; top:61%; left:76%; width:15%; height:14%;"></div>
					<div class="floor f101" style="position: absolute; top:79%; left:8%; width:15%; height:14%;"></div>
					<div class="floor f102" style="position: absolute; top:79%; left:31%; width:15%; height:14%;"></div>
					<div class="floor f103" style="position: absolute; top:79%; left:53%; width:15%; height:14%;"></div>
					<div class="floor f104" style="position: absolute; top:79%; left:76%; width:15%; height:14%;"></div>
					
					<!--每一栋楼的房间  结束-->
					
					<!--房间内的报警设备  开始-->
					<div class="alarm alarmShuiJin" style="position: absolute; top:36%; left:41%; width:6%; height:9%;"></div>
					<div class="alarm alarmYanWu" style="position: absolute; top:47%; left:77%; width:5%; height:6%;"></div>
					<div class="alarm alarmMeiQi" style="position: absolute; top:50%; left:24%; width:5%; height:7%;"></div>
					<div class="alarm alarmRuQin" style="position: absolute; top:61%; left:43%; width:4%; height:6%;"></div>
					
					<!--弹出层报警信息-->
					<div class="alarmInfo" style="width:100px; height:30px; background: skyblue; color:#fff; position: absolute; left:0; top:0; text-align: center; line-height: 30px;">
						<p>报警器正常</p>
					</div>
					
					<!--房间内的报警设备  结束-->
					
					
				</div>
				<!--右侧人员信息-->
				<div class="info">
					<div class="info_top">
						<p class="danyuan" style="text-align: center; font-size: 20px;;">一单元</p>
						<!--单元选择-->
						<ul id="danyuan">
							<li><a href="javascript:;">一单元</a></li>
							<li><a href="javascript:;">二单元</a></li>
							<li><a href="javascript:;">三单元</a></li>
							<li><a href="javascript:;">四单元</a></li>
							<li><a href="javascript:;">五单元</a></li>
							<li><a href="javascript:;">六单元</a></li>
						</ul>
						<!--房间选择-->
						<ul id="room">
							<li><a href="javascript:;">101</a></li>
							<li><a href="javascript:;">102</a></li>
							<li><a href="javascript:;">103</a></li>
							<li><a href="javascript:;">104</a></li>
							<li><a href="javascript:;">201</a></li>
							<li><a href="javascript:;">202</a></li>
							<li><a href="javascript:;">203</a></li>
							<li><a href="javascript:;">204</a></li>
							<li><a href="javascript:;">301</a></li>
							<li><a href="javascript:;">302</a></li>
							<li><a href="javascript:;">303</a></li>
							<li><a href="javascript:;">304</a></li>
							<li><a href="javascript:;">401</a></li>
							<li><a href="javascript:;">402</a></li>
							<li><a href="javascript:;">403</a></li>
							<li><a href="javascript:;">404</a></li>
							<li><a href="javascript:;">501</a></li>
							<li><a href="javascript:;">502</a></li>
							<li><a href="javascript:;">503</a></li>
							<li><a href="javascript:;">504</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		

</body>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/viewRoom.js"></script>
</html>
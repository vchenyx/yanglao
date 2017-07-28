<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var basePath = "<%=basePath %>";

</script>
<style>
	*{margin:0; padding:0;}
	.container{width:100%; min-width:1200px; position: absolute; top:0; bottom:0; overflow: hidden;}
	.pic{width:1200px; height:100px; margin: 0 auto;}
	.pic .left{width:400px; height:100px; float: left;}
	.pic .left img{width:100px; height:100px; margin-left:150px;}
	
	.pic .right{width:800px; height:100px; float: right;}
	.pic .right span{display: block; float: left; font-size: 100px; font-family: "微软雅黑"; margin: -10px 0 0 50px;}
	.pic .right img{width:100px; height:100px; float:left;}
	.pic .right .xinlv{width:400px; height:100px; float: left;}
	.pic .right .huxi{width:400px; height:100px; float: right;}
	
	.content{width:100%; min-width: 1200px; height:500px; margin:0 auto;}
</style>


</head>
<body style="min-width:1200px;">

	<div class="container">
		<div class="pic">
			<div class="left">
				<img id="state" src="<%=basePath %>back/images/sit.png" alt="" />
			</div>
			<div class="right">
				<div class="xinlv">
					<img src="<%=basePath %>back/images/xinlv.png" alt="" />
					<span id="heartNum" style="color:#db646d">--</span>
				</div>
				<div class="huxi">
					<img src="<%=basePath %>back/images/huxi.png" alt="" />
					<span id="breathNum" style="color:#02abe2;">--</span>
				</div>
			</div>
		</div>
		<div class="selectNo" style="width:200px; height:60px; outline: none; margin-left: 20px;">
			<span style="font-size: 14px; font-family: '微软雅黑';">请选择设备：</span>
			<select style="width:200px; height:30px;" id="deviceNo" onchange="switchDevoce()">
				<option value="">--请选择--</option>
				<option value="641650000230">设备一</option>
				<option value="641650000231">设备二</option>
			</select>
		</div>
		<div class="content">
			<div id="main1" style="width:500px; height:430px; border:1px solid #ccc; float:left;">
			
			</div>
			<div id="main2" style="width:500px; height:430px; border:1px solid #ccc; float:right;">
			
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>back/js/echarts.min.js"></script>
<script type="text/javascript" src="<%=basePath %>back/js/pension/oldBed.js"></script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="renderer" content="webkit" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
	<meta charset="UTF-8">
	<title>健康首页</title>
	<link rel="stylesheet" href="<%=basePath %>back/css/health/reset.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>back/css/health/public.css"/>
	<link rel="stylesheet" href="<%=basePath %>back/css/health/index.css" />
	<script type="text/javascript">
		var basePath = "<%=basePath %>";
	</script>
</head>
<body>
	<div class="container">
		<!--头部-->
		<div class="top">
			<div class="header">
				<div class="header_content">
					<div class="logo">
						<img src="<%=basePath %>back/images/logo1.png" alt="" />
					</div>
					<div class="nav">
						<ul>
							<li>
								<a href="javascript:;">
									<div class="out">
										<div class="liTop">
											<p>首页</p>
										</div>
										<div class="liBottom active">
											<div></div>
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="<%=basePath %>turn/healthData.do">
									<div class="out">
										<div class="liTop">
											<p>健康数据</p>
										</div>
										<div class="liBottom">
											<div></div>
										</div>
									</div>
								</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<!--内容-->
		<div class="content">
			<div class="content-left">
				<div class="lt">
					<img class="photo" src="<%=basePath %>back/images/userlogo.png" alt="" />
					<div class="info">
						<p class="name">金坤</p>
						<p class="man"><span>普通人群 </span>|<span> 39岁</span></p>
						<p><span>170cm </span>|<span> 76kg</span></p>
						<p><span>0积分 </span>|<span> 一级会员</span></p>
						<p>82433</p>
					</div>
					<div class="money">
						<ul>
							<li>
								<img src="<%=basePath %>back/images/vmoney.png" alt="" />
								<span>康币</span>
								<p>0</p>
							</li>
							<li>|</li>
							<li>
								<img src="<%=basePath %>back/images/contribute.gif" alt="" />
								<span>贡献</span>
								<p>0</p>
							</li>
							<li>|</li>
							<li>
								<img src="<%=basePath %>back/images/prestige.png" alt="" />
								<span>声望</span>
								<p>0</p>
							</li>
						</ul>
					</div>
				</div>
				<div class="lb">
					<ul>
						<li class="one">
							<p>血压</p>
							<p>BLOOD PRESSURE</p>
							<span>></span>
							<ul class="hide">
								<li>血压数据</li>
							</ul>
						</li>
						<li class="one">
							<p>血氧</p>
							<p>OXYGEN</p>
							<span>></span>
							<ul class="hide">
								<li>血氧数据</li>
							</ul>
						</li>
						<li class="one">
							<p>心电</p>
							<p>ECG</p>
							<span>></span>
							<ul class="hide">
								<li>心电图</li>
							</ul>
						</li>
						<li class="one">
							<p>血液</p>
							<p>BLOOD</p>
							<span>></span>
							<ul class="hide">
								<li>血糖</li>
								<li>血脂</li>
								<li>血尿酸</li>
								<li>血酮体</li>
							</ul>
						</li>
						<li class="one">
							<p>人体成分</p>
							<p>MOTION</p>
							<span>></span>
							<ul class="hide">
								<li>人体成分</li>
							</ul>
						</li>
						<li class="one">
							<p>尿液数据</p>
							<p>URINE</p>
							<span>></span>
							<ul class="hide">
								<li>尿十一项</li>
							</ul>
						</li>
						<li class="one">
							<p>体温数据</p>
							<p>TEMPRETURE</p>
							<span>></span>
							<ul class="hide">
								<li>体温</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<div class="content-right">
				<div class="today_health">
					<p>今日健康状况(<span class="date"></span>)</p>
					<div class="health_left">
						<ul>
							<li>心&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;率：65</li>
							<li>血&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;压：80</li>
							<li>血&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;脂：正常</li>
							<li>血&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;氧：正常</li>
							<li>血&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;糖：正常</li>
							<li>体&nbsp;&nbsp;成&nbsp;&nbsp;分：脂肪偏多</li>
							<li>脉&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;搏：正常</li>
							<li>血&nbsp;&nbsp;尿&nbsp;&nbsp;酸：正常</li>
							<li>尿&nbsp;&nbsp;成&nbsp;&nbsp;分：正常</li>
							<li>体&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;温：正常</li>
							<li>学&nbsp;&nbsp;酮&nbsp;&nbsp;体：正常</li>
							<li>居住环境：正常</li>
						</ul>
					</div>
					<div class="health_right">
						<img style="width:110px; height:110px;" src="<%=basePath %>back/images/qiu3_18.png" alt="" />
						<div style="float:left; margin:10px 0 0 0;">
							<span style="width:12px; height:12px; background: red; display:block; float:left;"></span>
							<span style="width:24px; height:12px; display:block; float:left;">危险</span>
							<span style="width:12px; height:12px; margin-left: 6px; background: #6fce10; display:block; float:left;"></span>
							<span style="width:24px; height:12px; display:block; float:left;">正常</span>
							<span style="width:12px; height:12px; margin-left: 6px; background: #808080; display:block; float:left;"></span>
							<span style="width:12px; height:12px; display:block; float:left;">无</span>
						</div>
					</div>
				</div>
				<div class="nav_bottom">
					<ul>
						<li>
							<img src="<%=basePath %>back/images/main_center_1.png" alt="" />
							<a href="javascript:;">体成分</a>
						</li>
						<li>
							<img src="<%=basePath %>back/images/main_center_2.png" alt="" />
							<a href="javascript:;">尿成分</a>
						</li>
						<li>
							<img src="<%=basePath %>back/images/main_center_3.png" alt="" />
							<a href="javascript:;">血液</a>
						</li>
						<li>
							<img src="<%=basePath %>back/images/main_center_4.png" alt="" />
							<a href="javascript:;">室内环境</a>
						</li>
						<li>
							<img src="<%=basePath %>back/images/main_center_5.png" alt="" />
							<a href="javascript:;">心肺功能</a>
						</li>
						<li>
							<img src="<%=basePath %>back/images/main_center_6.png" alt="" />
							<a href="javascript:;">心血管健康</a>
						</li>
					</ul>
				</div>
				<div class="nav_node">
					<div class="nav_nodes nav_node1">
						<img src="<%=basePath %>back/images/ren0.png" style="position: absolute; top:64px; left:93px; width:170px; height:124px;" alt="" />
						<table style="width:100%; height:100%; border-collapse: collapse; table-layout: fixed;">
							<tr>
								<td>
									<span>脂肪含量</span><br />
									<span>--</span>
								</td>
								<td>
									<span>水分含量</span><br />
									<span>--</span>
								</td>
								<td>
									<span>内脏脂肪等级</span><br />
									<span>--</span>
								</td>
							</tr>
							<tr>
								<td>
									<span>体重</span><br />
									<span>--</span>
								</td>
								<td>
									
								</td>
								<td>
									<span>骨骼含量</span><br />
									<span>--</span>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: none;">
									<span>BMI</span><br />
									<span>--</span>
								</td>
								<td style="border-bottom: none;">
									<span>肌肉含量</span><br />
									<span>--</span>
								</td>
								<td style="border-bottom: none;">
									<span>BMR</span><br />
									<span>--</span>
								</td>
							</tr>
						</table>
					</div>
					<div class="nav_nodes nav_node2">
						<img src="<%=basePath %>back/images/niao0.png" style="position: absolute; top:63px; left:90px; width:178px; height:124px;" alt="" />
						<table style="width:100%; height:100%; border-collapse: collapse; table-layout: fixed;">
							<tr>
								<td>
									<span>尿胆原</span><br />
									<span>--</span>
								</td>
								<td>
									<span>红细胞</span><br />
									<span>--</span>
								</td>
								<td>
									<span>维生素</span><br />
									<span>--</span>
								</td>
								<td>
									<span>葡萄糖</span><br />
									<span>--</span>
								</td>
							</tr>
							<tr>
								<td>
									<span>尿比重</span><br />
									<span>--</span>
								</td>
								<td>
									
								</td>
								<td>
									
								</td>
								<td>
									<span>亚硝酸盐</span><br />
									<span>--</span>
								</td>
							</tr>
							<tr>
								<td>
									<span>白细胞</span><br />
									<span>--</span>
								</td>
								<td>
									
								</td>
								<td>
									
								</td>
								<td>
									<span>PH值</span><br />
									<span>--</span>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: none;">
									<span>蛋白质</span><br />
									<span>--</span>
								</td>
								<td style="border-bottom: none;">
									<span>酮体</span><br />
									<span>--</span>
								</td>
								<td style="border-bottom: none;">
									<span>胆红素</span><br />
									<span>--</span>
								</td>
								<td style="border-bottom: none;">
									
								</td>
							</tr>
						</table>
					</div>
					<div class="nav_nodes nav_node3">
						<img src="<%=basePath %>back/images/xue0.png" style="position: absolute; top:64px; left:93px; width:170px; height:124px;" alt="" />
						<table style="width:100%; height:100%; border-collapse: collapse; table-layout: fixed;">
							<tr>
								<td>
									<span>血糖</span><br />
									<span>--</span>
								</td>
								<td>
									<span>血尿酸</span><br />
									<span>--</span>
								</td>
								<td>
									<span>血酮</span><br />
									<span>--</span>
								</td>
							</tr>
							<tr>
								<td>
									<span>胆固醇</span><br />
									<span>--</span>
								</td>
								<td>
									
								</td>
								<td>
									<span>甘油三酯</span><br />
									<span>--</span>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: none;">
									<span>低密度脂蛋白</span><br />
									<span>--</span>
								</td>
								<td style="border-bottom: none;">
									<span>高密度脂蛋白</span><br />
									<span>--</span>
								</td>
							</tr>
						</table>
					</div>
					<div class="nav_node4">
						<img src="<%=basePath %>back/images/shi0.png" style="position: absolute; top:64px; left:93px; width:170px; height:124px;" alt="" />
						<table style="width:100%; height:100%; border-collapse: collapse; table-layout: fixed;">
							<tr>
								<td>
									<span>温度</span><br />
									<span>--</span>
								</td>
								<td>
									<span>湿度</span><br />
									<span>--</span>
								</td>
								<td>
									<span>甲醛</span><br />
									<span>--</span>
								</td>
							</tr>
							<tr>
								<td>
									
								</td>
								<td>
									
								</td>
								<td>
									
								</td>
							</tr>
							<tr>
								<td style="border-bottom: none;">
									<span>二氧化碳</span><br />
									<span>--</span>
								</td>
								<td style="border-bottom: none;">
									<span>PM2.5</span><br />
									<span>--</span>
								</td>
								<td style="border-bottom: none;">
									<span>一氧化碳</span><br />
									<span>--</span>
								</td>
							</tr>
						</table>
					</div>
					<div class="nav_node5">
						<div class="temp">
							<div class="temp_left" style="">
								<span>
									TEMP
								</span><br />
								<span>体温</span>
							</div>
							<div class="temp_right">
								<span>
									--
								</span>
							</div>
						</div>
						<div class="nibp">
							<div class="nibp_left" style="">
								<span>
									NIBP
								</span><br />
								<span>血压</span>
							</div>
							<div class="nibp_right">
								<span>收缩压：</span>
								<span>
									--
								</span>
								<span style="margin-left:87px;">舒张压：</span>
								<span>
									--
								</span>
								<span style="margin-left:87px;">平均压：</span>
								<span>
									--
								</span>
							</div>
						</div>
					</div>
					<div class="nav_node6">
						<div style="float:left; margin:35px 0 0 10px;">
							<span>心电图</span>
						</div>
						<div style="float:left; margin:10px 0 0 10px;">
							<img src="<%=basePath %>back/images/xin_03.png" alt="" />
						</div>
						<div style="float:left; margin:35px 0 0 10px;">
							<span>心率HR <i style="color:#7fc210;">70</i> bmp</span>
						</div>
					</div>
					<div class="nav_node7">
						<div style="float:left; margin:35px 0 0 10px;">
							<span>血氧图</span>
						</div>
						<div style="float:left; margin:10px 0 0 10px;">
							<img src="<%=basePath %>back/images/oxygen_03.png" alt="" />
						</div>
						<div style="float:left; margin:35px 0 0 10px;">
							<span>SP02 <i style="color:#7fc210;">98</i> % <span>心率HR <i style="color:#7fc210;">84</i> bmp</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
	</div>		
	
	<script src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
	<script src="<%=basePath %>back/js/health/home.js"></script>
	
	
</body>
</html>
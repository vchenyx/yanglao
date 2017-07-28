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
	<title></title>
	<link rel="stylesheet" href="<%=basePath %>back/css/health/reset.css" />
	<link rel="stylesheet" href="<%=basePath %>back/css/health/public.css" />
	<link rel="stylesheet" href="<%=basePath %>back/css/health/healthData.css" />
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
								<a href="<%=basePath %>turn/toHealth.do">
									<div class="out">
										<div class="liTop">
											<p>首页</p>
										</div>
										<div class="liBottom">
											<div></div>
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="javascript:;">
									<div class="out">
										<div class="liTop">
											<p>健康数据</p>
										</div>
										<div class="liBottom active">
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
				<div class="shouzhi">
					
				</div>
				<div class="maibo">
					
				</div>
				<div class="xinzang">
					
				</div>
				<div class="xueguan">
					
				</div>
				<div class="foot">
					
				</div>
				<div class="shen">
					
				</div>
				
				
				<div class="out_xinlv"></div>
				<div class="xinlv">
					<p>
						<span><img src="<%=basePath %>back/images/main_center_6_0.png" alt="" /></span>
						<span style="color:#fff;">心率：</span>
						<span style="color:#6fce10;">70</span>
						<span style="color:#fff; margin-left:53px;"><img src="<%=basePath %>back/images/main_center_6_0.png" alt="" /></span>
						<span style="color:#fff;">体温：</span>
						<span style="color:#fff;">--</span>
					</p>
					<p style="margin-top:15px;">
						<span><img src="<%=basePath %>back/images/main_center_6_0.png" alt="" /></span>
						<span style="color:#fff;">血氧饱和度：</span>
						<span style="color:#6fce10;">98</span>
						<span style="color:#fff; margin:0 17px 0 17px;">/</span>
						<span style="color:#fff;">脉搏：</span>
						<span style="color:#6fce10;">84</span>
					</p>
					<p style="margin-top:15px;">
						<span><img src="<%=basePath %>back/images/main_center_6_0.png" alt="" /></span>
						<span style="color:#fff;">血压（mmHg/L）：</span>
						<span style="color:#6fce10;">收缩压 131</span>&nbsp;&nbsp;
						<span style="color:#fff;">舒张压：</span>
						<span style="color:#6fce10;">79</span>
					</p>
				</div>
				
				<div class="out_blood"></div>
				<div class="blood">
					<p>
						<span><img src="<%=basePath %>back/images/main_center_6_0.png" alt="" /></span>
						<span style="color:#fff;">血液</span>
					</p>
					<p style="margin-top:10px;">
						<span style="color:#fff;">血糖：</span>
						<span style="color:red;">6.7</span>
						<span style="color:#fff; margin-left:10px;">血酮体：</span>
						<span style="color:#fff;">--</span>
						<span style="color:#fff; margin-left:10px;">甘油三酯：</span>
						<span style="color:#fff;">--</span>
					</p>
					<p style="margin-top:6px;">
						<span style="color:#fff;">胆固醇：</span>
						<span style="color:#fff;">--</span>
						<span style="color:#fff; margin-left:10px;">血尿酸：</span>
						<span style="color:#fff;">--</span>
					</p>
					<p style="margin-top:6px;">
						<span style="color:#fff;">高密度脂蛋白：</span>
						<span style="color:#fff;">--</span>
						<span style="color:#fff; margin-left:10px;">低密度脂蛋白：</span>
						<span style="color:#fff;">--</span>
					</p>
				</div>
				<div class="out_niao"></div>
				<div class="niao">
					<p>
						<span><img src="<%=basePath %>back/images/main_center_6_0.png" alt="" /></span>
						<span style="color:#fff;">尿成分</span>
					</p>
					<p style="margin-top: 8px;">
						<span style="color:#fff; width:55px; display: inline-block;">尿比重：</span>
						<span style="color:#fff; width:35px; display: inline-block;">100.9</span>
						<span style="color:#fff; margin-left: 30px; width:55px; display: inline-block;">尿PH值：</span>
						<span style="color:#fff; width:35px; display: inline-block;">--</span>
					</p>
					<p style="margin-top: 5px;">
						<span style="color:#fff; width:55px; display: inline-block;">尿蛋白：</span>
						<span style="color:#fff; width:35px; display: inline-block;">--</span>
						<span style="color:#fff; margin-left: 30px; width:55px; display: inline-block;">尿糖：</span>
						<span style="color:#fff; width:35px; display: inline-block;">--</span>
					</p>
					<p style="margin-top: 5px;">
						<span style="color:#fff; width:55px; display: inline-block;">尿酮体：</span>
						<span style="color:#fff; width:35px; display: inline-block;">--</span>
						<span style="color:#fff; margin-left: 30px; width:55px; display: inline-block;">胆红素：</span>
						<span style="color:#fff; width:35px; display: inline-block;">--</span>
					</p>
					<p style="margin-top: 5px;">
						<span style="color:#fff; width:53px; display: inline-block;">尿胆原：</span>
						<span style="color:#fff; width:35px; display: inline-block;">--</span>
						<span style="color:#fff; margin-left: 30px; width:60px; display: inline-block;">亚硝酸盐：</span>
						<span style="color:#fff; width:35px; display: inline-block;">--</span>
					</p>
					<p style="margin-top: 5px;">
						<span style="color:#fff; width:55px; display: inline-block;">白细胞：</span>
						<span style="color:#fff; width:35px; display: inline-block;">--</span>
						<span style="color:#fff; margin-left: 30px; width:55px; display: inline-block;">红细胞：</span>
						<span style="color:#fff; width:35px; display: inline-block;">--</span>
					</p>
					<p style="margin-top: 5px;">
						<span style="color:#fff; width:55px; display: inline-block;">维C：</span>
						<span style="color:#fff; width:35px; display: inline-block;">--</span>
					</p>
				</div>
				<div class="out_ti"></div>
				<div class="ti">
					<p>
						<span><img src="<%=basePath %>back/images/main_center_6_0.png" alt="" /></span>
						<span style="color:#fff; width:55px; display: inline-block;">体成分</span>
					</p>
					<p style="margin-top: 8px;">
						<span style="color:#fff; width:55px; display: inline-block;">体重(kg):</span>
						<span style="color:#fff; width:35px; display: inline-block;">66.5</span>
						<span style="color:#fff; margin-left: 18px; width:55px; display: inline-block;">身高(cm):</span>
						<span style="color:#fff; width:35px; display: inline-block;">170</span>
					</p>
					<p style="margin-top: 5px;">
						<span style="color:#fff; width:55px; display: inline-block;">肌肉(%):</span>
						<span style="color:#fff; width:35px; display: inline-block;">0</span>
						<span style="color:#fff; margin-left: 18px;">水(%):</span>
						<span style="color:#fff; width:35px; display: inline-block;">0</span>
					</p>
					<p style="margin-top: 5px;">
						<span style="color:#fff; width:55px; display: inline-block;">骨骼(%):</span>
						<span style="color:#fff; width:35px; display: inline-block;">0</span>
						<span style="color:#fff; margin-left: 18px; width:55px; display: inline-block;">脂肪(%):</span>
						<span style="color:#fff; width:35px; display: inline-block;">0</span>
					</p>
					<p style="margin-top: 5px;">
						<span style="color:#fff; width:55px; display: inline-block;">BMR:</span>
						<span style="color:#fff; width:35px; display: inline-block;">0</span>
						<span style="color:#fff; margin-left: 18px; width:55px; display: inline-block;">BMI:</span>
						<span style="color:#fff; width:35px; display: inline-block;">0</span>
					</p>
					<p style="margin-top: 5px;">
						<span style="color:#fff; width:65px; display: inline-block;">内脂肪(%):</span>
						<span style="color:#fff; width:35px; display: inline-block;">0</span>
					</p>
				</div>
			</div>
		</div>
		
		
	</div>		
	
	<script src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
	<script src="<%=basePath %>back/js/health/healthData.js"></script>
</body>
</html>
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
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>

<style>
			*{margin:0; padding:0;}
			.container{width:100%; position: absolute; top:0; bottom: 0; overflow: hidden;}
			.container #main1{width:49%; height:100%; float:left; border-right:1px solid #ccc;}
			.container #main2{width:49%; height:100%; float: right;}
		</style>
		<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
		<script src="<%=basePath %>back/js/echarts.min.js"></script>
		
</head>
<body>
<div class="container">
			<div id="main1">
				
			</div>
			<div id="main2">
				
			</div>
		</div>
		
		<script>
			$(function(){
				$("#main1").width($(window).width()/2);
				$("#main1").height($(window).height());
				$("#main2").width($(window).width()/2.01);
				$("#main2").height($(window).height());
				$(window).resize(function(){
					$("#main1").width($(window).width()/2);
					$("#main1").height($(window).height());
					$("#main2").width($(window).width()/2.01);
					$("#main2").height($(window).height());
					eChartsOld();
					eChartsAge();
				})
				eChartsOld();
				eChartsAge();
			});
			//按年龄统计
			function eChartsOld(){
				var myChart = echarts.init(document.getElementById("main1"));
				$.ajax({
					url:basePath + "static/js/old.json",
					type:"GET",
					async:false,
					error:function(){
						alert("error");
					},
					success:function(data){
						var oData = eval(data);
						var info1 = [];
						var info2 = [];
						for(var i=0;i<oData.length;i++){
							info1.push(oData[i].name)
							info2.push({value:""+oData[i].value+"",name:""+oData[i].name+""})
						}
						myChart.setOption({
							title:{
								text:"按年龄统计",
								x:"center"
							},
							tooltip:{
								trigger:"item",
								formatter:"{a} <br/>{b} : {c} ({d}%)"
							},
							legend:{
								orient:"verticle",
								left:"left",
								data:info1
							},
							series:[
								{
									name:'按年龄统计',
									type:'pie',
									radius:'55%',
									data:info2,
									itemStyle:{
										normal:{
											label:{
												show:true,
												formatter:"{b} : {c} ({d}%)"
											},
											labelLine:{show:true}
										}
									}
								}
							]
						})
					}
				});
			}
			//按男女统计
			function eChartsAge(){
				var myChart = echarts.init(document.getElementById("main2"));
				option = {
					title:{
						text:"按男女统计（1000人）",
						x:"center"
					},
				    color: ['#3398DB'],
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis : [
				        {
				            type : 'category',
				            data : ['男', '女'],
				            axisTick: {
				                alignWithLabel: true
				            }
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'所占比例',
				            type:'bar',
				            barWidth: '30%',
				            data:[580,420],
				            itemStyle:{
				            	normal:{
					            	label:{
					            		show:true,
					            		position:'inside',
					            		formatter:"{c}人"
					            	}
				            	}
				            }
				        }
				    ]
				};
				myChart.setOption(option);
			}
		</script>

</body>
</html>
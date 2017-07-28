<%@page import="com.common.util.global.ConfigureFile"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="UTF-8">
	<title>机构申请中心</title>
	<link rel="stylesheet" href="<%=basePath %>web/css/reset.css" />
	<link rel="stylesheet" href="<%=basePath %>web/js/plugins/layui/css/layui.css" />
	<link rel="stylesheet" href="<%=basePath %>web/css/serviceRegist.css" />
	<link rel="stylesheet" href="<%=basePath %>web/css/public.css" />
	
	<script type="text/javascript" src="<%=basePath %>web/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>web/js/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="<%=basePath %>web/js/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath %>web/js/ProvinceAndCityJson.js"></script>
	<script type="text/javascript" src="<%=basePath %>web/js/serviceRegist.js"></script>
	<!-- 谷歌地图api -->
	<script src="http://maps.google.cn/maps?file=api&v=2&sensor=false&key=<%=ConfigureFile.google_key %>" type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath %>web/js/sight_spot.js"></script>
	<script type="text/javascript">
		var basePath = "<%=basePath %>";
	</script>
</head>
<body onload="GoogleInitialize();" onunload="GUnload();">
	<div class="container">
		<!--内容-->
		<div class="content">
			<!-- ****注册****-->
            <div class="register">
            	<div id="container">
            	</div>
                <div class="left">
                    <p>--机构申请信息--</p>
                    <span id="error"></span>
                    <div class="inp_out">
	                  	<span class="title">机构名称：</span>
	                  	<input id="name" class="inp" type="text" placeholder="请输入机构名称"/>
                    </div>
                    <div class="inp_out">
                    	<span class="title">机构地址：</span>
                    	<div id="place">
	                    	<select id="SelectProvince" name="SelectProvince">
				            </select>
				            <select id="SelectCity" name="SelectCity">
				            </select>
				            <select id="SelectArea" name="SelectArea">
				            </select>
                    	</div>
                    </div>
                    <div class="inp_out">
	                    <textarea id="address" class="textarea" name="place" placeholder="请输入详细地址"></textarea>
                    </div>
					<div class="inp_out">
	                    <span style="color:red; margin-left:140px;">* 点击定位按钮后如定位不准确，请拖动右侧地图显示的定位图标更改位置</span>
                    </div>
                    <div class="inp_out">
	                    <a href='javascript:;' class='layui-btn layui-btn-small' style="margin:0 0 0 140px; font-family:'微软雅黑';" onclick=placePosition()>定位</a>
					</div>
                    <div class="inp_out">
                    	<span class="title">联系人姓名：</span>
	                    <input id="linkName" class="inp" type="text" placeholder="请输入联系人姓名"/>
                    </div>
                    <div class="inp_out">
                    	<span class="title">联系人电话：</span>
	                    <input id="linkPhone" class="inp" type="text" placeholder="请输入联系人电话"/>
                    </div>
                    <div class="inp_out">
                    	<span class="title">机构创建时间：</span>
	                    <input id="creationTime" class="inp" type="text" placeholder="请设置登录密码" onClick="WdatePicker()"/>
                    </div>
                    <div class="inp_out">
                    	<span class="title">邮箱：</span>
	                    <input id="email" class="inp" style="float:left; margin-right:40px;" type="text" placeholder="请输入联系人电话"/>
                    </div>
                    <div class="inp_out">
                    	<span class="title">xx资质上传：</span>
                    	<div class="site-demo-upload" style="width:150px; height:30px; float:left; margin:10px 0 0 20px;">
							<div class="site-demo-upbar">
							  <input type="file" name="file" class="layui-upload-file no1" id="test">
							</div>
						</div>
                    </div>
                    <div class="inp_out add_img">
                    	<div class="img_out img_out1">
                    		<%-- <div class="img">
                    			<a href="javascript:;" class="del">删 除</a>
                    			<div class="face"></div>
		                    	<img src="<%=basePath %>web/images/doctor1.png" alt="" />
                    		</div>
                    		<div class="img">
                    			<a href="javascript:;" class="del">删 除</a>
                    			<div class="face"></div>
		                    	<img src="<%=basePath %>web/images/doctor1.png" alt="" />
                    		</div> --%>
                    	</div>
                    </div>
                    <div class="inp_out">
                    	<span class="title">xx资质上传：</span>
                    	<div class="site-demo-upload" style="width:150px; height:30px; float:left; margin:10px 0 0 20px;">
							<div class="site-demo-upbar">
							  <input type="file" name="file" class="layui-upload-file no2" id="test">
							</div>
						</div>
                    </div>
                    <div class="inp_out add_img">
                    	<div class="img_out img_out2">
                    		
                    	</div>
                    </div>
                    <div class="inp_out">
                    	<span class="title">xx资质上传：</span>
                    	<div class="site-demo-upload" style="width:150px; height:30px; float:left; margin:10px 0 0 20px;">
							<div class="site-demo-upbar">
							  <input type="file" name="file" class="layui-upload-file no3" id="test">
							</div>
						</div>
                    </div>
                    <div class="inp_out add_img">
                    	<div class="img_out img_out3">
                    		
                    	</div>
                    </div>
                    <input type="button" id="oBtn1" onclick="submit_info()" value="确认提交" />
                </div>
            </div>
		</div>
	</div>		
	<input type="hidden" id="googleLat"/>
    <input type="hidden" id="googleLng"/>
	<input type="hidden" id="certificate1" value="">
	<input type="hidden" id="certificate2" value="">
	<input type="hidden" id="certificate3" value="">
	<input type="hidden" id="areaId" value="">
	<input type="hidden" id="flag" value="">
</body>
<script type="text/javascript">
 	var layer;
	layui.use(['element', 'layer','upload'], function() {
		var element = layui.element(),
			layer = layui.layer;
			$ = layui.jquery,
			upload = layui.upload;
		layui.upload({
			elem: '.no1',
			url: '<%=basePath %>register/uploadFile.do',
			success: function(res){
				str = '<div class="img">';
				str += '<a href="javascript:;" flag="1" class="del">删 除</a>';
				str += '<div class="face"></div>';
				str += '<img src="<%=basePath %>';
				str += res.data;
				str += '" alt="" /></div>';
    			$(".img_out1").html(str);
    			$("#certificate1").val(res.data);
			}
		}); 
		layui.upload({
			elem: '.no2',
			url: '<%=basePath %>register/uploadFile.do',
			success: function(res){
				str = '<div class="img">';
				str += '<a href="javascript:;" flag="2" class="del">删 除</a>';
				str += '<div class="face"></div>';
				str += '<img src="<%=basePath %>';
				str += res.data;
				str += '" alt="" /></div>';
				$(".img_out2").html(str);
				$("#certificate2").val(res.data);
			}
		}); 
		layui.upload({
			elem: '.no3',
			url: '<%=basePath %>register/uploadFile.do',
			success: function(res){
				str = '<div class="img">';
				str += '<a href="javascript:;" flag="3" class="del">删 除</a>';
				str += '<div class="face"></div>';
				str += '<img src="<%=basePath %>';
				str += res.data;
				str += '" alt="" /></div>';
				$(".img_out3").html(str);
				$("#certificate3").val(res.data);
			}
		}); 
	});
	$(function() {
		$("#flag").val("${flag}");
	});
</script>
</html>

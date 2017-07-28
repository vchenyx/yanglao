<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<link rel="stylesheet" href="<%=basePath %>back/css/datemore.css" />
<style>
	.img{width:auto; height:200px; float:left; margin-right:10px; position: relative; overflow: hidden;}
	.img .face{display:none; width:100%; height:200px; position: absolute; top:0; left:0; background: #000; opacity: 0.4; filter:alpha(opacity=40)}
	.img .del{display:none; width:70px; height:30px; position: absolute; top:90px; left:50%; margin-left:-35px; background: #009688; z-index: 2; text-align: center; color:#fff; line-height: 30px;}
	.img img{height:200px;}
</style>
<script type="text/javascript">
	var basePath = "<%=basePath %>";
</script>
</head>
<body>
	<form class="layui-form">
		<input type="hidden" id="oldId">
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务单号：</label>
			<div class="layui-input-block">
				<input type="text"  id="orderId" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
			</div>
		</div>
		<c:if test="${userType != 51 }">
			<div class="layui-form-item" style="margin-top:10px; margin-bottom: 0px">
				<label class="layui-form-label" style="width:80px;margin-top: 0px; padding-top: 0px"><font color="red" style="size:2px" >*</font>服务站点：</label>
				<div class="layui-input-inline"style="width:470px;">
					<select id="stationId">
	        			<option value="">--请选择--</option>
	       			</select>
				</div>
			</div>
		</c:if>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>老人姓名：</label>
			<div class="layui-input-block">
				<input type="text" id="name" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>联系电话：</label>
			<div class="layui-input-block">
				<input type="text"  id="phone" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:10px; margin-bottom: 0px">
			<label class="layui-form-label" style="width:80px;margin-top: 0px; padding-top: 0px"><font color="red" style="size:2px" >*</font>所属小区：</label>
			<div class="layui-input-inline"style="width:470px;">
				<select id="community" lay-filter="community">
        			<option value="">--请选择--</option>
       			</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>详细地址：</label>
			<div class="layui-input-block">
				<input type="text" id="address" placeholder="请输入详细地址" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:10px; margin-bottom: 0px">
			<label class="layui-form-label" style="width:80px;margin-top: 0px; padding-top: 0px"><font color="red" style="size:2px" >*</font>服务类别：</label>
			<div class="layui-input-inline"style="width:470px;">
				<select id="providerType" lay-filter="providerType">
        			<option value="">--请选择--</option>
       			</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:10px; margin-bottom: 0px">
			<label class="layui-form-label" style="width:80px;margin-top: 0px; padding-top: 0px"><font color="red" style="size:2px" >*</font>服务商：</label>
			<div class="layui-input-inline"style="width:470px;">
				<select id="provider" lay-filter="provider">
        			<option value="">--请选择--</option>
       			</select>
			</div>
		</div>
		
		<div class="layui-form-item" style="margin-top:20px;">
			<div class="layui-field-box" id="serviceItems">
			</div>
		</div>
		
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>配送费：</label>
			<div class="layui-input-block">
				<input type="text" id="deliveryCost" placeholder="请输入单笔配送费" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
			</div>
		</div>
		
		<!-- <div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>服务日期：</label>
			<div class="layui-input-block">
				<input class="layui-input" id="dates" placeholder="请选择服务日期" onclick="layui.laydate({elem:this,istime:true, format:'YYYY-MM-DD'})" style="width:470px;"/>
			</div>
		</div> -->
	</form>
	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	<script type="text/javascript"  src="<%=basePath %>back/js/datemore.js"></script> 
	
	<script type="text/javascript">
		var layer;
		var form;
		layui.config({
			base: '<%=basePath %>manager/js/'
		}).use(['element', 'layer','form','upload','laydate'], function() {
			layer = parent.layer === undefined ? layui.layer : parent.layer;
			form = layui.form();
			var element = layui.element(),
				$ = layui.jquery,
				laydate = layui.laydate,
				upload = layui.upload;
			
			form.on('select(providerType)',function(data){
				getAllProvider(data.value);
				form.render();
			});
			form.on('select(provider)',function(data){
				getAllServiceItems(data.value);
				form.render();
			});
		});
		
		$(function() {
			var userType = "${userType }";
			if (userType != 51) {
				getAllStation();
			}
			getAllCommunity();
			getAllProviderType();
		});
		
		//stationId
		function getAllStation() {
			$.ajax({
				url: basePath + "call/getAllStation.do",
				type: "post",
				data: {
					"random": Math.random()
				},
				async: false,
				dataType: "json",
				success: function(data) {
					var str = '<option value="">--请选择--</option>';
					for (var i = 0; i < data.length; i++) {
						var obj = data[i];
						str += '<option value="' + obj.id + '">' + obj.name + '</option>';
					}
					$("#stationId").html(str);
				}
			});
		}
		function getAllProviderType() {
			$.ajax({
				url: basePath + "provider/getAllProviderType.do",
				type: "post",
				data: {
					"random": Math.random()
				},
				async: false,
				dataType: "json",
				success: function(data) {
					var str = '<option value="">--请选择--</option>';
					for (var i = 0; i < data.length; i++) {
						var obj = data[i];
						str += '<option value="' + obj.id + '">' + obj.name + '</option>';
					}
					$("#providerType").html(str);
					form.render();
				}
			});
		}
		
		function getAllProvider(id) {
			$.ajax({
				url: basePath + "provider/getAllProviderByTypeId.do",
				type: "post",
				data: {
					"typeId": id,
					"random": Math.random()
				},
				async: false,
				dataType: "json",
				success: function(data) {
					var str = '<option value="">--请选择--</option>';
					for (var i = 0; i < data.length; i++) {
						var obj = data[i];
						str += '<option value="' + obj.id + '">' + obj.name + '</option>';
					}
					$("#provider").html(str);
				}
			});
		}
		function getAllCommunity() {
			$.ajax({
				url: basePath + "community/getCommunityList.do",
				type: "post",
				async: false,
				dataType: "json",
				success: function(data) {
					if (data.length > 0) {
						var str = '<option value="">--请选择--</option>';
						for (var i = 0; i < data.length; i++) {
							var obj = data[i];
							str += '<option value="' + obj.id + '&' + obj.address + '">' + obj.name + '</option>';
						}
						$("#community").html(str);
					} else {
						layer.alert("暂无社区信息，请在‘社区管理’模块中添加！");
					}
				}
			});
		}
		
		function getAllServiceItems(id) {
			$.ajax({
				url: basePath + "provider/gatAllServiceItems.do",
				type: "post",
				data: {
					"providerId": id,
					"random": Math.random()
				},
				async: false,
				dataType: "json",
				success: function(data) {
					if (data.length > 0) {
						var str = '<table class="site-table table-hover">';
							str += '<thead>';
							str += '<tr>';
							str += '<th width="20%">服务项目</th>';
							str += '<th width="20%">服务内容</th>';
							str += '<th width="30%">服务日期</th>';
							str += '<th width="5%">价格</th>';
							str += '<th width="5%">单位</th>';
							str += '<th width="10%">数量</th>';
							str += '</tr>';
							str += '</thead>';
							str += '<tbody id="prov_tbd">';
							
							var numDiv = "<img width='20px' onclick='minusNum(this)' src='" + basePath + "back/images/jian.png' >"
										+ '<input type="text" value="0" autocomplete="off" class="layui-input" style="display: inline; width:50px; text-align:center; padding-left: 0px;">'
										+ "<img width='20px' onclick='addNum(this)' src='" + basePath + "back/images/jia.png' >";
							
							for (var i = 0; i < data.length; i++) {
								var obj = data[i];
								str += "<tr name='chooseItems'>";
								str += "<td>" + obj.servicePro + "</td>";
								str += "<td>" + obj.serviceContent + "</td>";
								str += "<td>" + '<input type="text" placeholder="请选择服务日期" readonly="readonly" onclick="addTime(this)" autocomplete="off" class="layui-input" style="width:261px; margin-right:30px;">' + "</td>";
								str += "<td>" + obj.unitPrice + "</td>";
								str += "<td>" + obj.serviceUnit + "</td>";
								str += "<td>" + numDiv + "</td>";
								str += "</tr>";
							}
							str += '</tbody>';
							str += '</table>';
							$("#serviceItems").html(str);
					} else {
						layer.alert("暂无服务，请选择其他服务商！");
					}
				}
			});
		}
		
		function minusNum(event) {
			var val = parseInt($(event).next().val());
			if (val == 0) {
			} else {
				$(event).next().val(val - 1);
			}
		}
		
		function addNum(event) {
			var val = parseInt($(event).prev().val());
			$(event).prev().val(val + 1);
		}
		
	</script>
</body>
</html>
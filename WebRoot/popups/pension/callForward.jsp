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
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
</head>
<body>
	<form class="layui-form">
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>工单号：</label>
			<div class="layui-input-block">
				<input type="text" id="peopleName" placeholder="请输入工单号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>受理部门：</label>
			<div class="layui-input-block">
				<input type="text" id="peopleName" placeholder="请输入受理部门" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>接单人：</label>
			<div class="layui-input-block">
				<input type="text" id="age" lay-verify="pass" placeholder="请输入接单人" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;">接单时间：</label>
			<div class="layui-input-block">
				<input class="layui-input" placeholder="接单时间" onclick="layui.laydate({elem:this,istime:true})" style="width:400px;"/>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>来电人姓名：</label>
			<div class="layui-input-block">
				<input type="text" id="cellphone" placeholder="请输入来电人姓名" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>来电人电话：</label>
			<div class="layui-input-block">
				<input type="text" id="cellphone" placeholder="请输入来电人电话" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>预约对象：</label>
			<div class="layui-input-block">
				<input type="radio" value="0" id="sex"  name="yiyuan" title="合作医院" checked/>
				<input type="radio" value="1" id="sex1" name="yiyuan" title="其他医院"/>
				<input type="radio" value="1" id="sex1" name="yiyuan" title="家庭医生"/>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;">预约医院：</label>
			<div class="layui-input-block"  style="width:400px;">
				<select>
        			<option value="1" id="upd_edu1">医院一</option>
       				<option value="2" id="upd_edu2">医院二</option>
       			</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;">一级科室：</label>
			<div class="layui-input-block"  style="width:400px;">
				<select>
        			<option value="1" id="upd_edu1">一级科室一</option>
       				<option value="2" id="upd_edu2">一级科室二</option>
       			</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;">二级科室：</label>
			<div class="layui-input-block"  style="width:400px;">
				<select>
        			<option value="1" id="upd_edu1">二级科室一</option>
       				<option value="2" id="upd_edu2">二级科室二</option>
       			</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;">选择医生：</label>
			<div class="layui-input-block"  style="width:400px;">
				<select>
        			<option value="1" id="upd_edu1">医生一</option>
       				<option value="2" id="upd_edu2">医生二</option>
       			</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;">预约日期：</label>
			<div class="layui-input-block">
				<input class="layui-input" placeholder="预约日期" onclick="layui.laydate({elem:this,istime:true})" style="width:400px;"/>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>预约时间：</label>
			<div class="layui-input-block">
				<input type="radio" value="0" id="sex"  name="time" title="上午" checked/>
				<input type="radio" value="1" id="sex1" name="time" title="下午"/>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>备注：</label>
			<div class="layui-input-block">
				<textarea name="" id="" style="width:400px; height:100px; margin-right:30px;"></textarea>
			</div>
		</div>		
	</form>
	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	
	<script type="text/javascript">
			layui.use(['element', 'layer','form','laydate'], function() {
				var element = layui.element(),
					$ = layui.jquery,
					layer = layui.layer,
					laydate = layui.laydate(),
					form = layui.form();
//				
			});
			
		</script>
</body>
</html>
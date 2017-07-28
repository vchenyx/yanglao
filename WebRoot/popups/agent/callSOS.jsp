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
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<style>
	.admin-table-page {
	    position: absolute;
	    z-index: 19940201;
	    top: 155px;
	    height:45px;
	    width: 100%;
	    background-color: #eee;
	    border-bottom: 1px solid #ddd;
	    left: 0px;
	}
</style>
</head>
<body>
	<div class="layui-form-item" style="margin-top:20px;">
		<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>工单号：</label>
		<div class="layui-input-block">
			<input type="text" id="peopleName" placeholder="请输入工单号" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
		</div>
	</div>
	<fieldset class="layui-elem-field">
		<legend>紧急联系人</legend>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>家庭电话：</label>
			<div class="layui-input-block">
				<input type="text" id="cellphone" placeholder="请输入家庭电话" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>紧急联系人：</label>
			<div class="layui-input-block">
				<input type="text" id="cellphone" placeholder="请输入联系人" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>联系电话：</label>
			<div class="layui-input-block">
				<input type="text" id="cellphone" placeholder="请输入联系电话" autocomplete="off" class="layui-input" style="width:400px; margin-right:30px;">
			</div>
		</div>
	</fieldset>	
	<fieldset class="layui-elem-field" style="position: relative;">
		<legend>亲属信息</legend>
		<table class="site-table table-hover" style="margin-bottom: 100px;">
			<thead>
				<tr>
					<th><input type="checkbox" id="selected-all"></th>
					<th>亲属姓名</th>
					<th>联系电话</th>
					<th>与老人关系</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox"></td>
					<td>高庶民</td>
					<td>15896425897</td>
					<td>儿子</td>
					<td>
						<a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="admin-table-page">
			<div id="page" class="page">
			</div>
		</div>
	</fieldset>
	<fieldset class="layui-elem-field">
		<legend>紧急报警电话</legend>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:50px; color:red; text-align: center;">报警：</label>
			<label class="layui-form-label" style="width:50px; text-align: center; border-right:1px solid #ccc;">110</label>
			<label class="layui-form-label" style="width:50px; text-align: center; color:red;">火警：</label>
			<label class="layui-form-label" style="width:50px; text-align: center; border-right:1px solid #ccc;">119</label>
			<label class="layui-form-label" style="width:50px; text-align: center; color:red;">急救：</label>
			<label class="layui-form-label" style="width:50px; text-align: center; border-right:1px solid #ccc;">120</label>
			<label class="layui-form-label" style="width:50px; text-align: center; color:red;">交警：</label>
			<label class="layui-form-label" style="width:50px; text-align: center; border-right:1px solid #ccc;">122</label>
			<label class="layui-form-label" style="width:50px; text-align: center; color:red;">消协：</label>
			<label class="layui-form-label" style="width:50px; text-align: center;">12315</label>
		</div>
	</fieldset>	
	<fieldset class="layui-elem-field">
		<legend>事件记录</legend>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>事件记录：</label>
			<div class="layui-input-block">
				<textarea name="" id="" style="width:400px; height:100px; margin-right:30px;"></textarea>
			</div>
		</div>
		<form class="layui-form">
			<div class="layui-form-item" style="margin-top:20px;">
				<label class="layui-form-label" style="width:80px;">处理状态：</label>
				<div class="layui-input-block"  style="width:400px;">
					<select>
	        			<option value="1" id="upd_edu1">未处理</option>
	       				<option value="2" id="upd_edu2">已处理</option>
	       			</select>
				</div>
			</div>
		</form>
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>处理办法：</label>
			<div class="layui-input-block">
				<textarea name="" id="" style="width:400px; height:100px; margin-right:30px;"></textarea>
			</div>
		</div>
	</fieldset>
	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	
	<script type="text/javascript">
			layui.config({
				base: '<%=basePath %>back/plugins/layui/modules/'
			}).use(['element', 'layer','form','laydate','icheck','laypage'], function() {
				var element = layui.element(),
					$ = layui.jquery,
					layer = layui.layer,
					laypage = layui.laypage,
					laydate = layui.laydate(),
					form = layui.form();
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});
				
				//page
				laypage({
					cont: 'page',
					pages: 25,
					groups: 5,
					jump: function(obj, first) {
						var curr = obj.curr;
						if(!first) {
//							layer.msg('第 '+ obj.curr +' 页');
						}
					}
				});
				
				
				$('.site-table tbody tr').on('click', function(event) {
					var $this = $(this);
					var $input = $this.children('td').eq(0).find('input');
					$input.on('ifChecked', function(e) {
						$this.css('background-color', '#EEEEEE');
					});
					$input.on('ifUnchecked', function(e) {
						$this.removeAttr('style');
					});
					$input.iCheck('toggle');
				}).find('input').each(function() {
					var $this = $(this);
					$this.on('ifChecked', function(e) {
						$this.parents('tr').css('background-color', '#EEEEEE');
					});
					$this.on('ifUnchecked', function(e) {
						$this.parents('tr').removeAttr('style');
					});
				});
				$('#selected-all').on('ifChanged', function(event) {
					var $input = $('.site-table tbody tr td').find('input');
					$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
				});

			});
			
		</script>
</body>
</html>
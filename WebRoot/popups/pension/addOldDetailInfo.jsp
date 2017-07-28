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
	<meta charset="UTF-8">
	<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
	<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
	<script type="text/javascript"  src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
	<style>
		#left{width:49.8%; height: 760px; float:left;}
		#right{width:49.8%; height: 760px; float:right;}
		.img{width:auto; height:200px; float:left; margin-right:10px; position: relative; overflow: hidden;}
		.img .face{display:none; width:100%; height:200px; position: absolute; top:0; left:0; background: #000; opacity: 0.4; filter:alpha(opacity=40)}
		.img .del{display:none; width:70px; height:30px; position: absolute; top:80px; left:50%; margin-left:-35px; background: #009688; z-index: 2; text-align: center; color:#fff; line-height: 30px;}
		.img img{height:200px;}
		.place select{height:35px;}
		.place select option{height:35px;}
		
		.top{width:100%; height:50px; border-bottom: 1px solid #ccc; position: absolute; top:0; background: #fff; z-index: 2;}
		.bottom{position: absolute; top:50px; bottom:0; overflow: hidden; width:100%;}
		.d1,.d2,.d3{width:100%; height:100%; overflow: auto;}
		.floor1,.floor2,.floor3{width:200px; height:50px; float:left; text-align: center; line-height: 50px; border-right:1px solid #ccc; cursor: pointer; font-size: 16px; font-family: "微软雅黑";}
		.active{background: #ccc; color: #fff;}
		
		.site-table tbody td {
		    padding: 0;
		    min-height: 20px;
		    line-height: 20px;
		    border: 1px solid #ddd;
		    font-size: 14px;
		    font-weight: 400;
		     padding: 0 0;
		}
		.site-table tbody td input{width:100%; height:100%; border: 1px solid #ddd; border-top: none; border-bottom: none; border-left: none; text-align: center; height:30px}
	</style>
	<script>
		var basePath = "<%=basePath %>";
		$(function(){
			//疾病用药情况导航栏切换
			$(".top div").click(function(){
				var num = $(this).attr("class").split("r")[1];
				$(this).addClass("active").siblings().removeClass("active");
				$(".bottom .d1").css("display","none");
				$(".bottom .d2").css("display","none");
				$(".bottom .d3").css("display","none");
				$(".bottom .d"+num+"").css("display","block");
			});
			
			layui.use(['element', 'layer','form','laydate','upload'], function() {
				var element = layui.element(),
					$ = layui.jquery,
					layer = layui.layer,
					upload = layui.upload,
					laydate = layui.laydate,
					form = layui.form();
				//是否有锻炼
				form.on('radio(duanlian)',function(data){
					if(data.value == 1){
						$("#duanlian").css("display","block");
					}else{
						$("#duanlian").css("display","none");
					}
				})
				
				//家务如何分配
				form.on('checkbox(jiawu)',function(data){
					if(data.value == 5){
						if (data.elem.checked == true) {
							$(".oJiaWu").css("display","block");
						}else{
							$(".oJiaWu").css("display","none");
						}
					}
				})
				//主食
				form.on('checkbox(zhushi)',function(data){
					if(data.value == 3){
						if (data.elem.checked == true) {
							$(".oZhuShi").css("display","block");
						}else{
							$(".oZhuShi").css("display","none");
						}
					}
				})
				//副食
				form.on('checkbox(fushi)',function(data){
					if(data.value == 3){
						if (data.elem.checked == true) {
							$(".oFuShi").css("display","block");
						}else{
							$(".oFuShi	").css("display","none");
						}
					}
				})
				//食物过敏
				form.on('checkbox(shiwuguomin)',function(data){
					if(data.value == 4){
						if (data.elem.checked == true) {
							$(".oShiWuGuoMin").css("display","block");
						}else{
							$(".oShiWuGuoMin	").css("display","none");
						}
					}
				})
				//锻炼方式
				form.on('checkbox(duanlianfangshi)',function(data){
					if(data.value == 4){
						if (data.elem.checked == true) {
							$(".oDuanLianFangShi").css("display","block");
						}else{
							$(".oDuanLianFangShi").css("display","none");
						}
					}
				})
				//家庭主要照护者
				form.on('checkbox(zhaohu)',function(data){
					if(data.value == '其他'){
						if (data.elem.checked == true) {
							$(".oZhaoHu").css("display","block");
						}else{
							$(".oZhaoHu").css("display","none");
						}
					}
				})
				//医疗服务类
				form.on('checkbox(yiliao)',function(data){
					console.log(data)
					if(data.value == "其他"){
						if (data.elem.checked == true) {
							$(".oYiLiao").css("display","block");
						} else {
							$(".oYiLiao").css("display","none");
						}
					}
				})
				//安全类
				form.on('checkbox(anquan)',function(data){
					console.log(data.elem.checked)
					if(data.value == "其他"){
						if (data.elem.checked == true) {
							$(".oAnQuan").css("display","block");
						} else {
							$(".oAnQuan").css("display","none");
						}
					}
				})
			})
			
		})
		//添加处方用药
		function addYongYao(){
			var html = '<tr>'
					 + '<td><input type="text"/></td>'
					 + '<td><input type="text"/></td>'
					 + '<td><input type="text"/></td>'
					 + '<td><input type="text"/></td>'
					 + '<td><input type="text"/></td>'
					 + '<td><input type="text"/></td>'
					 + '<td><span style="cursor: pointer; color:red" onclick="javascript:delYongYao(this);"><i class="layui-icon">&#xe640;</i></span></td>'
					 + '</tr>'
			$("tbody").append(html)
		}
		//删除处方用药
		function delYongYao(dt){
			$(dt).parent().parent().remove();
		}
	</script>
</head>
<body>
	<div class="top">
		<div class="floor1 active">个人基本信息</div>
		<div class="floor2">生活能力评估</div>
		<div class="floor3">疾病用药情况</div>
	</div>
	<div class="bottom">
		<div class="d1" style="display: block;">
			<div class="layui-form">
				<div id="left">
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>姓名：</label>
						<div class="layui-input-block">
							<input type="text" id="peopleName" placeholder="请输入人员姓名" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>性别：</label>
						<div class="layui-input-block">
							<input type="radio" value="0" id="sex"  name="sex" title="男" checked/>
							<input type="radio" value="1" id="sex1" name="sex" title="女"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>出生日期：</label>
						<div class="layui-input-block">
							<input class="layui-input" id="birthday" placeholder="出生日期" onclick="layui.laydate({elem:this,istime:true})" style="width:470px;"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>所属小区：</label>
						<div class="layui-input-block" style="width:470px;">
							<select id="community" lay-filter="community">
								<option value="">--请选择--</option>
			       			</select>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>家庭住址：</label>
						<div class="layui-input-block">
							<input type="text" id="address" placeholder="请输入详细地址" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>户籍地址：</label>
						<div class="layui-input-block">
							<input type="text" id="cencusAddr" placeholder="请输入户籍地址" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>家庭电话：</label>
						<div class="layui-input-block">
							<input type="text" id="cellphone" placeholder="请输入家庭电话" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>紧急联系人：</label>
						<div class="layui-input-block">
							<input type="text" id="linkName" placeholder="请输入紧急联系人姓名" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>紧急联系人电话：</label>
						<div class="layui-input-block">
							<input type="text" id="linkPhone" placeholder="请输入紧急联系人电话" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>常住类型：</label>
						<div class="layui-input-block">
							<input type="radio" value="0" id="huji"  name="huji" title="户籍" checked/>
							<input type="radio" value="1" id="feihuji" name="huji" title="非户籍"/>
						</div>
					</div>
					
				</div>
				<div id="right" style="margin-bottom: 20px;">
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>民族：</label>
						<div class="layui-input-block">
							<input type="text" id="nation" placeholder="请输入民族" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>职业：</label>
						<div class="layui-input-block">
							<input type="radio" value="退休" id="workState"  name="workState" title="退休" checked/>
							<input type="radio" value="离休" id="workState1" name="workState" title="离休"/>
							<input type="radio" value="在职" id="workState2" name="workState" title="在职"/>
							<input type="text" id="work" placeholder="请输入职业" autocomplete="off" class="layui-input" style="display: inline;width:223px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>老人类别：</label>
						<div class="layui-input-inline" style="width:470px;">
							<select id="oldType" lay-filter="oldType">
								<option value="">--请选择--</option>
			        			<option value="托底">托底</option>
			       				<option value="扶助">扶助</option>
			       				<option value="普惠">普惠</option>
			       				<option value="其他">其他</option>
			       			</select>
						</div>
						<div class="oldTypeOther" style="display: none; margin-left: 110px">
							<div class="layui-input-inline">
								<input type="text" id="oldTypeOther" placeholder="请输入老人类别" class="layui-input" style="width:470px; margin-right:30px;">
							</div>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>收入/困难：</label>
						<div class="layui-input-block">
							<input type="text" id="income" placeholder="请输入收入/困难情况" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>婚姻状况：</label>
						<div class="layui-input-inline" style="width:470px;">
							<select id="maritalStatus" lay-filter="maritalStatus">
			        			<option value="">--请选择--</option>
			        			<option value="已婚">已婚</option>
			       				<option value="未婚">未婚</option>
			       				<option value="离异">离异</option>
			       				<option value="丧偶">丧偶</option>
			       			</select>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>居家养老情况：</label>
						<div class="layui-input-inline" style="width:470px;">
							<select id="yanglao" lay-filter="yanglao">
								<option value="">--请选择--</option>
			        			<option value="两位老人">两位老人</option>
			       				<option value="独居">独居</option>
			       				<option value="子女陪伴">子女陪伴</option>
			       				<option value="保姆照料(住家)">保姆照料(住家)</option>
			       				<option value="保姆照料(不住家)">保姆照料(不住家)</option>
			       				<option value="其他">其他</option>
			       			</select>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>医疗费用支付方式：</label>
						<div class="layui-input-inline"style="width:470px;">
							<select lay-filter="zhifu" id="zhifu">
								<option value="">--请选择--</option>
			        			<option value="全公费">全公费</option>
			       				<option value="部分公费">部分公费</option>
			       				<option value="城镇职工医疗保险">城镇职工医疗保险</option>
			       				<option value="城镇居民医疗保险">城镇居民医疗保险</option>
			       				<option value="商业医疗保险">商业医疗保险</option>
			       				<option value="新型农村合作医疗">新型农村合作医疗</option>
			       				<option value="贫困救助">贫困救助</option>
			       				<option value="全自费">全自费</option>
			       				<option value="其他">其他</option>
			       			</select>
						</div>
						<div class="zhifu" style="display: none;">
							<div class="layui-input-inline">
								<input type="text" id="payTarget" placeholder="请输入支付方式" class="layui-input" style="width:470px; margin-right:30px;">
							</div>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>血型：</label>
						<div class="layui-input-block"  style="width:470px;">
							<select id="bloodType">
								<option value="">--请选择--</option>
			        			<option value="A">A</option>
			       				<option value="B">B</option>
			       				<option value="O">O</option>
			       				<option value="AB">AB</option>
			       				<option value="RH阴性">RH阴性</option>
			       				<option value="RH阳性">RH阳性</option>
			       				<option value="不详">不详</option>
			       			</select>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:10px; margin-bottom: 0px">
						<label class="layui-form-label" style="width:80px;margin-top: 0px; padding-top: 0px"><font color="red" style="size:2px" >*</font>药物&nbsp;&nbsp;&nbsp;&nbsp;<br>过敏史：</label>
						<div class="layui-input-inline"style="width:470px;">
							<select id="guomin" lay-filter="guomin">
								<option value="">--请选择--</option>
			        			<option value="无">无</option>
			       				<option value="青霉素">青霉素</option>
			       				<option value="磺胺">磺胺</option>
			       				<option value="链霉素">链霉素</option>
			       				<option value="其他">其他</option>
			       			</select>
						</div>
					</div>
					<div class="guomin" style="display: none; margin-top: 0px">
						<div class="layui-input-block">
							<input type="text" id="guominOther" placeholder="请输入药物过敏史" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:10px; margin-bottom: 0px; height: 49px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>暴露史：</label>
						<div class="layui-input-inline"style="width:470px;">
							<select id="baolu" lay-filter="baolu">
								<option value="">--请选择--</option>
			        			<option value="无">无</option>
			       				<option value="化学品">化学品</option>
			       				<option value="毒物">毒物</option>
			       				<option value="射线">射线</option>
			       				<option value="其他">其他</option>
			       			</select>
						</div>
					</div>
					<div class="baolu" style="display: none; margin-top: 0px">
						<div class="layui-input-block">
							<input type="text" id="baoluOther" placeholder="请输入暴露史" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					
					
					<div class="layui-form-item" style="margin-top:10px;">
						<label class="layui-form-label" style="width:80px;">身份证号：</label>
						<div class="layui-input-block">
							<input type="text" id="idCard" placeholder="请输入人员身份证号" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
				</div>
				<fieldset class="layui-elem-field" style="margin-left: 15px;margin-top: 10px;width: 1209px;">
						<legend>既往史</legend>
						<div class="layui-form-item" style="margin-top:20px;">
							<label class="layui-form-label" style="width:80px; padding-left: 0px"><font color="red" style="size:2px" >*</font>疾病：</label>
							<div id="jibing" class="layui-input-block">
								<input type="checkbox" name="jibing" lay-filter='bing' value="高血压" title="高血压">
								<input type="checkbox" name="jibing" lay-filter='bing' value="糖尿病" title="糖尿病">
								<input type="checkbox" name="jibing" lay-filter='bing' value="冠心病" title="冠心病">
								<input type="checkbox" name="jibing" lay-filter='bing' value="恶性肿瘤" title="恶性肿瘤">
								<input type="checkbox" name="jibing" lay-filter='bing' value="脑出血、脑梗塞" title="脑出血、脑梗塞">
								<input type="checkbox" name="jibing" lay-filter='bing' value="慢阻肺" title="慢阻肺">
								<input type="checkbox" name="jibing" lay-filter='bing' value="结核病" title="结核病">
								<input type="checkbox" name="jibing" lay-filter='bing' value="精神分裂症" title="精神分裂症">
								<input type="checkbox" name="jibing" lay-filter='bing' value="肝炎" title="肝炎">
								<input type="checkbox" name="jibing" lay-filter='bing' value="颈椎病、腰椎病" title="颈椎病、腰椎病">
								<input type="checkbox" name="jibing" lay-filter='bing' value="骨性关节炎" title="骨性关节炎">
								<input type="checkbox" name="jibing" lay-filter='bing' value="其他" title="其他">
							</div>
							<div id="timer" style=" margin-bottom: 20px;">
			
							</div>
							<div id="addJibing" class="layui-form-item btn_shoushu" style="margin:20px 0 20px 110px; display: none;">
								<a href="javascript:;" class="layui-btn layui-btn-small" onclick="javascript:addJibingOther();" style="background: #3983e1;">
									<i class="layui-icon">&#xe608;</i> 添加其他疾病
								</a>
							</div>
						</div>
						<div class="layui-form-item" style="margin-top:20px;">
							<label class="layui-form-label" style="width:80px; padding-left: 0px"><font color="red" style="size:2px" >*</font>手术：</label>
							<div class="layui-input-block"style="width:470px;">
								<input type="radio" value="0" name="shoushu" lay-filter="shoushu" id="shoushuwu" title="无" checked/>
								<input type="radio" value="1" name="shoushu" lay-filter="shoushu" id="shoushuyou" title="有"/>
							</div>
							<div>
								<div id="shoushu" style="display: none; margin-left:100px;">
									<div class="layui-form-item shoushu" style="margin-top:20px;">
										<label class="layui-form-label" style="width:80px; padding-left: 0px"><font color="red" style="size:2px" >*</font>手术名称：</label>
										<div class="layui-input-inline" style="width:242px;">
											<input type="text" id="age" placeholder="请输入手术名称" autocomplete="off" class="layui-input" style="width:242px; margin-right:10px;">
										</div>
										<label class="layui-form-label" style="width:50px;"><font color="red" style="size:2px" >*</font>时间：</label>
										<div class="layui-input-inline" style="width:242px;">
											<input type='text' class='layui-input' placeholder='请输入手术时间' style='width:242px;' />
										</div>
										<div class="layui-input-inline" style="margin-top:3px; width: 80px;">
											<a href="javascript:;" class="layui-btn layui-btn-small" onclick="removed(this)" style="background: #3983e1;">
												<i class="layui-icon">&#xe608;</i> 删除
											</a>
										</div>
									</div>
								</div>
								<div class="layui-form-item btn_shoushu" style="margin:20px 0 20px 110px; display: none;">
									<a href="javascript:;" class="layui-btn layui-btn-small" onclick="javascript:addShou();" style="background: #3983e1;">
										<i class="layui-icon">&#xe608;</i> 添加手术历史
									</a>
								</div>
							</div>
						</div>
						<div class="layui-form-item" style="margin-top:20px;">
							<label class="layui-form-label" style="width:80px; padding-left: 0px"><font color="red" style="size:2px" >*</font>重大外伤：</label>
							<div class="layui-input-block"style="width:470px;">
								<input type="radio" value="0" name="waishang" lay-filter="waishang" title="无" checked/>
								<input type="radio" value="1" name="waishang" lay-filter="waishang" id="waishangyou" title="有"/>
							</div>
							<div>
								<div id="waishang" style="display: none; margin-left:100px;">
									<div class="layui-form-item waishang" style="margin-top:20px;">
										<label class="layui-form-label" style="width:80px; padding-left: 0px"><font color="red" style="size:2px" >*</font>外伤名称：</label>
										<div class="layui-input-inline" style="width: 242px">
											<input type="text" id="age" placeholder="请输入外伤名称" autocomplete="off" class="layui-input" style="width:242px; margin-right:30px;">
										</div>
										<label class="layui-form-label" style="width:50px;"><font color="red" style="size:2px" >*</font>时间：</label>
										<div class="layui-input-inline" style="width: 242px">
											<input type='text' class='layui-input' placeholder='请输入患伤时间' style='width:242px;' />
										</div>
										<div class="layui-input-inline" style="margin-top:3px; width: 80px;">
											<a href="javascript:;" class="layui-btn layui-btn-small" onclick="removed(this)" style="background: #3983e1;">
												<i class="layui-icon">&#xe608;</i> 删除
											</a>
										</div>
									</div>
								</div>
								<div class="layui-form-item btn_waishang" style="margin:20px 0 20px 110px; display: none;">
									<a href="javascript:;" class="layui-btn layui-btn-small" onclick="javascript:addWaiShang();" style="background: #3983e1;">
										<i class="layui-icon">&#xe608;</i> 添加外伤历史
									</a>
								</div>
							</div>
						</div>
						<div class="layui-form-item" style="margin-top:20px;">
							<label class="layui-form-label" style="width:80px; padding-left: 0px"><font color="red" style="size:2px" >*</font>输血：</label>
							<div class="layui-input-block"style="width:470px;">
								<input type="radio" value="0" name="shuxue" lay-filter="shuxue" title="无" checked/>
								<input type="radio" value="1" name="shuxue" lay-filter="shuxue" id="shuxueyou" title="有"/>
							</div>
							<div>
								<div id="shuxue" style="display: none; margin-left:100px;">
									<div class="layui-form-item shuxue" style="margin-top:20px;">
										<label class="layui-form-label" style="width:80px; padding-left: 0px"><font color="red" style="size:2px" >*</font>输血原因：</label>
										<div class="layui-input-inline" style="width:242px;">
											<input type="text" id="age" placeholder="请输入输血原因" autocomplete="off" class="layui-input" style="width:242px; margin-right:30px;">
										</div>
										<label class="layui-form-label" style="width:50px;"><font color="red" style="size:2px" >*</font>时间：</label>
										<div class="layui-input-inline" style="width:242px;">
											<input type='text' class='layui-input' placeholder='请输入输血时间' style='width:242px;' />
										</div>
										<div class="layui-input-inline" style="margin-top:3px; width: 80px;">
											<a href="javascript:;" class="layui-btn layui-btn-small" onclick="removed(this)" style="background: #3983e1;">
												<i class="layui-icon">&#xe608;</i> 删除
											</a>
										</div>
									</div>
								</div>
								<div class="layui-form-item btn_shuxue" style="margin:20px 0 20px 110px; display: none;">
									<a href="javascript:;" class="layui-btn layui-btn-small" onclick="javascript:addShuXue();" style="background: #3983e1;">
										<i class="layui-icon">&#xe608;</i> 添加输血历史
									</a>
								</div>
							</div>
						</div>
					</fieldset>
					<fieldset class="layui-elem-field" style="margin-left: 15px;width: 1209px;">
						<legend>家族史</legend>
						<div class="layui-form-item" style="margin-top:20px;">
							<label class="layui-form-label" style="width:80px; padding-left: 0px"><font color="red" style="size:2px" >*</font>父亲：</label>
							<div class="layui-input-block" id="father">
								<input type="checkbox" lay-filter='fu_bing' value="高血压" title="高血压">
								<input type="checkbox" lay-filter='fu_bing' value="糖尿病" title="糖尿病">
								<input type="checkbox" lay-filter='fu_bing' value="冠心病" title="冠心病">
								<input type="checkbox" lay-filter='fu_bing' value="恶性肿瘤" title="恶性肿瘤">
								<input type="checkbox" lay-filter='fu_bing' value="脑卒中（脑出血、脑梗塞）" title="脑卒中（脑出血、脑梗塞）">
								<input type="checkbox" lay-filter='fu_bing' value="先天畸形" title="先天畸形">
								<input type="checkbox" lay-filter='fu_bing' value="结核病" title="结核病">
								<input type="checkbox" lay-filter='fu_bing' value="精神分裂症" title="精神分裂症">
								<input type="checkbox" lay-filter='fu_bing' value="肝炎" title="肝炎">
								<input type="checkbox" lay-filter='fu_bing' value="颈椎病、腰椎病" title="颈椎病、腰椎病">
							</div>
							
							
							<label class="layui-form-label" style="width:80px; margin: 10px 0 10px 60px;">其他：</label>
							<div class="layui-input-block" style="margin: 10px 0 10px 20px;">
								<input type="text" id="fatherOther" placeholder="请输入疾病" autocomplete="off" class="layui-input" style="width:420px;">
							</div>
							<label class="layui-form-label" style="width:80px; padding-left: 0px"><font color="red" style="size:2px" >*</font>母亲：</label>
							<div class="layui-input-block"  id="mother">
								<input type="checkbox" lay-filter='mu_bing' value="高血压" title="高血压">
								<input type="checkbox" lay-filter='mu_bing' value="糖尿病" title="糖尿病">
								<input type="checkbox" lay-filter='mu_bing' value="冠心病" title="冠心病">
								<input type="checkbox" lay-filter='mu_bing' value="恶性肿瘤" title="恶性肿瘤">
								<input type="checkbox" lay-filter='mu_bing' value="脑卒中（脑出血、脑梗塞）" title="脑卒中（脑出血、脑梗塞）">
								<input type="checkbox" lay-filter='mu_bing' value="先天畸形" title="先天畸形">
								<input type="checkbox" lay-filter='mu_bing' value="结核病" title="结核病">
								<input type="checkbox" lay-filter='mu_bing' value="精神分裂症" title="精神分裂症">
								<input type="checkbox" lay-filter='mu_bing' value="肝炎" title="肝炎">
								<input type="checkbox" lay-filter='mu_bing' value="颈椎病、腰椎病" title="颈椎病、腰椎病">
							</div>
							<label class="layui-form-label" style="width:80px; margin: 10px 0 10px 60px;">其他：</label>
							<div class="layui-input-block" style="margin: 10px 0 10px 20px;">
								<input type="text" id="motherOther" placeholder="请输入疾病" autocomplete="off" class="layui-input" style="width:420px;">
							</div>
							<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>兄弟姐妹：</label>
							<div class="layui-input-block" id="brother">
								<input type="checkbox" lay-filter='xiong_bing' value="高血压" title="高血压">
								<input type="checkbox" lay-filter='xiong_bing' value="糖尿病" title="糖尿病">
								<input type="checkbox" lay-filter='xiong_bing' value="冠心病" title="冠心病">
								<input type="checkbox" lay-filter='xiong_bing' value="恶性肿瘤" title="恶性肿瘤">
								<input type="checkbox" lay-filter='xiong_bing' value="脑卒中（脑出血、脑梗塞）" title="脑卒中（脑出血、脑梗塞）">
								<input type="checkbox" lay-filter='xiong_bing' value="先天畸形" title="先天畸形">
								<input type="checkbox" lay-filter='xiong_bing' value="结核病" title="结核病">
								<input type="checkbox" lay-filter='xiong_bing' value="精神分裂症" title="精神分裂症">
								<input type="checkbox" lay-filter='xiong_bing' value="肝炎" title="肝炎">
								<input type="checkbox" lay-filter='xiong_bing' value="颈椎病、腰椎病" title="颈椎病、腰椎病">
							</div>
							<label class="layui-form-label" style="width:80px; margin: 10px 0 10px 60px;">其他：</label>
							<div class="layui-input-block" style="margin: 10px 0 10px 20px;">
								<input type="text" id="brotherOther" placeholder="请输入疾病" autocomplete="off" class="layui-input" style="width:420px;">
							</div>
							<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>子女：</label>
							<div class="layui-input-block" id="son">
								<input type="checkbox" lay-filter='zinv_bing' value="高血压" title="高血压">
								<input type="checkbox" lay-filter='zinv_bing' value="糖尿病" title="糖尿病">
								<input type="checkbox" lay-filter='zinv_bing' value="冠心病" title="冠心病">
								<input type="checkbox" lay-filter='zinv_bing' value="恶性肿瘤" title="恶性肿瘤">
								<input type="checkbox" lay-filter='zinv_bing' value="脑卒中（脑出血、脑梗塞）" title="脑卒中（脑出血、脑梗塞）">
								<input type="checkbox" lay-filter='zinv_bing' value="先天畸形" title="先天畸形">
								<input type="checkbox" lay-filter='zinv_bing' value="结核病" title="结核病">
								<input type="checkbox" lay-filter='zinv_bing' value="精神分裂症" title="精神分裂症">
								<input type="checkbox" lay-filter='zinv_bing' value="肝炎" title="肝炎">
								<input type="checkbox" lay-filter='zinv_bing' value="颈椎病、腰椎病" title="颈椎病、腰椎病">
							</div>
							<label class="layui-form-label" style="width:80px; margin: 10px 0 10px 60px;">其他：</label>
							<div class="layui-input-block" style="margin: 10px 0 10px 20px;">
								<input type="text" id="sonOther" placeholder="请输入疾病" autocomplete="off" class="layui-input" style="width:420px;">
							</div>
						</div>
					</fieldset>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>遗传病史：</label>
						<div class="layui-input-block"style="width:470px;">
							<input type="radio" value="0" name="yichuan" lay-filter="yichuan" title="无" checked/>
							<input type="radio" value="1" name="yichuan" lay-filter="yichuan" id="yichuan" title="有"/>
						</div>
						<div class="layui-input-block yichuan" style="display: none;">
							<input type="text" id="yichuanbing" placeholder="请输入遗传病名称" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>有无残疾：</label>
						<div class="layui-input-block"style="width:470px;">
							<input type="radio" value="0" name="canji" lay-filter="canji" title="无残疾" checked/>
							<input type="radio" value="1" name="canji" lay-filter="canji" id="canji" title="有"/>
						</div>
						<div class="canji" style="display: none;">
							<div class="layui-input-block">
								<input type="checkbox" lay-filter='canji' value="1" title="听力残">
								<input type="checkbox" lay-filter='canji' value="2" title="言语残">
								<input type="checkbox" lay-filter='canji' value="3" title="肢体残">
								<input type="checkbox" lay-filter='canji' value="4" title="智力残">
								<input type="checkbox" lay-filter='canji' value="5" title="眼">
								<input type="checkbox" lay-filter='canji' value="6" title="精神残"><br />
							</div>
							<div class="layui-input-block" style="margin-top:10px;">
								<input type="text" id="canjihao" placeholder="请输入残疾证号" autocomplete="off" class="layui-input" style="width:470px; margin-right:30px;">
							</div>
						</div>
					</div>
					
					<!-- <div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>上传图片：</label>
						<div class="site-demo-upload" style="width:150px; height:30px; float:left;">
							<div class="site-demo-upbar">
							  <input type="file" name="file" class="layui-upload-file" id="test">
							</div>
						</div>
					</div> -->
					<!-- <div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:80px;"></label>
						<div class="older_img" style="width:150px; height:150px; float:left;">
							<div class="img">
			        			<a href="javascript:;" class="del">删 除</a>
			        			<div class="face"></div>
			                	<img src="../images/login_bg.png" alt="" />
			        		</div>
						</div>
					</div> -->
			</div>
		</div>
		
		<div class="d3" style="display: none;">
			<div class="layui-form">
				<fieldset class="layui-elem-field" style="margin-left: 15px;margin-top: 10px;width: 1270px;">
					<legend>用药情况</legend>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:110px;"><font color="red" style="size:2px" >*</font>服药依从性：</label>
						<div class="layui-input-block">
							<input type="radio" value="0" name="fuyao" title="规律服药" checked/>
							<input type="radio" value="1" name="fuyao" title="间断服药"/>
							<input type="radio" value="2" name="fuyao" title="不服药"/>
							<input type="radio" value="3" name="fuyao" title="无法说清楚目前用药种类"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:110px;"><font color="red" style="size:2px" >*</font>开药周期：</label>
						<div class="layui-input-block">
							<input type="radio" value="0" name="kaiyao" title="每月一次" checked/>
							<input type="text" id="kaiyaoDay" style="margin-top:10px; width:50px; height:20px; border:none; border-bottom: 1px solid #ccc; text-align: center;" /><span style="margin-right: 10px;">号开药</span>
							<input type="radio" value="1" name="kaiyao" title="按天开药"/>
							<input type="text" id="kaiyaoDate" style="margin-top:10px; width:50px; height:20px; border:none; border-bottom: 1px solid #ccc; text-align: center;" /><span style="margin-right: 10px;">天开一次药</span>
							<input type="radio" value="2" name="kaiyao" title="不规律"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:110px;"><font color="red" style="size:2px" >*</font>开药医院名称：</label>
						<div class="layui-input-block" id="hospitals">
							<div>
								<input type="text" id="firstDrug" name="drugStore" style="margin-top:10px; width:400px; height:20px; border:none; border-bottom: 1px solid #ccc;" />
								<a href="javascript:;" class="layui-btn layui-btn-small" onclick="javascript:addHospital();" style="">
									<i class="layui-icon">&#xe608;</i> 添 加
								</a>
							</div>
						</div>
					</div>
					<div class="layui-field-box">
						<table class="site-table table-hover">
							<thead>
								<tr>
									<th rowspan="2">处方用药名称<br>(包含针剂、塞剂,例如:胰岛素、开塞路)</th>
									<th colspan="4">用药时间和剂量</th>
									<th rowspan="2">备注</th>
									<th rowspan="2">操作</th>
								</tr>
								<tr>
									<th>早</th>
									<th>中</th>
									<th>晚</th>
									<th>睡前</th>
								</tr>
							</thead>
							<tbody id="yaoTbody">
								<tr id="firstTr">
									<td>
										<input type="text"/>
									</td>
									<td>
										<input type="text"/>
									</td>
									<td>
										<input type="text"/>
									</td>
									<td>
										<input type="text"/>
									</td>
									<td>
										<input type="text"/>
									</td>
									<td>
										<input type="text"/>
									</td>
									<td>
										<span onclick="javascript:delYongYao(this);" style="cursor: pointer; color:red">
											<i class="layui-icon">&#xe640;</i>
										</span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<a href="javascript:;" class="layui-btn layui-btn-small" onclick="javascript:addYongYao();" style="margin-left: 600px;">
						<i class="layui-icon">&#xe608;</i> 添 加
					</a>
				</fieldset>
				<fieldset class="layui-elem-field" style="margin-left: 15px;margin-top: 10px;width: 1270px;">
					<legend>吸氧</legend>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:110px;"><font color="red" style="size:2px" >*</font>吸氧：</label>
						<div class="layui-input-block">
							<span>平均每日</span>
							<input type="text" id="xiyang" style="margin-top:10px; width:50px; height:20px; border:none; border-bottom: 1px solid #ccc; text-align: center;" />
							<span>小时</span>
						</div>
					</div>
				</fieldset>
				<fieldset class="layui-elem-field" style="margin-left: 15px;margin-top: 10px;width: 1270px;">
					<legend>疫苗预防接种史</legend>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:110px;"><font color="red" style="size:2px" >*</font>流感疫苗：</label>
						<div class="layui-input-block">
							<input type="radio" value="0" name="liugan" title="未接种" checked/>
							<input type="radio" value="1" name="liugan" title="一次"/>
							<input type="radio" value="2" name="liugan" title="二次（含）以上"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:110px;"><font color="red" style="size:2px" >*</font>肺炎球菌疫苗：</label>
						<div class="layui-input-block">
							<input type="radio" value="0" name="feiyan" title="从未接种" checked/>
							<input type="radio" value="1" name="feiyan" title="近五年内接种"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px;">
						<label class="layui-form-label" style="width:110px;"><font color="red" style="size:2px" >*</font>其它疫苗名称：</label>
						<div class="layui-input-block">
							<input type="text" id="otherVaccine" style="margin-top:10px; width:400px; height:20px; border:none; border-bottom: 1px solid #ccc;" />
						</div>
					</div>
				</fieldset>
				<div class="layui-form-item" style="margin-top:20px;">
					<label class="layui-form-label" style="width:80px;"><font color="red" style="size:2px" >*</font>备注：</label>
					<div class="layui-input-block">
						<p>药片(丸)剂量单位请用mg</p>
						<p>药水剂量单位请用cc</p>
						<p>塞剂剂量单位请用#</p>
						<p>胰岛素剂量请用u</p>
						<p>药膏或擦剂请记录次数即可</p>
					</div>
				</div>
			</div>
		</div>
		
		<div class="d2" style="display: none;">
			<div class="layui-form">
				<fieldset class="layui-elem-field" style="margin-left: 15px;margin-top: 10px;width: 1270px;">
					<legend>生活能力评估</legend>
					<div class="layui-form-item" style="margin-top:20px; margin-bottom: 0;">
						<label class="layui-form-label" style="width:120px;">1、家务如何分配</label>
					</div>
					<div class="layui-form-item" >
						<label class="layui-form-label" style="width:30px;"></label>
						<div class="layui-input-inline" style="width: 700px;">
							<input type="checkbox" lay-filter='jiawu' name="jiawu" value="0" title="自己做">
							<input type="checkbox" lay-filter='jiawu' name="jiawu" value="1" title="老伴做">
							<input type="checkbox" lay-filter='jiawu' name="jiawu" value="2" title="与老伴一起做">
							<input type="checkbox" lay-filter='jiawu' name="jiawu" value="3" title="子女做">
							<input type="checkbox" lay-filter='jiawu' name="jiawu" value="4" title="小时工">
							<input type="checkbox" lay-filter='jiawu' name="jiawu" value="5" title="其他">
						</div>
						<div class="layui-input-inline oJiaWu" style="width: 400px; display: none;">
							<input type="text" id='qitaJiawu' placeholder="请输入其他方式" autocomplete="off" class="layui-input" style="">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px; margin-bottom: 0;">
						<label class="layui-form-label" style="width:110px;">2、衣物清洗&nbsp;&nbsp;&nbsp;&nbsp;</label>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label" style="width:30px;"></label>
						<div class="layui-input-inline" style="width: 900px;">
							<input type="checkbox" name="clear" value="0" title="不论大小衣物都是自己洗">
							<input type="checkbox" name="clear" value="1" title="小件自己洗大件靠帮助">
							<input type="checkbox" name="clear" value="2" title="能洗但是自己不洗">
							<input type="checkbox" name="clear" value="3" title="自己洗不了">
							<input type="checkbox" name="clear" value="4" title="洗衣机洗">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px; margin-bottom: 0;">
						<label class="layui-form-label" style="width:110px;">3、饮食习惯&nbsp;&nbsp;&nbsp;&nbsp;</label>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:110px;">主食偏好：</label>
						<div class="layui-input-inline" style="width: 500px;">
							<input type="checkbox" name="zhushi" lay-filter="zhushi" value="0" title="面食">
							<input type="checkbox" name="zhushi" lay-filter="zhushi" value="1" title="米">
							<input type="checkbox" name="zhushi" lay-filter="zhushi" value="2" title="米面均衡">
							<input type="checkbox" name="zhushi" lay-filter="zhushi" value="3" title="其他">
						</div>
						<div class="layui-input-inline oZhuShi" style="width: 400px; display: none;">
							<input type="text" id="qitaZhushi" placeholder="请输入其他主食" autocomplete="off" class="layui-input" style="">
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:110px;">副食偏好：</label>
						<div class="layui-input-inline" style="width: 510px;">
							<input type="checkbox" value="0" name="fushi" lay-filter="fushi" title="荤素均衡"/>
							<input type="checkbox" value="1" name="fushi" lay-filter="fushi" title="荤食为主"/>
							<input type="checkbox" value="2" name="fushi" lay-filter="fushi" title="素食为主"/>
							<input type="checkbox" value="3" name="fushi" lay-filter="fushi" title="其他"/>
						</div>
						<div class="layui-input-inline oFuShi" style="width: 400px; display: none;">
							<input type="text" id="qitaFushi" placeholder="请输入其他副食" autocomplete="off" class="layui-input" style="">
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:110px;">食物过敏：</label>
						<div class="layui-input-inline" style="width: 510px;">
							<input type="checkbox" value="0" name="shiwuguomin" lay-filter="shiwuguomin" title="无"/>
							<input type="checkbox" value="1" name="shiwuguomin" lay-filter="shiwuguomin" title="海鲜"/>
							<input type="checkbox" value="2" name="shiwuguomin" lay-filter="shiwuguomin" title="蘑菇"/>
							<input type="checkbox" value="3" name="shiwuguomin" lay-filter="shiwuguomin" title="豆类"/>
							<input type="checkbox" value="4" name="shiwuguomin" lay-filter="shiwuguomin" title="其他"/>
						</div>
						<div class="layui-input-inline oShiWuGuoMin" style="width: 400px; display: none;">
							<input type="text" id="qitaShiwuguomin" placeholder="请输入其他过敏食物" autocomplete="off" class="layui-input" style="">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px; margin-bottom: 0;">
						<label class="layui-form-label" style="width:110px;">4、体育锻炼&nbsp;&nbsp;&nbsp;&nbsp;</label>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:30px;"></label>
						<label class="layui-form-label" style="width:110px;">是否有锻炼身体的习惯：</label>
						<div class="layui-input-inline">
							<input type="radio" value="0" name="duanlian" lay-filter="duanlian" title="无" checked/>
							<input type="radio" value="1" name="duanlian" lay-filter="duanlian" title="有"/>
						</div>
					</div>
					<div id="duanlian" style="display:none;">
						<div class="layui-form-item" style="margin:0;">
							<label class="layui-form-label" style="width:30px;"></label>
							<label class="layui-form-label" style="width:110px;">锻炼的频次：</label>
							<div class="layui-input-inline" style="width: 250px">
								<input type="radio" value="0" name="duanlianpinci" title="每天" checked/>
								<input type="radio" value="1" name="duanlianpinci" title=" " checked/>
								<span style="margin-left: -20px">每周</span>
								<input id="pinci" type="text" style="margin-top:10px; width:20px; height:20px; border:none; border-bottom: 1px solid #ccc;" />
								<span>次</span>
							</div>
						</div>
						<div class="layui-form-item" style="margin:0;">
							<label class="layui-form-label" style="width:30px;"></label>
							<label class="layui-form-label" style="width:110px;">每次锻炼时间：</label>
							<div class="layui-input-inline" style="width: 500px;">
								<input type="radio" value="0" name="duanlianshijian" title="大于半小时" checked/>
								<input type="radio" value="1" name="duanlianshijian" title="半小时"/>
								<input type="radio" value="2" name="duanlianshijian" title="小于半小时"/>
							</div>
						</div>
						<div class="layui-form-item" style="margin:0;">
							<label class="layui-form-label" style="width:30px;"></label>
							<label class="layui-form-label" style="width:110px;">坚持锻炼时间：</label>
							<div class="layui-input-inline">
								<input type="text" id="jianchi" style="margin-top:10px; width:30px; height:20px; border:none; border-bottom: 1px solid #ccc;" />
								<span>年</span>
							</div>
						</div>
						<div class="layui-form-item" style="margin:0;">
							<label class="layui-form-label" style="width:30px;"></label>
							<label class="layui-form-label" style="width:110px;">锻炼方式：</label>
							<div class="layui-input-inline" style="width: 550px;">
								<input type="checkbox" value="0" name="duanlianfangshi" lay-filter="duanlianfangshi" title="走路"/>
								<input type="checkbox" value="1" name="duanlianfangshi" lay-filter="duanlianfangshi" title="跑步"/>
								<input type="checkbox" value="2" name="duanlianfangshi" lay-filter="duanlianfangshi" title="打太极"/>
								<input type="checkbox" value="3" name="duanlianfangshi" lay-filter="duanlianfangshi" title="广场舞"/>
								<input type="checkbox" value="4" name="duanlianfangshi" lay-filter="duanlianfangshi" title="其他"/>
							</div>
							<div class="layui-input-inline oDuanLianFangShi" style="width: 400px; display: none;">
								<input type="text" id="qitaDuanlianfangshi" placeholder="请输入其他锻炼方式" autocomplete="off" class="layui-input" style="">
							</div>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px; margin-bottom: 0;">
						<label class="layui-form-label" style="width:120px; text-align: right;">5、排便是否正常</label>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:30px;"></label>
						<div class="layui-input-inline" style="width: 132px;">
							<input type="radio" value="0" name="paibian" lay-filter="paibian" title="是" checked/>
							<input type="radio" value="1" name="paibian" lay-filter="paibian" title="否"/>
						</div>
						<div class="layui-input-inline" style="width: 500px;">
							<span>频次如何？</span>
							<input type="text" id="paibianPinci" style="margin-top:10px; width:400px; height:20px; border:none; border-bottom: 1px solid #ccc;" />
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px; margin-bottom: 0;">
						<label class="layui-form-label" style="width:95px; text-align: right;">6、是否起夜</label>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:30px;"></label>
						<div class="layui-input-inline" style="width: 132px;">
							<input type="radio" value="0" name="qiye" lay-filter="qiye" title="是" checked/>
							<input type="radio" value="1" name="qiye" lay-filter="qiye" title="否"/>
						</div>
						<div class="layui-input-inline" style="width: 500px;">
							<span>几次？</span>
							<input type="text" id="qiyePinci" style="margin-top:10px; width:400px; height:20px; border:none; border-bottom: 1px solid #ccc;" />
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px; margin-bottom: 0;">
						<label class="layui-form-label" style="width:120px;">7、睡眠质量如何</label>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:30px;"></label>
						<div class="layui-input-inline" style="width: 300px;">
							<input type="radio" value="0" name="shuimian" lay-filter="shuimian" title="很好" checked/>
							<input type="radio" value="1" name="shuimian" lay-filter="shuimian" title="一般"/>
							<input type="radio" value="1" name="shuimian" lay-filter="shuimian" title="不好失眠"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:20px; margin-bottom: 0;">
						<label class="layui-form-label" style="width:120px;">8、洗浴频次如何</label>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:30px;"></label>
						<div class="layui-input-inline" style="width: 250px;">
							<input type="radio" value="0" name="xiyu" lay-filter="xiyu" title="独立完成" checked/>
							<input type="radio" value="1" name="xiyu" lay-filter="xiyu" title="需要辅助"/>
						</div>
						<div class="layui-input-inline" style="width: 500px;">
							<span>洗浴频次</span>
							<input type="text" id="xiyuPinci" style="margin-top:10px; width:400px; height:20px; border:none; border-bottom: 1px solid #ccc;" />
						</div>
					</div>
					<div class="layui-form-item" style="margin-bottom:20px;">
						<label class="layui-form-label" style="width:30px;"></label>
						<div class="layui-input-inline" style="width: 500px;">
							<span>其他情况</span>
							<input type="text" id="qitaqingkuang" style="margin-top:10px; width:400px; height:20px; border:none; border-bottom: 1px solid #ccc;" />
						</div>
					</div>
				</fieldset>
				<fieldset class="layui-elem-field" style="margin-left: 15px;margin-top: 10px;width: 1270px;">
					<legend>其他评估</legend>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:100px;">是否使用辅具：</label>
						<div class="layui-input-inline" style="width: 300px;">
							<input type="radio" value="0" name="fuju" lay-filter="fuju" title="是" checked/>
							<input type="radio" value="1" name="fuju" lay-filter="fuju" title="否"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:100px;">辅具类型：</label>
						<div id="fujuleixing" class="layui-input-block">
							<input type="checkbox" name="fujuleixing" lay-filter='fujuleixing' value="拐杖" title="拐杖">
							<input type="checkbox" name="fujuleixing" lay-filter='fujuleixing' value="助行器" title="助行器">
							<input type="checkbox" name="fujuleixing" lay-filter='fujuleixing' value="轮椅" title="轮椅">
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:100px;">四肢情况：</label>
						<div class="layui-input-inline" style="width: 300px;">
							<input type="radio" value="0" name="sizhi" lay-filter="sizhi" title="正常" checked/>
							<input type="radio" value="1" name="sizhi" lay-filter="sizhi" title="异常"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:100px;">关节畸形：</label>
						<div class="layui-input-inline" style="width: 300px;">
							<input type="radio" value="0" name="guanjie" lay-filter="guanjie" title="无" checked/>
							<input type="radio" value="1" name="guanjie" lay-filter="guanjie" title="有"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:100px;">下肢静脉曲张：</label>
						<div class="layui-input-inline" style="width: 300px;">
							<input type="radio" value="0" name="jingmaiquzhang" lay-filter="jingmaiquzhang" title="无" checked/>
							<input type="radio" value="1" name="jingmaiquzhang" lay-filter="jingmaiquzhang" title="有"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:100px;">下肢水肿：</label>
						<div class="layui-input-inline" style="width: 300px;">
							<input type="radio" value="0" name="shuizhong" lay-filter="shuizhong" title="无" checked/>
							<input type="radio" value="1" name="shuizhong" lay-filter="shuizhong" title="有"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:100px;">平地行走：</label>
						<div class="layui-input-inline" style="width: 300px;">
							<input type="radio" value="0" name="xingzou" lay-filter="xingzou" title="独立" checked/>
							<input type="radio" value="1" name="xingzou" lay-filter="xingzou" title="部分帮助"/>
							<input type="radio" value="2" name="xingzou" lay-filter="xingzou" title="极大帮助"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:100px;">上下楼梯：</label>
						<div class="layui-input-inline" style="width: 300px;">
							<input type="radio" value="0" name="louti" lay-filter="louti" title="独立" checked/>
							<input type="radio" value="1" name="louti" lay-filter="louti" title="部分帮助"/>
							<input type="radio" value="2" name="louti" lay-filter="louti" title="极大帮助"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:100px;">身体健康状况：</label>
						<div class="layui-input-inline" style="width: 300px;">
							<input type="radio" value="0" name="zhuangkuang" lay-filter="zhuangkuang" title="全自理" checked/>
							<input type="radio" value="1" name="zhuangkuang" lay-filter="zhuangkuang" title="半自理"/>
							<input type="radio" value="2" name="zhuangkuang" lay-filter="zhuangkuang" title="不自理"/>
						</div>
					</div>
					<div class="layui-form-item" style="margin:0;">
						<label class="layui-form-label" style="width:100px;">家庭主要照护者：</label>
						<div id="zhaohu" class="layui-input-block">
							<input type="checkbox" name="zhaohu" lay-filter='zhaohu' value="自己" title="自己">
							<input type="checkbox" name="zhaohu" lay-filter='zhaohu' value="老伴" title="老伴">
							<input type="checkbox" name="zhaohu" lay-filter='zhaohu' value="儿女、孙辈或其他亲属" title="儿女、孙辈或其他亲属">
							<input type="checkbox" name="zhaohu" lay-filter='zhaohu' value="保姆、小时工" title="保姆、小时工">
							<input type="checkbox" name="zhaohu" lay-filter='zhaohu' value="邻里帮助" title="邻里帮助">
							<input type="checkbox" name="zhaohu" lay-filter='zhaohu' value="其他" title="其他">
						</div>
						<div class="layui-input-inline oZhaoHu" style="width: 400px; display: none;">
							<input type="text" id="zhaohuQita" placeholder="请输入其他方式" autocomplete="off" class="layui-input" style="">
						</div>
					</div>
				</fieldset>
				<fieldset class="layui-elem-field" style="margin-left: 15px;margin-top: 10px;width: 1270px;">
					<legend>需求情况</legend>
					<div class="layui-form-item" style="margin-top:20px; margin-bottom: 0;">
						<label class="layui-form-label" style="width:130px;">您有什么服务需求？</label>
					</div>
					<div class="layui-form-item" style="margin-top:0;">
						<label class="layui-form-label" style="width:120px; padding-left: 0px"><font color="red" style="size:2px" >*</font>生活照料类：</label>
						<div id="shenghuo" class="layui-input-block">
							<input type="checkbox" name="shenghuo" lay-filter='shenghuo' value="助餐" title="助餐">
							<input type="checkbox" name="shenghuo" lay-filter='shenghuo' value="助洁" title="助洁">
							<input type="checkbox" name="shenghuo" lay-filter='shenghuo' value="助浴" title="助浴">
							<input type="checkbox" name="shenghuo" lay-filter='shenghuo' value="修脚、理发" title="修脚、理发">
							<input type="checkbox" name="shenghuo" lay-filter='shenghuo' value="专业养老护理员" title="专业养老护理员">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:0;">
						<label class="layui-form-label" style="width:120px; height:100px; padding-left: 0px"><font color="red" style="size:2px" >*</font>医疗服务类：</label>
						<div id="yiliao" class="layui-input-block">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="医生上门" title="医生上门">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="护士上门" title="护士上门">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="康复师上门" title="康复师上门">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="养生保健讲座" title="养生保健讲座">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="心理慰藉" title="心理慰藉">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="健康指标检测" title="健康指标检测">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="陪同就医" title="陪同就医">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="代取送药" title="代取送药">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="放松按摩" title="放松按摩">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="健康管理" title="健康管理">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="居家照护培训" title="居家照护培训">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="辅助器具配备" title="辅助器具配备">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="家属喘息服务（外出接送服务、入户生活照料、短期托管）" title="家属喘息服务（外出接送服务、入户生活照料、短期托管）">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="用药指导" title="用药指导">
							<input type="checkbox" name="yiliao" lay-filter='yiliao' value="其他" title="其他">
						</div>
						<div class="layui-input-block oYiLiao" style="width: 400px; margin-left:134px; display: none;">
							<input type="text" id="yiliaoQita" placeholder="请输入其他医疗服务类" autocomplete="off" class="layui-input" style="">
						</div>
					</div>
					<div class="layui-form-item" style="margin-top:0;">
						<label class="layui-form-label" style="width:120px; padding-left: 0px"><font color="red" style="size:2px" >*</font>安全类：</label>
						<div id="anquan" class="layui-input-block">
							<input type="checkbox" name="anquan" lay-filter='anquan' value="适老化改造" title="适老化改造">
							<input type="checkbox" name="anquan" lay-filter='anquan' value="适老化家具" title="适老化家具">
							<input type="checkbox" name="anquan" lay-filter='anquan' value="其他" title="其他">
						</div>
						<div class="layui-input-block oAnQuan" style="width: 400px; margin-left:134px; display: none;">
							<input type="text" id="anquanQita" placeholder="请输入其他安全类" autocomplete="off" class="layui-input" style="">
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	
	<input type="hidden" id="inp">
	<input type="hidden" value="" id="place">
	<input type="hidden" value="" id="canjiStr">
	<script type="text/javascript">
		var form;
		layui.use(['element', 'layer','form','laydate','upload'], function() {
				form = layui.form();
			var element = layui.element(),
				$ = layui.jquery,
				layer = layui.layer,
				upload = layui.upload,
				laydate = layui.laydate;
			
			
			
			$("#inp").click(function(){
				form.render();
			});
			
			
			
			form.on('checkbox(bing)',function(msg){
				if(msg.elem.checked){
					if (msg.elem.value == "其他") {
						var html = "<div class='layui-input-block jibing' style='margin-top:20px;' flag='val"+msg.elem.value+"'>"
								 + "<label class='layui-form-label' style='width:120px; padding:9px 0; text-align:right;'><font color='red' style='size:2px' >*</font>疾病名称：</label>"
								 + "<div class='layui-input-inline' style='margin-top:0;'>"
								 + "<input type='text' placeholder='请输入疾病名称' autocomplete='off' class='layui-input' style='width:242px; margin-right:30px;'>"
								 + "</div>"
								 + "<label class='layui-form-label' style='width:100px; padding:9px 0; text-align:right;'><font color='red' style='size:2px' >*</font>时间：</label>"
								 + "<div class='layui-input-inline' style='margin-top:0;'>"
								 + "<input type='text' class='layui-input' placeholder='请输入确诊时间' style='width:242px;' />"
							     + "</div>"
								 + "</div>";
								 $("#addJibing").css("display", "block");
								 
					} else {
						var html = "<div class='layui-input-block jibing' style='margin-top:20px;' flag='val"+msg.elem.value+"'>"
								 + "<label class='layui-form-label' style='width:120px; padding:9px 0; text-align:right;'><font color='red' style='size:2px' >*</font>"+msg.elem.title+"：</label>"
								 + "<input type='text' class='layui-input' placeholder='请输入确诊时间' style='width:242px;' />"
							     + "</div>";
					}
					$("#timer").append(html);
				}
				else{
					$("#val"+msg.elem.value+"").remove();
					if (msg.elem.value == "其他") {
						$("#addJibing").css("display", "none");
					}
				}
				form.render();
			})
			
			
			form.on('checkbox(canji)',function(msg){
				
				var canjiStr = $("#canjiStr").val();
				if(msg.elem.checked){
					canjiStr += "," + msg.elem.value;
				} else {
					canjiStr = canjiStr.replace("," + msg.elem.value, "");
				}
				$("#canjiStr").val(canjiStr);
				form.render();
			})
			
			
			layui.upload({
				url: '上传接口url',
				success: function(res){
				  console.log(res);
				  var html = "<div class='img'>"
	            		   + "<a href='javascript:;' class='del'>删 除</a>"
	            		   + "<div class='face'></div>"
	                       + "<img src='"+res.url+"' />"
	            		   + "</div>"
				  $(".older_img").html(html);
				}
			});
			
			//滑过图片
			$(".older_img").delegate('.img','mouseover',function(){
				$(this).find(".face").css("display","block")
				$(this).find(".del").css("display","block")
			});
			$(".older_img").delegate('.img','mouseout',function(){
				$(this).find(".face").css("display","none")
				$(this).find(".del").css("display","none")
			});
			
			//删除图片
			$(".older_img").delegate('.del','click',function(){
				$(this).parent().remove();
			});
			
			
			form.on('select(demo1)',function(data){
				InitCity();
				form.render();
			})
			form.on('select(demo2)',function(data){
				InitArea();
				form.render();
			})
			
			//居家养老情况
			/* form.on('select(yanglao)',function(data){
				if(data.value == 4){
					$(".zhujia").css("display","block");
				}
				else{
					$(".zhujia").css("display","none");
				}
			}) */
			
			//医疗费用支付方式
			form.on('select(zhifu)',function(data){
				if(data.value == 9){
					$(".zhifu").css("display","block");
				}
				else{
					$(".zhifu").css("display","none");
				}
			})
			
			//药物过敏史
			form.on('select(guomin)',function(data){
				if(data.value == 5){
					$(".guomin").css("display","block");
				}
				else{
					$(".guomin").css("display","none");
				}
			})
			
			//暴露史
			form.on('select(baolu)',function(data){
				if(data.value == 5){
					$(".baolu").css("display","block");
				}
				else{
					$(".baolu").css("display","none");
				}
			})
			
			form.on('select(oldType)',function(data){
				if(data.value == '其他'){
					$(".oldTypeOther").css("display","block");
				}
				else{
					$(".oldTypeOther").css("display","none");
				}
			})
			
			//既往史-手术
			form.on('radio(shoushu)',function(data){
				if(data.value == 1){
					$("#shoushu").css("display","block");
					$(".btn_shoushu").css("display","block");
				}
				else{
					$("#shoushu").css("display","none");
					$(".btn_shoushu").css("display","none");
				}
			})
			
			//既往史-重大外伤
			form.on('radio(waishang)',function(data){
				if(data.value == 1){
					$("#waishang").css("display","block");
					$(".btn_waishang").css("display","block");
				}
				else{
					$("#waishang").css("display","none");
					$(".btn_waishang").css("display","none");
				}
			})
			
			//既往史-输血
			form.on('radio(shuxue)',function(data){
				if(data.value == 1){
					$("#shuxue").css("display","block");
					$(".btn_shuxue").css("display","block");
				}
				else{
					$("#shuxue").css("display","none");
					$(".btn_shuxue").css("display","none");
				}
			})
			
			//遗传病史
			form.on('radio(yichuan)',function(data){
				if(data.value == 1){
					$(".yichuan").css("display","block");
				}
				else{
					$(".yichuan").css("display","none");
				}
			})
			
			//残疾
			form.on('radio(canji)',function(data){
				if(data.value == 1){
					$(".canji").css("display","block");
				}
				else{
					$(".canji").css("display","none");
				}
			});
		});
		
		// 添加其他疾病
		function addJibingOther() {
			var html = "<div class='layui-input-block jibing' flag='val其他' style='margin-top:20px;'>"
					 + "<label class='layui-form-label' style='width:120px; padding:9px 0; text-align:right;'><font color='red' style='size:2px' >*</font>疾病名称：</label>"
					 + "<div class='layui-input-inline' style='margin-top:0;'>"
					 + "<input type='text' placeholder='请输入疾病名称' autocomplete='off' class='layui-input' style='width:242px; margin-right:30px;'>"
					 + "</div>"
					 + "<label class='layui-form-label' style='width:100px; padding:9px 0; text-align:right;'><font color='red' style='size:2px' >*</font>时间：</label>"
					 + "<div class='layui-input-inline' style='margin-top:0;'>"
					 + "<input type='text' class='layui-input' placeholder='请输入确诊时间' style='width:242px;' />"
				     + "</div>"
				     + "<div class='layui-input-inline' style='margin-top:3px; width: 80px;'>"
				     + "<a href='javascript:;' class='layui-btn layui-btn-small' onclick='removed(this)' style='margin-left: 60px; background: #3983e1;'>"
					 + "<i class='layui-icon'>&#xe608;</i> 删除"
					 + "</a>"
					 + "</div>"
					 + "</div>";
			$("#timer").append(html);
		}
		
		//添加手术历史
		function addShou(){
			var html = "<div class='layui-form-item shoushu' style='margin-top:20px;'>"
					 + "<label class='layui-form-label' style='width:80px; padding-left: 0px'><font color='red' style='size:2px'>*</font>手术名称：</label>"
					 + "<div class='layui-input-inline' style='width:242px;'>"
					 + "<input type='text' placeholder='请输入手术名称' autocomplete='off' class='layui-input' style='width:242px; margin-right:30px;'>"
					 + "</div>"
					 + "<label class='layui-form-label' style='width:50px;'><font color='red' style='size:2px' >*</font>时间：</label>"
					 + "<div class='layui-input-inline' style='width:242px;'>"
					 + "<input type='text' class='layui-input' placeholder='请输入手术时间' style='width:242px;' />"
					 + "</div>"
					 + "<div class='layui-input-inline' style='margin-top:3px; width: 80px; margin-right: 0px;'>"
					 + "<a href='javascript:;' class='layui-btn layui-btn-small' onclick='removed(this)' style='background: #3983e1;'>"
					 + "<i class='layui-icon'>&#xe608;</i> 删除"
					 + "</a>"
					 + "</div>"
					 + "</div>";
			$("#shoushu").append(html);
		}
		//添加输血历史
		function addShuXue(){
			var html = "<div class='layui-form-item shuxue' style='margin-top:20px;'>"
					 + "<label class='layui-form-label' style='width:80px; padding-left: 0px'><font color='red' style='size:2px' >*</font>输血原因：</label>"
					 + "<div class='layui-input-inline' style='width:242px;'>"
					 + "<input type='text' placeholder='请输入输血原因' autocomplete='off' class='layui-input' style='width:242px; margin-right:30px;'>"
					 + "</div>"
					 + "<label class='layui-form-label' style='width:50px;'><font color='red' style='size:2px' >*</font>时间：</label>"
					 + "<div class='layui-input-inline' style='width:242px;'>"
					 + "<input type='text' class='layui-input' placeholder='请输入输血时间' style='width:242px;' />"
					 + "</div>"
					 + "<div class='layui-input-inline' style='margin-top:3px; width: 80px;'>"
					 + "<a href='javascript:;' class='layui-btn layui-btn-small' onclick='removed(this)' style='background: #3983e1;'>"
					 + "<i class='layui-icon'>&#xe608;</i> 删除"
					 + "</a>"
					 + "</div>"
					 + "</div>";
			$("#shuxue").append(html);
		}
		//添加外伤历史
		function addWaiShang(){
			var html = "<div class='layui-form-item waishang' style='margin-top:20px;'>"
					 + "<label class='layui-form-label' style='width:80px; padding-left: 0px'><font color='red' style='size:2px' >*</font>外伤名称：</label>"
					 + "<div class='layui-input-inline'  style='width: 242px'>"
					 + "<input type='text' placeholder='请输入外伤名称' autocomplete='off' class='layui-input' style='width:242px; margin-right:30px;'>"
					 + "</div>"
					 + "<label class='layui-form-label' style='width:50px;'><font color='red' style='size:2px' >*</font>时间：</label>"
					 + "<div class='layui-input-inline' style='width: 242px'>"
					 + "<input type='text' class='layui-input' placeholder='请输入患伤时间' style='width:242px;' />"
					 + "</div>"
					 + "<div class='layui-input-inline' style='margin-top:3px; width: 80px; margin-right: 0px;'>"
					 + "<a href='javascript:;' class='layui-btn layui-btn-small' onclick='removed(this)' style='background: #3983e1;'>"
					 + "<i class='layui-icon'>&#xe608;</i> 删除"
					 + "</a>"
					 + "</div>"
					 + "</div>";
			$("#waishang").append(html);
		}
		
		function removed(dt){
			$(dt).parent().parent().remove();
		}
		
		function addHospital() {
			var str = '<div><input type="text" name="drugStore" style="margin-top:10px; width:400px; height:20px; border:none; border-bottom: 1px solid #ccc;" /><span onclick="javascript:delHospital(this);" style="cursor: pointer; color:red"><i class="layui-icon">&#xe640;</i></span></div>';
			$("#hospitals").append(str); 
		}
		
		function delHospital(event) {
			$(event).parent().remove();
			
		}
		
		$(function() {
			getAllCommunity();
		})
		
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
						form.render();
					} else {
						layer.alert("暂无社区信息，请在‘社区管理’模块中添加！");
					}
				}
			});
		}
		
	</script>
</body>
</html>
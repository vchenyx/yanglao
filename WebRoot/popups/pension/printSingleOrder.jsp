<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.container {
	width: 100%;
	position: absolute;
	top: 0;
	bottom: 0;
	overflow: hidden;
}

table {
	width: 100%;
	border-collapse: collapse; /*border:none*/
}

table tr td {
	border: 1px solid #000;
	height: 30px;
	width: 20%; /*color:transparent; border:none;*/
}
</style>

<script type="text/javascript"
	src="<%=basePath%>back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	basePath = "<%=basePath%>";
</script>
</head>
<body>

	<table border="" cellspacing="" cellpadding="">
		<tr>
			<td colspan="5" style="text-align: center; height:50px;">
				东城区小棉袄爱老居家养老服务记录</td>
		</tr>
		<tr>
			<td>老人姓名</td>
			<td class="olderName" style="color:#000;">李翠萍</td>
			<td>联系电话</td>
			<td colspan="2" class="olderTel" style="color:#000;">13652486548</td>
		</tr>
		<tr>
			<td class="noprint">服务项目</td>
			<td class="shenghuo"><span style="color:#000; display: block;">√</span>
			</td>
			<td class="yiliao"><span style="color:#000; display: none;">√</span>
			</td>
			<td class="jingshen"><span style="color:#000; display: none;">√</span>
			</td>
			<td class="anquan"><span style="color:#000; display: none;">√</span>
			</td>
		</tr>
		<tr>
			<td>服务内容</td>
			<td colspan="4" style="color:#000;">斯蒂芬如果男人女人套近乎</td>
		</tr>
		<tr>
			<td>服务地址</td>
			<td colspan="4" style="color:#000;">海淀区中关村天创科技大厦</td>
		</tr>
		<tr>
			<td>服务日期</td>
			<td><input type="text" style="width:30px; border:none;"
				value="2017" readonly="readonly" />年 <input type="text"
				style="width:30px; border:none;" value="07" readonly="readonly" />月
				<input type="text" style="width:30px; border:none;" value="19"
				readonly="readonly" />日</td>
			<td>服务起止时间</td>
			<td colspan="2"><input type="text"
				style="width:60px; border:none;" value="08" readonly="readonly" />时
				<input type="text" style="width:60px; border:none;" value="45"
				readonly="readonly" />分 <span style="margin:0 6px">——</span> <input
				type="text" style="width:60px; border:none;" value="10"
				readonly="readonly" />时 <input type="text"
				style="width:60px; border:none;" value="23" readonly="readonly" />分
			</td>
		</tr>
		<tr style="height:250px;">
			<td colspan="5">
				<p>以下内容由服务对象填写（√）:</p> <input type="text"
				style="width:100%; height:100px; outline: none; border:none"
				readonly="readonly" />
				<div>
					<span> 服务人员提供服务是否规范： <span style="margin-left: 10px;"
						class="guifan">√</span> <span
						style="margin-left: 20px; color:#000;" class="buguifan">√</span>
					</span>
				</div>
				<div style="margin-top:10px;">
					<span> 您对服务是否满意： <span style="margin-left: 10px;"
						class="henmanyi">√</span> <span
						style="margin-left: 30px; color:#000;" class="jibenmanyi">√</span>
						<span style="margin-left: 30px;" class="bumanyi">√</span>
					</span>
				</div>
				<div style="margin-top:10px;">
					<span> 请留下您的宝贵意见： <input type="text" class="yijian"
						readonly="readonly" value="得分王清华阳光不同把你的同时消费"
						style="width:80%; border:none;" />
					</span>
				</div>
			</td>
		</tr>
	</table>


	<script type="text/javascript">
		$(function() {
			window.print();
			//window.close();
		})
	</script>


</body>
</html>
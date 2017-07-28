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
<title>机构--老人详情展示</title>
<link rel="stylesheet" href="<%=basePath %>back/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=basePath %>back/css/global.css" media="all">
<link rel="stylesheet" href="<%=basePath %>back/css/table.css" />
<script type="text/javascript">
	var basePath = "${basePath}";
	var pensionId = "${user.id }";
</script>
</head>
<body>
<div class="admin-main">
	<fieldset class="layui-elem-field">
		<div class="layui-field-box">
			<table class="site-table table-hover">
				<thead>
					<tr style="height:40px;" class="firstRow">
						<th width="70px">
							老人姓名：
						</th>
						<td align="left">
							<div id="name"></div>
						</td>
						<th width="70px">
							性别：
						</th>
						<td align="left">
							<div id="sex"></div>
						</td>
						<th width="70px">
							身份证号码：
						</th>
						<td align="left">
							<div id="idCard"></div>
						</td>
						<th align="center" rowspan="4">
							照片：
						</th>
						<td align="center" rowspan="4">
							<div id="headImg">
								 <input type="hidden" id="headImg" name="" value=""><div id="headImg"></div><div class="" filetype="*.jpg;*.png;*.gif" id="headImg" filewidth="130" fileheight="160" showbutton="0" name="_text" style="display: none;"></div><div id="headImg"><img src="/content/images/mansphoto.png" width="130" height="160"></div>
							</div>
						</td>
					</tr>
					<tr style="height:40px;" class="firstRow">
						<th width="70px">
							民族：
						</th>
						<td align="left">
							<div id="">汉族</div>
						</td>
						<th width="70px">
							婚姻状况：
						</th>
						<td align="left">
							<div id="">已婚</div>
						</td>
						<th width="70px">
							联系手机号：
						</th>
						<td align="left">
							<div id="phone"></div>
						</td>
					</tr>
					<tr style="height:40px;" class="firstRow">
						<th width="70px">
							常驻类型：
						</th>
						<td align="left">
							<div id="">户籍</div>
						</td>
						<th width="70px">
							出生日期：
						</th>
						<td align="left">
							<div id="">1941-09-05</div>
						</td>
						<th width="70px">
							年龄：
						</th>
						<td align="left">
							<div id="age"></div>
						</td>
					</tr>
					<tr style="height:40px;">
						<th width="100px">
							居住情况：
						</th>
						<td align="left" colspan="3">
							<div id="">与子女合住</div>
						</td>
						<th width="170px">
							职业：
						</th>
						<td align="left">
							<div id="">退休</div>
						</td>
					</tr>
					<tr style="height:40px;">
						<th width="70px">
							家庭住址：
						</th>
						<td align="left" colspan="7">
							<div id="">北京市海淀区彩和坊路8号</div>
						</td>
					</tr>
					<tr style="height:35px">
                        <td colspan="8" align="left" style="border-left:1px solid #EOECFF;">
                            <font face="黑体" color="#228B22" size="3px">老人联系方式</font> 
                        </td>
                    </tr>
                    <tr style="height:40px;">
                        <th width="80px">
                            	家庭电话：
                        </th>
                        <td align="left">
                            <div id="h_Phone"></div>
                        </td>
                        <th width="140px">
                            	紧急联系人：
                        </th>
                        <td align="left" width="140px">
                            <div id="h_Emergency">儿子</div>
                        </td>
                        <th width="80px">
                            	联系电话：
                        </th>
                        <td align="left" colspan="4">
                            <div id="h_Phone2">18878551915</div>
                        </td>
                    </tr>
                    <tr style="height:35px">
                        <td colspan=8" align="left" style="border-left:1px solid #EOECFF;">
                            <font face="黑体" color="#228B22" size="3px">老人自身信息</font>
                        </td>
                    </tr>
                    <tr style="height:40px;">
                        <th width="70px">
                            	暴露史：
                        </th>
                        <td align="left">
                            <div id="h_ServiceType">无</div>
                        </td>
                        <th width="70px">
                            	血型：
                        </th>
                        <td align="left">
                            <div id="h_ServiceState">A型</div>
                        </td>
                        <th width="70px">
                            	药物过敏史：
                        </th>
                        <td align="left" colspan="4">
                            <div id="h_ServiceCombo">无</div>
                        </td>
                    </tr>
                    <tr style="height:35px">
                        <td colspan="8" align="left" style="border-left:1px solid #EOECFF;border-right:1px solid #EOECFF;">
                            <font face="黑体" color="#228B22" size="3px">备注信息</font>
                        </td>
                    </tr>
                    <tr style="height:40px;">
                        <th width="70px">
                            	文化程度：
                        </th>
                        <td align="left">
                            <div id="h_Education">初中</div>
                        </td>
                        <th width="70px">
                           	 遗传病史：
                        </th>
                        <td align="left">
                            <div id="h_Major">无</div>
                        </td>
                        <th width="70px">
                           	 有无残疾：
                        </th>
                        <td align="left" colspan="4">
                            <div id="h_Profession">无</div>
                        </td>
                    </tr>
                    <tr style="height:35px">
                        <td colspan="8" align="left" style="border-left:1px solid #EOECFF;border-right:1px solid #EOECFF;">
                            <font face="黑体" color="#228B22" size="3px">既往史</font>
                        </td>
                    </tr>
                    <tr style="height:40px;">
                        <th width="70px">
                                                                      疾病：
                        </th>
                        <td align="left">
                            <div id="h_Education"></div>
                        </td>
                        <th width="70px">
                           	 手术：
                        </th>
                        <td align="left">
                            <div id="h_Major"></div>
                        </td>
                        <th width="70px">
                           	 重大外伤：
                        </th>
                        <td align="left">
                            <div id="h_Profession"></div>
                        </td>
                        <th width="70px">
                           	 输血：
                        </th>
                        <td align="left">
                            <div id="h_Profession"></div>
                        </td>
                    </tr>
                    <tr style="height:35px">
                        <td colspan="8" align="left" style="border-left:1px solid #EOECFF;border-right:1px solid #EOECFF;">
                            <font face="黑体" color="#228B22" size="3px">家族史</font>
                        </td>
                    </tr>
                    <tr style="height:40px;">
                        <th width="70px">
                                                                      父亲：
                        </th>
                        <td align="left">
                            <div id="h_Education"></div>
                        </td>
                        <th width="70px">
                           	 母亲：
                        </th>
                        <td align="left">
                            <div id="h_Major"></div>
                        </td>
                        <th width="70px">
                           	 兄弟姐妹：
                        </th>
                        <td align="left">
                            <div id="h_Profession"></div>
                        </td>
                        <th width="70px">
                           	 子女：
                        </th>
                        <td align="left">
                            <div id="h_Profession"></div>
                        </td>
                    </tr>
				</thead>
				<tbody id="show_tbd">
				</tbody>
			</table>

		</div>
	</fieldset>
</div>
<script type="text/javascript" src="<%=basePath %>back/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>back/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath %>back/js/pension/ckMangager.js"></script>
</body>
</html>
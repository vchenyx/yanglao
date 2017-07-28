<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>关于我们</title>
    <link rel="stylesheet" href="${basePath }css/reset.css" />
    <link rel="stylesheet" href="${basePath }css/public.css" />
    <link rel="stylesheet" href="${basePath }css/about.css" />
</head>
<body>
	<div class="container" id="about">
		<div class="header">
			<jsp:include page="../web/top.jsp" />
		</div>
		
		
		<div class="content">
	        <div class="about_info">
	            <div class="left">
	            
	            	<p>联系我们</p>
					<p>服务热线：010-62698752-888 &nbsp;&nbsp;&nbsp;&nbsp;移动电话：13811398136&nbsp;&nbsp;&nbsp;传真：010-62698752-800</p>
					<p>Email：mafujun@jinkun-innovation.com</p>
					<p>北京金坤科创技术有限公司  版权所有  Copyright2013 京ICP备14033636号</p>
	                <p>地址：北京市海淀区中关村彩和坊路8号天创科技大厦1207A-E</p>
	            </div>
	        </div>
	    </div>
		
		
	</div>

	<script type="text/javascript" src="${basePath }js/jquery-1.9.1.min.js"></script>
	
	<script type="text/javascript">
		$(".header .top .nav ul li").eq(4).addClass("first").siblings().removeClass("first");
	</script>

</body>
</html>




















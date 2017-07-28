var navs = "";
layui.use('element',function() {
	var $ = layui.jquery,
		element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
	var url = "";
	if (userFlag == 5) {//机构   查询下属所有站点信息， 人员信息， 老人信息， 添加站点， 添加部门， 添加人员， 添加供应商, 所有工单、投诉单增删改查
		url = basePath + "back/datas/navPension2.json";
	}
	if (userFlag == 51) {//站点   本站点所有人员增删改查， 老人档案增删改查， 工单增删改查， 投诉单增删改查, 托底扶助增删改查， 服务记录生成查询
		url = basePath + "back/datas/stationPoint.json";
	}
	if (userFlag == 52) {//客服部 呼叫中心
		url = basePath + "back/datas/callCenter0.json";
	}
	if (userFlag == 510) {//站点人员 同站点
		url = basePath + "back/datas/stationPoint.json";
	}
	if (userFlag == 520) {//客服人员 呼叫中心
		url = basePath + "back/datas/callCenter.json";
	}
	if (userFlag == 9) {//admin
		url = basePath + "back/datas/navAdmin.json";
	}
	if (userFlag == 1) {//监护人
		url = basePath + "back/datas/guardian.json";
	}
	if (userFlag == 3) {//医生
		url = basePath + "back/datas/doctor.json";
	}
	if (userFlag == 6) {//物业
		url = basePath + "back/datas/property.json";
	}
	if (userFlag == 7) {//监医疗
		url = basePath + "back/datas/medical.json";
	}
	if (userFlag == 8) {//政府
		url = basePath + "back/datas/government.json";
	}
	if (userFlag == 10) {//服务商
		url = basePath + "back/datas/serviceProvider.json";
	}
	$.ajax({
		type:"GET",
		url: url,
		async:false,
		error:function(){
			alert("请求失败")
		},
		success:function(data){
			navs = eval(data);
		}
	})
	
})



//<img src='../back/images/laoren.png' style='margin-top:-2px;' />


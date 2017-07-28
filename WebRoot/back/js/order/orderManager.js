layui.config({
	base : basePath+ 'back/plugins/layui/modules/'
});
var isOpen = true;
var open_close = false;
var pagesize=20;//每页显示条数
var currentpage=1;//当前页
var search_count_url = basePath+"order/getAllOrderCount.do ";//查询数量
var search_list_url = basePath+"order/getAllList.do";//查询数据集合
var find_by_id   = basePath+"complain/findById.do";
var findOrderBy_id   = basePath+"order/findById.do";
var deal_ids = "";
var dept_option="";
init();


//searchDepartment();
searchCount();


function searchInfo() {
	searchCount();
}

//条件搜索查询框触发
$('#search').on('click', function() {
	if(isOpen){
		isOpen = false;
		if(open_close){
			$(".layui-form").css("overflow","hidden");
			$(".layui-form").animate({"height":"0"},400);
			open_close = false;
		}else{
			$(".layui-form").css("overflow","");
			$(".layui-form").animate({"height":"150px"},400);
			open_close = true;
		}
		setTimeout(function(){
			isOpen = true;},400);
	}
});

var layer;
//页面初始化
function init(){
	layui.use(['laypage','layer','icheck','form', 'laydate'],function() {
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		var $ = layui.jquery, 
		laypage = layui.laypage,
		form = layui.form(),
		laydate = layui.laydate;
		$('input').iCheck({
			checkboxClass : 'icheckbox_flat-green'
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
		$('#selected-all2').on('ifChanged', function(event) {
			var $input = $('.site-table tbody tr td ').find('input[name=ch1] ');
			$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
		});
	});
}
//分页查询总页数
function searchCount(){
	layui.use(['laypage','layer'],function() {
		var $ = layui.jquery, 
		laypage = layui.laypage,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		//查询总数
		var startDate = $("#startDate").val();
		var gggg;
		if (startDate != null && startDate != "") {
			gggg = myDate.getYMDLong(startDate);
		} else {
			gggg = null;
		}
		var endDate = $("#endDate").val();
		var ffff;
		if (endDate != null && endDate != "") {
			ffff = myDate.getYMDLong(endDate);
		} else {
			ffff = null;
		}
		$.ajax({
			url:search_count_url,
			data:{
				"name":$.trim($("#name").val()),
				"phone":$.trim($("#phone").val()),
				"startDate": gggg,
				"endDate": ffff,
				"pagesize":pagesize
			},
			type:"post",
			async:false,
			success:function(json){
				var pageCount = json.pageCount;//总页数
				var totalCount = json.totalCount;//总页数
				$(".pageCount").html(pageCount);
				$(".totalCount").html(totalCount);
				// page
				laypage({
					cont : 'page',
					pages : pageCount,
					groups : 5,
					jump : function(obj, first) {
						currentpage = obj.curr;
						if (!first) {
							searchList();
						}
					}
				});
				searchList();
			},
			error:function(){
			}
			
		});
	});
}
//查询数据集合
function searchList(){
	$("#selected-all").removeAttr("checked");
	//查询总数
	var startDate = $("#startDate").val();
	var gggg;
	if (startDate != null && startDate != "") {
		gggg = myDate.getYMDLong(startDate);
	} else {
		gggg = null;
	}
	var endDate = $("#endDate").val();
	var ffff;
	if (endDate != null && endDate != "") {
		ffff = myDate.getYMDLong(endDate);
	} else {
		ffff = null;
	}
	$.ajax({
		url:search_list_url,
		data:{
			"name":$.trim($("#name").val()),
			"phone":$.trim($("#phone").val()),
			"startDate":gggg,
			"endDate":ffff,
			"pagesize":pagesize,
			"currentpage":currentpage,
		},
		type:"post",
		async:false,
		dataType:"json",
		success:function(json){
			console.log(json);
			var html = "";
			var flag = 1;
			for(var i in json){
				var obj = json[i];//获取信息对象
				html += "<tr>";
				html += "<td>" + (flag++) + "</td>";
				html += "<td>" + obj.stationId + "</td>";
				html += "<td>" + obj.name + "</td>";
				html += "<td>" + obj.phone + "</td>";
				html += "<td onclick='showItems(" + obj.id + ")'>" + obj.content + "</td>";
				html += "<td>" + obj.countPrice + "</td>";
				html += "<td>" + (obj.deliveryCost == null ? "--" : obj.deliveryCost) + "</td>";
				html += "<td>" + (obj.orderState == 0 ? "未服务" : obj.orderState == 1 ? "待回访" : "") + "</td>";
				html += "<td>" + obj.address + "</td>";
				html += "<td>" + (obj.dates == null ? "--" : myDate.longToDateYMD(obj.dates)) + "</td>";
				html += "<td>" + (obj.providerName == null ? "--" : obj.providerName) + "</td>";
				html += "<td>" + (obj.startEndTime == null ? "--" : obj.startEndTime) + "</td>";
				html += "<td>" + (obj.staffName == null ? "--" : obj.staffName) + "</td>";
				html += "<td>" + (obj.standard == 0 ? "是" : obj.standard == 1 ? "否" : "--") + "</td>";
				if(obj.state == 0 ){
					html += "<td>很满意</td>";
				}
				if(obj.state == 1 ){
					html += "<td>基本满意</td>";
				}
				if(obj.state == 2 ){
					html += "<td>不满意</td>";
				}else{
					html += "<td>---</td>";
					
				}
				html += "<td>" + (obj.opinion == null ? "--" : obj.opinion) + "</td>";
				if(obj.orderState == 0){
					html += "<td><a href='javascript:;' data='" + JSON.stringify(obj) + "' onclick='printOrder(this)' data-id='1'  class='layui-btn layui-btn-danger layui-btn-mini'>打印</a>&nbsp;&nbsp;";
					html += "<a href='javascript:;' onclick='serviceForward("+obj.id+")' data-id='1'  class='layui-btn layui-btn-danger layui-btn-mini'>处理</a>&nbsp;&nbsp;";
					html += "<a href='javascript:;' onclick='deleteOrder("+obj.id+")' data-id='1'  class='layui-btn layui-btn-danger layui-btn-mini'>删除</a></td>";
				}else if(obj.orderState == 1){
					html += "<td><a href='javascript:;' onclick='huifangForward("+obj.id+")' data-id='1' class='layui-btn layui-btn-danger layui-btn-mini'>回访</a></td>";

				}else{
					html += "<td>---</td>";
				}
				html += "</tr>";
			}
//			html.replace(/null/g, "--");
			$("#content_list").html(html);
			init();
		}
	});
}



function getCount() {
	var startDate = $("#startDate").val();
	var ssss;
	if (startDate != null && startDate != "") {
		ssss = myDate.getYMDLong(startDate);
	} else {
		ssss = null;
	}
	var endDate = $("#endDate").val();
	var mmmm;
	if (endDate != null && endDate != "") {
		mmmm = myDate.getYMDLong(endDate);
	} else {
		mmmm = null;
	}
	$.ajax({
		url:basePath + "order/getCountDetail.do",
		data:{
			"name":$.trim($("#name").val()),
			"phone":$.trim($("#phone").val()),
			"startDate":ssss,
			"endDate":mmmm,
		},
		type:"post",
		dataType:"json",
		success:function(json){
			if (json.length > 0) {
				layui.use(['layer'], function() {
					var $ = layui.jquery,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
					layer.open({
						type: 2,
						title: '服务商结算信息',
						content: basePath + 'popups/pension/showCountItem.jsp',
						btn: ['确定'],
						area: ['600px', '80%'],
						maxmin: true,
						full: function(elem) {
							var win = window.top === window.self ? window : parent.window;
							$(win).on('resize', function() {
								var $this = $(this);
								elem.width($this.width()).height($this.height()).css({
									top: 0,
									left: 0
								});
								elem.children('div.layui-layer-content').height($this.height() - 95);
							});
						},
						success: function(layero, index) {
							//成功加载iframe
							var body = layer.getChildFrame('body', index);
							var str = "";
							for (var i = 0; i < json.length; i++) {
								var obj = json[i];
								var itemList = obj.itemList;
								var listCount = obj.listCount;
								// 服务内容明细
								var itemStr = "<table><tr><th>名称</th><th>数量</th><th>单价</th><th>总价</th></tr>"
								// 配送费明细
								var deliveryCostStr = "<table><tr><th>名称</th><th>数量</th><th>单价</th><th>总价</th></tr>"
								for (var j = 0; j < itemList.length; j++) {
									var item = itemList[j];
									itemStr += "<tr>";
									itemStr += "<td>" + item.name + "</td>";
									itemStr += "<td>" + item.countNum + "</td>";
									itemStr += "<td>" + item.singlePrice + "</td>";
									itemStr += "<td>" + item.countPrice + "</td>";
									itemStr += "</tr>";
								}
								
								for ( var key in listCount) {
									var nameAndPrice = key.split("&");
									var singlePrice = parseFloat(nameAndPrice[1]);
									var count = parseInt(listCount[key]);
									deliveryCostStr += "<tr>";
									deliveryCostStr += "<td>" + nameAndPrice[0] + "</td>";
									deliveryCostStr += "<td>" + count + "</td>";
									deliveryCostStr += "<td>" + singlePrice + "</td>";
									deliveryCostStr += "<td>" + (count * singlePrice) + "</td>";
									deliveryCostStr += "</tr>";
								}
								
								itemStr += "</table>";
								deliveryCostStr += "</table>";
								str += "<tr>";
								str += "<td>" + obj.providerName + "</td>";
								str += "<td>" + itemStr + "</td>";
								str += "<td>" + obj.countPrice + "</td>";
								str += "<td>" + deliveryCostStr + "</td>";
								str += "<td>" + obj.deliveryCost + "</td>";
								str += "<td>" + obj.dateStr + "</td>";
								str += "</tr>";
							}
							body.find("#prov_tbd").html(str);
						},
						yes:function(index,layero){
							var body = layer.getChildFrame('body', index);
							layer.closeAll();
						},
						end:function(){
							layer.closeAll();
						}
					});
				});
			} else {
				layer.alert("暂无信息！");
			}
		}
	});
}
//工单录入-回访
function huifangForward(id){
	$.ajax({
		url:findOrderBy_id,
		data: {
			"id": id,
		},
		type: "post",
		dataType: "json",
		success: function(json) {
		var  list = json.list;
		var  list2 = json.list2;
		console.log(list)
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '工单回访',
			content: basePath + 'popups/pension/addOrUpdateOrder.jsp',
			btn: ['保存', '取消'],
			area: ['900px', '90%'],
			maxmin: true,
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			},
			success: function(layero, index) {
				//成功加载iframe
				var body = layer.getChildFrame('body', index);
				
				body.find("#aaa").css("display","block");
				
				body.find("#orderId").val(list.orderId);
				var project = "";
				if(list.project == 0){
					project = "助餐服务";
				} else if (list.project == 1){
					project = "助医服务";
				} else if (list.project == 2){
					project = "助洁服务";
				} else {
					project = "助浴服务";
				}
				body.find("#project").val(project);
				body.find("#serviceType").val(list.serviceType);
				body.find("#serviceType").attr("disabled", true);
				body.find("#stationId").val(list2.name);
				body.find("#name").val(list.name);
				body.find("#phone").val(list.phone);
				body.find("#address").val(list.address);
				body.find("#content").val(list.content);
				body.find("#dates").val(new Date(list.dates).toLocaleDateString().split("/").join("-"));
				body.find("#startEndTime").val(list.startEndTime);
				body.find("#startEndTime").attr("disabled", true);
				body.find("#providerName").val(list.providerName);
				body.find("#unit").attr("disabled", true);
				body.find("#singlePrice").val(list.singlePrice);
				body.find("#singlePrice").attr("disabled", true);
				body.find("#serviceUnit").val(list.serviceUnit);
				body.find("#serviceUnit").attr("disabled", true);
				body.find("#staffName").val(list.staffName);
				body.find("#staffName").attr("disabled", true);
				body.find("#inp").click();
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var standard = body.find("#standard").val();
				var  state =body.find("#state").val();
				var opinion =body.find("#opinion").val();	
				$.ajax({
					url: basePath + "order/huifangOrder.do",
					type: 'post',
					data: {
						"id":id,
						"standard":standard,
						"state":state,
						"opinion":opinion,
					},
					dataType: 'json',
					error:function(){
						layer.alert("添加失败");
					},
					success: function(data) {
						if (data.flag == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
								searchInfo();
							});
						} else {
							layer.alert("添加失败");
						}
					}
				});
			}
		});
	});
		}
	});
}

//工单录入
function serviceForward(id){
	$.ajax({
		url:findOrderBy_id,
		data: {
			"id": id,
		},
		type: "post",
		dataType: "json",
		success: function(json) {
		var  list = json.list;
		var  list2 = json.list2;
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '工单处理',
			content: basePath + 'popups/pension/addOrUpdateOrder.jsp',
			btn: ['保存', '取消'],
			area: ['900px', '90%'],
			maxmin: true,
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			},
			success: function(layero, index) {
				//成功加载iframe
				var body = layer.getChildFrame('body', index);
				body.find("#orderId").val(list.orderId);
				var project = "";
				if(list.project == 0){
					project = "助餐服务";
				} else if (list.project == 1){
					project = "助医服务";
				} else if (list.project == 2){
					project = "助洁服务";
				} else {
					project = "助浴服务";
				}
				body.find("#project").val(project);
				body.find("#providerName").val(list.providerName);
				body.find("#stationId").val(list2.name);
				body.find("#name").val(list.name);
				body.find("#phone").val(list.phone);
				body.find("#address").val(list.address);
				body.find("#content").val(list.content);
				body.find("#dates").val(new Date(list.dates).toLocaleDateString().split("/").join("-"));
				body.find("#inp").click();
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var startEndTime = body.find("#startEndTime").val();
				var  unit =	body.find("#unit").val();
				var staffName =body.find("#staffName").val();	
				var singlePrice =body.find("#singlePrice").val();	
				var serviceUnit =body.find("#serviceUnit").val();	
				var serviceType =body.find("#serviceType").val();	
				$.ajax({
					url: basePath + "order/chuliOrder.do",
					type: 'post',
					data: {
						"id":id,
						"startEndTime": startEndTime,
						"staffName":staffName,
						"serviceType":serviceType,
					},
					dataType: 'json',
					error:function(){
						layer.alert("添加失败");
					},
					success: function(data) {
						if (data.flag == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
								searchInfo();
							});
						} else {
							layer.alert("添加失败");
						}
					}
				});
			}
		});
	});
		}
	});
}


//投诉处理
function callComplaint1(id){
	$.ajax({
		url:find_by_id,
		data: {
			"id": id,
		},
		type: "post",
		dataType: "json",
		success: function(json) {
		var  list = json.list;
		console.log(list)
			
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '投诉处理',
			content: basePath + 'popups/pension/callComplaint.jsp',
			btn: ['保存', '取消'],
			area: ['1000px', '80%'],
			maxmin: true,
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			},
			success: function(layero, index) {
				//成功加载iframe
				var body = layer.getChildFrame('body', index);
				body.find("#acceptPerson").val(list.acceptPerson);
				body.find("#handleDept").val(list.handleDept);
				body.find("#acceptTime").val(list.acceptTime);
				body.find("#complaintNumbers").val(list.complaintNumbers);
				body.find("#cellphone").val(list.cellphone);
				body.find("#complaintPeople").val(list.complaintPeople);
				body.find("#fuWuS").val(list.fuWuS);
				body.find("#fuWuSCellphone").val(list.fuWuSCellphone);
				body.find("#complaintContent").html(list.complaintContent);
				body.find("#manageContent").html(list.manageContent);
				body.find("#singlePrice").html(list.singlePrice);
				body.find("#serviceUnit").html(list.serviceUnit);
				var option1 = body.find('#selectId').find("option");
				for(var i = 0;i<option1.length;i++){
					if(list.manageState == option1.eq(i).val()){
						body.find('#selectId').find("option")[i].selected = true;
					}
				}
				body.find("#inp").click()
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var acceptPerson =  body.find("#acceptPerson").val();
				var handleDept = body.find("#handleDept").val();
				var acceptTime = body.find("#acceptTime").val();
				var complaintNumbers = body.find("#complaintNumbers").val();
				var cellphone=  body.find("#cellphone").val();
				var complaintPeople = body.find("#complaintPeople").val();
				var fuWuS= body.find("#fuWuS").val();
				var fuWuSCellphone= body.find("#fuWuSCellphone").val();
				var complaintContent = body.find("#complaintContent").text();
				var manageContent = body.find("#manageContent").text();
				var selectId = body.find("#selectId").val();
				$.ajax({
					url:save_info,
					type:"post",
					data:{
						"id":id,
						"acceptPerson":acceptPerson,
						"handleDept":handleDept,
						"acceptTime":acceptTime,
						"complaintNumbers":complaintNumbers,
						"cellphone":cellphone,
						"complaintPeople":complaintPeople,
						"fuWuS":fuWuS,
						"fuWuSCellphone":fuWuSCellphone,
						"complaintContent":complaintContent,
						"manageContent":manageContent,
						"selectId":selectId
						},
					success:function(){
						layer.alert("处理成功",{icon:6},function(){
							layer.closeAll();
							searchInfo();
						});
					},
					error:function(){
						layer.alert("删除失败",{icon:5});
					},
				}); 
			
			},
			end:function(){
				//getAllDevicePension();
			}
		});
	});
		}
	});
}

function callComplaint2(id){
	$.ajax({
		url:find_by_id,
		data: {
			"id": id,
		},
		type: "post",
		dataType: "json",
		success: function(json) {
		var  list = json.list;
		console.log(list);
			
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '处理投诉',
			content: basePath + 'popups/pension/returnVisit.jsp',
			btn: ['保存', '取消'],
			area: ['1000px', '80%'],
			maxmin: true,
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			},
			success: function(layero, index) {
				var body = layer.getChildFrame('body', index);
				body.find("#acceptPerson").val(list.acceptPerson);
				body.find("#handleDept").val(list.handleDept);
				body.find("#acceptTime").val(list.acceptTime);
				body.find("#complaintNumbers").val(list.complaintNumbers);
				body.find("#cellphone").val(list.cellphone);
				body.find("#complaintPeople").val(list.complaintPeople);
				body.find("#fuWuS").val(list.fuWuS);
				body.find("#fuWuSCellphone").val(list.fuWuSCellphone);
				body.find("#returnVisitContent").html(list.complaintContent);
				var option1 = body.find('#selectId').find("option");
				for(var i = 0;i<option1.length;i++){
					if(list.manageState == option1.eq(i).val()){
						body.find('#selectId').find("option")[i].selected = true;
					}
				}
				body.find("#inp").click()
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var acceptPerson =  body.find("#acceptPerson").val();
				var handleDept = body.find("#handleDept").val();
				var acceptTime = body.find("#acceptTime").val();
				var complaintNumbers = body.find("#complaintNumbers").val();
				var cellphone=  body.find("#cellphone").val();
				var complaintPeople = body.find("#complaintPeople").val();
				var fuWuS= body.find("#fuWuS").val();
				var fuWuSCellphone= body.find("#fuWuSCellphone").val();
				var manageContent = body.find("#returnVisitContent").text();
				var selectId = body.find("#selectId").val();
				var returnVisitPeople = body.find("#returnVisitPeople").val();
				var returnVisitDate = body.find("#returnVisitDate").val();
				$.ajax({
					url: basePath + "returnvisit/saveReturnVisit.do",
					data: {
						"id":id,
						"selectId": selectId,
						"returnVisitPeople": returnVisitPeople,
						"returnVisitDate": returnVisitDate
					},
					type: "post",
					dataType: "json",
					success: function(data) {
						if (data.flag == 1) {
							layer.alert("回访成功", function() {
								layer.closeAll();
							});
						}
					}
				})
			},
			end:function(){
				getAllDevicePension();
			}
		});
	});
		}
	});
}

//添加
function callComplaint(id){
	$.ajax({
		url:find_by_id,
		data: {
			"id": id,
		},
		type: "post",
		dataType: "json",
		success: function(json) {
		var  list = json.list;
		console.log(list);
			
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '处理投诉',
			content: basePath + 'popups/pension/returnVisit.jsp',
			btn: ['保存', '取消'],
			area: ['1000px', '80%'],
			maxmin: true,
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			},
			success: function(layero, index) {
				//成功加载iframe
				var body = layer.getChildFrame('body', index);
				body.find("#acceptPerson").val(list.acceptPerson);
				body.find("#handleDept").val(list.handleDept);
				body.find("#acceptTime").val(list.acceptTime);
				body.find("#complaintNumbers").val(list.complaintNumbers);
				body.find("#cellphone").val(list.cellphone);
				body.find("#complaintPeople").val(list.complaintPeople);
				body.find("#fuWuS").val(list.fuWuS);
				body.find("#fuWuSCellphone").val(list.fuWuSCellphone);
				body.find("#returnVisitContent").html(list.complaintContent);
				var option1 = body.find('#selectId').find("option");
				for(var i = 0;i<option1.length;i++){
					if(list.manageState == option1.eq(i).val()){
						body.find('#selectId').find("option")[i].selected = true;
					}
				}
				body.find("#inp").click()
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var acceptPerson =  body.find("#acceptPerson").val();
				var handleDept = body.find("#handleDept").val();
				var acceptTime = body.find("#acceptTime").val();
				var complaintNumbers = body.find("#complaintNumbers").val();
				var cellphone=  body.find("#cellphone").val();
				var complaintPeople = body.find("#complaintPeople").val();
				var fuWuS= body.find("#fuWuS").val();
				var fuWuSCellphone= body.find("#fuWuSCellphone").val();
				var complaintContent = body.find("#complaintContent").text();
				var manageContent = body.find("#manageContent").text();
				var selectId = body.find("#selectId").val();
				$.ajax({
					url: basePath + "device/saveDevice.do",
					data: {
						"deviceType": deviceType,
						"oldId": oldId,
						"deviceNo": deviceNo
					},
					type: "post",
					dataType: "json",
					success: function(data) {
						if (data.flag == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
							});
						}
					}
				})
			},
			end:function(){
				getAllDevicePension();
			}
		});
	});
		}
	});
}


function delUser(id){
	layui.use(['laypage','layer'],function() {
		var $ = layui.jquery, 
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		
		layer.confirm("确认要删除吗",{
			title:"提示",
			icon:3
		},function(index,layero){
			$.ajax({
				url:del_url,
				type:"post",
				data:{"id":id},
				success:function(){
					layer.alert("删除成功",{icon:6},function(){
						layer.closeAll();
						searchList();
					});
				},
				error:function(){
					layer.alert("删除失败",{icon:5});
				},
			}); 
		});
	});
		 
 };


//模糊查询
function searchInfo(){
	searchCount();
}

//添加信息
function addMESS(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加表单',
			content: basePath+'popups/pension/addPensionPeople.jsp',
			btn: ['保存', '取消'],
			area: ['600px', '500px'],
			maxmin: true,
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			},
			success: function(layero, index) {
				//成功加载iframe
				var body = layer.getChildFrame('body', index);
				body.find("#department").html(dept_option);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				
				var url_img = body.find("#jing_dian_img .big").find("img").attr("src");
				if(body.find('#peopleName').val() == ""){
					layer.alert("姓名不能为空！");
					return false;
				}
				if(body.find("#cellphone").val() == ""){
					layer.alert("手机号不能为空！");
					return false;
				}
				if(body.find("#duty").val() == ""){
					layer.alert("职务描述不能为空！");
					return false;
				}
				if(body.find("#age").val() == ""){
					layer.alert("年龄不能为空！");
					return false;
				}
				$.ajax({
						url:add_url,
						type:"post",
						data:{
							"age":body.find("#age").val(),
							"urlImg":url_img,
					       	"peopleName":body.find("#peopleName").val(),
				        	"sex":body.find("input[name=sex]:checked").val(),
				        	"idCard":body.find("#idCard").val(),
				        	"department":body.find("#department").val(),
				        	"cellphone":body.find("#cellphone").val(),
				        	"duty":body.find("#duty").val(),
				        	"random":Math.random()
						},
						async:true,
						success:function(json){
							
							if(json.state == "error"){
								layer.alert("操作异常！",{icon:5});
				        		
				        	}else{
				        		layer.alert("添加成功",{icon:6},function(){
				        			layer.closeAll();
				        		});
				        	}
							
						},
						error:function(){
							layer.alert("添加失败",{icon:5});
						}
	
				});
				//获取编辑页面的值，并用ajax传值到后台，返回成功关闭窗口layer.closeAll();
					
			},
			
			end:function(){
				//关闭窗口，重新刷新本页面数据
				searchCount();
			}
			
		});
		
	});
}
function showUpd(id){
	var list =null;
	$.ajax({
		url:find_byId,
		type:"post",
		data:{
        	"id":id,
        	"random":Math.random()
		},
		async:true,
		success:function(json){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '编辑表单',
			content: basePath+'popups/pension/addPensionPeople.jsp',
			btn: ['保存', '取消'],
			area: ['800px', '800px'],
			maxmin: true,
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			},
			success: function(layero, index) {
				var list = json.data;
				var body = layer.getChildFrame('body', index);
				body.find("#peopleName").val(list[0].peopleName);
				console.log(list[0].sex)
				if(list[0].sex == 2){ 
					body.find('#sex').prop("checked","checked");
				}else{
					body.find('#sex1').prop("checked","checked");
				}
				body.find("#inp").click();
				body.find("#department").html(dept_option);
				var option1 = body.find('#department').find("option");
				for(var i = 0;i<option1.length;i++){
					if(list[0].peopleDeptId == option1.eq(i).val()){
						body.find('#department').find("option")[i].selected = true;
					}
				}
				body.find("#age").val(list[0].age);
				body.find("#idCard").val(list[0].idCard);
				body.find("#cellphone").val(list[0].cellphone);
				body.find("#jing_dian_img").html("<img src ='"+list[0].imgUrl+"'>")
				body.find("#duty").val(list[0].dutyMs);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				
				var url_img = body.find("#jing_dian_img .big").find("img").attr("src");
				if(body.find('#peopleName').val() == ""){
					layer.alert("姓名不能为空！");
					return false;
				}
				if(body.find("#cellphone").val() == ""){
					layer.alert("手机号不能为空！");
					return false;
				}
				if(body.find("#duty").val() == ""){
					layer.alert("职务描述不能为空！");
					return false;
				}
				if(body.find("#age").val() == ""){
					layer.alert("年龄不能为空！");
					return false;
				}
				$.ajax({
					url:add_url,
					type:"post",
					data:{
						"id":id,
						"age":body.find("#age").val(),
						"urlImg":url_img,
				       	"peopleName":body.find("#peopleName").val(),
			        	"sex":body.find("input[name=sex]:checked").val(),
			        	"idCard":body.find("#idCard").val(),
			        	"department":body.find("#department").val(),
			        	"cellphone":body.find("#cellphone").val(),
			        	"duty":body.find("#duty").val(),
			        	"random":Math.random()
					},
					async:true,
					success:function(json){
						
						if(json.state == "error"){
							layer.alert("操作异常！",{icon:5});
			        		
			        	}else{
			        		layer.alert("修改成功",{icon:6},function(){
			        			layer.closeAll();
			        		});
			        	}
						
					},
					error:function(){
						layer.alert("添加失败",{icon:5});
					}

			});
				
			}
		});
	});
		},
		error:function(){
			layer.alert("修改失败",{icon:5});
		}
		});
}

function showItems(orderId) {
	$.ajax({
		url: basePath + "order/getOrderItems.do",
		data: {
			"orderId": orderId
		},
		type: 'post',
		dataType: 'json',
		success: function(data) {
			if (data.length > 0) {
				layui.use(['layer'], function() {
					var $ = layui.jquery,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
					layer.open({
						type: 2,
						title: '服务内容明细',
						content: basePath + 'popups/pension/showOrderItem.jsp',
						btn: ['确定'],
						area: ['600px', '80%'],
						maxmin: true,
						full: function(elem) {
							var win = window.top === window.self ? window : parent.window;
							$(win).on('resize', function() {
								var $this = $(this);
								elem.width($this.width()).height($this.height()).css({
									top: 0,
									left: 0
								});
								elem.children('div.layui-layer-content').height($this.height() - 95);
							});
						},
						success: function(layero, index) {
							//成功加载iframe
							var body = layer.getChildFrame('body', index);
							var str = "";
							for (var i = 0; i < data.length; i++) {
								var obj = data[i];
								str += "<tr>";
								str += "<td>" + obj.name + "</td>";
								str += "<td>" + obj.singlePrice + "</td>";
								str += "<td>" + obj.countNum + "</td>";
								str += "<td>" + obj.countPrice + "</td>";
								str += "</tr>";
							}
							body.find("#prov_tbd").html(str);
						},
						yes:function(index,layero){
							var body = layer.getChildFrame('body', index);
							layer.closeAll();
						},
						end:function(){
							layer.closeAll();
						}
					});
				});
			} else {
				alert("暂无服务项目！");
			}
		}
	});
	
}


function deleteOrder(id) {
	layer.confirm("您确定删除此条信息？", function() {
		$.ajax({
			url: basePath + "order/deleteOrder.do",
			data: {
				"id": id,
				"random": Math.random()
			},
			type: "post",
			dataType: "json",
			success: function(data) {
				if (data.rtn == 1) {
					layer.alert("删除成功",{icon:6},function(){
	        			layer.closeAll();
	        			searchCount();
	        		});
				}
			}, 
		});
	})
	
}


//小棉袄工单
function serviceOrder() {
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '工单录入',
			content: basePath + 'popups/agent/workOrder.jsp',
			btn: ['保存', '取消'],
			area: ['1000px', '90%'],
			maxmin: true,
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			},
			success: function(layero, index) {
				//成功加载iframe
				var body = layer.getChildFrame('body', index);
				body.find("#orderId").val(getOrderNo(0));
				/*body.find("#phone").val(callPhone);
				body.find("#name").val(oldInfo.name);
				body.find("#address").val(oldInfo.address);*/
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				
				var orderId = body.find("#orderId").val();
				var name = body.find("#name").val();
				var stationId = body.find("#stationId").val();
				var community = body.find("#community").val();
				var communityId = 0;
				var baseAddr = "";
				if (community.length > 0) {
					var comm = community.split("&");
					communityId = comm[0];
					baseAddr = comm[1];
				}
				
				var address = baseAddr + body.find("#address").val();
				var phone = body.find("#phone").val();
				var providerId = body.find("#provider").val();
				
				var itemsArr = body.find("[name='chooseItems']");
				
				var orderJson = {};
				
				for (var i = 0; i < itemsArr.length; i++) {
					var content = "";
					var countPrice = 0;
					var item = itemsArr[i];
					var tds = $(item).find("td");
					var oneVal = $(tds[5]).find("input").val();
					var items = "";
					if (oneVal > 0) {
						content += ($(tds[0]).text()) + " × " + oneVal + "；";
						countPrice += parseInt($(tds[3]).text()) * parseInt(oneVal);
						items = $(tds[0]).text() + "&" + oneVal + "&" + $(tds[3]).text() + "&" + $(tds[4]).text() + "@";
					}
					var dateVal = $(tds[2]).find("input").val();
					dateVal = dateVal.substring(0, dateVal.length - 1);
					var dateArr = dateVal.split(";");
					for (var s = 0; s < dateArr.length; s++) {
						var dateOne = dateArr[s];
						if (dateOne != null && dateOne != "") {
							var list = orderJson[dateOne];
							if (list == null || list == undefined || list == "") {
								list = {};
								list["content"] = content;
								list["countPrice"] = countPrice;
								list["items"] = items;
								orderJson[dateOne] = list;
							} else {
								var content1 = list["content"];
								var countPrice1 = list["countPrice"];
								var items1 = list["items"];
								list["content"] = content1 + content;
								list["items"] = items1 + items;
								list["countPrice"] = parseInt(countPrice1) + parseInt(countPrice);
								orderJson[dateOne] = list;
							}
						}
					}
				}
				var deliveryCost = body.find("#deliveryCost").val();
				
				$.ajax({
					url: basePath + "order/saveXMAOrder.do",
					type: 'post',
					data: {
						"orderId": orderId,
						"communityId": communityId,
						"name": name,
						"providerId": providerId,
						"stationId": stationId,
						"address": address,
						"phone": phone,
						"deliveryCost": deliveryCost,
						"orderJson": JSON.stringify(orderJson)
					},
					dataType: 'json',
					error:function(){
						layer.alert("添加失败");
					},
					success: function(data) {
						callPhone = "";
						if (data.rtn > 0) {
							layer.alert("添加成功", function() {
								layer.closeAll();
							});
							searchCount();
						} else {
							layer.alert("添加失败");
						}
					}
				});
			}
		});
	});
}


function getOrderNo(flag) {
	//0 服务单, 1咨询单, 2投诉单, 3预约挂号单, 4紧急求助单
	switch (flag) {
	case 0:
		return "FW-" + new Date().getTime();
		break;
	case 1:
		return "ZX-" + new Date().getTime();
		break;
	case 2:
		return "TS-" + new Date().getTime();
		break;
	case 3:
		return "GH-" + new Date().getTime();
		break;
	case 4:
		return "SOS-" + new Date().getTime();
		break;
	default:
		break;
	}
}


function printOrder(event) {
	var data = $(event).attr("data");
	var obj = eval("(" + data + ")");
	
	$.ajax({
		url: basePath + "order/getOrderItems.do",
		data: {
			"orderId": obj.id
		},
		type: 'post',
		dataType: 'json',
		success: function(items) {
			var itemStr = "";
			for (var i = 0; i < items.length; i++) {
				var item = items[i];
				itemStr += item.name + "×" + item.countNum + "，单价：" + item.singlePrice + "；";
			}
			
			layui.use(['layer'], function() {
				var $ = layui.jquery,
				layer = parent.layer === undefined ? layui.layer : parent.layer;
				layer.open({
					type: 2,
					title: '工单打印',
					content: basePath + 'popups/pension/settlement.jsp',
//			btn: ['保存', '取消'],
					area: ['100%', '90%'],
					maxmin: true,
					full: function(elem) {
						var win = window.top === window.self ? window : parent.window;
						$(win).on('resize', function() {
							var $this = $(this);
							elem.width($this.width()).height($this.height()).css({
								top: 0,
								left: 0
							});
							elem.children('div.layui-layer-content').height($this.height() - 95);
						});
					},
					success: function(layero, index) {
						//成功加载iframe
						var body = layer.getChildFrame('body', index);
						body.find(".name").html(obj.name);
						body.find(".phone").html(obj.phone);
						body.find(".content").html(itemStr);
						body.find(".address").html(obj.address);
						body.find(".dates").html((obj.dates == null ? "--" : myDate.longToDateYMD(obj.dates)));
						body.find(".providerName").html(obj.providerName);
						layer.closeAll();
					},
				});
			});
		}
	});
	
}


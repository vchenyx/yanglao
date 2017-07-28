layui.config({
	base: basePath + 'back/plugins/layui/modules/'
});

var isOpen = true;
var open_close = false;

init();
$(function() {
//	getOldInfoByPhone();
	writeInfo();
})

var oldInfo;
var callPhone;

function writeInfo() {
	var callInPhone = $("#callInPhone", window.parent.document).val();
	callPhone = callInPhone;
	$("#callInPhone", window.parent.document).val("");
	if (callInPhone != null && callInPhone != "") {
		$("#phone").val(callInPhone);
		$.ajax({
			url: basePath + "call/getInfoByPhone.do",
			data: {
				"phone": callInPhone
			},
			type: "post",
			dataType: "json",
			success: function(data) {
				oldInfo = data;
				if (data != null && data != "") {
					var name = data.name;
					var sex = data.sex == 0 ? '男' : '女';
					var age = getAge(data.birthday);
					var maritalStatus = data.maritalStatus;
					var income = data.income;
					var linkName = data.linkName;
					var linkPhone = data.linkPhone;
					var idCard = data.idCard;
					var address = data.address;
					$("#name").val(name);
					$("#sex").val(sex);
					$("#age").val(age);
					$("#maritalStatus").val(maritalStatus);
					$("#income").val(income);
					$("#idCard").val(idCard);
					$("#address").val(address);
					$("#linkName").val(linkName);
					$("#linkPhone").val(linkPhone);
					placePosition(address);
				}
			}
		});
	}
}

//初始化（表格、单选框初始化）
function init(){
	layui.use(['icheck', 'laypage','layer','form'], function() {
		$ = layui.jquery,
		laypage = layui.laypage,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		$('input').iCheck({
			checkboxClass: 'icheckbox_flat-green'
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
}

layui.use(['laypage','layer','form'], function() {
	$ = layui.jquery,
	laypage = layui.laypage,
	form = layui.form,
	layer = parent.layer === undefined ? layui.layer : parent.layer;
	//page
	/*laypage({
		cont: 'page',
		pages: 25, //总页数
		groups: 5, //连续显示分页数
		jump: function(obj, first) {
			//得到了当前页，用于向服务端请求对应数据
			var curr = obj.curr;
			if(!first) {
				layer.msg('第 '+ obj.curr +' 页');
			}
		}
	});*/

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
				$(".layui-form").animate({"height":"100px"},400);
				open_close = true;
			}
			setTimeout(function(){
				isOpen = true;},400);
		}
	});

});


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
				body.find("#phone").val(callPhone);
				body.find("#orderId").val(getOrderNo(0));
				if (oldInfo != null && oldInfo != undefined) {
					body.find("#name").val(oldInfo.name);
					body.find("#address").val(oldInfo.address);
					body.find("#oldId").val(oldInfo.id);
				}
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				
				var orderId = body.find("#orderId").val();
				var name = body.find("#name").val();
				var stationId = body.find("#stationId").val();
				var oldId = body.find("#oldId").val();
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
						if (dataOne != null && dateOne != "") {
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
						"oldId": oldId,
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
						} else {
							layer.alert("添加失败");
						}
					}
				});
			}
		});
	});
}

//工单录入
function serviceForward(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '工单录入',
			content: basePath + 'popups/pension/workOrder.jsp',
			btn: ['保存', '取消'],
			area: ['650px', '90%'],
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
				body.find("#orderNo").val(getOrderNo(0));
				body.find("#handleDept").val(pensionName);
				body.find("#acceptTime").val(longToDate(new Date().getTime()));
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var orderNo = body.find("#orderNo").val();
				var handleDept = body.find("#handleDept").val();
				var acceptPerson = body.find("#acceptPerson").val();
				var acceptTime = body.find("#acceptTime").val();
				var linkName = body.find("#linkName").val();
				var linkPhone = body.find("#linkPhone").val();
				var serviceType = body.find("input[name='serviceType']:checked").val();
				var serviceClassify = body.find("#serviceClassify").val();
				var serviceItem = body.find("#serviceItem").val();
				var serviceAddr = body.find("#serviceAddr").val();
				var serviceTime = body.find("#serviceTime").val();
				var isUrgency = body.find("input[name='isUrgency']:checked").val();
				var payType = body.find("input[name='payType']:checked").val();
				var remarks = body.find("#remarks").val();
				var state = 0;
				$.ajax({
					url: basePath + "order/saveOrder.do",
					type: 'post',
					data: {
						'orderNo': orderNo,
						'handleDept': handleDept,
						'acceptPerson': acceptPerson,
						'acceptTime': new Date(acceptTime).getTime(),
						'linkName': linkName,
						'linkPhone': linkPhone,
						'serviceType': serviceType,
						'serviceClassify': serviceClassify,
						'serviceItem': serviceItem,
						'serviceAddr': serviceAddr,
						'serviceTime': new Date(serviceTime).getTime(),
						'isUrgency': isUrgency,
						'payType': payType,
						'remarks': remarks,
						'state': state
					},
					dataType: 'json',
					error:function(){
						layer.alert("添加失败");
					},
					success: function(data) {
						if (data.flag == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
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
//来电咨询
function serviceConsult(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '来电咨询',
			content: basePath + 'popups/agent/callConsult.jsp',
			btn: ['保存', '取消'],
			area: ['650px', '90%'],
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
				body.find("#orderNo").val(getOrderNo(1));
				body.find("#handleDept").val(pensionName);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var orderNo = body.find("#orderNo").val();
				var handleDept = body.find("#handleDept").val();
				var acceptPerson = body.find("#acceptPerson").val();
				var acceptTime = body.find("#acceptTime").val();
				var linkName = body.find("#linkName").val();
				var linkPhone = body.find("#linkPhone").val();
				var consultContent = body.find("#consultContent").val();
				var state = body.find("#state").val();
				
				$.ajax({
					url: basePath + "order/saveConsult.do",
					type: 'post',
					data: {
						'orderNo': orderNo,
						'handleDept': handleDept,
						'acceptPerson': acceptPerson,
						'acceptTime': new Date(acceptTime).getTime(),
						'linkName': linkName,
						'linkPhone': linkPhone,
						'consultContent': consultContent,
						'state': state
					},
					dataType: 'json',
					error:function(){
						layer.alert("添加失败");
					},
					success: function(data) {
						if (data.flag == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
							});
						} else {
							layer.alert("添加失败");
						}
					}
				});
			},
			end:function(){
			}
		});
	});
}
//来电投诉
function complaint(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '来电投诉',
			content: basePath + 'popups/agent/callComplaint.jsp',
			btn: ['保存', '取消'],
			area: ['650px', '90%'],
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
				body.find("#complaintNumbers").val(getOrderNo(2));
				body.find("#cellphone").val(oldInfo.phone);
				
				body.find("#complaintPeople").val(oldInfo.name);
				body.find("#acceptTime").val(longToDate(new Date().getTime()));
				
				
				$.ajax({
					url: basePath + "order/getLinkOrder.do",
					type: "post",
					data: {
						"linkPhone": oldInfo.phone,
						"random": Math.random()
					},
					async: false,
					dataType: "json",
					success: function(data) {
						var str = '<option value="">--请选择--</option><option value="">--不关联--</option>';
						for (var i = 0; i < data.length; i++) {
							var obj = data[i];
							var proType = (obj.project == 0 ? '助餐' : obj.project == 1 ? '助医' : obj.project == 2 ? '助洁' : obj.project == 3 ? '助浴' : '');
							
							str += '<option value="' + obj.orderId + '">' + proType + "-" + obj.content + '</option>';
						}
						body.find("#linkOrderNo").html(str);
						body.find("#inp").click();
					}
				});
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var complaintNumbers = body.find("#complaintNumbers").val();
				var stationId = body.find("#stationId").val();
				var acceptTime = body.find("#acceptTime").val();
				var complaintPeople = body.find("#complaintPeople").val();
				var cellphone = body.find("#cellphone").val();
				var linkOrderNo = body.find("#linkOrderNo").val();
				var complaintContent = body.find("#complaintContent").val();
				var manageState = body.find("#manageState").val();
				var manageContent = body.find("#manageContent").val();
				
				$.ajax({
					url: basePath + "order/saveComplaint.do",
					type: 'post',
					data: {
						'complaintNumbers': complaintNumbers,
						'stationId': stationId,
						'acceptTime': new Date(acceptTime).getTime(),
						'complaintPeople': complaintPeople,
						'cellphone': cellphone,
						'linkOrderNo': linkOrderNo,
						'complaintContent': complaintContent,
						'manageState': manageState,
						'manageContent': manageContent
					},
					dataType: 'json',
					/*error:function(){
						layer.alert("添加失败");
					},*/
					success: function(data) {
						if (data.rtn == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
							});
						} else {
							layer.alert("添加失败");
						}
					}
				})
			},
			end:function(){
			}
		});
	});
}
//预约挂号
function forwardRegist(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '预约挂号',
			content: basePath + 'popups/pension/callForward.jsp',
			btn: ['保存', '取消'],
			area: ['650px', '90%'],
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
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
			},
			end:function(){
			}
		});
	});
}
//紧急求助
function newSOS(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '紧急求助',
			content: basePath + 'popups/agent/callSOS.jsp',
			btn: ['保存', '取消'],
			area: ['650px', '90%'],
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
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
			},
			end:function(){
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


function longToDate(longTime) {
	var date = new Date(longTime);
	var year = date.getFullYear();
	var month = date.getMonth() < 10 ? "0" + date.getMonth() : date.getMonth();
	var day = date.getDay() < 10 ? "0" + date.getDay() : date.getDay();
	var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}


function getAge(longTime) {
	var now = new Date().getFullYear();
	var born = new Date(longTime).getFullYear();
	return now - born;
}





layui.config({
	base: basePath + 'back/plugins/layui/modules/'
});

var isOpen = true;
var open_close = false;

init();
$(function() {
	getAll();
})
function getAll() {
	$.ajax({
		url: basePath + "provider/getAllProvider.do",
		type: "get",
		dataType: "json",
		success: function(data) {
			var typeMap = data.typeMap;
			var list = data.list;
			var str = "";
			for (var i = 0; i < list.length; i++) {
				var obj = list[i];
				str += '<tr>';
				str += '<td>' + typeMap[obj.typeId] + '</td>';
				str += '<td>' + obj.name + '</td>';
				str += '<td>' + obj.address + '</td>';
				str += '<td>' + obj.linkName + '</td>';
				str += '<td>' + obj.linkPhone + '</td>';
				str += '<td>' + obj.serviceScope + '</td>';
				str += '<td>' + (obj.state == "1" ? '可用' : '不可用') + '</td>';
				str += '<td>' + obj.grade + '</td>';
				str += '<td>' + (obj.createTime == null ? "--" : myDate.longToDateYMD(obj.createTime)) + '</td>';
				str += '<td>';
				str += '<a href="javascript:;"  data-id="1" data-opt="del" onclick="addServiceItem(' + obj.id + ')" class="layui-btn layui-btn-danger layui-btn-mini">添加服务项目</a>';
				str += '<a href="javascript:;"  data-id="1" data-opt="del" onclick="showServiceItems(' + obj.id + ')" class="layui-btn layui-btn-danger layui-btn-mini">查看服务项目</a>';
				str += 	'</td>';
				str += '</tr>';
			}
			$("#prov_tbd").html(str);
		}
	})
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

//添加
function addMESS(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加服务商',
			content: basePath + 'popups/pension/addProvider.jsp',
			btn: ['保存', '取消'],
			area: ['900px', '80%'],
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
				var typeId = body.find("#typeId").val();
				var name = body.find("#name").val();
				var address = body.find("#address").val();
				var linkName = body.find("#linkName").val();
				var linkPhone = body.find("#linkPhone").val();
				var serviceScope = body.find("#serviceScope").val();
				$.ajax({
					url: basePath + "provider/addProvider.do",
					data: {
						"typeId": typeId,
						"name": name,
						"address": address,
						"linkName": linkName,
						"linkPhone": linkPhone,
						"serviceScope": serviceScope,
					},
					type: "post",
					dataType: "json",
					success: function(data) {
						if (data.rtn == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
								getAll();
							});
						}
					}
				})
				
			},
			end:function(){
				
			}
		});
	});
}

function showServiceItems(id) {
	$.ajax({
		url: basePath + "provider/gatAllServiceItems.do",
		data: {
			"providerId": id
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
						title: '添加服务项目',
						content: basePath + 'popups/pension/showProviderItem.jsp',
						btn: ['保存', '取消'],
						area: ['900px', '80%'],
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
								str += "<td>" + obj.servicePro + "</td>";
								str += "<td>" + obj.serviceContent + "</td>";
								str += "<td>" + obj.unitPrice + "</td>";
								str += "<td>" + obj.serviceUnit + "</td>";
								str += "<td>" + obj.serviceStandard + "</td>";
								str += "<td>" + obj.state + "</td>";
								str += "<td>" + "" + "</td>";
								str += "</tr>";
							}
							body.find("#prov_tbd").html(str);
						},
						yes:function(index,layero){
							var body = layer.getChildFrame('body', index);
							
						},
						end:function(){
							
						}
					});
				});
			} else {
				alert("暂无服务项目！");
			}
		}
	});
}

//添加服务项目
function addServiceItem(id){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加服务项目',
			content: basePath + 'popups/pension/addProviderItem.jsp',
			btn: ['保存', '取消'],
			area: ['900px', '80%'],
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
				var servicePro = body.find("#servicePro").val();
				var serviceContent = body.find("#serviceContent").val();
				var unitPrice = body.find("#unitPrice").val();
				var serviceUnit = body.find("#serviceUnit").val();
				var serviceStandard = body.find("#serviceStandard").val();
				$.ajax({
					url: basePath + "provider/addProviderItem.do",
					data: {
						"providerId": id,
						"servicePro": servicePro,
						"serviceContent": serviceContent,
						"unitPrice": unitPrice,
						"serviceUnit": serviceUnit,
						"serviceStandard": serviceStandard,
					},
					type: "post",
					dataType: "json",
					success: function(data) {
						if (data.rtn == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
								getAll();
							});
						}
					}
				})
				
			},
			end:function(){
				
			}
		});
	});
}

//修改
function updateProvider(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '修改服务商',
			content: basePath + 'popups/pension/addProvider.jsp',
			btn: ['保存', '取消'],
			area: ['900px', '80%'],
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
				var providerId = body.find("#providerId").val()
				var name = body.find("#name").val()
				var block = body.find("#block").val()
				var community = body.find("#community").val()
				var project = body.find("#project").val()
				var contact = body.find("#contact").val()
				var phone = body.find("#phone").val()
				var grade = body.find("input[name='grade']:checked").val()
				var state = body.find("input[name='state']:checked").val()
				var json = {};
				json["providerId"] = providerId;
				json["name"] = name;
				json["block"] = block;
				json["community"] = community;
				json["project"] = project;
				json["contact"] = contact;
				json["phone"] = phone;
				json["grade"] = grade;
				json["state"] = state;
				/*alert(
						"name:" + name + "," +
						"sex:" + sex + "," +
						"age:" + age + "," +
						"idCard:" + idCard + "," +
						"phone:" + phone + "," +
						"headImg:" + headImg + "," +
						"eduBackground:" + eduBackground
				);*/
				$.ajax({
					url: basePath + "provider/userRegisiter.do",
					data: {
						"jsonStr": JSON.stringify(json),
					},
					type: "post",
					dataType: "json",
					success: function(data) {
						if (data.rtn == 1) {
							layer.alert("修改成功", function() {
								layer.closeAll();
								getAll();
							});
						}
					}
				})
				
			},
			end:function(){
				
			}
		});
	});
}


function deleteProvider(id) {
	layui.confirm("确定删除?", {icon: 5}, function() {
		alert(1);
	})
}


function deleteProvider(event) {
	var objStr = $(event).attr("msg");
	layui.layer.confirm("确定删除?", function() {
		$.ajax({
			url: basePath + "provider/deleteProvider.do",
			data: eval("(" + objStr + ")"),
			type: 'post',
			dataType: 'json',
			success: function(data) {
				if (data.flag == 1) {
					layui.layer.alert("删除成功", function() {
						layui.layer.closeAll();
						getAll();
					});
				}
			}
		});
	})
}






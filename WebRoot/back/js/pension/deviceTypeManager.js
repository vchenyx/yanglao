layui.config({
	base: 'plugins/layui/modules/'
});

var isOpen = true;
var open_close = false;

init();

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
	laypage({
		cont: 'page',
		pages: 25, //总页数
		groups: 5, //连续显示分页数
		jump: function(obj, first) {
			//得到了当前页，用于向服务端请求对应数据
			var curr = obj.curr;
			if(!first) {
//				layer.msg('第 '+ obj.curr +' 页');
			}
		}
	});

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

$(function() {
	getAllDeviceType();
});

function getAllDeviceType() {
	$.ajax({
		url: basePath + "device/getAllDeviceType.do",
		type: 'get',
		dataType: 'json',
		success: function(data) {
			if (data.length > 0) {
				var str = '';
				for (var i = 0; i < data.length; i++) {
					var obj = data[i];
					str += '<tr>';
					str += '<td>' + obj.deviceType + '</td>';
					str += '<td>' + obj.proFactory + '</td>';
					str += '<td>' + obj.deviceDesc + '</td>';
					str += '<td>' + obj.proFunction + '</td>';
					str += '<td>' + longToDate(obj.enterTime) + '</td>';
					str += '<td><img src="' + basePath + obj.deviceImg + '" height="100px"></td>';
					str += '<td>';
					str += '<a href="javascript:;" onclick="deleteNurse(' + obj.id + ')" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>';
					str += '</td>';
					str += '</tr>';
				}
				$("#show_tbd").html(str);
			}
		}
	})
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

//添加
function addMESS(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加设备类型',
			content: basePath + 'popups/pension/addDeviceType.jsp',
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
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var deviceType = body.find("#deviceType").val();
				var desc = body.find("#desc").val();
				var deviceImg = body.find("#deviceImg").val();
				var proFactory = body.find("#proFactory").val();
				var proFunction = body.find("#proFunction").val();
//				alert(deviceType + "------" + desc + "------" + deviceImg);
				$.ajax({
					url: basePath + "device/saveDeviceType.do",
					data: {
						"deviceType": deviceType,
						"deviceDesc": desc,
						"deviceImg": deviceImg,
						"proFunction": proFunction,
						"proFactory": proFactory
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
				getAllDeviceType();
			}
		});
	});
}

function deleteNurse(id) {
	layer.confirm("确定删除?", function() {
		$.ajax({
			url: basePath + "pension/deleteNurse.do?id=" + id,
			type: "post",
			dataType: "json",
			success: function(data) {
				layer.alert("删除成功", function() {
					layer.closeAll();
					getAllDeviceType();
				});
			}
		})
	})
}

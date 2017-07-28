layui.config({
	base: 'plugins/layui/modules/'
});

var isOpen = true;
var open_close = false;

init();

//初始化（表格、单选框初始化）
function init(){
	layui.use(['layer','form'], function() {
		$ = layui.jquery,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
	});
}

layui.use(['layer','form'], function() {
	$ = layui.jquery,
	laypage = layui.laypage,
	form = layui.form,
	layer = parent.layer === undefined ? layui.layer : parent.layer;
});

$(function() {
	getCommunityList();
});

function getCommunityList() {
	
	$.ajax({
		url: basePath + "community/getCommunityList.do",
		type: 'get',
		dataType: 'json',
		success: function(data) {
			if (data.length > 0) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					var obj = data[i];
					str += '<tr>';
					str += '<td>' + obj.name + '</td>';
					str += '<td>' + obj.address + '</td>';
					str += '<td>';
					str += '<a msg=\''+JSON.stringify(obj)+'\' href="javascript:;" onclick="updateCommunity(this)" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">修改</a>&nbsp;&nbsp;';
					str += '<a href="javascript:;" onclick="deleteCommunity(' + obj.id + ')" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>';
					str += '</td>';
					str += '</tr>'
				}
				$("#content_list").html(str);
			}
		}
	})
}


//添加
function addMESS(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加社区',
			content: basePath + 'popups/pension/addCommunity.jsp',
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
				var name = body.find("#name").val();
				var address = body.find("#address").val();
				$.ajax({
					url: basePath + "community/saveCommunity.do",
					data: {
						"name": name,
						"address": address
					},
					type: "post",
					dataType: "json",
					success: function(data) {
						if (data.rtn == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
								getCommunityList();
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
function updateCommunity(event){
	var community = eval("(" + $(event).attr("msg") + ")");
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '修改社区',
			content: basePath + 'popups/pension/addCommunity.jsp',
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
				body.find("#name").val(community.name);
				body.find("#address").val(community.address);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var name = body.find("#name").val();
				var address = body.find("#address").val();
				community["name"] = name;
				community["address"] = address;
				$.ajax({
					url: basePath + "community/updateCommunity.do",
					data: community,
					type: "post",
					dataType: "json",
					success: function(data) {
						if (data.rtn == 1) {
							layer.alert("修改成功", function() {
								layer.closeAll();
								getCommunityList();
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

function deleteCommunity(id) {
	layer.confirm("您确定删除此条信息？", function() {
		$.ajax({
			url: basePath + "community/deleteCommunity.do",
			data: {
				"id": id
			},
			dataType: "json",
			type: "post",
			success: function(data) {
				if (data.rtn == 1) {
					layer.alert("删除成功", function() {
						layer.closeAll();
						getCommunityList();
					});
				}
			}
		});
	});
}


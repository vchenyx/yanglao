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
		url: basePath + "provider/getAllProviderType.do",
		type: "get",
		dataType: "json",
		success: function(data) {
			if (data.length > 0) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					var obj = data[i];
					str += '<tr>';
					str += '<td>' + obj.name + '</td>';
					str += '<td>' + myDate.longToDate(obj.createTime) + '</td>';
					str += '<td>';
					str += '<a href="javascript:;" msg=\'' +JSON.stringify(obj)+ '\' data-id="1" data-opt="del" onclick="deleteProviderClass(this)" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>';
					str += 	'</td>';
					str += '</tr>';
				}
				$("#prov_tbd_class").html(str);
			}
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
			title: '添加分类',
			content: basePath + 'popups/pension/addProviderCla.jsp',
			btn: ['保存', '取消'],
			area: ['800px', '70%'],
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
				$.ajax({   
					url: basePath + "provider/addProviderType.do",
					data: {
						"name": name,
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


function deleteProviderClass(id) {
	layui.confirm("确定删除?", {icon: 5}, function() {
		alert(1);
	})
}


function deleteProviderClass(event) {
	var objStr = $(event).attr("msg");
	layui.layer.confirm("确定删除?", function() {
		$.ajax({
			url: basePath + "provider/deleteProviderClass.do",
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




layui.config({
	base: basePath + 'back/plugins/layui/modules/'
});

var isOpen = true;
var open_close = false;

init();

$(function() {
	getAllWaitAudit();
})

function getAllWaitAudit() {
	$.ajax({
		url: basePath + "admin/getPensionStationRequest.do",
		type: 'get',
		dataType: 'json',
		success: function(data) {
			if (data.length > 0) {
				var objStr = '';
				for (var i = 0; i < data.length; i++) {
					var obj = data[i];
					objStr += '<tr>';
					objStr += '<td>' + obj.name + '</td>'
					objStr += '<td>' + obj.address + '</td>'
					objStr += '<td>' + obj.linkName + '</td>'
					objStr += '<td>' + obj.linkPhone + '</td>'
					objStr += '<td>' + obj.email + '</td>'
					objStr += '<td>' + obj.requestTime + '</td>'
					objStr += '<td>';
					objStr += '<a msg=\'' + JSON.stringify(obj) + '\' href="javascript:;" onclick="javascript:addMESS(this);" class="layui-btn layui-btn-mini">审 核</a>';
					objStr += '</td>';
					objStr += '</tr>';
				}
				$("#table_show").html(objStr);
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

});

//添加
function addMESS(event){
	var objStr = $(event).attr("msg");
	var obj = eval("(" + objStr + ")");
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '审核信息',
			content: basePath + 'popups/admin/audit.jsp',
			btn: ['提交', '取消'],
			area: ['1020px', '80%'],
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
				body.find('#name').val(obj.name);
				body.find('#address').val(obj.address);
				body.find('#linkName').val(obj.linkName);
				body.find('#linkPhone').val(obj.linkPhone);
				body.find('#email').val(obj.email);
				body.find('#creationTime').val(obj.creationTime);
				var str1 = '<div class="img"><img src="' + basePath + obj.certificate1 + '" alt="" /></div>';
				var str2 = '<div class="img"><img src="' + basePath + obj.certificate2 + '" alt="" /></div>';
				var str3 = '<div class="img"><img src="' + basePath + obj.certificate3 + '" alt="" /></div>';
				body.find('#str1').html(str1);
				body.find('#str2').html(str2);
				body.find('#str3').html(str3);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				through = body.find('input[name="through"]:checked').val();
				alert(through);
				$.ajax({
					url: basePath + 'admin/ratifyPensionStation.do',
					type: 'post',
					data: {
						'id': obj.id,
						'areaId': obj.areaId,
						'isPass': through,
						'msg': body.find('#msg').val()
					},
					dataType: 'post',
					success: function(data) {
						layer.alert("操作成功！");
					}
				})
			},
			end:function(){
				getAllWaitAudit();
			}
		});
	});
}






































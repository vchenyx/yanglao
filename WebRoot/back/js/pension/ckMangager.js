layui.config({
	base: basePath + 'back/plugins/layui/modules/'
});

var isOpen = true;
var open_close = false;
init();
$(function() {
	getAllOld();
})
function getAllOld() {
	$.ajax({
		url: basePath + "pension/getAllOldList.do",
		type: "get",
		dataType: "json",
		success: function(data) {
			if (data.length > 0) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					var obj = data[i];
					str += '<tr>';
					str += '<td>' + obj.name + '</td>';
					str += '<td>' + (obj.sex == 1 ? '男' : '女') + '</td>';
					str += '<td>' + obj.age + '</td>';
					str += '<td>' + obj.idCard + '</td>';
					str += '<td>' + obj.phone + '</td>';
					str += '<td>' + (obj.guardianName == null ? "" : obj.guardianName) + '</td>';
					str += '<td>' + (obj.guardianPhone == null ? "" : obj.guardianPhone) + '</td>';
					str += '<td>' + obj.address + '</td>';
					str += '<td>';
					str += 	'</td>';
					str += '</tr>';
				}
				$("#show_tbd").html(str);
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
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
	getAllNurse();
});

function getAllNurse() {
	$.ajax({
		url: basePath + "pension/getAllNurseList.do",
		type: 'get',
		dataType: 'json',
		success: function(data) {
			if (data.length > 0) {
				var str = '';
				for (var i = 0; i < data.length; i++) {
					var obj = data[i];
					str += obj[i];
					var eduBackground = '';
					if (obj.eduBackground == 1) {
						eduBackground = '初中及以下';
					}
					if (obj.eduBackground == 2) {
						eduBackground = '高中';
					}
					if (obj.eduBackground == 3) {
						eduBackground = '大专';
					}
					if (obj.eduBackground == 4) {
						eduBackground = '本科';
					}
					if (obj.eduBackground == 5) {
						eduBackground = '研究生及以上';
					}
					str += '<tr>';
					str += '<td>' + obj.name + '</td>';
					str += '<td>' + obj.age + '</td>';
					str += '<td>' + (obj.sex == 1 ? '男' : '女') + '</td>';
					str += '<td>' + eduBackground + '</td>';
					str += '<td>' + '<img height="80px" src="' + basePath + obj.headImg + '" alt="头像">' + '</td>';
					str += '<td>' + new Date(obj.createTime).toLocaleDateString() + '</td>';
					str += '<td>';
					str += '<a href="javascript:;" onclick="deleteNurse(' + obj.id + ')" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>';
					str += '</td>';
					str += '</tr>';
				}
				$("#tbd_show").html(str);
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
			title: '添加护工',
			content: basePath + 'popups/pension/addNurse.jsp',
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
				var name = body.find("#peopleName").val()
				var sex = body.find("input[name='sex']:checked").val()
				var age = body.find("#age").val()
				var idCard = body.find("#idCard").val()
				var phone = body.find("#cellphone").val()
				var headImg = body.find("#headImg").val()
				var eduBackground = body.find("#eduBackground").val()
				var address = body.find("#address").val()
				var json = {};
				json["name"] = name;
				json["sex"] = sex;
				json["age"] = age;
				json["idCard"] = idCard;
				json["phone"] = phone;
				json["headImg"] = headImg;
				json["eduBackground"] = eduBackground;
				json["pensionStationId"] = pensionId;
				json["address"] = address;
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
					url: basePath + "register/userRegisiter.do",
					data: {
						"flag": 2,
						"jsonStr": JSON.stringify(json),
						"pwd": ""
					},
					type: "post",
					dataType: "json",
					success: function(data) {
						if (data.rtn == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
							});
						}
					}
				})
				
			},
			end:function(){
				getAllNurse();
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
					getAllNurse();
				});
			}
		})
	})
}

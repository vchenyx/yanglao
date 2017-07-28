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
		url: basePath + "guradian/getAllOld.do",
		type: "get",
		dataType: "json",
		success: function(data) {
			if (data.length > 0) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					var obj = data[i];
					str += '<tr>';
					str += '<td>' + obj.name + '</td>';
					str += '<td>' + (obj.relation == null ? "--" : obj.relation) + '</td>';
					str += '<td>' + (obj.sex == 1 ? '男' : '女') + '</td>';
					str += '<td>' + obj.age + '</td>';
					str += '<td>' + obj.idCard + '</td>';
					str += '<td>' + obj.phone + '</td>';
					str += '<td>' + obj.address + '</td>';
					str += '<td>' + (obj.pensionStationName == null ? "--" : obj.pensionStationName) + '</td>';
					str += '<td>';
					str += '<a href="javascript:;" class="layui-btn layui-btn-mini" msg=\''+ JSON.stringify(obj) +'\' onclick="editOld(this)">编辑</a>';
					str += '<a href="javascript:;" data-id="1" data-opt="del" onclick="deleteOld(' + obj.id +  ')" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>';
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

//添加
function addMESS(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加老人',
			content: basePath + 'popups/guardian/addOlder.jsp',
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
				var relation = body.find("#relation").val()
				var pensionStationId = body.find("#pensionStationId").val()
				var cardNo1 = body.find("#cardNo1").val()
				var cardNo2 = body.find("#cardNo2").val()
				var cardNo3 = body.find("#cardNo3").val()
				var cardNo4 = body.find("#cardNo4").val()
				var address = body.find("#address").val()
				var illness = body.find("#illness").val()
				var json = {};
				json["name"] = name;
				json["sex"] = sex;
				json["age"] = age;
				json["idCard"] = idCard;
				json["phone"] = phone;
				json["headImg"] = headImg;
				json["eduBackground"] = eduBackground;
				json["guardianId"] = guardianId;
				json["relation"] = relation;
				json["pensionStationId"] = pensionStationId;
				json["cardNo4"] = cardNo4;
				json["cardNo3"] = cardNo3;
				json["cardNo2"] = cardNo2;
				json["cardNo1"] = cardNo1;
				json["address"] = address;
				json["illness"] = illness;
				$.ajax({
					url: basePath + "register/userRegisiter.do",
					data: {
						"flag": 0,
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
				getAllOld();
			}
		});
	});
}

function editOld(event) {
	var msg = eval("(" + $(event).attr("msg") + ")");
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加老人',
			content: basePath + 'popups/guardian/updateOlder.jsp',
			btn: ['保存', '取消'],
			area: ['650px', '80%'],
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
				body.find("#peopleName").val(msg.name);
				body.find("#age").val(msg.age)
				body.find("#idCard").val(msg.idCard);
				body.find("#cellphone").val(msg.phone);
				body.find("#headImg").val(msg.headImg);
				body.find("#eduBackground").val(msg.eduBackground);
				body.find("#relation").val(msg.relation);
				body.find("#pensionStationId").val(msg.pensionStationId);
				body.find("#cardNo1").val(msg.cardNo1);
				body.find("#cardNo2").val(msg.cardNo2);
				body.find("#cardNo3").val(msg.cardNo3);
				body.find("#cardNo4").val(msg.cardNo4);
				body.find("#address").val(msg.address);
				body.find("#illness").val(msg.illness);
				body.find("#id").val(msg.id);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var id = body.find("#id").val()
				var name = body.find("#peopleName").val()
				var sex = body.find("input[name='sex']:checked").val()
				var age = body.find("#age").val()
				var idCard = body.find("#idCard").val()
				var phone = body.find("#cellphone").val()
				var headImg = body.find("#headImg").val()
				var eduBackground = body.find("#eduBackground").val()
				var relation = body.find("#relation").val()
				var pensionStationId = body.find("#pensionStationId").val()
				var cardNo1 = body.find("#cardNo1").val()
				var cardNo2 = body.find("#cardNo2").val()
				var cardNo3 = body.find("#cardNo3").val()
				var cardNo4 = body.find("#cardNo4").val()
				var address = body.find("#address").val()
				var illness = body.find("#illness").val()
				$.ajax({
					url: basePath + "guradian/updateOldInfo.do",
					data: {
						"id": id,
						"name": name,
						"sex": sex,
						"age": age,
						"idCard": idCard,
						"phone": phone,
						"headImg": headImg,
						"eduBackground": eduBackground,
						"guardianId": guardianId,
						"relation": relation,
						"pensionStationId": msg.pensionStationId,
						"cardNo4": cardNo4,
						"cardNo3": cardNo3,
						"cardNo2": cardNo2,
						"cardNo1": cardNo1,
						"address": address,
						"illness": illness,
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
				getAllOld();
			}
		});
	});
}

function deleteOld(id) {
	layui.confirm("确定删除?", {icon: 5}, function() {
		alert(1);
	})
}




































layui.config({
	base: 'plugins/layui/modules/'
});

var isOpen = true;
var open_close = false;

init();

//初始化（表格、单选框初始化）
function init(){
	layui.use(['icheck','layer','form'], function() {
		$ = layui.jquery,
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
//	laypage({
//		cont: 'page',
//		pages: 25, //总页数
//		groups: 5, //连续显示分页数
//		jump: function(obj, first) {
//			//得到了当前页，用于向服务端请求对应数据
//			var curr = obj.curr;
//			if(!first) {
////				layer.msg('第 '+ obj.curr +' 页');
//			}
//		}
//	});

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
				$(".layui-form").animate({"height":"70px"},400);
				open_close = true;
			}
			setTimeout(function(){
				isOpen = true;},400);
		}
	});

});

//通过审核
//添加
function throughShenHe(dt){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.confirm("确认通过审核?",function(){
			layer.alert("审核通过",function(){
				$(dt).parent().parent().remove();
				layer.closeAll();
			})
		})
	});
}


//添加
function addMESS(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加老人',
			content: 'tanchuceng/laidiantousu.html',
			btn: ['添加', '取消'],
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
				getAllValue(body);
				var peopleName = body.find("#peopleName").val();
				var sex = body.find("input[name = 'sex']:checked").val();
				var age = body.find("#age").val();
				var idCard = body.find("#idCard").val();
				var cellphone = body.find("#cellphone").val();
				var eduBackground = body.find("#eduBackground").find("option:selected").val();
//				$.ajax({
//					type:'post',
//					url:'',
//					async:false,
//					dataType:"json",
//					data:{
//						"random":Math.random()
//					},
//					error:function(){
//						alert("失败");
//					},
//					success:function(json){
//						alert(json);
//					}
//				})
				
			},
			end:function(){
				
			}
		});
	});
}

//编辑
function edit(d1,d2,d3,d4,d5,d6){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加老人',
			content: 'temp/edit_olderInfo.html',
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
				body.find("#peopleName").val(d1);
				body.find("input[name='sex']").eq(1).prop("checked","checked");
				body.find("#age").val(d3);
				body.find("#idCard").val(d4);
				body.find("#cellphone").val(d5);
				body.find("#eduBackground").val(d6);
				body.find("#inp").click();
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var peopleName = body.find("#peopleName").val();
				var sex = body.find("input[name = 'sex']:checked").val();
				var age = body.find("#age").val();
				var age = body.find("#idCard").val();
				var cellphone = body.find("#cellphone").val();
				var eduBackground = body.find("#eduBackground").find("option:selected").val();
				$.ajax({
					type:'post',
					url:'',
					async:false,
					dataType:"json",
					data:{
						"random":Math.random()
					},
					error:function(){
						alert("失败");
					},
					success:function(json){
						alert(json);
					}
				})
				
			},
			end:function(){
				
			}
		});
	});
}

function getAllValue(body){
	console.log(body.find("#selectTime").val())
	var json = "";
	var num = body.find(".jiwangshi input");
	for(var i = 0;i<num.length;i++){
		if(num.eq(i).prop("checked")){
			json += num.eq(i).val() + ",";
		}
	}
	var dataJson = $.trim(json);
	console.log(dataJson.substring(0,dataJson.length-1));
	
//	var chencked = body.find("input[type = 'checkbox']:checked").val(); 
//	alert(chencked)

	$.get("url?callback=?",{data1:"data1",data2:"data2"})


}






































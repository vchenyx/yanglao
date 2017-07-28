layui.config({
	base : basePath+ 'back/plugins/layui/modules/'
});
var isOpen = true;
var open_close = false;
var pagesize=20;//每页显示条数
var currentpage=1;//当前页
var search_count_url = basePath+"fenceDevice/getAllGatewayCount.do ";//查询数量
var search_list_url = basePath+"fenceDevice/getAllGatewayList.do";//查询数据集合
var deal_ids = "";
var dept_option="";
init();
//searchDepartment();
searchCount();
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
			$(".layui-form").animate({"height":"150px"},400);
			open_close = true;
		}
		setTimeout(function(){
			isOpen = true;},400);
	}
});
//页面初始化
function init(){
	layui.use(['laypage','layer','icheck','form', 'laydate'],function() {
		var $ = layui.jquery, 
		laypage = layui.laypage,
		form = layui.form(),
		laydate = layui.laydate,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		$('input').iCheck({
			checkboxClass : 'icheckbox_flat-green'
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
		$('#selected-all2').on('ifChanged', function(event) {
			var $input = $('.site-table tbody tr td ').find('input[name=ch1] ');
			$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
		});
	});
}
//分页查询总页数
function searchCount(){
	layui.use(['laypage','layer'],function() {
		var $ = layui.jquery, 
		laypage = layui.laypage,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		//查询总数
		$.ajax({
			url:search_count_url,
			data:{
				"pagesize":pagesize
			},
			type:"post",
			async:false,
			success:function(json){
				var pageCount = json.pageCount;//总页数
				// page
				laypage({
					cont : 'page',
					pages : pageCount,
					groups : 5,
					jump : function(obj, first) {
						currentpage = obj.curr;
						if (!first) {
							searchList();
						}
					}
				});
				searchList();
			},
			error:function(){
			}
			
		});
	});
}
//查询数据集合
function searchList(){
	$("#selected-all").removeAttr("checked");
	$.ajax({
		url:search_list_url,
		data:{
			"pagesize":pagesize,
			"currentpage":currentpage,
		},
		type:"post",
		async:false,
		dataType:"json",
		success:function(json){
			var html = "";
			for(var i in json){
				var obj = json[i];//获取信息对象
				html += "<tr>";
				html += "<td>" + obj.name + "</td>";
				html += "<td>" + obj.deviceSerial + "</td>";
				html += "<td>" + obj.createDate + "</td>";
				html += "<td>" + obj.oldId + "</td>";
				html += "<td>";
				html += "<a href='javascript:;' onclick='callComplaint2("+obj.id+")' data-id='1' data-opt='del' class='layui-btn layui-btn-danger layui-btn-mini'>回访</a>";
				html += "</td>";
				html += "</tr>";
			}
			$("#content_list").html(html);
			init();
		}
	});
}



//添加
function addGateway(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '处理投诉',
			content: basePath + 'popups/fence/addGateway.jsp',
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
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				var name =  body.find("#name").val();
				var deviceSerial =  body.find("#deviceSerial").val();
				$.ajax({
					url: basePath + "fenceDevice/savdGatewayInfo.do",
					data: {
						"name": name,
						"deviceSerial": deviceSerial,
						"createTime": new Date().getTime()
					},
					type: "post",
					dataType: "json",
					success: function(data) {
						if (data.rtn > 0) {
							layer.alert("添加成功", function() {
								layer.closeAll();
							});
						}
					}
				})
			},
			end:function(){
				searchList();
			}
		});
	});
}


function delUser(id){
	layui.use(['laypage','layer'],function() {
		var $ = layui.jquery, 
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		
		layer.confirm("确认要删除吗",{
			title:"提示",
			icon:3
		},function(index,layero){
			$.ajax({
				url:del_url,
				type:"post",
				data:{"id":id},
				success:function(){
					layer.alert("删除成功",{icon:6},function(){
						layer.closeAll();
						searchList();
					});
				},
				error:function(){
					layer.alert("删除失败",{icon:5});
				},
			}); 
		});
	});
		 
 };


//模糊查询
function searchInfo(){
	searchCount();
}

//添加信息
function addMESS(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加表单',
			content: basePath+'popups/pension/addPensionPeople.jsp',
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
				body.find("#department").html(dept_option);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				
				var url_img = body.find("#jing_dian_img .big").find("img").attr("src");
				if(body.find('#peopleName').val() == ""){
					layer.alert("姓名不能为空！");
					return false;
				}
				if(body.find("#cellphone").val() == ""){
					layer.alert("手机号不能为空！");
					return false;
				}
				if(body.find("#duty").val() == ""){
					layer.alert("职务描述不能为空！");
					return false;
				}
				if(body.find("#age").val() == ""){
					layer.alert("年龄不能为空！");
					return false;
				}
				$.ajax({
						url:add_url,
						type:"post",
						data:{
							"age":body.find("#age").val(),
							"urlImg":url_img,
					       	"peopleName":body.find("#peopleName").val(),
				        	"sex":body.find("input[name=sex]:checked").val(),
				        	"idCard":body.find("#idCard").val(),
				        	"department":body.find("#department").val(),
				        	"cellphone":body.find("#cellphone").val(),
				        	"duty":body.find("#duty").val(),
				        	"random":Math.random()
						},
						async:true,
						success:function(json){
							
							if(json.state == "error"){
								layer.alert("操作异常！",{icon:5});
				        		
				        	}else{
				        		layer.alert("添加成功",{icon:6},function(){
				        			layer.closeAll();
				        		});
				        	}
							
						},
						error:function(){
							layer.alert("添加失败",{icon:5});
						}
	
				});
				//获取编辑页面的值，并用ajax传值到后台，返回成功关闭窗口layer.closeAll();
					
			},
			
			end:function(){
				//关闭窗口，重新刷新本页面数据
				searchCount();
			}
			
		});
		
	});
}
function showUpd(id){
	var list =null;
	$.ajax({
		url:find_byId,
		type:"post",
		data:{
        	"id":id,
        	"random":Math.random()
		},
		async:true,
		success:function(json){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '编辑表单',
			content: basePath+'popups/pension/addPensionPeople.jsp',
			btn: ['保存', '取消'],
			area: ['800px', '800px'],
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
				var list = json.data;
				var body = layer.getChildFrame('body', index);
				body.find("#peopleName").val(list[0].peopleName);
				console.log(list[0].sex)
				if(list[0].sex == 2){ 
					body.find('#sex').prop("checked","checked");
				}else{
					body.find('#sex1').prop("checked","checked");
				}
				body.find("#inp").click();
				body.find("#department").html(dept_option);
				var option1 = body.find('#department').find("option");
				for(var i = 0;i<option1.length;i++){
					if(list[0].peopleDeptId == option1.eq(i).val()){
						body.find('#department').find("option")[i].selected = true;
					}
				}
				body.find("#age").val(list[0].age);
				body.find("#idCard").val(list[0].idCard);
				body.find("#cellphone").val(list[0].cellphone);
				body.find("#jing_dian_img").html("<img src ='"+list[0].imgUrl+"'>")
				body.find("#duty").val(list[0].dutyMs);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				
				var url_img = body.find("#jing_dian_img .big").find("img").attr("src");
				if(body.find('#peopleName').val() == ""){
					layer.alert("姓名不能为空！");
					return false;
				}
				if(body.find("#cellphone").val() == ""){
					layer.alert("手机号不能为空！");
					return false;
				}
				if(body.find("#duty").val() == ""){
					layer.alert("职务描述不能为空！");
					return false;
				}
				if(body.find("#age").val() == ""){
					layer.alert("年龄不能为空！");
					return false;
				}
				$.ajax({
					url:add_url,
					type:"post",
					data:{
						"id":id,
						"age":body.find("#age").val(),
						"urlImg":url_img,
				       	"peopleName":body.find("#peopleName").val(),
			        	"sex":body.find("input[name=sex]:checked").val(),
			        	"idCard":body.find("#idCard").val(),
			        	"department":body.find("#department").val(),
			        	"cellphone":body.find("#cellphone").val(),
			        	"duty":body.find("#duty").val(),
			        	"random":Math.random()
					},
					async:true,
					success:function(json){
						
						if(json.state == "error"){
							layer.alert("操作异常！",{icon:5});
			        		
			        	}else{
			        		layer.alert("修改成功",{icon:6},function(){
			        			layer.closeAll();
			        		});
			        	}
						
					},
					error:function(){
						layer.alert("添加失败",{icon:5});
					}

			});
				
			}
		});
	});
		},
		error:function(){
			layer.alert("修改失败",{icon:5});
		}
		});
}


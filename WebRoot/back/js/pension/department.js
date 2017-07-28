layui.config({
	base : basePath+ 'back/plugins/layui/modules/'
});
var isOpen = true;
var open_close = false;
var pagesize=20;//每页显示条数
var currentpage=1;//当前页
var search_count_url = basePath+"department/GetCountID.do ";//查询数量
var search_list_url = basePath+"department/getList.do";//查询数据集合
var add_url = basePath+"department/addDepartment.do";//添加数据
var del_url = basePath +"department/deleteDepartmentById.do";//删除数据
var update_UserV2 =basePath +"department/update.do";
var find_byId =	basePath +"department/findById.do";
var find_dept_byid= basePath +"department/findByDept.do";
var deal_ids = "";
var dept_option="";
init();
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
	layui.use(['laypage','layer','icheck','form'],function() {
		var $ = layui.jquery, 
		laypage = layui.laypage,
		form = layui.form(),
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
				"name":$.trim($("#name").val()),
				"cellphone":$.trim($("#cellphone").val()),
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
			"name":$.trim($("#name").val()),
			"cellphone":$.trim($("#cellphone").val()),
			"pagesize":pagesize,
			"currentpage":currentpage,
		},
		type:"post",
		async:false,
		dataType:"json",
		success:function(json){
			var list = json.userList;//总数
			var html = "";
			for(var i in list){
				var obj = list[i];//获取信息对象c
				console.log(obj);
				html += "<tr>";
				html	+="<td>"+obj.name+"</td>";
			 	html	+="<td>"+obj.address+"</td>";
			 	html	+="<td>"+obj.linkName+"</td>";
			 	html	+="<td>"+obj.linkPhone+"</td>";
			 	html	+="<td>"+myDate.longToDateYMD(obj.creationTime)+"</td>";
			 	html	+="<td>"+obj.email+"</td>";
				html	+="<td>已生成</td>";
				html	+="<td>";
				html	+="<a href='javascript:;' class='layui-btn layui-btn-mini' onclick=showUpd('"+obj.id+"')>编辑</a>&nbsp;&nbsp;";
				html	+="<a href='javascript:;' class='layui-btn layui-btn-danger layui-btn-mini' onclick=del('"+obj.id+"')>删除</a>&nbsp;&nbsp;";
				html	+="</td>";
				html	+="</td>";
				html	+="</tr>";
			}
			$("#content_list").html(html);
			init();
		}
		
	});
}

function del(id){
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
function addMESS(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加表单',
			content: basePath+'popups/pension/addDepartment.jsp',
			btn: ['保存', '取消'],
			area: ['700px', '500px'],
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
				/*var url_img = body.find("#jing_dian_img .big").find("img").attr("src");*/
				var value  = body.find('input[name="isAccount"]:checked').val();
				if(value == null){
					layer.alert("部门类型不能为空！");
					return false;
				}
				if(body.find('#name').val() == ""){
					layer.alert("名称不能为空！");
					return false;
				}
				if(body.find("#address").val() == ""){
					layer.alert("地址不能为空！");
					return false;
				}
				if(body.find("#linkName").val() == ""){
					layer.alert("联系人不能为空！");
					return false;
				}
				if(body.find("#creationTime").val() == ""){
					layer.alert("创建时间不能为空！");
					return false;
				}
				if(body.find("#linkPhone").val() == ""){
					layer.alert("联系人电话不能为空！");
					return false;
				}
			/*	var value  = body.find('input[name="isAccount"]:checked').val();*/
				console.log(add_url)
				$.ajax({
						type:"post",
						url:basePath + "department/addDepartment.do",
						data:{
							"state":value,
					       	"name":body.find("#name").val(),
					    	"address":body.find("#address").val(),
					    	"linkName":body.find("#linkName").val(),
					    	"linkPhone":body.find("#linkPhone").val(),
					    	"creationTime":new Date(body.find("#creationTime").val()).getTime(),
					    	"email":body.find("#email").val(),
				        	"random":Math.random()
						},
						async:true,
						success:function(json){
							console.log(json)
							if(json.state == "ssss"){
								layer.alert("手机号已存在！",{icon:5});
				        		
				        	}
							if(json.state == "exist"){
								layer.alert("手机号已存在！",{icon:5});
				        		
				        	}
							if(json.state == "error"){
								layer.alert("操作异常！",{icon:5});
				        		
				        	}else{
				        		layer.alert("添加成功",{icon:6},function(){
				        			layer.closeAll();
				        		});
				        	}
							searchCount();
							
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
			content: basePath+'popups/pension/addDepartment.jsp',
			btn: ['保存', '取消'],
			area: ['800px', '80%'],
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
				var list = json.list;
				console.log(list);
				var body = layer.getChildFrame('body', index);
				body.find("#hidden").css("display","none");
				body.find("#name").val(list[0].name);
				body.find("#address").val(list[0].address);
				body.find("#linkName").val(list[0].linkName);
				body.find("#linkPhone").val(list[0].linkPhone);
				body.find("#creationTime").val(myDate.longToDateYMD(list[0].creationTime));
				body.find("#email").val(list[0].email);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				/*var url_img = body.find("#jing_dian_img .big").find("img").attr("src");*/
				if(body.find('#name').val() == ""){
					layer.alert("名称不能为空！");
					return false;
				}
				if(body.find("#address").val() == ""){
					layer.alert("地址不能为空！");
					return false;
				}
				if(body.find("#linkName").val() == ""){
					layer.alert("联系人不能为空！");
					return false;
				}
				if(body.find("#creationTime").val() == ""){
					layer.alert("创建时间不能为空！");
					return false;
				}
				if(body.find("#linkPhone").val() == ""){
					layer.alert("联系人电话不能为空！");
					return false;
				}
				$.ajax({
					url:add_url,
					type:"post",
					data:{
						"id":id,
				       	"name":body.find("#name").val(),
				    	"address":body.find("#address").val(),
				    	"linkName":body.find("#linkName").val(),
				    	"linkPhone":body.find("#linkPhone").val(),
				    	"creationTime":new Date(body.find("#creationTime").val()).getTime(),
				    	"email":body.find("#email").val(),
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
						
						searchCount();
						
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


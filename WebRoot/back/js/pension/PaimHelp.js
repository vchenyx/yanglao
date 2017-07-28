layui.config({
	base: basePath + 'back/plugins/layui/modules/'
});
var pagesize = 20;
var currentpage;
var isOpen = true;
var open_close = false;
var search_count_url = basePath +"paim/getAllPaimCount.do";//查询分页总数
var del_url = basePath +"paim/deleteUser.do";//删除数据
var find_by_id   = basePath+"paim/findById.do"
var add_url = basePath+"paim/addPaim.do";//添加数据
init();
$(function() {
	searchInfo();
});
function searchList() {
	var name = $("#name").val();
	var age = $("#age").val();
	var sex = $("input[name='sex']:checked").val();
	var managerClass = $("input[name='managerClass']:checked").val();
	var phone = $("#phone").val();
	var communitys = $("#communitys").val();
	var maritalStatus = $("#maritalStatus").val();
	var income = $("#income").val();
	$.ajax({
		url: basePath + "paim/getAllPaim.do",
		type: "get",
		data: {
			"name": name,
			"age": age,
			"sex": sex,
			"managerClass": managerClass,
			"phone": phone,
			"communitys": communitys,
			"maritalStatus": maritalStatus,
			"income": income,
			"pagesize":pagesize,
			"currentpage":currentpage,
			"random": Math.random()
		},
		dataType: "json",
		success: function(data) {
			
			var str = "";
			for (var i = 0; i < data.length; i++) {
				var obj = data[i];
				str += '<tr>';
				str += '<td>' + (i + 1) + '</td>';
				str += '<td>' + obj.name + '</td>';
				str += '<td>' + (obj.sex == 0 ? '男' : '女')+ '</td>';
				str += '<td>' + obj.idCrad + '</td>';
				str += '<td>' + myDate.longToDateYMD(obj.stateBirthday) + '</td>';
				str += '<td>' + obj.age + '</td>';
				str += '<td>' + obj.phone + '</td>';
				str += '<td>' + (obj.managerClass ==0 ? '托底' : '扶助') + '</td>';
				str += '<td>' + obj.communitys + '</td>';
				str += '<td>' + obj.address + '</td>';
				str += '<td>' + obj.urgencyName + '</td>';
				str += '<td>' + obj.urgencyPhone + '</td>';
				str += '<td>' + obj.maritalStatus + '</td>';
				str += '<td>' + obj.income + '</td>';
				str += '<td>';
				str += "<a href='javascript:;' class='layui-btn layui-btn-mini' onclick=showServiceRecords('"+obj.name+"')>查看服务信息</a>&nbsp;&nbsp;";
				str += "<a href='javascript:;' class='layui-btn layui-btn-mini' onclick=updatePaim('"+obj.id+"')>编辑</a>&nbsp;&nbsp;";
				str += "<a href='javascript:;' class='layui-btn layui-btn-danger layui-btn-mini' onclick=delPaim('"+obj.id+"')>删除</a>&nbsp;&nbsp;";
				str += 	'</td>';
				str += '</tr>';
			}
			$("#pahe_tbd").html(str);
		}
	})
}
//初始化（表格、单选框初始化）
function init(){
	layui.use(['icheck', 'laypage','layer','form','upload'], function() {
		$ = layui.jquery,
		laypage = layui.laypage,
		form = layui.form,
		upload = layui.upload(),
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		$('input').iCheck({
			checkboxClass: 'icheckbox_flat-green'
		});
		
		layui.upload({
			url: basePath + 'poi/importUnderpinAssist.do',
			success: function(res){
			  console.log(res);
			  if (res.rtn == 1) {
				  layer.alert("上传成功", function() {
					  layer.closeAll();
				  });
				  initPage();
			  } else {
				  layer.alert("上传失败，请检查文本格式！");
			  }
			}
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

function searchInfo(){
	initPage();
}

function initPage() {
	layui.use(['laypage','layer','form'], function() {
		$ = layui.jquery,
		laypage = layui.laypage,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		//page
		
		var name = $("#name").val();
		var age = $("#age").val();
		var sex = $("input[name='sex']:checked").val();
		var managerClass = $("input[name='managerClass']:checked").val();
		var phone = $("#phone").val();
		var communitys = $("#communitys").val();
		var maritalStatus = $("#maritalStatus").val();
		var income = $("#income").val();
		$.ajax({
			url:search_count_url,
			data:{
				"name": name,
				"age": age,
				"sex": sex,
				"managerClass": managerClass,
				"phone": phone,
				"communitys": communitys,
				"maritalStatus": maritalStatus,
				"income": income,
				"pagesize":pagesize,
				"random": Math.random()
			},
			type:"post",
			async:false,
			success:function(json){
				var pageCount = json.pageCount;//总页数
				var totalCount = json.totalCount;//总页数
				$(".pageCount").html(pageCount);
				$(".totalCount").html(totalCount);
				
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
}

//添加
function addPaim(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加服务商',
			content: basePath + 'popups/pension/addPaim.jsp',
			btn: ['保存', '取消'],
			area: ['900px', '80%'],
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
				var id = body.find("#id").val();
				var name = body.find("#name").val();
				var sex = body.find("input[name='sex']:checked").val();
				var idCrad = body.find("#idCrad").val();
				var stateBirthday = body.find("#stateBirthday").val();
				var age = body.find("#age").val();
				var phone = body.find("#phone").val();
				var managerClass = body.find("input[name='managerClass']:checked").val();
				var communitys = body.find("#communitys").val();
				var address = body.find("#address").val();
				var urgencyName = body.find("#urgencyName").val();
				var urgencyPhone = body.find("#urgencyPhone").val();
				var maritalStatus = body.find("#maritalStatus").val();
				var income = body.find("#income").val();
				$.ajax({
					url: add_url,
					data: {
						"id": id,
						"name": name,
						"sex": sex,
						"idCrad": idCrad,
						"stateBirthday": new Date(stateBirthday).getTime(),
						"age": age,
						"phone": phone,
						"managerClass": managerClass,
						"communitys": communitys,
						"address": address,
						"urgencyName": urgencyName,
						"urgencyPhone": urgencyPhone,
						"maritalStatus": maritalStatus,
						"income": income,
					},
					type: "post",
					dataType: "json",
					success: function(data) {
						if (data.rtn == 1) {
							layer.alert("添加成功", function() {
								layer.closeAll();
								initPage();
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

function importPaim() {
	$("#importPaim").css("display","inline-block");
}


function updatePaim(id){
	var list =null;
	$.ajax({
		url:find_by_id,
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
			content: basePath+'popups/pension/addPaim.jsp',
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
				body.find("#name").val(list.name);
				if(list.sex ==0){
					body.find('#sex').prop("checked","checked");
				}else {
					body.find('#sex1').prop("checked","checked");
				}
				body.find("#idCrad").val(list.idCrad);
				body.find("#stateBirthday").val(list.stateBirthday);
				body.find("#age").val(list.age);
				body.find("#phone").val(list.phone);
				body.find("#managerClass").val(list.managerClass);
				body.find("#communitys").val(list.communitys);
				body.find("#address").val(list.address);
				body.find("#urgencyName").val(list.urgencyName);
				body.find("#urgencyPhone").val(list.urgencyPhone);
				body.find("#maritalStatus").val(list.maritalStatus);
				body.find("#income").val(list.income);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				if(body.find('#name').val() == ""){
					layer.alert("名称不能为空！");
					return false;
				}
				if(body.find("#address").val() == ""){
					layer.alert("地址不能为空！");
					return false;
				}
				if(body.find("#urgencyName").val() == ""){
					layer.alert("紧急联系人不能为空！");
					return false;
				}
				if(body.find("#urgencyPhone").val() == ""){
					layer.alert("紧急联系人电话不能为空！");
					return false;
				}
				if(body.find("#managerClass").val() == ""){
					layer.alert("老人类别！");
					return false;
				}
				$.ajax({
					url:add_url,
					type:"post",
					data:{
						"id":id,
				       	"name":body.find("#name").val(),
				       	"sex":body.find("#sex").val(),
				       	"idCrad":body.find("#idCrad").val(),
				       	"stateBirthday":body.find("#stateBirthday").val(),
				       	"creationTime":new Date(body.find("#creationTime").val()).getTime(),
				       	"age":body.find("#age").val(),
				       	"phone":body.find("#phone").val(),
				       	"managerClass":body.find("#managerClass").val(),
				       	"communitys":body.find("#communitys").val(),
				       	"address":body.find("#address").val(),
				    	"urgencyName":body.find("#urgencyName").val(),
				    	"urgencyPhone":body.find("#urgencyPhone").val(),
				    	"maritalStatus":body.find("#maritalStatus").val(),
				    	"income":body.find("#income").val(),
			        	"random":Math.random()
					},
					async:true,
					success:function(json){
						
						if(json.state == "error"){
							layer.alert("操作异常！",{icon:5});
			        		
			        	}else{
			        		layer.alert("修改成功",{icon:6},function(){
			        			layer.closeAll();
			        			initPage();
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




function delPaim(id){
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
						initPage();
					});
				},
				error:function(){
					layer.alert("删除失败",{icon:5});
				},    
			}); 
		});
	});
		 
 };

 
 function showServiceRecords(name) {
	 $.ajax({
			url: basePath + "paim/shouServiceRecords.do",
			data: {
				"name": name
			},
			type: 'post',
			dataType: 'json',
			success: function(data) {
				if (data.length > 0) {
					layui.use(['layer'], function() {
						var $ = layui.jquery,
						layer = parent.layer === undefined ? layui.layer : parent.layer;
						layer.open({
							type: 2,
							title: '服务记录',
							content: basePath + 'popups/pension/showServiceRecords.jsp',
							btn: ['保存', '取消'],
							area: ['900px', '80%'],
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
								body.find("#totalCount").html(data.length);
								var html = "";
								for (var i = 0; i < data.length; i++) {
									var obj = data[i];
									html += "<tr>";
									html	+="<td>"+obj.name+"</td>";
								 	html	+="<td>"+(obj.sex == 0 ? "男" : "女")+"</td>";
								 	html	+="<td>"+obj.age+"</td>";
								 	html	+="<td>"+obj.phone+"</td>";
								 	html	+="<td>"+obj.community+"</td>";
							 		html	+="<td>" + obj.oldType + "</td>";
								 	html	+="<td>"+obj.procuderName+"</td>";
								 	html	+="<td>"+obj.serviceAddress+"</td>";
								 	html	+="<td>"+(obj.serviceDate == null ? "--" : myDate.longToDateYMD(obj.serviceDate)) + "</td>";
								 	html	+="<td>"+(obj.startEndTime == null ? "--" : obj.startEndTime) + "</td>";
								 	html	+="<td>"+(obj.serviceContent == null ? "--" : obj.serviceContent) + "</td>";
									/*html	+="<td>"+obj.singlePrice+"</td>";
									html	+="<td>"+obj.serviceUnit+"</td>";*/
									html	+="<td>"+obj.countPrice+"</td>";
									html	+="<td>"+(obj.oldComment == null ? "--" : obj.oldComment)+"</td>";
									html	+="<td>"+(obj.serviceType == null ? "--" : obj.serviceType)+"</td>";
									html	+="</tr>";
								}
								body.find("#prov_tbd").html(html);
							},
							yes:function(index,layero){
								var body = layer.getChildFrame('body', index);
								
							},
							end:function(){
								
							}
						});
					});
				} else {
					layer.alert("暂无服务信息！");
				}
			}
		});
 }
 
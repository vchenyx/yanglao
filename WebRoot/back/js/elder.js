layui.config({
	base : basePath+ 'plugins/layui/modules/'
});
var isOpen = true;
var open_close = false;
var pagesize=2;//每页显示条数
var currentpage=1;//当前页
var search_count_url = basePath+"elderly/getElderCount.do";//查询数量
var search_List_url = basePath+"elderly/getElderList.do";//查询数据集合
var add_url = basePath+"elderly/elderlyInfoSet.do";//添加数据
var update_url = basePath+"elderly/elderlyInfoModify.do";//修改数据
var del_url = basePath+"elderly/elderlyInfoDel.do";//删除数据

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
			$(".layui-form").animate({"height":"60px"},400);
			open_close = true;
		}
		setTimeout(function(){
			isOpen = true;},400);
	}
});

//页面初始化
function init(){
	layui.use(['laypage','layer','icheck'],function() {
		var $ = layui.jquery, 
		laypage = layui.laypage,
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
		$('#selected-all').on('ifChanged', function(event) {
			var $input = $('.site-table tbody tr td').find('input');
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
			type:"post",
			data:{
				"realname":$.trim($("#username").val()),
				"pagesize":pagesize,
				"random":Math.random()
			},
			async:false,
			success:function(json){
				var pageCount = json.pageCount;//总数
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
	$.ajax({
		url:search_List_url,
		type:"post",
		data:{
			"realname":$.trim($("#username").val()),
			"pagesize":pagesize,
			"currentpage":currentpage,
			"random":Math.random()
		},
		async:false,
		success:function(json){
			var list = json.list;//总数
			var html = "";
			for(var i in list){
				var obj = list[i];//获取信息对象
				html += "<tr>"
					/*+"<td><input type='checkbox'></td>"*/
					+"<td>"+obj.realName+"</td>"
					+"<td>"+(obj.sex==1?"男":"女")+"</td>"
					+"<td>"+obj.age+"</td>"
					+"<td>"+obj.linkName+"</td>"
					+"<td>"+obj.telephone+"</td>"
					+"<td>"+obj.linkName2+"</td>"
					+"<td>"+obj.telephone2+"</td>"
					+"<td>"+new Date(obj.createDate.time).Format("yyyy-MM-dd hh:mm:ss")+"</td>"
					+"<td>"
					+"<a href='javascript:;' class='layui-btn layui-btn-mini' onclick=eidtMESS('"+obj.id+"','"+obj.realName +"','"+obj.sex +"','"+obj.age +"','"+obj.linkName +"','"+obj.telephone +"','"+obj.linkName2 +"','"+obj.telephone2 +"','"+obj.registElderlyNo +"','"+obj.registGuardianNo +"','"+obj.registProviderNo +"','"+obj.registMedicalNo +"','"+obj.registCommunityNo +"')>编辑</a>"
					+"&nbsp;&nbsp;"
					+"<a href='javascript:;' data-id='1' data-opt='del' class='layui-btn layui-btn-danger layui-btn-mini' onclick=javascript:delElderInfo('"+ obj.id +"')>删除</a>"
					+"</td>"
					+"</tr>"
			}
			$("#content_list").html(html);
		},
		error:function(){
		}
		
	});
}

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
			content: basePath+'elder/elderEdit.jsp',
			btn: ['保存', '取消'],
			area: ['600px', '400px'],
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
				var realname = body.find('#realName').val();
				var age = body.find('#age').val();
				var linkname = body.find('#linkName').val();
				var sex;
				if(body.find('#man').prop("checked")){
					sex = 1;
				}
				else{
					sex = 2;
				}
				var telephone = body.find('#telephone').val();
				var linkname2 = body.find('linkName2').val();
				var telephone2 = body.find('telephone2').val();
				
				if(realnameTest(realname) && ageTest(age)){
					$.ajax({
							url:add_url,
							type:"post",
							data:{
								"realName":realname,
								"age":age,
								"sex":sex,
								"linkName":linkname,
								"telephone":telephone,
								"linkName2":linkname2,
								"telephone2":telephone2,
								"random":Math.random()
							},
							async:true,
							success:function(){
								layer.alert("添加成功!",{icon:6},function(){
									layer.closeAll();
								});
							},
							error:function(){
								layer.alert("添加失败",{icon:5});
							}
					});
				//获取编辑页面的值，并用ajax传值到后台，返回成功关闭窗口layer.closeAll();
				}
			},
			end:function(){
				//关闭窗口，重新刷新本页面数据
				searchList();
			}
		});
	});
}

//编辑信息
function eidtMESS(v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '编辑表单',
			content: basePath+'elder/elderEdit.jsp',
			btn: ['保存', '取消'],
			area: ['600px', '400px'],
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
				var body = layer.getChildFrame('body', index);
				body.find("#id").val(v1);
				body.find("#realName").val(v2);
				if(v3 =  '1'){
					body.find("#man").prop("checked","checked");
				}
				else{
					body.find("#women").prop("checked","checked");
				}
				body.find("#age").val(v4);
				body.find("#linkName").val(v5);
				body.find("#telephone").val(v6);
				body.find("#linkName2").val(v7);
				body.find("#telephone2").val(v8);
				body.find("#registCommunityNo").val(v13);
				body.find("#registMedicalNo").val(v12);
				body.find("#registProviderNo").val(v11);
				body.find("#registGuardianNo").val(v10);
				body.find("#registElderlyNo").val(v9);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				//获取编辑页面的值，并用ajax传值到后台，返回成功关闭窗口layer.closeAll();
				var id = body.find('#id').val();
				var realname = body.find('#realName').val();
				var age = body.find('#age').val();
				var linkname = body.find('#linkName').val();
				var sex;
				if(body.find('#man').prop("checked")){
					sex = 1;
				}
				else{
					sex = 2;
				}
				var telephone = body.find('#telephone').val();
				var linkname2 = body.find('#linkName2').val();
				var telephone2 = body.find('#telephone2').val();
				
				if(realnameTest(realname) && ageTest(age)){
					
					$.ajax({
							url:update_url,
							type:"post",
							data:{
								"id":id,
								"realName":$.trim(realname),
								"sex":sex,
								"age":$.trim(age),
								"linkName":$.trim(linkname),
								"telephone":$.trim(telephone),
								"linkName2":$.trim(linkname2),
								"telephone2":$.trim(telephone2),
								"random":Math.random()
							},
							async:true,
							success:function(){
								layer.alert("编辑成功!",{icon:6},function(){
									layer.closeAll();
								});
							},
							error:function(){
								layer.alert("编辑失败!",{icon:5});
							}
					});
				}
				
			},
			end:function(){
				//关闭窗口，重新刷新本页面数据
				searchList();
			}
		});
	});
}
function delElderInfo(id){
	layui.use(['laypage','layer'],function() {
		var $ = layui.jquery, 
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		
		layer.confirm("确认删除？",{
			btn:['确认','取消'],
			btn2:function(index,layero){
				
			}
		},function(index,layero){
			$.ajax({
				url:del_url,
				type:"post",
				data:{"id":id},
				success:function(){
					layer.alert("删除成功!",{icon:6});
					searchList();
				},
				error:function(){
					layer.alert("删除失败",{icon:5});
				},
			}); 
			//layer.closeAll();
		})
	})
		 
 };


function realnameTest(value){
	if($.trim(value) == ""){
		alert("真实姓名不能为空");
		return false;
	}
	return true;
}

function ageTest(value){
	if($.trim(value) == ""){
		alert("年龄不能为空");
		return false;
	}else if(isNaN(parseInt($.trim(value)))){
		alert("请输入数字");
		return false;
	}
	return true;
}

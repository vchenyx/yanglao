layui.config({
	base : basePath+ 'back/plugins/layui/modules/'
});
var info;
var isOpen = true;
var open_close = false;
var pagesize=10;//每页显示条数
var currentpage=1;//当前页
var search_count_url = basePath+"knowledgeBase/getCount.do";//查询数量
var search_list_url = basePath+"knowledgeBase/getList.do";  //查询数据集合
var add_url = basePath+"knowledgeBase/add.do";              //添加数据
var del_one_url = basePath+"knowledgeBase/delete.do";       //删除单条数据
var del_page_url = basePath+"knowledgeBase/deletePage.do";  //删除数据
var upd_url = basePath+"knowledgeBase/update.do";          //编辑数据
var deal_ids = "";
var projInfo = {};
searchCount();

//页面初始化
function init(){
	layui.use(['element','laypage','layer','icheck','form'],function() {
		var element = layui.element(),
			$ = layui.jquery, 
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
		$('#selected-all').on('ifChanged', function(event) {
			var $input = $('.site-table tbody tr td').find('input');
			$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
		});
	});
}

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

//添加触发
$('#add').on('click', function() {
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		//为父类型名称赋值
		$.ajax({
			url:basePath+"knowledgeBase/getListName.do",
			type:"post",
			async:true,
			success:function(json){
				//alert(JSON.stringify(json.listName[0].name))
				layer.open({
					type: 2,
					title: '添加表单',
					content: basePath+'popups/pension/addKnowledgeBase.jsp',
					btn: ['保存', '取消'],
					area: ['600px', '300px'],
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
						//为添加弹出层父类型名称赋值
						var html = '';
						for(var i in json.listName){
							var obj = json.listName[i];//获取信息对象
							html += '<option value="'+obj.name+'">'+obj.name+'</option>';
						}
						body.find("#typeId").html(html);
						body.find("#inp").click();
					},
					yes:function(index,layero){
						var body = layer.getChildFrame('body', index);
						if(check(body,layer)){
							$.ajax({
									url:add_url,
									type:"post",
									data:info,
									async:true,
									success:function(json){
										if(json.state == "success"){
											layer.alert("保存成功",{icon:6},function(){
												layer.closeAll();
												searchCount();
											});
										}else if(json.state == "exist"){
											layer.alert("信息已经存在，请勿重复录入",{icon:5},function(){
												layer.closeAll();
											});
										}else if(json.state == "no"){
											layer.alert("添加出现异常，请与管理员联系",{icon:5},function(){
												layer.closeAll();
											});
										}else if(json.state == "typeId"){
											layer.alert("请先添加父类型名称",{icon:5},function(){
												layer.closeAll();
											});
										}
									},
									error:function(){
										layer.alert("保存失败",{icon:5});
									}
							});
						//获取编辑页面的值，并用ajax传值到后台，返回成功关闭窗口layer.closeAll();
						}
					},
					end:function(){
						//关闭窗口，重新刷新本页面数据
						searchCount();
					}
				});
			},
			error:function(){
				layer.alert("保存失败",{icon:5});
			}
		});
	});
});

//模糊查询
function searchInfo(){
	searchCount();
}


//分页查询总页数
function searchCount(){
	layui.use(['element','laypage','layer'],function() {
		var element = layui.element(),
			$ = layui.jquery, 
			laypage = layui.laypage,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		
		//查询总数
		$.ajax({
			url:search_count_url,
			data:{
				"name":$.trim($("#name").val()),
				"pagesize":pagesize,
				"random":Math.random()
			},
			type:"post",
			dataType : "json",
			async:false,
			success:function(json){
				var pageCount = json.pageCount;//总页数
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
	layui.use(['layer'],function() {
		var $ = layui.jquery, 
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		$.ajax({
			url:search_list_url,
			data:{
				"name":$.trim($("#name").val()),
				"pagesize":pagesize,
				"currentpage":currentpage,
				"random":Math.random()
			},
			type:"post",
			dataType : "json",
			async:false,
			success:function(list){
				var html = "";
				for(var i in list){
					var obj = list[i];//获取信息对象
					html += "<tr>";
				    html += "<td><input type='checkbox' value='"+obj.id+"' name='ch1'/></td>";
					if(obj.pId=='0'){
						html+="<td>一级</td>";
					}else{
						html+="<td>二级</td>";
					}
					html+="<td>"+obj.name+"</td>";
					html+="<td>"+obj.creationTime+"</td>";
					html+="<td><a href=javascript:upd('"+obj.id+"','"+obj.pId+"','"+obj.name+"') data-id='1' data-opt='upd' class='layui-btn layui-btn-danger layui-btn-mini'>编辑</a>&nbsp;&nbsp;"
					+"<a href=javascript:delOneInfo('"+obj.id+"') data-id='1' data-opt='del'  class='layui-btn layui-btn-mini'>删除</a></td>";
					
					html+="</tr>";
				}
				$("#content_list").html(html);
				init();
			},
			error:function(){
				layer.alert("访问失败",{icon:5});
			}
			
		});
	});
}

function enterSystem(un){
	
	window.parent.location.href=basePath+"index/index.do";
}

//删除单条数据
function delOneInfo(dd){
	layui.use(['layer'],function() {
		var $ = layui.jquery, 
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		
		layer.confirm("确认要删除吗?",{
			icon: 3, 
			title:'提示'
		},function(index,layero){
			layer.close(index);
			$.ajax({
			    url: del_one_url,
			    data:{
			    	"id":dd,
			    	"random":Math.random()
			   	},
			    type: "post",
			    success: function (json) {
			    	$(dd).attr("disabled",false);
			    	if(json.state == "success"){
			    		layer.alert("删除成功",{icon:6},function(){
							layer.closeAll();
							searchCount();
						});
			    	}else{
			    		layer.alert("操作异常",{icon:5},function(){
			    			layer.closeAll();
			    		});
			    	}
			    },
			   	error:function(){
					layer.alert("删除失败",{icon:5},function(){
		    			layer.closeAll();
		    		});
				}
			});
		});
	});
}

//批量删除触发
$('#batch_del').on('click', function() {
	if(isSelect()){
		layui.use(['layer'],function() {
			var $ = layui.jquery, 
			layer = parent.layer === undefined ? layui.layer : parent.layer;
			
			layer.confirm("确认要删除吗?",{
				icon: 3, 
				title:'提示'
			},function(index,layero){
				layer.close(index);
				loadMsg();
				$.ajax({
					url:del_page_url,
					data:{
						"ids":deal_ids,
						"random":Math.random()
					},
					type:"post",
					success:function(){
						layer.alert("删除成功",{icon:6},function(){
							layer.closeAll();
							deal_ids = "";
							searchList();
						});
					},
					error:function(){
						layer.closeAll();
						layer.alert("删除失败",{icon:5});
					},
				});
			});
		});
	}
});

//修改信息
function upd(d,d1,d2) {
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '编辑表单',
			content: basePath+'popups/pension/updKnowledgeBase.jsp',
			btn: ['保存', '取消'],
			area: ['600px', '300px'],
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
				updInfo(body,d,d1,d2);
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				if(check(body,layer)){
					$.ajax({
							url:upd_url,
							type:"post",
							data:info,
							async:true,
							success:function(json){
								if(json.state == "success"){
									layer.alert("更新成功",{icon:6},function(){
										layer.closeAll();
										searchCount();
									});
								}else if(json.state == "no"){
									layer.alert("更新出现异常，请与管理员联系",{icon:5},function(){
										layer.closeAll();
									});
								}
							},
							error:function(){
								layer.alert("更新失败",{icon:5});
							}
					});
				//获取编辑页面的值，并用ajax传值到后台，返回成功关闭窗口layer.closeAll();
				}
			},
			end:function(){
				//关闭窗口，重新刷新本页面数据
				searchCount();
			}
		});
	});
}

//编辑赋值
function updInfo(body,d,d1,d2){
	body.find("#id").val(d);
	body.find("#name").val(d2);
}
//信息校验
function check(body, layer) {

	var id = body.find('#id').val();
	var pId = body.find('#pId').val();
	var typeId = "";
	if(pId != 0){
		typeId = body.find('#typeId').val();
	}
	var name = body.find('#name').val();
	
	if(name == "" ){
		layer.alert("类型名称不能为空",{icon:5});
		return false;
	}
	var str= stripscript(name);
	if(str==false){
		layer.alert("类型名称不能输入特殊字符",{icon:5});
		return false;
	}

	info = eval({
		"id":id,
		"pId":pId,
		"typeId":typeId,
		"name":name,
		"random":Math.random()
	});
	return true;
}


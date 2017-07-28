layui.config({
	base : basePath+ 'back/plugins/layui/modules/'
});
var isOpen = true;
var open_close = false;
var pagesize=20;//每页显示条数
var currentpage=1;//当前页
var search_count_url = basePath+"complaintManage/GetCountID.do ";//查询数量
var search_list_url = basePath+"complaintManage/getList.do";//查询数据集合
var find_by_id   = basePath+"complaintManage/findById.do";
var save_info    = basePath+"complaintManage/SaveComplaintManage.do";
var deal_ids = "";
var dept_option="";
$(function(){
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
});
//页面初始化
function init(){
	layui.use(['laypage','layer','icheck','form', 'laydate'],function() {
		var $ = layui.jquery, 
			laypage = layui.laypage,
			laydate = layui.laydate,
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
				"complaintPeople":$.trim($("#complaintPeople").val()),
				"servicePeople":$.trim($("#servicePeople").val()),
				"beginTime":$.trim($("#beginTime").val()),
				"time":$.trim($("#endTime").val()),
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
			"complaintPeople":$.trim($("#complaintPeople").val()),
			"servicePeople":$.trim($("#servicePeople").val()),
			"time":$.trim($("#beginTime").val()+"——"+$("#endTime").val()),
			"cellphone":$.trim($("#cellphone").val()),
			"pagesize":pagesize,
			"currentpage":currentpage,
		},
		type:"post",
		async:false,
		dataType:"json",
		success:function(json){
			var list = json.userList;//总数
			console.log(json)
			var html = "";
			for(var i in list){
				var obj = list[i];//获取信息对象
				html += "<tr>";
				html	+="<td>"+obj.complaintNumbers+"</td>";
			 	html	+="<td>"+obj.complaintPeople+"</td>";
			 	html	+="<td>"+obj.cellphone+"</td>";
			 	html	+="<td>"+obj.cellphoneTape+"</td>";
			 	if(obj.manageState == 0){
			 	html	+="<td>未处理</td>";
			 	}
			 	if(obj.manageState == 1){
				html	+="<td>处理中</td>";
				 }
			 	if(obj.manageState == 1){
				html	+="<td>已处理</td>";
				}
				html	+="<td>"+obj.complaintNumbers+"</td>";
				html	+="<td>"+obj.servicePeople+"</td>";
				if(obj.returnVisitState == 0){
				html	+="<td>未回访</td>";
				}
				if(obj.returnVisitState == 1){
				html	+="<td>已回访</td>";
				}
				html	+="<td>"+obj.complaintContent+"</td>";
				html	+="<td>"+obj.managePeople+"</td>";
				html	+="<td>"+obj.manageDate+"</td>";
				html	+="<td>"+obj.registePeople+"</td>";
				html	+="<td>"+obj.registerDate+"</td>";
				html	+="<td>"+obj.returnVisitDate+"</td>";
				html	+="<td><a href='javascript:;' onclick='callComplaint("+obj.id+")' data-id='1' data-opt='del' class='layui-btn layui-btn-danger layui-btn-mini'>处理</a></td>";
				html	+="</tr>";
			}
			$("#content_list").html(html);
			init();
		}
		
	});
}



//投诉处理
function callComplaint(id){
	$.ajax({
		url:find_by_id,
		data: {
			"id": id,
		},
		type: "post",
		dataType: "json",
		success: function(json) {
		var  list = json.list;
		console.log(list)
			
	layui.use(['layer'], function() {
		var $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '投诉处理',
			content: basePath + 'popups/pension/callComplaint.jsp',
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
				body.find("#acceptPerson").val(list.acceptPerson);
				body.find("#handleDept").val(list.handleDept);
				body.find("#acceptTime").val(list.acceptTime);
				body.find("#complaintNumbers").val(list.complaintNumbers);
				body.find("#cellphone").val(list.cellphone);
				body.find("#complaintPeople").val(list.complaintPeople);
				body.find("#fuWuS").val(list.fuWuS);
				body.find("#fuWuSCellphone").val(list.fuWuSCellphone);
				body.find("#complaintContent").html(list.complaintContent);
				body.find("#manageContent").html(list.manageContent);
				var option1 = body.find('#selectId').find("option");
				for(var i = 0;i<option1.length;i++){
					if(list.manageState == option1.eq(i).val()){
						body.find('#selectId').find("option")[i].selected = true;
					}
				}
				body.find("#inp").click()
			},
			yes:function(index,layero){
				
				var body = layer.getChildFrame('body', index);
				var acceptPerson =  body.find("#acceptPerson").val();
				var handleDept = body.find("#handleDept").val();
				var acceptTime = body.find("#acceptTime").val();
				var complaintNumbers = body.find("#complaintNumbers").val();
				var cellphone=  body.find("#cellphone").val();
				var complaintPeople = body.find("#complaintPeople").val();
				var fuWuS= body.find("#fuWuS").val();
				var fuWuSCellphone= body.find("#fuWuSCellphone").val();
				var complaintContent = body.find("#complaintContent").text();
				var manageContent = body.find("#manageContent").text();
				var selectId = body.find("#selectId").val();
				$.ajax({
					url:save_info,
					type:"post",
					data:{
						"id":id,
						"acceptPerson":acceptPerson,
						"handleDept":handleDept,
						"acceptTime":acceptTime,
						"complaintNumbers":complaintNumbers,
						"cellphone":cellphone,
						"complaintPeople":complaintPeople,
						"fuWuS":fuWuS,
						"fuWuSCellphone":fuWuSCellphone,
						"complaintContent":complaintContent,
						"manageContent":manageContent,
						"selectId":selectId
						},
					success:function(){
						layer.alert("处理成功",{icon:6},function(){
							layer.closeAll();
							searchList();
						});
					},
					error:function(){
						layer.alert("删除失败",{icon:5});
					},
				}); 
			
			},
			end:function(){
				//getAllDevicePension();
			}
		});
	});
		}
	});
}
//模糊查询
function searchInfo(){
	searchCount();
}

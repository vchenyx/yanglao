layui.config({
	base : basePath+ 'back/plugins/layui/modules/'
});
var isOpen = true;
var open_close = false;
var pagesize=20;//每页显示条数
var currentpage=1;//当前页
var search_list_url = basePath+"pensionDept/getPeopleDeptList.do";//查询数据集合
var add_url = basePath+"pensionDept/addPeopleDept.do";//添加数据
var del_url = basePath +"pensionDept/deletePeopleDept.do";//删除数据
var update_UserV2 =basePath +"pensionDept/updateUser.do";
var find_byId =	basePath +"pensionDept/findById.do";
var deal_ids = "";
var dept_option="";
init();
searchList();

//条件搜索查询框触发
$('#search').on('click', function() {
	if(isOpen){
		isOpen = false;
		if(open_close){
			$(".layui-form").css("overflow","hidden");
			$(".layui-form").animate({"height":"0"},300);
			open_close = false;
		}else{
			$(".layui-form").css("overflow","");
			$(".layui-form").animate({"height":"150px"},200);
			open_close = true;
		}
		setTimeout(function(){
			isOpen = true;},300);
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
//查询数据集合
function searchList(){
	$.ajax({
		url:search_list_url,
		data:{
			"name":$.trim($("#name").val()),
		},
		type:"post",
		async:false,
		dataType:"json",
		success:function(json){
			var list = json.list;//总数
			var html = "";
			for(var i in list){
				var obj = list[i];//获取信息对象
				html += "<tr>";
				html	+="<td>"+obj.name+"</td>";
				html	+="<td>"
				html	+="<a href='javascript:;' class='layui-btn layui-btn-danger layui-btn-mini' onclick=delUser('"+obj.id+"')>删除</a>&nbsp;&nbsp;";
				html	+="</td>"
				html	+="</tr>";
			}
			$("#content_list").html(html);
			init();
		}
		
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
	searchList();
}

//添加信息
function addMESS(){
	layui.use(['layer'], function() {
		var $ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		layer.open({
			type: 2,
			title: '添加表单',
			content: basePath+'popups/pension/addPeopleDept.jsp',
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
			},
			yes:function(index,layero){
				var body = layer.getChildFrame('body', index);
				if(body.find('#name').val() == ""){
					layer.alert("姓名不能为空！");
					return false;
				}
				var value  = body.find('input[name="userDuty"]:checked').val();
				var name =value+"——"+body.find("#name").val()
				$.ajax({
						url:add_url,
						type:"post",
						data:{
							"name":name,
				        	"random":Math.random()
						},
						async:true,
						success:function(json){
							
							if(json.state == "error"){
								layer.alert("操作异常！",{icon:5});
				        		
				        	}else{
				        		layer.alert("添加成功",{icon:6},function(){
				        			layer.closeAll();
				        			searchList();
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
				searchList();
			}
			
		});
		
	});
}

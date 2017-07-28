layui.config({
	base : basePath+ 'back/plugins/layui/modules/'
});
var isOpen = true;
var open_close = false;
var pagesize=20;//每页显示条数
var currentpage=1;//当前页
var search_count_url = basePath+"serviceRecord/GetUserCountID.do ";//查询数量
var search_list_url = basePath+"serviceRecord/getUserList.do";//查询数据集合
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
	layui.use(['laypage','layer','icheck','form','upload'],function() {
		var $ = layui.jquery, 
		laypage = layui.laypage,
		form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		$('input').iCheck({
			checkboxClass : 'icheckbox_flat-green'
		});
		
		layui.upload({
			url: basePath + 'poi/importServiceRecords.do',
			success: function(res){
			  console.log(res);
			  if (res.rtn == 1) {
				  layer.alert("上传成功", function() {
					  layer.closeAll();
				  });
				  searchList();
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
				"community":$.trim($("#community").val()),
				"serviceAddress":$.trim($("#serviceAddress").val()),
				"pagesize":pagesize
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
	});
}

function importServiceRecords() {
	$("#importServiceRecords").css("display","inline-block");
}

//查询数据集合
function searchList(){
	$("#selected-all").removeAttr("checked");
	$.ajax({
		url:search_list_url,
		data:{
			"name":$.trim($("#name").val()),
			"cellphone":$.trim($("#cellphone").val()),
			"community":$.trim($("#community").val()),
			"serviceAddress":$.trim($("#serviceAddress").val()),
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
			$("#content_list").html(html);
			init();
		}
		
	});
}



//模糊查询
function searchInfo(){
	searchCount();
}


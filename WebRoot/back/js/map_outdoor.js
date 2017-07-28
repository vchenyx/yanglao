layui.config({
	base: 'plugins/layui/modules/'
});
			
layui.use(['icheck','element', 'layer','form'], function() {
	var element = layui.element(),
		$ = layui.jquery,
		layer = layui.layer,
		form = layui.form();
	$('input').iCheck({
		checkboxClass: 'icheckbox_flat-green'
	});
	
	
	//动态获取地图div高度
	$(window).on('resize', function() {
		var header_height = $(".header_top").height();
		var all_width = $(window).width();
		var all_height = $(window).height();
		var info_width = $(".info").width();
		var content_width = $(".content").width();
		var height;
		if((all_height - header_height -50) < 500){
			height = 500;
		}
		else{
			height = all_height - header_height -50;
		}
		$(".content").css("height",height);
	}).resize();
	
	$('.site-table tbody tr').on('click', function(event) {
		$(this).addClass("active").siblings().removeClass("active")
	});
	
});

//弹出层
function face(dt){
	layui.use(['element', 'layer'], function() {
		var element = layui.element(),
			$ = layui.jquery,
			layer = layui.layer;
		layer.closeAll();
		var title = $(dt).find("option[value='"+$(dt).val()+"']").html();
		if($(dt).val() != 0){
			layer.open({
		        type: 1,
		        title: title,
		        area: ['500px','300px'],
		        shade: 0,
		        maxmin: true,
		        move:false,
		        offset: [
		          $(window).height()-300,'14px'
		        ],
		        content: '<fieldset class="layui-elem-field layui-field-title" style="margin:0;">'
							+'区域人员总数：<b style="color:red;">1</b>'
						+'</fieldset>'
						+'<div class="layui-field-box" style="padding:0;">'
							+'<table class="site-table table-hover" id="table_face">'
								+'<thead>'
									+'<tr>'
										+'<th>编号</th>'
										+'<th>姓名</th>'
										+'<th>职务</th>'
										+'<th>进入时间</th>'
									+'</tr>'
								+'</thead>'
								+'<tbody>'
									+'<tr>'
										+'<td>ZJ22138</td>'
										+'<td>何亮</td>'
										+'<td>技术员</td>'
										+'<td>2017.03.24 09:10</td>'
									+'</tr>'
								+'</tbody>'
							+'</table>'
						+'</div>',
		        success:function(){
		        	
		        }
		    });
		}
	});
}


















layui.config({
	base: basePath + 'static/plugins/layui/modules/'
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
		$(".content").css("height","700px");
	}).resize();
	
	$('.site-table tbody tr').on('click', function(event) {
		$(this).addClass("active").siblings().removeClass("active")
	});
	
	
	
	
	
	
	
	$(".one").click(function(){
		$(".info_top").find(".danyuan").html("当前位置："+$(this).find("a").html());
		var a = $(".info_top ul li a");
		for(var i = 0;i<a.length;i++){
			if(a.eq(i).html() == $(".info_top").find(".danyuan").html().split("：")[1]){
				a.eq(i).parent().css("display","none");
			}
		}
		$("#danyuan").css("display","block");
		$(".danyuan").css("display","block");
		$(".one").css("display","none");
		$("#container").find("img").attr("src", basePath + "static/images/floor1.png");
		$(".floor").css("display","block");
	})
	
	$(".floor").click(function(){
		$("#container").find("img").attr("src", basePath + "static/images/yihu1.png");
		var room = $(this).attr("class").split(" ")[1].split("f")[1];
		$(".info_top").find(".danyuan").html("当前位置："+room);
		$("#danyuan").css("display","none");
		$(".floor").css("display","none");
		$("#room").css("display","block");
		$(".alarm").css("display","block");
	})
	
	$(".info_top #danyuan li").click(function(){
		$(".info_top").find(".danyuan").html("当前位置："+$(this).find("a").html());
		$(this).css("display","none").siblings().css("display","block");
		
	})
	
	$(".info_top #room li").click(function(){
		$(".info_top").find(".danyuan").html("当前位置："+$(this).find("a").html());
		$(this).css("display","none").siblings().css("display","block");
		
	})
	
	$(".alarm").hover(function(e){
		var left = $(this).css("left");
		var top = $(this).css("top").split("px")[0] - 35;
		$(".alarmInfo").css("display","block");
		if($(this).attr("class").split(" ")[1] == "alarmShuiJin"){
			$(".alarmInfo").html("<p>水浸报警正常</p>");
		}
		else if($(this).attr("class").split(" ")[1] == "alarmYanWu"){
			$(".alarmInfo").html("<p>烟雾报警正常</p>");
		}
		else if($(this).attr("class").split(" ")[1] == "alarmMeiQi"){
			$(".alarmInfo").html("<p>煤气报警正常</p>");
		}
		else{
			$(".alarmInfo").html("<p>入侵报警正常</p>");
		}
		$(".alarmInfo").css("left",left);
		$(".alarmInfo").css("top",top + "px");
	},function(){
		$(".alarmInfo").css("display","none");
	})
	
	
});

function selected(){
	$("#container").find("img").attr("src", basePath + "static/images/xiaoqu.png");
	$(".one").css("display","block");
	$(".floor").css("display","none");
	$("#room").css("display","none");
	$("#danyuan").css("display","none");
	$(".danyuan").css("display","none");
	$(".alarm").css("display","none");
}






















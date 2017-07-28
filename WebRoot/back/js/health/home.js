$(function(){
	//头部鼠标滑过
	$(".header .nav ul").delegate('.out','mouseover',function(){
		$(this).find(".liBottom div").stop().animate({"width":"106px","height":"4px"},100);
	});
	$(".header .nav ul").delegate('.out','mouseout',function(){
		$(this).find(".liBottom div").stop().animate({"width":"0","height":"0"},100);
	});
	
	//鼠标点击头部
	$(".header .nav ul").delegate('li','click',function(){
		$(".liBottom").removeClass('active');
		$(this).find(".liBottom").addClass('active');
	});
	//鼠标滑过列表
	$("ul").on("mouseover",".one",function(){
		$(this).css("background","#67a331")
		$(this).find("p").css("color","#fff");
		$(this).find("span").css("color","#fff");
		$(this).find(".hide").css("display","block");
	})
	$("ul").on("mouseout",".one",function(){
		$(this).css("background","#F7F7F7");
		$(this).find("p").css("color","#777777");
		$(this).find("span").css("color","#777777");
		$(this).find(".hide").css("display","none");
	})
	$("ul").on("mouseover",".one>.hide>li",function(){
		$(this).css("background","#297029");
	})
	$("ul").on("mouseout",".one>.hide>li",function(){
		$(this).css("background","#67a331");
	})
	
	//滑过nav_bottom
	$(".nav_bottom>ul>li").hover(function(){
		if($(this).prop("class") == "click_li"){
			return;
		}
		else{
			$(this).css("background","#67a331");
			var num = $(this).find("img").attr("src").split("images")[1].split(".")[0];
			var src = basePath + "back/images" + num + "_0" + ".png";
			$(this).find("img").attr("src",src);
			$(this).find("a").css("color","#fff");
		}
	},function(){
		if($(this).prop("class") == "click_li"){
			return;
		}
		else{
			$(this).css("background","#fff");
			var num = $(this).find("img").attr("src").split("images")[1].split(".")[0].split("_")[2];
			var src = basePath + "back/images" + "/main_center_" + num + ".png";
			$(this).find("img").attr("src",src);
			$(this).find("a").css("color","#000");
		}
	})
	
	//点击nav_bottom
	$(".nav_bottom>ul>li").click(function(){
		var li_num = $(".nav_bottom>ul>li");
		for(var i = 0;i<li_num.length;i++){
			var num1 = $(".nav_bottom>ul>li").eq(i).find("img").attr("src").split("images")[1].split(".")[0].split("_")[2];
			var src1 = basePath + "back/images" + "/main_center_" + num1 + ".png";
			$(".nav_bottom>ul>li").eq(i).css("background","#fff");
			$(".nav_bottom>ul>li").eq(i).find("img").attr("src",src1);
			$(".nav_bottom>ul>li").eq(i).find("a").css("color","#000");
			$(".nav_bottom>ul>li").eq(i).removeClass("click_li");
		}
		
		var num = $(this).find("img").attr("src").split("images")[1].split(".")[0];
		var src = basePath + "back/images" + num + "_0" + ".png";
		console.log(num)
		$(this).css("background","#67a331");
		$(this).find("img").attr("src",src);
		$(this).find("a").css("color","#fff");
		$(this).addClass("click_li");
		
		var index = $(this).index()+1;
		if(index == 5){
			$(".container>.content>.content-right>.nav_node>div").css("border","1px solid #ccc");
			$(".container>.content>.content-right>.nav_node>.nav_node"+(index)+"").find(".nibp").css("border","1px solid #ccc");
			$(".container>.content>.content-right>.nav_node>.nav_node"+index+"").find(".temp").css("border","1px solid #f17215");
			$(".container>.content>.content-right>.nav_node>.nav_node"+(index + 1)+"").css("border","1px solid #f17215");
			$(".container>.content>.content-right>.nav_node>.nav_node"+(index + 2)+"").css("border","1px solid #f17215");
			$(this).css("background","#67a331");
			return;
		}
		else if(index == 6){
			$(".container>.content>.content-right>.nav_node>div").css("border","1px solid #ccc");
			$(".container>.content>.content-right>.nav_node>.nav_node"+(index - 1)+"").find(".temp").css("border","1px solid #ccc");
			$(".container>.content>.content-right>.nav_node>.nav_node"+index+"").css("border","1px solid #f17215");
			$(".container>.content>.content-right>.nav_node>.nav_node"+(index - 3)+"").css("border","1px solid #f17215");
			$(".container>.content>.content-right>.nav_node>.nav_node"+(index - 1)+"").find(".nibp").css("border","1px solid #f17215");
			$(this).css("background","#67a331");
			return;
		}
		$(".container>.content>.content-right>.nav_node>.nav_node5").find(".temp").css("border","1px solid #ccc");
		$(".container>.content>.content-right>.nav_node>.nav_node5").find(".nibp").css("border","1px solid #ccc");
		$(".container>.content>.content-right>.nav_node>div").css("border","1px solid #ccc");
		$(".container>.content>.content-right>.nav_node>.nav_node"+index+"").css("border","1px solid #f17215");
		$(this).css("background","#67a331");
	})
	
	var date = new Date();
	$(".date").html(date.getFullYear() + "年" + (date.getMonth()+1) + "月" + date.getDate() + "日");
})

$(function() {
	getOldBaseInfo();//获取老人信息
	
})

function getOldBaseInfo() {
	var oldId = 2;
	$.ajax({
		url: basePath + "health/getOldInfo.do",
		data: {
			"oldId": oldId,
			"random": Math.random()
		},
		type: 'get',
		dataType: 'json',
		success: function(data) {
			$(".name").text(data.name);
		}
	})
}




























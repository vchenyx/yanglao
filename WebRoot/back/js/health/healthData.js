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
	
	//鼠标滑过人体
	//滑过手指
	$(".shouzhi").hover(function(){
		$(".out_xinlv").css({"opacity":"0.7","filter":"alpha(opacity=70)","border":"2px solid #fff"});
	},function(){
		$(".out_xinlv").css({"opacity":"0.2","filter":"alpha(opacity=20)","border":"none"});
	})
	//划过脉搏
	$(".maibo").hover(function(){
		$(".out_xinlv").css({"opacity":"0.7","filter":"alpha(opacity=70)","border":"2px solid #fff"});
	},function(){
		$(".out_xinlv").css({"opacity":"0.2","filter":"alpha(opacity=20)","border":"none"});
	})
	//滑过心脏
	$(".xinzang").hover(function(){
		$(".out_xinlv").css({"opacity":"0.7","filter":"alpha(opacity=70)","border":"2px solid #fff"});
	},function(){
		$(".out_xinlv").css({"opacity":"0.2","filter":"alpha(opacity=20)","border":"none"});
	})
	//滑过心管
	$(".xueguan").hover(function(){
		$(".out_blood").css({"opacity":"0.7","filter":"alpha(opacity=70)","border":"2px solid #fff"});
	},function(){
		$(".out_blood").css({"opacity":"0.2","filter":"alpha(opacity=20)","border":"none"});
	})
	
	//滑过尿成分
	$(".shen").hover(function(){
		$(".out_niao").css({"opacity":"0.7","filter":"alpha(opacity=70)","border":"2px solid #fff"});
	},function(){
		$(".out_niao").css({"opacity":"0.2","filter":"alpha(opacity=20)","border":"none"});
	})
	
	//滑过脚
	$(".foot").hover(function(){
		$(".out_ti").css({"opacity":"0.7","filter":"alpha(opacity=70)","border":"2px solid #fff"});
	},function(){
		$(".out_ti").css({"opacity":"0.2","filter":"alpha(opacity=20)","border":"none"});
	})
	
})




























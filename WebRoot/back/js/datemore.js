
var that;

// <input readonly="readonly" onclick="addTime(this)" type="text" />

//点击文本框日历显现，定位到文本框下面
function addTime(dt){
	$(".date div").removeClass("active");
	var val = $(dt).val();
	if (val != "" && val != null && val.length > 0) {
		var valArr = val.split(";");
		var divArr = $(".date div");
		for (var i = 0; i < valArr.length; i++) {
			for (var j = 0; j < divArr.length; j++) {
				var divVal = $(divArr[j]).attr("result");
				if (divVal == valArr[i]) {
					$(divArr[j]).addClass("active");
				}
				
			}
		}
	}
	that = dt;
	var left = $(dt).offset().left;
	var top = $(dt).offset().top + $(dt).height() + 2;
	$(".date").css({"display":"block","left":left,"top":top});
}



$(function(){
	var html = '<div class="date">'
				+'<div class="zhou"></div>'
				+'<div class="zhou"></div>'
				+'<div class="zhou"></div>'
				+'<div class="zhou"></div>'
				+'<div class="zhou"></div>'
				+'<div class="zhou"></div>'
				+'<div class="zhou"></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div></div>'
				+'<div style="border-right:1px solid #ccc">1</div>'
				+'<div class="zhou btn" style="border:none; width:75px; padding:0 0px;"><button style="background-color: #3983e1;border:none; width:75px; height:30px; outline:none; margin-top:3px; font-size:16px; cursor:pointer;">确 定</button></div>'
				+'<div class="zhou del" style="border:none; width:75px; padding:0 0px;"><button style="border:none; width:75px; height:30px; outline:none; margin-top:3px; font-size:16px; cursor:pointer;">清 除</button></div>'
			+'</div>';
	$("body").append(html);
	
	//周几-开始
	var week = new Date().getTime();
	var newWeek;
	
	for(var i = 0;i<7;i++){
		newWeek = new Date(week).getDay();
		if(newWeek == 0){
			newWeek = "日"
		}
		if(newWeek == 1){
			newWeek = "一"
		}
		if(newWeek == 2){
			newWeek = "二"
		}
		if(newWeek == 3){
			newWeek = "三"
		}
		if(newWeek == 4){
			newWeek = "四"
		}
		if(newWeek == 5){
			newWeek = "五"
		}
		if(newWeek == 6){
			newWeek = "六"
		}
		$(".date").find("div").eq(i).html(newWeek)
		week = week + 24*60*60*1000;
	}
	//周几-结束
	
	//年月日开始
	var date = new Date().toLocaleDateString().split("/").join("-");
	
	var nextDate = new Date().getTime();
	var result;
	var resultMonth;
	var resultDay;
	
	for(var i = 7;i<37;i++){
		result = new Date(nextDate).toLocaleDateString().split("/").join("-");
		if(result.split("-")[1] <= 9){
			resultMonth = "0" + result.split("-")[1];
		}else{
			resultMonth = result.split("-")[1];
		}
		if(result.split("-")[2] <= 9){
			resultDay = "0" + result.split("-")[2];
		}else{
			resultDay = result.split("-")[2];
		}
		result = result.split("-")[0] + "-" + resultMonth + "-" + resultDay;
		$(".date").find("div").eq(i).html(result.split("-")[2]);
		$(".date").find("div").eq(i).attr("result", result);
		nextDate = nextDate + 24*60*60*1000;
	}
	//年月日结束
	
	//点击日历对应日期输入到文本框
	$(".date div").not(".zhou").click(function(){
		var oldHtml = $(that).val();
		var html = $(this).attr("result");
		if(oldHtml != ""){
			if (oldHtml.indexOf(html) < 0){
				$(that).val(oldHtml + html + ";"); 
				$(this).addClass("active");
			} else {
				$(that).val(oldHtml.replace(html + ";", "")); 
				$(this).removeClass("active");
			}
		}else{
			$(that).val(html + ";");
			$(this).addClass("active");
		}
		//$(".date").css("display","none");
	})

		//点击确定按钮隐藏日历
	$(".btn").click(function(){
		$(".date").css("display","none");
	})
	$(".del").click(function(){
		$(that).val("");
		$(".date div").removeClass("active");
		//$(".date").css("display","none");
	})
})
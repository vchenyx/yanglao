$(function(){
	
	//文本框获得、失去焦点时样式改变
	$(".inp1").on("focus",function(){
		$(".inp1").css({"border":"1px solid #65baff","background":"url(images/USER_hi.png) 10px center no-repeat"})
	})
	$(".inp1").on("blur",function(){
		$(".inp1").css({"border":"1px solid #b3b3b3","background":"url(images/USER_nor.png) 10px center no-repeat"})
	})
	
	$(".inp2").on("focus",function(){
		$(".inp2").css({"border":"1px solid #65baff","background":"url(images/password_hi.png) 10px center no-repeat"})
	})
	$(".inp2").on("blur",function(){
		$(".inp2").css({"border":"1px solid #b3b3b3","background":"url(images/password_nor.png) 10px center no-repeat"})
	});
	

})

	
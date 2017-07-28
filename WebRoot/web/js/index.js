
$(function(){
	//ajax引入头部
	$.ajax({
		type:"GET",
		async:false,
		url:"top.html",
		error:function(){
			alert(0)
		},
		success:function(data){
			$(".header").html(data)
		}
	})
	//ajax引入底部
	$.ajax({
		type:"GET",
		async:false,
		url:"bottom.html",
		error:function(){
			alert(0)
		},
		success:function(data){
			$(".footer").html(data)
		}
	})
})

































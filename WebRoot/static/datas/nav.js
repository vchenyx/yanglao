var navs = "";
layui.use('element',function() {
	var $ = layui.jquery,
		element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
	
	//政府登陆
//	$.ajax({
//		type:"GET",
//		url:"datas/government.json",
//		async:false,
//		error:function(){
//			alert("请求失败")
//		},
//		success:function(data){
//			console.log(typeof data)
//			navs = data;
//		}
//	})
	
	//物业登陆
//	$.ajax({
//		type:"GET",
//		url:"datas/property.json",
//		async:false,
//		error:function(){
//			alert("请求失败")
//		},
//		success:function(data){
//			console.log(typeof data)
//			navs = data;
//		}
//	})
	
	//监护人登陆
//	$.ajax({
//		type:"GET",
//		url:"datas/guardian.json",
//		async:false,
//		error:function(){
//			alert("请求失败")
//		},
//		success:function(data){
//			console.log(typeof data)
//			navs = data;
//		}
//	})

	//医生登陆
//	$.ajax({
//		type:"GET",
//		url:"datas/doctor.json",
//		async:false,
//		error:function(){
//			alert("请求失败")
//		},
//		success:function(data){
//			console.log(typeof data)
//			navs = data;
//		}
//	})
	
	//医疗机构登陆
//	$.ajax({
//		type:"GET",
//		url:"datas/medical.json",
//		async:false,
//		error:function(){
//			alert("请求失败")
//		},
//		success:function(data){
//			console.log(typeof data)
//			navs = data;
//		}
//	})
	
	//服务商登陆
	$.ajax({
		type:"GET",
		url:"datas/serviceProvider.json",
		async:false,
		error:function(){
			alert("请求失败")
		},
		success:function(data){
			console.log(typeof data)
			navs = data;
		}
	})


	

})


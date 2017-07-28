
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
//验证身份提交信息
function submit(){
	var left = document.getElementsByClassName("left")[0];
	var error = document.getElementById("error");
	var inp = left.getElementsByTagName("input");
	if(inp[0].value == ""){
		error.innerHTML = "* 请输入姓名"
		error.style.display = "block";
	}
	else if(inp[1].value == ""){
		error.innerHTML = "* 请输入11位手机号"
		error.style.display = "block";
	}
	else if(inp[2].value == ""){
		error.innerHTML = "* 请输入年龄"
		error.style.display = "block";
	}
	else if(inp[3].value == ""){
		error.innerHTML = "* 请设置登录密码"
		error.style.display = "block";
	}
	else if(inp[4].value == "" || inp[4].value != inp[3].value){
		error.innerHTML = "* 确认登录密码"
		error.style.display = "block";
	}
	else{
		error.style.display = "none";
		$.ajax({
			url:"",
			data:{"name":inp[0].value,"phone":inp[1].value,"age":inp[2].value,"password1":inp[3].value,"password2":inp[4].value},
			type:"GET",
			error:function(){
				alert("注册失败")
			},
			success:function(data){
				alert(data);
			}
		});
	};
};

































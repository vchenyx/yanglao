
$(function(){
	//滑过图片
	$(".add_img .img_out").delegate('.img','mouseover',function(){
		$(this).find(".face").css("display","block")
		$(this).find(".del").css("display","block")
	});
	$(".add_img .img_out").delegate('.img','mouseout',function(){
		$(this).find(".face").css("display","none")
		$(this).find(".del").css("display","none")
	});
	
	//删除图片
	$(".add_img .img_out").delegate('.del','click',function(){
		$(this).parent().remove();
	});
	
})
//验证身份提交信息
function submit_info(){
	
	var left = document.getElementsByClassName("left")[0];
	var error = document.getElementById("error");
	var inp = left.getElementsByTagName("input");
	SelectedResults();
	
	if(inp[0].value == ""){
		error.innerHTML = "* 请输入机构名称"
		error.style.display = "block";
	}
	else if(arr[0].split(";")[1] == "undefined"){
		error.innerHTML = "* 请选择机构地址所在省"
		error.style.display = "block";
	}
	else if(arr[1].split(";")[1] == "undefined"){
		error.innerHTML = "* 请选择机构地址所在市"
		error.style.display = "block";
	}
	else if(arr[2].split(";")[1] == "--县/区/县级地区--"){
		error.innerHTML = "* 请选择机构地址所在旗县"
		error.style.display = "block";
	}
	else if(inp[1].value == ""){
		error.innerHTML = "* 请输入联系人姓名"
		error.style.display = "block";
	}
	else if(inp[2].value == ""){
		error.innerHTML = "* 请输入联系人电话"
		error.style.display = "block";
	}
	else if(inp[3].value == ""){
		error.innerHTML = "* 请输入机构创建时间"
		error.style.display = "block";
	}
	else if(inp[4].value == ""){
		error.innerHTML = "* 请输入邮箱";
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

































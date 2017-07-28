
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
		error.innerHTML = "* 请输入姓名"
		error.style.display = "block";
	}
	else if(inp[3].value == ""){
		error.innerHTML = "* 请输入年龄"
		error.style.display = "block";
	}
	else if(inp[4].value == ""){
		error.innerHTML = "* 请输入身份证号"
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
	else if(inp[3].value == ""){
		error.innerHTML = "* 请输入机构创建时间"
		error.style.display = "block";
	}
	else if(inp[5].value == ""){
		error.innerHTML = "* 请输入联系电话";
		error.style.display = "block";
	} else if ($("#pwd").val() != $("#pwd1").val()) {
		error.innerHTML = "* 两次密码输入不一致";
		error.style.display = "block";
	}
	else{
		error.style.display = "none";
		
		json = {};
		var prov = arr[0].split(";")[1];
		var city = arr[1].split(";")[1];
		city = city == "县" ? "" : city;
		address = prov + city + arr[2].split(";")[1] + $("#address").val();
		json["address"] = address;
		json["name"] = $("#name").val();
		sex = $('input[name="sex"]:checked').val();
		json["sex"] = sex;
		json["age"] = $("#age").val();
		json["idCard"] = $("#idCard").val();
		json["phone"] = $("#phone").val();
		json["headImg"] = $("#headImg").val();
		pwd = $("#pwd").val();
		$.ajax({
			url: basePath + "register/userRegisiter.do",
			data:{
				'flag': 1,
				'pwd': pwd,
				'jsonStr': JSON.stringify(json)
			},
			type:"post",
			error:function(){
				alert("注册失败")
			},
			success:function(data){
				layer.alert("注册成功，返回至登录页!", function() {
					location.href = basePath + "login/toLogin.do?flag=" + 1;
				});
			}
		});
	};
};

































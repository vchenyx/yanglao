
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
		flag = $(this).attr("flag");
		if (flag == 1) {
			$("#certificate1").val("");
		}
		if (flag == 2) {
			$("#certificate2").val("");
		}
		if (flag == 3) {
			$("#certificate3").val("");
		}
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
	} else if ($("#googleLat").val() == "" || $("#googleLng").val() == null) {
		error.innerHTML = "* 请定位地址";
		error.style.display = "block";
	} else {
		error.style.display = "none";
		json = {};
		var prov = arr[0].split(";")[1];
		var city = arr[1].split(";")[1];
		city = city == "县" ? "" : city;
		address = prov + city + arr[2].split(";")[1] + $("#address").val();
		json["address"] = address;
		name = $("#name").val();
		json["name"] = name;
		linkName = $("#linkName").val();
		json["linkName"] = linkName;
		linkPhone = $("#linkPhone").val();
		json["linkPhone"] = linkPhone;
		creationTime = $("#creationTime").val();
		json["creationTime"] = creationTime;
		email = $("#email").val();
		json["email"] = email;
		certificate1 = $("#certificate1").val();
		json["certificate1"] = certificate1;
		certificate2 = $("#certificate2").val();
		json["certificate2"] = certificate2;
		certificate3 = $("#certificate3").val();
		json["certificate3"] = certificate3;
		areaId = arr[2].split(";")[0];
		json["areaId"] = areaId;
		latGG = $("#googleLat").val();
		json["latGG"] = latGG;
		lonGG = $("#googleLng").val();
		json["lonGG"] = lonGG;
		
		$.ajax({
			url:basePath + "register/userRegisiter.do",
			data:{
				"jsonStr": JSON.stringify(json),
				"areaId": areaId,
				"flag": $("#flag").val()
			},
			type:"post",
			error:function(){
				alert("注册失败")
			},
			success:function(data){
				layer.alert("您的申请已提交，请等待管理员通知！", function() {
					location.href = basePath + "login/toLogin.do?flag=" + 5;
				});
			}
		});
	};
};

































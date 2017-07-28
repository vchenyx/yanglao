function communityNameTest(){
	if($.trim($("#communityName").val()) == ""){
		$("#error").html("* 机构名称不能为空");
		return false;
	}
	$("#error").html("");
	return true;
}

function communityAddrTest(){
	if($.trim($("#communityAddr").val()) == ""){
		$("#error").html("* 机构地址不能为空");
		return false;
	}
	$("#error").html("");
	return true;
}

function communityPhoneNameTest(){
	if($.trim($("#communityPhoneName").val()) == ""){
		$("#error").html("* 联系人姓名不能为空");
		return false;
	}
	$("#error").html("");
	return true;
}

function communityPhoneTest(){
	if($.trim($("#communityPhone").val()) == ""){
		$("#error").html("* 联系人电话不能为空");
		return false;
	}
	else if(isNaN(parseInt($.trim($("#communityPhone").val())))){
		$("#error").html("* 输入手机号非数字，请重新输入！");
		return false;
	}
	else if($.trim($("#communityPhone").val()).length != 11){
		$("#error").html("* 输入手机号不够11位，请重新输入！");
		return false;
	}
	$("#error").html("");
	return true;
}

function passwordTest(){
	if($.trim($("#password").val()) == ""){
		$("#error").html("* 登录密码不能为空");
		return false;
	}
	$("#error").html("");
	return true;
}

function passwordsTest(){
	if($.trim($("#passwords").val()) == ""){
		$("#error").html("* 确认密码不能为空");
		return false;
	}
	if($.trim($("#passwords").val()) != $.trim($("#password").val())){
		$("#error").html("* 确认密码和登录密码必须相同");
		return false;
	}
	$("#error").html("");
	return true;
}
//验证身份提交信息
function registerCommunity(){
	if(communityNameTest() && communityAddrTest() && communityPhoneNameTest()
			&& communityPhoneTest() && passwordTest() && passwordsTest()){
		document.communityForm.submit();
	}
};

































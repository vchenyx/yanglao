function guardianNameTest(){
	if($.trim($("#guardianName").val()) == ""){
		$("#error").html("* 姓名不能为空");
		return false;
	}
	$("#error").html("");
	return true;
}

function guardianPhoneTest(){
	if($.trim($("#guardianPhone").val()) == ""){
		$("#error").html("* 手机号不能为空");
		return false;
	}
    else if(isNaN(parseInt($.trim($("#guardianPhone").val())))){
	$("#error").html("* 输入手机号非数字，请重新输入！");
	return false;
}
    else if($.trim($("#guardianPhone").val()).length != 11){
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
		$("#error").html("* 确认密码和登录密码必须相同")
		return false;
	}
	$("#error").html("");
	return true;
}


//验证身份提交信息
function registerGuardian(){
	if(guardianNameTest() && guardianPhoneTest() && passwordTest()
			&& passwordsTest()){
		document.guardianForm.submit();
	}
};


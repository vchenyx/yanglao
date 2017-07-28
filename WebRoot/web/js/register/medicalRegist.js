function medicalNameTest(){
	if($.trim($("#medicalName").val()) == ""){
		$("#error").html("* 医疗机构名称不能为空");
		return false;
	}
	
	$("#error").html("");
	return true;
}

function medicalAddrTest(){
	if($.trim($("#medicalAddr").val()) == ""){
		$("#error").html("* 医疗机构地址不能为空");
		return false;
	}
	$("#error").html("");
	return true;
}

function medicalPhoneNameTest(){
	if($.trim($("#medicalPhoneName").val()) == ""){
		$("#error").html("* 医疗机构联系人不能为空");
		return false;
	}
	$("#error").html("");
	return true;
}

function medicalPhoneTest(){
	if($.trim($("#medicalPhone").val()) == ""){
		$("#error").html("* 医疗机构联系电话不能为空");
		return false;
	}
else if(isNaN(parseInt($.trim($("#medicalPhone").val())))){
	$("#error").html("* 输入手机号非数字，请重新输入！");
	return false;
}
else if($.trim($("#medicalPhone").val()).length != 11){
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
function registerMedical(){
	if(medicalNameTest() && medicalAddrTest() && medicalPhoneNameTest()
			&& medicalPhoneTest() && passwordTest() && passwordsTest()){
		document.medicalForm.submit();
	}
};

































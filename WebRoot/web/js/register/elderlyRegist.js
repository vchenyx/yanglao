function realnameTest(){
	if($.trim($("#realname").val()) == ""){
		$("#error").html("* 姓名不能为空");
		return false;
	}
	$("#error").html("");
	return true;
}
function telephoneTest(){
	if($.trim($("#telephone").val()) == ""){
		$("#error").html("* 手机号作为登录方式，不能为空");
		return false;
	}
	else if(isNaN(parseInt($.trim($("#telephone").val())))){
		$("#error").html("* 输入手机号非数字，请重新输入！");
		return false;
	}
	else if($.trim($("#telephone").val()).length != 11){
		$("#error").html("* 输入手机号不够11位，请重新输入！");
		return false;
	}
	$("#error").html("");
	return true;
}
function ageTest(){
	if($.trim($("#age").val()) == ""){
		$("#error").html("* 年龄不能为空");
		return false;
	}else if(isNaN(parseInt($.trim($("#age").val())))){
		$("#error").html("* 请输入数字");
		return false;
	}/*else if(parseInt($.trim($("#age").val()))<=50 || parseInt($.trim($("#age").val()))>="150"){
		$("#error").html("* 请输入正确的年龄");
		return false;
	}*/
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
function registerELderly(){
	if(realnameTest() && telephoneTest() 
			&& ageTest() && passwordTest()
			&& passwordsTest()){
		document.elderlyForm.submit();
	}
};
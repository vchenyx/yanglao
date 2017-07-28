/*过滤文本框中的非法字符
 * 
 *  
 */
function stripscript(s) {
	var pattern = new RegExp(
			"[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var rs = "";
	for ( var i = 0; i < s.length; i++) {
		rs = rs + s.substr(i, 1).replace(pattern, '');
	}
	if (rs.length < s.length) {
		return false;
	} else {
		return true;
	}
}        

/*
 * mac地址格式的校验
 * 
 * 
 */
function macCheck(macs){
	var temp1=/[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}/;
    if (!temp1.test(macs)){
		return false;
	}
	else{
		return true;
	}
}
    
    
    
 /*
	 * ip地址格式的校验
	 * 
	 * 
	 */
function isIP(ip){
	var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
	if(re.test(ip)){
		ip.match(re);
		if(RegExp.$1<=255&&RegExp.$1>=0
		   &&RegExp.$2<=255&&RegExp.$2>=0
		   &&RegExp.$3<=255&&RegExp.$3>=0
		   &&RegExp.$4<=255&&RegExp.$4>=0){
			return true;
		}else{
			return false;
		}
	}else{
		return false;
	}
}
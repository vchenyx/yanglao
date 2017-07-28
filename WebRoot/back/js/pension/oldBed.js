var deviceNo = 641716000005;

function switchDevoce() {
	var val = $("#deviceNo").val();
	if (val == "") {
		deviceNo = 641650000231;
	} else {
		deviceNo = val;
	}
	data1 = [];
	data2 = [];
	getDeviceNew30s()
	getInitData1();
	getInitData2();
	//init();
}


/**
 * 查看设备状态
 */
function getDeviceStatus() {
	$.ajax({
		url: basePath + "darma/getDeviceStatus.do?deviceNo=" + deviceNo,
		type: 'post',
		dataType: 'json',
		success: function(data) {
			alert(JSON.stringify(data))
		}
	});
}
/**
 * 获取睡眠状态
 */
function getSleepState() {
	var lastDate = "2017-05-24 08:30:00";
	$.ajax({
		url: basePath + "darma/getSleepState.do?deviceNo=" + deviceNo + "&lastDate=" + lastDate,
		type: 'post',
		dataType: 'json',
		success: function(data) {
			alert(JSON.stringify(data))
		}
	});
}
 

/**
 * 获取30s数据
 */
function getDeviceNew30s() {
	$.ajax({
		url: basePath + "darma/getDeviceNew30s.do?deviceNos=" + deviceNo,
		type: 'post',
		dataType: 'json',
		async: false,
		success: function(data) {
			handle30sData(data);
		}
	});
}

var heartArr = [];
var breathArr = [];
function handle30sData(data) {
	var retCode = data.retCode;
	var devicesData = data.data;
	var dev1 = devicesData[deviceNo];
	heartArr = dev1.heart;//心率  val 数值 65436 的数据为无效的值
	var statusArr = dev1.status;//状态:3_离床,4_在床,5_设备故障,6_离线
	var motionArr = dev1.motion;//体动
	breathArr = dev1.breath;//呼吸 -100 的数据为无效的值
	var signalArr = dev1.signal;//设备信号
	if (statusArr.length > 0) {
		lastStatue = statusArr[statusArr.length -1];
		console.log("state: " + lastStatue.val);
		if (lastStatue.val == 3 || lastStatue.val == 10) {//离床
			$("#state").attr("src", basePath + "back/images/li.png")
		}
		if (lastStatue.val == 4) {//在床
			$("#state").attr("src", basePath + "back/images/sit.png")
		}
		if (lastStatue.val == 5) {//故障
			$("#state").attr("src", basePath + "back/images/guzhang.png")
		}
		if (lastStatue.val == 6) {//离线
			$("#state").attr("src", basePath + "back/images/lixian.png")
		}
	}
	/* alert("心率数据: " + JSON.stringify(heartArr));
	alert("状态数据: " + JSON.stringify(statusArr));
	alert("体动数据: " + JSON.stringify(motionArr));
	alert("呼吸数据: " + JSON.stringify(breathArr));
	alert("信号数据: " + JSON.stringify(signalArr)); */
}
/**
 * 获取用户行为数据
 * type0 所有, 1呼吸, 2心率, 3体动原始数据, 4睡眠状态原始数据, 5处理后的体动数据
 */
function manBehaveData() {
	$.ajax({
		url: basePath + "darma/manBehaveData.do?deviceNo=641650000230&type=" + 0,
		type: 'post',
		dataType: 'json',
		success: function(data) {
			alert(JSON.stringify(data))
		}
	});
}


var data1 = [];
var data2 = [];

var myChart1 = echarts.init(document.getElementById("main1"));
var myChart2 = echarts.init(document.getElementById("main2"));

function getInitData1() {
	for (var i = 0; i < heartArr.length; i++) {
		var newHeart = heartArr[i];
		var now = new Date(newHeart.markTime * 1000);
	    var value = newHeart.val;
	    if (value == 65436) {
	    	value = 50;
	    }
	    data1.push({
	        name: now.toString(),
	        value: [now,value]
	    });
	}
}
function getInitData2() {
	for (var i = 0; i < breathArr.length; i++) {
		var newBreath = breathArr[i];
		var now = new Date(newBreath.markTime * 1000);
	    var value = newBreath.val;
	    if (value == -100) {
	    	value = 25;
	    }
	    data2.push({
	        name: now.toString(),
	        value: [now,value]
	    });
	}
}

$(function(){
	getDeviceNew30s()
	getInitData1();
	getInitData2();
	
	$("#main1").width($(window).width() * 0.5);
	$("#main1").height($(window).height()-$(".pic").height()-62);
	$("#main2").width($(window).width() * 0.49);
	$("#main2").height($(window).height()-$(".pic").height()-62);
	$(window).resize(function(){
		$("#main1").width($(window).width() * 0.5);
		$("#main1").height($(window).height()-$(".pic").height()-62);
		$("#main2").width($(window).width() * 0.49);
		$("#main2").height($(window).height()-$(".pic").height()-62);
		init();
	})
	init();
})


function randomData1() {
	if (heartArr != undefined && heartArr != "undefined" && heartArr.length > 0) {
		var newHeart = heartArr[heartArr.length - 1];
	    now = new Date(newHeart.markTime * 1000);
	    value = newHeart.val;
	    console.log("value: " + value);
	    ran = getIndex();
//	    console.log("ran: " + ran);
	    if (value == 65436) {
		    $("#heartNum").text("--");
		    value = 50;
	    } else {
		    $("#heartNum").text(value);
		    value = 50 + value * ran;
	    }
	    var nowStr = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
	    return {
	        name: now.toString(),
	        value: [
	            now,
	            value,
	        ]
	    }
		
	}
}
function randomData2() {
	if (breathArr != undefined && breathArr != "undefined" && breathArr.length > 0) {
		var newBreath = breathArr[breathArr.length - 1];
	    now = new Date(newBreath.markTime * 1000);
	    value = newBreath.val;
	    var ran1 = getIndex1;
//	    console.log("呼吸: " + value);
	    if (value == -100) {
		    $("#breathNum").text("--");
		    value = 25;
	    } else {
		    $("#breathNum").text(value);
		    value = 20 + value * ran;
	    }
	    return {
	        name: now.toString(),
	        value: [
	            now,
	            value
	        ]
	    }
		
	}
}
var flag = 0;
function getIndex() {
	n = 1;
	m = 5
	var c = m-n+1;    
	if (flag == 0) {
	    flag = 1;
	    return Math.floor(Math.random() * c + n) / -10;  
	} else {
	    flag = 0;
	    return Math.floor(Math.random() * c + n) / 10;  
	}
}
var flag1 = 0;
function getIndex1() {
	n = 1;
	m = 3;
	var c = m-n+1;    
	if (flag1 == 0) {
		flag1 = 1;
		return Math.floor(Math.random() * c + n) / -10;  
	} else {
		flag1 = 0;
		return Math.floor(Math.random() * c + n) / 10;  
	}
}

function init() {
	var myChart1 = echarts.init(document.getElementById("main1"));
	var myChart2 = echarts.init(document.getElementById("main2"));
	option1 = {
	    title: {
	        text: '心率'
	    },
	    tooltip: {
	        trigger: 'axis',
	        formatter: function (params) {
	            params = params[0];
	            var date = new Date(params.name);
	            return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
	        },
	        axisPointer: {
	            animation: false
	        }
	    },
	    xAxis: {
	        type: 'time',
	        splitLine: {
	            show: false
	        }
	    },
	    yAxis: {
	        type: 'value',
	        min: 0,
	        max: 120,
	        boundaryGap: [0, '100%'],
	        splitLine: {
	            show: false
	        }
	    },
	    series: [{
	        name: '模拟数据',
	        type: 'line',
	        smooth: true,
	        showSymbol: false,
	        hoverAnimation: false,
	        data: data1
	    }]
	};
		
	option2 = {
	    title: {
	        text: '呼吸'
	    },
	    tooltip: {
	        trigger: 'axis',
	        formatter: function (params) {
	            params = params[0];
	            var date = new Date(params.name);
	            return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
	        },
	        axisPointer: {
	            animation: false
	        }
	    },
	    xAxis: {
	        type: 'time',
	        splitLine: {
	            show: false
	        }
	    },
	    yAxis: {
	        type: 'value',
	        min: 0,
	        max: 50,
	        boundaryGap: [0, '100%'],
	        
	        splitLine: {
	            show: false
	        }
	    },
	    series: [{
	        name: '模拟数据',
	        type: 'line',
	        showSymbol: false,
	        markLine: {
	        	smooth: true,
	        },
	        hoverAnimation: false,
	        data: data2
	    }]
	};
	myChart1.setOption(option1);
	myChart2.setOption(option2);

	setInterval(function () {
		getDeviceNew30s()
		
	    data1.shift();
	    data1.push(randomData1());
	    data2.shift();
	    data2.push(randomData2());

	    myChart1.setOption({
	        series: [{
	            data: data1
	        }]
	    });
	    
	    myChart2.setOption({
	        series: [{
	            data: data2
	        }]
	    });
	}, 1000);
}










/*var open = true;
var arr1 = [1.3,0.6];
var arr2 = [1.005,0.995];
var num1 = 0;
function abc(dt){
	if(num1 <= (dt/2)){
		if(open){
			console.log(0)
			dt = dt*arr2[0];
			open = false;
		}
		else{
			dt = dt*arr2[1];
			open = true;
		}
	}
	else if(num1 >= (dt/2) && num1 <= (dt/2 + 2)){
		if(open){
			dt = dt*arr1[0];
			open = false;
		}
		else{
			dt = dt*arr1[1];
			open = true;
		}
	}
	else{
		num1 = 0;
	}
	num1++;
	return dt;
}*/

layui.config({
	base: 'plugins/layui/modules/'
});
			
layui.use(['icheck','element', 'layer','form'], function() {
	var element = layui.element(),
		$ = layui.jquery,
		layer = layui.layer,
		form = layui.form();
	$('input').iCheck({
		checkboxClass: 'icheckbox_flat-green'
	});
	
	
	//动态获取地图div高度
	$(window).on('resize', function() {
		var header_height = $(".header_top").height();
		var all_width = $(window).width();
		var all_height = $(window).height();
		var info_width = $(".info").width();
		var content_width = $(".content").width();
		var height;
		if((all_height - header_height -50) < 500){
			height = 500;
		}
		else{
			height = all_height - header_height -50;
		}
		$(".content").css("height",height);
	}).resize();
	
	$('.site-table tbody tr').on('click', function(event) {
		$(this).addClass("active").siblings().removeClass("active")
	});
	
	//点击右侧人员时显示人员信息
	$('.site-table tbody tr').on('click', function(event) {
		
	});
	
});



//开启方式：1人员定位2部门定位3区域定位
var location_model = 1;
function locationModel(dt,lat,lng,level){
	$("#alarm_msg").val(0);
	$("#areaname_msg").val("");
	if(dt==1){
		$("#people_location").css("display","block");
		$("#dept_location").css("display","none");
		$("#area_location").css("display","none");
		level = getMapLevel();
	}else if(dt==2){
		$("#people_location").css("display","none");
		$("#dept_location").css("display","block");
		$("#area_location").css("display","none");
		level = 2;
		//缩放级别
		zoomToLevel(lat,lng,level);
	}else if(dt==3){
		$("#people_location").css("display","none");
		$("#dept_location").css("display","none");
		$("#area_location").css("display","block");
		level = getMapLevel();
	}
	//改变定位方式
	location_model = dt;
	//初始化选项
	initSelect();
	//取消定时
	clearTimeout(time_out);
	//加载所有定位人员信息
   	loadAllLocation();
}
//初始化选项
function initSelect(){
	$("#speedlocationbox").val("");
	//不可绘制
	showDrawArea(0);
}
/*人员定位方法*/
//加载所有定位人员信息
function loadAllLocation(){
	$.ajax({
        url: "../location/loadAllLocation.do",    		data:{"location_model":location_model,"username":$("#username").val(),"workerid":$("#speedlocationbox").val(),"random":Math.random()},
        type: "post",
        dataType : "json",
        async: false,
        success: function (json) {
        	//加载人员信息
        	$("#locationpeoplenum").html(json.location_people_num);
        	$("#alarmpeoplenum").html(json.alarm_people_num);
        	$("#speedlocationbox").html(json.speedlocationbox);
        	if(json.alarm_people_num > 0){
        		play("sound","../sounds/ASK_HELP.mp3");
        		if(location_model != 1 && $("#alarm_msg").val() == 0){
        			alert("有人报警，请选择人员定位，跟踪报警人员位置！");
        			$("#alarm_msg").val(1);
        		}
        	}else{
        		$("#alarm_msg").val(0);
        	}
        	if(location_model == 3){
        		if($("#areaname_msg").val() == $("#area_speedlocationbox").val()){
            		creatAreaPoint(json.locations,getMapLevel());
        		}else{
            		creatAreaPoint(json.locations,json.level);
        			$("#areaname_msg").val($("#area_speedlocationbox").val());            			
        		}
        	}else{
        		clearLine();
        		clearMarkers();
        	}
            m_speed_people_map = json.location_map;
       		loadSpeedPeopleLocation();
       		
        },
    });
	time_out = setTimeout("loadAllLocation()",time);
}
//快速定位跟踪
function speedTracker(){
	var id = $("#older_position").val();
	//取消定时
	clearTimeout(time_out);
	//可以定位
	m_is_tracking = true;
	//加头标
	m_tracking_key.put(track_arrow_feature,id);
	//改变定位模式
	location_model = 1;
	//加载所有定位人员信息
   	loadAllLocation();
}
//取消跟踪
function cancelSpeedTracker(){
	//取消定位跟踪
	m_is_tracking = false;
	//去除跟踪图标
	clearTrackArrowFeature();
}
























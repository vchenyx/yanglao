/**************************全time局变量初始化-开始*****************************/
var time = 5000;                                            		        //时间轮回
var time_out ; 															//时间定时器
var project_path_header = "..";                    		 		//项目名称头，用于路径
var m_position_map;            		                                //室外位置地图
var m_speed_people_map;										//从后台获得室外地图上的人员图元坐标
var m_people_position_map = new HashMap();		//存储室外地图上的人员图元坐标
var m_position_point = new HashMap();			   		//存储室外地图上的人员图元坐标
var m_position_marker = new HashMap();       	       //存储室外地图上的人员图元
var m_position_marker_info = new HashMap();      //存储室外地图上的人员窗口信息
var m_position_loctype = new HashMap();             //存储室外地图上的人员的报警类型
var m_tracking_key = new HashMap();                   //存储室外地图上的人员的定位key
var m_tracker_phone_id;                                  			//跟踪手机设备号
var track_arrow_feature = "track_arrow_feature";   //定位跟踪
var m_is_tracking = false;  										//是否跟踪,默认不跟踪
var DEFAULT_PEOPLE_IMAGE_SRC = basePath + "back/images/people.png";		    //默认人员图像
var DEFAULT_PEOPLE_IMAGE_SRC1 = basePath + "back/images/people.gif";         //人员报警图像
var DEFAULT_PEOPLE_IMAGE_SRC2 = basePath + "back/images/nurse.png";         //人员报警图像
var TRACK_ARROW_IMAGE_SRC = basePath + "back/images/track_arrow1.gif";	//跟踪箭头图像
var ALARM_NOTICE_SOUND = project_path_header + "/sounds/ASK_HELP.mp3";                               //声音文件

var m_is_draw = false;                                              //是否可以绘制，默认不可绘制

GMarker.prototype.setIndex=function(index){
	this.index=index;
};

var polyline;
var markers=[];

/**
 * 加载谷歌地图
 */
function loadScript(lat,lng,level) { 
	var lat_new = 39.9493;
	var lng_new = 116.3975;
	var level_new = 2;
	if(lat != null && lat != ""){
		lat_new = parseFloat(lat);
	}
	if(lng != null && lng != ""){
		lng_new = parseFloat(lat);
	}
	if(level != null && level != ""){
		level_new = parseInt(level);
	}
	GoogleInitialize(lat_new,lng_new,level_new);
 }

function GoogleInitialize(north,east,level) {
	if (GBrowserIsCompatible()) {
		m_position_map = new GMap2(document.getElementById("container"));
		//禁止鼠标双击缩放
		m_position_map.disableDoubleClickZoom();
		m_position_map.setCenter(new GLatLng(north, east), level);
		
		GEvent.addListener(m_position_map,'click',function(overlay,point){
		     if(point && m_is_draw)createMarker(point);
		});
		loadAllLocation();
	}
}
//创建区域坐标
function creatAreaPoint(locs,level){
	var points=[];
	for(var i=0;i<locs.length;i++){
		points[i] = new GLatLng(locs[i].latitude, locs[i].longitude);
	}
	if(level == locs[0].level){
		zoomToLevel(locs[0].latitude, locs[0].longitude,level);
	}
	reCreateMarker(points);
}

//创建marker
function createMarker(point){
	//创建标注
	var baseIcon = new GIcon();  
	baseIcon.shadow = "http://www.google.cn/mapfiles/dd-start.png";  
	var myicon = new GIcon(baseIcon);
	var marker = new GMarker(point, {icon:myicon,draggable:true,bouncy:true});
	var index = markers.length;
	
	GEvent.addListener(marker,'dblclick',function(){
		deleteMarker(marker);
	});
	GEvent.addListener(marker,'dragstart',function(){
		marker.setImage("http://www.google.com/mapfiles/dd-start.png");
	});
	GEvent.addListener(marker,'dragend',function(){
		marker.setImage("http://www.google.com/mapfiles/marker.png");
	});
	GEvent.addListener(marker,'drag',function(){
		redrawPolyline();
	});
	
	marker.setIndex(index);
	markers[index] = marker;
	m_position_map.addOverlay(marker);
	
	redrawPolyline();
}


//创建marker
function reCreateMarker(points){
	for(var i=0;i<points.length;i++){
		//创建标注
		var baseIcon = new GIcon();  
		baseIcon.shadow = "http://www.google.cn/mapfiles/dd-start.png";  
		var myicon = new GIcon(baseIcon);
		var marker = new GMarker(points[i], {icon:myicon,draggable:true,bouncy:true});
		marker.setIndex(i);
		markers[i] = marker;
		m_position_map.addOverlay(marker);
	}
	redrawPolyline(points);
	for(var i=0;i<markers.length;i++){
		m_position_map.removeOverlay(markers[i]);
	}
	markers=[];
}

function deleteMarker(marker){
	var index=marker.index;
	m_position_map.removeOverlay(marker);
	for(var i=index;i<markers.length-1;i++){
		markers[i+1].setIndex(i);
		markers[i]=markers[i+1];
	}
	markers.length=markers.length-1;
	redrawPolyline();
}
/*
function getRectanglePoint(lat1,lat2,lng1,lng2){
	var up_lat;
	var down_lat;
	var left_lng;
	var right_lng;
	if(lat1 < lat2){
		up_lat = lat2;
		down_lat = lat1;
	}else{
		up_lat = lat1;
		down_lat = lat2;
	}
	if(lng1 < lng2){
		left_lng = lng1;
		right_lng = lng2;
	}else{
		left_lng = lng2;
		right_lng = lng1;
	}
	var points=[];
	points[0] = new GLatLng(up_lat, left_lng);
	points[1] = new GLatLng(up_lat, right_lng);
	points[2] = new GLatLng(down_lat, right_lng);
	points[3] = new GLatLng(down_lat, left_lng);
	points[4] = points[0];
	return points;
}
//矩形
function addPolyline(points){
	polyline=new GPolyline(
		points,
		"#FF0000",
		5,
		0.5
	);
	m_position_map.addOverlay(polyline);
}
*/
function addPolyline(){
	var points=[];
	for(var i=0;i<markers.length;i++){
		points[i]=markers[i].getPoint();
	}
	polyline=new GPolyline(
		points,
		"#FF0000",
		5,
		0.5
	);
	m_position_map.addOverlay(polyline);
}
//完成区域
function finishPolyline(){
	clearLine();
	var points=[];
	for(var i=0;i<markers.length;i++){
		points[i]=markers[i].getPoint();
	}
	points[i] = points[0];
	polyline=new GPolyline(
			points,
			"#FF0000",
			5,
			0.5
	);
	m_position_map.addOverlay(polyline);
}
function removePolyline(){
	polyline.remove();
	polyline=null;
}
function redrawPolyline(points){
	if(polyline){
		removePolyline();
	}
	addPolyline(points);
}

//清除不再上传的图元map
function clearMapKey(){
	
	var keys = m_position_point.keySet();
	for(var i in keys){
		var key = keys[i];
		if(!m_people_position_map.containsKey(key)){
			m_position_point.remove(key);
			m_position_loctype.remove(key);
			m_position_map.removeOverlay(m_position_marker.get(key));
			m_position_marker.remove(key);
			if(m_tracking_key.get(track_arrow_feature) == key){
				m_position_map.removeOverlay(m_position_marker.get(track_arrow_feature));
			}
		}
	}
}

//加载快速定位的人的位置信息
function loadSpeedPeopleLocation(json){
	$("#locationpeoplenum").html(json.length);
	m_people_position_map.clear();
	var peopleShow = "";
	var alarmNum = 0;
	$.each(json, function(n) {
		values = json[n];
		//存储地图人员map
		var key = values.id;  //用户id
		m_people_position_map.put(key, values);
		//获取定位值;
		var userId = values.id;
		var east_lontitude = values.lonGG; //东经
		var north_latitude= values.latGG;//北纬
		var netType = "GPRS";           //网络类型
		var locType = values.posiType;           //定位类型
		var entryTime = new Date(values.posiTime).toLocaleString();  //时间
		var departmentname = values.pensionName;  //部门
		var peoplename = values.name;  //人名
		var phonenum = values.phone;  //手机号码
		var peopleId = values.id;  //工号
		var everytime = new Date().toLocaleString();  //更新时间
		//储存报警类型
		m_position_loctype.put(key,locType);
		//创建坐标
		var point = new GLatLng(north_latitude, east_lontitude);
		//将坐标存在map
		m_position_point.put(key,point);
		
		//创建标注
		var baseIcon = new GIcon();  
		baseIcon.shadow = "http://www.google.cn/mapfiles/shadow50.png";  
		   
//		var loc_type = "";
		var content = "";
		var peopleType = "";
		if(locType == 0){
			loc_type = "老人定位";
			baseIcon.iconSize = new GSize(52, 68);  
			var myicon = new GIcon(baseIcon);  
			myicon.image = DEFAULT_PEOPLE_IMAGE_SRC;
			peopleType = "老人";
			content = "<table>"+
	    	"<tr><td>人员姓名：</td><td><b>"+peoplename+"</b></td></tr>" +
	    	"<tr><td>联系电话：</td><td><b>"+phonenum+"</b></td></tr>" +
	    	"<tr><td>监&nbsp;&nbsp;护&nbsp;&nbsp;人：</td><td><b>"+values.guardianName+"</b></td></tr>" +
	    	"<tr><td>联系电话：</td><td><b>"+values.guardianPhone+"</b></td></tr>" +
	    	"<tr><td>定位方式：</td><td><b>"+netType+"</b></td></tr>" +
	    	"<tr><td>定位类型：</td><td><b>"+loc_type+"</b></td></tr>" +
	    	"<tr><td>上传时间：</td><td><b>"+entryTime+"</b></td></tr>" +
	    	"<tr><td>人员类别：</td><td><b>"+peopleType+"</b></td></tr>" +
	    	"</table>";
		} else if(locType == 1){
			alarmNum++;
			loc_type = "SOS求助";
			baseIcon.iconSize = new GSize(52, 68);  
			var myicon = new GIcon(baseIcon);  
			myicon.image = DEFAULT_PEOPLE_IMAGE_SRC1;
			peopleType = "老人";
			content = "<table>"+
	    	"<tr><td>人员姓名：</td><td><b>"+peoplename+"</b></td></tr>" +
	    	"<tr><td>联系电话：</td><td><b>"+phonenum+"</b></td></tr>" +
	    	"<tr><td>监护人：</td><td><b>"+values.guardianName+"</b></td></tr>" +
	    	"<tr><td>监护人电话：</td><td><b>"+values.guardianPhone+"</b></td></tr>" +
	    	"<tr><td>定位方式：</td><td><b>"+netType+"</b></td></tr>" +
	    	"<tr><td>定位类型：</td><td><b>"+loc_type+"</b></td></tr>" +
	    	"<tr><td>上传时间：</td><td><b>"+entryTime+"</b></td></tr>" +
	    	"<tr><td>人员类别：</td><td><b>"+peopleType+"</b></td></tr>" +
	    	"</table>";
		} else {
			loc_type = "护工定位";
			baseIcon.iconSize = new GSize(40, 56);  
			var myicon = new GIcon(baseIcon);  
			myicon.image = DEFAULT_PEOPLE_IMAGE_SRC2;
			peopleType = "护工";
			content = "<table>"+
	    	"<tr><td>人员姓名：</td><td><b>"+peoplename+"</b></td></tr>" +
	    	"<tr><td>联系电话：</td><td><b>"+phonenum+"</b></td></tr>" +
	    	"<tr><td>定位方式：</td><td><b>"+netType+"</b></td></tr>" +
	    	"<tr><td>定位类型：</td><td><b>"+loc_type+"</b></td></tr>" +
	    	"<tr><td>上传时间：</td><td><b>"+entryTime+"</b></td></tr>" +
	    	"<tr><td>人员类别：</td><td><b>"+peopleType+"</b></td></tr>" +
	    	"</table>";
		}
		var marker = new GMarker(point, myicon);
		if(m_position_marker.get(key) != null){
			m_position_map.removeOverlay(m_position_marker.get(key));
		}
		m_position_map.addOverlay(marker);
		//将人员图元存在map
		m_position_marker.put(key, marker);
		//创建窗口
		if (locType == 1) {
			peopleShow += "<tr style='background: red;'><td onclick='showMsg(this)' msg='" + JSON.stringify(values) + "'>" + peoplename + "</td></tr>";
		} else {
			peopleShow += "<tr><td onclick='showMsg(this)' msg='" + JSON.stringify(values) + "'>" + peoplename + "</td></tr>";
		}
		/*GEvent.addListener(marker, "mouseover", function(){  
			m_position_map.openInfoWindow(point, content);
		});*/  
		GEvent.addListener(marker, "click", function(){  
			m_position_map.openInfoWindow(point, content);
		});  
		
	});
	$("#peopleShow").html(peopleShow);
	$("#alarmpeoplenum").html(alarmNum);
	if(m_position_point.size()>0){
		clearMapKey();
	}
//	alert(m_tracking_key.get(track_arrow_feature));
	if(m_tracking_key.containsKey(track_arrow_feature)){
		positionPeople(m_tracking_key.get(track_arrow_feature));
	}

}

function showMsg(event) {
	var obj = eval("(" + $(event).attr("msg") + ")");
	$("#older_position").val(obj.id);
	$(".old_name").html(obj.name);
	$(".old_age").html(obj.age);
	$(".old_phone").html(obj.phone);
	$(".old_peopleId").html(obj.idCard);
	$(".old_sex").html((obj.sex == "1" ? '男' : '女'));
}

//定位人员
function positionPeople(key){
	if (m_is_tracking && m_position_point.containsKey(key)) {
		m_position_map.panTo(m_position_point.get(key));
		if (m_position_marker.containsKey(track_arrow_feature)){
			m_position_map.removeOverlay(m_position_marker.get(track_arrow_feature));
		}
		var baseIcon = new GIcon();  
		baseIcon.shadow = "http://www.google.cn/mapfiles/shadow50.png";  
		baseIcon.iconSize = new GSize(20, 20); //图片尺寸
		baseIcon.shadowSize = new GSize(0, 0 );  
		if(m_position_loctype.get(key) < 1){
			baseIcon.iconAnchor = new GPoint(10, 42);  //偏移
		}else if(m_position_loctype.get(key) == 1){
			baseIcon.iconAnchor = new GPoint(10, 52);  //偏移
		}
		baseIcon.infoWindowAnchor = new GPoint(0, 0); 
		var icon = new GIcon(baseIcon);
	    icon.image = TRACK_ARROW_IMAGE_SRC;
	    var myMarker = new GMarker(m_position_point.get(key), icon);
		m_position_map.addOverlay(myMarker);
		//将人员图元存在map
		m_position_marker.put(track_arrow_feature, myMarker);
		//存储定位key
		m_tracking_key.put(track_arrow_feature,key);
    }	
}
//清除跟踪图标
function clearTrackArrowFeature(){
	if (m_position_marker.containsKey(track_arrow_feature)){
		m_position_map.removeOverlay(m_position_marker.get(track_arrow_feature));
	}
}
//移动到地图级别
function zoomToLevel(lat,lng,level){
	if(level == 0){
		level = m_position_map.getZoom();
	}
	if(lat==0 && lng==0){
		m_position_map.setCenter(new GLatLng(39.9493, 116.3975), level);
	}else{
		m_position_map.setCenter(new GLatLng(lat, lng), level);
	}
}
function getMapLevel(){
	return m_position_map.getZoom();
}
function clearLine(){
	if(polyline){
		removePolyline();
	}
}
function clearMarkers(){
	for(var i=0;i<markers.length;i++){
		m_position_map.removeOverlay(markers[i]);
	}
	markers=[];
}
//开启绘制地图
function openDraw(dt){
	if(dt == 1){
		//开始绘制
		clearLine();
		clearMarkers();
		return 1;
	}else if(dt == 2){
		//完成绘制
		if(markers.length<3){
			alert("至少三个点才能确定一个区域");
			return 0;
		}
		finishPolyline();
		var latlng="";
		for(var i=0;i<markers.length;i++){
			latlng += markers[i].getPoint().lat()+","+markers[i].getPoint().lng()+";";
		}
		latlng += markers[0].getPoint().lat()+","+markers[0].getPoint().lng();
		return latlng;
	}else if(dt == 3){
		//取消绘制
		clearLine();
		clearMarkers();
		return 3;
	}else if(dt == 4){
		//删除绘制
		return 4;
	}else if(dt == 5){
		//取消弹出层
		return 5;
	}else{
		return 10;
	}
}

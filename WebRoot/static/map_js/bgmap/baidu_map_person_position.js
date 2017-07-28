/**************************全time局变量初始化-开始*****************************/
var time = 5000;                                            		        //时间轮回
var time_out ; 															//时间定时器
var project_path_header = "..";                    		 		//项目名称头，用于路径
var m_position_map;            		                                //室外位置地图
var m_speed_people_map = new HashMap();			   		//存储室外地图上的人员图元坐标
var m_position_point = new HashMap();			   		//存储室外地图上的人员图元坐标
var m_position_marker = new HashMap();       	       //存储室外地图上的人员图元
var m_position_marker_info = new HashMap();      //存储室外地图上的人员窗口信息
var m_tracker_phone_id;                                  			//跟踪手机设备号
var m_is_tracking = false;  										//是否跟踪,默认不跟踪
var DEFAULT_PEOPLE_IMAGE_SRC1 = project_path_header + "/img/map_images/people.gif";         //人员报警图像
var DEFAULT_PEOPLE_IMAGE_SRC = project_path_header + "/img/map_images/people.png";		    //默认人员图像
var TRACK_ARROW_IMAGE_SRC = project_path_header + "/img/map_images/track_arrow1.gif";	//跟踪箭头图像


/**
 * 加载百度地图
 */
function loadScript() {  
//    var script = document.createElement("script");  
//    script.src = "http://api.map.baidu.com/api?v=1.4&callback=BaiduInitialize";  
//    document.body.appendChild(script);  
	BaiduInitialize(116.3975,39.9493,5);
 }

function BaiduInitialize(east,north,level) {
	m_position_map = new BMap.Map('map'); // 创建Map实例
	m_position_map.centerAndZoom(new BMap.Point(east,north),level);//初始化时，即可设置中心点和地图缩放级别。  
	m_position_map.enableScrollWheelZoom();//启动鼠标滚轮缩放地图
	//map.enableKeyboard();//启动键盘操作地图
	m_position_map.addControl(new BMap.NavigationControl());
	loadSpeedPeople();
}
/*
//百度坐标转换
function translate(key){
	alert(m_position_point.get(key).lng+"");
	BMap.Convertor.translate(m_position_point.get(key),0,translateCallback); 
}
function translateCallback(point){
	alert(point.lng+","+point.lat);
}
*/
function clearAllMap(){
	m_position_point.clear();
	m_position_marker_info.clear();
	m_position_map.clearOverlays();
	m_position_marker.clear();
	m_speed_people_map.clear();
}
//加载快速定位的人的位置信息
function loadSpeedPeopleLocation(){
	
	$.each(m_speed_people_map, function(key, values) {

		var userid = key;  //用户id
		//获取定位值;
		var east_lontitude = values.lontitude; //东经
		var north_latitude= values.latitude;//北纬
		var locType = values.locType;           //网络类型
		var entryTime = values.entryTime;  //时间
		var departmentname = values.departmentname;  //部门
		var username = values.username;  //人名
		var everytime = values.everytime;  //更新时间
		
		//创建坐标
		var point = new BMap.Point(east_lontitude,north_latitude);
		//将坐标存在map
		m_position_point.put(key,point);
		
//		if (m_position_marker.containsKey(key)){
//			m_position_map.removeOverlay(m_position_marker.get(key));
//		}
//		
//		// 创建图标对象
//		var myIcon = new BMap.Icon("http://localhost:8080/travel_tracker/img/map_images/people.png", new BMap.Size(40, 40), {
//			anchor: new BMap.Size(24, 72)
//		});
		// 创建标注对象并添加到地图
		var marker = new BMap.Marker(point);
//		var marker = new BMap.Marker(point, {icon: myIcon});
		
		//添加人员图元
		m_position_map.addOverlay(marker);
		//将人员图元存在map
		m_position_marker.put(key, marker);
		
	    //为图元添加弹窗信息
		var sContent = "<table>"+
	    	"<tr><td>所属部门：</td><td>"+departmentname+"</td></tr>" +
	    	"<tr><td>人员姓名：</td><td>"+username+"</td></tr>" +
	    	"<tr><td>定位模式：</td><td>"+locType+"</td></tr>" +
	    	"<tr><td>上传时间：</td><td>"+entryTime+"</td></tr>" +
	    	"<tr><td>更新频率：</td><td>"+everytime+"</td></tr>" +
	    	"</table>";
	    
		 //为图元添加弹窗信息
	    var infoWindow = new BMap.InfoWindow(sContent); 
	    //将窗口信息存在map
	    m_position_marker_info.put(key, infoWindow);
	   
	    marker.addEventListener('click', function(e){
	        //alert(e.point.lng + ', ' + e.point.lat);
	        this.openInfoWindow(infoWindow); // 打开信息窗口
	    });
	    
	});

}

//定位人员
function positionPeople(key){
	if (m_is_tracking) {
		m_position_map.zoomTo(m_position_map.getZoom() + 2);
		m_position_map.panTo(m_position_point.get(key));
		if (m_position_marker.containsKey("track_arrow_feature")) {
			m_position_map.removeOverlay(m_position_marker.get("track_arrow_feature"));
		}
		addMarker("track_arrow_feature",m_position_point.get(key));
    }	
}

//编写自定义函数，创建标注
function addMarker(key,point){
	// 创建图标对象
	var myIcon = new BMap.Icon(TRACK_ARROW_IMAGE_SRC, new BMap.Size(48, 48), {
		// 指定定位位置。
		// 当标注显示在地图上时，其所指向的地理位置距离图标左上
		// 角各偏移10像素和25像素。您可以看到在本例中该位置即是
		// 图标中央下端的尖角位置。
//		offset: new BMap.Size(0, 0),
		anchor: new BMap.Size(24, 72),
		// 设置图片偏移。
		// 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
		// 需要指定大图的偏移位置，此做法与css sprites技术类似。
//		imageOffset: new BMap.Size(10, 10) // 设置图片偏移
	});
	// 创建标注对象并添加到地图
	var marker = new BMap.Marker(point, {icon: myIcon});
	m_position_map.addOverlay(marker);
	//将人员图元存在map
	m_position_marker.put(key, marker);
}



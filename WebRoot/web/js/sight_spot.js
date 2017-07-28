/** 地图全局变量*/
var m_position_map;
var global_marker;


/**
 * 加载谷歌地图
 */
function GoogleInitialize() {
	if (GBrowserIsCompatible()) {
		var options = {draggableCursor:"crosshair",draggingCursor:"move"};
		m_position_map = new GMap2(document.getElementById("container"),options);
		m_position_map.setCenter(new GLatLng(38.9041, 116.4073),7);
		m_position_map.setMapType(G_NORMAL_MAP);
		//禁止鼠标双击缩放
		m_position_map.disableDoubleClickZoom();
	}
}

//定位
function placePosition(){
	SelectedResults();
	
	if(arr[0].split(";")[1] == "undefined"){
		alert("请选择机构地址所在省");
		return false;
	}
	else if(arr[1].split(";")[1] == "undefined"){
		alert("请选择机构地址所在市");
		return false;
	}
	else if(arr[2].split(";")[1] == "--县/区/县级地区--"){
		alert("请选择机构地址所在旗县");
		return false;
	}
	
	var address = $.trim(arr[0].split(";")[1] + arr[1].split(";")[1] + arr[2].split(";")[1] + $(".textarea").val());
	var geocoder = new GClientGeocoder();
	geocoder.getLatLng(address,function(point){
		if (!point){
			address = arr[0].split(";")[1] + arr[1].split(";")[1] + arr[2].split(";")[1];
			geocoder.getLatLng(address,function(point){
				var lat=point.lat();
				var lng=point.lng();
				$("#googleLat").val(lat);
				$("#googleLng").val(lng);
				var myicon = new GIcon();
				var marker = new GMarker(point,{icon:myicon,draggable: true});
				GEvent.addListener(marker, 'dragend',function(event){
					$("#googleLat").val(marker.getPoint().lat());
					$("#googleLng").val(marker.getPoint().lng());
				});
				m_position_map.clearOverlays()
				m_position_map.addOverlay(marker);
				m_position_map.setCenter(point, 12);
			});
			
		}
		else{
			var lat=point.lat();
			var lng=point.lng();
			$("#googleLat").val(lat);
			$("#googleLng").val(lng);
			var myicon = new GIcon();
			var marker = new GMarker(point,{icon:myicon,draggable: true});
			GEvent.addListener(marker, 'dragend',function(event){
				$("#googleLat").val(marker.getPoint().lat());
				$("#googleLng").val(marker.getPoint().lng());
			});
			m_position_map.clearOverlays()
			m_position_map.addOverlay(marker);
			m_position_map.setCenter(point, 12);
		}
	});
	
}



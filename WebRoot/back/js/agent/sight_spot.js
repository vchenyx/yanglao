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
function placePosition(address){
	
	var geocoder = new GClientGeocoder();
	geocoder.getLatLng(address,function(point){
		if (!point){
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
				m_position_map.clearOverlays();
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
			m_position_map.clearOverlays();
			m_position_map.addOverlay(marker);
			m_position_map.setCenter(point, 12);
		}
	});
	
}



/**
 * 该文件主要用于控制svg文档上的图形或文字； /*包括 添加、删除、移动等
 */

/**
 * 创建一个新的图形
 * 
 * @param shape
 * @returns
 */
function createsvgele(shape) {
	var new_shape;
	switch (browser_type) {
	case "IE":
		// new_shape = svgDoc.createElement(shape);

		// IE11支持该种方式
		new_shape = document.createElementNS('http://www.w3.org/2000/svg',
				shape);
		break;
	default:
		// IE不支持此方法
		new_shape = document.createElementNS('http://www.w3.org/2000/svg',
				shape);
		break;
	}
	return new_shape;
}

/**
 * 创建一个group
 * 
 * @param id
 * @param x
 * @param y
 * @param width
 * @param height
 * @param name
 * @param style
 * @returns
 */
function create_group(id, x, y, width, height, name, style) {
	var group = createsvgele('g');
	group.setAttribute("id", id);
	group.setAttribute("name", name);
	group.setAttribute("x", x);
	group.setAttribute("y", y);
	group.setAttribute("width", width);
	group.setAttribute("height", height);
	group.setAttribute("style", style);
	group.setAttribute("filter", "none");
	return group;
}

/**
 * 创建一个矩形
 * 
 * @param id
 * @param x
 * @param y
 * @param width
 * @param height
 * @param name
 * @param style
 * @returns
 */
function create_rect(id, x, y, width, height, name, style) {
	var rect = createsvgele('rect');
	rect.setAttribute("id", id);
	rect.setAttribute("name", name);
	rect.setAttribute("x", x);
	rect.setAttribute("y", y);
	rect.setAttribute("width", width);
	rect.setAttribute("height", height);
	rect.setAttribute("style", style);
	return rect;
}

/**
 * 创建一条线
 * 
 * @param id
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param name
 * @param style
 * @returns
 */
function create_line(id, x1, y1, x2, y2, name, style) {
	var line = createsvgele('line');
	line.setAttribute("id", id);
	line.setAttribute("name", name);
	line.setAttribute("x1", x1);
	line.setAttribute("y1", y1);
	line.setAttribute("x2", x2);
	line.setAttribute("y2", y2);
	line.setAttribute("style", style);
	return line;
}

/**
 * 创建一个文本
 * 
 * @param id
 * @param x
 * @param y
 * @param text_content
 * @param name
 * @param style
 * @returns
 */
function create_text(id, x, y, text_content, name, style) {
	var text = createsvgele('text');
	text.setAttribute("id", id);
	text.setAttribute("name", name);
	text.setAttribute("x", x);
	text.setAttribute("y", y);
	text.setAttribute("style", style);
	var tNode = svgDoc.createTextNode(text_content);
	text.appendChild(tNode);
	return text;
}

/**
 * 创建一个图像
 * 
 * @param id
 * @param x
 * @param y
 * @param width
 * @param height
 * @param image_src
 *            图像相对于svg文件的位置
 * @param name
 * @param style
 * @returns
 */
function create_image(id, x, y, width, height, image_src, name, style) {
	var image = createsvgele('image');
	image.setAttribute("id", id);
	image.setAttribute("name", name);
	image.setAttribute("x", x);
	image.setAttribute("y", y);
	image.setAttribute("width", width);
	image.setAttribute("height", height);
	image.setAttribute("style", style);
	image.setAttributeNS("http://www.w3.org/1999/xlink", "href", image_src);
	return image;
}

/**
 * 显示被定位人员/标签的tips
 * 
 * @param {}
 *            evt
 */
function show_detail_msg(evt) {
	// 定位标签的id
	var tag_mac = evt.target.id;
	var tips;
	var head_image_src = ""; // 头像地址
	var name, gender, people_id = "";
	var current_pos_str = "";
	if (m_people_map.containsKey(tag_mac)) {
		head_image_src = m_people_map.get(tag_mac).olderBaseInfo.peopleImage;
		if (("" == head_image_src) || (null == head_image_src)) {
			head_image_src = DEFAULT_HEAD_IMAGE_SRC;
		} else {
			head_image_src = PEOPLE_HEAD_IMAGE_SRC_HEADER + head_image_src;
		}
		name = m_people_map.get(tag_mac).olderBaseInfo.peopleName;
		gender = m_people_map.get(tag_mac).olderBaseInfo.gender;
		people_id = m_people_map.get(tag_mac).olderBaseInfo.peopleId;
		people_type = m_people_map.get(tag_mac).olderBaseInfo.peopleType;
		remarks = m_people_map.get(tag_mac).olderBaseInfo.remarks;//护理建议
		current_pos_str = "("
				+ m_people_map.get(tag_mac).tagBean.positionBean.x_pos
						.toFixed(2)
				+ ","
				+ m_people_map.get(tag_mac).tagBean.positionBean.y_pos
						.toFixed(2)
				+ ","
				+ m_people_map.get(tag_mac).tagBean.positionBean.z_pos
						.toFixed(2) + ")";
		tips = "<div class='divWindowHead'><table><tr><td valign='top'>"
				+ "<img src='" + head_image_src
				+ "' border='1' alt='' align='left' width=50 height=50/></td>"
				+ "<td valign='top'><div>" + "姓名：" + name + "</br>" + "性别："
				+ gender + "</br>" + "标签号：" + tag_mac + "</br>" + "位置："
				+ current_pos_str + "</div></td>"
				+ "</tr><tr><td valign='top' colspan='2'><div>" + people_type+":"+remarks
				+ "</div></td></tr>" + "</table></div>";
	} else if (m_worker_map.containsKey(tag_mac)) {
		head_image_src = m_worker_map.get(tag_mac).workerBaseInfo.peopleImage;
		if (("" == head_image_src) || (null == head_image_src)) {
			head_image_src = DEFAULT_HEAD_IMAGE_SRC;
		} else {
			head_image_src = PEOPLE_HEAD_IMAGE_SRC_HEADER + head_image_src;
		}
		name = m_worker_map.get(tag_mac).workerBaseInfo.peopleName;
		gender = m_worker_map.get(tag_mac).workerBaseInfo.gender;
		people_id = m_worker_map.get(tag_mac).workerBaseInfo.peopleId;
		dept = m_worker_map.get(tag_mac).workerBaseInfo.firstDept;
		duty = m_worker_map.get(tag_mac).workerBaseInfo.duty;
		current_pos_str = "("
				+ m_worker_map.get(tag_mac).tagBean.positionBean.x_pos
						.toFixed(2)
				+ ","
				+ m_worker_map.get(tag_mac).tagBean.positionBean.y_pos
						.toFixed(2)
				+ ","
				+ m_worker_map.get(tag_mac).tagBean.positionBean.z_pos
						.toFixed(2) + ")";
		tips = "<div class='divWindowHead'><table><tr><td valign='top'>"
				+ "<img src='" + head_image_src
				+ "' border='1' alt='' align='left' width=50 height=50/></td>"
				+ "<td valign='top'><div>" + "姓名：" + name + "</br>" + "性别："
				+ gender + "</br>" + "标签号：" + tag_mac + "</br>" + "位置："
				+ current_pos_str + "</div></td>"
				+ "</tr><tr><td valign='top' colspan='2'><div>"
				+ dept+":"+duty + "</div></td></tr>" + "</table></div>";
	}

	layer.tips(tips, this, {
		style : [ 'background-color:#B2DFEE; color:#fff', '#78BA32' ],
		guide : 1,
		isGuide : false,
		maxWidth : 300,
		time : 2
	});

	// 更新页面iframe中人员详细信息
	if ("Chrome" == browser_type || "Firefox" == browser_type) {
		document.getElementById('people_detail_iframe').contentWindow.document
				.getElementById('people_detail_head_image').src = head_image_src;
		document.getElementById('people_detail_iframe').contentWindow.document
				.getElementById('people_detail_id').textContent = people_id;
		document.getElementById('people_detail_iframe').contentWindow.document
				.getElementById('people_detail_name').textContent = name;
		document.getElementById('people_detail_iframe').contentWindow.document
				.getElementById('people_detail_gender').textContent = gender;
		document.getElementById('people_detail_iframe').contentWindow.document
				.getElementById('people_detail_tag_mac').textContent = tag_mac;
		document.getElementById('people_detail_iframe').contentWindow.document
				.getElementById('people_detail_pos').textContent = current_pos_str;
	} else {
		(window.frames['people_detail_iframe']).document
				.getElementById('people_detail_head_image').src = head_image_src;
		(window.frames['people_detail_iframe']).document
				.getElementById('people_detail_id').innerText = people_id;
		(window.frames['people_detail_iframe']).document
				.getElementById('people_detail_name').innerText = name;
		(window.frames['people_detail_iframe']).document
				.getElementById('people_detail_gender').innerText = gender;
		(window.frames['people_detail_iframe']).document
				.getElementById('people_detail_tag_mac').innerText = tag_mac;
		(window.frames['people_detail_iframe']).document
				.getElementById('people_detail_pos').innerText = current_pos_str;
	}

}

/**
 * 刷新一个标签图元的位置
 * 
 * @param {}
 *            parent 图元的父group
 * @param {}
 *            id 唯一ID tag的mac
 * @param {}
 *            x x轴位置
 * @param {}
 *            y y轴位置
 * @param {}
 *            image_src
 * @param {}
 *            name 图元名称
 * @param {}
 *            style
 */
function refresh_tag_feature(parent, id, x, y, image_src, name, style) {
	// 不存在则添加
	if (svgRoot.getElementById(id) == null) {
		var newFeature = create_image(id, x, y, DEFAULT_IMAGE_WIDTH,
				DEFAULT_IMAGE_HEIGHT, image_src, name, style);
		// 添加鼠标滑过的tips
		newFeature.addEventListener("mousemove", show_detail_msg);
		parent.appendChild(newFeature);
	} else { // 本身存在则刷新位置
		var oldFeature = svgRoot.getElementById(id);
		oldFeature.setAttribute("x", x);
		oldFeature.setAttribute("y", y);
		oldFeature.setAttributeNS("http://www.w3.org/1999/xlink", "href",
				image_src);
	}

	// 当前有定位标签被跟踪，则刷新跟踪箭头的位置
	if (m_is_tracking) {
		refresh_track_arrow();
	}
}

/**
 * 通过指示箭头实时跟踪在地图上的某定位标签
 * 
 * @param {}
 *            tag_mac 待跟踪的定位标签的mac
 */
function real_time_tracking(tag_mac) {
	// 若当前地图上有标签正在被跟踪则停止跟踪
	stop_tracking();

	var is_existed = false; // 地图上是否存在某定位标签
	if (m_people_map.containsKey(tag_mac) || m_worker_map.containsKey(tag_mac)) {
		is_existed = true;
	}

	// 地图上不存在某定位标签的信息
	if (!is_existed) {
		layer.alert('当前地图上没有该定位卡的位置信息！');
	} else {
		var tag_feature = svgRoot.getElementById(tag_mac);
		var x_pos = tag_feature.getAttribute("x");
		var y_pos = tag_feature.getAttribute("y");

		var track_arrow = create_image("track_arrow_feature", x_pos
				- DEFAULT_TRACK_ARROW_X_SHIFT, y_pos
				- DEFAULT_TRACK_ARROW_Y_SHIFT, DEFAULT_IMAGE_WIDTH * 3,
				DEFAULT_IMAGE_HEIGHT * 3, TRACK_ARROW_IMAGE_SRC,
				"track_arrow_feature", "");
		track_arrow_group.appendChild(track_arrow);

		m_is_tracking = true;
		m_tracked_tag_mac = tag_mac;
	}
}

/**
 * 停止当前地图上对某标签的定位跟踪
 */
function stop_tracking() {
	m_is_tracking = false;
	var track_arrow = svgRoot.getElementById("track_arrow_feature");
	if (track_arrow != null) {
		track_arrow_group.removeChild(track_arrow);
	}
}

/**
 * 刷新地图上跟踪箭头的位置
 */
function refresh_track_arrow() {
	if (m_is_tracking) {
		var tag_feature = svgRoot.getElementById(m_tracked_tag_mac);
		if (tag_feature != null) {
			var x_pos = tag_feature.getAttribute("x");
			var y_pos = tag_feature.getAttribute("y");
			var track_arrow = svgRoot.getElementById("track_arrow_feature");
			if (track_arrow != null) {
				track_arrow.setAttribute("x", x_pos
						- DEFAULT_TRACK_ARROW_X_SHIFT);
				track_arrow.setAttribute("y", y_pos
						- DEFAULT_TRACK_ARROW_Y_SHIFT);
			}
		}
	}
}

/**
 * 画指定region_id的区域围栏
 * 
 * @param {}
 *            region_id
 */
function draw_single_region_rail(region_id) {
	if (m_region_map.containsKey(region_id)) {
		var regionBean = m_region_map.get(region_id);
		var line_style = "stroke:red; stroke-width:1.25";
		var line1 = create_line("", regionBean.a_left_map,
				regionBean.b_left_map, regionBean.a_bottom_map,
				regionBean.b_bottom_map, "", line_style);
		var line2 = create_line("", regionBean.a_bottom_map,
				regionBean.b_bottom_map, regionBean.a_right_map,
				regionBean.b_right_map, "", line_style);
		var line3 = create_line("", regionBean.a_right_map,
				regionBean.b_right_map, regionBean.a_top_map,
				regionBean.b_top_map, "", line_style);
		var line4 = create_line("", regionBean.a_top_map, regionBean.b_top_map,
				regionBean.a_left_map, regionBean.b_left_map, "", line_style);
		rail_group.appendChild(line1);
		rail_group.appendChild(line2);
		rail_group.appendChild(line3);
		rail_group.appendChild(line4);
	}
}

/**
 * 清理围栏线条
 */
function clear_rail_line() {
	var length = rail_group.childNodes.length;
	while (length != 0) {
		rail_group.removeChild(rail_group.lastChild);
		length = rail_group.childNodes.length;
	}
}

/**
 * 判断当前点是否在某个区域围栏里， 如果在则画出该围栏
 * 
 * @param {}
 *            current_point
 */
function get_current_region(current_point) {
	var key_set = m_region_map.keySet();
	for ( var index in key_set) {
		var regionBean = m_region_map.get(key_set[index]);
		var left_point = {
			x : regionBean.a_left_map,
			y : regionBean.b_left_map
		};
		var bottom_point = {
			x : regionBean.a_bottom_map,
			y : regionBean.b_bottom_map
		};
		var right_point = {
			x : regionBean.a_right_map,
			y : regionBean.b_right_map
		};
		var top_point = {
			x : regionBean.a_top_map,
			y : regionBean.b_top_map
		};
		var vertexs = new Array(left_point, bottom_point, right_point,
				top_point);
		if (point_in_figure(current_point, vertexs)) {
			draw_single_region_rail(key_set[index]);
			break;
		}
	}
}

/**
 * 判断点是否在多边形里 var current_point = {x:x_value, y:y_value};
 * 
 * @param {}
 *            current_point 需要判断的点
 * @param {}
 *            vertexs 多边形顶点坐标
 * @return {}
 */
function point_in_figure(current_point, vertexs) {
	// 任意四边形有4个顶点
	var nCount = vertexs.length;
	var figurePoints = new Array();
	for ( var i = 0; i < nCount; i++) {
		figurePoints[i] = vertexs[i];
	}
	var nCross = 0;
	for ( var i = 0; i < nCount; i++) {
		// 依次取相邻的两个点
		var pStart = figurePoints[i];
		var pEnd = figurePoints[(i + 1) % nCount];

		// 相邻的两个点是平行于x轴的，肯定不相交，忽略
		if (pStart.y == pEnd.y)
			continue;

		// 交点在pStart,pEnd的延长线上，pCur肯定不会与pStart.pEnd相交，忽略
		if (current_point.y < Math.min(pStart.y, pEnd.y)
				|| current_point.y > Math.max(pStart.y, pEnd.y))
			continue;

		// 求当前点和x轴的平行线与pStart,pEnd直线的交点的x坐标
		var x = (current_point.y - pStart.y) * (pEnd.x - pStart.x)
				/ (pEnd.y - pStart.y) + pStart.x;

		// 若x坐标大于当前点的坐标，则有交点
		if (x > current_point.x)
			nCross++;
	}

	// 单边交点为偶数，点在多边形之外
	return (nCross % 2 == 1);
}
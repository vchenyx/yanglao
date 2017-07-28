package com.jinkun.main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.common.service.RedisService;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.PensionPeopleInfo;
import com.jinkun.main.beans.UnderpinAssist;
import com.jinkun.main.service.CallCenterService;

@Controller
@RequestMapping("/call")
public class CallCenterController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CallCenterService callCenterService;
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/getInfoByPhone.do")
	@ResponseBody
	public JSONObject getInfoByPhone(String phone, HttpSession session) {
		String subWhere = "";
		int userType = Integer.parseInt(session.getAttribute("userType").toString());
		if (userType == 51) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			subWhere += " and stationId = " + pension.getId();
		}
		if (userType == 510 || userType == 520) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			if (userType == 510) {
				subWhere += " and stationId = " + pp.getStationId();
			} else {
				subWhere += " and pensionId = " + pp.getPensionId();
			}
		}
		List<MOldPeopleInfo> list = commonService.findByHql(MOldPeopleInfo.class, " phone like '%" + phone + "%' " + subWhere, "");
		if (list.isEmpty()) {
			return null;
		} else {
			MOldPeopleInfo assist = list.get(0);
			JSONObject json = new JSONObject();
			json.put("id", assist.getId());
			json.put("name", assist.getName());
			json.put("sex", assist.getSex());
			json.put("age", assist.getBirthday());
			json.put("maritalStatus", assist.getYanglao());
			json.put("income", assist.getPayType());
			json.put("idCard", assist.getIdCard());
			json.put("address", assist.getCommunityName() + assist.getAddress());
			json.put("linkName", assist.getLinkName());
			json.put("linkPhone", assist.getLinkPhone());
			return json;
		}
	}
	
	/**
	 * 坐席获取所有站点信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllStation.do")
	@ResponseBody
	public List<MPensionStationInfo> getAllStation(HttpSession session) {
		PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
		String subWhere = " pid = " + pp.getPensionId() + " and stationType = 51";
		List<MPensionStationInfo> list = commonService.findByHql(MPensionStationInfo.class, subWhere, "");
		return list;
	}
}

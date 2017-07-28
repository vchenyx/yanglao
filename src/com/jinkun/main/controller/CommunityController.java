package com.jinkun.main.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.jinkun.main.beans.MCommunityInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.PensionPeopleInfo;

@Controller
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/saveCommunity.do")
	@ResponseBody
	public String saveCommunity(MCommunityInfo community, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		community.setCreateTime(new Date().getTime());
		community.setPensionId(pension.getPid());
		community.setStationId(pension.getId());
		commonService.saveInfo(community);
		return "{\"rtn\": 1}";
	}
	
	@RequestMapping("/getCommunityList.do")
	@ResponseBody
	public List<MCommunityInfo> getCommunityList(HttpSession session) {
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		String subWhere = "";
		if (userType == 5 || userType == 51 || userType == 52) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				subWhere += " pensionId = " + pension.getId();
			}
			if (userType == 51) {
				subWhere += " stationId = " + pension.getId();
			}
			if (userType == 52) {
				subWhere += " pensionId = " + pension.getPid();
			}
		}
		if (userType == 510 || userType == 520) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			if (userType == 510) {
				subWhere += " stationId = " + pp.getStationId();
			}
			if (userType == 520) {
				subWhere += " pensionId = " + pp.getPensionId();
			}
		}
		
		return commonService.findByHql(MCommunityInfo.class, subWhere, " createTime desc");
	}
	
	@RequestMapping("/deleteCommunity.do")
	@ResponseBody
	public String deleteCommunity(MCommunityInfo community) {
		commonService.deleteData(MCommunityInfo.class, " id = " + community.getId());
		return "{\"rtn\": 1}";
	}
	
	@RequestMapping("/updateCommunity.do")
	@ResponseBody
	public String updateCommunity(MCommunityInfo community) {
		commonService.updateInfo(community);
		return "{\"rtn\": 1}";
	}
}

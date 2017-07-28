package com.jinkun.main.controller;

import java.util.Date;
import java.util.List;

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
import com.jinkun.main.service.OldInfoService;

@Controller
@RequestMapping("/oldInfo")
public class OldInfoController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private OldInfoService oldInfoService;
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/saveOldInfo.do")
	@ResponseBody
	public String saveOldInfo(MOldPeopleInfo old, HttpSession session) {
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		if (userType == 5 || userType == 51) {
			MPensionStationInfo ss  =  (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				old.setPensionId(ss.getId());
			}
			if (userType == 51) {
				old.setPensionId(ss.getPid());
				old.setStationId(ss.getId());
			}
		}
		if (userType == 510) {
			PensionPeopleInfo pp  =  (PensionPeopleInfo) session.getAttribute("user");
			old.setPensionId(pp.getPensionId());
			old.setStationId(pp.getStationId());
		}
		old.setCreateTime(new Date().getTime());
		oldInfoService.saveOldInfo(old);
		return "{\"rtn\": 1}";
	}
	
	
	
	@RequestMapping("/findByid")
	@ResponseBody
	public JSONObject findByid(HttpSession session,Integer id) {
		JSONObject json = new JSONObject();
		MOldPeopleInfo findByIntegerId = (MOldPeopleInfo) commonService.findByIntegerId(MOldPeopleInfo.class, id);
		json.put("list", findByIntegerId);
		return json;
	}
	
	

	/**
	 * 站点、部门获取老人信息列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/stationGetAllOlder.do")
	@ResponseBody
	public List<MOldPeopleInfo> stationGetAllOlder(HttpSession session) {
		int userType = Integer.parseInt(session.getAttribute("userType").toString());
		Object sessionUser = session.getAttribute("user");
		Integer id = ((MPensionStationInfo) sessionUser).getId();
		List<MOldPeopleInfo> oldList = null;
		if (userType == 5) {
			String pensionIds = "";
			List<MPensionStationInfo> pensionList = commonService.findByHql(MPensionStationInfo.class, " pid = " + id, "");
			if (!pensionList.isEmpty()) {
				for (MPensionStationInfo mPensionStationInfo : pensionList) {
					pensionIds += ", " + mPensionStationInfo.getId();
				}
			}
			if (pensionIds.length() > 0) {
				pensionIds = " (" + pensionIds.substring(1) + ") ";
				oldList = commonService.findByHql(MOldPeopleInfo.class, " pensionId in " + pensionIds, "createTime desc");
			}
		}
		if (userType == 51) {
			oldList = commonService.findByHql(MOldPeopleInfo.class, " pensionId = " + id, "createTime desc");
		}
		return oldList;
	}
	
	/**
	 * 坐席或机构人员获取老人列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/peopleGetAllOlder")
	@ResponseBody
	public List<MOldPeopleInfo> peopleGetAllOlder(HttpSession session) {
		String userType = session.getAttribute("userType").toString();
		if (userType != null && userType.contains("51")) { // 站点人员 查本站点
			
		}
		if (userType != null && userType.contains("52")) { // 客服部人员 查所有
			
		}
		if (userType != null && userType.contains("50")) { // 机构直属人员 查所有
			
		}
		return null;
	}
	
	@RequestMapping("updateOldInfo.do")
	@ResponseBody
	public String updateOldInfo(MOldPeopleInfo old, HttpSession session) {
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		if (userType == 5 || userType == 51) {
			MPensionStationInfo ss  =  (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				old.setPensionId(ss.getId());
			}
			if (userType == 51) {
				old.setPensionId(ss.getPid());
				old.setStationId(ss.getId());
			}
		}
		if (userType == 510) {
			PensionPeopleInfo pp  =  (PensionPeopleInfo) session.getAttribute("user");
			old.setPensionId(pp.getPensionId());
			old.setStationId(pp.getStationId());
		}
		oldInfoService.updateOldInfo(old);
		return "{\"rtn\": 1}";
	}
	
}

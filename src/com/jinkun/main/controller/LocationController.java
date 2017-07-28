/*package com.jinkun.main.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.common.service.RedisService;
import com.common.util.JSONUtil;
import com.common.util.global.DefalutRedisKey;
import com.jinkun.main.beans.LLocationLastInfo;
import com.jinkun.main.beans.MGuardianInfo;
import com.jinkun.main.beans.MNurseInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.form.PosiShowInfo;

@Controller
@RequestMapping("/location")
public class LocationController {

	@Autowired
	private CommonService commonService;
	@Autowired
	private RedisService redisService;
	
	*//**
	 * 查询机构内所有老人和护工位置信息
	 *//*
	@RequestMapping("/getAllPosi.do")
	@ResponseBody
	public List<PosiShowInfo> getAllPosi(HttpSession session) {
		MPensionStationInfo pensionStation = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pensionStation.getId();
		List<MOldPeopleInfo> oldList = commonService.findByHql(MOldPeopleInfo.class, " pensionStationId = " + id, "");
		List<MNurseInfo> nurseList = commonService.findByHql(MNurseInfo.class, " pensionStationId = " + id, "");
		List<PosiShowInfo> posiList = new ArrayList<PosiShowInfo>();
		Map<String, String> map = redisService.getHash(DefalutRedisKey.peoplePosiKey);
		if (!oldList.isEmpty()) {
			for (MOldPeopleInfo old : oldList) {//循环老人
				PosiShowInfo posiInfo = new PosiShowInfo();
				if (map.containsKey(old.getUserId())) {
					LLocationLastInfo lastLoca = JSONUtil.strToBean(map.get(old.getUserId()), LLocationLastInfo.class);
					MGuardianInfo guardian = (MGuardianInfo) commonService.findByHqlUnique(MGuardianInfo.class, " phone = '" + old.getGuardianPhone() + "' ");
					posiInfo.setId(old.getUserId());
					posiInfo.setName(old.getName());
					if (guardian != null) {
						posiInfo.setGuardianName(guardian.getName());
						posiInfo.setGuardianPhone(guardian.getPhone());
					}
					posiInfo.setAge(old.getAge());
					posiInfo.setIdCard(old.getIdCard());;
					posiInfo.setSex(old.getSex());
					posiInfo.setPhone(old.getPhone());
					posiInfo.setAddress(old.getAddress());
					posiInfo.setHeadImg(old.getHeadImg());
					posiInfo.setPensionName(pensionStation.getName());
					posiInfo.setLatGps(lastLoca.getLatGps());
					posiInfo.setLatGG(lastLoca.getLatGG());
					posiInfo.setLonGps(lastLoca.getLonGps());
					posiInfo.setLonGG(lastLoca.getLonGG());
					posiInfo.setNetType(lastLoca.getNetType());
					posiInfo.setPosiType(lastLoca.getPosiType());
					posiInfo.setPosiTime(new Date(lastLoca.getPosiTime()));
					posiList.add(posiInfo);
				}
			}
		}
		if (!nurseList.isEmpty()) {
			for (MNurseInfo nurse : nurseList) {
				PosiShowInfo posiInfo = new PosiShowInfo();
				if (map.containsKey(nurse.getUserId())) {
					LLocationLastInfo lastLoca = JSONUtil.strToBean(map.get(nurse.getUserId()), LLocationLastInfo.class);
					posiInfo.setId(nurse.getUserId());
					posiInfo.setSex(nurse.getSex());
					posiInfo.setAge(nurse.getAge());
					posiInfo.setIdCard(nurse.getIdCard());
					posiInfo.setName(nurse.getName());
					posiInfo.setPhone(nurse.getPhone());
					posiInfo.setAddress(nurse.getAddress());
					posiInfo.setHeadImg(nurse.getHeadImg());
					posiInfo.setPensionName(pensionStation.getName());
					posiInfo.setLatGps(lastLoca.getLatGps());
					posiInfo.setLatGG(lastLoca.getLatGG());
					posiInfo.setLonGps(lastLoca.getLonGps());
					posiInfo.setLonGG(lastLoca.getLonGG());
					posiInfo.setNetType(lastLoca.getNetType());
					posiInfo.setPosiType(lastLoca.getPosiType());
					posiInfo.setPosiTime(new Date(lastLoca.getPosiTime()));
					posiList.add(posiInfo);
				}
			}
		}
		return posiList;
	}
	
}
*/
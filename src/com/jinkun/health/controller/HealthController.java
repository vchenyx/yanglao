package com.jinkun.health.controller;

import java.util.List;

import javax.xml.ws.RespectBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.common.util.DateUtil;
import com.jinkun.health.beans.UAnimalHeatInfo;
import com.jinkun.health.beans.UBloodInfo;
import com.jinkun.health.beans.UBloodOxygenInfo;
import com.jinkun.health.beans.UBloodPressureInfo;
import com.jinkun.health.beans.UBodyElementInfo;
import com.jinkun.health.beans.UECGInfo;
import com.jinkun.health.beans.UPissElementInfo;
import com.jinkun.health.form.GetHealthArgs;
import com.jinkun.main.beans.MOldPeopleInfo;

@Controller
@RequestMapping("/health")
public class HealthController {

	@Autowired
	private CommonService commonService;
	
	/**
	 * 获取老人基本信息
	 * @param oldId
	 * @return
	 */
	@RequestMapping(value="/getOldInfo.do", method = RequestMethod.GET)
	@ResponseBody
	public MOldPeopleInfo getOldInfo(Integer oldId) {
		return (MOldPeopleInfo) commonService.findByIntegerId(MOldPeopleInfo.class, oldId);
	}
	
	/**
	 * 数据展示
	 */
	@RequestMapping(value="/getAnimalHeat.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Object> getAnimalHeat(GetHealthArgs args) {
		int oldId = args.getOldId();
		int dateFlag = args.getDateFlag();
		int typeFlag = args.getTypeFlag();
		Long startTime = 0l;
		Long endTime = 0l;
		if (dateFlag == 0) {
			startTime = DateUtil.getStartTimeDay();
			endTime = DateUtil.getEndTimeDay();
		}
		if (dateFlag == 1) {
			startTime = DateUtil.getStartTimeWeek();
			endTime = DateUtil.getEndTimeWeek();
		}
		if (dateFlag == 2) {
			startTime = DateUtil.getStartTimeMonth();
			endTime = DateUtil.getEndTimeMonth();
		}
		if (dateFlag == 3) {
			startTime = DateUtil.strToLong(args.getStartTime());
			endTime = DateUtil.strToLong(args.getEndTime());
		}
		String subWhere = " oldId = " + oldId 
				+ " and measureDate >= " + startTime 
				+ " and measureDate <= " + endTime;
		Class clazz = null;
		if (typeFlag == 0) {
			clazz = UBloodPressureInfo.class;
		}
		if (typeFlag == 1) {
			clazz = UBloodOxygenInfo.class;
		}
		if (typeFlag == 2) {
			clazz = UECGInfo.class;
		}
		if (typeFlag == 3) {
			clazz = UBloodInfo.class;
		}
		if (typeFlag == 4) {
			clazz = UBodyElementInfo.class;
		}
		if (typeFlag == 5) {
			clazz = UPissElementInfo.class;
		}
		if (typeFlag == 6) {
			clazz = UAnimalHeatInfo.class;
		}
		List<Object> list = commonService.findByHql(clazz, subWhere, "");
		return list;
	}
	
}

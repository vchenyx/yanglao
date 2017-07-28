/*package com.jinkun.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.jinkun.main.beans.MGuardianInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.form.OldInfo;
import com.jinkun.main.service.GuradianService;

@Controller
@RequestMapping("/guradian")
public class GuradianController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private GuradianService guradianService;
	
	@RequestMapping("/getPensionByAreaId.do")
	@ResponseBody
	public List<MPensionStationInfo> getPensionByAreaId(Integer areaId) {
		List<MPensionStationInfo> list = commonService.findByHql(MPensionStationInfo.class, " areaId = " + areaId, "");
		return list;
	}
	
	@RequestMapping("/getAllOld.do")
	@ResponseBody
	public List<OldInfo> getAllOld(HttpSession session) {
		MGuardianInfo gura = (MGuardianInfo) session.getAttribute("user");
		List<MOldPeopleInfo> list = commonService.findByHql(MOldPeopleInfo.class, " guardianId = " + gura.getId(), "");
		List<OldInfo> oldList = new ArrayList<OldInfo>();
		if (!list.isEmpty()) {
			for (MOldPeopleInfo mold : list) {
				OldInfo old = new OldInfo();
				old.setId(mold.getId());
				old.setUserId(mold.getUserId());//用户ID
				old.setName(mold.getName());
				old.setSex(mold.getSex());
				old.setAge(mold.getAge());
				old.setPhone(mold.getPhone());
				old.setAddress(mold.getAddress());
				old.setIdCard(mold.getIdCard());
				old.setIllness(mold.getIllness());//疾病
				old.setGuardianName(gura.getName());
				old.setPensionStationId(mold.getPensionStationId());//养老机构ID
				if (mold.getPensionStationId() != null) {
					MPensionStationInfo pension = (MPensionStationInfo) commonService.findByIntegerId(MPensionStationInfo.class, mold.getPensionStationId());
					if (pension != null) {
						old.setPensionStationName(pension.getName());
					}
				}
				old.setHeadImg(mold.getHeadImg());
				old.setEduBackground(mold.getEduBackground());
				oldList.add(old);
			}
		}
		return oldList;
	}
	
	@RequestMapping("/bindPensionStation.do")
	@ResponseBody
	public String bindPensionStation(Integer oldId, Integer pensionId) {
		guradianService.updateOldPension(oldId, pensionId);
		return "{\"flag\": 1}";
	}
	
	@RequestMapping("/updateOldInfo.do")
	@ResponseBody
	public String updateOldInfo(MOldPeopleInfo old) {
		guradianService.updateOldInfo(old);
		return "{\"flag\": 1}";
	}
	
}
*/
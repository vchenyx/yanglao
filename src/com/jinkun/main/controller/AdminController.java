package com.jinkun.main.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.common.util.MD5;
import com.common.util.global.ConfigureFile;
import com.jinkun.main.beans.AAuditStationInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 查询所有待审核养老机构
	 * @return
	 */
	@RequestMapping("/getPensionStationRequest.do")
	@ResponseBody
	public List<MPensionStationInfo> getPensionStationRequest() {
		String subWhere = " state = 1 ";
		String orderBy = " requestTime asc ";
		List<MPensionStationInfo> list = commonService.findByHql(MPensionStationInfo.class, subWhere, orderBy);
		return list;
	}
	
	/**
	 * 养老机构审核通过
	 * @param pensionStation
	 * @return
	 */
	@RequestMapping("/ratifyPensionStation.do")
	@ResponseBody
	public String ratifyPensionStation(MPensionStationInfo pensionStation, Integer isPass, String msg) {
		if (isPass == 1) {//通过
			Integer areaId = pensionStation.getAreaId();
			String subWhere = " areaId = " + areaId + " and userType = 5";
			String orderby = "createTime desc";
			List<MUserInfo> userList = commonService.findByHql(MUserInfo.class, subWhere, orderby);
			String account = "PS" + areaId;
			if (userList.isEmpty()) {
				account += "001";
			} else {
				String lastAccount = userList.get(0).getAccount();
				String suffix = lastAccount.substring(8);
				int intSuffix = Integer.parseInt(suffix) + 1;
				if (intSuffix < 10) {
					account += "00" + intSuffix;
				} else if (intSuffix >= 10 && intSuffix < 100) {
					account += "0" + intSuffix;
				} else {
					account += intSuffix;
				}
			}
			MUserInfo userInfo = new MUserInfo();
			userInfo.setAccount(account);
			userInfo.setAreaId(areaId);
			userInfo.setPwd(MD5.md5Encode(ConfigureFile.default_pwd));
			userInfo.setCreateTime(new Date());
			userInfo.setUserType(5);
			String id = adminService.saveUserInfo(userInfo);
			AAuditStationInfo auditStation = new AAuditStationInfo();
			auditStation.setIsPass(isPass);
			auditStation.setCreateTime(new Date());
			auditStation.setStationId(pensionStation.getId());
			auditStation.setStationType(5);
			pensionStation.setState(2);
			adminService.saveAuditStationInfo(auditStation);
			pensionStation.setUserId(id);
			adminService.updatePensionStation(pensionStation);
			return "{\"flag\": 1, \"account\": " + account + "}";
		} else {//未通过
			pensionStation.setState(3);
			adminService.updatePensionStation(pensionStation);
			AAuditStationInfo auditStation = new AAuditStationInfo();
			auditStation.setIsPass(isPass);
			auditStation.setCreateTime(new Date());
			auditStation.setStationId(pensionStation.getId());
			auditStation.setStationType(5);
			auditStation.setMsg(msg);
			adminService.saveAuditStationInfo(auditStation);
			return "{\"flag\": 0}";
		}
	}
	
}

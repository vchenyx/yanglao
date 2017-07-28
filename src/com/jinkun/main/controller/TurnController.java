package com.jinkun.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.service.RedisService;
import com.common.util.JSONUtil;
import com.common.util.global.DefalutRedisKey;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.form.LoginUser;

@Controller
@RequestMapping("turn")
public class TurnController {

	@Autowired
	private RedisService redisService;

	/**
	 * admin--审核
	 * 
	 * @return
	 */
	@RequestMapping("/adminAudit.do")
	public String adminAudit() {
		return "admin/adminAudit";
	}

	/**
	 * 登录成功跳转页面
	 * 
	 * @param flag
	 * @return
	 */
	@RequestMapping("/loginSuccess.do")
	public ModelAndView loginSuccess(Integer flag) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index/main");
		mv.addObject("userFlag", flag);
		return mv;
	}

	/**
	 * 主页面引入内容
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/chooseMain.do")
	public String chooseMain(HttpSession session) {
		String hash = redisService.getHash(DefalutRedisKey.loginKey, session.getId());
		LoginUser loginUser = JSONUtil.strToBean(hash, LoginUser.class);
		Integer userType = loginUser.getUserType();
		if (userType == 9) {// admin
			return "admin/adminAudit";
		}
		if (userType == 5) {//机构   下属所有站点、部门信息， 人员信息， 老人信息，  添加供应商, 所有工单、投诉单增删改查
			return "pension/homePage";
		}
		if (userType == 51) {//站点   本站点所有人员增删改查， 老人档案增删改查， 工单增删改查， 投诉单增删改查 station
			return "station/stationPoint";
		}
		if (userType == 52) {//客服部 呼叫中心
			return "station/customerService";
		}
		if (userType == 510) {//站点人员 同站点
			return "station/stationPoint";
		}
		if (userType == 520) {//客服人员 呼叫中心
			return "agent/homePage";
		}
		if (userType == 1) {// 监护人
			return "static/guardianIndex";
		}
		if (userType == 6) {// 物业
			return "static/viewRoom";
		}
		if (userType == 7) {// 医疗
			return "static/medicalIndex";
		}
		if (userType == 8) {// 政府
			return "static/allOlderInfo";
		}
		if (userType == 10) {// 服务商
			return "static/serviceProvider";
		}
		if (userType == 3) {// 医生
			return "static/doctorIndex";
		}
		return "";
	}

	/**
	 * 机构--地图定位
	 * 
	 * @return
	 */
	@RequestMapping("/mapInDoor.do")
	public String mapInDoor() {
		return "pension/mapInDoor";
	}

	/**
	 * 机构--人员管理--护工管理
	 * 
	 * @return
	 */
	@RequestMapping("/pensionNurseManager.do")
	public String pensionNurseManager() {
		return "pension/nurseManager";
	}

	/**
	 * 机构--老人健康--老人管理
	 * 
	 * @return
	 */
	@RequestMapping("/pensionOldManager.do")
	public String pensionOldManager() {
		return "pension/oldManager";
	}

	/**
	 * 机构--报警管理--推送消息
	 * 
	 * @return
	 */
	@RequestMapping("/pushMSGPage.do")
	public String pushMSGPage() {
		return "pension/pushMSG";
	}

	/**
	 * 机构--报警管理--信息管理
	 * 
	 * @return
	 */
	@RequestMapping("/alarmManager.do")
	public String alarmManager() {
		return "pension/alarmManager";
	}

	/**
	 * 机构--床位监测--床位监测
	 * 
	 * @return
	 */
	@RequestMapping("/oldBed.do")
	public String oldBed() {
		return "pension/oldBed";
	}


	/**
	 * 机构--机构管理--设备管理
	 * 
	 * @return
	 */
	@RequestMapping("/deviceManager.do")
	public String deviceManager() {
		return "pension/deviceManager";
	}

	/**
	 * 机构--老人管理--健康页
	 * 
	 * @return
	 */
	@RequestMapping("/toHealth.do")
	public String toHealth(Integer id, Model model) {
		model.addAttribute("oldId", id);
		return "health/home";
	}

	/**
	 * 机构--老人管理--健康数据
	 * 
	 * @return
	 */
	@RequestMapping("/healthData.do")
	public String healthData(Integer id, Model model) {
		model.addAttribute("oldId", id);
		return "health/healthData";
	}

	/**
	 * 机构--系统字典--分类管理
	 * 
	 * @return
	 */
	@RequestMapping("/dictionaryManager.do")
	public String dictionaryManager() {
		return "pension/dictionaryManager";
	}

	/**
	 * 监护人--信息管理--老人管理
	 * 
	 * @return
	 */
	@RequestMapping("/guradianOldManager.do")
	public String guradianOldManager() {
		return "guardian/oldManager";
	}

	/**
	 * 服务商--服务商管理
	 * 
	 * @return
	 */
	@RequestMapping("/facilitatorOldManage.do")
	public String facilitatorOldManage() {
		return "pension/fwProvider";
	}

	/**
	 * 服务商--商品分类管理
	 * 
	 * @return
	 */
	@RequestMapping("/facilitatorClassManage.do")
	public String facilitatorClassManage() {
		return "pension/fwProviderManage";
	}

	/**
	 * 养老服务--日间照料
	 * 
	 * @return
	 */
	@RequestMapping("/dayCare.do")
	public String dayCare() {
		return "pension/rjDayCare";
	}

	@RequestMapping("/callAlert.do")
	public String callAlert() {
		return "agent/callAlert";
	}


	/**
	 * 人员管理--人员信息
	 * 
	 * @return
	 */
	@RequestMapping("/ToUserManager.do")
	public String ToUserManager() {
		return "pension/userManager";
	}

	/**
	 * 人员管理--人员类型
	 * 
	 * @return
	 */
	@RequestMapping("/ToPeopleDept.do")
	public String ToPeopleDept() {
		return "pension/peopleDept";
	}

	/**
	 * 投诉管理--待处理投诉
	 * 
	 * @return
	 */
	@RequestMapping("/ToComplaint.do")
	public String ToComplaint() {
		return "pension/complaintManage";
	}

	@RequestMapping("/ToReturnVisit.do")
	public String ToReturnVisit() {
		return "pension/returnVisit";
	}

	@RequestMapping("/ToComplain.do")
	public String ToComplain() {
		return "pension/complain";
	}
	
	@RequestMapping("alertModal.do")
	public String alertModal() {
		return "pension/openTab";
	}
	@RequestMapping("ToDepartment.do")
	public String department() {
		return "pension/department";
	}

	
	 /** 
		 * 知识库--知识库信息
		 * @return
		 */
		@RequestMapping("/knowledgeBase.do")
		public String knowledgeBase(){
			return "pension/zsKnowledgeBase";
		}
		 /** 
		 * 知识库--知识库信息
		 * @return
		 */
		@RequestMapping("/knowledgeBaseInfo.do")
		public String knowledgeBaseInfo(){
			return "pension/zsKnowledgeBaseInfo";
		}

	@RequestMapping("/orderManager.do")
	public String orderManager() {
		return "order/orderManager";
	}
	
	
	
	//==static==========================================================================================
	
	@RequestMapping("/bindDoctor.do")
	public String bindDoctor() {
		return "static/bindDoctor";
	}
	@RequestMapping("/bindMedical.do")
	public String bindMedical() {
		return "static/bindMedical";
	}
	@RequestMapping("/olderShenHe.do")
	public String olderShenHe() {
		return "static/olderShenHe";
	}
	@RequestMapping("/medicalDoctorInfo.do")
	public String medicalDoctorInfo() {
		return "static/medicalDoctorInfo";
	}
	@RequestMapping("/medicalOlderInfo.do")
	public String medicalOlderInfo() {
		return "static/medicalOlderInfo";
	}
	@RequestMapping("/oldChart.do")
	public String oldChart() {
		return "static/oldChart";
	}
	@RequestMapping("/roomAlarmInfo.do")
	public String roomAlarmInfo() {
		return "static/roomAlarmInfo";
	}
	@RequestMapping("/roomAlarmDeal.do")
	public String roomAlarmDeal() {
		return "static/roomAlarmDeal";
	}
	@RequestMapping("/serviceProviderManage.do")
	public String serviceProviderManage() {
		return "static/serviceProviderManage";
	}
	
	
	/** 
	 * 老人--托底扶助
	 * @return
	 */
	@RequestMapping("/PaimHelep.do")
	public String PaimHelep(){
		return "pension/PaimHelep";
	}
	/** 
	 * 老人--托底扶助
	 * @return
	 */
	@RequestMapping("/gatewayManager.do")
	public String gatewayManager(){
		return "pension/gatewayManager";
	}
	
	/**
	 * 安防设备
	 */
	@RequestMapping("/deviceTypeManager.do")
	public String deviceTypeManager(){
		return "pension/deviceTypeManager";
	}
	/**
	 * 安防设备
	 */
	@RequestMapping("/serviceRecord.do")
	public String serviceRecord(){
		return "pension/serviceRecord";
	}
	/**
	 * 安防设备
	 */
	@RequestMapping("/turnCallHome.do")
	public String turnCallHome(){
		return "agent/homePage";
	}
	
	@RequestMapping("/communityManage.do")
	public String communityManage() {
		return "pension/communityManage";
	}
}


package com.jinkun.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.common.util.JSONUtil;
import com.common.util.PageUtil;
import com.jinkun.main.beans.AAlarmInfo;
import com.jinkun.main.beans.MNurseInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.PensionPeopleInfo;
import com.jinkun.main.form.OldInfo;
import com.jinkun.main.service.PensionStationService;

@Controller
@RequestMapping("/pension")
public class PensionStationController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private PensionStationService pensionStationService;
	
	@RequestMapping("/getAllOndCount.do")
	@ResponseBody
	public JSONObject getAllOndCount(MOldPeopleInfo old, HttpSession session,HttpServletRequest request){
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		String subWhere = "";
		
		
		if (userType == 5 || userType == 51 || userType == 52) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				subWhere = " pensionId = " + pension.getId();
			}
			if (userType == 51) {
				subWhere = " stationId = " + pension.getId();
			}
			if (userType == 52) {
				subWhere = " pensionId = " + pension.getPid();
			}
		}
		if (userType == 510 || userType == 520) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			if (userType == 510) {
				subWhere = " stationId = " + pp.getStationId();
			}
			if (userType == 520) {
				subWhere = " pensionId = " + pp.getPensionId();
			}
		}
		
		if (old.getName() != null && !"".equals(old.getName())) {
			subWhere += " and name = '" + old.getName() + "'"; 
		}
		if (old.getPhone() != null && !"".equals(old.getPhone())) {
			subWhere += " and phone = '" + old.getPhone() + "'"; 
		}
		if (old.getIdCard() != null && !"".equals(old.getIdCard())) {
			subWhere += " and idCard = '" + old.getIdCard() + "'"; 
		}
		
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		long records = commonService.getResultCount(MOldPeopleInfo.class, subWhere);
		long pageCount = PageUtil.getPageCount(records, pagesize);
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		json.put("totalCount", records);
		return json;
	}
	
	@RequestMapping("/getAllOldList.do")
	@ResponseBody
	public List<MOldPeopleInfo> getAllOldList(MOldPeopleInfo old, HttpSession session,HttpServletRequest request){
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		String subWhere = "";
		if (userType == 5 || userType == 51 || userType == 52) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				subWhere = " pensionId = " + pension.getId();
			}
			if (userType == 51) {
				subWhere = " stationId = " + pension.getId();
			}
			if (userType == 52) {
				subWhere = " pensionId = " + pension.getPid();
			}
		}
		if (userType == 510 || userType == 520) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			if (userType == 510) {
				subWhere = " stationId = " + pp.getStationId();
			}
			if (userType == 520) {
				subWhere = " pensionId = " + pp.getPensionId();
			}
		}
		
		if (old.getName() != null && !"".equals(old.getName())) {
			subWhere += " and name = '" + old.getName() + "'"; 
		}
		if (old.getPhone() != null && !"".equals(old.getPhone())) {
			subWhere += " and phone = '" + old.getPhone() + "'"; 
		}
		if (old.getIdCard() != null && !"".equals(old.getIdCard())) {
			subWhere += " and idCard = '" + old.getIdCard() + "'"; 
		}
		
		Integer currentpage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		return commonService.findByHqlPage(MOldPeopleInfo.class, subWhere, "createTime desc", currentpage, pagesize);
	}
	
	/**
	 * 获取所有老人信息
	 * @param session
	 * @return
	 *//*
	@RequestMapping("/getAllOldList.do")
	@ResponseBody
	public List<OldInfo> getAllOldList(HttpSession session) {
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		if (userType == 5 || userType == 51) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				return commonService.findByHql(MOldPeopleInfo.class, " pensionId = " + pension.getId(), "");
			}
			if (userType == 51) {
				return commonService.findByHql(MOldPeopleInfo.class, " stationId = " + pension.getId(), "");
			}
		}
		if (userType == 510) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			return commonService.findByHql(MOldPeopleInfo.class, " stationId = " + pp.getStationId(), "");
		}
		return null;
	}*/
	
	/**
	 * 获取所有护工信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllNurseList.do")
	@ResponseBody
	public List<MNurseInfo> getAllNurseList(HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		List<MNurseInfo> list = commonService.findByHql(MNurseInfo.class, " pensionStationId = " + id, "");
		return list;
	}
	
	/**
	 * 获取所有告警信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllAlarm.do")
	@ResponseBody
	public List<AAlarmInfo> getAllAlarm(HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		List<AAlarmInfo> list = commonService.findByHql(AAlarmInfo.class, " belongPension = " + id, " uploadTime desc");
		return list;
	}
	
	/**
	 * 删除护工
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteNurse.do")
	@ResponseBody
	public String deleteNurse(Integer id) {
		pensionStationService.deleteNurse(id);
		return "{\"flag\":1}";
	}
	/**
	 * 删除老人
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteOlder.do")
	@ResponseBody
	public String deleteNurse(MOldPeopleInfo old) {
		pensionStationService.deleteOlder(old);
		return "{\"flag\":1}";
	}
	
	/**
	 * 老人绑定设备
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping("/bindDevice.do")
	@ResponseBody
	public String bindDevice(String jsonStr) {
		MOldPeopleInfo old = JSONUtil.strToBean(jsonStr, MOldPeopleInfo.class);
		pensionStationService.updateOldBindDevice(old);
		return "{\"flag\": 1}";
	}
	
	/**
	 * 获取所有离床报警器
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllBedDevice.do")
	@ResponseBody
	public List<MOldPeopleInfo> getAllBedDevice(HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		String subWhere = " pensionStationId = " + id + " and bedDeviceNo <> null";
		List<MOldPeopleInfo> list = commonService.findByHql(MOldPeopleInfo.class, subWhere, "");
		return list;
	}
	
	
	
	
	
	
	
	/*@RequestMapping("/getAllOldListCount.do")
	@ResponseBody
	public JSONObject getAllOldListCount(HttpServletRequest request, HttpSession session) {
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		List<MOldPeopleInfo> list = commonService.findByHql(MOldPeopleInfo.class, " pensionStationId = " + id, "");
		return list;
	}
	
	@RequestMapping("/getAllNurseListCount.do")
	@ResponseBody
	public JSONObject getAllNurseListCount(HttpServletRequest request, HttpSession session) {
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		List<MNurseInfo> list = commonService.findByHql(MNurseInfo.class, " pensionStationId = " + id, "");
		return list;
	}*/
}

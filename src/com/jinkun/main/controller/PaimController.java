package com.jinkun.main.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.common.util.MD5;
import com.common.util.PageUtil;
import com.common.util.global.ConfigureFile;
import com.jinkun.main.beans.CServiceRecords;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.beans.PensionPeopleInfo;
import com.jinkun.main.beans.UnderpinAssist;

@Controller
@RequestMapping("/paim")
public class PaimController {
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/getAllPaimCount.do")
	@ResponseBody
	public JSONObject getAllPaimCount(UnderpinAssist assit, HttpSession session,HttpServletRequest request) {
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		String subWhere = "";
		if (assit.getName() != null && !"".equals(assit.getName())) {
			subWhere += " and name = '" + assit.getName() + "'";
		}
		if (assit.getAge() != null && assit.getAge() > 0) {
			subWhere += " and age = " + assit.getAge();
		}
		if (assit.getSex() != null) {
			subWhere += " and sex = " + assit.getSex();
		}
		if (assit.getManagerClass() != null) {
			subWhere += " and managerClass = " + assit.getManagerClass();
		}
		if (assit.getPhone() != null && !"".equals(assit.getPhone())) {
			subWhere += " and phone like '%" + assit.getPhone() + "%'";
		}
		if (assit.getCommunitys() != null && !"".equals(assit.getCommunitys())) {
			subWhere += " and communitys like '%" + assit.getCommunitys() + "%'";
		}
		if (assit.getMaritalStatus() != null && !"".equals(assit.getMaritalStatus())) {
			subWhere += " and maritalStatus like '%" + assit.getMaritalStatus() + "%'";
		}
		if (assit.getIncome() != null && !"".equals(assit.getIncome())) {
			subWhere += " and income like '%" + assit.getIncome() + "%'";
		}
		
		long totalCount = 0;
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		if (userType == 5 || userType == 51 || userType == 52) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				totalCount =  commonService.getResultCount(UnderpinAssist.class, " pensionId = " + pension.getId() + subWhere);
			}
			if (userType == 51) {
				totalCount =  commonService.getResultCount(UnderpinAssist.class, " stationId = " + pension.getId() + subWhere);
			}
			if (userType == 52) {
				totalCount =  commonService.getResultCount(UnderpinAssist.class, " pensionId = " + pension.getPid() + subWhere);
			}
		}
		if (userType == 510 || userType == 520) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			if (userType == 510) {
				totalCount =  commonService.getResultCount(UnderpinAssist.class, " stationId = " + pp.getStationId() + subWhere);
			}
			if (userType == 520) {
				totalCount =  commonService.getResultCount(UnderpinAssist.class, " pensionId = " + pp.getPensionId() + subWhere);
			}
		}
		long pageCount = PageUtil.getPageCount(totalCount, pagesize);
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		json.put("totalCount", totalCount);
		return json;
	}
	
	/**
	 * 获取托底扶助信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllPaim.do")
	@ResponseBody
	public List<UnderpinAssist> getPensionByAreaId(UnderpinAssist assit, HttpSession session, HttpServletRequest request) {
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		
		String subWhere = "";
		if (assit.getName() != null && !"".equals(assit.getName())) {
			subWhere += " and name = '" + assit.getName() + "'";
		}
		if (assit.getAge() != null && assit.getAge() > 0) {
			subWhere += " and age = " + assit.getAge();
		}
		if (assit.getSex() != null) {
			subWhere += " and sex = " + assit.getSex();
		}
		if (assit.getManagerClass() != null) {
			subWhere += " and managerClass = " + assit.getManagerClass();
		}
		if (assit.getPhone() != null && !"".equals(assit.getPhone())) {
			subWhere += " and phone like '%" + assit.getPhone() + "%'";
		}
		if (assit.getCommunitys() != null && !"".equals(assit.getCommunitys())) {
			subWhere += " and communitys like '%" + assit.getCommunitys() + "%'";
		}
		if (assit.getMaritalStatus() != null && !"".equals(assit.getMaritalStatus())) {
			subWhere += " and maritalStatus like '%" + assit.getMaritalStatus() + "%'";
		}
		if (assit.getIncome() != null && !"".equals(assit.getIncome())) {
			subWhere += " and income like '%" + assit.getIncome() + "%'";
		}
		
		Integer currentpage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		if (userType == 5 || userType == 51 || userType == 52) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				return commonService.findByHqlPage(UnderpinAssist.class, " pensionId = " + pension.getId() + subWhere, " createTime desc", currentpage, pagesize);
			}
			if (userType == 51) {
				return commonService.findByHqlPage(UnderpinAssist.class, " stationId = " + pension.getId() + subWhere, " createTime desc", currentpage, pagesize);
			}
			if (userType == 52) {
				return commonService.findByHqlPage(UnderpinAssist.class, " pensionId = " + pension.getPid() + subWhere, " createTime desc", currentpage, pagesize);
			}
		}
		if (userType == 510 || userType == 520) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			if (userType == 510) {
				return commonService.findByHqlPage(UnderpinAssist.class, " stationId = " + pp.getStationId() + subWhere, " createTime desc", currentpage, pagesize);
			}
			if (userType == 520) {
				return commonService.findByHqlPage(UnderpinAssist.class, " pensionId = " + pp.getPensionId() + subWhere, " createTime desc", currentpage, pagesize);
			}
		}
		return null;
	}
	
	@RequestMapping("/deleteUser.do")
	@ResponseBody
	public JSONObject deleteUser(HttpSession session,Integer id) {
		
		UnderpinAssist findByIntegerId =(UnderpinAssist) commonService.findByIntegerId(UnderpinAssist.class, id);
		Integer muserId = findByIntegerId.getId();
		if(muserId !=null){
			commonService.deleteData(UnderpinAssist.class, "id ='"+id+"'");
		}
		JSONObject json = new JSONObject();
		commonService.deleteData(UnderpinAssist.class, "id ="+id);
		return json;
	}
	
	/**
	 * 新增
	 * @param id
	 * @return
	 */
	@RequestMapping("addPaim.do")
	@ResponseBody
	public String addPaim(UnderpinAssist assist, Integer flag, String pwd,HttpSession session) {
		
		int pensionId = 0;
		int stationId = 0;
		
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		if (userType == 51) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			pensionId = pension.getPid();
			stationId = pension.getId();
		}
		if (userType == 510) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			pensionId = pp.getPensionId();
			stationId = pp.getStationId();
		}
		
		assist.setCreateTime(new Date().getTime());
		assist.setPensionId(pensionId);
		assist.setStationId(stationId);
		commonService.saveInfo(assist);
		return "{\"rtn\": 1}";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/findById.do")
	@ResponseBody
	public JSONObject findById(HttpSession session,Integer id) throws Exception {
		JSONObject json = new JSONObject();
		List<UnderpinAssist> findByHql = commonService.findByHql(UnderpinAssist.class, "id = "+id, null);
		json.put("list", findByHql.get(0));
		return json;
	}
	
	@RequestMapping("/shouServiceRecords.do") 
	@ResponseBody
	public List<CServiceRecords> shouServiceRecords(String name, String phone, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		String subWhere = " stationId = " + pension.getId();
		subWhere += " and name = '" + name + "'";
		return commonService.findByHql(CServiceRecords.class, subWhere, " serviceDate desc");
	}
	
	
}

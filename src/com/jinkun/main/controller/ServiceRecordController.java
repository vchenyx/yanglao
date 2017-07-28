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
import com.common.util.PageUtil;
import com.jinkun.main.beans.CServiceRecords;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.PensionPeopleInfo;

@Controller
@RequestMapping("/serviceRecord")
public class ServiceRecordController {

	@Autowired
	private CommonService commonService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getUserList.do")
	@ResponseBody
	public List<CServiceRecords> getUserList(String name, String cellphone,String community, String serviceAddress, HttpSession session,HttpServletRequest request){
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
		
		if (name != null && !"".equals(name)) {
			subWhere += " and name = '" + name + "'"; 
		}
		if (cellphone != null && !"".equals(cellphone)) {
			subWhere += " and phone like '%" + cellphone + "%'"; 
		}
		if (community != null && !"".equals(community)) {
			subWhere += " and community = '" + community + "'"; 
		}
		if (serviceAddress != null && !"".equals(serviceAddress)) {
			subWhere += " and serviceAddress = '" + serviceAddress + "'"; 
		}
		
		Integer currentpage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		return commonService.findByHqlPage(CServiceRecords.class, subWhere, " serviceDate desc", currentpage, pagesize);
	}
	/*
	 * 查询人员总数
	 * */
	@RequestMapping("/GetUserCountID.do")
	@ResponseBody
	public JSONObject selsectUserCountID(String name, String cellphone, String community, String serviceAddress, HttpSession session,HttpServletRequest request){
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
		
		if (name != null && !"".equals(name)) {
			subWhere += " and name = '" + name + "'"; 
		}
		if (cellphone != null && !"".equals(cellphone)) {
			subWhere += " and phone like '%" + cellphone + "%'"; 
		}
		if (community != null && !"".equals(community)) {
			subWhere += " and community = '" + community + "'"; 
		}
		if (serviceAddress != null && !"".equals(serviceAddress)) {
			subWhere += " and serviceAddress = '" + serviceAddress + "'"; 
		}
		
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		long records = commonService.getResultCount(CServiceRecords.class, subWhere);
		long pageCount = PageUtil.getPageCount(records, pagesize);
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		json.put("totalCount", records);
		return json;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/findBycolumn.do")
	@ResponseBody
	public JSONObject findBycolumn(HttpSession session) throws Exception {
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
		JSONObject json = new JSONObject();
		List<MPensionStationInfo> findByHqlGetColumn = commonService.findByHqlGetColumn(MPensionStationInfo.class, subWhere, null, " id,name,stationType");
		json.put("list", findByHqlGetColumn);
		return json;
	}
	
}
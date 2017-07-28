package com.jinkun.fence.controller;

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
import com.jinkun.fence.beans.YSDeviceInfo;
import com.jinkun.fence.beans.YSDeviceType;
import com.jinkun.fence.beans.YSGatewayInfo;
import com.jinkun.main.beans.MPropertyInfo;

@Controller
@RequestMapping("/fenceDevice")
public class FenceDeviceController {

	@Autowired
	private CommonService commonService;
	
	/**
	 * 机构添加设备类型
	 * @param deviceType
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveDeviceType.do")
	@ResponseBody
	public String saveDeviceType(YSDeviceType deviceType, HttpSession session) {
		MPropertyInfo user = (MPropertyInfo) session.getAttribute("user");
		deviceType.setPropertyId(user.getId());
		commonService.saveInfo(deviceType);
		return "{\"rtn\": " + deviceType.getId() + "}";
	}
	
	/**
	 * 物业保存设备信息
	 * @param deviceInfo
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveDeviceInfo.do")
	@ResponseBody
	public String saveDeviceInfo(YSDeviceInfo deviceInfo, HttpSession session) {
		commonService.saveInfo(deviceInfo);
		return "{\"rtn\": " + deviceInfo.getId() + "}";
	}
	
	/**
	 * 物业保存网关信息
	 * @param gatewayInfo
	 * @param session
	 * @return
	 */
	@RequestMapping("savdGatewayInfo.do")
	@ResponseBody
	public String savdGatewayInfo(YSGatewayInfo gatewayInfo, HttpSession session) {
		MPropertyInfo user = (MPropertyInfo) session.getAttribute("user");
		gatewayInfo.setPropertyId(user.getId());
		commonService.saveInfo(gatewayInfo);
		return "{\"rtn\": " + gatewayInfo.getId() + "}";
	}
	
	@RequestMapping("/getAllGatewayCount.do")
	@ResponseBody
	public JSONObject getAllGatewayCount(HttpSession session,HttpServletRequest request){
		MPropertyInfo user = (MPropertyInfo) session.getAttribute("user");
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		String subWhere = " propertyId = " + user.getId();
		long records = commonService.getResultCount(YSGatewayInfo.class, subWhere);
		long pageCount = PageUtil.getPageCount(records, pagesize);
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		return json;
	}
	
	/**
	 * 物业查询所有网关列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllGatewayList.do")
	@ResponseBody
	public List<YSGatewayInfo> getAllGatewayList(HttpServletRequest request, HttpSession session) {
		MPropertyInfo user = (MPropertyInfo) session.getAttribute("user");
		Integer currentPage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pageSize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		String subWhere = " propertyId = " + user.getId();
		String orderBy = " createTime desc";
		return commonService.findByHqlPage(YSGatewayInfo.class, subWhere, orderBy, currentPage, pageSize);
	}
	
	@RequestMapping("/getAllDdeviceTypeCount.do")
	@ResponseBody
	public JSONObject getAllDdeviceTypeCount(HttpSession session,HttpServletRequest request){
		MPropertyInfo user = (MPropertyInfo) session.getAttribute("user");
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		String subWhere = " propertyId = " + user.getId();
		long records = commonService.getResultCount(YSDeviceType.class, subWhere);
		long pageCount = PageUtil.getPageCount(records, pagesize);
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		return json;
	}
	
	/**
	 * 查询所有设备类型列表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllDdeviceTypeList.do")
	@ResponseBody
	public List<YSDeviceType> getAllDdeviceTypeList(HttpServletRequest request, HttpSession session) {
		MPropertyInfo user = (MPropertyInfo) session.getAttribute("user");
		Integer currentPage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pageSize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		String subWhere = " propertyId = " + user.getId();
		String orderBy = " createTime desc";
		return commonService.findByHqlPage(YSDeviceType.class, subWhere, orderBy, currentPage, pageSize);
	}
}

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
import com.jinkun.main.beans.FComplaintInfo;
import com.jinkun.main.beans.MPensionStationInfo;
@Controller
@RequestMapping("/complain")
public class ComplainController {

	@Autowired
	private CommonService commonService;


	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getList.do")
	@ResponseBody
	public String getUserList(HttpSession session,HttpServletRequest request){
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		Integer currentpage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		String complaintPeople = request.getParameter("complaintPeople") == null?"":request.getParameter("complaintPeople").trim();
		String servicePeople = request.getParameter("servicePeople") == null?"":request.getParameter("servicePeople").trim();
		String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime").trim();
		String cellphone = request.getParameter("cellphone") == null?"":request.getParameter("cellphone").trim();
		String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime").trim();
		String subwhere =" 1=1";
			subwhere +=" and pensionId ="+id;
		if(!"".equals(complaintPeople)){
			subwhere += " and complaintPeople like '%"+complaintPeople+"%'";
		}
		if(!"".equals(cellphone)){
			subwhere += " and cellphone like '%"+cellphone+"'";
		}
		if(!"".equals(servicePeople)){
			subwhere += " and servicePeople like '%"+servicePeople+"'";
		}
		if(!"".equals(endTime) && !"".equals(beginTime)){
			subwhere += "and "+beginTime+" > "+endTime+" and "+endTime+"< "+beginTime+"";
		}
		List<FComplaintInfo> userList = commonService.findByHqlPage(FComplaintInfo.class, subwhere, "id desc", currentpage, pagesize);
		JSONObject json = new JSONObject();
		json.put("userList", userList);
		return json.toString();
	}
	@RequestMapping("/GetCountID.do")
	@ResponseBody
	public JSONObject selsectUserCountID(HttpSession session,HttpServletRequest request){
		
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		String complaintPeople = request.getParameter("complaintPeople") == null?"":request.getParameter("complaintPeople").trim();
		String servicePeople = request.getParameter("servicePeople") == null?"":request.getParameter("servicePeople").trim();
		String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime").trim();
		String cellphone = request.getParameter("cellphone") == null?"":request.getParameter("cellphone").trim();
		String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime").trim();
		String subwhere =" 1=1";
			subwhere +=" and pensionId ="+id;
		if(!"".equals(complaintPeople)){
			subwhere += " and complaintPeople like '%"+complaintPeople+"%'";
		}
		if(!"".equals(cellphone)){
			subwhere += " and cellphone like '%"+cellphone+"'";
		}
		if(!"".equals(servicePeople)){
			subwhere += " and servicePeople like '%"+servicePeople+"'";
		}
		if(!"".equals(endTime) && !"".equals(beginTime)){
			subwhere += "and "+beginTime+" > "+endTime+" and "+endTime+"< "+beginTime+"";
		}
		long records = commonService.getResultCount(FComplaintInfo.class, subwhere);
		long pageCount = PageUtil.getPageCount(records, pagesize);
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		return json;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/findById.do")
	@ResponseBody
	public JSONObject findById(HttpSession session,Integer id) throws Exception {
		JSONObject json = new JSONObject();
		List<FComplaintInfo> findByHql = commonService.findByHql(FComplaintInfo.class, "id = "+id, null);
		json.put("list", findByHql.get(0));
		return json;
	}
	
	
}

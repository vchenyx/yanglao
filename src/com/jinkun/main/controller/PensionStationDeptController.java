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
import com.common.util.PageUtil;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.PensionPeopleDeptInfo;
import com.jinkun.main.beans.PensionPeopleInfo;

@Controller
@RequestMapping("/pensionDept")
public class PensionStationDeptController {

	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/deletePeopleDept.do")
	@ResponseBody
	public JSONObject deletePeopleDept(HttpSession session,Integer id) {
		JSONObject json = new JSONObject();
		commonService.deleteData(PensionPeopleDeptInfo.class, "id ="+id);
		return json;
	}
	
	

	@RequestMapping("/addPeopleDept.do")
	@ResponseBody
	public JSONObject addPeopleDept(HttpSession session,String name, HttpServletRequest request) throws Exception {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer ids = pension.getId();
		JSONObject json = new JSONObject();
			try {
				List<PensionPeopleDeptInfo> findByHql = commonService.findByHql(PensionPeopleDeptInfo.class, " name ='"+name+"'", null);
				if(findByHql.size() >0 ){
					json.put("state", "exist");
					return json; 
				}
				PensionPeopleDeptInfo dept = new PensionPeopleDeptInfo();
				dept.setPensionId(ids);
				dept.setCreateTime(new Date().getTime());
				dept.setCreateUsr("");
				dept.setName(name);
				commonService.saveInfo(dept);
				
			} catch (Exception e) {
				// TODO: handle exception
				json.put("state", "error");
			}
			json.put("state", "success");
		return json;
	}
	
	@RequestMapping("/getPeopleDeptList.do")
	@ResponseBody
	public String getPeopleDeptList(HttpSession session,HttpServletRequest request){
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		String name = request.getParameter("name") == null?"":request.getParameter("name").trim();
		String subwhere =" 1=1";
		subwhere +=" and pensionId ="+id;
		if(!"".equals(name)){
			subwhere += " and (peopleName like '%"+name+"%' or username like '%"+name.toLowerCase()+"%') ";
		}
		List<PensionPeopleDeptInfo> findByHql = commonService.findByHql(PensionPeopleDeptInfo.class, subwhere, null);
		JSONObject json = new JSONObject();
		json.put("list", findByHql);
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/findByDept.do")
	@ResponseBody
	public JSONObject findByDept(HttpSession session) throws Exception {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		JSONObject json = new JSONObject();
		List<PensionPeopleDeptInfo> findByHql = commonService.findByHql(PensionPeopleDeptInfo.class, "pensionId ="+id, null);
		json.put("list", findByHql);
		return json;
	}
	
}

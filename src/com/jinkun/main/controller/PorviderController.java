package com.jinkun.main.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.jinkun.main.beans.FWProvider;
import com.jinkun.main.beans.FWProviderType;
import com.jinkun.main.beans.FWServiceItems;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.service.PensionStationService;

@Controller
@RequestMapping("/provider")
public class PorviderController {
	@Autowired
	private PensionStationService pensionStationService;

	@Autowired
	private CommonService commonService;

	/**
	 * 获取服务商分类信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllProviderType.do")
	@ResponseBody
	public List<FWProviderType> getAllProviderType(HttpSession session) {
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		String subWhere = "";
		if (userType == 5) {
			subWhere = " pensionId = " + pension.getId();
		}
		if (userType == 51) {
			subWhere = " stationId = " + pension.getId();
		}
		return commonService.findByHql(FWProviderType.class, subWhere, " createTime desc");
	}
	
	@RequestMapping("/getAllProvider.do")
	@ResponseBody
	public Map<String, Object> getAllProvider(HttpSession session) {
		List<FWProviderType> typeList = getAllProviderType(session);
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		Map<Integer, String> typeMap = new HashMap<Integer, String>();
		String ids = "";
		if (!typeList.isEmpty()) {
			for (FWProviderType fwProviderType : typeList) {
				typeMap.put(fwProviderType.getId(), fwProviderType.getName());
				ids += "," + fwProviderType.getId();
			}
		}
		String subWhere = "";
		if (!"".equals(ids)) {
			ids = ids.substring(1);
			subWhere = " typeId in (" + ids + ")";
			List providerList = commonService.findByHql(FWProvider.class, subWhere, " createTime desc");
			rtnMap.put("typeMap", typeMap);
			rtnMap.put("list", providerList);
			return rtnMap;
		}
		return null;
	}
	
	@RequestMapping("/getAllProviderByTypeId.do")
	@ResponseBody
	public List<FWProvider> getAllProviderByTypeId(Integer typeId) {
		return commonService.findByHql(FWProvider.class, " typeId = " + typeId, "createTime asc");
	}


	/**
	 * 删除服务商
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteProvider.do")
	@ResponseBody
	public String deleteProvider(FWProvider pro) {
		return "{\"flag\":1}";
	}

	/**
	 * 服务商分类管理删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteProviderClass.do")
	@ResponseBody
	public String deleteProviderClass(FWProviderType pType) {
		return "{\"flag\":1}";
	}

	/**
	 * 服务商类型新增
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("addProviderType.do")
	@ResponseBody
	public String addProviderType(FWProviderType pType, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer stationId = pension.getId();
		Integer pensionId = pension.getPid();
		pType.setStationId(stationId);
		pType.setPensionId(pensionId);
		pType.setCreateTime(new Date().getTime());
		commonService.saveInfo(pType);
		return "{\"rtn\": 1}";
	}

	@RequestMapping("/addProvider.do")
	@ResponseBody
	public String addProvider(FWProvider provider, HttpSession session) {
		provider.setCreateTime(new Date().getTime());
		provider.setState(1);
		provider.setGrade(5f);
		commonService.saveInfo(provider);
		return "{\"rtn\": 1}";
	}
	
	@RequestMapping("/addProviderItem.do")
	@ResponseBody
	public String addProviderItem(FWServiceItems item) {
		item.setState(1);
		item.setCreateTime(new Date().getTime());
		commonService.saveInfo(item);
		return "{\"rtn\": 1}";
	}
	
	@RequestMapping("/gatAllServiceItems.do")
	@ResponseBody
	public List<FWServiceItems> gatAllServiceItems(Integer providerId) {
		return commonService.findByHql(FWServiceItems.class, " providerId = " + providerId, " createTime desc");
	}
}

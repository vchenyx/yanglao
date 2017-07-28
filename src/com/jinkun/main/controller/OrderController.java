package com.jinkun.main.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.common.service.RedisService;
import com.common.util.DateUtil;
import com.common.util.PageUtil;
import com.jinkun.main.beans.CConsultInfo;
import com.jinkun.main.beans.CForwardRegistInfo;
import com.jinkun.main.beans.CPhoneSOSInfo;
import com.jinkun.main.beans.CServiceRecords;
import com.jinkun.main.beans.CWorkOrderInfo;
import com.jinkun.main.beans.FComplaintInfo;
import com.jinkun.main.beans.FWProvider;
import com.jinkun.main.beans.GDOrderItems;
import com.jinkun.main.beans.GDwork;
import com.jinkun.main.beans.MCommunityInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.PensionPeopleInfo;
import com.jinkun.main.form.OrderCountList;
import com.jinkun.main.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 获取已处理工单信息总数
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllOrderCount.do")
	@ResponseBody
	public JSONObject getDoneCount(GDwork order, Long startDate, Long endDate, HttpSession session,HttpServletRequest request) {
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		long totalCount = 0;
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		
		String subWhere = "";
		if (order.getName() != null && !"".equals(order.getName())) {
			subWhere += " and name = '" + order.getName() + "'";
		}
		if (order.getPhone() != null && !"".equals(order.getPhone())) {
			subWhere += " and phone like '%" + order.getPhone() + "%'";
		}
		
		if (startDate == null && endDate == null) {
			subWhere += " and dates = " + DateUtil.getTodayYMDLong();
		} else {
			if (startDate != null && startDate == endDate) {
				subWhere += " and dates = " + startDate;
			} else {
				if (startDate != null) {
					subWhere += " and dates >= " + startDate;
				}
				if (endDate != null) {
					subWhere += " and dates <= " + endDate;
				}
			}
		}
		
		
		if (userType == 5 || userType == 51 || userType == 52) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				totalCount =  commonService.getResultCount(GDwork.class, " pensionId = " + pension.getId() + subWhere);
			}
			if (userType == 51) {
				totalCount =  commonService.getResultCount(GDwork.class, " stationId = " + pension.getId() + subWhere);
			}
			if (userType == 52) {
				totalCount =  commonService.getResultCount(GDwork.class, " pensionId = " + pension.getPid() + subWhere);
			}
		}
		if (userType == 510 || userType == 520) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			if (userType == 510) {
				totalCount =  commonService.getResultCount(GDwork.class, " stationId = " + pp.getStationId() + subWhere);
			}
			if (userType == 520) {
				totalCount =  commonService.getResultCount(GDwork.class, " pensionId = " + pp.getPensionId() + subWhere);
			}
		}
		long pageCount = PageUtil.getPageCount(totalCount, pagesize);
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		json.put("totalCount", totalCount);
		return json;
	}
	
	/**
	 * 获取所有工单信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllList.do")
	@ResponseBody
	public List<GDwork> getAllList(GDwork order,Long startDate, Long endDate, HttpServletRequest request, HttpSession session) {
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		Integer currentpage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		String subWhere = "";
		if (order.getName() != null && !"".equals(order.getName())) {
			subWhere += " and name = '" + order.getName() + "'";
		}
		if (order.getPhone() != null && !"".equals(order.getPhone())) {
			subWhere += " and phone like '%" + order.getPhone() + "%'";
		}
		
		if (startDate == null && endDate == null) {
			subWhere += " and dates = " + DateUtil.getTodayYMDLong();
		} else {
			if (startDate != null && startDate == endDate) {
				subWhere += " and dates = " + startDate;
			} else {
				if (startDate != null) {
					subWhere += " and dates >= " + startDate;
				}
				if (endDate != null) {
					subWhere += " and dates <= " + endDate;
				}
			}
		}
		
		if (userType == 5 || userType == 51 || userType == 52) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				return commonService.findByHqlPage(GDwork.class, " orderState <> 2 and  pensionId = " + pension.getId() + subWhere, " dates asc", currentpage, pagesize);
			}
			if (userType == 51) {
				return commonService.findByHqlPage(GDwork.class, " orderState <> 2 and  stationId = " + pension.getId() + subWhere, " dates asc", currentpage, pagesize);
			}
			if (userType == 52) {
				return commonService.findByHqlPage(GDwork.class, " orderState <> 2 and  pensionId = " + pension.getPid() + subWhere, " dates asc", currentpage, pagesize);
			}
		}
		if (userType == 510 || userType == 520) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			if (userType == 510) {
				return commonService.findByHqlPage(GDwork.class, " orderState <> 2 and  stationId = " + pp.getStationId() + subWhere, " dates asc", currentpage, pagesize);
			}
			if (userType == 520) {
				return commonService.findByHqlPage(GDwork.class, " orderState <> 2 and  pensionId = " + pp.getPensionId() + subWhere, " dates asc", currentpage, pagesize);
			}
		}
		return null;
	}
	
	/**
	 * 保存服务工单
	 * @param order
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveOrder.do")
	@ResponseBody
	public String saveOrder(CWorkOrderInfo order, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		order.setPensionId(id);
		order.setCreateTime(new Date().getTime());
		orderService.saveOrder(order);
		return "{\"flag\": 1}";
	}
	
	@RequestMapping("/chuliOrder.do")
	@ResponseBody
	public String chuliOrder(String serviceType, Integer id, String startEndTime, String staffName, HttpSession session) {
		GDwork fbi = (GDwork)commonService.findByIntegerId(GDwork.class, id);
		fbi.setStartEndTime(startEndTime);
		fbi.setServiceType(serviceType);
		fbi.setStaffName(staffName);
		fbi.setOrderState(1);
		commonService.updateInfo(fbi);
		return "{\"flag\": 1}";
	}
	
	@RequestMapping("/huifangOrder.do")
	@ResponseBody
	public String huifangOrder(Integer id,Integer standard ,Integer state,String opinion, HttpSession session) {
		Integer stationId = null;
		Integer pensionId = null;
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		if (userType == 51) {
			MPensionStationInfo pen = (MPensionStationInfo) session.getAttribute("user");
			stationId = pen.getId();
			pensionId = pen.getPid();
		}
		
		if (userType == 510) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			stationId = pp.getStationId();
			pensionId = pp.getPensionId();
		}
		
		GDwork findByIntegerId = (GDwork)commonService.findByIntegerId(GDwork.class, id);
		findByIntegerId.setStandard(standard);
		findByIntegerId.setState(state);
		findByIntegerId.setOpinion(opinion);
		findByIntegerId.setOrderState(2);
		commonService.updateInfo(findByIntegerId);
		try {
			List<MOldPeopleInfo> findByHql = commonService.findByHql(MOldPeopleInfo.class, "phone ='"+findByIntegerId.getPhone()+"'", null);
			if(findByHql.size() > 0 ){
				MOldPeopleInfo ss = findByHql.get(0);
				CServiceRecords csr = new  CServiceRecords();
				csr.setAge(DateUtil.getAgeByLong(ss.getBirthday()));
				csr.setName(ss.getName());
				csr.setSex(ss.getSex());
				csr.setPhone(ss.getPhone());
				csr.setCommunity(ss.getAddress());
				csr.setOldType(ss.getOldType());
				csr.setStartEndTime(findByIntegerId.getStartEndTime());
				csr.setServiceAddress(findByIntegerId.getAddress());
				csr.setServiceContent(findByIntegerId.getContent());
				csr.setServiceDate(findByIntegerId.getDates());
				csr.setRemarks(opinion);
				csr.setProcuderName(findByIntegerId.getProviderName());
				csr.setProviderId(findByIntegerId.getProviderId());
				csr.setServiceType(findByIntegerId.getServiceType());
				csr.setOldComment(opinion);
				csr.setCreateTime(new Date().getTime());
				csr.setStationId(stationId);
				csr.setPensionId(pensionId);
				csr.setOrderId(findByIntegerId.getId());
				csr.setOldId(ss.getId());
				csr.setSinglePrice(findByIntegerId.getSinglePrice());
				csr.setServiceUnit(findByIntegerId.getServiceUnit());
				csr.setCountPrice(findByIntegerId.getCountPrice());
				commonService.saveInfo(csr);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "{\"flag\": 1}";
	}
	/**
	 * 保存投诉工单
	 * @param complaint
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveComplaint.do")
	@ResponseBody
	public String saveComplaint(FComplaintInfo complaint, HttpSession session) {
		PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
		if (complaint.getStationId() == null) {
			complaint.setStationId(pp.getStationId());
		}
		complaint.setPensionId(pp.getPensionId());
		complaint.setAcceptId(pp.getId());
		orderService.saveOrder(complaint);
		return "{\"rtn\": 1}";
	}
	
	/**
	 * 保存预约挂号工单
	 * @param forward
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveForward.do")
	@ResponseBody
	public String saveComplaint(CForwardRegistInfo forward, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		forward.setPensionId(id);
		forward.setCreateTime(new Date().getTime());
		orderService.saveOrder(forward);
		return "{\"flag\": 1}";
	}
	
	/**
	 * 保存紧急求助工单
	 * @param phoneSOS
	 * @param session
	 * @return
	 */
	@RequestMapping("/savePhoneSOS.do")
	@ResponseBody
	public String savePhoneSOS(CPhoneSOSInfo phoneSOS, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		phoneSOS.setPensionId(id);
		phoneSOS.setCreateTime(new Date().getTime());
		orderService.saveOrder(phoneSOS);
		return "{\"flag\": 1}";
	}
	
	/**
	 * 保存咨询工单
	 * @param consult
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveConsult.do")
	@ResponseBody
	public String saveConsult(CConsultInfo consult, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		consult.setPensionId(id);
		consult.setCreateTime(new Date().getTime());
		orderService.saveOrder(consult);
		return "{\"flag\": 1}";
	}
	
	/**
	 * 查询关联工单
	 * @param orderType
	 * @param linkPhone
	 * @param session
	 * @return
	 */
	@RequestMapping("/getLinkOrder.do")
	@ResponseBody
	public List<GDwork> getLinkOrder(String linkPhone, HttpSession session) {
		PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
		int userType = Integer.parseInt(session.getAttribute("userType").toString());
		Integer id = pp.getPensionId();
		String subWhere = " phone = '" + linkPhone + "'" + " and pensionId = " + id;
		if (userType == 510) { //如果是机构人员，只能关联本机构工单
			subWhere += " and stationId = " + pp.getStationId();
		}
		//String orderBy = " createTime desc";
		List<GDwork> list = commonService.findByHql(GDwork.class, subWhere, "");
		return list;
	}
	
	/**
	 * 小棉袄工单（坐席保存在页面添加站点ID，站点人员保存在后台添加站点ID）
	 * @param order
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveXMAOrder.do")
	@ResponseBody
	public String saveXMAOrder(GDwork orders, String orderJson, HttpSession session) {
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		JSONObject orderObj = JSONObject.fromObject(orderJson);
		
		Integer stationId = null;
		Integer pensionId = null;
		Integer oldId = null;
		String providerName = "";
		
		
		
		Integer acceptId = null;
		if (userType == 520 || userType == 510) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			pensionId = pp.getPensionId();
			acceptId = pp.getId();
			if (orders.getStationId() == null) {
				stationId = pp.getStationId();
			} else {
				stationId = orders.getStationId();
			}
		}
		if (userType == 51) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			pensionId = pension.getPid();
			stationId = pension.getId();
		}
		if (orders.getProviderId() != null) {
			FWProvider pro = (FWProvider) commonService.findByIntegerId(FWProvider.class, orders.getProviderId());
			providerName = pro.getName();
		}
		
		if (orders.getOldId() == null) {
			String subwhere = " stationId = " + stationId + " and name = '" + orders.getName() + "' and phone like '%" + orders.getPhone() + "%'";
			List<MOldPeopleInfo> oldList = commonService.findByHql(MOldPeopleInfo.class, subwhere, "");
			if (!oldList.isEmpty() && oldList.size() > 0) {
				MOldPeopleInfo mOldPeopleInfo = oldList.get(0);
				oldId = mOldPeopleInfo.getId();
			}
		} else {
			oldId = orders.getOldId();
		}
		
		Iterator keys = orderObj.keys();
		while (keys.hasNext()) {
			GDwork order = new GDwork();
			Object key = (Object) keys.next();
			Long dateLong = DateUtil.strToLongDate(key.toString(), "yyyy-MM-dd");
			JSONObject obj = (JSONObject) orderObj.get(key.toString());
			String content = obj.get("content").toString();
			Float countPrice = Float.parseFloat(obj.get("countPrice").toString());
			String items = obj.get("items").toString();
			order.setName(orders.getName());
			order.setProviderId(orders.getProviderId());;
			order.setStationId(orders.getStationId());
			order.setAddress(orders.getAddress());
			order.setPhone(orders.getPhone());
			order.setDeliveryCost(orders.getDeliveryCost());
			order.setDates(dateLong);
			order.setContent(content);
			order.setCountPrice(countPrice);
			order.setOrderState(0);
			order.setCreateTime(new Date().getTime());
			order.setAcceptId(acceptId);
			order.setPensionId(pensionId);
			order.setStationId(stationId);
			order.setProviderName(providerName);
			order.setCommunityId(orders.getCommunityId());
			order.setOldId(oldId);
			commonService.saveInfo(order);
			
			items.substring(0, items.length() - 1);
			String[] split = items.split("@");
			List<GDOrderItems> itemList = new ArrayList<GDOrderItems>();
			for (int i = 0; i < split.length; i++) {
				String[] sss = split[i].split("&");
				GDOrderItems item = new GDOrderItems();
				int num = Integer.parseInt(sss[1]);
				float price = Float.parseFloat(sss[2]);
				item.setName(sss[0]);
				item.setCountNum(num);
				item.setSinglePrice(price);
				item.setCountPrice(num * price);
				item.setProviderId(orders.getProviderId());
				item.setServiceDate(dateLong);
				item.setCreateTime(new Date().getTime());
				item.setStationId(stationId);
				item.setOrderId(order.getId());
				item.setItemsUnit(sss[3]);
				itemList.add(item);
			}
			commonService.saveList(itemList);
			
		}
		
		
		return "{\"rtn\": " + 1 + "}";
	}
	
	
	
	
	@RequestMapping("/findById.do")
	@ResponseBody
	public JSONObject findById(Integer id, HttpSession session) {
		

		JSONObject json = new JSONObject();
		GDwork findByIntegerId = (GDwork)	commonService.findByIntegerId(GDwork.class, id);
		Integer stationId = findByIntegerId.getStationId();
		MPensionStationInfo ss =(MPensionStationInfo) commonService.findByIntegerId(MPensionStationInfo.class, stationId);
		json.put("list", findByIntegerId);
		json.put("list2", ss);
		
		
		return json;
	}
	
	@RequestMapping("/deleteOrder.do")
	@ResponseBody
	public String deleteOrder(Integer id) {
		commonService.deleteData(GDwork.class, " id = " + id);
		commonService.deleteData(GDOrderItems.class, " orderId = " + id);
		return "{\"rtn\": " + 1 + "}";
	}
	
	@RequestMapping("/getOrderItems.do")
	@ResponseBody
	public List<GDOrderItems> getOrderItems(Integer orderId) {
		return commonService.findByHql(GDOrderItems.class, " orderId = " + orderId, "");
	}
	
	@RequestMapping("/getCountDetail.do")
	@ResponseBody
	public List<OrderCountList> getCountDetail(String name, String phone, Long startDate, Long endDate, HttpSession session) {
		
		List<MCommunityInfo> communityList = getCommunityList(session);
		Map<Integer, String> communityMap = new HashMap<Integer, String>();
		for (MCommunityInfo mCommunityInfo : communityList) {
			communityMap.put(mCommunityInfo.getId(), mCommunityInfo.getName());
		}
		
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		String baseSubWhere = "";
		if (userType == 51) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			baseSubWhere = " stationId = " + pension.getId();
		}
		
		if (name != null && !"".equals(name)) {
			baseSubWhere += " and name = '" + name + "'";
		}
		if (phone != null && !"".equals(phone)) {
			baseSubWhere += " and phone like '%" + phone + "%'";
		}
		
		String dateStr = "";
		if (startDate == endDate  && startDate != null) {
			dateStr = DateUtil.longToYMD(startDate);
		} else {
			dateStr = DateUtil.longToYMD(startDate) + "至" + DateUtil.longToYMD(endDate);
		}
		
		if (startDate == null && endDate == null) {
			baseSubWhere += " and dates = " + DateUtil.getTodayYMDLong();
			dateStr = DateUtil.longToYMD(DateUtil.getTodayYMDLong());
		} else {
			if (startDate != null && startDate == endDate) {
				baseSubWhere += " and dates = " + startDate;
				dateStr = DateUtil.longToYMD(startDate);
			} else {
				if (startDate != null && endDate != null && startDate != endDate) {
					baseSubWhere += " and dates >= " + startDate + " and dates <= " + endDate;
					dateStr = DateUtil.longToYMD(startDate) + "至" + DateUtil.longToYMD(endDate);
				} else {
					if (startDate != null && endDate == null) {
						baseSubWhere += " and dates >= " + startDate;
						dateStr = DateUtil.longToYMD(startDate) + "之后";
					}
					if (endDate != null && startDate == null) {
						baseSubWhere += " and dates <= " + endDate;
						dateStr = DateUtil.longToYMD(endDate) + "之前";
					}
				}
			}
		}
		
		//查询出所有符合条件的工单
		List<GDwork> orderList = commonService.findByHql(GDwork.class, baseSubWhere, " createTime desc");
		//所有相同服务商的工单放在一个map中
		Map<String, List<GDwork>> map1 = new HashMap<String, List<GDwork>>();
		for (GDwork order : orderList) { // 把相同服务商的工单放在一块
			List<GDwork> getList = map1.get(order.getProviderName());
			if (getList == null) {
				List<GDwork> sameList = new ArrayList<GDwork>();
				sameList.add(order);
				map1.put(order.getProviderName(), sameList);
			} else {
				getList.add(order);
				map1.put(order.getProviderName(), getList);
			}
		}
		
		List<OrderCountList> rtnList = new ArrayList<OrderCountList>();
		
		// 循环相同服务商的工单，按照每个服务商出一张三联单
		Set<Entry<String, List<GDwork>>> entrySet = map1.entrySet();
		for (Entry<String, List<GDwork>> entry : entrySet) {
			OrderCountList countList = new OrderCountList();
			
			List<GDwork> list = entry.getValue();
			Float countPrice = 0f; // 计算总价
			Float deliveryCost = 0f; // 计算总配送费
			
			
			countList.setProviderName(entry.getKey()); //设置服务商名称
			countList.setDateStr(dateStr); // 设置统计时间
			List<GDOrderItems> itemList = new ArrayList<GDOrderItems>();
			Map<String, Integer> listCountMap = new HashMap<String, Integer>();
			for (GDwork order : list) {
				countPrice += order.getCountPrice(); //总价相加
				deliveryCost += order.getDeliveryCost(); // 配送费相加
				Integer communityId = order.getCommunityId();
				String communityName = communityMap.get(communityId);
				String key = communityName + "&" + order.getDeliveryCost();
				listCountMap.put(key, listCountMap.get(key) == null ? 1 : listCountMap.get(key) + 1);
				List<GDOrderItems> findItemList = commonService.findByHql(GDOrderItems.class, " orderId = " + order.getId(), ""); // 查询工单下的所有服务项目
				itemList.addAll(findItemList); // 把服务项目放入服务项目几个中
			}
			
			countList.setListCount(listCountMap);
			countList.setCountPrice(countPrice);
			countList.setDeliveryCost(deliveryCost);
			Map<String, List<GDOrderItems>> itemMap = new HashMap<String, List<GDOrderItems>>(); // 以服务名称为key的map，value是集合
			
			
			for (GDOrderItems gdOrderItems : itemList) { // 循环处理服务项目
				List<GDOrderItems> list2 = itemMap.get(gdOrderItems.getName());
				if (list2 == null) { // 如果没有改key，添加新的
					List<GDOrderItems> list3 = new ArrayList<GDOrderItems>();
					list3.add(gdOrderItems);
					itemMap.put(gdOrderItems.getName(), list3);
				} else { // 如果有，在原有list上继续添加
					list2.add(gdOrderItems);
					itemMap.put(gdOrderItems.getName(), list2);
				}
			}
			
			List<GDOrderItems> finalItemList = new ArrayList<GDOrderItems>();
			Set<Entry<String, List<GDOrderItems>>> entrySet2 = itemMap.entrySet();
			for (Entry<String, List<GDOrderItems>> entry2 : entrySet2) {
				GDOrderItems finalItem = new GDOrderItems();
				finalItem.setName(entry2.getKey());
				Float countPrice1 = 0f;
				Integer countNum = 0;
				List<GDOrderItems> value = entry2.getValue();
				for (GDOrderItems gdOrderItems : value) {
					countNum += gdOrderItems.getCountNum();
					countPrice1 += gdOrderItems.getCountPrice();
					finalItem.setSinglePrice(gdOrderItems.getSinglePrice());
				}
				finalItem.setCountPrice(countPrice1);
				finalItem.setCountNum(countNum);
				finalItemList.add(finalItem);
			}
			countList.setItemList(finalItemList);
			rtnList.add(countList);
		}
		
		return rtnList;
	}
	
	public List<MCommunityInfo> getCommunityList(HttpSession session) {
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		String subWhere = "";
		if (userType == 5 || userType == 51 || userType == 52) {
			MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
			if (userType == 5) {
				subWhere += " pensionId = " + pension.getId();
			}
			if (userType == 51) {
				subWhere += " stationId = " + pension.getId();
			}
			if (userType == 52) {
				subWhere += " pensionId = " + pension.getPid();
			}
		}
		if (userType == 510 || userType == 520) {
			PensionPeopleInfo pp = (PensionPeopleInfo) session.getAttribute("user");
			if (userType == 510) {
				subWhere += " stationId = " + pp.getStationId();
			}
			if (userType == 520) {
				subWhere += " pensionId = " + pp.getPensionId();
			}
		}
		
		return commonService.findByHql(MCommunityInfo.class, subWhere, " createTime desc");
	}
}

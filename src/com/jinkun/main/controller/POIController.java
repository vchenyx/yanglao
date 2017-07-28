package com.jinkun.main.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.common.service.CommonService;
import com.common.util.DateUtil;
import com.common.util.ExcelException;
import com.common.util.ExcelUtil;
import com.jinkun.main.beans.CServiceRecords;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.PensionPeopleInfo;
import com.jinkun.main.beans.UnderpinAssist;
import com.jinkun.main.form.POIServiceRecords;
import com.jinkun.main.form.POIUnderpinAssist;

@Controller
@RequestMapping("/poi")
public class POIController {

	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/importUnderpinAssist.do")
	@ResponseBody
	public String importUnderpinAssist(@RequestParam("excel") MultipartFile file, HttpSession session) {
		Integer pensionId = null; // 
		Integer stationId = null;
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
		
		try {
			InputStream inputStream = file.getInputStream();
			LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
			fieldMap.put("序号", "id");
			fieldMap.put("姓名", "name");
			fieldMap.put("性别", "sex");
			fieldMap.put("身份证号", "idCrad");
			fieldMap.put("出生年月", "stateBirthday");
			fieldMap.put("年龄", "age");
			fieldMap.put("电话", "phone");
			fieldMap.put("老人类别", "managerClass");
			fieldMap.put("所属社区", "communitys");
			fieldMap.put("居住地址（详细）", "address");
			fieldMap.put("紧急联系人/关系", "urgencyName");
			fieldMap.put("紧急联系人联系电话", "urgencyPhone");
			fieldMap.put("婚姻状况/居住情况", "maritalStatus");
			fieldMap.put("收入/资金困难", "income");
			String[] uniqueFields = {"序号", "姓名"};
			ExcelUtil<POIUnderpinAssist> excelUtil = new ExcelUtil<POIUnderpinAssist>();
			List<POIUnderpinAssist> readList = excelUtil.excelToList(inputStream, "总表", POIUnderpinAssist.class, fieldMap, uniqueFields);
			String datePattern = "yyyy.MM.dd";
			
			Integer flag = 0;
			List<UnderpinAssist> dbList = new ArrayList<UnderpinAssist>();
			
			int last = readList.size() % 50;
			int size = readList.size() / 50;
			int pageSize = 0;
			for (POIUnderpinAssist poiRead : readList) {
				UnderpinAssist assist = new UnderpinAssist();
				assist.setName(poiRead.getName());
				assist.setSex("女".equals(poiRead.getSex()) ? 1 : 0);
				String idCrad = poiRead.getIdCrad();
				assist.setIdCrad(idCrad);
				assist.setStateBirthday(DateUtil.strToLongDate(poiRead.getStateBirthday(), datePattern));
				assist.setAge(Integer.parseInt(poiRead.getAge()));
				assist.setPhone(poiRead.getPhone());
				assist.setManagerClass("托底".equals(poiRead.getManagerClass()) ? 0 : 1);
				assist.setCommunitys(poiRead.getCommunitys());
				assist.setAddress(poiRead.getAddress());
				assist.setUrgencyName(poiRead.getUrgencyName());
				assist.setUrgencyPhone(poiRead.getUrgencyPhone());
				assist.setMaritalStatus(poiRead.getMaritalStatus());
				assist.setIncome(poiRead.getIncome());
				
				assist.setCreateTime(new Date().getTime());
				assist.setPensionId(pensionId);
				assist.setStationId(stationId);
				//poiService.saveUnderpinAssist(assist);
				dbList.add(assist);
				flag++;
				if (flag == 50) {
					commonService.saveList(dbList);
					dbList.removeAll(dbList);
					flag = 0;
					pageSize++;
				}
				if (pageSize == size && flag == last) {
					commonService.saveList(dbList);
					dbList.removeAll(dbList);
				}
			}
			return "{\"rtn\": 1}";
		} catch (IOException | ExcelException e) {
			e.printStackTrace();
			return "{\"rtn\": 0}";
		}
		
	}
	
	@RequestMapping("/importServiceRecords.do")
	@ResponseBody
	public String importServiceRecords(@RequestParam("excel") MultipartFile file, HttpSession session) {
		Integer pensionId = null; // 
		Integer stationId = null;
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
		
		try {
			InputStream inputStream = file.getInputStream();
			LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
			fieldMap.put("序号", "id");
			fieldMap.put("姓名", "name");
			fieldMap.put("性别", "sex");
			fieldMap.put("年龄", "age");
			fieldMap.put("联系电话", "phone");
			fieldMap.put("社区", "community");
			fieldMap.put("老人类别", "oldType");
			fieldMap.put("服务商名称", "procuderName");
			fieldMap.put("服务地址", "serviceAddress");
			fieldMap.put("服务日期", "serviceDate");
			fieldMap.put("服务起止时间", "startEndTime");
			fieldMap.put("服务内容", "serviceContent");
			fieldMap.put("价格", "singlePrice");
			fieldMap.put("时长/数量", "serviceUnit");
			fieldMap.put("消费合计", "countPrice");
			fieldMap.put("服务方式", "serviceType");
			fieldMap.put("服务反馈情况", "oldComment");
			fieldMap.put("备注（数量）", "remarks");
			String[] uniqueFields = {"序号", "姓名"};
			ExcelUtil<POIUnderpinAssist> excelUtil = new ExcelUtil<POIUnderpinAssist>();
			List<POIServiceRecords> readList = excelUtil.excelToList(inputStream, "总表", POIServiceRecords.class, fieldMap, uniqueFields);
			
			Integer flag = 0;
			List<CServiceRecords> dbList = new ArrayList<CServiceRecords>();
			int last = readList.size() % 50;
			int size = readList.size() / 50;
			int pageSize = 0;
			
			for (POIServiceRecords poiRead : readList) {
				CServiceRecords record = new CServiceRecords();
				record.setName(poiRead.getName());
				record.setSex(("女".equals(poiRead.getSex()) ? 1 : 0));
				record.setAge(Integer.parseInt(poiRead.getAge()));
				record.setPhone(poiRead.getPhone());
				record.setCommunity(poiRead.getCommunity());
				record.setOldType((poiRead.getOldType()));
				record.setProcuderName(poiRead.getProcuderName());
				record.setServiceAddress(poiRead.getServiceAddress());
				record.setServiceDate(DateUtil.strToLongDate(poiRead.getServiceDate(), "yyyy/MM/dd"));
				record.setStartEndTime(poiRead.getStartEndTime());
				record.setServiceContent(poiRead.getServiceContent());
				record.setSinglePrice(Float.parseFloat(poiRead.getSinglePrice()));
				record.setServiceUnit(Integer.parseInt(poiRead.getServiceUnit()));
				record.setCountPrice(Float.parseFloat(poiRead.getCountPrice()));
				record.setServiceType(poiRead.getServiceType());
				record.setOldComment(poiRead.getOldComment());
				record.setRemarks(poiRead.getRemarks());
				
				record.setCreateTime(new Date().getTime());
				record.setPensionId(pensionId);
				record.setStationId(stationId);
				//poiService.saveUnderpinAssist(assist);
				dbList.add(record);
				flag++;
				if (flag == 50) {
					commonService.saveList(dbList);
					dbList.removeAll(dbList);
					flag = 0;
					pageSize++;
				}
				if (pageSize == size && flag == last) {
					commonService.saveList(dbList);
					dbList.removeAll(dbList);
				}
			}
			return "{\"rtn\": 1}";
		} catch (IOException | ExcelException e) {
			e.printStackTrace();
			return "{\"rtn\": 0}";
		}
	}
}

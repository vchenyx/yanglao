package com.jinkun.health.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.jinkun.health.beans.UDeviceInfo;
import com.jinkun.health.beans.UDeviceType;
import com.jinkun.health.service.DeviceService;
import com.jinkun.main.beans.MPensionStationInfo;

/**
 * 机构设备管理页面控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/device")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 机构保存设备类型
	 * @param deviceType
	 * @return
	 */
	@RequestMapping("/saveDeviceType.do")
	@ResponseBody
	public String saveDeviceType(UDeviceType deviceType, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer penId = pension.getId();
		deviceType.setPensionId(penId);
		deviceType.setEnterTime(System.currentTimeMillis());
		Integer id = deviceService.saveDeviceType(deviceType);
		if (id != null && id > 0) {
			return "{\"flag\": 1}";
		}
		return "{\"flag\": 0}";
	}
	
	/**
	 * 保存设备信息
	 * @param device
	 * @return
	 */
	@RequestMapping("/saveDevice.do")
	@ResponseBody
	public String saveDevice(UDeviceInfo device, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer penId = pension.getId();
		device.setPensionId(penId);
		device.setEnterTime(System.currentTimeMillis());
		Integer id = deviceService.saveDevice(device);
		if (id != null && id > 0) {
			return "{\"flag\": 1}";
		}
		return "{\"flag\": 0}";
	}
	
	/**
	 * 查询本机构所有设备类型
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllDeviceType.do")
	@ResponseBody
	public List<UDeviceType> getAllDeviceType(HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		String subWhere = " pensionId = " + id;
		List<UDeviceType> deviceTypeList = commonService.findByHql(UDeviceType.class, subWhere, " enterTime desc");
		return deviceTypeList;
	}
	
	/**
	 * 删除设备类型
	 * @param deviceType
	 * @return
	 */
	@RequestMapping("/deleteDeviceType.do")
	@ResponseBody
	public String deleteDeviceType(UDeviceType deviceType) {
		String subWhere = " type = " + deviceType.getId();
		List<UDeviceInfo> list = commonService.findByHql(UDeviceInfo.class, subWhere, "");
		if (list.isEmpty()) {
			deviceService.deleteDeviceType(deviceType);
			return "{\"flag\": 1}";
		}
		return "{\"flag\": 0}";
	}
	
	/**
	 * 删除设备
	 * @param device
	 * @return
	 */
	@RequestMapping("/deleteDevice.do")
	@ResponseBody
	public String deleteDevice(UDeviceInfo device) {
		deviceService.deleteDevice(device);
		return "{\"flag\": 1}";
	}
	
	/**
	 * 查询本机构所有设备
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllDevicePension.do")
	@ResponseBody
	public List<UDeviceInfo> getAllDevicePension(HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		String subWhere = " pensionId = " + id;
		List<UDeviceInfo> deviceTypeList = commonService.findByHql(UDeviceInfo.class, subWhere, " enterTime desc");
		return deviceTypeList;
	}
	
	/**
	 * 查询老人所有设备
	 * @param oldId
	 * @return
	 */
	@RequestMapping("/getAllDeviceOld.do")
	@ResponseBody
	public List<UDeviceInfo> getAllDevicePension(Integer oldId) {
		String subWhere = " oldId = " + oldId;
		List<UDeviceInfo> deviceTypeList = commonService.findByHql(UDeviceInfo.class, subWhere, " enterTime desc");
		return deviceTypeList;
	}
	
	/**
	 * 修改设备信息
	 * @param device
	 * @return
	 */
	@RequestMapping("/updateDevice.do")
	@ResponseBody
	public String updateDevice(UDeviceInfo device) {
		deviceService.updateDevice(device);
		return "{\"flag\": 1}";
	}
	
	
	
}

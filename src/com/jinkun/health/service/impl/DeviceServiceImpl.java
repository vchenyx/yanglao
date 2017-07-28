package com.jinkun.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.health.beans.UDeviceInfo;
import com.jinkun.health.beans.UDeviceType;
import com.jinkun.health.service.DeviceService;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public Integer saveDevice(UDeviceInfo device) {
		commonDao.saveTable(device);
		return device.getId();
	}
	
	@Override
	public Integer saveDeviceType(UDeviceType deviceType) {
		commonDao.saveTable(deviceType);
		return deviceType.getId();
	}
	
	@Override
	public void deleteDeviceType(UDeviceType deviceType) {
		commonDao.deleteTable(deviceType);
	}
	
	@Override
	public void deleteDevice(UDeviceInfo device) {
		commonDao.deleteTable(device);
	}
	
	@Override
	public void updateDevice(UDeviceInfo device) {
		commonDao.updateTable(device);
	}
}

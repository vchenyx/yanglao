package com.jinkun.health.service;

import com.jinkun.health.beans.UDeviceInfo;
import com.jinkun.health.beans.UDeviceType;

public interface DeviceService {

	Integer saveDeviceType(UDeviceType deviceType);

	Integer saveDevice(UDeviceInfo device);

	void deleteDeviceType(UDeviceType deviceType);

	void deleteDevice(UDeviceInfo device);

	void updateDevice(UDeviceInfo device);

}

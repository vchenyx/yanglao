package com.jinkun.main.service;

import com.jinkun.main.beans.AAlarmInfo;

public interface AlarmService {

	Long saveAlarmInfo(AAlarmInfo alarmInfo);

	void updateAlarmHandle(long longAlarmId, String userId);

}

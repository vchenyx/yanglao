package com.jinkun.main.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.AAlarmInfo;
import com.jinkun.main.service.AlarmService;

@Service
@Transactional
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public Long saveAlarmInfo(AAlarmInfo alarmInfo) {
		commonDao.saveTable(alarmInfo);
		return alarmInfo.getId();
	}
	
	@Override
	public void updateAlarmHandle(long alarmId, String userId) {
		String sql = "update a_alarm_info set handleId = '" + userId + "', handleTime = now() where id = " + alarmId; 
		commonDao.updateColumnBySql(sql);
	}
}

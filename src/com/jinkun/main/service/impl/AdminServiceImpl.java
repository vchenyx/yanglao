package com.jinkun.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.AAuditStationInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public String saveUserInfo(MUserInfo userInfo) {
		commonDao.saveTable(userInfo);
		return userInfo.getId();
	}
	
	@Override
	public void updatePensionStation(MPensionStationInfo pensionStation) {
		Integer id = pensionStation.getId();
		Integer state = pensionStation.getState();
		String userId = pensionStation.getUserId();
		String sql = "update m_pension_station_info set state = " + state + " , userId = '" + userId + " ' where id = " + id;
		commonDao.updateColumnBySql(sql);
	}
	
	@Override
	public void saveAuditStationInfo(AAuditStationInfo auditStation) {
		commonDao.saveTable(auditStation);
	}
}

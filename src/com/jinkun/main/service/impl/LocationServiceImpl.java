package com.jinkun.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.AAlarmInfo;
import com.jinkun.main.beans.LLocationLastInfo;
import com.jinkun.main.beans.LLocationTrackInfo;
import com.jinkun.main.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void saveLocationLast(LLocationLastInfo lastLoca) {
		String userId = lastLoca.getUserId();
		List<LLocationLastInfo> list = commonDao.findByHql(LLocationLastInfo.class, " userId = '" + userId + "' ", "");
		if (list.isEmpty()) {
			commonDao.saveTable(lastLoca);
		} else {
			commonDao.updateTable(lastLoca);
		}
	}
	
	@Override
	public void saveLocationTrack(LLocationTrackInfo trackLoca) {
		commonDao.saveTable(trackLoca);
	}
	
	@Override
	public void updateLocationLast(LLocationLastInfo lastLoca) {
		String sql = "update l_location_last_info set deviceId = '" + lastLoca.getDeviceId() + "'"
											+ ", latGG = " + lastLoca.getLatGG() 
											+ ", lonGG = " + lastLoca.getLonGG()
											+ ", lonGps = " + lastLoca.getLonGps()
											+ ", latGps = " + lastLoca.getLatGps()
											+ ", netType = '" + lastLoca.getNetType() + "'"
											+ ", posiTime = " + lastLoca.getPosiTime()
											+ ", posiType = " + lastLoca.getPosiType()
											+ " where userId = '" + lastLoca.getUserId() + "'";
		commonDao.updateColumnBySql(sql);
	}
	
	@Override
	@Async
	public void saveLocationTrackAsync(LLocationTrackInfo trackLoca) {
		commonDao.saveTable(trackLoca);
	}
	
	@Override
	public AAlarmInfo saveAlarmInfo(LLocationTrackInfo trackLoca) {
		
		return null;
	}
	
	@Override
	public void updateAlarmInfo(LLocationTrackInfo trackLoca) {
		
	}
}

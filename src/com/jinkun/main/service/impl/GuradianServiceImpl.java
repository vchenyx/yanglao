package com.jinkun.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.service.GuradianService;

@Service
@Transactional
public class GuradianServiceImpl implements GuradianService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void updateOldPension(Integer oldId, Integer pensionId) {
		String sql = " update m_old_info set pensionStationId = " + pensionId + " where id = " + oldId;
		commonDao.updateColumnBySql(sql);
	}
	
	@Override
	public void updateOldInfo(MOldPeopleInfo old) {
		commonDao.updateTable(old);
	}
}

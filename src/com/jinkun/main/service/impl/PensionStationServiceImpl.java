package com.jinkun.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.FWProvider;
import com.jinkun.main.beans.FWProviderType;
import com.jinkun.main.beans.MNurseInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.service.PensionStationService;

@Service
@Transactional
public class PensionStationServiceImpl implements PensionStationService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void deleteNurse(Integer id) {
		MNurseInfo nurse = new MNurseInfo();
		nurse.setId(id);
		commonDao.deleteTable(nurse);		
	}
	
	@Override
	public void updateOldBindDevice(MOldPeopleInfo old) {
		commonDao.updateTable(old);
	}
	
	@Override
	public void deleteOlder(MOldPeopleInfo old) {
		commonDao.deleteTable(old);
	}

	@Override
	public void deleteProvider(FWProvider old) {
		commonDao.deleteProvider(old);
	}

	@Override
	public void deleteProviderClass(FWProviderType old) {
		commonDao.deleteProviderClass(old);
	}
	
	
}

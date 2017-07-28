package com.jinkun.main.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.service.OldInfoService;

@Service
@Transactional
public class OldInfoServiceImpl implements OldInfoService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void saveOldInfo(MOldPeopleInfo old) {
		commonDao.saveTable(old);
	}
	
	@Override
	public void updateOldInfo(MOldPeopleInfo old) {
		commonDao.updateTable(old);
	}
}

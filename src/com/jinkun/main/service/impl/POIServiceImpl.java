package com.jinkun.main.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.UnderpinAssist;
import com.jinkun.main.service.POIService;

@Service
@Transactional
public class POIServiceImpl implements POIService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void saveUnderpinAssistList(List<UnderpinAssist> dbList) {
		commonDao.saveTableList(dbList);
	}

	@Override
	public void saveUnderpinAssist(UnderpinAssist underpinAssist) {
		commonDao.saveTable(underpinAssist);		
	}
}

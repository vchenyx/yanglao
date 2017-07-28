package com.jinkun.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.SDictionary;
import com.jinkun.main.service.DictionaryService;

@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public Integer saveDictionary(SDictionary dic) {
		commonDao.saveTable(dic);
		return dic.getId();
	}
}

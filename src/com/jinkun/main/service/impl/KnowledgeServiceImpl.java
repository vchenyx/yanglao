package com.jinkun.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.KnowledgeBase;
import com.jinkun.main.service.KnowledgeService;
@Service
@Transactional
public class KnowledgeServiceImpl implements KnowledgeService {
	@Autowired
	private CommonDao commonDao;

	@Override
	public void deleteKnowledge(KnowledgeBase old) {
//		commonDao.deleteKnowledge(old);
	}
}

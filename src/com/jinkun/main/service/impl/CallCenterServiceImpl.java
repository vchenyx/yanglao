package com.jinkun.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.service.CallCenterService;

@Service
@Transactional
public class CallCenterServiceImpl implements CallCenterService {

	@Autowired
	private CommonDao commonDao;
}

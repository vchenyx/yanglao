package com.jinkun.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.CWorkOrderInfo;
import com.jinkun.main.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void saveOrder(Object order) {
		commonDao.saveTable(order);
	}
}

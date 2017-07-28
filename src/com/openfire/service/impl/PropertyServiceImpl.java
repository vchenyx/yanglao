package com.openfire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.openfire.beans.OfProperty;
import com.openfire.service.PropertyService;

/**
 * 系统属性实现类
 * @author zhangbin
 *
 */
@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private CommonDao commonDao;
	public CommonDao getCommonDao() {
		return commonDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	/*
	 * 按属性名查询属性值(non-Javadoc)
	 * @see com.openfire.service.PropertyService#findByName(java.lang.String)
	 */
	@Override
	public String findByName(String name) {
		OfProperty of = (OfProperty)commonDao.findByStringId(OfProperty.class, name);
		if(of==null){
			return "";
		}
		return of.getPropValue();
	}

}

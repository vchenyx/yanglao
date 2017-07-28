package com.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.common.service.CommonService;
import com.jinkun.main.beans.MUserInfo;

/**
 * 通用service
 * @author zhangbin
 *
 */

@Service
@Transactional
public class CommonServiceImpl implements CommonService{
	@Autowired
	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public long getResultCount(Class className, String subwhere) {
		return commonDao.getCountByHql(className, subwhere);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByHql(Class className, String subwhere, String orderby) {
		return commonDao.findByHql(className, subwhere, orderby);
	}
	
	@Override
	public Object findByHqlUnique(Class className, String subwhere) {
		// TODO Auto-generated method stub
		return commonDao.findByHqlUnique(className, subwhere);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByHqlPage(Class className, String subwhere, String orderby,
			Integer currentPage, Integer pageSize) {
		return commonDao.findByHqlPage(className, subwhere,orderby, currentPage, pageSize);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getResult(Class className, String subwhere, String orderby,
			int current, int pagesize) {
		//创建数据
		Object[] obj = new Object[2];
		//获取总数
		obj[0] = getResultCount(className, subwhere);
		//获取数据集
		obj[1] = findByHqlPage(className, subwhere,orderby, current, pagesize);
		return obj;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteData(Class className, String subwhere) {
		commonDao.deleteTableMoreData(className, subwhere);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void updateDate(Class className, String[] setHql, String subwhere) {
		String hql="";
		//添加修改语句
		for(int i=0;i<setHql.length;i++){
			hql += setHql[i]+" ,";
		}
		if(!"".equals(hql))hql = hql.substring(0,hql.length()-1);
		commonDao.updateTableMoreData(className, hql, subwhere);
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByHqlGetColumn(Class className, String subwhere,
			String orderby, String columns) {
		return commonDao.findByHqlGetColumn(className, subwhere, orderby, columns);
	}
	
	@Override
	public Object findByStringId(Class clazz, String id) {
		return commonDao.findByStringId(clazz, id);
	}
	
	@Override
	public Object findByIntegerId(Class clazz, Integer id) {
		// TODO Auto-generated method stub
		return commonDao.findByIntegerId(clazz, id);
	}
	
	@Override
	public Object findByLongId(Class className, Long id) {
		// TODO Auto-generated method stub
		return commonDao.findByLongId(className, id);
	}
	
	@Override
	public void saveInfo(Object className) {
			commonDao.saveTable(className);
	}

	@Override
	public void saveProv(Object className) {
			commonDao.saveTable(className);
	}
	
	@Override
	public boolean updateInfo(Object obj) {
		try {
			commonDao.updateTable(obj);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public void saveList(List<?> list) {
		commonDao.saveTableList(list);
	}
	
	
}

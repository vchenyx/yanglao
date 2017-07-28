package com.common.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * service操作
 * @author zhangbin
 *
 */
@SuppressWarnings("rawtypes")
public interface CommonService {
	
	//hql分页查询数量
	public long getResultCount(Class className,String subwhere);
	
	//根据hql语句查询数据
	public List findByHql(Class className,String subwhere,String orderby);
	
	public Object findByHqlUnique(Class className,String subwhere);
	
	//根据hql语句分页查询数据
	public List findByHqlPage(Class className,String subwhere,String orderby,Integer currentPage,Integer pageSize);
	
	//hql分页
	public Object[] getResult(Class className,String subwhere,String orderby,int current,int pagesize);

	//批量删除数据
	public void deleteData(Class className,String subwhere);
	
	//批量修改数据
	public void updateDate(Class className,String[] setHql,String subwhere);
	
	
	//修改对象
	public boolean updateInfo(Object obj);
	
	public List findByHqlGetColumn(Class className,String subwhere,String orderby,String columns);

	public Object findByStringId(Class clazz, String id);
	
	public Object findByIntegerId(Class clazz, Integer id);
	
	public Object findByLongId(Class className, Long id);
	
	public void saveInfo(Object className);
	
	public void saveProv(Object className);
	
	public void saveList(List<?> list);
	
}

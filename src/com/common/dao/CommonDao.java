package com.common.dao;

import java.util.List;

import com.jinkun.main.beans.FWProvider;
import com.jinkun.main.beans.FWProviderType;

/**
 * 
 * 说明：万能无敌dao，数据库接口
 * 作者：张彬
 * 时间：2016-1-11
 * 
 * */
@SuppressWarnings("rawtypes")
public interface CommonDao {
	
	//根据实体类，添加数据库信息
	public void saveTable(Object obj);
	
	//根据实体类，修改数据库信息
	public void updateTable(Object obj);
	
	//根据实体类，更新数据库信息
	public void renewTable(Object obj);
	
	public void updateColumnBySql(String sql);
	
	//根据实体类，删除数据库信息
	public void deleteTable(Object obj);
	
	//根据实体类，主键id：int 查询
	public Object findByIntegerId(Class className,Integer id);
	
	//根据实体类，主键id：Long 查询
	public Object findByLongId(Class className,Long id);
	
	//根据实体类，主键id：String 查询
	public Object findByStringId(Class className,String id);
	
	//根据实体类，hql查询数量
	public Long getCountByHql(Class className,String subwhere);

	//根据实体类，hql查询条件 查询
	public List findByHql(Class className,String subwhere,String orderby);
	
	//分页查询：根据实体类，hql查询条件，当前页，显示数查询
	public List findByHqlPage(Class className,String subwhere,String orderby,Integer currentPage,Integer pageSize);
	
	//根据实体类，hql查询,得到列值
	public List findByHqlGetColumn(Class className,String subwhere,String orderby,String columns);
	
	//根据实体类，hql分页查询,得到列值
	public List findByHqlGetColumnPage(Class className,String subwhere,String orderby,String columns,Integer currentPage,Integer pageSize);

	//根据实体类和查询条件，批量删除数据
	public void deleteTableMoreData(Class className,String subwhere);

	//根据实体类和查询条件，批量修改数据
	public void updateTableMoreData(Class className,String setHql,String subwhere);
	
	//根据数据库表名和查询条件，批量删除数据
	public void deleteTableMoreData(String tableName,String subwhere);
	
	//根据数据库表名和查询条件，批量修改数据
	public void updateTableMoreData(String tableName,String setSql,String subwhere);
	
	//根据数据库表名和查询条件，查询数据
	public List findBySql(String tableName,String subwhere,String orderby);

	//根据数据库表名和查询条件，分页查询数据
	public List findBySqlPage(String tableName,String subwhere,String orderby,Integer currentPage,Integer pageSize);
	
	//根据数据库表名和查询条件，查询列数据
	public List findBySqlGetColumn(String tableName,String columns,String subwhere,String orderby);
	
	//根据数据库表名和查询条件，分页查询,得到列值
	public List findBySqlGetColumnPage(String tableName,String columns,String subwhere,String orderby,Integer currentPage,Integer pageSize);
	
	//根据数据库表名和查询条件，sql查询数量
	public Long getCountBySql(String tableName,String subwhere);
	
	//根据hql查询集合
	public List findByHql(String hql);
	
	//根据hql查数量
	public Integer getCountByHql(String hql);

	public Object findByHqlUnique(Class className, String subwhere);
	//
	public void deleteProvider(FWProvider old);

	public void deleteProviderClass(FWProviderType old);
	
	public void saveTableList(List<?> list);
	
	public Object executeSql(String sql);
	
}

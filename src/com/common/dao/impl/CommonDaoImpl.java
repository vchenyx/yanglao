package com.common.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.FWProvider;
import com.jinkun.main.beans.FWProviderType;
import com.jinkun.main.beans.MUserInfo;

/**
 * 
 * 说明：万能无敌dao实现类，数据库操作 作者：张彬 时间：2016-1-11
 * sessionFactory.openSession()打开session，要手动关闭session
 * sessionFactory.getCurrentSession()获取当前session，自动关闭session，不能update，delete方法
 * 对数据库的增删改操作使用：sessionFactory.openSession()
 * 对数据库的查询操作使用：sessionFactory.getCurrentSession()
 * 对数据库表进行通用的操作：Object可对类对象自动转换，Object作用是实体类
 * 
 * */
public class CommonDaoImpl extends HibernateDaoSupport implements CommonDao {

	/**
	 * 保存数据表
	 */
	@Override
	public void saveTable(Object obj) {
		try{
			this.getHibernateTemplate().save(obj);
			this.getHibernateTemplate().flush();			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 修改数据表
	 */
	@Override
	public void updateTable(Object obj) {
		this.getHibernateTemplate().update(obj);
		this.getHibernateTemplate().flush();
	}

	/**
	 * 更新数据表
	 */
	@Override
	public void renewTable(Object obj) {
		this.getHibernateTemplate().saveOrUpdate(obj);
		this.getHibernateTemplate().flush();
	}

	/**
	 * 删除数据表
	 */
	@Override
	public void deleteTable(Object obj) {
		this.getHibernateTemplate().delete(obj);
		this.getHibernateTemplate().flush();
	}

	/**
	 * 查找主键为Integer类型的数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object findByIntegerId(Class className, Integer id) {
		
		Object obj = this.getHibernateTemplate().get(className, id);
		
		return obj;
	}

	/**
	 * 查找主键为Long类型的数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object findByLongId(Class className, Long id) {
		
		Object obj = this.getHibernateTemplate().get(className, id);
		
		return obj;
	}

	/**
	 * 查找主键为String类型的数据
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object findByStringId(Class className, String id) {
		
		Object obj = this.getHibernateTemplate().get(className, id);
		
		return obj;
	}

	/**
	 * 查询数据表中总记录数
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Long getCountByHql(Class className, String subwhere) {
		
		Object obj = this
				.getHibernateTemplate()
				.find(" select count(*) "
						+ subwhereToHql(className, subwhere, "")).iterator()
				.next();
		
		if (obj == null) {
			return Long.valueOf(0);
		} else {
			return Long.valueOf(obj.toString());
		}
	}

	/**
	 * 按hql语句查询对象
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List findByHql(Class className, String subwhere, String orderby) {
		
		List list = this.getHibernateTemplate().find(
				subwhereToHql(className, subwhere, orderby));
		
		return list;
	}
	
	@Override
	public Object findByHqlUnique(Class className, String subwhere) {
		List<?> list = this.getHibernateTemplate().find(
				subwhereToHql(className, subwhere));
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 按hql分页查询对象集合
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List findByHqlPage(final Class className, final String subwhere,
			final String orderby, final Integer currentPage,
			final Integer pageSize) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(subwhereToHql(
								className, subwhere, orderby));
						query.setFirstResult((currentPage - 1) * pageSize);
						query.setMaxResults(pageSize);
						List list = query.list();
						return list;
					}
				});

	}

	/**
	 * 查询列
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List findByHqlGetColumn(Class className, String subwhere,
			String orderby, String columns) {
		
		List list = this.getHibernateTemplate().find(
				"select " + columns
						+ subwhereToHql(className, subwhere, orderby));
		
		return list;
	}

	/**
	 * 分页查询列
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List findByHqlGetColumnPage(final Class className,
			final String subwhere, final String orderby, String columns,
			final Integer currentPage, final Integer pageSize) {

		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(" select "
								+ subwhereToHql(className, subwhere, orderby));
						query.setFirstResult((currentPage - 1) * pageSize);
						query.setMaxResults(pageSize);
						List list = query.list();
						return list;
					}
				});
	}

	/*
	 * 根据查询条件删除数据(non-Javadoc)
	 * 
	 * @see com.common.dao.CommonDao#deleteTable(java.lang.Class,
	 * java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void deleteTableMoreData(Class className, String subwhere) {
		this.getHibernateTemplate().bulkUpdate(
				" delete " + subwhereToHql(className, subwhere, ""));
		this.getHibernateTemplate().flush();
	}

	/*
	 * 批量修改数据(non-Javadoc)
	 * 
	 * @see com.common.dao.CommonDao#updateTableMoreData(java.lang.Class,
	 * java.lang.String[], java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void updateTableMoreData(Class className, String setHql,
			String subwhere) {
		this.getHibernateTemplate().bulkUpdate(
				updwhereToHql(className, setHql, subwhere));
		this.getHibernateTemplate().flush();
	}

	// --------执行sql----------

	/*
	 * 批量删除数据
	 */
	@Override
	public void deleteTableMoreData(final String tableName,
			final String subwhere) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(" delete "
						+ subwhereToSql(tableName, subwhere, ""));
				query.executeUpdate();
				session.clear();
				return true;
			}

		});
	}

	/*
	 * 批量修改数据(non-Javadoc)
	 * 
	 * @see com.common.dao.CommonDao#updateTableMoreData(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void updateTableMoreData(final String tableName,
			final String setSql, final String subwhere) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(updwhereToSql(tableName,
						setSql, subwhere));
				query.executeUpdate();
				session.clear();
				return true;
			}

		});
	}
	
	@Override
	public void updateColumnBySql(final String sql) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(sql);
				query.executeUpdate();
				session.clear();
				return true;
			}
		});
	}
	

	// 根据数据库表名和查询条件，查询数据
	@SuppressWarnings("rawtypes")
	@Override
	public List findBySql(final String tableName, final String subwhere,
			final String orderby) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException {
						List list = session.createSQLQuery(
								"select * "
										+ subwhereToSql(tableName, subwhere,
												orderby)).list();
						return list;
					}

				});
	}

	// 根据数据库表名和查询条件，分页查询数据
	@SuppressWarnings("rawtypes")
	@Override
	public List findBySqlPage(final String tableName, final String subwhere,
			final String orderby, final Integer currentPage,
			final Integer pageSize) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(" select * "
								+ subwhereToSql(tableName, subwhere, orderby));
						query.setFirstResult((currentPage - 1) * pageSize);
						query.setMaxResults(pageSize);
						List list = query.list();
						return list;
					}

				});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findBySqlGetColumn(final String tableName,
			final String columns, final String subwhere, final String orderby) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException {
						List list = session.createSQLQuery(
								" select "
										+ columns
										+ subwhereToSql(tableName, subwhere,
												orderby)).list();
						return list;
					}

				});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findBySqlGetColumnPage(final String tableName,
			final String columns, final String subwhere, final String orderby,
			final Integer currentPage, final Integer pageSize) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(" select "
								+ columns
								+ subwhereToSql(tableName, subwhere, orderby));
						query.setFirstResult((currentPage - 1) * pageSize);
						query.setMaxResults(pageSize);
						// 将查询到的记录保存到list中
						List list = query.list();
						return list;
					}

				});

	}

	/*
	 * 根据数据库表名查sql语句(non-Javadoc)
	 * 
	 * @see com.common.dao.CommonDao#getCountBySql(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Long getCountBySql(final String tableName, final String subwhere) {
		return (Long) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException {
						Object obj = session
								.createSQLQuery(
										" select count(*) "
												+ subwhereToSql(tableName,
														subwhere, "")).list()
								.iterator().next();
						if (obj == null) {
							return Long.valueOf(0);
						} else {
							return Long.valueOf(obj.toString());
						}
					}

				});
	}

	// 对数据库进行hql语句生成
	@SuppressWarnings("rawtypes")
	private String subwhereToHql(Class className, String subwhere,
			String orderby) {
		String hql = " from " + className.getSimpleName() + " ";
		// 判断是否有条件
		if (subwhere != null && subwhere.trim().length() > 0) {
			hql += " where " + subwhere;
		}
		// 判断是否有排序
		if (orderby != null && orderby.trim().length() > 0) {
			hql += " order by " + orderby;
		}
		// 返回hql语句
		return hql;
	}
	// 对数据库进行hql语句生成
	@SuppressWarnings("rawtypes")
	private String subwhereToHql(Class className, String subwhere) {
		String hql = " from " + className.getSimpleName() + " ";
		// 判断是否有条件
		if (subwhere != null && subwhere.trim().length() > 0) {
			hql += " where " + subwhere;
		}
		// 返回hql语句
		return hql;
	}

	// 对数据库进行sql语句生成
	private String subwhereToSql(String tableName, String subwhere,
			String orderby) {
		String sql = " from " + tableName;
		// 判断是否有条件
		if (subwhere != null && subwhere.trim().length() > 0) {
			sql += " where " + subwhere;
		}
		if (orderby != null && orderby.trim().length() > 0) {
			sql += " order by " + orderby;
		}
		// 返回sql语句
		return sql;
	}

	// Hql修改语句生成
	@SuppressWarnings("rawtypes")
	private String updwhereToHql(Class className, String setHql, String subwhere) {
		String hql = " update " + className.getSimpleName() + " set " + setHql;
		// 判断是否有条件
		if (subwhere != null && subwhere.trim().length() > 0) {
			hql += " where " + subwhere;
		}
		return hql;
	}

	// sql修改语句生成
	private String updwhereToSql(String tableName, String setSql,
			String subwhere) {
		String sql = " update " + tableName + " set " + setSql;
		// 判断是否有条件
		if (subwhere != null && subwhere.trim().length() > 0) {
			sql += " where " + subwhere;
		}
		return sql;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByHql(String hql) {
		return this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public Integer getCountByHql(String hql) {
		Object obj = this.getHibernateTemplate().find(hql).iterator().next();
		if(obj == null){
			return 0;
		}else{
			return Integer.parseInt(obj.toString());
		}
	}
	
	public void updateInfoById(){
		
	}

	@Override
	public void deleteProvider(FWProvider old) {
		this.getHibernateTemplate().delete(old);
		this.getHibernateTemplate().flush();
	}

	@Override
	public void deleteProviderClass(FWProviderType old) {
		this.getHibernateTemplate().delete(old);
		this.getHibernateTemplate().flush();
	}
	
	@Override
	public void saveTableList(final List list) {
		try{
			this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
				@Override
				public Object doInHibernate(Session session) {
					for (Object object : list) {
						session.save(object);
					}
					session.flush();
					session.clear();
					return null;
				}
			});
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	@Override
	public Object executeSql(final String sql) {
		try{
			this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
				@Override
				public Object doInHibernate(Session session) {
					SQLQuery createSQLQuery = session.createSQLQuery(sql);
					return createSQLQuery.executeUpdate();
				}
			});
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

}

package com.openfire.service;

/**
 * 系统属性接口
 * @author zhangbin
 *
 */
public interface PropertyService {

	//按属性名查询属性值
	public String findByName(String name);
	
}

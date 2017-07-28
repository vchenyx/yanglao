package com.openfire.service;

import com.openfire.beans.OfUser;

/**
 * openfire用户接口
 * @author zhangbin
 *
 */
public interface UserService {

	//添加用户
	public void saveUser(String username,String password,String passwordkey,String name);
	
	//更新用户
	public void updateUser(OfUser user,String password,String passwordkey,String name);
	
	//按用户名查找用户
	public OfUser findByUsername(String username);
	
}

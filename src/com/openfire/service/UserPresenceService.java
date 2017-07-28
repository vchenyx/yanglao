package com.openfire.service;

/**
 * 用户离线接口
 * @author zhangbin
 *
 */
public interface UserPresenceService {

	//添加用户离线
	public void saveUserPresence(String username);
	
	//判断用户是否离线
	public boolean isPresence(String username);
	
}

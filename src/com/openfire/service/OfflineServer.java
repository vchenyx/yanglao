package com.openfire.service;

import java.util.List;

/**
 * 添加离线信息
 * @author zhangbin
 *
 */
public interface OfflineServer {

	//添加离线信息
	public void saveOffline(String jid,String username,String message);
	
	//删除离线消息
	public void deleteOffline(String username);
	
	//按用户名查离线信息
	public List findByUsername(String username);
	
}

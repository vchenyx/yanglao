package com.openfire.service;

import java.util.List;

import com.openfire.beans.OfRoster;

/**
 * 
 * 好友管理接口
 * @author zhangbin
 *
 */
public interface RosterService {
	
	//添加好友关系和分组
	public void saveRoster(String username,String nick,String domain,String groupName);
	
	//查找好友关系
	public List<OfRoster> findByUsernameAndNick(String username,String nick);
	
}

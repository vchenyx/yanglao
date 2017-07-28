package com.openfire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.openfire.beans.OfRoster;
import com.openfire.beans.OfRosterGroups;
import com.openfire.beans.OfRosterGroupsId;
import com.openfire.service.RosterService;

/**
 * 
 * 好友管理接口
 * @author zhangbin
 *
 */
@Service
public class RosterServiceImpl implements RosterService {
	
	@Autowired
	private CommonDao commonDao;
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	//添加好友关系
	@Override
	public void saveRoster(String username,String nick,String domain,String groupName) {
		
		//创建OfRoster
		OfRoster roster = new OfRoster();
		//设置邀请者
		roster.setUsername(username);
		//设置Jid
		roster.setJid(nick+"@"+domain);
		//设置状态：1,邀请，2被邀请，3双方同意
		roster.setSub(Short.valueOf("3"));
		//设置访问参数
		roster.setAsk(Short.valueOf("-1"));
		//设置接收参数
		roster.setRecv(Short.valueOf("-1"));
		//设置被邀请者
		roster.setNick(nick);
		
		//保存到数据库
		commonDao.saveTable(roster);
		
		saveRosterGroup(roster.getRosterId(),Short.valueOf("0"),groupName);
		
	}

	public void saveRosterGroup(long rosterid, Short rank, String groupName) {
		//创建主键类：OfRosterGroupsId
		OfRosterGroupsId groupid = new OfRosterGroupsId();
		//创建分组OfRosterGroupsId
		OfRosterGroups groups = new OfRosterGroups();
		
		//设置id
		groupid.setRosterId(rosterid);
		//设置排序
		groupid.setRank(rank);
		//设置主键
		groups.setId(groupid);
		//设置组名
		groups.setGroupName(groupName);

		//保存到数据库
		commonDao.saveTable(groups);
		
	}

	/*
	 * 查找好友关系(non-Javadoc)
	 * @see com.openfire.service.RosterService#findByUsernameAndNick(java.lang.String, java.lang.String)
	 */
	@Override
	public List<OfRoster> findByUsernameAndNick(String username, String nick) {
		return (List<OfRoster>)commonDao.findByHql(OfRoster.class, "username='"+username+"' and nick='"+nick+"'", "");
	}

}

package com.openfire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.openfire.beans.OfPresence;
import com.openfire.service.UserPresenceService;

/**
 * 用户离线接口实现类
 * @author zhangbin
 *
 */
@Service
public class UserPresenceServiceImpl implements UserPresenceService {

	@Autowired
	private CommonDao commonDao;
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	/*
	 * 添加离线信息(non-Javadoc)
	 * @see com.phone.service.UserPresence#saveUserPresence()
	 */
	@Override
	public void saveUserPresence(String username) {
		
		//创建离线类
		OfPresence presence = new OfPresence();
		//设置用户名
		presence.setUsername(username);
		//设置时间戳
		presence.setOfflineDate(String.valueOf(System.currentTimeMillis()));
		//保存到数据库
		commonDao.saveTable(presence);
		
	}
	
	
	/*
	 * 判断用户是否离线(non-Javadoc)
	 * @see com.openfire.service.UserPresenceService#isPresence(java.lang.String)
	 */
	@Override
	public boolean isPresence(String username) {
		OfPresence presence = (OfPresence)commonDao.findByStringId(OfPresence.class, username);
		if(presence == null){
			return false;
		}
		return true;
	}

	

}

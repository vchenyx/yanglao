package com.openfire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.openfire.beans.OfUser;
import com.openfire.service.UserService;
import com.openfire.util.Blowfish;
/**
 * openfire用户接口实现类
 * @author zhangbin
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CommonDao commonDao;
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	/*
	 * 添加用户(non-Javadoc)
	 * @see com.openfire.service.UserService#saveUser(com.openfire.beans.OfUser)
	 */
	@Override
	public void saveUser(String username,String password,String passwordkey,String name) {
		
		//获取时间戳
		String time = String.valueOf(System.currentTimeMillis());
		//创建openfire用户
		OfUser user = new OfUser();
		//设置用户名
		user.setUsername(username);
		//设置密码
		user.setEncryptedPassword(new Blowfish(passwordkey).encryptString(password));
		//设置姓名
		user.setName(name);
		//设置创建时间
		user.setCreationDate(time);
		//设置更新时间
		user.setModificationDate(time);
		//保存到数据库
		commonDao.saveTable(user);
		
	}
	
	/*
	 * 更新用户(non-Javadoc)
	 * @see com.openfire.service.UserService#updateUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateUser(OfUser user,String password,
			String passwordkey, String name) {
		//设置密码
		user.setEncryptedPassword(new Blowfish(passwordkey).encryptString(password));
		//设置姓名
		user.setName(name);
		//更新数据库
		commonDao.updateTable(user);
	}
	/*
	 * 按用户名查找用户(non-Javadoc)
	 * @see com.openfire.service.UserService#findByUsername(java.lang.String)
	 */
	@Override
	public OfUser findByUsername(String username) {
		return (OfUser)commonDao.findByStringId(OfUser.class, username);
	}



}

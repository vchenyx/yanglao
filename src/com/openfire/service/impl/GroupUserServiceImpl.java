package com.openfire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.openfire.beans.OfGroupUser;
import com.openfire.beans.OfGroupUserId;
import com.openfire.service.GroupUserService;

@Service
public class GroupUserServiceImpl implements GroupUserService {

	@Autowired
	private CommonDao commonDao;
	public CommonDao getCommonDao() {
		return commonDao;
	}
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	/*
	 * 添加人员组名(non-Javadoc)
	 * @see com.phone.service.UserGroupService#saveUserGroup(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveUserGroup(String groupName, String username) {
		
		//创建人员分组对象
		OfGroupUser groupuser = new OfGroupUser();
		//创建主键类对象
		OfGroupUserId groupuserId = new OfGroupUserId();
		
		//设置管理员属性
		groupuserId.setAdministrator(Short.valueOf("0"));
		//设置组名
		groupuserId.setGroupName(groupName);
		//设置用户名
		groupuserId.setUsername(username);

		//设置人员分组主键类
		groupuser.setId(groupuserId);
		
		//保存到数据库
		commonDao.saveTable(groupuser);

	}
	
	/*
	 * 删除人员组名(non-Javadoc)
	 * @see com.phone.service.UserGroupService#deleteUserGroup(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteUserGroup(String groupName, String username) {
		commonDao.deleteTableMoreData("ofGroupUser", "groupName='"+groupName+"' and username='"+username+"' and administrator=0");
	}

	/*
	 * 验证用户组是否存在(non-Javadoc)
	 * @see com.openfire.service.GroupUserService#isExistUserGroup(java.lang.String, java.lang.String, short)
	 */
	@Override
	public boolean isExistUserGroup(String groupName, String username,
			short administrator) {
		List list = commonDao.findByHql(OfGroupUser.class, "groupName='"+groupName+"' and username='"+username+"' and administrator = "+administrator, "");
		if(list.isEmpty() || list.size() == 0){
			return false;
		}
		return true;
	}

}

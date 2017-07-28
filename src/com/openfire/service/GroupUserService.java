package com.openfire.service;

/**
 * 分组下的用户接口
 * @author zhangbin
 *
 */
public interface GroupUserService {

	//添加人员部门：部门名，用户名
	public void saveUserGroup(String groupName,String username);
	
	//删除人员部门
	public void deleteUserGroup(String groupName,String username);
	
	//验证用户组是否存在
	public boolean isExistUserGroup(String groupName,String username,short administrator);
	
}

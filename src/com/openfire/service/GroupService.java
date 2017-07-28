package com.openfire.service;

/**
 * 手机分组接口
 * @author zhangbin
 *
 */
public interface GroupService {
	
	//添加组名，名字：部门名称，描述：部门id
	public void saveGroup(String groupName,String description);
	
	//修改组名
	public void updateGroup(String oldGroupName,String newGroupName);
	
	//删除指定组名
	public void deleteGroup(String groupName);
	
	//删除不存在的组名
	public void deleteUnExistGroup(String[] groupName);
	
	//验证用户组是否存在
	public boolean isExistGroup(String groupName);
	
}

package com.openfire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.openfire.beans.OfGroup;
import com.openfire.beans.OfGroupProp;
import com.openfire.beans.OfGroupPropId;
import com.openfire.service.GroupService;

/**
 * 手机分组接口实现类
 * @author zhangbin
 *
 */
@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private CommonDao commonDao;
	public CommonDao getCommonDao() {
		return commonDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	/*
	 * 添加组名(non-Javadoc)
	 * @see com.phone.service.groupService#saveGroup(java.lang.String)
	 */
	@Override
	public void saveGroup(String groupName,String description) {
		
		//创建Ofgroup对象
		OfGroup group = new OfGroup();
		//设置描述：部门id
		group.setDescription(description);
		//设置组名：部门名
		group.setGroupName(groupName);
		//添加Ofgroup
		commonDao.saveTable(group);
		
		//创建Ofgroupprop对象
		OfGroupProp  groupprop = new OfGroupProp();
		//创建OfgrouppropId对象
		OfGroupPropId grouppropid = new OfGroupPropId();
		
		//设置组名
		grouppropid.setGroupName(groupName);
		//设置名字
		grouppropid.setName("sharedRoster.displayName");
		//设置OfgrouppropId
		groupprop.setId(grouppropid);
		groupprop.setPropValue("");
		//添加Ofgroupprop
		commonDao.saveTable(groupprop);
	
		//设置名字
		grouppropid.setName("sharedRoster.groupList");
		//设置OfgrouppropId
		groupprop.setId(grouppropid);
		groupprop.setPropValue("");
		//添加Ofgroupprop
		commonDao.saveTable(groupprop);

		//设置名字
		grouppropid.setName("sharedRoster.showInRoster");
		//设置OfgrouppropId
		groupprop.setId(grouppropid);
		//设置值
		groupprop.setPropValue("nobody");
		//添加Ofgroupprop
		commonDao.saveTable(groupprop);
		
	}
	/*
	 * 修改组名(non-Javadoc)
	 * @see com.phone.service.groupService#updateGroup(java.lang.String)
	 */
	@Override
	public void updateGroup(String groupNameOld,String groupNameNew) {
	
		//修改Ofgroup
		commonDao.updateTableMoreData(OfGroup.class, "groupName = '"+groupNameNew+"'", "groupName = '"+groupNameOld+"'");
		//修改ofgroupprop
		commonDao.updateTableMoreData("ofGroupProp", "groupName = '"+groupNameNew+"'", "groupName = '"+groupNameOld+"'");
	
	}
	/*
	 * 删除组名(non-Javadoc)
	 * @see com.phone.service.groupService#deleteGroup(java.lang.String)
	 */
	@Override
	public void deleteGroup(String groupName) {
		
		//删除Ofgroup
		commonDao.deleteTableMoreData(OfGroup.class, "groupName = '"+groupName+"'");
		//删除ofgroupprop
		commonDao.deleteTableMoreData("ofGroupProp", "groupName = '"+groupName+"'");

	}

	/*
	 * 删除不存在的组名(non-Javadoc)
	 * @see com.phone.service.groupService#findByGroupname()
	 */
	@Override
	public void deleteUnExistGroup(String[] groupName) {
		String names = "";
		for(int i=0;i<groupName.length;i++){
			if(i>0)names += ",";
			names+= "'"+groupName[i]+"'";
		}
		//删除Ofgroup
		commonDao.deleteTableMoreData(OfGroup.class, "groupName not in ("+names+")");
		//删除ofgroupprop
		commonDao.deleteTableMoreData("ofGroupProp", "groupName not in ("+names+")");
	}
	
	/*
	 * 验证用户组是否存在(non-Javadoc)
	 * @see com.openfire.service.GroupService#isExist(java.lang.String)
	 */
	@Override
	public boolean isExistGroup(String groupName) {
		OfGroup group = (OfGroup)commonDao.findByStringId(OfGroup.class, groupName);
		if(group == null){
			return false;
		}
		return true;
	}
	

}

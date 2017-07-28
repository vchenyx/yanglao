package com.jinkun.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.MGuardianInfo;
import com.jinkun.main.beans.MNurseInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.service.RegisterService;
import com.openfire.beans.OfUser;
import com.openfire.util.Blowfish;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public boolean isNotExist(String account) {
		List list = commonDao.findByHql(MUserInfo.class, " account = '" + account + "' ", "");
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	@Override
	public MUserInfo saveUserInfo(MUserInfo userInfo) {
		commonDao.saveTable(userInfo);
		return userInfo;
	}
	
	@Override
	public Integer saveOldPeople(MOldPeopleInfo oldPeople) {
		commonDao.saveTable(oldPeople);
		return oldPeople.getId();
	}
	
	@Override
	public Integer saveGuardian(MGuardianInfo giuardian) {
		commonDao.saveTable(giuardian);
		return giuardian.getId();
	}
	
	@Override
	public Integer saveNurse(MNurseInfo nurse) {
		commonDao.saveTable(nurse);
		return nurse.getId();
	}
	
	@Override
	public Integer savePensionStation(MPensionStationInfo pensionStation) {
		commonDao.saveTable(pensionStation);
		return pensionStation.getId();
	}
	
	/**
	 * 保存openFire账号
	 */
	@Override
	public void saveOpenFireUser(MUserInfo sysUser) {
		String account = sysUser.getAccount();
		String pwd = sysUser.getPwd();
		String name = sysUser.getName();
		Integer areaId = sysUser.getAreaId();
		Integer userType = sysUser.getUserType();
		OfUser ofUser = new OfUser();
		//获取时间戳
		String time = String.valueOf(System.currentTimeMillis());
		//创建openfire用户
		OfUser user = new OfUser();
		//设置用户名
		user.setUsername(account);
		//设置密码
		user.setPlainPassword(pwd);
		user.setEncryptedPassword(pwd);
		//设置姓名
		user.setName(name);
		//设置创建时间
		user.setCreationDate(time);
		//设置更新时间
		user.setModificationDate(time);
		commonDao.saveTable(ofUser);
	}
	
	@Override
	public void updateOpenFireUser(MUserInfo sysUser) {
		String account = sysUser.getAccount();
		String pwd = sysUser.getPwd();
		String name = sysUser.getName();
		Integer areaId = sysUser.getAreaId();
		Integer userType = sysUser.getUserType();
		OfUser ofUser = new OfUser();
		//获取时间戳
		String time = String.valueOf(System.currentTimeMillis());
		//创建openfire用户
		OfUser user = new OfUser();
		//设置用户名
		user.setUsername(account);
		//设置密码
		user.setPlainPassword(pwd);
		user.setEncryptedPassword(pwd);
		//设置姓名
		user.setName(name);
		//设置创建时间
		user.setCreationDate(time);
		//设置更新时间
		user.setModificationDate(time);
		commonDao.updateTable(ofUser);
	}
}

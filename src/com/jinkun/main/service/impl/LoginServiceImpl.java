package com.jinkun.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.common.util.MD5;
import com.jinkun.main.beans.MGuardianInfo;
import com.jinkun.main.beans.MNurseInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.form.PeopleInfoBean;
import com.jinkun.main.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public Map<String, Object> login(MUserInfo userInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		String account = userInfo.getAccount();
		String pwd = userInfo.getPwd();
		if (StringUtils.isNotEmpty(account)) {
			if (StringUtils.isNotEmpty(pwd)) {
				List<MUserInfo> list = commonDao.findByHql(MUserInfo.class, " account = '" + account + "' ", "");
				if (!list.isEmpty()) {
					MUserInfo sysUser = list.get(0);
					if (MD5.md5Encode(pwd).equals(sysUser.getPwd())) {//登录成功
						map.put("user", sysUser);
						map.put("userType", sysUser.getUserType());
						map.put("flag", 5);
					} else {//密码不正确
						map.put("flag", 4);
					}
				} else {//账号不正确
					map.put("flag", 3);
				}
			} else {//账号为空
				map.put("flag", 2);
			}
		} else {//密码为空
			map.put("flag", 1);
		}
		return map;
	}
	
	/**
	 * 手机登录获取人员信息
	 */
	@Override
	public PeopleInfoBean getPeopleInfo(MUserInfo sysUser) {
		PeopleInfoBean pif = new PeopleInfoBean();
		String userId = sysUser.getId();
		Integer userType = sysUser.getUserType();
		pif.setUserId(userId);
		pif.setAccount(sysUser.getAccount());
		pif.setUserType(sysUser.getUserType());
		if (userType == 0) {//老人
			List<MOldPeopleInfo> list = commonDao.findByHql(MOldPeopleInfo.class, " userId = '" + userId + "' ", "");
			if (!list.isEmpty()) {
				MOldPeopleInfo old = list.get(0);
				pif.setName(old.getName());
//				pif.setAge(old.getAge());
				pif.setSex(old.getSex());
//				pif.setHeadImg(old.getHeadImg());
				pif.setPensionId(old.getPensionId());
			}
		}
		if (userType == 1) {//监护人
			List<MGuardianInfo> list = commonDao.findByHql(MGuardianInfo.class, " userId = '" + userId + "' ", "");
			if (!list.isEmpty()) {
				MGuardianInfo guar = list.get(0);
				pif.setName(guar.getName());
				pif.setAge(guar.getAge());
				pif.setSex(guar.getSex());
				pif.setHeadImg(guar.getHeadImg());
			}
		}
		if (userType == 2) {//护工
			List<MNurseInfo> list = commonDao.findByHql(MNurseInfo.class, " userId = '" + userId + "' ", "");
			if (!list.isEmpty()) {
				MNurseInfo nurse = list.get(0);
				pif.setName(nurse.getName());
				pif.setAge(nurse.getAge());
				pif.setSex(nurse.getSex());
				pif.setHeadImg(nurse.getHeadImg());
				pif.setPensionId(nurse.getPensionStationId());
			}
		}
		return pif;
	}
}

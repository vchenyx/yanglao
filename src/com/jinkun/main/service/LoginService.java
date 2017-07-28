package com.jinkun.main.service;

import java.util.Map;

import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.form.PeopleInfoBean;

public interface LoginService {

	Map<String, Object> login(MUserInfo userInfo);

	PeopleInfoBean getPeopleInfo(MUserInfo sysUser);

}

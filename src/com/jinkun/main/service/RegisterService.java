package com.jinkun.main.service;

import com.jinkun.main.beans.MGuardianInfo;
import com.jinkun.main.beans.MNurseInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;

public interface RegisterService {

	MUserInfo saveUserInfo(MUserInfo userInfo);

	Integer saveOldPeople(MOldPeopleInfo oldPeople);

	Integer saveGuardian(MGuardianInfo giuardian);

	Integer saveNurse(MNurseInfo nurse);

	Integer savePensionStation(MPensionStationInfo pensionStation);

	boolean isNotExist(String account);

	void saveOpenFireUser(MUserInfo sysUser);

	void updateOpenFireUser(MUserInfo sysUser);

}

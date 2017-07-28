package com.jinkun.main.service;

import com.jinkun.main.beans.AAuditStationInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;

public interface AdminService {

	String saveUserInfo(MUserInfo userInfo);

	void updatePensionStation(MPensionStationInfo pensionStation);

	void saveAuditStationInfo(AAuditStationInfo auditStation);

}

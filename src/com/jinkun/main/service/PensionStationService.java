package com.jinkun.main.service;

import com.jinkun.main.beans.FWProvider;
import com.jinkun.main.beans.FWProviderType;
import com.jinkun.main.beans.MOldPeopleInfo;

public interface PensionStationService {

	void deleteNurse(Integer id);

	void updateOldBindDevice(MOldPeopleInfo old);

	void deleteOlder(MOldPeopleInfo old);

	void deleteProvider(FWProvider old);

	void deleteProviderClass(FWProviderType old);


}

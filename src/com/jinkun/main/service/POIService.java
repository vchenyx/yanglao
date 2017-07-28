package com.jinkun.main.service;

import java.util.List;

import com.jinkun.main.beans.UnderpinAssist;

public interface POIService {

	void saveUnderpinAssistList(List<UnderpinAssist> dbList);
	void saveUnderpinAssist(UnderpinAssist underpinAssist);

}

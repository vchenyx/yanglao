package com.jinkun.main.service;

import com.jinkun.main.beans.AAlarmInfo;
import com.jinkun.main.beans.LLocationLastInfo;
import com.jinkun.main.beans.LLocationTrackInfo;

public interface LocationService {

	void saveLocationLast(LLocationLastInfo lastLoca);

	void saveLocationTrack(LLocationTrackInfo trackLoca);

	void updateLocationLast(LLocationLastInfo lastLoca);
	
	void saveLocationTrackAsync(LLocationTrackInfo trackLoca);
	
	void updateAlarmInfo(LLocationTrackInfo trackLoca);
	
	AAlarmInfo saveAlarmInfo(LLocationTrackInfo trackLoca);

}

package com.jinkun.main.service;

import com.jinkun.main.beans.PPushInfo;

public interface PushService {

	void savePushInfo(PPushInfo pushInfo);

	void saveOffline(String server_ip, String account, String send_msg);

}

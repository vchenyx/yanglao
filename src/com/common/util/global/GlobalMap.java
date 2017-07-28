package com.common.util.global;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

public class GlobalMap {

	/**
	 * 全局的告警map
	 */
	public static ConcurrentHashMap<String, Long> alarmMap = new ConcurrentHashMap<String, Long>();
}

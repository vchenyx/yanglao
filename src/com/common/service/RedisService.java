package com.common.service;

import java.util.Map;

public interface RedisService {
	public void addToHash(String key,String hashkey,String hashvalue);
	public Boolean isHasKey(String key,String hashkey);
	public void delToHash(String key,String hashkey);
	public Map<String,String> getHash(String str);
	public String getHash(String key, String field);
}

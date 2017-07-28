package com.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.service.RedisService;

@Service
@Transactional
public class RedisServiceImpl implements RedisService{

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void addToHash(String key, String hashkey, String hashvalue) {

        HashOperations<String, String, String> hp = redisTemplate.opsForHash();

        Map<String, String> hash = new HashMap<String,String>();
        
        hash.put(hashkey,hashvalue);
	        
        hp.putAll(key, hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean isHasKey(String key, String hashkey) {
		
		HashOperations<String, String, String> hp = redisTemplate.opsForHash();
        return hp.hasKey(key, hashkey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delToHash(String key, String hashkey) {
		 HashOperations<String, String, String> hp = redisTemplate.opsForHash();
	     hp.delete(key, hashkey);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getHash(String str) {
		HashOperations<String, String, String> hp = redisTemplate.opsForHash();
        Map<String,String> results = hp.entries(str);
        return results;
	}
	
	@Override
	public String getHash(String key, String field) {
		HashOperations<String, String, String> hp = redisTemplate.opsForHash();
		String string = hp.get(key, field);
		return string;
	}
	
}

package com.common.service;

import java.util.Map;

public interface HttpClient {

	public String doGet(String url);
	
	public String doGet(String url, Map<String, Object> param);
	
	public String doPost(String url, Map<String, Object> params);
	
	public String doPost(String url);
}

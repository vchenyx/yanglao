package com.common.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	/**
	 * 字符串转bean
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static <T> T strToBean(String str, Class<T> clazz) {
		ObjectMapper objMper = new ObjectMapper();
		try {
			return objMper.readValue(str, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * bean转Json字符串
	 * @param bean
	 * @return
	 */
	public static String beanToStr(Object bean) {
		ObjectMapper mapper = new ObjectMapper();  
        try {
			return mapper.writeValueAsString(bean);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	/**
	 * Map<String, Object>转bean
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();  
        try {
        	return mapper.readValue(beanToStr(map), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
	
	/**
	 * bean转Map<String, Object>
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		ObjectMapper mapper = new ObjectMapper();  
		try {
			return mapper.readValue(beanToStr(bean), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

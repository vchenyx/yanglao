package com.common.util;

import java.util.UUID;


public class CommonUtil {

	//生成tooken
	public static String creatTooken(){
		return UUID.randomUUID().toString();
	}

	//得到MD5加密后的字符串
	public static String getMd5Str(String str){
		return MD5.md5Encode(str);
	}
	
}

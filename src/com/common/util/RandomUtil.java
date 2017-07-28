package com.common.util;

import java.util.Random;

public class RandomUtil {
	
	public static final String pensionStation = "PS";
	
	public static String strArr[] = {
										"1", "2", "3", "4",
										"5", "6", "7", "8",
										"9", "0", "A", "B",
										"C", "D", "E", "F",
										"G", "H", "I", "J",
										"K", "L", "M", "N",
										"O", "P", "Q", "R",
										"S", "T", "U", "V",
										"W", "X", "Y", "Z",
									};

	
	/**
	 * 生成图片后5位随机数
	 * @return
	 */
	public static String getRandom5() {
		Random r = new Random();
		String rtnStr = "";
		for (int i = 0; i < 5; i++) {
			int nextInt = r.nextInt(36);
			rtnStr += strArr[nextInt];
		}
		return rtnStr;
	}
	
	public static String getPensionStationAccount(Integer areaCode) {
		String str = pensionStation + areaCode;
		return str;
	}
}

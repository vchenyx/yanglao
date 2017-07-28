package com.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ConvertNum {
	
	//保留小数后n位
	public static Double keepDecimalNum(Double value,Integer figure){
		return new BigDecimal(value).setScale(figure,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//显示小数点后n位
	public static String showDecimalNum(Double value,Integer figure){
		return new BigDecimal(value).setScale(figure,BigDecimal.ROUND_HALF_UP).toString();
	}

	//显示金钱格式
	public static String showMoneyFormat(Double value){
		return new DecimalFormat(",###.##").format(value);
	}
	
	
}

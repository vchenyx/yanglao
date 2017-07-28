package com.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

public class DateUtil {

	/**
	 * 字符串转日期(传null返回当前日期)
	 * yyyy-MM-dd HH:mm:ss
	 * @param dateStr
	 * @return
	 */
	public static Date strToDateDefault(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (StringUtils.isNoneEmpty(dateStr)) {
				return sdf.parse(dateStr);
			} else {
				return new Date();
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String longToYMD(Long dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(dateTime));
		
	}
	
	/**
	 * 默认字符串转毫秒值
	 * @param yyyy-MM-dd HH:mm:ss 格式的字符串
	 * @return 毫秒值
	 */
	public static Long strToLongDate(String dateStr) {
		return strToLongDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 字符串转毫秒值
	 * @param 日期类型的字符串
	 * @param 字符串的格式
	 * @return 毫秒值
	 */
	public static Long strToLongDate(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 日期转字符串 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String dateToStrDefault(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 获取毫秒值 yyyy-MM-dd HH:mm:ss
	 * @param dateStr
	 * @return
	 */
	public static Long strToLong(String dateStr) {
		return strToDateDefault(dateStr).getTime();
	}
	
	/**
	 * 默认日期转YMD
	 * @param date
	 * @return
	 */
	public static Date dateToYMDDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(date);
		try {
			return sdf.parse(format);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取当天开始时间的毫秒值
	 * @return
	 */
	public static Long getStartTimeDay() {
		Date dateToYMDDate = dateToYMDDate(new Date());
		return dateToYMDDate.getTime();
	}
	
	/**
	 * 获取当天结束时间的毫秒值
	 * @return
	 */
	public static Long getEndTimeDay() {
		Date dateToYMDDate = dateToYMDDate(new Date());
		return dateToYMDDate.getTime() + 24 * 60 * 60 * 1000 - 1;
	}
	
	/**
	 * 获取本周的开始时间毫秒值
	 * @param date
	 * @return
	 */
	public static Long getStartTimeWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date time = cal.getTime();
		return dateToYMDDate(time).getTime();
	}
	
	/**
	 * 获取本周结束时间的毫秒值
	 * @param date
	 * @return
	 */
	public static Long getEndTimeWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date time = cal.getTime();
		return dateToYMDDate(time).getTime() + 24 * 60 * 60 * 1000 - 1;
	}
	
	/**
	 * 获取本月开始的毫秒值
	 * @param date
	 * @return
	 */
	public static Long getStartTimeMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		Date time = cal.getTime();
		Date dateToYMDDate = dateToYMDDate(time);
		return dateToYMDDate.getTime();
	}
	
	/**
	 * 获取本月结束的毫秒值
	 * @param date
	 * @return
	 */
	public static Long getEndTimeMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		Date dateToYMDDate = dateToYMDDate(time);
		Long endTime = dateToYMDDate.getTime() + 24 * 60 * 60 * 1000 - 1;
		System.out.println(new Date(endTime));
		return endTime;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(new Date(getEndTimeDay()));
	}
	
	@SuppressWarnings("deprecation")
	public static Integer getAgeByLong(Long birthTime) {
		int birYear = new Date(birthTime).getYear();
		return (new Date().getYear()) - birYear;
	}
	
	public static Long getTodayYMDLong() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		try {
			return sdf.parse(format).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}

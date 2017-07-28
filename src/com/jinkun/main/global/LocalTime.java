package com.jinkun.main.global;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalTime {

	public static String getDateTime() {
		Date now = new Date();
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");// 日期时间格式
		String datetime = datetimeFormat.format(now);
		return datetime;
	}

	public static String getDate() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 日期格式
		String date = dateFormat.format(now);
		return date;
	}

	public static String getTime() {
		Date now = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");// 时间格式
		String time = timeFormat.format(now);
		return time;
	}
}

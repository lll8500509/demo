package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String getStringByDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String getStringByDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String getStringByDate(Long date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(date));
	}

	public static Date getDateByString(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dateHour(Date date , int x) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, x);
		String time = getStringByDate(cal.getTime(),"yyyyMMddHHmmsss");
		return time;
	}
	
}

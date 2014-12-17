package com.xtrip.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author longnh
 */
public class DateHelper {

	public static DateFormat GMT;
	static{
		synchronized (DateHelper.class) {
			GMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			GMT.setTimeZone(TimeZone.getTimeZone("GMT"));			
		}
	}
	
	public static long getBinIndex(Date start_time, long interval,
			Date created_at) {
		long diff = created_at.getTime() - start_time.getTime();
		if (diff < 0 || interval <= 0) return -1l;		
		return diff / (interval*1000);
	}
	
	
	
}

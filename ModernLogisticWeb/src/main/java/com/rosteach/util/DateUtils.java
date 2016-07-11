package com.rosteach.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtils {
	
	public static Date asDate(LocalDate localDate) {
		 java.util.Date utilDate = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    return sqlDate;
	}

	public static Date asDate(LocalDateTime localDateTime) {
		java.util.Date utilDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}

	/*public static LocalDate asLocalDate(Date date) {
	    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date) {
	    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}*/
}

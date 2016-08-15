package com.rosteach.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.xml.datatype.XMLGregorianCalendar;

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
	
	public static String getNextDate(){
		LocalDate date = LocalDate.now();
		String nextDate = "";
		if(date.getDayOfWeek().getValue()==5){
			nextDate=date.plusDays(3).toString();
		}else{
			nextDate=date.plusDays(1).toString();//.plusDays(1).toString();
		} 
		return nextDate;
	}
	public static String getFormatDateXML(String date){
		return date.substring(8,10)+"."+date.substring(5,7)+"."+date.substring(0,4);
	}
	
}

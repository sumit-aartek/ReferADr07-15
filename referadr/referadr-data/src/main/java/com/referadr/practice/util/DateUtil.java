package com.referadr.practice.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

  public static Date convertStringToDate(String dateString) {
    Date convertedCurrentDate = null;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    try {
      convertedCurrentDate = sdf.parse(dateString);
      System.out.println(sdf.format(convertedCurrentDate));
    } catch (Exception ex) {
      System.out.println(ex);
    }
    return convertedCurrentDate;
  }

  public static String convertDateFormat(String dateString) {
    String convertedCurrentDate = null;
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
      Date tempDate = simpleDateFormat.parse(dateString);
      SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      System.out.println("Output date is = " + outputDateFormat.format(tempDate));
      convertedCurrentDate = outputDateFormat.format(tempDate);
    } catch (ParseException ex) {
      System.out.println("Parse Exception");
    }
    return convertedCurrentDate;
  }
  
  public static String getYYYYMMDD(Date date) {
	  SimpleDateFormat formatter5=new SimpleDateFormat("yyyy-MM-dd");
	    return formatter5.format(date);
	  }
  
	public static Date getCurrentDate() {
		String format = "yyyy/MM/dd HH:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		return convertStringToDate(dateFormat.format(cal.getTime()), format);

	}

	  public static Date convertStringToDate(String dateString, String format) {
		  Date convertedCurrentDate = null;
		  SimpleDateFormat sdf = new SimpleDateFormat(format);
		  try {
			  convertedCurrentDate = sdf.parse(dateString);
		  } catch (Exception ex) {
			  System.out.println(ex);
		  }
		  return convertedCurrentDate;
	  }
	  
	  public static String getDateFormat(Date date, String format) {
		  SimpleDateFormat formatter5=new SimpleDateFormat(format);
		    return formatter5.format(date);
		  }

}

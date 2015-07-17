package com.referadr.practice.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
  public static void main(String[] args) {
  try
  {
    String currentDate="02/02/2015";
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
     Date tempDate=simpleDateFormat.parse(currentDate);
     SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");           
     System.out.println("Output date is = "+outputDateFormat.format(tempDate));
   } catch (ParseException ex) 
   {
         System.out.println("Parse Exception");
   }
  }
}

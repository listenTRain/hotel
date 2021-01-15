package cn.com.djin.springboot.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author:快乐风男
 * time:19:37
 */
public class DateUtils {

   private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

   public static Date stringToDate(String dateStr){
       try {
           return sdf.parse(dateStr);
       } catch (ParseException e) {
           e.printStackTrace();
           return null;
       }
   }
}

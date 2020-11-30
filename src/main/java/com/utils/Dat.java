package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class Dat {



    public static String formatDateByUnixTime(long unixTime, String dateFormat) {
        return dateFormat(new Date(unixTime * 1000L), dateFormat);
    }
    
    public static String dateFormat(Date date, String dateFormat) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            return format.format(date);
        }
        return "";
    }
    
    public static int getCurrentUnixTime() {
        return getUnixTimeByDate(new Date());
    }
    
    public static int getUnixTimeByDate(Date date) {
        return (int)(date.getTime() / 1000L);
    }

}
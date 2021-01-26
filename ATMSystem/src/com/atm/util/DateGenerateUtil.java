package com.atm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成日期的工具类
 */
public class DateGenerateUtil {
    static Date date = new Date();
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat randomDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public static String dateStringGenerate(){
        return simpleDateFormat.format(date);
    }
    public static long randomNumber(){
        return Long.valueOf(randomDateFormat.format(date));
    }
}

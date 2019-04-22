package com.messiyang.modelreplace.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 时间型转换成字符串型
     * @param date
     * @return
     */
    public static String DateconvertString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createdate = sdf.format(date);
        return createdate;
    }

    /**
     * 字符串转换成时间
     * @param time
     * @return
     */
    public static  Date StringConverDate(String time){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(time);
            return date;
        }catch (Exception e){
            return null;
        }

    }
}

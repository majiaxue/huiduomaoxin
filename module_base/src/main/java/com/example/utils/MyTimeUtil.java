package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyTimeUtil {
    /**
     * @param time 1541569323155
     * @return 2018-11-07 13:42:03
     */
    public static String date2String(String time) {
        Long value = Long.valueOf(time);
        Date date = new Date(value);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(date);
    }

}

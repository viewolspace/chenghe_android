package com.ch.parttime.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zdy On 2019/7/27.
 */
public class DateUtil {

    /**
     * @param milSecond 时间戳
     * @return
     */
    public static String getDateToString(long milSecond) {
        return getDateToString(milSecond,"yyyy.MM.dd");
    }
    /**
     * @param milSecond 时间戳
     * @param pattern 日期格式
     * @return
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}

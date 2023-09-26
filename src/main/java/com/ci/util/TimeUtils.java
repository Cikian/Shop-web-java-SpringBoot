package com.ci.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/9/26 16:35
 */
public class TimeUtils {
    public static String getNowTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}

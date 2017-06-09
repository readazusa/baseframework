package cn.com.oceansoft.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by smc on 2016/1/22.
 */
public class DateFormatUtils {

    public static SimpleDateFormat getSimpleDateFormat(String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return  simpleDateFormat;
    }

    public  static String getFormatDate(String pattern){
        return getFormatDate(new Date(),pattern);
    }

    public static String getFormatDate(Date date , String pattern){
        return getSimpleDateFormat(pattern).format(date);
    }


}

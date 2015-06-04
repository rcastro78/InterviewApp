package com.applaudostudios.nfl12bars.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by RafaelCastro on 1/6/15.
 */
public class DateConversion {
    final static String ORIGINAL_DATE_FORMAT="yyyy-MM-dd HH:mm:ss Z";
    final static String OUTPUT_START_TIME="EEEE M/dd h:mm a";
    final static String OUTPUT_END_TIME="h:mm a";
    public String setStartTime(String dt) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ORIGINAL_DATE_FORMAT);
        SimpleDateFormat df_output = new SimpleDateFormat(OUTPUT_START_TIME);
        Date date = simpleDateFormat.parse(dt);
        TimeZone destTz = TimeZone.getDefault();
        simpleDateFormat.setTimeZone(destTz);
        String result = df_output.format(date);
        return result;
    }

    public String setEndTime(String dt) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ORIGINAL_DATE_FORMAT);
        SimpleDateFormat df_output = new SimpleDateFormat(OUTPUT_END_TIME);
        Date date = simpleDateFormat.parse(dt);
        TimeZone destTz = TimeZone.getDefault();
        simpleDateFormat.setTimeZone(destTz);
        String result = df_output.format(date);
        return result;
    }
}

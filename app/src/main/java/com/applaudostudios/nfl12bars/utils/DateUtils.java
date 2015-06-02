package com.applaudostudios.nfl12bars.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by RafaelCastro on 1/6/15.
 */
public class DateUtils {
    public String setStartTime(String dt) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        SimpleDateFormat df_output = new SimpleDateFormat("EEEE M/dd h:mm a");
        Date date = simpleDateFormat.parse(dt);
        TimeZone destTz = TimeZone.getDefault();
        simpleDateFormat.setTimeZone(destTz);
        String result = df_output.format(date);
        return result;
    }

    public String setEndTime(String dt) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        SimpleDateFormat df_output = new SimpleDateFormat("h:mm a");
        Date date = simpleDateFormat.parse(dt);
        TimeZone destTz = TimeZone.getDefault();
        simpleDateFormat.setTimeZone(destTz);
        String result = df_output.format(date);
        return result;
    }
}

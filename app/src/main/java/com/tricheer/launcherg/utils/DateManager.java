package com.tricheer.launcherg.utils;


import android.content.Context;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Date Manager
 *
 * @author Jun.Wang
 */
public class DateManager {

//    >> For the month of September:
//    M -> 9
//    MM -> 09
//    MMM -> Sep
//    MMMM -> September
//
//    >> For 7 minutes past the hour:
//    m -> 7
//    mm -> 07
//    mmm -> 007
//    mmmm -> 0007
//
//    >> Examples for April 6, 1970 at 3:23am:
//    "MM/dd/yy h:mmaa" -> "04/06/70 3:23am"
//    "MMM dd, yyyy h:mmaa" -> "Apr 6, 1970 3:23am"
//    "MMMM dd, yyyy h:mmaa" -> "April 6, 1970 3:23am"
//    "E, MMMM dd, yyyy h:mmaa" -> "Mon, April 6, 19703:23am&
//    "EEEE, MMMM dd, yyyy h:mmaa" -> "Monday, April 6, 1970 3:23am"
//    "&apos;Noteworthy day: &apos;M/d/yy" -> "Noteworthy day: 4/6/70"

    public static final String FORMAT_yMdw1 = "yyyy/MM/dd(E)";
    public static final String FORMAT_hm24 = "HH:mm";
    public static final String FORMAT_hm12 = "hh:mm";

    public interface DateDelegate {
        public void onTimeFormatChange(boolean is24);
    }

    public static String getHourBySysFormat(Context context, long millisecond) {
        if (DateFormat.is24HourFormat(context)) {
            return getFormattedTime(millisecond, FORMAT_hm24);
        }
        return getFormattedTime(millisecond, FORMAT_hm12);
    }

    public static String getFormattedTime(long millisecond, String strFormat) {
        //TimeZone
        //TimeZone.getDefault()
        //TimeZone.getTimeZone(String)
        //TimeZone.getAvailableIDs()
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(millisecond));
    }
}

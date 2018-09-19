package com.tricheer.launcherg.utils;

import android.app.AlarmManager;
import android.content.Context;
import android.provider.Settings;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Configuration of {@link android.provider.Settings.System}
 *
 * @author Jun.Wang
 */
public class SettingsSysConfig {
    /**
     * If system hour format is 24HOUR
     */
    public static boolean isSysHour24(Context context) {
        return DateFormat.is24HourFormat(context);
    }

    /**
     * Set system hour format
     *
     * @param context : {@link Context}
     * @param format: "24" or "12"
     */
    public static void setSysHour24(Context context, String format) {
        Settings.System.putString(context.getContentResolver(), Settings.System.TIME_12_24, format);
    }

    /**
     * Get system Date&Time auto set value
     *
     * @param context:{@link Context}
     * @return int : 1 auto, 0 no auto
     */
    public static int getSysDateAndTimeAutoSet(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), Settings.System.AUTO_TIME);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Set system Date&Time auto set value
     *
     * @param context:{@link Context}
     * @param autoSet:       1 auto, 0 no auto
     */
    public static void setSysDateAndTimeAutoSet(Context context, int autoSet) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.AUTO_TIME, autoSet);
    }

    /**
     * Set system date
     *
     * @param context : {@link Context}
     * @param year    : year number
     * @param month   : month number
     * @param day     : day number
     */
    public static void setSysDate(Context context, int year, int month, int day) {
        setSysDateAndTime(context, year, month, day, 0, 0, 0, 0);
    }

    /**
     * Set system date and time
     *
     * @param context : {@link Context}
     * @param year    : year number
     * @param month   : month number
     * @param day     : day number
     * @param hour    : hour number
     * @param minute  : minute number
     */
    public static void setSysDateAndTime(Context context, int year, int month, int day, int hour, int minute) {
        setSysDateAndTime(context, year, month, day, hour, minute, 0, 0);
    }

    /**
     * /**
     * Set system date and time
     *
     * @param context     : {@link Context}
     * @param year        : year number
     * @param month       : month number
     * @param day         : day number
     * @param hour        : hour number
     * @param minute      : minute number
     * @param second      : second number
     * @param millisecond : millisecond number
     */
    public static void setSysDateAndTime(Context context, int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar c = Calendar.getInstance();
        //
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        //
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        //
        c.set(Calendar.MILLISECOND, millisecond);

        //
        long when = c.getTimeInMillis();
        if (when / 1000 < Integer.MAX_VALUE) {
            ((AlarmManager) context.getSystemService(Context.ALARM_SERVICE)).setTime(when);
        }
    }

    /**
     * Whether system key touch tone is ON
     *
     * @param context:{@link Context}
     * @return boolean
     */
    public static boolean isKeyTouchToneON(Context context) {
        int val = 0;
        try {
            //1 open ; 0 close
            val = Settings.System.getInt(context.getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val == 1;
    }

    /**
     * Set key touch tone
     *
     * @param context: {@link Context}
     * @param value:   1 open ; 0 close
     */
    public static void setKeyTouchTone(Context context, int value) {
        try {
            Settings.System.putInt(context.getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
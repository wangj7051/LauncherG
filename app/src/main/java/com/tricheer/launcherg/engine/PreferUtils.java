package com.tricheer.launcherg.engine;

import com.tricheer.launcherg.utils.PreferenceHelper;

/**
 * Share preference Operate method
 *
 * @author Jun.Wang
 */
public class PreferUtils extends PreferenceHelper {


    /**
     * Current wallpaper index
     *
     * @param isSave       : Whether save or get
     * @param wallpaperIdx : new value to store
     * @return int : Stored value
     */
    public static int getCurrWallPaperIdx(boolean isSave, int wallpaperIdx) {
        final String KEY = "SYS_LAUNCHER_WALLPAPER_IDX";
        if (isSave) {
            saveInt(KEY, wallpaperIdx);
        }
        return getInt(KEY, -1);
    }

    /**
     * Get current font size
     *
     * @param isSave : Whether save or get
     * @param newVal : new value to store
     * @return String : Stored value
     */
    public static String getCurrFontSize(boolean isSave, String newVal) {
        final String KEY = "SYS_LAUNCHER_FONT_SIZE";
        if (isSave) {
            saveString(KEY, newVal);
        }
        return getString(KEY, SysFontManager.MIDDLE);
    }

    /**
     * Show On-screen ID ??
     *
     * @param isSave           : Whether save or get
     * @param isShowOnScreenID : Show or not?
     * @return boolean: true means show.
     */
    public static boolean isShowOnScreenID(boolean isSave, boolean isShowOnScreenID) {
        final String KEY = "SHOW_ON_SCREEN_ID";
        if (isSave) {
            saveBoolean(KEY, isShowOnScreenID);
        }
        return getBoolean(KEY, true);
    }

    /**
     * Get screen timeout
     *
     * @param isSave  : Whether save or get
     * @param timeout : new value to store
     * @return int : Stored value
     */
    public static int getScreenTimout(boolean isSave, int timeout) {
        final String KEY = "SYS_SCREEN_TIMEOUT";
        if (isSave) {
            saveInt(KEY, timeout);
        }
        return getInt(KEY, 0);
    }

    /**
     * Key touch tone ON ??
     *
     * @param isSave           : Whether save or get
     * @param isShowOnScreenID : Show or not?
     * @return boolean: true means show.
     */
    public static boolean isKeytouchToneON(boolean isSave, boolean isShowOnScreenID) {
        final String KEY = "KEY_TOUCH_TONE_FLAG";
        if (isSave) {
            saveBoolean(KEY, isShowOnScreenID);
        }
        return getBoolean(KEY, true);
    }

    /**
     * Send Caller ID ON ??
     *
     * @param isSave           : Whether save or get
     * @param isSendCallerIdON : Show or not?
     * @return boolean: true means show.
     */
    public static boolean isSendCallerIdON(boolean isSave, boolean isSendCallerIdON) {
        final String KEY = "MENU_PHONE_SEND_CALLER_ID";
        if (isSave) {
            saveBoolean(KEY, isSendCallerIdON);
        }
        return getBoolean(KEY, true);
    }

    /**
     * [Menu-Phone-Prefix-ON/OFF] ON ??
     *
     * @param isSave          : Whether save or get
     * @param isPhonePrefixON : Show or not?
     * @return boolean: true means show.
     */
    public static boolean isPhonePrefixON(boolean isSave, boolean isPhonePrefixON) {
        final String KEY = "MENU_PHONE_PREFIX_ON_OFF";
        if (isSave) {
            saveBoolean(KEY, isPhonePrefixON);
        }
        return getBoolean(KEY, true);
    }

    /**
     * [Menu-Phone-Prefix-Prefix Number] value
     *
     * @param isSave Whether save or get
     * @param newVal new value to store
     * @return String
     */
    public static String getPhonePrefix(boolean isSave, String newVal) {
        final String KEY = "MENU_PHONE_PREFIX_NUMBER";
        if (isSave) {
            saveString(KEY, newVal);
        }
        return getString(KEY, "");
    }
}

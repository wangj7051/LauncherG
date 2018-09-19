package com.tricheer.launcherg.engine;

import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import java.util.Locale;

/**
 * System Language Manager
 *
 * @author Jun.Wang
 */
public class LanguageManager {

    //Font type
    public static final String LANG_EN = "en";
    public static final String LANG_JA = "ja";

    /**
     * System Font Delegate
     */
    public interface LanguageDelegate {
        public void onLanguageChanged();
    }

    /**
     * Get current {@link Locale}
     *
     * @param context:{@link Context}
     * @see Locale
     */
    public static Locale getCurrLocale(Context context) {
        Configuration config = context.getResources().getConfiguration();
        Locale locale;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            locale = config.getLocales().get(0);
        } else {
            locale = config.locale;
        }
        return locale;
    }

    /**
     * Get current system language code.
     *
     * @param context:{@link Context}
     * @return String :{@link #LANG_EN} or {@link #LANG_JA}
     */
    public static String getCurrLang(Context context) {
        Locale currLocale = getCurrLocale(context);
        if (currLocale != null) {
            String currLang = currLocale.getLanguage();
            return currLang == null ? "" : currLang;
        }
        return "";
    }

    /**
     * If language changed? Target language should not equals current language.
     *
     * @param context:            {@link Context}
     * @param targetLang:Language will be set
     * @return boolean:true means changed.
     */
    public static boolean isLangChanged(Context context, String targetLang) {
        Locale currLocale = getCurrLocale(context);
        if (currLocale != null) {
            String currLang = currLocale.getLanguage();
            return !TextUtils.equals(currLang, targetLang);
        }
        return true;
    }

    /**
     * Only Japanese and English is allowed.
     * If current language is neither Japanese nor English , you should set Japanese as default.
     *
     * @param context:{@link Context}
     */
    public static void initSys(Context context) {
        Locale locale = getCurrLocale(context);
        if (locale != null) {
            String sysLanguage = locale.getLanguage();
            if (!(LanguageManager.LANG_JA.equalsIgnoreCase(sysLanguage) || LanguageManager.LANG_EN.equalsIgnoreCase(sysLanguage))) {
                updateSysLang(Locale.JAPANESE);
            }
        }
    }

    /**
     * Update language in Application. System language will not change.
     *
     * @param context :{@link Context}
     * @param config: {@link Configuration}
     */
    public static void updateLang(Context context, Configuration config) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                context.createConfigurationContext(config);
            } else {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update system language by selected language
     *
     * @param lang : {@link #LANG_EN} or {@link #LANG_JA}
     */
    public static void updateSysLang(String lang) {
        if (LANG_EN.equalsIgnoreCase(lang)) {
            updateSysLang(Locale.ENGLISH);
        } else if (LANG_JA.equalsIgnoreCase(lang)) {
            updateSysLang(Locale.JAPANESE);
        }
    }

    /**
     * Update system language by selected language
     *
     * @param locale : {@link Locale}
     */
    public static void updateSysLang(Locale locale) {
        try {
            IActivityManager iActivityManager = ActivityManagerNative.getDefault();
            Configuration config = iActivityManager.getConfiguration();
            config.setLocale(locale);
            iActivityManager.updateConfiguration(config);
            Log.i("updateSysLang", "");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

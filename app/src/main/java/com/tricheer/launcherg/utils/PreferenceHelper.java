package com.tricheer.launcherg.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;
import java.util.Set;

/**
 * Common PreferenceHelper
 *
 * @author Jun.Wang
 */
public class PreferenceHelper {

    /**
     * SharedPreferences.Editor
     */
    private static SharedPreferences.Editor editor = null;
    /**
     * SharedPreferences
     */
    private static SharedPreferences preferences = null;

    /**
     * INIT
     */
    public static void initPreference(Context context) {
        //
        // mContext = context;

        //
        getSharedPreferences(context);
        getEditor(context);
    }

    /**
     * INIT SharedPreferences.Editor editor
     */
    private static SharedPreferences.Editor getEditor(Context context) {
        if (editor == null) {
            editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        }

        return editor;
    }

    /**
     * INIT SharedPreferences preferences
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }

        return preferences;
    }

    /**
     * Save String
     */
    public static void saveString(String key, String value) {
        editor.putString(key, value).commit();
    }

    /**
     * Save Set<String>
     */
    @SuppressLint("NewApi")
    public static void saveStringSet(String key, Set<String> value) {
        editor.putStringSet(key, value).commit();
    }

    /**
     * Save Integer
     */
    public static void saveInt(String key, Integer value) {
        editor.putInt(key, value).commit();
    }

    /**
     * Save Long
     */
    public static void saveLong(String key, Long value) {
        editor.putLong(key, value).commit();
    }

    /**
     * Save Float
     */
    public static void saveFloat(String key, Float value) {
        editor.putFloat(key, value).commit();
    }

    /**
     * Save Boolean
     */
    public static void saveBoolean(String key, Boolean value) {
        editor.putBoolean(key, value).commit();
    }

    /**
     * Get String
     */
    public static String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    /**
     * Get Set<String>
     */
    @SuppressLint("NewApi")
    public static Set<String> getStringSet(String key, Set<String> defaultValue) {
        return preferences.getStringSet(key, defaultValue);
    }

    /**
     * Get Integer
     */
    public static Integer getInt(String key, Integer defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    /**
     * Get Long
     */
    public static Long getLong(String key, Long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    /**
     * Get Float
     */
    public static Float getFloat(String key, Float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    /**
     * Get Boolean
     */
    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    /**
     * Delete By Key
     */
    public static void delete(String currentKey) {
        editor.remove(currentKey).commit();
    }

    /**
     * Clear all data
     */
    public static void clearAll() {
        editor.clear().commit();
    }

    /**
     * Get all Data
     */
    public static Map<String, ?> getAllData() {
        Map<String, ?> map = preferences.getAll();
        return map;
    }
}

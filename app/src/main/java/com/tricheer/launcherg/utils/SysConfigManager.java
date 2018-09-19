package com.tricheer.launcherg.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * System Configuration Manager
 */
public class SysConfigManager {
    /**
     * Get {@link Resources} with default font scale number.
     * <p>Return this method where u override getResource()</p>
     */
    public static Resources getResWithDefaultFontScale(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        //Default font scale number is 1
        if (configuration.fontScale != 1) {
            Configuration newConfiguration = new Configuration();
            newConfiguration.fontScale = 1;
            newConfiguration.setToDefaults();
            resources.updateConfiguration(newConfiguration, resources.getDisplayMetrics());
        }
        return resources;
    }
}

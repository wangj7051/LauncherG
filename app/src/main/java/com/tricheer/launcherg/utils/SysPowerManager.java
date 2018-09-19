package com.tricheer.launcherg.utils;

import android.content.Context;
import android.os.PowerManager;
import android.os.SystemClock;

import java.lang.reflect.Method;

/**
 * System power util methods
 */
public class SysPowerManager {

    private static PowerManager mPowerManager;

    public static void init(Context context) {
        mPowerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    }

    public static void wakeUp() {
        if (mPowerManager != null) {
            try {
                Method method = mPowerManager.getClass().getMethod("wakeUp", long.class);
                method.invoke(mPowerManager, SystemClock.uptimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void goToSleep() {
        if (mPowerManager != null) {
            try {
                Method method = mPowerManager.getClass().getMethod("goToSleep", long.class);
                method.invoke(mPowerManager, SystemClock.uptimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

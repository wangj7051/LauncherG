package com.tricheer.launcherg.engine;

import android.media.RingtoneManager;

import com.tricheer.launcherg.MainActivity;
import com.tricheer.launcherg.activity.BaseFragActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Application Stack Manager
 *
 * @author Jun.Wang
 */
public class AppStackManager {
    /**
     * Store Activity
     */
    private static Map<String, BaseFragActivity> mActs = new HashMap<>();

    public static void addToStack(BaseFragActivity act) {
        if (act == null) {
            return;
        }

        String clsName = act.getClass().getName();
        if (mActs.containsKey(clsName)) {
            if (act instanceof MainActivity) {
                return;
            }

            BaseFragActivity oldAct = mActs.get(clsName);
            if (oldAct != null) {
                oldAct.finish();
            }
        }
        mActs.put(clsName, act);
    }

    public static void removeFromStack(BaseFragActivity act) {
        String clsName = act.getClass().getName();
        if (mActs.containsKey(clsName)) {
            mActs.remove(clsName);
        }
    }

    public static void removeAllFromStack(BaseFragActivity act) {
        if (act instanceof MainActivity) {
            for (Iterator<Map.Entry<String, BaseFragActivity>> it = mActs.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, BaseFragActivity> entry = it.next();
                BaseFragActivity val = entry.getValue();
                if (val instanceof MainActivity) {
                    continue;
                }

                //Finish all the activity on top of launcher
                it.remove();
                if (val != null) {
                    val.finish();
                }
            }
        }
    }

    public static void notifyFontSizeChanged() {
        for (BaseFragActivity act : mActs.values()) {
            if (act != null) {
                act.onFontSizeChanged();
            }
        }
    }

    public static void notifyRefreshOnScreenID(boolean isShow) {
        for (BaseFragActivity act : mActs.values()) {
            if (act != null) {
                act.refreshOnScreenID(isShow);
            }
        }
    }

    public static void notifyLanguageChanged() {
        for (BaseFragActivity act : mActs.values()) {
            if (act != null) {
                act.onLanguageChanged();
            }
        }
    }

    public static void notifyScreenTimoutChanged() {
        for (BaseFragActivity act : mActs.values()) {
            if (act != null) {
                act.onScreenTimeoutChanged();
            }
        }
    }

    /**
     * Notify on system volume status changed
     *
     * @param flag : <p>1 Ringtones changed</p>
     *             <p>2 Ringtone volume changed</p>
     *             <p>3 In-call volume changed</p>
     *             <p>4 Speaker volume changed</p>
     *             <p>5 Keytouch tone changed</p>
     */
    public static void notifySysVolChanged(int flag) {
        for (BaseFragActivity act : mActs.values()) {
            if (act != null) {
                act.onSysVolChanged(flag);
            }
        }
    }

    /**
     * Notify on system ring tone changed
     *
     * @param type {@link RingtoneManager#TYPE_RINGTONE}
     *             or {@link RingtoneManager#TYPE_NOTIFICATION}
     *             or {@link RingtoneManager#TYPE_ALARM}
     *             or {@link RingtoneManager#TYPE_ALL}
     */
    public static void notifySysRingtoneChanged(int type) {
        for (BaseFragActivity act : mActs.values()) {
            if (act != null) {
                act.onSysRingToneChanged(type);
            }
        }
    }

    /**
     * Notify on system volume status changed
     *
     * @param isON true/false
     */
    public static void notifySendCallerIdON(boolean isON) {
        for (BaseFragActivity act : mActs.values()) {
            if (act != null) {
                act.onSendCallerIdON(isON);
            }
        }
    }

    /**
     * Notify on [Menu-Phone-Prefix-ON/OFF] changed
     *
     * @param isON true/false
     */
    public static void notifyPhonePrefixON(boolean isON) {
        for (BaseFragActivity act : mActs.values()) {
            if (act != null) {
                act.onPhonePrefixON(isON);
            }
        }
    }

    /**
     * Notify on [Menu-Phone-Prefix-ON/OFF] changed
     *
     * @param prefix Phone prefix number
     */
    public static void notifyPhonePrefixChanged(String prefix) {
        for (BaseFragActivity act : mActs.values()) {
            if (act != null) {
                act.onPhonePrefixChanged(prefix);
            }
        }
    }
}

package com.tricheer.launcherg.utils;

import android.content.Context;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * System ring tones util method
 * <p>Calling: "/system/media/audio/ringtones"</p>
 * <p>SMS notify: "/system/media/audio/notifications"</p>
 * <p>Alarm: "/system/media/audio/alarms"</p>
 *
 * @author Jun.Wang
 */
public class SysRingTongsUtil {
    //TAG
    private static final String TAG = "SysRingTongsUtil";

    /**
     * Get {@link RingtoneManager}
     *
     * @param context {@link Context}
     * @param type    {@link RingtoneManager#TYPE_RINGTONE}
     *                or {@link RingtoneManager#TYPE_NOTIFICATION}
     *                or {@link RingtoneManager#TYPE_ALARM}
     *                or {@link RingtoneManager#TYPE_ALL}
     * @return android.media.RingtoneManager
     */
    public static RingtoneManager getRingtoneManager(Context context, int type) {
        RingtoneManager ringtoneManager = new RingtoneManager(context);
        ringtoneManager.setType(type);
        return ringtoneManager;
    }

    /**
     * Query ring tones
     *
     * @param ringtoneManager {@link RingtoneManager}
     * @return List&lt;Ringtone&gt;
     */
    public static List<Ringtone> queryAllRingTones(RingtoneManager ringtoneManager) {
        List<Ringtone> listItems = new ArrayList<Ringtone>();
        try {
            //Get List
            Cursor cursor = ringtoneManager.getCursor();
            int loop = cursor.getCount();
            for (int idx = 0; idx < loop; idx++) {
                Ringtone ringtone = ringtoneManager.getRingtone(idx);
                listItems.add(ringtone);
                Log.i(TAG, "ringtone: " + ringtone.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listItems;
    }

    /**
     * Get Ringtone
     *
     * @param ringtoneManager {@link RingtoneManager}
     * @param pos             Position in list.
     * @return Uri
     */
    public static Uri getRingtoneUri(RingtoneManager ringtoneManager, int pos) {
        return ringtoneManager.getRingtoneUri(pos);
    }

    /**
     * Get current ring tone uri
     *
     * @param context {@link Context}
     * @param type    {@link RingtoneManager#TYPE_RINGTONE}
     *                or {@link RingtoneManager#TYPE_NOTIFICATION}
     *                or {@link RingtoneManager#TYPE_ALARM}
     *                or {@link RingtoneManager#TYPE_ALL}
     * @return Uri
     */
    public static Uri getCurrRingtoneUri(Context context, int type) {
        return RingtoneManager.getActualDefaultRingtoneUri(context, type);
    }

    /**
     * Get ring tone position.
     *
     * @param ringtoneManager {@link RingtoneManager}
     * @param uri             {@link Uri}
     * @return int
     */
    public static int getRingtonePos(RingtoneManager ringtoneManager, Uri uri) {
        return ringtoneManager.getRingtonePosition(uri);
    }

    /**
     * Get current ring tone position.
     *
     * @param context {@link Context}
     * @param type    {@link RingtoneManager#TYPE_RINGTONE}
     *                or {@link RingtoneManager#TYPE_NOTIFICATION}
     *                or {@link RingtoneManager#TYPE_ALARM}
     *                or {@link RingtoneManager#TYPE_ALL}
     * @return int
     */
    public static int getCurrRingtongPos(Context context, RingtoneManager ringtoneManager, int type) {
        return getRingtonePos(ringtoneManager, getCurrRingtoneUri(context, type));
    }

    /**
     * Set current ring tone.
     *
     * @param context {@link Context}
     * @param type    {@link RingtoneManager#TYPE_RINGTONE}
     *                or {@link RingtoneManager#TYPE_NOTIFICATION}
     *                or {@link RingtoneManager#TYPE_ALARM}
     *                or {@link RingtoneManager#TYPE_ALL}
     * @param uri     {@link Uri}
     */
    public static void setCurrRingtone(Context context, int type, Uri uri) {
        RingtoneManager.setActualDefaultRingtoneUri(context, type, uri);
    }

    /**
     * Play ring tone.
     *
     * @param ringtone {@link Ringtone}
     */
    public static void play(Ringtone ringtone) {
        if (!ringtone.isPlaying()) {
            ringtone.play();
        }
    }
}

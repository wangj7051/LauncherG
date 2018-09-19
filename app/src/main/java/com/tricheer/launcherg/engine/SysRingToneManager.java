package com.tricheer.launcherg.engine;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.tricheer.launcherg.utils.SysRingTongsUtil;

import java.util.List;

/**
 * System ring tone manager
 *
 * @author Jun.Wang
 */
public class SysRingToneManager {

    private Context mContext;
    private RingtoneManager mRingtoneManager;
    private int mType;

    public interface SysRingToneDelegate {
        public void onSysRingToneChanged(int type);
    }

    /**
     * Constructor
     *
     * @param context {@link Context}
     * @param type    {@link RingtoneManager#TYPE_RINGTONE}
     *                or {@link RingtoneManager#TYPE_NOTIFICATION}
     *                or {@link RingtoneManager#TYPE_ALARM}
     *                or {@link RingtoneManager#TYPE_ALL}
     */
    public SysRingToneManager(Context context, int type) {
        mContext = context;
        mType = type;
        //RingtoneManager
        mRingtoneManager = new RingtoneManager(mContext);
        mRingtoneManager.setType(mType);
    }

    /**
     * Get ring tong type.
     *
     * @return int {@link RingtoneManager#TYPE_RINGTONE}
     * or {@link RingtoneManager#TYPE_NOTIFICATION}
     * or {@link RingtoneManager#TYPE_ALARM}
     * or {@link RingtoneManager#TYPE_ALL}
     */
    public int getType() {
        return mType;
    }

    /**
     * Query ring tones
     *
     * @return List&lt;Ringtone&gt;
     */
    public List<Ringtone> queryAllRingTones() {
        return SysRingTongsUtil.queryAllRingTones(mRingtoneManager);
    }

    /**
     * Get current ring tone position.
     *
     * @return int
     */
    public int getCurrRingtongPos() {
        Uri currUri = SysRingTongsUtil.getCurrRingtoneUri(mContext, mType);
        return SysRingTongsUtil.getRingtonePos(mRingtoneManager, currUri);
    }

    /**
     * Set current ring tone.
     */
    public void setCurrRingtone(int pos) {
        Uri uri = SysRingTongsUtil.getRingtoneUri(mRingtoneManager, pos);
        SysRingTongsUtil.setCurrRingtone(mContext, mType, uri);
    }

    /**
     * Play ring tone.
     *
     * @param ringtone {@link Ringtone}
     */
    public void play(Ringtone ringtone) {
        SysRingTongsUtil.play(ringtone);
    }
}

package com.tricheer.launcherg.engine;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.tricheer.launcherg.utils.SysVolUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * System volume manager
 *
 * @author Jun.Wang
 */
public class SysVolManager {
    //TAG
    private final String TAG = "SysVolManager";

    //Max Volume
    private int mMaxVolOfRing, mMaxVolOfCall, mMaxVolOfSpeaker;

    //Volume region number
    //e.g. The max volume of system is 30, and will be divided to 3 region.
    //So the level1 should be 0, level2 should be 15, level3 should be 30.
    private final int VOL_REGIONS = 8;

    //Volume levels.
    //e.g. The max volume of system is 30, and will be divided to 3 region.
    //So the level1 should be 0, level2 should be 15, level3 should be 30.
    private List<Integer> mListVolsOfRing = new ArrayList<Integer>();
    private List<Integer> mListVolsOfCall = new ArrayList<Integer>();
    private List<Integer> mListVolsOfSpeaker = new ArrayList<Integer>();

    /**
     * System Font Delegate
     */
    public interface SysVolDelegate {
        /**
         * Notify on system volume status changed
         *
         * @param flag : <p>1 Ringtones changed</p>
         *             <p>2 Ringtone volume changed</p>
         *             <p>3 In-call volume changed</p>
         *             <p>4 Speaker volume changed</p>
         *             <p>5 Keytouch tone changed</p>
         */
        public void onSysVolChanged(int flag);
    }


    private SysVolManager() {
    }

    private static class SingletonHolder {
        private static final SysVolManager INSTANCE = new SysVolManager();
    }

    public static SysVolManager instance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(Context context) {
        //Initialize
        SysVolUtil.init(context);

        //Get Max
        mMaxVolOfRing = SysVolUtil.getVol(true, AudioManager.STREAM_RING);
        mMaxVolOfCall = SysVolUtil.getVol(true, AudioManager.STREAM_VOICE_CALL);
        mMaxVolOfSpeaker = SysVolUtil.getVol(true, AudioManager.STREAM_MUSIC);
        Log.i(TAG, "maxVolOfRing:" + mMaxVolOfRing + " ; maxVolOfCall:" + mMaxVolOfCall + " ; maxVolOfSpeaker:" + mMaxVolOfSpeaker);

        //Divide regions
        mListVolsOfRing.addAll(getDividedLevels(mMaxVolOfRing));
        mListVolsOfCall.addAll(getDividedLevels(mMaxVolOfCall));
        mListVolsOfSpeaker.addAll(getDividedLevels(mMaxVolOfSpeaker));

        int loop = mListVolsOfRing.size();
        for (int idx = 0; idx < loop; idx++) {
            int volVal = mListVolsOfRing.get(idx);
            Log.i(TAG, "level:" + idx + " - value:" + volVal);
        }
    }

    private List<Integer> getDividedLevels(final int maxVol) {
        List<Integer> listVolLevels = new ArrayList<Integer>();
        int delta = getVolDelta(maxVol);
        for (int idx = 0; idx < VOL_REGIONS; idx++) {
            if (idx == 0) {
                listVolLevels.add(0);
            } else if (idx == VOL_REGIONS - 1) {
                listVolLevels.add(maxVol);
            } else {
                int lastVol = listVolLevels.get(idx - 1);
                listVolLevels.add(lastVol + delta);
            }
        }
        return listVolLevels;
    }

    /**
     * <p>Volume region number</p>
     * <p>e.g. The max volume of system is 30, and will be divided to 3 region.</p>
     * <p>So the level1 should be 0, level2 should be 15, level3 should be 30.</p>
     *
     * @return int
     */
    public int getRegions() {
        return VOL_REGIONS;
    }

    /**
     * Get current volume of ring.
     *
     * @return int
     */
    public int getVolLevelOfRing() {
        int vol = SysVolUtil.getVol(false, AudioManager.STREAM_RING);
        return vol / getVolDelta(mMaxVolOfRing);
    }

    /**
     * Set volume of ring
     *
     * @param level : Current volume level.{@link #VOL_REGIONS}
     */
    public void setVolOfRing(int level) {
        try {
            int volVal = mListVolsOfRing.get(level);
            setVol(AudioManager.STREAM_RING, volVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get current volume of call.
     *
     * @return int
     */
    public int getVolLevelOfCall() {
        int vol = SysVolUtil.getVol(false, AudioManager.STREAM_VOICE_CALL);
        return vol / getVolDelta(mMaxVolOfRing);
    }

    /**
     * Set volume of call
     *
     * @param level : Current volume level.{@link #VOL_REGIONS}
     */
    public void setVolOfCall(int level) {
        try {
            int volVal = mListVolsOfCall.get(level);
            setVol(AudioManager.STREAM_VOICE_CALL, volVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get current volume of speaker.
     *
     * @return int
     */
    public int getVolLevelOfSpeaker() {
        int vol = SysVolUtil.getVol(false, AudioManager.STREAM_VOICE_CALL);
        return vol / getVolDelta(mMaxVolOfRing);
    }

    /**
     * Set volume of call
     *
     * @param level : Current volume level.{@link #VOL_REGIONS}
     */
    public void setVolOfSpeaker(int level) {
        try {
            int volVal = mListVolsOfSpeaker.get(level);
            setVol(AudioManager.STREAM_MUSIC, volVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get volume delta for stepping add or minus
     *
     * @return int
     */
    private int getVolDelta(int maxVol) {
        return maxVol / (VOL_REGIONS - 1);
    }

    /**
     * Set volume
     *
     * @param volVal : Volume value. {@link AudioManager#getStreamMaxVolume(int)}
     */
    private void setVol(int streamType, int volVal) {
        SysVolUtil.setSysVol(streamType, volVal);
    }
}

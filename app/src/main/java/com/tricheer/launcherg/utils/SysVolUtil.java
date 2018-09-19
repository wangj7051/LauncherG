package com.tricheer.launcherg.utils;

import android.content.Context;
import android.media.AudioManager;

/**
 * System volume util method
 *
 * @author Jun.Wang
 */
public class SysVolUtil {

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static AudioManager getAudioManager() {
        return (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * @param isGetMax    : true=Get Max volume; false=Get current volume.
     * @param streamType: <p>{@link AudioManager#STREAM_VOICE_CALL} 通话音量;</p>
     *                    <p>{@link AudioManager#STREAM_SYSTEM} 系统音量;</p>
     *                    <p>{@link AudioManager#STREAM_RING} 铃声音量;</p>
     *                    <p>{@link AudioManager#STREAM_MUSIC} 音乐音量;</p>
     *                    <p>{@link AudioManager#STREAM_ALARM} 提示声音音量;</p>
     * @return: int=volume value
     */
    public static int getVol(boolean isGetMax, int streamType) {
        AudioManager audioManager = getAudioManager();
        if (isGetMax) {
            return audioManager.getStreamMaxVolume(streamType);
        }
        return audioManager.getStreamVolume(streamType);
    }


    /**
     * Set system volume
     *
     * @param streamType: <p>{@link AudioManager#STREAM_VOICE_CALL} 通话音量;</p>
     *                    <p>{@link AudioManager#STREAM_SYSTEM} 系统音量;</p>
     *                    <p>{@link AudioManager#STREAM_RING} 铃声音量;</p>
     *                    <p>{@link AudioManager#STREAM_MUSIC} 音乐音量;</p>
     *                    <p>{@link AudioManager#STREAM_ALARM} 提示声音音量;</p>
     * @param index:      <p>The volume index to set.</p>
     *                    <p> See {@link AudioManager#getStreamMaxVolume(int)} for the largest valid value.</p>
     * @param flags:      <p>{@link AudioManager#FLAG_PLAY_SOUND} 调整音量时播放声音</p>
     *                    {@link AudioManager#FLAG_SHOW_UI} 调整时显示音量条,就是按音量键出现的那个</p>
     */
    public static void setSysVol(int streamType, int index, int flags) {
        try {
            AudioManager audioManager = getAudioManager();
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, flags);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set system volume
     *
     * @param streamType: <p>{@link AudioManager#STREAM_VOICE_CALL} 通话音量;</p>
     *                    <p>{@link AudioManager#STREAM_SYSTEM} 系统音量;</p>
     *                    <p>{@link AudioManager#STREAM_RING} 铃声音量;</p>
     *                    <p>{@link AudioManager#STREAM_MUSIC} 音乐音量;</p>
     *                    <p>{@link AudioManager#STREAM_ALARM} 提示声音音量;</p>
     * @param index:      <p>The volume index to set.</p>
     *                    <p> See {@link AudioManager#getStreamMaxVolume(int)} for the largest valid value.</p>
     */
    public static void setSysVol(int streamType, int index) {
        setSysVol(streamType, index, AudioManager.FLAG_PLAY_SOUND);
    }
}

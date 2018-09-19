package com.tricheer.launcherg.activity.sound;

import android.content.Context;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.BaseTemplateActivity;
import com.tricheer.launcherg.frags.sound.RingTonesFragment;
import com.tricheer.launcherg.view.MsgToast;

/**
 * [Menu-Sound-Ringtones] Activity
 *
 * @author Jun.Wang
 */
public class RingTonesActivity extends BaseTemplateActivity {
    /**
     * TAG
     */
    private static final String TAG = "RingTonesActivity";

    /**
     * Context Object
     */
    private Context mContext;
    private Handler mHandler = new MsgHandler(this);

    /**
     * Widgets
     */
    private RingTonesFragment fragContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        // Variables
        mContext = this;
        loadPage();
    }

    @Override
    protected void loadPage() {
        setPageTitle(getString(R.string.sound_ringtones));
        setPageTips("");
        loadContentFrag();
    }

    private void loadContentFrag() {
        if (fragContent == null) {
            fragContent = new RingTonesFragment();
        }
        loadV4Fragment(fragContent);
    }

    @Override
    public void onGetKeyEvent(KeyEvent event) {
        if (fragContent != null) {
            fragContent.onGetKeyEvent(event);
        }
    }

    @Override
    public void onKeyPressed(int keyCode) {
        Log.i(TAG, "onKeyPressed(" + keyCode + ")");
        if (fragContent != null) {
            fragContent.onKeyPressed(keyCode);
        }
    }

    @Override
    public void onSysRingToneChanged(int type) {
        switch (type) {
            case RingtoneManager.TYPE_RINGTONE:
                MsgToast.toastShort(mContext, getString(R.string.toast_set_completed));
                mHandler.sendEmptyMessageDelayed(MsgHandler.MSG_DELAY_FINISH, MsgHandler.COM_DELAY_TIME);
                break;
        }
        super.onSysRingToneChanged(type);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

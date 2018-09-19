package com.tricheer.launcherg.activity.sound;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.BaseTemplateActivity;
import com.tricheer.launcherg.frags.sound.KeytouchToneFragment;
import com.tricheer.launcherg.view.MsgToast;

/**
 * [Menu-Sound-Keytouch tone] Activity
 *
 * @author Jun.Wang
 */
public class KeytouchToneActivity extends BaseTemplateActivity {
    /**
     * TAG
     */
    private static final String TAG = "KeytouchToneActivity";

    /**
     * Context Object
     */
    private Context mContext;
    private Handler mHandler = new MsgHandler(this);

    /**
     * Widgets
     */
    private KeytouchToneFragment fragContent;

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
        setPageTitle(getString(R.string.sound_keytouch_tone));
        setPageTips("");
        loadContentFrag();
    }

    private void loadContentFrag() {
        if (fragContent == null) {
            fragContent = new KeytouchToneFragment();
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
    public void onSysVolChanged(int flag) {
        switch (flag) {
            case 5:
                MsgToast.toastShort(mContext, getString(R.string.toast_set_completed));
                mHandler.sendEmptyMessageDelayed(MsgHandler.MSG_DELAY_FINISH, MsgHandler.COM_DELAY_TIME);
                break;
        }
        super.onSysVolChanged(flag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

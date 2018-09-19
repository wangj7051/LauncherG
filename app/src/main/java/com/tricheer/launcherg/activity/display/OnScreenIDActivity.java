package com.tricheer.launcherg.activity.display;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.BaseTemplateActivity;
import com.tricheer.launcherg.frags.display.OnScreenIDFragment;
import com.tricheer.launcherg.view.MsgToast;

/**
 * [Menu-Display-On-screen ID] Activity
 *
 * @author Jun.Wang
 */
public class OnScreenIDActivity extends BaseTemplateActivity {
    /**
     * TAG
     */
    private static final String TAG = "OnScreenIDActivity";

    /**
     * Context Object
     */
    private Context mContext;
    private Handler mHandler = new MsgHandler(this);

    /**
     * Widgets
     */
    private OnScreenIDFragment fragContent;

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
        setPageTitle(getString(R.string.on_screen_id));
        setPageTips("");
        loadContentFrag();
    }

    private void loadContentFrag() {
        if (fragContent == null) {
            fragContent = new OnScreenIDFragment();
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
    public void refreshOnScreenID(boolean isShow) {
        MsgToast.toastShort(mContext, getString(R.string.toast_set_completed));
        super.refreshOnScreenID(isShow);
        mHandler.sendEmptyMessageDelayed(MsgHandler.MSG_DELAY_FINISH, MsgHandler.COM_DELAY_TIME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

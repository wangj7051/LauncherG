package com.tricheer.launcherg.activity.display;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.BaseTemplateActivity;
import com.tricheer.launcherg.frags.display.ScreenTimeoutFragment;
import com.tricheer.launcherg.service.ControlScreenTimeout;
import com.tricheer.launcherg.service.ControlService;
import com.tricheer.launcherg.view.MsgToast;

/**
 * [Menu-Display-Screen timeout] Activity
 *
 * @author Jun.Wang
 */
public class ScreenTimeoutActivity extends BaseTemplateActivity {
    /**
     * TAG
     */
    private static final String TAG = "ScreenTimeoutActivity";

    /**
     * Context Object
     */
    private Context mContext;
    private Handler mHandler = new MsgHandler(this);
    private ControlScreenTimeout mControlScreenTimeout;

    /**
     * Widgets
     */
    private ScreenTimeoutFragment fragContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        // Variables
        mContext = this;

        //
        bindControlService(this, 2);
        loadPage();
    }

    @Override
    protected void onControlServiceChanged(ControlService service, int status) {
        super.onControlServiceChanged(service, status);
        mControlScreenTimeout = service.getControlScreenTimeout();
    }

    @Override
    protected void loadPage() {
        setPageTitle(getString(R.string.screen_timeout));
        setPageTips("");
        loadContentFrag();
    }

    private void loadContentFrag() {
        if (fragContent == null) {
            fragContent = new ScreenTimeoutFragment();
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
    public void onScreenTimeoutChanged() {
        MsgToast.toastShort(mContext, getString(R.string.toast_set_completed));
        super.onScreenTimeoutChanged();
        mHandler.sendEmptyMessageDelayed(MsgHandler.MSG_DELAY_FINISH, MsgHandler.COM_DELAY_TIME);
    }

    @Override
    protected void onDestroy() {
        bindControlService(this, 3);
        super.onDestroy();
    }
}

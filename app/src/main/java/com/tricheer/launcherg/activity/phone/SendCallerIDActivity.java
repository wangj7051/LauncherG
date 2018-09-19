package com.tricheer.launcherg.activity.phone;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.BaseTemplateActivity;
import com.tricheer.launcherg.frags.phone.SendCallerIDFragment;
import com.tricheer.launcherg.view.MsgToast;

/**
 * [Menu-Phone-Send Caller ID] Activity
 *
 * @author Jun.Wang
 */
public class SendCallerIDActivity extends BaseTemplateActivity {
    /**
     * TAG
     */
    private static final String TAG = "SendCallerIDActivity";

    /**
     * Context Object
     */
    private Context mContext;
    private Handler mHandler = new MsgHandler(this);

    /**
     * Widgets
     */
    private SendCallerIDFragment fragContent;

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
        setPageTitle(getString(R.string.send_caller_id));
        setPageTips("");
        loadContentFrag();
    }

    private void loadContentFrag() {
        if (fragContent == null) {
            fragContent = new SendCallerIDFragment();
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
    public void onSendCallerIdON(boolean isON) {
        MsgToast.toastShort(mContext, getString(R.string.toast_set_completed));
        mHandler.sendEmptyMessageDelayed(MsgHandler.MSG_DELAY_FINISH, MsgHandler.COM_DELAY_TIME);
        super.onSendCallerIdON(isON);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.tricheer.launcherg.activity.phone;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.BaseTemplateActivity;
import com.tricheer.launcherg.frags.phone.PhonePrefixNumberFragment;
import com.tricheer.launcherg.view.MsgToast;

/**
 * [Menu-Phone-Prefix-Prefix Number] Activity
 *
 * @author Jun.Wang
 */
public class PhonePrefixNumberActivity extends BaseTemplateActivity {
    /**
     * TAG
     */
    private static final String TAG = "PrefixNumberActivity";

    /**
     * Context Object
     */
    private Context mContext;
    private Handler mHandler = new MsgHandler(this);

    /**
     * Widgets
     */
    private PhonePrefixNumberFragment fragContent;

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
        setPageTitle(getString(R.string.prefix));
        setPageTips("");
        loadContentFrag();
    }

    private void loadContentFrag() {
        if (fragContent == null) {
            fragContent = new PhonePrefixNumberFragment();
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
    public void onPhonePrefixChanged(String prefix) {
        MsgToast.toastShort(mContext, getString(R.string.toast_set_completed));
        mHandler.sendEmptyMessageDelayed(MsgHandler.MSG_DELAY_FINISH, MsgHandler.COM_DELAY_TIME);
        super.onPhonePrefixChanged(prefix);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

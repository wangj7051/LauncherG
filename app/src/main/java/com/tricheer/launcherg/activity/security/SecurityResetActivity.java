package com.tricheer.launcherg.activity.security;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.BaseTemplateActivity;
import com.tricheer.launcherg.frags.security.SecurityResetFragment;

/**
 * [Menu-Security-Reset] Activity
 *
 * @author Jun.Wang
 */
public class SecurityResetActivity extends BaseTemplateActivity {
    /**
     * TAG
     */
    private static final String TAG = "SecurityResetActivity";

    /**
     * Context Object
     */
    private Context mContext;

    /**
     * Widgets
     */
    private SecurityResetFragment fragContent;

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
        setPageTitle(getString(R.string.reset));
        setPageTips("");
        loadContentFrag();
    }

    private void loadContentFrag() {
        if (fragContent == null) {
            fragContent = new SecurityResetFragment();
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
    protected void onDestroy() {
        super.onDestroy();
    }
}

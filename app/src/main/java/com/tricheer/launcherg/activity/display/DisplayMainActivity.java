package com.tricheer.launcherg.activity.display;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.BaseTemplateActivity;
import com.tricheer.launcherg.frags.display.DisplayMainFragment;

/**
 * Display Main Page
 *
 * @author Jun.Wang
 */
public class DisplayMainActivity extends BaseTemplateActivity {
    /**
     * TAG
     */
    private static final String TAG = "DisplayMainActivity";

    /**
     * Context Object
     */
    private Context mContext;

    /**
     * Widgets
     */
    private DisplayMainFragment fragContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CacheLv.restorePos(getIntent());
        init();
    }

    private void init() {
        // Variables
        mContext = this;
        loadPage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Forcibly reload page
        if (isReloadForciblyOnResume()) {
            forceRestart();
        }
    }

    private void forceRestart() {
        Intent itMe = new Intent(mContext, getClass());
        if (fragContent != null) {
            CacheLv.cachePos(itMe, fragContent.getCurrSelectedPos());
        }
        startActivity(itMe);
    }

    @Override
    protected void loadPage() {
        setPageTitle(getString(R.string.display));
        setPageTips("");
        loadContentFrag();
    }

    private void loadContentFrag() {
        if (fragContent == null) {
            fragContent = new DisplayMainFragment();
            fragContent.setInitPos(CacheLv.getPos());
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

package com.tricheer.launcherg.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.frags.MenuFragment;

/**
 * Menu
 *
 * @author Jun.Wang
 */
public class MenuActivity extends BaseTemplateActivity {
    /**
     * TAG
     */
    private static final String TAG = "MenuActivity";

    /**
     * Context Object
     */
    private Context mContext;

    /**
     * Widgets
     */
    private MenuFragment fragContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        setPageTitle(getString(R.string.menu));
        setPageTips("");
        loadContentFrag();
    }

    private void loadContentFrag() {
        //Add new fragment
        if (fragContent == null) {
            fragContent = new MenuFragment();
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

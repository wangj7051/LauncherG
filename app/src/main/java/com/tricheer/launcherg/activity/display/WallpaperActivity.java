package com.tricheer.launcherg.activity.display;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.BaseTemplateActivity;
import com.tricheer.launcherg.frags.display.WallpaperFragment;

/**
 * [Menu-Display-Wallpaper] Activity
 *
 * @author Jun.Wang
 */
public class WallpaperActivity extends BaseTemplateActivity {
    /**
     * TAG
     */
    private static final String TAG = "WallpaperActivity";

    /**
     * Context Object
     */
    private Context mContext;

    /**
     * Widgets
     */
    private WallpaperFragment fragContent;

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
        setPageTitle(getString(R.string.wallpaper));
        setPageTips("");
        loadContentFrag();
    }

    private void loadContentFrag() {
        if (fragContent == null) {
            fragContent = new WallpaperFragment();
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

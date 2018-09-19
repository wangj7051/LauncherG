package com.tricheer.launcherg.frags.display;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.display.WallpaperActivity;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.engine.WallpaperUtil;
import com.tricheer.launcherg.frags.BaseFragment;

/**
 * [Menu-Display-Screen timeout] Fragment
 *
 * @author Jun.Wang
 */
public class WallpaperFragment extends BaseFragment {
    // TAG
    private static final String TAG = "WallpaperFragment";

    // Widgets
    private View fragV;

    //Variables
    private WallpaperActivity mAttachedActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (WallpaperActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_display_wallpaper, null);
        return fragV;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
    }

    @Override
    public void onGetKeyEvent(KeyEvent event) {
    }

    @Override
    public void onKeyPressed(int keyCode) {
        Log.i(TAG, "onKeyPressed(" + keyCode + ")");
        switch (keyCode) {
            case KeypadManager.BACK:
                mAttachedActivity.onBackPressed();
                break;
            case KeypadManager.LEFT:
                WallpaperUtil.setPrev(mAttachedActivity);
                break;
            case KeypadManager.RIGHT:
                WallpaperUtil.setNext(mAttachedActivity);
                break;
        }
    }
}
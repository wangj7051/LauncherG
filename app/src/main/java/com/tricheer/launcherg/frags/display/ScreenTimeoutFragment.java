package com.tricheer.launcherg.frags.display;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.display.ScreenTimeoutActivity;
import com.tricheer.launcherg.engine.AppStackManager;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.engine.ScreenTimoutManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.engine.PreferUtils;
import com.tricheer.launcherg.view.MsgToast;

/**
 * [Menu-Display-Screen timeout] Fragment
 *
 * @author Jun.Wang
 */
public class ScreenTimeoutFragment extends BaseFragment {
    // TAG
    private static final String TAG = "ScreenTimeoutFragment";

    // Widgets
    private View fragV;
    private TextView tvDuration;

    //Variables
    private ScreenTimeoutActivity mAttachedActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (ScreenTimeoutActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_display_screen_timeout, null);
        return fragV;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        //widgets
        tvDuration = fragV.findViewById(R.id.tv_duration);
        setTimeoutDuration(true);
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
            case KeypadManager.FUNC:
                saveTimeoutDuration();
                break;
            case KeypadManager.NUM0:
                ScreenTimoutManager.setTimout(0);
                setTimeoutDuration(false);
                break;
            case KeypadManager.NUM1:
                ScreenTimoutManager.setTimout(1);
                setTimeoutDuration(false);
                break;
            case KeypadManager.NUM2:
                ScreenTimoutManager.setTimout(2);
                setTimeoutDuration(false);
                break;
            case KeypadManager.NUM3:
                ScreenTimoutManager.setTimout(3);
                setTimeoutDuration(false);
                break;
            case KeypadManager.NUM4:
                ScreenTimoutManager.setTimout(4);
                setTimeoutDuration(false);
                break;
            case KeypadManager.NUM5:
                ScreenTimoutManager.setTimout(5);
                setTimeoutDuration(false);
                break;
            case KeypadManager.NUM6:
                ScreenTimoutManager.setTimout(6);
                setTimeoutDuration(false);
                break;
            case KeypadManager.NUM7:
                ScreenTimoutManager.setTimout(7);
                setTimeoutDuration(false);
                break;
            case KeypadManager.NUM8:
                ScreenTimoutManager.setTimout(8);
                setTimeoutDuration(false);
                break;
            case KeypadManager.NUM9:
                ScreenTimoutManager.setTimout(9);
                setTimeoutDuration(false);
                break;
        }
    }

    private void setTimeoutDuration(boolean isInit) {
        if (isInit) {
            int duration = PreferUtils.getScreenTimout(false, 0);
            ScreenTimoutManager.initTimeout(duration);
        }
        tvDuration.setText(ScreenTimoutManager.getScreenTimeout());
    }

    private void saveTimeoutDuration() {
        try {
            String strDuration = ScreenTimoutManager.getScreenTimeout();
            int duration = Integer.valueOf(strDuration);
            if (duration > 60) {
                MsgToast.toastShort(mAttachedActivity, getString(R.string.toast_set_invalid_input));
            } else {
                PreferUtils.getScreenTimout(true, duration);
                AppStackManager.notifyScreenTimoutChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
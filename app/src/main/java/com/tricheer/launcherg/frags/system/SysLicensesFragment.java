package com.tricheer.launcherg.frags.system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.system.SysLicensesActivity;
import com.tricheer.launcherg.activity.system.SysVersionActivity;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;

/**
 * [Menu-System-Licenses] Fragment
 *
 * @author Jun.Wang
 */
public class SysLicensesFragment extends BaseFragment {
    // TAG
    private static final String TAG = "SysLicensesFragment";

    // Widgets
    private View fragV;

    //Variables
    private SysLicensesActivity mAttachedActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (SysLicensesActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_sys_licenses, null);
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
        }
    }
}
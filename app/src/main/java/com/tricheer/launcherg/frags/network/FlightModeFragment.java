package com.tricheer.launcherg.frags.network;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.network.FlightModeActivity;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;

/**
 * [Menu-Network-Flight mode] Fragment
 *
 * @author Jun.Wang
 */
public class FlightModeFragment extends BaseFragment {
    // TAG
    private static final String TAG = "FlightModeFragment";

    // Widgets
    private View fragV;

    //Variables
    private FlightModeActivity mAttachedActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (FlightModeActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_network_flightmode, null);
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
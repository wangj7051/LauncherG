package com.tricheer.launcherg.frags.sound;

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
import com.tricheer.launcherg.activity.sound.RingtoneVolActivity;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.engine.SysVolManager;
import com.tricheer.launcherg.frags.BaseFragment;

/**
 * [Menu-Sound-Ringtone Volume] Fragment
 *
 * @author Jun.Wang
 */
public class RingtoneVolFragment extends BaseFragment {
    // TAG
    private static final String TAG = "RingtoneVolFragment";

    // Widgets
    private View fragV;
    private TextView tvVol;

    //Variables
    private RingtoneVolActivity mAttachedActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (RingtoneVolActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_sound_ringtone_vol, null);
        return fragV;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        //Widgets
        tvVol = fragV.findViewById(R.id.tv_vol);
        tvVol.setText(String.valueOf(getVolLevel()));
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
            case KeypadManager.UP:
                raiseVol();
                break;
            case KeypadManager.DOWN:
                reduceVol();
                break;
        }
    }

    private int getVolLevel() {
        return SysVolManager.instance().getVolLevelOfRing();
    }

    private void raiseVol() {
        int regions = SysVolManager.instance().getRegions();
        int currVolLevel = getVolLevel();
        currVolLevel++;
        if (currVolLevel < regions) {
            SysVolManager.instance().setVolOfRing(currVolLevel);
            tvVol.setText(String.valueOf(currVolLevel));
        }
    }

    private void reduceVol() {
        int currVolLevel = getVolLevel();
        currVolLevel--;
        if (currVolLevel >= 0) {
            SysVolManager.instance().setVolOfRing(currVolLevel);
            tvVol.setText(String.valueOf(currVolLevel));
        }
    }
}
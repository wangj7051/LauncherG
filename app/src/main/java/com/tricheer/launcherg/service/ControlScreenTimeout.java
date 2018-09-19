package com.tricheer.launcherg.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

public class ControlScreenTimeout {
    //TAG
    private final String TAG = "ControlScreenTimeout";

    private Context mContext;
    private Handler mHandler = new Handler();

    public ControlScreenTimeout(Context context) {
        mContext = context;
        registerScreenReceiver(true);
    }

    private BroadcastReceiver mScreenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(TAG, "action: " + action);
            if (Intent.ACTION_SCREEN_ON.equalsIgnoreCase(action)) {
            } else if (Intent.ACTION_SCREEN_OFF.equalsIgnoreCase(action)) {
            }
        }
    };

    private void registerScreenReceiver(boolean isReg) {
        try {
            if (isReg) {
                IntentFilter ifScreen = new IntentFilter();
                ifScreen.addAction(Intent.ACTION_SCREEN_ON);
                ifScreen.addAction(Intent.ACTION_SCREEN_OFF);
                mContext.registerReceiver(mScreenReceiver, ifScreen);
            } else {
                mContext.unregisterReceiver(mScreenReceiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        registerScreenReceiver(false);
    }
}

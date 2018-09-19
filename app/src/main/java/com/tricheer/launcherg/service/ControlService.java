package com.tricheer.launcherg.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Control Service
 * <p>1.Control Battery listener</p>
 */
public class ControlService extends Service {

    //Control screen timeout
    private ControlScreenTimeout mControlScreenTimeout;

    /**
     * Get Service Object
     */
    public class LocalBinder extends Binder {
        public ControlService getService() {
            return ControlService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mControlScreenTimeout = new ControlScreenTimeout(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Get {@link ControlScreenTimeout} Object
     *
     * @return {@link ControlScreenTimeout}
     */
    public ControlScreenTimeout getControlScreenTimeout() {
        return mControlScreenTimeout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Destroy ControlScreenTimeout
        if (mControlScreenTimeout != null) {
            mControlScreenTimeout.onDestroy();
            mControlScreenTimeout = null;
        }
    }
}

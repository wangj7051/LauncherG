package com.tricheer.launcherg.activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.tricheer.launcherg.MainActivity;
import com.tricheer.launcherg.R;
import com.tricheer.launcherg.engine.AppComManager;
import com.tricheer.launcherg.engine.AppStackManager;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.engine.LanguageManager;
import com.tricheer.launcherg.engine.PhoneCallerIDManager;
import com.tricheer.launcherg.engine.PhonePrefixManager;
import com.tricheer.launcherg.engine.ScreenTimoutManager;
import com.tricheer.launcherg.engine.SysFontManager;
import com.tricheer.launcherg.engine.SysRingToneManager;
import com.tricheer.launcherg.engine.SysVolManager;
import com.tricheer.launcherg.service.ControlService;
import com.tricheer.launcherg.utils.SysConfigManager;

/**
 * Base Fragment Activity
 * <p>1. Process Stack</p>
 */
public abstract class BaseFragActivity extends FragmentActivity implements KeypadManager.KeyDelegate,
        SysFontManager.SysFontDelegate,
        LanguageManager.LanguageDelegate,
        AppComManager.AppComDelegate,
        ScreenTimoutManager.ScreenTimoutDelegate,
        SysVolManager.SysVolDelegate,
        SysRingToneManager.SysRingToneDelegate,
        PhoneCallerIDManager.PhoneCallerIDDelegate,
        PhonePrefixManager.PhonePrefixDelegate {

    /**
     * If forcibly reload page when "OnResume()" is executing
     */
    private boolean isReloadForciblyOnResume = false;

    /**
     * {@link ControlService} connection
     */
    private ServiceConnection mControlServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            ControlService controlService = ((ControlService.LocalBinder) binder).getService();
            onControlServiceChanged(controlService, 1);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            onControlServiceChanged(null, 0);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppStackManager.addToStack(this);
        init();
    }

    private void init() {
        setPageFontStyle();
    }

    private void setPageFontStyle() {
        if (this instanceof MainActivity) {
            setTheme(R.style.THEME_FIXED);
        } else {
            setTheme(SysFontManager.getFontStyleID());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        //Process Event
        onGetKeyEvent(event);
        if (event.getAction() == KeyEvent.ACTION_UP) {
            onKeyPressed(event.getKeyCode());
        }
        return true;
    }

    /**
     * Keep default fontscale
     */
    @Override
    public Resources getResources() {
//        return super.getResources();
        return SysConfigManager.getResWithDefaultFontScale(super.getResources());
    }

    @Override
    public void onFontSizeChanged() {
        setPageFontStyle();
        isReloadForciblyOnResume = true;
    }

    @Override
    public void onLanguageChanged() {
        isReloadForciblyOnResume = true;
    }

    protected boolean isReloadForciblyOnResume() {
        if (isReloadForciblyOnResume) {
            isReloadForciblyOnResume = false;
            return true;
        }
        return false;
    }

    @Override
    public void refreshOnScreenID(boolean isShow) {
    }

    @Override
    public void onScreenTimeoutChanged() {
    }

    /**
     * Notify on system volume status changed
     *
     * @param flag : <p>1 Ringtones changed</p>
     *             <p>2 Ringtone volume changed</p>
     *             <p>3 In-call volume changed</p>
     *             <p>4 Speaker volume changed</p>
     *             <p>5 Keytouch tone changed</p>
     */
    @Override
    public void onSysVolChanged(int flag) {
    }

    @Override
    public void onSysRingToneChanged(int type) {
    }

    /**
     * {@link ControlService} status change method
     *
     * @param service:{@link ControlService}
     * @param status:1       connected; 0 disconnected
     */
    protected void onControlServiceChanged(ControlService service, int status) {
    }

    @Override
    public void onSendCallerIdON(boolean isON) {
    }

    @Override
    public void onPhonePrefixON(boolean isON) {
    }

    @Override
    public void onPhonePrefixChanged(String prefix) {
    }

    /**
     * Get {@link ControlService}
     *
     * @param context {@link Context}
     * @param flag    1.start and bind; 2.bind; 3.unbind; 4.stop
     */
    protected void bindControlService(Context context, int flag) {
        try {
            Intent itControlService = new Intent(context, ControlService.class);
            switch (flag) {
                case 1:
                    startService(itControlService);
                case 2:
                    bindService(itControlService, mControlServiceConnection, Service.BIND_AUTO_CREATE);
                    break;
                case 3:
                    unbindService(mControlServiceConnection);
                    break;
                case 4:
                    stopService(itControlService);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppStackManager.removeFromStack(this);
    }

    /**
     * Message handler
     */
    protected static class MsgHandler extends Handler {
        public static final long COM_DELAY_TIME = 3000;
        public static final int MSG_DELAY_FINISH = 1;

        private Activity mCurrActivity;

        public MsgHandler(Activity activity) {
            mCurrActivity = activity;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_DELAY_FINISH:
                    mCurrActivity.onBackPressed();
                    break;
            }
        }
    }

    /**
     * Cache on Fragment recreate ...
     * <p>1. Common {@link android.widget.ListView} selected position</p>
     */
    protected static final class CacheLv {
        // ListView selected position
        private static int mmSelectedPos = -1;
        private static final String LV_SELECTED_POS = "BUNDLE_SELECTED_POS";

        public static void cachePos(Intent intent, int pos) {
            if (intent != null) {
                intent.putExtra(LV_SELECTED_POS, pos);
            }
        }

        public static void restorePos(Intent intent) {
            mmSelectedPos = intent.getIntExtra(LV_SELECTED_POS, -1);
        }

        public static int getPos() {
            int resVal = (mmSelectedPos == -1) ? 0 : mmSelectedPos;
            mmSelectedPos = -1;
            return resVal;
        }
    }
}

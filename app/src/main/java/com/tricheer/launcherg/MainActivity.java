package com.tricheer.launcherg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.tricheer.launcherg.activity.BaseFragActivity;
import com.tricheer.launcherg.activity.MenuActivity;
import com.tricheer.launcherg.activity.sound.RingtoneVolActivity;
import com.tricheer.launcherg.engine.AppStackManager;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.utils.DateManager;
import com.tricheer.launcherg.utils.DisplayUtil;

/**
 * Launcher Main Activity
 *
 * @author Jun.Wang
 */
public class MainActivity extends BaseFragActivity {
    /**
     * TAG
     */
    private static final String TAG = "MainActivity";

    /**
     * Context Object
     */
    private Context mContext;
    private Handler mHandler = new MyHandler(this);

    // Widgets
    private TextView tvDate, tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Log.i(TAG, "dp: " + DisplayUtil.px2dip(mContext, 210));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.sendEmptyMessageDelayed(MyHandler.MSG_CLEAR_STACK, MyHandler.COM_DELAY_TIME);
    }

    private void init() {
        // Variables
        mContext = this;

        // Widgets
        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);

        //
        bindControlService(this, 1);
        loadPage();
    }

    private void loadPage() {
        long currTimeMS = System.currentTimeMillis();
        tvDate.setText(DateManager.getFormattedTime(currTimeMS, DateManager.FORMAT_yMdw1));
        tvTime.setText(DateManager.getHourBySysFormat(mContext, currTimeMS));
    }

    @Override
    public void onGetKeyEvent(KeyEvent event) {
    }

    @Override
    public void onKeyPressed(int keyCode) {
        Log.i(TAG, "onKeyPressed(" + keyCode + ")");
        switch (keyCode) {
            case KeypadManager.FUNC:
                startActivity(new Intent(mContext, MenuActivity.class));
                break;
            case KeypadManager.UP:
            case KeypadManager.DOWN:
                startActivity(new Intent(mContext, RingtoneVolActivity.class));
                break;
            case KeypadManager.LEFT://TODO
                break;
            case KeypadManager.RIGHT://TODO
                break;
            case KeypadManager.QUICK1://TODO
                break;
            case KeypadManager.QUICK2://TODO
                break;
            case KeypadManager.QUICK3://TODO
                break;
            case KeypadManager.NUM0://TODO
            case KeypadManager.NUM1:
            case KeypadManager.NUM2:
            case KeypadManager.NUM3:
            case KeypadManager.NUM4:
            case KeypadManager.NUM5:
            case KeypadManager.NUM6:
            case KeypadManager.NUM7:
            case KeypadManager.NUM8:
            case KeypadManager.NUM9:
            case KeypadManager.POUND:
            case KeypadManager.STAR:
                break;
            case KeypadManager.WORD://TODO
                break;
            case KeypadManager.HANDS_FREE://TODO
                break;
        }
    }

    @Override
    public void onFontSizeChanged() {
        super.onFontSizeChanged();
    }

    @Override
    protected void onDestroy() {
        bindControlService(this, 4);
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    /**
     * Message Handler
     */
    private static class MyHandler extends Handler {

        static final long COM_DELAY_TIME = 300;
        static final int MSG_CLEAR_STACK = 1;

        private BaseFragActivity mAct;

        MyHandler(BaseFragActivity act) {
            mAct = act;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_CLEAR_STACK:
                    AppStackManager.removeAllFromStack(mAct);
                    break;
            }
        }
    }
}

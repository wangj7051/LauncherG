package com.tricheer.launcherg.engine;

import android.view.KeyEvent;

/**
 * KeyCode Manager Class
 * <p>$adb shell input keyevent KEYCODE_A</p>
 */
public class KeypadManager {

    /**
     * Key Delegate
     */
    public interface KeyDelegate {
        public void onGetKeyEvent(KeyEvent event);

        public void onKeyPressed(int keyCode);
    }

    //$adb shell
    //$input keyevent KEYCODE_A
    //$input keyevent 10000

    // Quick key code
    public static final int QUICK1 = KeyEvent.KEYCODE_F1;
    public static final int QUICK2 = KeyEvent.KEYCODE_F2;
    public static final int QUICK3 = KeyEvent.KEYCODE_F3;

    // Direction
    public static final int UP = KeyEvent.KEYCODE_DPAD_UP;
    public static final int DOWN = KeyEvent.KEYCODE_DPAD_DOWN;
    public static final int LEFT = KeyEvent.KEYCODE_DPAD_LEFT;
    public static final int RIGHT = KeyEvent.KEYCODE_DPAD_RIGHT;
    public static final int FUNC = KeyEvent.KEYCODE_DPAD_CENTER;

    // Operate - Left
    public static final int DELETE = KeyEvent.KEYCODE_DEL;
    public static final int WORD = KeyEvent.KEYCODE_F4;

    // Number Key
    public static final int NUM0 = KeyEvent.KEYCODE_0;
    public static final int NUM1 = KeyEvent.KEYCODE_1;
    public static final int NUM2 = KeyEvent.KEYCODE_2;
    public static final int NUM3 = KeyEvent.KEYCODE_3;
    public static final int NUM4 = KeyEvent.KEYCODE_4;
    public static final int NUM5 = KeyEvent.KEYCODE_5;
    public static final int NUM6 = KeyEvent.KEYCODE_6;
    public static final int NUM7 = KeyEvent.KEYCODE_7;
    public static final int NUM8 = KeyEvent.KEYCODE_8;
    public static final int NUM9 = KeyEvent.KEYCODE_9;
    public static final int STAR = KeyEvent.KEYCODE_STAR;
    public static final int POUND = KeyEvent.KEYCODE_POUND;

    // Operate - Right
    public static final int BACK = KeyEvent.KEYCODE_BACK;
    public static final int HOLD_ON = KeyEvent.KEYCODE_F5;
    public static final int HANDS_FREE = KeyEvent.KEYCODE_F7;
    public static final int AUTO_RECORD = KeyEvent.KEYCODE_F6;
}

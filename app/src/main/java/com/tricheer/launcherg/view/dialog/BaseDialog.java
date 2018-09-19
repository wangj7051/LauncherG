package com.tricheer.launcherg.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.tricheer.launcherg.engine.KeypadManager;

/**
 * Base Dialog
 */
public abstract class BaseDialog extends AlertDialog implements KeypadManager.KeyDelegate {

    public BaseDialog(Context context) {
        super(context);
    }

    protected BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        //Process Event
        onGetKeyEvent(event);
        if (event.getAction() == KeyEvent.ACTION_UP) {
            onKeyPressed(event.getKeyCode());
        }
        return true;
    }

    @Override
    public void onGetKeyEvent(KeyEvent event) {
    }
}

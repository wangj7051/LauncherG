package com.tricheer.launcherg.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tricheer.launcherg.R;

/**
 * Message Toast
 *
 * @author Jun.Wang
 */
public class MsgToast {

    /**
     * Toast message with short duration={{@link Toast#LENGTH_LONG}}
     *
     * @param context:{@link Context}
     * @param msg:Message that you want to show in the toast.
     */
    public static void toastLong(Context context, String msg) {
        toast(context, Toast.LENGTH_LONG, msg);
    }

    /**
     * Toast message with short duration={{@link Toast#LENGTH_SHORT}}
     *
     * @param context:{@link Context}
     * @param msg:Message that you want to show in the toast.
     */
    public static void toastShort(Context context, String msg) {
        toast(context, Toast.LENGTH_SHORT, msg);
    }

    @SuppressLint("InflateParams")
    private static void toast(Context context, int duration, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        //Create View
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View msgV = layoutInflater.inflate(R.layout.v_toast, null);
        TextView tvMsg = msgV.findViewById(R.id.tv_msg);
        tvMsg.setText(msg);

        //Toast
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(duration);
        toast.setView(msgV);
        toast.show();
    }
}

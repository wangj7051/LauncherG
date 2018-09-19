package com.tricheer.launcherg.utils;

import android.annotation.IdRes;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

/**
 * Text Util Methods
 *
 * @author Jun.Wang
 */
public class TxtStyleUtil {
    /**
     * Part of text background color change
     */
    public static SpannableStringBuilder getBgBuilder(Context context, String s, int start, int end, @IdRes int bgResID) {
        BackgroundColorSpan colorSpan = new BackgroundColorSpan(context.getResources().getColor(bgResID));
        SpannableStringBuilder ssb = new SpannableStringBuilder(s);
        ssb.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }

    /**
     * Part of text color change
     */
    public static SpannableStringBuilder getTxtBuilder(Context context, String s, int start, int end, @IdRes int txtResID) {
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(txtResID));
        SpannableStringBuilder ssb = new SpannableStringBuilder(s);
        ssb.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return ssb;
    }

    /**
     * Part of text & background color change
     */
    public static SpannableStringBuilder getBgNTxtBuilder(Context context, String s, int start, int end, @IdRes int txtResID, @IdRes int bgResID) {
        BackgroundColorSpan colorSpan1 = new BackgroundColorSpan(context.getResources().getColor(bgResID));
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(context.getResources().getColor(txtResID));
        SpannableStringBuilder ssb = new SpannableStringBuilder(s);
        ssb.setSpan(colorSpan1, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(colorSpan2, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return ssb;
    }
}

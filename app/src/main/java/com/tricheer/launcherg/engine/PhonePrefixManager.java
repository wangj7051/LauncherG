package com.tricheer.launcherg.engine;

import android.text.TextUtils;

/**
 * [Menu-Phone-Prefix] Manager
 *
 * @author Jun.Wang
 */
public class PhonePrefixManager {

    /**
     * Cursor position
     */
    private int mCursorPos;

    /**
     * Prefix number string
     */
    private StringBuffer mPrefixNumber;

    /**
     * Prefix number length
     * <p>Prefix should be equals to or shorter then {@link #PREFIX_LEN}</p>
     */
    private static final int PREFIX_LEN = 8;

    /**
     * System Font Delegate
     */
    public interface PhonePrefixDelegate {
        public void onPhonePrefixON(boolean isON);

        public void onPhonePrefixChanged(String prefix);
    }

    public PhonePrefixManager() {
        //Cursor position
        mPrefixNumber = new StringBuffer(PreferUtils.getPhonePrefix(false, ""));
        mCursorPos = TextUtils.isEmpty(mPrefixNumber) ? 1 : mPrefixNumber.length();
    }

    public String getPrefix() {
        return mPrefixNumber.toString();
    }

    public int getCursorPos() {
        return mCursorPos;
    }

    public boolean isPrefixFocused() {
        if (TextUtils.isEmpty(mPrefixNumber)) {
            return false;
        }
        return mCursorPos < mPrefixNumber.length();
    }

    public int focusNext() {
        mCursorPos++;
        if (TextUtils.isEmpty(mPrefixNumber)) {
            mCursorPos = 1;
        } else if (mCursorPos > mPrefixNumber.length()) {
            mCursorPos = mPrefixNumber.length();
        }
        return mCursorPos;
    }

    public int focusPrev() {
        mCursorPos--;
        if (TextUtils.isEmpty(mPrefixNumber)) {
            mCursorPos = 1;
        } else if (mCursorPos < 0) {
            mCursorPos = 0;
        }
        return mCursorPos;
    }

    public String insert(String sChar) {
        if (mPrefixNumber.length() < PREFIX_LEN) {
            if (TextUtils.isEmpty(mPrefixNumber)) {
                mPrefixNumber.append(sChar);
            } else if (mCursorPos >= mPrefixNumber.length()) {
                mPrefixNumber.append(sChar);
            } else {
                mPrefixNumber.insert(mCursorPos, sChar);
            }
            focusNext();
        }
        return mPrefixNumber.toString();
    }

    public String delete() {
        if (!TextUtils.isEmpty(mPrefixNumber)) {
            if (mCursorPos >= mPrefixNumber.length()) {
                mPrefixNumber.deleteCharAt(mPrefixNumber.length() - 1);
                focusPrev();
            } else {
                mPrefixNumber.deleteCharAt(mCursorPos);
            }
        }
        return mPrefixNumber.toString();
    }
}

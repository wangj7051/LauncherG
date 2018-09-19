package com.tricheer.launcherg.frags.phone;

import android.annotation.IdRes;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.phone.PhonePrefixNumberActivity;
import com.tricheer.launcherg.engine.AppStackManager;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.engine.PhonePrefixManager;
import com.tricheer.launcherg.engine.PreferUtils;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.utils.TxtStyleUtil;
import com.tricheer.launcherg.view.dialog.ConfirmDialog;

/**
 * [Menu-Phone-Prefix-Number] Fragment
 *
 * @author Jun.Wang
 */
public class PhonePrefixNumberFragment extends BaseFragment {
    // TAG
    private static final String TAG = "PrefixNumberFragment";

    // Widgets
    private View fragV;
    private TextView tvPrefix;
    private ImageView ivArrow;

    private ConfirmDialog mConfirmDialog;

    //Variables
    private PhonePrefixNumberActivity mAttachedActivity;
    private PhonePrefixManager mPrefixManager;

    @IdRes
    private static final int BG_CURSOR = R.color.white_10percent;
    @IdRes
    private static final int BG_TRANSPARENT = android.R.color.transparent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (PhonePrefixNumberActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_phone_prefix_number, null);
        return fragV;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        //Variables
        mPrefixManager = new PhonePrefixManager();

        //Widgets
        tvPrefix = fragV.findViewById(R.id.tv_prefix);
        tvPrefix.setText(mPrefixManager.getPrefix());

        ivArrow = fragV.findViewById(R.id.iv_arrow);
        ivArrow.setBackgroundColor(getResources().getColor(BG_CURSOR));
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
            case KeypadManager.DELETE:
                deletePrefix();
                break;
            case KeypadManager.LEFT:
                focusPrev();
                break;
            case KeypadManager.RIGHT:
                focusNext();
                break;
            case KeypadManager.FUNC:
                confirm();
                break;
            case KeypadManager.NUM1:
                addPrefix("1");
                break;
            case KeypadManager.NUM2:
                addPrefix("2");
                break;
            case KeypadManager.NUM3:
                addPrefix("3");
                break;
            case KeypadManager.NUM4:
                addPrefix("4");
                break;
            case KeypadManager.NUM5:
                addPrefix("5");
                break;
            case KeypadManager.NUM6:
                addPrefix("6");
                break;
            case KeypadManager.NUM7:
                addPrefix("7");
                break;
            case KeypadManager.NUM8:
                addPrefix("8");
                break;
            case KeypadManager.NUM9:
                addPrefix("9");
                break;
            case KeypadManager.NUM0:
                addPrefix("0");
                break;
            case KeypadManager.STAR:
                addPrefix("*");
                break;
            case KeypadManager.POUND:
                addPrefix("#");
                break;
        }
    }

    private void addPrefix(String sChar) {
        setPrefix(mPrefixManager.insert(sChar), mPrefixManager.getCursorPos());
    }

    private void deletePrefix() {
        setPrefix(mPrefixManager.delete(), mPrefixManager.getCursorPos());
    }

    private void setPrefix(String prefix, int cursorPos) {
        try {
            if (mPrefixManager.isPrefixFocused()) {
                ivArrow.setBackgroundColor(getResources().getColor(BG_TRANSPARENT));
                tvPrefix.setText(TxtStyleUtil.getBgBuilder(mAttachedActivity, prefix, cursorPos, cursorPos + 1, BG_CURSOR));
            } else {
                ivArrow.setBackgroundColor(getResources().getColor(BG_CURSOR));
                tvPrefix.setText(prefix);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void focusNext() {
        setPrefix(mPrefixManager.getPrefix(), mPrefixManager.focusNext());
    }

    private void focusPrev() {
        setPrefix(mPrefixManager.getPrefix(), mPrefixManager.focusPrev());
    }

    private void confirm() {
        if (mConfirmDialog == null) {
            mConfirmDialog = new ConfirmDialog(mAttachedActivity);
            mConfirmDialog.setOpItems(getString(R.string.dialog_msg_save));
            mConfirmDialog.setConfirmCallback(new ConfirmDialog.ConfirmDelegate() {
                @Override
                public void onCallback(int flag) {
                    Log.i(TAG, "flag: " + flag);
                    switch (flag) {
                        case -1:
                            PreferUtils.getPhonePrefix(true, mPrefixManager.getPrefix());
                            AppStackManager.notifyPhonePrefixChanged(mPrefixManager.getPrefix());
                            break;
                        case -2:
                            break;
                    }
                }
            });
        }
        mConfirmDialog.show();
    }
}
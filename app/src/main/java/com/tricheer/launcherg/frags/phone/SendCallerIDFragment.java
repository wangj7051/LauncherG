package com.tricheer.launcherg.frags.phone;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.phone.SendCallerIDActivity;
import com.tricheer.launcherg.adapter.phone.SendCallerIDFragItemAdapter;
import com.tricheer.launcherg.engine.AppComManager;
import com.tricheer.launcherg.engine.AppStackManager;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.phone.SendCallerIDFragItem;
import com.tricheer.launcherg.engine.PreferUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * [Menu-Display-Send Caller ID] Fragment
 *
 * @author Jun.Wang
 */
public class SendCallerIDFragment extends BaseFragment {
    // TAG
    private static final String TAG = "SendCallerIDFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private SendCallerIDActivity mAttachedActivity;
    private SendCallerIDFragItemAdapter mLvItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (SendCallerIDActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_phone_sendcallerid, null);
        return fragV;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        //
        lvContent = fragV.findViewById(R.id.lv_content);
        loadLvContent();
    }

    private void loadLvContent() {
        // Construct data list
        List<SendCallerIDFragItem> listItems = new ArrayList<SendCallerIDFragItem>();

        SendCallerIDFragItem item01 = new SendCallerIDFragItem();
        item01.name = mAttachedActivity.getString(R.string.on_upper);
        item01.setTag(AppComManager.ON);
        listItems.add(item01);

        SendCallerIDFragItem item02 = new SendCallerIDFragItem();
        item02.name = mAttachedActivity.getString(R.string.off_upper);
        item02.setTag(AppComManager.ON);
        listItems.add(item02);

        // Load ListView
        mLvItemAdapter = new SendCallerIDFragItemAdapter(mAttachedActivity, 0);
        mLvItemAdapter.setListItems(listItems);
        mLvItemAdapter.setSelectPos(getSelectedPosByTag());
        lvContent.setAdapter(mLvItemAdapter);
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
            case KeypadManager.UP:
                mLvItemAdapter.selectPrev();
                break;
            case KeypadManager.DOWN:
                mLvItemAdapter.selectNext();
                break;
            case KeypadManager.FUNC:
                loadPageByIdx(mLvItemAdapter.getSelectPos());
                break;
        }
    }

    public void loadPageByIdx(int pos) {
        //If loading position is not equals selected position
        if (pos != mLvItemAdapter.getSelectPos()) {
            mLvItemAdapter.selectPos(pos);
        }

        //Load selected page
        if (pos != getSelectedPosByTag()) {
            boolean isShow = (pos == 0);
            PreferUtils.isSendCallerIdON(true, isShow);
            AppStackManager.notifySendCallerIdON(isShow);
        }
    }

    /**
     * Get the select position of ListView.
     */
    private int getSelectedPosByTag() {
        return PreferUtils.isSendCallerIdON(false, false) ? 0 : 1;
    }
}
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
import com.tricheer.launcherg.activity.phone.PhonePrefixOnOffActivity;
import com.tricheer.launcherg.adapter.phone.PhonePrefixOnOffItemAdapter;
import com.tricheer.launcherg.engine.AppComManager;
import com.tricheer.launcherg.engine.AppStackManager;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.phone.PhonePrefixOnOffFragItem;
import com.tricheer.launcherg.engine.PreferUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * [Menu-Phone-Prefix-ON/OFF] Fragment
 *
 * @author Jun.Wang
 */
public class PhonePrefixOnOffFragment extends BaseFragment {
    // TAG
    private static final String TAG = "PrefixOnOffFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private PhonePrefixOnOffActivity mAttachedActivity;
    private PhonePrefixOnOffItemAdapter mLvItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (PhonePrefixOnOffActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_phone_prefix_onoff, null);
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
        List<PhonePrefixOnOffFragItem> listItems = new ArrayList<PhonePrefixOnOffFragItem>();

        PhonePrefixOnOffFragItem item01 = new PhonePrefixOnOffFragItem();
        item01.name = mAttachedActivity.getString(R.string.on_upper);
        item01.setTag(AppComManager.ON);
        listItems.add(item01);

        PhonePrefixOnOffFragItem item02 = new PhonePrefixOnOffFragItem();
        item02.name = mAttachedActivity.getString(R.string.off_upper);
        item02.setTag(AppComManager.ON);
        listItems.add(item02);

        // Load ListView
        mLvItemAdapter = new PhonePrefixOnOffItemAdapter(mAttachedActivity, 0);
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
            PreferUtils.isPhonePrefixON(true, isShow);
            AppStackManager.notifyPhonePrefixON(isShow);
        }
    }

    /**
     * Get the select position of ListView.
     */
    private int getSelectedPosByTag() {
        return PreferUtils.isPhonePrefixON(false, false) ? 0 : 1;
    }
}
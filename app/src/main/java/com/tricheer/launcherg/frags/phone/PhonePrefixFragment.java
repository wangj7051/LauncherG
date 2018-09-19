package com.tricheer.launcherg.frags.phone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.phone.PhonePrefixActivity;
import com.tricheer.launcherg.activity.phone.PhonePrefixNumberActivity;
import com.tricheer.launcherg.activity.phone.PhonePrefixOnOffActivity;
import com.tricheer.launcherg.adapter.phone.PhonePrefixFragItemAdapter;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.phone.PhonePrefixFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * [Menu-Display-Prefix] Fragment
 *
 * @author Jun.Wang
 */
public class PhonePrefixFragment extends BaseFragment {
    // TAG
    private static final String TAG = "PhonePrefixFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private PhonePrefixActivity mAttachedActivity;
    private PhonePrefixFragItemAdapter mLvItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (PhonePrefixActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_phone_prefix, null);
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
        List<PhonePrefixFragItem> listItems = new ArrayList<PhonePrefixFragItem>();

        PhonePrefixFragItem item01 = new PhonePrefixFragItem();
        item01.name = mAttachedActivity.getString(R.string.prefix_onoff);
        item01.cls = PhonePrefixOnOffActivity.class;
        listItems.add(item01);

        PhonePrefixFragItem item02 = new PhonePrefixFragItem();
        item02.name = mAttachedActivity.getString(R.string.prefix_num);
        item02.cls = PhonePrefixNumberActivity.class;
        listItems.add(item02);

        // Load ListView
        mLvItemAdapter = new PhonePrefixFragItemAdapter(mAttachedActivity, 0);
        mLvItemAdapter.setListItems(listItems);
        mLvItemAdapter.setSelectPos(0);
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
            case KeypadManager.NUM1:
                loadPageByIdx(0);
                break;
            case KeypadManager.NUM2:
                loadPageByIdx(1);
                break;
        }
    }

    public void loadPageByIdx(int pos) {
        //If loading position is not equals selected position
        if (pos != mLvItemAdapter.getSelectPos()) {
            mLvItemAdapter.selectPos(pos);
        }

        //Load selected page
        PhonePrefixFragItem item = mLvItemAdapter.getItem(pos);
        if (item != null) {
            startActivity(new Intent(mAttachedActivity, item.cls));
        }
    }
}
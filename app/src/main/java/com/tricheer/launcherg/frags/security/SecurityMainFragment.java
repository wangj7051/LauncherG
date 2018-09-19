package com.tricheer.launcherg.frags.security;

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
import com.tricheer.launcherg.activity.phone.SendCallerIDActivity;
import com.tricheer.launcherg.activity.phone.TotalCallTimeActivity;
import com.tricheer.launcherg.activity.security.SecurityCodeActivity;
import com.tricheer.launcherg.activity.security.SecurityMainActivity;
import com.tricheer.launcherg.activity.security.SecurityResetActivity;
import com.tricheer.launcherg.adapter.security.SecurityMainFragItemAdapter;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.security.SecurityMainFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Security main page Fragment
 *
 * @author Jun.Wang
 */
public class SecurityMainFragment extends BaseFragment {
    // TAG
    private static final String TAG = "SecurityMainFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private SecurityMainActivity mAttachedActivity;
    private SecurityMainFragItemAdapter mLvItemAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (SecurityMainActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_security_main, null);
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
        List<SecurityMainFragItem> listItems = new ArrayList<SecurityMainFragItem>();

        SecurityMainFragItem item01 = new SecurityMainFragItem();
        item01.name = mAttachedActivity.getString(R.string.security_code);
        item01.cls = SecurityCodeActivity.class;
        listItems.add(item01);

        SecurityMainFragItem item02 = new SecurityMainFragItem();
        item02.name = mAttachedActivity.getString(R.string.reset);
        item02.cls = SecurityResetActivity.class;
        listItems.add(item02);

        // Load ListView
        mLvItemAdapter = new SecurityMainFragItemAdapter(mAttachedActivity, 0);
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
        SecurityMainFragItem item = mLvItemAdapter.getItem(pos);
        if (item != null) {
            startActivity(new Intent(mAttachedActivity, item.cls));
        }
    }
}
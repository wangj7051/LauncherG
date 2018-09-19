package com.tricheer.launcherg.frags.system;

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
import com.tricheer.launcherg.activity.system.SysLicensesActivity;
import com.tricheer.launcherg.activity.system.SysUpdatesActivity;
import com.tricheer.launcherg.activity.system.SysVersionActivity;
import com.tricheer.launcherg.activity.system.SystemMainActivity;
import com.tricheer.launcherg.adapter.system.SystemMainFragItemAdapter;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.system.SystemMainFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * [Menu-System] main page Fragment
 *
 * @author Jun.Wang
 */
public class SystemMainFragment extends BaseFragment {
    // TAG
    private static final String TAG = "SystemMainFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private SystemMainActivity mAttachedActivity;
    private SystemMainFragItemAdapter mLvItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (SystemMainActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_system_main, null);
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
        List<SystemMainFragItem> listItems = new ArrayList<SystemMainFragItem>();

        SystemMainFragItem item01 = new SystemMainFragItem();
        item01.name = mAttachedActivity.getString(R.string.sys_version);
        item01.cls = SysVersionActivity.class;
        listItems.add(item01);

        SystemMainFragItem item02 = new SystemMainFragItem();
        item02.name = mAttachedActivity.getString(R.string.sys_updates);
        item02.cls = SysUpdatesActivity.class;
        listItems.add(item02);

        SystemMainFragItem item03 = new SystemMainFragItem();
        item03.name = mAttachedActivity.getString(R.string.sys_licenses);
        item03.cls = SysLicensesActivity.class;
        listItems.add(item03);

        // Load ListView
        mLvItemAdapter = new SystemMainFragItemAdapter(mAttachedActivity, 0);
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
            case KeypadManager.NUM3:
                loadPageByIdx(2);
                break;
        }
    }

    public void loadPageByIdx(int pos) {
        //If loading position is not equals selected position
        if (pos != mLvItemAdapter.getSelectPos()) {
            mLvItemAdapter.selectPos(pos);
        }

        //Load selected page
        SystemMainFragItem item = mLvItemAdapter.getItem(pos);
        if (item != null) {
            startActivity(new Intent(mAttachedActivity, item.cls));
        }
    }
}
package com.tricheer.launcherg.frags.date_n_time;

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
import com.tricheer.launcherg.activity.date_n_time.DateNTimeAutoActivity;
import com.tricheer.launcherg.activity.date_n_time.DateNTimeHourFormatActivity;
import com.tricheer.launcherg.activity.date_n_time.DateNTimeMainActivity;
import com.tricheer.launcherg.activity.date_n_time.DateNTimeSetActivity;
import com.tricheer.launcherg.adapter.date_n_time.DateNTimeMainFragItemAdapter;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.date_n_time.DateNTimeMainFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * [Menu-Date&Time] main page Fragment
 *
 * @author Jun.Wang
 */
public class DateNTimeMainFragment extends BaseFragment {
    // TAG
    private static final String TAG = "DateNTimeMainFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private DateNTimeMainActivity mAttachedActivity;
    private DateNTimeMainFragItemAdapter mLvItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (DateNTimeMainActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_date_n_time_main, null);
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
        List<DateNTimeMainFragItem> listItems = new ArrayList<DateNTimeMainFragItem>();

        DateNTimeMainFragItem item01 = new DateNTimeMainFragItem();
        item01.name = mAttachedActivity.getString(R.string.date_ntime_set);
        item01.cls = DateNTimeSetActivity.class;
        listItems.add(item01);

        DateNTimeMainFragItem item02 = new DateNTimeMainFragItem();
        item02.name = mAttachedActivity.getString(R.string.date_ntime_hour_format);
        item02.cls = DateNTimeHourFormatActivity.class;
        listItems.add(item02);

        DateNTimeMainFragItem item03 = new DateNTimeMainFragItem();
        item03.name = mAttachedActivity.getString(R.string.date_ntime_auto);
        item03.cls = DateNTimeAutoActivity.class;
        listItems.add(item03);

        // Load ListView
        mLvItemAdapter = new DateNTimeMainFragItemAdapter(mAttachedActivity, 0);
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
        DateNTimeMainFragItem item = mLvItemAdapter.getItem(pos);
        if (item != null) {
            startActivity(new Intent(mAttachedActivity, item.cls));
        }
    }
}
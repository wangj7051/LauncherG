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
import com.tricheer.launcherg.activity.phone.PhoneMainActivity;
import com.tricheer.launcherg.activity.phone.PhonePrefixActivity;
import com.tricheer.launcherg.activity.phone.SendCallerIDActivity;
import com.tricheer.launcherg.activity.phone.TotalCallTimeActivity;
import com.tricheer.launcherg.adapter.phone.PhoneMainFragItemAdapter;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.phone.PhoneMainFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Phone main page Fragment
 *
 * @author Jun.Wang
 */
public class PhoneMainFragment extends BaseFragment {
    // TAG
    private static final String TAG = "PhoneMainFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private PhoneMainActivity mAttachedActivity;
    private PhoneMainFragItemAdapter mLvItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (PhoneMainActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_phone_main, null);
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
        List<PhoneMainFragItem> listItems = new ArrayList<PhoneMainFragItem>();

        PhoneMainFragItem item01 = new PhoneMainFragItem();
        item01.name = mAttachedActivity.getString(R.string.send_caller_id);
        item01.cls = SendCallerIDActivity.class;
        listItems.add(item01);

        PhoneMainFragItem item02 = new PhoneMainFragItem();
        item02.name = mAttachedActivity.getString(R.string.total_call_time);
        item02.cls = TotalCallTimeActivity.class;
        listItems.add(item02);

        PhoneMainFragItem item03 = new PhoneMainFragItem();
        item03.name = mAttachedActivity.getString(R.string.prefix);
        item03.cls = PhonePrefixActivity.class;
        listItems.add(item03);

        // Load ListView
        mLvItemAdapter = new PhoneMainFragItemAdapter(mAttachedActivity, 0);
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
        PhoneMainFragItem item = mLvItemAdapter.getItem(pos);
        if (item != null) {
            startActivity(new Intent(mAttachedActivity, item.cls));
        }
    }
}
package com.tricheer.launcherg.frags.network;

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
import com.tricheer.launcherg.activity.network.FlightModeActivity;
import com.tricheer.launcherg.activity.network.NetworkApnsActivity;
import com.tricheer.launcherg.activity.network.NetworkMainActivity;
import com.tricheer.launcherg.activity.network.NetworkOperatorsActivity;
import com.tricheer.launcherg.activity.network.NetworkStatusActivity;
import com.tricheer.launcherg.activity.network.NetworkTetheringActivity;
import com.tricheer.launcherg.adapter.network.NetworkMainFragItemAdapter;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.network.NetworkMainFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * [Menu-Network] main page Fragment
 *
 * @author Jun.Wang
 */
public class NetworkMainFragment extends BaseFragment {
    // TAG
    private static final String TAG = "NetworkMainFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private NetworkMainActivity mAttachedActivity;
    private NetworkMainFragItemAdapter mLvItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (NetworkMainActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_network_main, null);
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
        List<NetworkMainFragItem> listItems = new ArrayList<NetworkMainFragItem>();

        NetworkMainFragItem item01 = new NetworkMainFragItem();
        item01.name = mAttachedActivity.getString(R.string.flight_mode);
        item01.cls = FlightModeActivity.class;
        listItems.add(item01);

        NetworkMainFragItem item02 = new NetworkMainFragItem();
        item02.name = mAttachedActivity.getString(R.string.status);
        item02.cls = NetworkStatusActivity.class;
        listItems.add(item02);

        NetworkMainFragItem item03 = new NetworkMainFragItem();
        item03.name = mAttachedActivity.getString(R.string.operators);
        item03.cls = NetworkOperatorsActivity.class;
        listItems.add(item03);

        NetworkMainFragItem item04 = new NetworkMainFragItem();
        item04.name = mAttachedActivity.getString(R.string.network_apns);
        item04.cls = NetworkApnsActivity.class;
        listItems.add(item04);

        NetworkMainFragItem item05 = new NetworkMainFragItem();
        item05.name = mAttachedActivity.getString(R.string.tethering);
        item05.cls = NetworkTetheringActivity.class;
        listItems.add(item05);

        // Load ListView
        mLvItemAdapter = new NetworkMainFragItemAdapter(mAttachedActivity, 0);
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
            case KeypadManager.NUM4:
                loadPageByIdx(3);
                break;
            case KeypadManager.NUM5:
                loadPageByIdx(4);
                break;
        }
    }

    public void loadPageByIdx(int pos) {
        //If loading position is not equals selected position
        if (pos != mLvItemAdapter.getSelectPos()) {
            mLvItemAdapter.selectPos(pos);
        }

        //Load selected page
        NetworkMainFragItem item = mLvItemAdapter.getItem(pos);
        if (item != null) {
            startActivity(new Intent(mAttachedActivity, item.cls));
        }
    }
}
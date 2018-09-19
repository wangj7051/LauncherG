package com.tricheer.launcherg.frags;

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
import com.tricheer.launcherg.activity.MenuActivity;
import com.tricheer.launcherg.activity.contacts.ContactsMainActivity;
import com.tricheer.launcherg.activity.date_n_time.DateNTimeMainActivity;
import com.tricheer.launcherg.activity.display.DisplayMainActivity;
import com.tricheer.launcherg.activity.network.NetworkMainActivity;
import com.tricheer.launcherg.activity.phone.PhoneMainActivity;
import com.tricheer.launcherg.activity.profile.ProfileMainActivity;
import com.tricheer.launcherg.activity.security.SecurityMainActivity;
import com.tricheer.launcherg.activity.sound.SoundMainActivity;
import com.tricheer.launcherg.activity.system.SystemMainActivity;
import com.tricheer.launcherg.activity.voicemail.VoicemailMainActivity;
import com.tricheer.launcherg.adapter.MenuFragItemAdapter;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.model.MenuFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu Fragment
 *
 * @author Jun.Wang
 */
public class MenuFragment extends BaseFragment {
    // TAG
    private static final String TAG = "MenuFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private MenuActivity mAttachedActivity;
    private MenuFragItemAdapter mLvItemAdapter;

    //Initial selected position of ListView
    private int mInitPos = 0;

    /**
     * Set initial selected position of ListView
     */
    public void setInitPos(int initPos) {
        mInitPos = initPos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (MenuActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_menu, null);
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
        List<MenuFragItem> listItems = new ArrayList<MenuFragItem>();

        MenuFragItem item01 = new MenuFragItem();
        item01.name = mAttachedActivity.getString(R.string.contacts);
        item01.cls = ContactsMainActivity.class;
        listItems.add(item01);

        MenuFragItem item02 = new MenuFragItem();
        item02.name = mAttachedActivity.getString(R.string.voicemail);
        item02.cls = VoicemailMainActivity.class;
        listItems.add(item02);

        MenuFragItem item03 = new MenuFragItem();
        item03.name = mAttachedActivity.getString(R.string.phone);
        item03.cls = PhoneMainActivity.class;
        listItems.add(item03);

        MenuFragItem item04 = new MenuFragItem();
        item04.name = mAttachedActivity.getString(R.string.security);
        item04.cls = SecurityMainActivity.class;
        listItems.add(item04);

        MenuFragItem item05 = new MenuFragItem();
        item05.name = mAttachedActivity.getString(R.string.network);
        item05.cls = NetworkMainActivity.class;
        listItems.add(item05);

        MenuFragItem item06 = new MenuFragItem();
        item06.name = mAttachedActivity.getString(R.string.sound);
        item06.cls = SoundMainActivity.class;
        listItems.add(item06);

        MenuFragItem item07 = new MenuFragItem();
        item07.name = mAttachedActivity.getString(R.string.display);
        item07.cls = DisplayMainActivity.class;
        listItems.add(item07);

        MenuFragItem item08 = new MenuFragItem();
        item08.name = mAttachedActivity.getString(R.string.date_time);
        item08.cls = DateNTimeMainActivity.class;
        listItems.add(item08);

        MenuFragItem item09 = new MenuFragItem();
        item09.name = mAttachedActivity.getString(R.string.system);
        item09.cls = SystemMainActivity.class;
        listItems.add(item09);

        MenuFragItem item10 = new MenuFragItem();
        item10.name = mAttachedActivity.getString(R.string.profile);
        item10.cls = ProfileMainActivity.class;
        listItems.add(item10);

        // Load ListView
        mLvItemAdapter = new MenuFragItemAdapter(mAttachedActivity, 0);
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
            case KeypadManager.NUM6:
                loadPageByIdx(5);
                break;
            case KeypadManager.NUM7:
                loadPageByIdx(6);
                break;
            case KeypadManager.NUM8:
                loadPageByIdx(7);
                break;
            case KeypadManager.NUM9:
                loadPageByIdx(8);
                break;
            case KeypadManager.NUM0:
                loadPageByIdx(9);
                break;
        }
    }

    public void loadPageByIdx(int pos) {
        //If loading position is not equals selected position
        if (pos != mLvItemAdapter.getSelectPos()) {
            mLvItemAdapter.selectPos(pos);
        }

        //Load selected page
        MenuFragItem item = mLvItemAdapter.getItem(pos);
        if (item != null) {
            startActivity(new Intent(mAttachedActivity, item.cls));
        }
    }

    /**
     * Get current selected position of {@link ListView}
     *
     * @return int
     */
    public int getCurrSelectedPos() {
        return mLvItemAdapter.getSelectPos();
    }
}
package com.tricheer.launcherg.frags.sound;

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
import com.tricheer.launcherg.activity.sound.IncallVolActivity;
import com.tricheer.launcherg.activity.sound.KeytouchToneActivity;
import com.tricheer.launcherg.activity.sound.RingTonesActivity;
import com.tricheer.launcherg.activity.sound.RingtoneVolActivity;
import com.tricheer.launcherg.activity.sound.SoundMainActivity;
import com.tricheer.launcherg.activity.sound.SpeakerVolActivity;
import com.tricheer.launcherg.adapter.sound.SoundMainFragItemAdapter;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.sound.SoundMainFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * [Menu-Sound] main page Fragment
 *
 * @author Jun.Wang
 */
public class SoundMainFragment extends BaseFragment {
    // TAG
    private static final String TAG = "SoundMainFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private SoundMainActivity mAttachedActivity;
    private SoundMainFragItemAdapter mLvItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (SoundMainActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_sound_main, null);
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
        List<SoundMainFragItem> listItems = new ArrayList<SoundMainFragItem>();

        SoundMainFragItem item01 = new SoundMainFragItem();
        item01.name = mAttachedActivity.getString(R.string.sound_ringtones);
        item01.cls = RingTonesActivity.class;
        listItems.add(item01);

        SoundMainFragItem item02 = new SoundMainFragItem();
        item02.name = mAttachedActivity.getString(R.string.sound_ringtone_vol);
        item02.cls = RingtoneVolActivity.class;
        listItems.add(item02);

        SoundMainFragItem item03 = new SoundMainFragItem();
        item03.name = mAttachedActivity.getString(R.string.sound_incall_vol);
        item03.cls = IncallVolActivity.class;
        listItems.add(item03);

        SoundMainFragItem item04 = new SoundMainFragItem();
        item04.name = mAttachedActivity.getString(R.string.sound_speaker_vol);
        item04.cls = SpeakerVolActivity.class;
        listItems.add(item04);

        SoundMainFragItem item05 = new SoundMainFragItem();
        item05.name = mAttachedActivity.getString(R.string.sound_keytouch_tone);
        item05.cls = KeytouchToneActivity.class;
        listItems.add(item05);

        // Load ListView
        mLvItemAdapter = new SoundMainFragItemAdapter(mAttachedActivity, 0);
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
        SoundMainFragItem item = mLvItemAdapter.getItem(pos);
        if (item != null) {
            startActivity(new Intent(mAttachedActivity, item.cls));
        }
    }
}
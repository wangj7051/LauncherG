package com.tricheer.launcherg.frags.display;

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
import com.tricheer.launcherg.activity.display.DisplayMainActivity;
import com.tricheer.launcherg.activity.display.FontSizeActivity;
import com.tricheer.launcherg.activity.display.LanguageActivity;
import com.tricheer.launcherg.activity.display.OnScreenIDActivity;
import com.tricheer.launcherg.activity.display.ScreenTimeoutActivity;
import com.tricheer.launcherg.activity.display.WallpaperActivity;
import com.tricheer.launcherg.adapter.display.DisplayMainFragItemAdapter;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.display.DisplayMainFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Display main page Fragment
 *
 * @author Jun.Wang
 */
public class DisplayMainFragment extends BaseFragment {
    // TAG
    private static final String TAG = "DisplayMainFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private DisplayMainActivity mAttachedActivity;
    private DisplayMainFragItemAdapter mLvItemAdapter;


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
        mAttachedActivity = (DisplayMainActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_display_main, null);
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
        List<DisplayMainFragItem> listItems = new ArrayList<DisplayMainFragItem>();

        DisplayMainFragItem item01 = new DisplayMainFragItem();
        item01.name = mAttachedActivity.getString(R.string.screen_timeout);
        item01.cls = ScreenTimeoutActivity.class;
        listItems.add(item01);

        DisplayMainFragItem item02 = new DisplayMainFragItem();
        item02.name = mAttachedActivity.getString(R.string.wallpaper);
        item02.cls = WallpaperActivity.class;
        listItems.add(item02);

        DisplayMainFragItem item03 = new DisplayMainFragItem();
        item03.name = mAttachedActivity.getString(R.string.font_size);
        item03.cls = FontSizeActivity.class;
        listItems.add(item03);

        DisplayMainFragItem item04 = new DisplayMainFragItem();
        item04.name = mAttachedActivity.getString(R.string.language);
        item04.cls = LanguageActivity.class;
        listItems.add(item04);

        DisplayMainFragItem item05 = new DisplayMainFragItem();
        item05.name = mAttachedActivity.getString(R.string.on_screen_id);
        item05.cls = OnScreenIDActivity.class;
        listItems.add(item05);

        // Load ListView
        mLvItemAdapter = new DisplayMainFragItemAdapter(mAttachedActivity, 0);
        mLvItemAdapter.setListItems(listItems);
        mLvItemAdapter.setSelectPos(mInitPos);
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
        DisplayMainFragItem item = mLvItemAdapter.getItem(pos);
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
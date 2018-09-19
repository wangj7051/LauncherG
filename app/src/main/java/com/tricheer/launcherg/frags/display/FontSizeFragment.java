package com.tricheer.launcherg.frags.display;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.display.FontSizeActivity;
import com.tricheer.launcherg.adapter.display.FontSizeFragItemAdapter;
import com.tricheer.launcherg.engine.AppStackManager;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.engine.SysFontManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.display.FontSizeFragItem;
import com.tricheer.launcherg.engine.PreferUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * [Menu-Display-Font size] Fragment
 *
 * @author Jun.Wang
 */
public class FontSizeFragment extends BaseFragment {
    // TAG
    private static final String TAG = "FontSizeFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private FontSizeActivity mAttachedActivity;
    private FontSizeFragItemAdapter mLvItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (FontSizeActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_display_fontsize, null);
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
        List<FontSizeFragItem> listItems = new ArrayList<FontSizeFragItem>();

        FontSizeFragItem item01 = new FontSizeFragItem();
        item01.name = mAttachedActivity.getString(R.string.large);
        item01.setTag(SysFontManager.LARGE);
        listItems.add(item01);

        FontSizeFragItem item02 = new FontSizeFragItem();
        item02.name = mAttachedActivity.getString(R.string.middle);
        item02.setTag(SysFontManager.MIDDLE);
        listItems.add(item02);

        FontSizeFragItem item03 = new FontSizeFragItem();
        item03.name = mAttachedActivity.getString(R.string.small);
        item03.setTag(SysFontManager.SMALL);
        listItems.add(item03);

        FontSizeFragItem item04 = new FontSizeFragItem();
        item04.name = mAttachedActivity.getString(R.string.very_small);
        item04.setTag(SysFontManager.VERY_SMALL);
        listItems.add(item04);

        // Load ListView
        mLvItemAdapter = new FontSizeFragItemAdapter(mAttachedActivity, 0);
        mLvItemAdapter.setListItems(listItems);
        mLvItemAdapter.setSelectPos(getSelectPosByTag());
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
                setFontSize();
                break;
        }
    }

    private void setFontSize() {
        int selectPos = mLvItemAdapter.getSelectPos();
        FontSizeFragItem item = mLvItemAdapter.getItem(selectPos);
        String storedFontSize = PreferUtils.getCurrFontSize(false, "");
        if (!TextUtils.equals(storedFontSize, item.getTag())) {
            PreferUtils.getCurrFontSize(true, item.getTag());
            //Notify all the page that need refresh.
            AppStackManager.notifyFontSizeChanged();
        }
    }

    /**
     * Get the select position of ListView when this activity initializing.
     */
    private int getSelectPosByTag() {
        String storedVal = PreferUtils.getCurrFontSize(false, "");
        if (SysFontManager.LARGE.equals(storedVal)) {
            return 0;
        } else if (SysFontManager.MIDDLE.equals(storedVal)) {
            return 1;
        } else if (SysFontManager.SMALL.equals(storedVal)) {
            return 2;
        } else if (SysFontManager.VERY_SMALL.equals(storedVal)) {
            return 3;
        }
        return 1;
    }
}
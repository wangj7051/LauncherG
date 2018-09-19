package com.tricheer.launcherg.frags.display;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.display.LanguageActivity;
import com.tricheer.launcherg.adapter.display.LanguageFragItemAdapter;
import com.tricheer.launcherg.engine.AppStackManager;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.engine.LanguageManager;
import com.tricheer.launcherg.frags.BaseFragment;
import com.tricheer.launcherg.model.display.LanguageFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * [Menu-Display-Screen timeout] Fragment
 *
 * @author Jun.Wang
 */
public class LanguageFragment extends BaseFragment {
    // TAG
    private static final String TAG = "LanguageFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private LanguageActivity mAttachedActivity;
    private LanguageFragItemAdapter mLvItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (LanguageActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_display_language, null);
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
        List<LanguageFragItem> listItems = new ArrayList<LanguageFragItem>();

        LanguageFragItem item01 = new LanguageFragItem();
        item01.name = mAttachedActivity.getString(R.string.language_ja);
        item01.setTag(LanguageManager.LANG_JA);
        listItems.add(item01);

        LanguageFragItem item02 = new LanguageFragItem();
        item02.name = mAttachedActivity.getString(R.string.language_en);
        item02.setTag(LanguageManager.LANG_EN);
        listItems.add(item02);

        // Load ListView
        mLvItemAdapter = new LanguageFragItemAdapter(mAttachedActivity, 0);
        mLvItemAdapter.setListItems(listItems);
        mLvItemAdapter.setSelectPos(getSelectedPosByTag());
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
        }
    }

    public void loadPageByIdx(int pos) {
        //If loading position is not equals selected position
        if (pos != mLvItemAdapter.getSelectPos()) {
            mLvItemAdapter.selectPos(pos);
        }

        //Load selected page
        LanguageFragItem item = mLvItemAdapter.getItem(pos);
        if (item != null) {
            if (LanguageManager.isLangChanged(mAttachedActivity, item.getTag())) {
                LanguageManager.updateSysLang(item.getTag());
                AppStackManager.notifyLanguageChanged();
            }
        }
    }

    /**
     * Get the select position of ListView when this activity initializing.
     */
    private int getSelectedPosByTag() {
        String currLang = LanguageManager.getCurrLang(mAttachedActivity);
        if (LanguageManager.LANG_JA.equals(currLang)) {
            return 0;
        } else if (LanguageManager.LANG_EN.equals(currLang)) {
            return 1;
        }
        return 0;
    }
}
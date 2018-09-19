package com.tricheer.launcherg.frags.sound;

import android.annotation.SuppressLint;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.activity.sound.RingTonesActivity;
import com.tricheer.launcherg.adapter.sound.RingTonesFragItemAdapter;
import com.tricheer.launcherg.engine.AppStackManager;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.engine.SysRingToneManager;
import com.tricheer.launcherg.frags.BaseFragment;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * [Menu-Sound-Ringtones] Fragment
 *
 * @author Jun.Wang
 */
public class RingTonesFragment extends BaseFragment {
    // TAG
    private static final String TAG = "RingTonesFragment";

    // Widgets
    private View fragV;
    private ListView lvContent;

    //Variables
    private RingTonesActivity mAttachedActivity;
    private RingTonesFragItemAdapter mLvItemAdapter;
    private LoadRingTonesTask mLoadLvTask;

    //
    private SysRingToneManager mSysRingToneManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttachedActivity = (RingTonesActivity) getActivity();
        mSysRingToneManager = new SysRingToneManager(mAttachedActivity, RingtoneManager.TYPE_RINGTONE);
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragV = inflater.inflate(R.layout.frag_sound_ringtones, null);
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
        cancelLoadRingTonesTask();
        mLoadLvTask = new LoadRingTonesTask(this);
        mLoadLvTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void onDataReady(List<Ringtone> listItems) {
        mLvItemAdapter = new RingTonesFragItemAdapter(mAttachedActivity, 0);
        mLvItemAdapter.setListItems(listItems);
        mLvItemAdapter.setSelectPos(mSysRingToneManager.getCurrRingtongPos());
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
                playSelected();
                break;
            case KeypadManager.DOWN:
                mLvItemAdapter.selectNext();
                playSelected();
                break;
            case KeypadManager.NUM1:
                mLvItemAdapter.selectPos(0);
                setSysRingTone();
                break;
            case KeypadManager.NUM2:
                mLvItemAdapter.selectPos(1);
                setSysRingTone();
                break;
            case KeypadManager.NUM3:
                mLvItemAdapter.selectPos(2);
                setSysRingTone();
                break;
            case KeypadManager.NUM4:
                mLvItemAdapter.selectPos(3);
                setSysRingTone();
                break;
            case KeypadManager.NUM5:
                mLvItemAdapter.selectPos(4);
                setSysRingTone();
                break;
            case KeypadManager.NUM6:
                mLvItemAdapter.selectPos(5);
                setSysRingTone();
                break;
            case KeypadManager.NUM7:
                mLvItemAdapter.selectPos(6);
                setSysRingTone();
                break;
            case KeypadManager.NUM8:
                mLvItemAdapter.selectPos(7);
                setSysRingTone();
                break;
            case KeypadManager.NUM9:
                mLvItemAdapter.selectPos(8);
                setSysRingTone();
                break;
            case KeypadManager.FUNC:
                setSysRingTone();
                break;
        }
    }

    private void playSelected() {
        try {
            int pos = mLvItemAdapter.getSelectPos();
            mSysRingToneManager.play(mLvItemAdapter.getItem(pos));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSysRingTone() {
        try {
            int currRingtonePos = mSysRingToneManager.getCurrRingtongPos();
            int newRingtonePos = mLvItemAdapter.getSelectPos();
            if (newRingtonePos != currRingtonePos) {
                mSysRingToneManager.setCurrRingtone(mLvItemAdapter.getSelectPos());
                AppStackManager.notifySysRingtoneChanged(mSysRingToneManager.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        cancelLoadRingTonesTask();
        super.onDestroy();
    }

    private void cancelLoadRingTonesTask() {
        if (mLoadLvTask != null) {
            mLoadLvTask.cancel(true);
            mLoadLvTask = null;
        }
    }

    /**
     * Load system ring tones task.
     */
    private static final class LoadRingTonesTask extends AsyncTask<Void, Void, List<Ringtone>> {

        private WeakReference<RingTonesFragment> mmWeakDelegate;

        LoadRingTonesTask(RingTonesFragment frag) {
            mmWeakDelegate = new WeakReference<RingTonesFragment>(frag);
        }

        @Override
        protected List<Ringtone> doInBackground(Void... params) {
            SysRingToneManager manager = mmWeakDelegate.get().mSysRingToneManager;
            return manager.queryAllRingTones();
        }

        @Override
        protected void onPostExecute(List<Ringtone> listItems) {
            super.onPostExecute(listItems);
            if (!isCancelled()) {
                try {
                    mmWeakDelegate.get().onDataReady(listItems);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
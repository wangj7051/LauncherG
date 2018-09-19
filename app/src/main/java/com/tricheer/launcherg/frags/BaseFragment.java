package com.tricheer.launcherg.frags;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.tricheer.launcherg.engine.KeypadManager;

/**
 * Base Fragment
 */
public abstract class BaseFragment extends Fragment implements KeypadManager.KeyDelegate {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

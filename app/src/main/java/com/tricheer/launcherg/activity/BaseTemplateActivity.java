package com.tricheer.launcherg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.utils.FragUtil;

/**
 * Base Template Activity
 * <p>1. Process Stack</p>
 */
public abstract class BaseTemplateActivity extends BaseFragActivity {

    // Widgets
    private TextView tvTitle, tvTips;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {
        //Widgets
        tvTitle = findViewById(R.id.tv_template_title);
        tvTips = findViewById(R.id.tv_template_tips);
    }

    protected abstract void loadPage();

    protected void setPageTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    protected void setPageTips(String tips) {
        if (tvTips != null) {
            tvTips.setText(tips);
        }
    }

    protected void loadV4Fragment(android.support.v4.app.Fragment frag) {
        FragUtil.loadV4Fragment(R.id.layout_frag_content, frag, getSupportFragmentManager());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

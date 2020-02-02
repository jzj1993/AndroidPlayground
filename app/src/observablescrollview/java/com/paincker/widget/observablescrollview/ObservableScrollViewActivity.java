package com.paincker.widget.observablescrollview;

import android.os.Bundle;

import com.paincker.R;
import com.paincker.common.BaseActivity;
import com.paincker.common.utils.log.L;
import com.paincker.common.utils.ui.ToastUtils;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 支持滑动状态监听的ScrollView
 */
public class ObservableScrollViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_scroll_view);
        ((ObservableScrollView) findViewById(R.id.scrollView)).setOnScrollListener(new ObservableScrollView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(ObservableScrollView view, int scrollState) {
                L.i("ObservableScrollView", "state = " + scrollState);
                ToastUtils.show(mContext, "state: " + scrollState);
            }

            @Override
            public void onScroll(ObservableScrollView view, boolean isTouchScroll, int l, int t, int oldl, int oldt) {
                L.d("ObservableScrollView", "scroll, isTouchScroll = " + isTouchScroll);
            }
        });
    }
}

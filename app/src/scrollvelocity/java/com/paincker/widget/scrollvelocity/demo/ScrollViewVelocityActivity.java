package com.paincker.widget.scrollvelocity.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ScrollView;

import com.paincker.R;
import com.paincker.common.BaseActivity;
import com.paincker.common.demo.DemoViewCreator;
import com.paincker.common.utils.ui.ToastUtils;
import com.paincker.widget.scrollvelocity.ViewVelocityHandler;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class ScrollViewVelocityActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_velocity_scroll);

        ScrollView scroll = findViewById(R.id.scroll);
        DemoViewCreator.addLongTextContent(scroll, DemoViewCreator.VERY_LONG_TEXT);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            ViewVelocityHandler handler = new ViewVelocityHandler(this);
            handler.setVelocityTrackerListener(new DemoListener(this));

            scroll.setOnScrollChangeListener(handler);
        } else {
            ToastUtils.show(this, "Android version not supported");
        }
    }
}

package com.paincker;

import android.os.Bundle;

import com.paincker.common.BaseActivity;
import com.paincker.common.demo.DemoViewCreator;
import com.paincker.widget.roundcornerlayout.RoundCornerLayoutActivity;
import com.paincker.widget.observablescrollview.ObservableScrollViewActivity;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(DemoViewCreator.createActivityEntryView(this, new Class[]{
                ObservableScrollViewActivity.class,
                RoundCornerLayoutActivity.class,
        }));
    }
}

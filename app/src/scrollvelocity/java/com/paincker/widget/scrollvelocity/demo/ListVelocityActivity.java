package com.paincker.widget.scrollvelocity.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.paincker.R;
import com.paincker.common.BaseActivity;
import com.paincker.common.demo.DemoViewCreator;
import com.paincker.widget.scrollvelocity.ListVelocityHandler;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class ListVelocityActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_velocity_list);

        ListVelocityHandler handler = new ListVelocityHandler(this);
        handler.setVelocityTrackerListener(new DemoListener(this));

        ListView listView = findViewById(R.id.list);
        DemoViewCreator.toTextListView(listView, 200);
        listView.setOnScrollListener(handler);
    }
}

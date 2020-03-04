package com.paincker.widget.scrollvelocity.demo;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.paincker.R;
import com.paincker.common.BaseActivity;
import com.paincker.common.demo.DemoViewCreator;
import com.paincker.widget.scrollvelocity.RecyclerVelocityHandler;

public class RecyclerVelocityActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_velocity_recycler);

        RecyclerVelocityHandler handler = new RecyclerVelocityHandler(this);
        handler.setVelocityTrackerListener(new DemoListener(this));

        RecyclerView recyclerView = findViewById(R.id.recycler);
        DemoViewCreator.toTextRecyclerView(recyclerView, 200);
        recyclerView.addOnScrollListener(handler);
    }
}

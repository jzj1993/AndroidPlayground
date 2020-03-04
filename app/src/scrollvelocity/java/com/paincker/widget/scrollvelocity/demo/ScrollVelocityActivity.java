package com.paincker.widget.scrollvelocity.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.paincker.common.demo.DemoViewCreator;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class ScrollVelocityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(DemoViewCreator.createActivityEntryView(this, new Class[]{
                RecyclerVelocityActivity.class,
                ListVelocityActivity.class,
                ScrollViewVelocityActivity.class,
        }));
    }
}

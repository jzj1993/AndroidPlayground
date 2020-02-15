package com.paincker.common.demo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.paincker.common.demo.DemoViewCreator;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 *
 */
public class DemoRecyclerView extends RecyclerView {

    public DemoRecyclerView(Context context) {
        super(context);
        DemoViewCreator.toTextRecyclerView(this);
    }

    public DemoRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DemoViewCreator.toTextRecyclerView(this);
    }

    public DemoRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        DemoViewCreator.toTextRecyclerView(this);
    }
}

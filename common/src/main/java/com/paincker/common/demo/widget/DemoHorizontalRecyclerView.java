package com.paincker.common.demo.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paincker.common.demo.widget.adapter.DemoRecyclerAdapter;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 *
 */
public class DemoHorizontalRecyclerView extends RecyclerView {

    public DemoHorizontalRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public DemoHorizontalRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DemoHorizontalRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        setAdapter(new DemoRecyclerAdapter().setHorizontal(true).addTextItem(context, 30));
    }
}

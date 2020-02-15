package com.paincker.common.demo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ListView;

import com.paincker.common.demo.widget.adapter.DemoListAdapter;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 *
 */
public class DemoListView extends ListView {

    public DemoListView(Context context) {
        super(context);
        init(context);
    }

    public DemoListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DemoListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setAdapter(new DemoListAdapter().addTextItem(context, 30));
    }
}

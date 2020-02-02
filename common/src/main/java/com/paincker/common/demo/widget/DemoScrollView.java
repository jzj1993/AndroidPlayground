package com.paincker.common.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import com.paincker.common.demo.DemoViewCreator;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 *
 */
public class DemoScrollView extends ScrollView {

    public DemoScrollView(Context context) {
        super(context);
        DemoViewCreator.addLongTextContent(this);
    }

    public DemoScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DemoViewCreator.addLongTextContent(this);
    }

    public DemoScrollView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        DemoViewCreator.addLongTextContent(this);
    }
}

package com.paincker.common.demo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.paincker.common.demo.DemoViewCreator;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

@SuppressLint("AppCompatCustomView")
public class DemoLongTextView extends TextView {

    public DemoLongTextView(Context context) {
        super(context);
        init();
    }

    public DemoLongTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DemoLongTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DemoLongTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        DemoViewCreator.toLongText(this);
    }
}

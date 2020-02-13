package com.paincker.widget.labelview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.paincker.R;


/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 支持边框、颜色填充、圆角的LinearLayout
 * Created by jzj on 2020/2/13.
 */
public class LabelLinearLayout extends LinearLayout {

    private final LabelConfig mConfiguration = new LabelConfig() {
        @Override
        public void apply() {
            if (isBackgroundDirty()) {
                LabelLinearLayout.this.setBackgroundDrawable(buildBackgroundDrawable());
            }
        }
    };

    public LabelLinearLayout(Context context) {
        super(context);
    }

    public LabelLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LabelLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LabelLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        @SuppressLint("CustomViewStyleable")
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        mConfiguration.initBackgroundAttributes(array);
        mConfiguration.apply();
        array.recycle();
    }

    public LabelConfig getConfiguration() {
        return mConfiguration;
    }
}

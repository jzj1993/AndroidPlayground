package com.paincker.widget.labelview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.paincker.R;


/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * Created by jzj on 2020/2/13.
 */
@SuppressLint("AppCompatCustomView")
public class LabelImageView extends ImageView {

    private final LabelConfig mConfiguration = new LabelConfig() {
        @Override
        public void apply() {
            if (isBackgroundDirty()) {
                LabelImageView.this.setBackgroundDrawable(buildBackgroundDrawable());
            }
            if (isIconDirty()) {
                LabelImageView.this.setImageDrawable(buildIconDrawable());
            }
        }
    };

    public LabelImageView(Context context) {
        super(context);
    }

    public LabelImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LabelImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LabelImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        @SuppressLint("CustomViewStyleable")
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        mConfiguration.initBackgroundAttributes(array);
        mConfiguration.initIconAttributes(array);
        mConfiguration.apply();
        array.recycle();
    }

    public LabelConfig getConfiguration() {
        return mConfiguration;
    }
}

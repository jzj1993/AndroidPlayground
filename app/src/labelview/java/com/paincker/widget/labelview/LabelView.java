package com.paincker.widget.labelview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.paincker.R;


/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 支持边框、颜色填充、圆角、图标的TextView
 * Created by jzj on 2020/2/13.
 */
@SuppressLint("AppCompatCustomView")
public class LabelView extends TextView {

    private final LabelConfig mConfiguration = new LabelConfig() {
        @Override
        public void apply() {
            if (isBackgroundDirty()) {
                LabelView.this.setBackgroundDrawable(buildBackgroundDrawable());
            }
            if (isTextColorDirty()) {
                LabelView.this.setTextColor(buildTextColor());
            }
            if (isIconDirty()) {
                LabelView.this.setIconLeft(buildIconDrawable());
            }
        }
    };

    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LabelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LabelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        mConfiguration.initBackgroundAttributes(array);
        mConfiguration.initTextColorAttributes(array);
        mConfiguration.initIconAttributes(array);
        mConfiguration.apply();
        array.recycle();
    }

    private void setIconLeft(Drawable drawableLeft) {
        Drawable[] drawables = getCompoundDrawables();
        //noinspection ConstantConditions
        if (drawables == null || drawables.length != 4) {
            setCompoundDrawables(drawableLeft, null, null, null);
        } else {
            setCompoundDrawables(drawableLeft, drawables[1], drawables[2], drawables[3]);
        }
    }

    public LabelConfig getConfiguration() {
        return mConfiguration;
    }
}

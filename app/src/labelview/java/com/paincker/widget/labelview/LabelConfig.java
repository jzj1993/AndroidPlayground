package com.paincker.widget.labelview;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import androidx.annotation.Nullable;

import com.paincker.R;

import java.util.ArrayList;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * LabelView，支持圆角、边框、背景填充色、文字颜色、图标属性的设置，
 * 且可为normal、selected、pressed、disabled状态分别设置
 */
public abstract class LabelConfig {

    /**
     * 背景填充颜色
     */
    private int mSolidColorNormal;
    private Integer mSolidColorSelected;
    private Integer mSolidColorPressed;
    private Integer mSolidColorDisabled;
    /**
     * 边框颜色
     */
    private int mBorderColorNormal;
    private Integer mBorderColorSelected;
    private Integer mBorderColorPressed;
    private Integer mBorderColorDisabled;
    /**
     * 边框宽度
     */
    private int mBorderWidth;
    /**
     * 圆角
     */
    private float mRadiusTopLeft;
    private float mRadiusTopRight;
    private float mRadiusBottomRight;
    private float mRadiusBottomLeft;

    private boolean mBackgroundDirty = true;

    /**
     * 文本颜色
     */
    private int mTextColorNormal;
    private Integer mTextColorSelected;
    private Integer mTextColorPressed;
    private Integer mTextColorDisabled;

    private boolean mTextColorDirty = true;

    /**
     * 图标
     */
    private Drawable mIconNormal;
    private Drawable mIconSelected;
    private Drawable mIconPressed;
    private Drawable mIconDisabled;

    private boolean mIconDirty = true;

    @Nullable
    private static Integer getColor(TypedArray array, int index) {
        return array.hasValue(index) ? array.getColor(index, Color.TRANSPARENT) : null;
    }

    private static Drawable buildStateDrawable(Drawable n, Drawable s, Drawable p, Drawable d) {
        if (n == null || (d == null && p == null && s == null)) {
            return n;
        }
        StateListDrawable state = new StateListDrawable();
        if (d != null) {
            state.addState(new int[]{-android.R.attr.state_enabled}, d);
        }
        if (p != null) {
            state.addState(new int[]{android.R.attr.state_pressed}, p);
        }
        if (s != null) {
            state.addState(new int[]{android.R.attr.state_selected}, s);
        }
        state.addState(new int[]{}, n);
        state.setBounds(0, 0, state.getMinimumWidth(), state.getMinimumHeight());
        return state;
    }

    private static ColorStateList buildColorStateList(int n, Integer s, Integer p, Integer d) {
//        if (s == null && p == null && d == null) {
//            return null;
//        }
        ArrayList<int[]> states = new ArrayList<>(4);
        ArrayList<Integer> colors = new ArrayList<>(4);
        if (d != null) {
            states.add(new int[]{-android.R.attr.state_enabled});
            colors.add(d);
        }
        if (p != null) {
            states.add(new int[]{android.R.attr.state_pressed});
            colors.add(p);
        }
        if (s != null) {
            states.add(new int[]{android.R.attr.state_selected});
            colors.add(s);
        }
        states.add(new int[]{});
        colors.add(n);
        int[] array = new int[colors.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = colors.get(i);
        }
        return new ColorStateList(states.toArray(new int[0][0]), array);
    }

    public void initBackgroundAttributes(TypedArray array) {
        mSolidColorNormal = array.getColor(R.styleable.LabelView_solidColorNormal, Color.TRANSPARENT);
        mSolidColorSelected = getColor(array, R.styleable.LabelView_solidColorSelected);
        mSolidColorPressed = getColor(array, R.styleable.LabelView_solidColorPressed);
        mSolidColorDisabled = getColor(array, R.styleable.LabelView_solidColorDisabled);

        mBorderColorNormal = array.getColor(R.styleable.LabelView_borderColorNormal, Color.TRANSPARENT);
        mBorderColorSelected = getColor(array, R.styleable.LabelView_borderColorSelected);
        mBorderColorPressed = getColor(array, R.styleable.LabelView_borderColorPressed);
        mBorderColorDisabled = getColor(array, R.styleable.LabelView_borderColorDisabled);

        mBorderWidth = array.getDimensionPixelSize(R.styleable.LabelView_borderWidth, 0);

        int radius = array.getDimensionPixelSize(R.styleable.LabelView_radiusDefault, 0);
        mRadiusTopLeft = array.getDimensionPixelSize(R.styleable.LabelView_radiusTopLeft, radius);
        mRadiusTopRight = array.getDimensionPixelSize(R.styleable.LabelView_radiusTopRight, radius);
        mRadiusBottomRight = array.getDimensionPixelSize(R.styleable.LabelView_radiusBottomRight, radius);
        mRadiusBottomLeft = array.getDimensionPixelSize(R.styleable.LabelView_radiusBottomLeft, radius);
    }

    public void initTextColorAttributes(TypedArray array) {
        mTextColorNormal = array.getColor(R.styleable.LabelView_textColorNormal, Color.TRANSPARENT);
        mTextColorSelected = getColor(array, R.styleable.LabelView_textColorSelected);
        mTextColorPressed = getColor(array, R.styleable.LabelView_textColorPressed);
        mTextColorDisabled = getColor(array, R.styleable.LabelView_textColorDisabled);
    }

    public void initIconAttributes(TypedArray array) {
        mIconNormal = array.getDrawable(R.styleable.LabelView_iconNormal);
        mIconSelected = array.getDrawable(R.styleable.LabelView_iconSelected);
        mIconPressed = array.getDrawable(R.styleable.LabelView_iconPressed);
        mIconDisabled = array.getDrawable(R.styleable.LabelView_iconDisabled);
    }

    public LabelConfig setBorderColorNormal(int borderColorNormal) {
        mBorderColorNormal = borderColorNormal;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setBorderColorSelected(Integer borderColorSelected) {
        mBorderColorSelected = borderColorSelected;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setBorderColorPressed(Integer borderColorPressed) {
        mBorderColorPressed = borderColorPressed;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setBorderColorDisabled(Integer borderColorDisabled) {
        mBorderColorDisabled = borderColorDisabled;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setSolidColorNormal(int solidColorNormal) {
        mSolidColorNormal = solidColorNormal;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setSolidColorSelected(Integer solidColorSelected) {
        mSolidColorSelected = solidColorSelected;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setSolidColorPressed(Integer solidColorPressed) {
        mSolidColorPressed = solidColorPressed;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setSolidColorDisabled(Integer solidColorDisabled) {
        mSolidColorDisabled = solidColorDisabled;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setBorderWidth(int borderWidth) {
        mBorderWidth = borderWidth;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setRadiusTopLeft(float radiusTopLeft) {
        mRadiusTopLeft = radiusTopLeft;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setRadiusTopRight(float radiusTopRight) {
        mRadiusTopRight = radiusTopRight;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setRadiusBottomLeft(float radiusBottomLeft) {
        mRadiusBottomLeft = radiusBottomLeft;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setRadiusBottomRight(float radiusBottomRight) {
        mRadiusBottomRight = radiusBottomRight;
        mBackgroundDirty = true;
        return this;
    }

    public LabelConfig setRadius(float radius) {
        mRadiusTopLeft = mRadiusTopRight = mRadiusBottomLeft = mRadiusBottomRight = radius;
        mBackgroundDirty = true;
        return this;
    }

    public boolean isBackgroundDirty() {
        return mBackgroundDirty;
    }

    /**
     * 创建背景Drawable，实现圆角、边框、填充
     */
    public Drawable buildBackgroundDrawable() {
        mBackgroundDirty = false;
        Drawable n = buildBackgroundStateDrawable(mBorderColorNormal, mSolidColorNormal);
        if (n == null) {
            return null;
        }
        Drawable d = buildBackgroundStateDrawable(mBorderColorDisabled, mSolidColorDisabled);
        Drawable p = buildBackgroundStateDrawable(mBorderColorPressed, mSolidColorPressed);
        Drawable s = buildBackgroundStateDrawable(mBorderColorSelected, mSolidColorSelected);
        return buildStateDrawable(n, s, p, d);
    }

    private Drawable buildBackgroundStateDrawable(Integer borderColor, Integer solidColor) {
        if (borderColor == null && solidColor == null) {
            return null;
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        if (mBorderWidth > 0 && borderColor != null) {
            drawable.setStroke(mBorderWidth, borderColor);
        }
        if (solidColor != null) {
            drawable.setColor(solidColor);
        }
        drawable.setCornerRadii(new float[]{
                mRadiusTopLeft, mRadiusTopLeft, mRadiusTopRight, mRadiusTopRight,
                mRadiusBottomRight, mRadiusBottomRight, mRadiusBottomLeft, mRadiusBottomLeft
        });
        return drawable;
    }

    public LabelConfig setTextColorNormal(int textColorNormal) {
        mTextColorNormal = textColorNormal;
        mTextColorDirty = true;
        return this;
    }

    public LabelConfig setTextColorSelected(int textColorSelected) {
        mTextColorSelected = textColorSelected;
        mTextColorDirty = true;
        return this;
    }

    public LabelConfig setTextColorPressed(int textColorPressed) {
        mTextColorPressed = textColorPressed;
        mTextColorDirty = true;
        return this;
    }

    public LabelConfig setTextColorDisabled(int textColorDisabled) {
        mTextColorDisabled = textColorDisabled;
        mTextColorDirty = true;
        return this;
    }

    public LabelConfig setTextColor(int color) {
        mTextColorNormal = mTextColorSelected = mTextColorPressed = mTextColorDisabled = color;
        mTextColorDirty = true;
        return this;
    }

    public boolean isTextColorDirty() {
        return mTextColorDirty;
    }

    public ColorStateList buildTextColor() {
        mTextColorDirty = false;
        return buildColorStateList(mTextColorNormal, mTextColorSelected, mTextColorPressed, mTextColorDisabled);
    }

    public LabelConfig setIconNormal(Drawable iconNormal) {
        mIconNormal = iconNormal;
        mIconDirty = true;
        return this;
    }

    public LabelConfig setIconSelected(Drawable iconSelected) {
        mIconSelected = iconSelected;
        mIconDirty = true;
        return this;
    }

    public LabelConfig setIconPressed(Drawable iconPressed) {
        mIconPressed = iconPressed;
        mIconDirty = true;
        return this;
    }

    public LabelConfig setIconDisabled(Drawable iconDisabled) {
        mIconDisabled = iconDisabled;
        mIconDirty = true;
        return this;
    }

    public boolean isIconDirty() {
        return mIconDirty;
    }

    public Drawable buildIconDrawable() {
        mIconDirty = false;
        return buildStateDrawable(mIconNormal, mIconSelected, mIconPressed, mIconDisabled);
    }

    public abstract void apply();
}

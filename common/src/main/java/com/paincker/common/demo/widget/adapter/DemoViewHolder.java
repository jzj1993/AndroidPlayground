package com.paincker.common.demo.widget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 *
 */
public abstract class DemoViewHolder<T> {

    protected final View mRoot;

    public DemoViewHolder(View root) {
        mRoot = root;
    }

    public DemoViewHolder(ViewGroup parent, int layout) {
        mRoot = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    public abstract void setData(T data, int position);
}

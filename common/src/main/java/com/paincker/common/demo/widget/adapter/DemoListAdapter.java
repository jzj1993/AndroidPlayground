package com.paincker.common.demo.widget.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paincker.common.demo.DemoViewCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 *
 */
public class DemoListAdapter extends BaseAdapter {

    private final ArrayList<View> mViews = new ArrayList<>();

    public DemoListAdapter() {
    }

    public DemoListAdapter(List<View> views) {
        addViews(views);
    }

    public DemoListAdapter(View... views) {
        addViews(views);
    }

    public static DemoListAdapter createTextListAdapter(Context context, int count) {
        return new DemoListAdapter().addTextItem(context, count);
    }

    public DemoListAdapter addTextItem(Context context, int count) {
        for (int i = 0; i < count; i++) {
            TextView textView = DemoViewCreator.bigText(context, String.valueOf(i));
            textView.setGravity(Gravity.CENTER);
            addView(textView);
        }
        return this;
    }

    public DemoListAdapter addView(View view) {
        mViews.add(view);
        return this;
    }

    public DemoListAdapter addViews(List<View> views) {
        mViews.addAll(views);
        return this;
    }

    public DemoListAdapter addViews(View... views) {
        Collections.addAll(mViews, views);
        return this;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mViews.get(position);
    }
}

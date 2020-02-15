package com.paincker.common.demo.widget.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
public class DemoPagerAdapter extends PagerAdapter {

    private final ArrayList<View> mViews = new ArrayList<>();

    public DemoPagerAdapter() {
    }

    public DemoPagerAdapter(List<View> views) {
        addViews(views);
    }

    public DemoPagerAdapter(View... views) {
        addViews(views);
    }

    public static DemoPagerAdapter createTextPagerAdapter(Context context, int page) {
        return new DemoPagerAdapter().addTextItem(context, page);
    }

    public DemoPagerAdapter addTextItem(Context context, int count) {
        for (int i = 0; i < count; i++) {
            TextView textView = DemoViewCreator.bigText(context, String.valueOf(i));
            textView.setGravity(Gravity.CENTER);
            addView(textView);
        }
        return this;
    }

    public DemoPagerAdapter addView(View view) {
        mViews.add(view);
        return this;
    }

    public DemoPagerAdapter addViews(List<View> views) {
        mViews.addAll(views);
        return this;
    }

    public DemoPagerAdapter addViews(View... views) {
        Collections.addAll(mViews, views);
        return this;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

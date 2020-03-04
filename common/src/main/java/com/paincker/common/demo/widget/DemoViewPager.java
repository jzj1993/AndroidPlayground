package com.paincker.common.demo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.paincker.common.demo.DemoViewCreator;
import com.paincker.common.demo.widget.adapter.DemoPagerAdapter;
import com.paincker.common.utils.ArrayUtil;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 *
 */
public class DemoViewPager extends ViewPager {

    private View[] mViews;

    public DemoViewPager(Context context) {
        super(context);
        init(context);
    }

    public DemoViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mViews = new View[]{
                DemoViewCreator.textRecyclerView(context),
                DemoViewCreator.textListView(context),
                DemoViewCreator.longTextScrollView(context)
        };
        setAdapter(new DemoPagerAdapter().addViews(mViews));
    }

    public View getViewItem(int index) {
        return ArrayUtil.getItem(mViews, index);
    }
}

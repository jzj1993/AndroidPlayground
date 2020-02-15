package com.paincker.common.demo.widget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
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
public class DemoRecyclerAdapter extends RecyclerView.Adapter<DemoRecyclerAdapter.TestRecyclerViewHolder> {

    private final ArrayList<View> mViews = new ArrayList<>();
    private boolean mHorizontal = false;

    public DemoRecyclerAdapter() {
    }

    public DemoRecyclerAdapter(List<View> views) {
        addViews(views);
    }

    public DemoRecyclerAdapter(View... views) {
        addViews(views);
    }

    public static DemoRecyclerAdapter createTextRecyclerAdapter(Context context, int count) {
        return new DemoRecyclerAdapter().addTextItem(context, count);
    }

    public boolean isHorizontal() {
        return mHorizontal;
    }

    public DemoRecyclerAdapter setHorizontal(boolean horizontal) {
        mHorizontal = horizontal;
        return this;
    }

    public DemoRecyclerAdapter addTextItem(Context context, int count) {
        for (int i = 0; i < count; i++) {
            TextView textView = DemoViewCreator.bigText(context, String.valueOf(i));
            textView.setGravity(Gravity.CENTER);
            addView(textView);
        }
        return this;
    }

    public DemoRecyclerAdapter addView(View view) {
        mViews.add(view);
        return this;
    }

    public DemoRecyclerAdapter addViews(List<View> views) {
        mViews.addAll(views);
        return this;
    }

    public DemoRecyclerAdapter addViews(View... views) {
        Collections.addAll(mViews, views);
        return this;
    }

    @Override
    public int getItemCount() {
        return mViews.size();
    }

    @Override
    public TestRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestRecyclerViewHolder(parent.getContext());
    }

    @Override
    public void onBindViewHolder(TestRecyclerViewHolder holder, int position) {
        holder.bindView(mViews.get(position));
    }

    public class TestRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final FrameLayout mFrameLayout;

        public TestRecyclerViewHolder(Context context) {
            super(new FrameLayout(context));
            mFrameLayout = (FrameLayout) itemView;
        }

        public void bindView(View view) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view);
            }
            mFrameLayout.removeAllViews();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mFrameLayout.addView(view, params);
            int width = mHorizontal ? ViewGroup.LayoutParams.WRAP_CONTENT : ViewGroup.LayoutParams.MATCH_PARENT;
            int height = mHorizontal ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
            mFrameLayout.setLayoutParams(new RecyclerView.LayoutParams(width, height));
        }
    }
}

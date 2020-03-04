package com.paincker.widget.scrollvelocity;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * RecyclerView滚动速度监听
 */
public class RecyclerVelocityHandler extends RecyclerView.OnScrollListener implements VelocityHandler {

    private final ScrollVelocityTracker mTracker;

    public RecyclerVelocityHandler(Context context) {
        mTracker = new ScrollVelocityTracker(context);
    }

    @Override
    public void setThreshold(int upThreshold, int downThreshold) {
        mTracker.setThreshold(upThreshold, downThreshold);
    }

    @Override
    public void setVelocityTrackerListener(VelocityTrackListener listener) {
        mTracker.setVelocityTrackerListener(listener);
    }

    @Override
    public void setThresholdInDp(Context context, int upThreshold, int downThreshold) {
        mTracker.setThresholdInDp(context, upThreshold, downThreshold);
    }

    @CallSuper
    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        mTracker.onScrollBy(dy);
    }

    @CallSuper
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            mTracker.reset();
        }
    }
}

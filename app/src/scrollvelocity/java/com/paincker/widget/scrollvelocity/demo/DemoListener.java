package com.paincker.widget.scrollvelocity.demo;

import android.content.Context;

import com.paincker.common.utils.log.L;
import com.paincker.common.utils.ui.ToastUtils;
import com.paincker.widget.scrollvelocity.VelocityTrackListener;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class DemoListener implements VelocityTrackListener {

    private final Context mContext;

    public DemoListener(Context context) {
        mContext = context;
    }

    @Override
    public void onVelocityChanged(int velocity) {
        L.d("VelocityTrack", "onVelocityChanged, v = %d", velocity);
    }

    @Override
    public void onScrollFast() {
        ToastUtils.show(mContext, "fast");
    }

    @Override
    public void onScrollSlow() {
        ToastUtils.show(mContext, "slow");
    }
}

package com.paincker.widget.labelview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.paincker.R;
import com.paincker.common.demo.DemoViewCreator;
import com.paincker.common.utils.ui.DisplayUtils;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * LabelView，支持圆角、边框、背景填充色、文字颜色、图标属性的设置，
 * 且可为normal、selected、pressed、disabled状态分别设置
 * <p>
 * Created by jzj on 2020/2/13.
 */
public class LabelViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_view);
        findViewById(R.id.label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        LinearLayout container = findViewById(R.id.container);
        LabelView labelView = new LabelView(this);
        DemoViewCreator.toBigText(labelView);
        labelView.setText("Label2");
        int dp10 = DisplayUtils.dp2px(this, 10);
        labelView.getConfiguration()
                .setTextColorNormal(Color.BLACK)
                .setTextColorPressed(Color.RED)
                .setRadius(dp10)
                .setBorderWidth(DisplayUtils.dp2px(this, 1))
                .setBorderColorNormal(Color.BLACK)
                .setBorderColorPressed(Color.RED)
                .setSolidColorNormal(Color.GREEN)
                .setSolidColorPressed(Color.CYAN)
                .apply();
        labelView.setPadding(dp10, dp10, dp10, dp10);
        labelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        container.addView(labelView, params);
    }
}

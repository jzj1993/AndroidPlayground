package com.paincker.develop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.paincker.R;
import com.paincker.develop.DevelopUtils;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class DevelopPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_develop_panel);
        ViewGroup container = findViewById(R.id.container);
        container.addView(getSwitch(this, DevelopUtils.DEBUG_MODE, "DebugMode", true));
    }

    private Switch getSwitch(final Context context, final String key, String text, boolean defaultValue) {
        Switch view = new Switch(this);
        view.setChecked(DevelopUtils.getBooleanSP(context, key, defaultValue));
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DevelopUtils.putBooleanSP(context, key, isChecked);
            }
        });
        view.setText(text);
        return view;
    }
}

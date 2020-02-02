package com.paincker.common.utils.ui;

import android.content.Context;
import android.widget.Toast;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * Toast工具类
 */
public class ToastUtils {

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}

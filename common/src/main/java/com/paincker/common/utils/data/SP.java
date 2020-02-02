package com.paincker.common.utils.data;

import android.content.Context;
import android.content.SharedPreferences;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class SP {

    public static final String fileName = "file";

    public static void putBooleanSP(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value).apply();
    }

    public static boolean getBooleanSP(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }
}

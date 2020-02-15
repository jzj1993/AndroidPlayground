package com.paincker.develop;

import android.content.Context;
import android.content.SharedPreferences;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class DevelopUtils {

    public static final String DEBUG_MODE = "debug_mode";
    private static final String FILE_NAME = "develop_preference";

    public static void putBooleanSP(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value).apply();
    }

    public static boolean getBooleanSP(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static boolean isDebugMode(Context context) {
        return getBooleanSP(context, DEBUG_MODE, true);
    }

    public static void setDebugMode(Context context, boolean value) {
        putBooleanSP(context, DEBUG_MODE, value);
    }
}

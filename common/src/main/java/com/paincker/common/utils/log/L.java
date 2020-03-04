package com.paincker.common.utils.log;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;

import com.paincker.common.utils.StringUtils;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * Log工具类
 */
public class L {

    public static final boolean ENABLE_LOG = true;

    private static final String PREFIX = "demo_";
    private static final String PREFIX_METHOD = "m_";
    private static final int LOG_METHOD_TAG_LEN = 15;

    public static void v(Object tag, String s, Object... args) {
        log(Log.VERBOSE, tag, s, args);
    }

    public static void d(Object tag, String s, Object... args) {
        log(Log.DEBUG, tag, s, args);
    }

    public static void i(Object tag, String s, Object... args) {
        log(Log.INFO, tag, s, args);
    }

    public static void w(Object tag, String s, Object... args) {
        log(Log.WARN, tag, s, args);
    }

    public static void e(Object tag, String s, Object... args) {
        log(Log.ERROR, tag, s, args);
    }

    public static void logNewEvent(MotionEvent event) {
        String s = MotionEventLogger.newEventToSimpleString(event);
        if (!TextUtils.isEmpty(s)) {
            Log.d(PREFIX + "event_______", s);
        }
    }

    public static void logMethodReset() {
        MethodLogger.reset();
    }

    public static void logMethodStart(Object tag, String method, Object... args) {
        L.d(logMethodTag(tag), MethodLogger.start(method, args));
    }

    public static void logMethodEnd(Object tag, String method, Object... args) {
        L.d(logMethodTag(tag), MethodLogger.end(method, args));
    }

    private static String logMethodTag(Object tag) {
        return PREFIX_METHOD + StringUtils.toLength(tag(tag), LOG_METHOD_TAG_LEN);
    }

    private static void log(int priority, Object tag, String s, Object[] args) {
        Log.println(priority, PREFIX + tag(tag), msg(s, args));
    }

    private static String tag(Object o) {
        if (o instanceof String) {
            return (String) o;
        } else if (o instanceof Class) {
            return ((Class) o).getSimpleName();
        } else {
            return o.getClass().getSimpleName();
        }
    }

    private static String msg(String s, Object[] args) {
        return args == null || args.length == 0 ? s : String.format(s, args);
    }
}

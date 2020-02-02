package com.paincker.common.utils.log;

import com.paincker.common.utils.StringUtils;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 打印方法调用堆栈
 */
public class MethodLogger {

    private static int level = 0;

    public static void reset() {
        level = 0;
    }

    public static String start(String method, Object... args) {
        ++level;
        return StringUtils.tab(level) + String.format(method, args);
    }

    public static String end(String method, Object... args) {
        --level;
        return StringUtils.tab(level) + String.format(method, args);
    }
}

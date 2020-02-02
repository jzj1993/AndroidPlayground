package com.paincker.common.utils;

import java.util.Arrays;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 字符串处理
 */
public class StringUtils {

    public static String repeat(String s, int count) {
        StringBuilder b = new StringBuilder();
        while (count-- > 0) {
            b.append(s);
        }
        return b.toString();
    }

    public static String repeat(char c, int count) {
        char[] array = new char[count];
        Arrays.fill(array, c);
        return new String(array);
    }

    /**
     * 字符串格式化为固定长度，多了裁剪，少了补空格
     *
     * @param s
     * @param length
     * @return
     */
    public static String toLength(String s, int length) {
        if (length <= 0) {
            return "";
        }
        if (s == null || s.length() == 0) {
            return space(length);
        }
        int len = s.length();
        if (len > length) {
            return s.substring(0, length);
        } else {
            return s + space(length - len);
        }
    }

    public static String tab(int count) {
        return space(count * 4);
    }

    public static String space(int length) {
        char[] array = new char[length];
        Arrays.fill(array, ' ');
        return new String(array);
    }
}

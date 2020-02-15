package com.paincker.common.utils;


import android.support.annotation.FloatRange;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class MathUtils {

    /**
     * 计算value在start ~ end之间的ratio。
     * 正常情况下start和end不应该相等，相等时返回0。
     * value = start 时 ratio = 0, value = end 时 ratio = 1。
     */
    public static float ratio(int value, int start, int end) {
        return start == end ? 0 : (float) (value - start) / (end - start);
    }

    /**
     * 计算value在start ~ end之间的ratio，并限制返回值在0~1之间。
     * 正常情况下start和end不应该相等，相等时返回0。
     * value = start 时 ratio = 0, value = end 时 ratio = 1。
     */
    public static float ratioWithBounds(int value, int start, int end) {
        return constrain(ratio(value, start, end), 0f, 1f);
    }

    /**
     * 将amount的值约束到low和high之间
     */
    public static int constrain(int amount, int low, int high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    /**
     * 将amount的值约束到low和high之间
     */
    public static long constrain(long amount, long low, long high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    /**
     * 将amount的值约束到low和high之间
     */
    public static float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    /**
     * 从from到to根据ratio线性插值。ratio=0时返回值等于from，ratio=1时返回值等于to。
     */
    public static int linear(int from, int to, @FloatRange(from = 0, to = 1) float ratio) {
        return from == to ? from : (int) (ratio * (to - from)) + from;
    }

    /**
     * 从from到to根据ratio线性插值。ratio=0时返回值等于from，ratio=1时返回值等于to。
     */
    public static float linear(float from, float to, @FloatRange(from = 0, to = 1) float ratio) {
        return from == to ? from : ratio * (to - from) + from;
    }

    /**
     * 符号函数，大于0返回1，小于0返回-1，等于0返回0
     */
    @SuppressWarnings("UseCompareMethod")
    public static int sign(int val) {
        return val > 0 ? 1 : (val < 0 ? -1 : 0);
    }

    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public static int random(int max) {
        return random(0, max);
    }

    public static boolean doubleEqual(double d1, double d2) {
        return Math.abs(d1 - d2) <= 1e-6;
    }
}

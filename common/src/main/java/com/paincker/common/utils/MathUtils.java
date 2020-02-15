package com.paincker.common.utils;


import android.support.annotation.FloatRange;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 *
 */
public class MathUtils {

    /**
     * 计算value在low~high之间的ratio
     * value = low 时 ratio = 0, value = height 时 ratio = 1
     */
    public static float ratio(int value, int low, int high) {
        return low >= high ? 0 : (float) (value - low) / (high - low);
    }

    /**
     * 计算value在low~high之间的ratio，并限制返回值在0~1之间
     */
    public static float ratioWithBounds(int value, int low, int high) {
        return ratio(constrain(value, low, high), low, high);
    }

    /**
     * 将amount的值约束到low和high之间
     *
     * @param amount
     * @param low
     * @param high
     * @return
     */
    public static int constrain(int amount, int low, int high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    /**
     * 将amount的值约束到low和high之间
     *
     * @param amount
     * @param low
     * @param high
     * @return
     */
    public static long constrain(long amount, long low, long high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    /**
     * 将amount的值约束到low和high之间
     *
     * @param amount
     * @param low
     * @param high
     * @return
     */
    public static float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    /**
     * 从from到to根据百分比percent线性插值
     *
     * @param from
     * @param to
     * @param percent
     * @return
     */
    public static int linear(int from, int to, @FloatRange(from = 0, to = 1) float percent) {
        return from == to ? from : (int) (percent * (to - from)) + from;
    }

    /**
     * 从from到to根据百分比percent线性插值
     *
     * @param from
     * @param to
     * @param percent
     * @return
     */
    public static float linear(float from, float to, @FloatRange(from = 0, to = 1) float percent) {
        return from == to ? from : percent * (to - from) + from;
    }

    /**
     * 符号函数，大于0返回1，小于0返回-1，等于0返回0
     */
    public static int sign(int val) {
        return val > 0 ? 1 : (val < 0 ? -1 : 0);
    }

    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public static int random(int max) {
        return random(0, max);
    }
}

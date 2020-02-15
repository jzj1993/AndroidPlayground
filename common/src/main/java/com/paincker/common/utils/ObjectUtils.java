package com.paincker.common.utils;

import android.support.annotation.Nullable;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 对象操作工具类
 */
public class ObjectUtils {

    /**
     * 任意对象强转成指定类型，转换失败则返回null
     */
    @SuppressWarnings("unchecked")
    public static <T> T safeCast(Object o) {
        try {
            return (T) o;
        } catch (ClassCastException e) {
            return null;
        }
    }

    /**
     * a和b是否相等。如果comparator非空，则使用comparator比较；否则使用“==”和equals比较
     *
     * @param a
     * @param b
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> boolean equals(T a, T b, @Nullable IComparator<T> comparator) {
        if (comparator != null) {
            return comparator.equals(a, b);
        }
        //noinspection EqualsReplaceableByObjectsCall
        return a == b || a != null && a.equals(b);
    }

    public interface IComparator<T> {
        boolean equals(T a, T b);
    }

    public interface ITarget<T> {
        boolean isTarget(T t);
    }
}

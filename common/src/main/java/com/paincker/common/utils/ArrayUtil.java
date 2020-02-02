package com.paincker.common.utils;

import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 数组操作工具类
 */
public class ArrayUtil {

    public static boolean notEmpty(Object[] array) {
        return !isEmpty(array);
    }

    public static boolean notEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean notEmpty(Adapter adapter) {
        return !isEmpty(adapter);
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Adapter adapter) {
        return adapter == null || adapter.isEmpty();
    }

    /**
     * 获取list的size，如果为空则返回0
     */
    public static <T> int sizeof(@Nullable T[] list) {
        return list == null ? 0 : list.length;
    }

    /**
     * 获取collection的size，如果为空则返回0
     */
    public static int sizeof(@Nullable Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * 获取list的item，如果list为空、超出范围，则返回null
     */
    @Nullable
    public static <T> T getItem(@Nullable List<T> list, int index) {
        return (list == null || index < 0 || index >= list.size()) ? null : list.get(index);
    }

    /**
     * 获取array的item，如果array为空、超出范围，则返回null
     */
    @Nullable
    public static <T> T getItem(@Nullable T[] array, int index) {
        return (array == null || index < 0 || index >= array.length) ? null : array[index];
    }

    /**
     * 将elements添加到collection中
     *
     * @param collection
     * @param elements
     * @param <T>
     */
    public static <T> void addAll(@Nullable Collection<T> collection, @Nullable Collection<? extends T> elements) {
        if (collection != null && ArrayUtil.notEmpty(elements)) {
            collection.addAll(elements);
        }
    }

    public static <T> int indexOf(@Nullable List<T> list, @NonNull ObjectUtils.ITarget<T> target) {
        if (notEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if (target.isTarget(list.get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static <T> int indexOf(@Nullable T[] array, @NonNull ObjectUtils.ITarget<T> target) {
        if (notEmpty(array)) {
            for (int i = 0; i < array.length; i++) {
                if (target.isTarget(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 将array所有非空元素加入到一个List中
     */
    @Nullable
    public static <T> ArrayList<T> getNonNullElements(T[] array) {
        if (isEmpty(array)) {
            return null;
        }
        ArrayList<T> list = new ArrayList<>();
        for (T t : array) {
            if (t != null) {
                list.add(t);
            }
        }
        return list.isEmpty() ? null : list;
    }

    /**
     * 将list中的所有元素和obj加入一个新的list返回
     *
     * @param list
     * @param obj
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> ArrayList<T> join(@Nullable Collection<T> list, @Nullable T obj) {
        ArrayList<T> result = new ArrayList<>();
        if (list != null) {
            result.addAll(list);
        }
        if (obj != null) {
            result.add(obj);
        }
        return result;
    }

    /**
     * 将所有list中的所有元素加入一个新的list返回
     *
     * @param lists
     * @param <T>
     * @return
     */
    @NonNull
    @SafeVarargs
    public static <T> ArrayList<T> joinLists(Collection<? extends T>... lists) {
        ArrayList<T> result = new ArrayList<>();
        if (lists != null) {
            for (Collection<? extends T> c : lists) {
                result.addAll(c);
            }
        }
        return result;
    }

    /**
     * 处理collections中每个非空元素
     *
     * @param collections
     * @param operator
     * @param <T>
     */
    public static <T> void forEachNonNull(@Nullable Collection<T> collections, @NonNull NonNullOperator<? super T> operator) {
        if (notEmpty(collections)) {
            for (T t : collections) {
                if (t != null) {
                    operator.operate(t);
                }
            }
        }
    }

    public interface NonNullOperator<T> {
        void operate(@NonNull T t);
    }
}

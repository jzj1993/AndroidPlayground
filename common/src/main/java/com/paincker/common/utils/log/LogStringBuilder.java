package com.paincker.common.utils.log;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.AnyRes;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.paincker.common.utils.ArrayUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 一些常用数据结构的格式化输出。
 */
public class LogStringBuilder implements Appendable, CharSequence, Serializable {

    @NonNull
    private final StringBuilder mBuilder;

    public LogStringBuilder() {
        mBuilder = new StringBuilder();
    }

    public LogStringBuilder(@Nullable String msg) {
        mBuilder = new StringBuilder(msg == null ? "" : msg);
    }

    public LogStringBuilder(@Nullable StringBuilder sb) {
        mBuilder = sb == null ? new StringBuilder() : sb;
    }

    private static boolean resourceHasPackage(@AnyRes int resId) {
        return (resId >>> 24) != 0;
    }

    @Override
    public int length() {
        return mBuilder.length();
    }

    @Override
    public char charAt(int index) {
        return mBuilder.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return mBuilder.subSequence(start, end);
    }

    @Override
    public LogStringBuilder append(char c) {
        mBuilder.append(c);
        return this;
    }

    @Override
    public LogStringBuilder append(CharSequence csq) {
        mBuilder.append(csq);
        return this;
    }

    @Override
    public LogStringBuilder append(CharSequence csq, int start, int end) {
        mBuilder.append(csq, start, end);
        return this;
    }

    public LogStringBuilder append(int i) {
        mBuilder.append(i);
        return this;
    }

    public LogStringBuilder append(long l) {
        mBuilder.append(l);
        return this;
    }

    public LogStringBuilder append(float f) {
        mBuilder.append(f);
        return this;
    }

    public LogStringBuilder append(double d) {
        mBuilder.append(d);
        return this;
    }

    public LogStringBuilder append(Object o) {
        mBuilder.append(o);
        return this;
    }

    public LogStringBuilder append(String str) {
        mBuilder.append(str);
        return this;
    }

    public LogStringBuilder append(StringBuffer sb) {
        mBuilder.append(sb);
        return this;
    }

    /**
     * 追加"null"
     */
    public LogStringBuilder appendNull() {
        return append("null");
    }

    /**
     * 追加List，格式"[ item1 | item2 ]"
     */
    public <T> LogStringBuilder append(T[] list) {
        return append(list, null);
    }

    /**
     * 追加List，格式"[ item1 | item2 ]"
     */
    public <T> LogStringBuilder append(T[] list, @Nullable MapFunc<? super T> mapFunc) {
        if (list == null) {
            return appendNull();
        }
        append("[ ");
        boolean first = true;
        for (T t : list) {
            if (first) {
                first = false;
            } else {
                append(" | ");
            }
            append(mapFunc == null ? String.valueOf(t) : mapFunc.map(t));
        }
        append(" ]");
        return this;
    }

    /**
     * 追加Collection，格式"[ item1 | item2 ]"
     */
    public <T> LogStringBuilder append(Collection<T> list) {
        return append(list, null);
    }

    /**
     * 追加Collection，格式"[ item1 | item2 ]"
     *
     * @param mapFunc 将Item转为字符串
     */
    public <T> LogStringBuilder append(Collection<T> list, @Nullable MapFunc<? super T> mapFunc) {
        if (list == null) {
            return appendNull();
        }
        append("[ ");
        boolean first = true;
        for (T t : list) {
            if (first) {
                first = false;
            } else {
                append(" | ");
            }
            append(mapFunc == null ? String.valueOf(t) : mapFunc.map(t));
        }
        append(" ]");
        return this;
    }

    public <K, V> LogStringBuilder append(Map<K, V> map) {
        return append(map, null, null);
    }

    /**
     * 追加Map，格式为"[ key1 : val1 | key2 : val2 ]"
     *
     * @param keyMapFunc 将Key映射到字符串。为空则使用 {@link String#valueOf(Object)}
     * @param valMapFunc 将Value映射到字符串。为空则使用 {@link String#valueOf(Object)}
     */
    public <K, V> LogStringBuilder append(Map<K, V> map, @Nullable MapFunc<K> keyMapFunc, @Nullable MapFunc<V> valMapFunc) {
        if (map == null) {
            return appendNull();
        }
        append("[ ");
        boolean first = true;
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        for (Map.Entry<K, V> entry : entrySet) {
            if (first) {
                first = false;
            } else {
                append(" | ");
            }
            K key = entry.getKey();
            append(keyMapFunc == null ? String.valueOf(key) : keyMapFunc.map(key));
            append(" : ");
            V val = entry.getValue();
            append(valMapFunc == null ? String.valueOf(val) : valMapFunc.map(val));
        }
        append(" ]");
        return this;
    }

    /**
     * 追加ResId名，格式"app:id/layout_comment"
     */
    public LogStringBuilder appendResIdName(Context context, @IdRes int resId) {
        if (context != null && resourceHasPackage(resId)) {
            final Resources r = context.getResources();
            if (r != null) {
                try {
                    String pkgname;
                    switch (resId & 0xff000000) {
                        case 0x7f000000:
                            pkgname = "app";
                            break;
                        case 0x01000000:
                            pkgname = "android";
                            break;
                        default:
                            pkgname = r.getResourcePackageName(resId);
                            break;
                    }
                    append(pkgname);
                    append(":");
                    append(r.getResourceTypeName(resId));
                    append("/");
                    append(r.getResourceEntryName(resId));
                    return this;
                } catch (Resources.NotFoundException ignored) {
                }
            }
        }
        return append(resId);
    }

    /**
     * 追加View，格式"[ LinearLayout | app:id/layout_comment ]"
     */
    public LogStringBuilder appendView(View view) {
        if (view == null) {
            return appendNull();
        }
        append("[ ");
        append(view.getClass().getSimpleName());
        append(" | ");
        appendResIdName(view.getContext(), view.getId());
        append(" ]");
        return this;
    }

    /**
     * 追加Intent，格式"{ Intent {xxx}, extra = [ key : val | key : val ] }"
     */
    public LogStringBuilder appendIntent(Intent intent) {
        if (intent == null) {
            return appendNull();
        }
        append("{ ").append(intent.toString()).append(", extra = ").appendBundle(intent.getExtras()).append(" }");
        return this;
    }

    /**
     * 追加Bundle，格式"[ key : val | key : val ]"
     */
    public LogStringBuilder appendBundle(Bundle bundle) {
        if (bundle == null) {
            return appendNull();
        }
        Set<String> keySet = bundle.keySet();
        boolean first = true;
        append("[ ");
        for (String key : keySet) {
            Object val = bundle.get(key);
            if (first) {
                first = false;
            } else {
                append(" | ");
            }
            append(key).append(" : ").append(val);
        }
        append(" ]");
        return this;
    }

    /**
     * 追加当前运行的Task(返回栈)信息
     */
    public LogStringBuilder appendRunningTasksInfo(Context context) {
        if (context == null) {
            return appendNull();
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(10);
        append("RunningTasks = [\n");
        if (ArrayUtil.notEmpty(tasks)) {
            for (ActivityManager.RunningTaskInfo info : tasks) {
                if (info != null) {
                    append("    ").append("task id = ").append(String.valueOf(info.id)).append(", ");
                    final String baseAct = info.baseActivity == null ? "null" : info.baseActivity.flattenToShortString();
                    append("base activity = ").append(baseAct).append(", ");
                    final String topAct = info.topActivity == null ? "null" : info.topActivity.flattenToShortString();
                    append("top activity = ").append(topAct).append('\n');
                }
            }
        }
        append("]");
        return this;
    }

    /**
     * 追加Throwable的堆栈信息
     */
    public LogStringBuilder appendStackTrace(Throwable t) {
        return append(Log.getStackTraceString(t));
    }

    /**
     * 追加堆栈信息
     */
    public LogStringBuilder appendStackTrace(StackTraceElement[] stackTraceElements) {
        return appendStackTrace(stackTraceElements, 0);
    }

    /**
     * 追加堆栈信息
     */
    public LogStringBuilder appendStackTrace(StackTraceElement[] stackTraceElements, @IntRange(from = 0) int startOffset) {
        if (stackTraceElements == null) {
            return appendNull();
        }
        for (int i = Math.max(startOffset, 0); i < stackTraceElements.length; i++) {
            append("\tat ");
            append(stackTraceElements[i].toString());
            append('\n');
        }
        return this;
    }

    /**
     * 追加当前线程的堆栈（跳过本方法内部的StackTraceElement）
     */
    public LogStringBuilder appendCurrentStackTrace() {
        final StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        return appendStackTrace(stack, calcStartOffset(stack, 2)); // 2: 跳过getStackTrace
    }

    /**
     * 跳过本工具类中的element
     */
    private int calcStartOffset(StackTraceElement[] stack, int start) {
        if (stack != null) {
            for (int i = start; i < stack.length; i++) {
                StackTraceElement element = stack[i];
                if (element == null) continue;
                if (!this.getClass().getName().equals(element.getClassName())) {
                    return i;
                }
            }
        }
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        return mBuilder.toString();
    }

    public void logVerbose(String tag) {
        if (L.ENABLE_LOG) {
            L.v(tag, toString());
        }
    }

    public void logDebug(String tag) {
        if (L.ENABLE_LOG) {
            L.d(tag, toString());
        }
    }

    public void logInfo(String tag) {
        if (L.ENABLE_LOG) {
            L.i(tag, toString());
        }
    }

    public void logWarning(String tag) {
        if (L.ENABLE_LOG) {
            L.w(tag, toString());
        }
    }

    public void logError(String tag) {
        if (L.ENABLE_LOG) {
            Log.e(tag, toString());
        }
    }

    public void log(int priority, String tag) {
        if (L.ENABLE_LOG) {
            Log.println(priority, tag, toString());
        }
    }

    /**
     * 将对象转换成字符串
     *
     * @param <T>
     */
    public interface MapFunc<T> {
        /**
         * 将对象转换成字符串
         *
         * @param t 对象
         * @return 字符串
         */
        String map(T t);
    }
}

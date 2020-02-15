package com.paincker.eventbus;

import android.support.annotation.NonNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class EventBus {

    private final Set<Object> mHandlers = new HashSet<>();

    public static EventBus getDefault() {
        return Holder.BUS;
    }

    /**
     * 添加Handler
     */
    public void register(Object handler) {
        if (handler != null) {
            mHandlers.add(handler);
        }
    }

    /**
     * 删除Handler
     */
    public void unregister(Object handler) {
        if (handler != null) {
            mHandlers.remove(handler);
        }
    }

    /**
     * 根据接口调用所有匹配的Handler
     *
     * @param clazz 接口
     * @return 返回一个代理对象，调用其方法相当于调用所有实例的对应方法，返回第一个实例的方法调用返回值
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public <Handler> Handler find(Class<Handler> clazz) {
        final Class<?>[] interfaces = new Class[]{clazz};
        final EventInvocationHandler<Handler> handler = new EventInvocationHandler<>(clazz);
        return (Handler) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, handler);
    }

    /**
     * 根据接口获取所有匹配的Handler
     *
     * @param clazz 接口
     * @return 返回一个 {@link IterableList}
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public <Handler> IterableList<Handler> findAll(Class<Handler> clazz) {
        IterableList<Handler> list = new IterableList<>();
        for (Object handler : mHandlers) {
            if (clazz.isInstance(handler)) {
                list.add((Handler) handler);
            }
        }
        return list;
    }

    private static class Holder {
        private static final EventBus BUS = new EventBus();
    }

    private class EventInvocationHandler<Handler> implements InvocationHandler {

        private final Class<Handler> mClazz;

        EventInvocationHandler(Class<Handler> clazz) {
            mClazz = clazz;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;
            boolean first = true;
            for (Object handler : mHandlers) {
                if (mClazz.isInstance(handler)) {
                    if (first) {
                        result = method.invoke(handler, args);
                        first = false;
                    } else {
                        method.invoke(handler, args);
                    }
                }
            }
            return result;
        }
    }
}

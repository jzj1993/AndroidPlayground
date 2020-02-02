package com.paincker.common.utils.ui;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.paincker.common.utils.log.L;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 发送MotionEvent，便于测试View
 */
public class EventUtils {

    private static Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 向Activity分发移动事件 ACTION_DOWN --> MOVE --> MOVE --> ... --> MOVE --> UP
     */
    public static void moveAction(final Activity activity, final EventInfoProvider provider) {
        int eventCount = provider.getCount();
        if (eventCount < 3) return;
        final long downTime = provider.getTime(0);

        int offsetTime = 0;
        for (int i = 0; i < eventCount; i++) {
            final int action;
            if (i == 0) {
                action = MotionEvent.ACTION_DOWN;
            } else if (i == eventCount - 1) {
                action = MotionEvent.ACTION_UP;
            } else {
                action = MotionEvent.ACTION_MOVE;
            }
            final int index = i;
            final long eventTime = provider.getTime(index);
            final int x = provider.getX(index);
            final int y = provider.getY(index);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    L.d(EventUtils.class, "----> send event(%d): xy = (%d, %d)", index, x, y);
                    activity.dispatchTouchEvent(MotionEvent.obtain(downTime, eventTime, action, x, y, 0));
                }
            }, offsetTime);
        }
    }

    /**
     * 向Activity分发移动事件 ACTION_DOWN --> MOVE --> MOVE --> ... --> MOVE --> UP
     *
     * @param eventCount 事件总Count，应该大于等于3
     */
    public static void moveAction(final Activity activity, final int startX, final int startY, final int stepX, final int stepY, final int stepT, final int eventCount) {
        moveAction(activity, new EventInfoProvider() {
            final long mDownTime = System.currentTimeMillis();

            @Override
            public int getCount() {
                return eventCount;
            }

            @Override
            public int getX(int index) {
                return startX + index * stepX;
            }

            @Override
            public int getY(int index) {
                return startY + index * stepY;
            }

            @Override
            public long getTime(int index) {
                return mDownTime + index * stepT;
            }
        });
    }

    public static void moveFromTargetCenter(final Activity activity, final View target, int startDelay, final int stepY) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] loc = new int[2];
                target.getLocationInWindow(loc);
                final int x = loc[0] + target.getWidth() / 2;
                final int y = loc[1] + target.getHeight() / 2;
                EventUtils.moveAction(activity, x, y, 0, stepY, 100, 30);
            }
        }, startDelay);
    }

    public static void moveUpFromTargetCenter(final Activity activity, final View target) {
        moveFromTargetCenter(activity, target, 1000, -20);
    }

    public static void moveDownFromTargetCenter(final Activity activity, final View target) {
        moveFromTargetCenter(activity, target, 800, 20);
    }

    public interface EventInfoProvider {

        /**
         * @return 事件总Count，应该大于等于3
         */
        int getCount();

        int getX(int index);

        int getY(int index);

        long getTime(int index);
    }

    public static abstract class OffsetArrayProvider implements EventInfoProvider {

        private final long mDownTime = System.currentTimeMillis();
        private final int mStartX;
        private final int mStartY;

        public OffsetArrayProvider(View centerInTarget) {
            int[] loc = new int[2];
            centerInTarget.getLocationInWindow(loc);
            mStartX = loc[0] + centerInTarget.getWidth() / 2;
            mStartY = loc[1] + centerInTarget.getHeight() / 2;
        }

        public OffsetArrayProvider(int startX, int startY) {
            mStartX = startX;
            mStartY = startY;
        }

        @Override
        public int getCount() {
            return Math.min(len(getArrayX()), len(getArrayY()));
        }

        private int len(int[] arr) {
            return arr == null ? Integer.MAX_VALUE : arr.length;
        }

        private int get(int[] arr, int index, int def) {
            return (arr == null || index < 0 || index >= arr.length) ? def : arr[index];
        }

        @Override
        public int getX(int index) {
            return mStartX + get(getArrayX(), index, 0);
        }

        @Override
        public int getY(int index) {
            return mStartY + get(getArrayY(), index, 0);
        }

        @Override
        public long getTime(int index) {
            return mDownTime + get(getTimeArray(), index, 100);
        }

        @Nullable
        protected abstract int[] getTimeArray();

        @Nullable
        protected abstract int[] getArrayX();

        @Nullable
        protected abstract int[] getArrayY();
    }
}

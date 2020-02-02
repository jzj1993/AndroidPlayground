package com.paincker.common.utils.log;

import android.annotation.SuppressLint;
import android.view.MotionEvent;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 打印MotionEvent信息
 */
public class MotionEventLogger {

    private static final MotionEventInfo info = new MotionEventInfo();

    public static boolean isNewEvent(MotionEvent event) {
        boolean result = !info.equals(event);
        info.copyFrom(event);
        return result;
    }

    public static String newEventToSimpleString(MotionEvent event) {
        return isNewEvent(event) ? eventToSimpleString(event) : "";
    }

    @SuppressLint("DefaultLocale")
    public static String eventToSimpleString(MotionEvent event) {
        return String.format("%s, raw xy = (%.1f, %.1f), xy = (%.1f, %.1f)", actionToString(event.getAction()), event.getRawX(), event.getRawY(), event.getX(), event.getY());
    }

    public static String actionToString(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";
            case MotionEvent.ACTION_UP:
                return "ACTION_UP";
            case MotionEvent.ACTION_CANCEL:
                return "ACTION_CANCEL";
            case MotionEvent.ACTION_OUTSIDE:
                return "ACTION_OUTSIDE";
            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_HOVER_MOVE:
                return "ACTION_HOVER_MOVE";
            case MotionEvent.ACTION_SCROLL:
                return "ACTION_SCROLL";
            case MotionEvent.ACTION_HOVER_ENTER:
                return "ACTION_HOVER_ENTER";
            case MotionEvent.ACTION_HOVER_EXIT:
                return "ACTION_HOVER_EXIT";
            case MotionEvent.ACTION_BUTTON_PRESS:
                return "ACTION_BUTTON_PRESS";
            case MotionEvent.ACTION_BUTTON_RELEASE:
                return "ACTION_BUTTON_RELEASE";
        }
        int index = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_POINTER_DOWN:
                return "ACTION_POINTER_DOWN(" + index + ")";
            case MotionEvent.ACTION_POINTER_UP:
                return "ACTION_POINTER_UP(" + index + ")";
            default:
                return Integer.toString(action);
        }
    }


    public static class MotionEventInfo {

        private int mAction;
        private float mX;
        private float mY;

        public MotionEventInfo() {

        }

        public void copyFrom(MotionEvent event) {
            mAction = event.getAction();
            mX = event.getRawX();
            mY = event.getRawY();
        }

        public boolean equals(MotionEvent event) {
            return event != null && mAction == event.getAction() && mX == event.getRawX() && mY == event.getRawY();
        }
    }
}

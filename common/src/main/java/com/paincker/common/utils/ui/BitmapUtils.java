package com.paincker.common.utils.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.paincker.copy.FastBlur;
import com.paincker.copy.RSBlur;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * Bitmap工具类
 */
public class BitmapUtils {

    /**
     * 图片模糊
     *
     * @param context
     * @param bitmap
     * @param radius
     * @return
     */
    public static Bitmap blur(Context context, Bitmap bitmap, int radius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            try {
                bitmap = RSBlur.blur(context, bitmap, radius);
            } catch (android.renderscript.RSRuntimeException e) {
                bitmap = FastBlur.blur(bitmap, radius, true);
            }
        } else {
            bitmap = FastBlur.blur(bitmap, radius, true);
        }
        return bitmap;
    }

    /**
     * View截取图片并缩放满足最大宽度
     *
     * @param view
     * @param maxWidth
     * @return
     */
    public static Bitmap captureFromView(View view, int maxWidth) {
        if (null == view) {
            return null;
        }
        final int width = view.getMeasuredWidth();
        if (width < maxWidth) {
            return captureFromView(view, Bitmap.Config.RGB_565);
        } else {
            return captureFromView(view, (float) maxWidth / width);
        }
    }

    /**
     * View截取图片并缩放
     *
     * @param view
     * @param scale
     * @return
     */
    public static Bitmap captureFromView(View view, float scale) {
        if (null == view) {
            return null;
        }
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = null;
        try {
            Bitmap cacheBitmap = view.getDrawingCache();
            if (null != cacheBitmap) {
                bitmap = Bitmap.createScaledBitmap(cacheBitmap,
                        (int) (cacheBitmap.getWidth() * scale),
                        (int) (cacheBitmap.getHeight() * scale), false);
            }
        } catch (OutOfMemoryError e) {
            return null;
        } finally {
            view.setDrawingCacheEnabled(false);
            view.destroyDrawingCache();
        }
        return bitmap;
    }

    /**
     * View截取图片
     *
     * @param view
     * @param config
     * @return
     */
    public static Bitmap captureFromView(View view, Bitmap.Config config) {
        if (null == view) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), config);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        int height = bitmap.getHeight();
        canvas.drawBitmap(bitmap, 0, height, paint);
        view.draw(canvas);
        return bitmap;
    }

    /**
     * 转换成单色图片，保留透明像素信息
     *
     * @param bitmap
     * @param color
     * @return
     */
    public static Bitmap toSingleColor(Bitmap bitmap, int color) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        final int length = width * height;
        final int[] colors = new int[length];
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = bitmap.getPixel(x, y);
                if (pixel != Color.TRANSPARENT) {
                    pixel = color;
                }
                colors[index] = pixel;
                ++index;
            }
        }
        return Bitmap.createBitmap(colors, width, height, Bitmap.Config.ARGB_8888);
    }

    /**
     * View生成阴影
     *
     * @param source
     * @param dest
     */
    public static void generateShadow(final View source, final ImageView dest) {
        if (source == null || dest == null) {
            return;
        }
        final Context context = source.getContext();
        new AsyncTask<Void, Void, Bitmap>() {

            private Bitmap mBitmap;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mBitmap = captureFromView(source, Bitmap.Config.ALPHA_8);
            }

            @Override
            protected Bitmap doInBackground(Void... params) {
                Bitmap bitmap = toSingleColor(mBitmap, Color.GRAY);
                mBitmap.recycle();
                Bitmap shadow = blur(context, bitmap, 20);
                bitmap.recycle();
                return shadow;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                dest.setImageBitmap(bitmap);
            }
        }.execute();
    }
}

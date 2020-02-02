package com.paincker.widget.roundcornerlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.paincker.R;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 圆角布局，内部可以嵌套任意View组件
 */
public class RoundCornerLayout extends FrameLayout {

    private final Paint mRoundPaint;
    private final Paint mImagePaint;

    private float mTopLeftRadius;
    private float mTopRightRadius;
    private float mBottomLeftRadius;
    private float mBottomRightRadius;

    public RoundCornerLayout(Context context) {
        this(context, null);
    }

    public RoundCornerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundCornerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerLayout);
            float radius = a.getDimension(R.styleable.RoundCornerLayout_android_radius, 0);
            mTopLeftRadius = a.getDimension(R.styleable.RoundCornerLayout_android_topLeftRadius, radius);
            mTopRightRadius = a.getDimension(R.styleable.RoundCornerLayout_android_topRightRadius, radius);
            mBottomLeftRadius = a.getDimension(R.styleable.RoundCornerLayout_android_bottomLeftRadius, radius);
            mBottomRightRadius = a.getDimension(R.styleable.RoundCornerLayout_android_bottomRightRadius, radius);
            a.recycle();
        }

        mRoundPaint = new Paint();
        mRoundPaint.setColor(Color.WHITE);
        mRoundPaint.setAntiAlias(true);
        mRoundPaint.setStyle(Paint.Style.FILL);
        mRoundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        mImagePaint = new Paint();
        mImagePaint.setXfermode(null);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), mImagePaint, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        drawTopLeft(canvas);
        drawTopRight(canvas);
        drawBottomLeft(canvas);
        drawBottomRight(canvas);
        canvas.restore();
    }

    private void drawTopLeft(Canvas canvas) {
        if (mTopLeftRadius > 0) {
            Path path = new Path();
            path.moveTo(0, mTopLeftRadius);
            path.lineTo(0, 0);
            path.lineTo(mTopLeftRadius, 0);
            path.arcTo(new RectF(0, 0, mTopLeftRadius * 2, mTopLeftRadius * 2), -90, -90);
            path.close();
            canvas.drawPath(path, mRoundPaint);
        }
    }

    private void drawTopRight(Canvas canvas) {
        if (mTopRightRadius > 0) {
            int width = getWidth();
            Path path = new Path();
            path.moveTo(width - mTopRightRadius, 0);
            path.lineTo(width, 0);
            path.lineTo(width, mTopRightRadius);
            path.arcTo(new RectF(width - 2 * mTopRightRadius, 0, width, mTopRightRadius * 2), 0, -90);
            path.close();
            canvas.drawPath(path, mRoundPaint);
        }
    }

    private void drawBottomLeft(Canvas canvas) {
        if (mBottomLeftRadius > 0) {
            int height = getHeight();
            Path path = new Path();
            path.moveTo(0, height - mBottomLeftRadius);
            path.lineTo(0, height);
            path.lineTo(mBottomLeftRadius, height);
            path.arcTo(new RectF(0, height - 2 * mBottomLeftRadius, mBottomLeftRadius * 2, height), 90, 90);
            path.close();
            canvas.drawPath(path, mRoundPaint);
        }
    }

    private void drawBottomRight(Canvas canvas) {
        if (mBottomRightRadius > 0) {
            int height = getHeight();
            int width = getWidth();
            Path path = new Path();
            path.moveTo(width - mBottomRightRadius, height);
            path.lineTo(width, height);
            path.lineTo(width, height - mBottomRightRadius);
            path.arcTo(new RectF(width - 2 * mBottomRightRadius, height - 2 * mBottomRightRadius, width, height), 0, 90);
            path.close();
            canvas.drawPath(path, mRoundPaint);
        }
    }
}

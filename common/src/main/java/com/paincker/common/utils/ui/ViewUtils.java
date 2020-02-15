package com.paincker.common.utils.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ScrollingView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.paincker.common.R;
import com.paincker.common.utils.MathUtils;


/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * View操作相关工具类
 */
public class ViewUtils {

    private static final int[] tempLoc = new int[2];

    /**
     * 滚动ScrollingView，并返回消费掉的像素。注意：在RecyclerView中不好用……
     */
    public static int verticalScrollBy(ScrollingView view, int dy) {
        if (dy == 0 || !(view instanceof View)) {
            return 0;
        }
        // 当前offset
        final int from = view.computeVerticalScrollOffset();
        // 滚动范围
        final int range = view.computeVerticalScrollRange() - view.computeVerticalScrollExtent();
        if (range == 0) {
            return 0;
        }
        // 滚动后位置
        final int to = MathUtils.constrain(from + dy, 0, range);
        if (to == from) {
            return 0;
        }
        ((View) view).scrollTo(0, to);
        return to - from;
    }

    /**
     * 设置textView的text。text为空时，textView不显示。
     *
     * @param textView 请勿传入null
     * @return textView是否显示
     */
    public static boolean setText(TextView textView, CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
            return false;
        } else {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
            return true;
        }
    }

    /**
     * 设置textView的text。text为0时，textView不显示。
     *
     * @param textView 请勿传入null
     * @return textView是否显示
     */
    public static boolean setText(TextView textView, int text) {
        if (text == 0) {
            textView.setVisibility(View.GONE);
            return false;
        } else {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
            return true;
        }
    }

    /**
     * 设置textView的text。text为空时，layout不显示。
     *
     * @param layout   请勿传入null
     * @param textView 请勿传入null
     * @return textView是否显示
     */
    public static boolean setText(ViewGroup layout, TextView textView, CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            layout.setVisibility(View.GONE);
            return false;
        } else {
            textView.setText(text);
            layout.setVisibility(View.VISIBLE);
            return true;
        }
    }

    /**
     * 设置TextView的text,当text为空时,展示默认的text
     *
     * @param textView
     * @param text
     * @param defaultText
     */
    public static void setText(TextView textView, CharSequence text, CharSequence defaultText) {
        if (textView == null) {
            return;
        }
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        } else {
            textView.setText(defaultText);
        }
    }

    /**
     * 设置ImageView的icon。icon为0时，imageView不显示。
     *
     * @param imageView 请勿传入null
     */
    public static boolean setImageResource(ImageView imageView, int resId) {
        if (resId == 0) {
            imageView.setVisibility(View.GONE);
            return false;
        } else {
            imageView.setImageResource(resId);
            imageView.setVisibility(View.VISIBLE);
            return true;
        }
    }

    /**
     * 通过LayoutParams设置View的尺寸
     *
     * @param view   View
     * @param width  宽度。可以是ViewGroup.LayoutParams.MATCH_PARENT，ViewGroup.LayoutParams.WRAP_CONTENT。为0则不设置。
     * @param height 高度。可以是ViewGroup.LayoutParams.MATCH_PARENT，ViewGroup.LayoutParams.WRAP_CONTENT。为0则不设置。
     * @return LayoutParams为null，则返回false
     */
    public static boolean setViewLayout(View view, int width, int height) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null) return false;
        if (width != 0) lp.width = width;
        if (height != 0) lp.height = height;
        view.setLayoutParams(lp);
        return true;
    }

    /**
     * 设置View的Alpha。Alpha=0时，View不展示
     */
    public static void setAlpha(View view, float alpha) {
        view.setVisibility(alpha > 0f ? View.VISIBLE : View.GONE);
        view.setAlpha(alpha);
    }

    /**
     * 根据Class倒序搜索View，返回第一个搜索结果。没搜索到则返回null。
     *
     * @param clazz 要搜索的View的类型是clazz或其子类
     */
    @SuppressWarnings("unchecked")
    public static <T> T inverseFindViewBySuperClass(View view, @NonNull Class<? extends T> clazz) {
        if (view == null) return null;

        if (clazz.isInstance(view)) return (T) view;

        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup) view;
            final int count = viewGroup.getChildCount();
            for (int i = count - 1; i >= 0; i--) {
                T result = inverseFindViewBySuperClass(viewGroup.getChildAt(i), clazz);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 根据Class倒序搜索View，返回第一个搜索结果。没搜索到则返回null。
     *
     * @param clazz 要搜索的View的类型是clazz
     */
    @SuppressWarnings("unchecked")
    public static <T> T inverseFindViewByClass(View view, @NonNull Class<? extends T> clazz) {
        if (view == null) return null;

        if (clazz == view.getClass()) return (T) view;

        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup) view;
            final int count = viewGroup.getChildCount();
            for (int i = count - 1; i >= 0; i--) {
                T result = inverseFindViewByClass(viewGroup.getChildAt(i), clazz);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 判断一个View是否是另一个View的嵌套ChildView
     */
    public static boolean isChildViewOf(View view, final View parent) {
        if (view == null || parent == null) return false;
        while (true) {
            if (view == parent) return true;

            final ViewParent viewParent = view.getParent();
            if (viewParent instanceof View) {
                view = (View) viewParent;
            } else {
                return false;
            }
        }
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            return 0;
        }
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 获取 ActionBar 高度，用于 android:layout_height="?android:attr/actionBarSize" 这类的 actionBar 高度获取
     *
     * @param context
     * @return
     */
    public static int getActionBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        Resources.Theme theme = context.getTheme();
        if (theme == null) {
            return 0;
        }
        TypedArray styledAttributes = theme.obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        if (styledAttributes == null) {
            return 0;
        }
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return actionBarSize;
    }

    /**
     * 加载Drawable并启动动画（如果支持）
     */
    public static void loadAnimatableDrawable(ImageView imageView, @DrawableRes int res) {
        Drawable dr = imageView.getResources().getDrawable(res);
        if (dr != null) {
            imageView.setImageDrawable(dr);
            if (dr instanceof Animatable) {
                ((Animatable) dr).start();
            }
        }
    }

    /**
     * 释放Drawable
     */
    public static void releaseDrawable(ImageView imageView) {
        if (imageView.getDrawable() != null) {
            imageView.getDrawable().setCallback(null);
        }
        imageView.setImageDrawable(null);
    }

    /**
     * 判断点击屏幕的位置是否在指定的view上
     *
     * @param view 点击的view
     * @param x    屏幕所在X位置
     * @param y    屏幕所在Y位置
     * @return
     */
    public static boolean isInViewArea(View view, float x, float y) {
        if (view == null) {
            return false;
        }
        view.getLocationOnScreen(tempLoc);
        RectF rect = new RectF(tempLoc[0], tempLoc[1], tempLoc[0] + view.getWidth(), tempLoc[1] + view.getHeight());
        return rect.contains(x, y);
    }

    public static boolean isEventInView(MotionEvent event, View view) {
        if (event == null || view == null) {
            return false;
        }
        view.getLocationOnScreen(tempLoc);
        RectF rect = new RectF(tempLoc[0], tempLoc[1], tempLoc[0] + view.getWidth(), tempLoc[1] + view.getHeight());
        return rect.contains(event.getRawX(), event.getRawY());
    }

    /**
     * view相对refView的顶部位置差（topLocationOf(view) - topLocationOf(ref)）
     *
     * @param view
     * @param refView
     * @return 大于0：view顶部在refView下方；小于0：在上方
     */
    public static int getDiffY(View view, View refView) {
        if (view == null || refView == null) return 0;
        view.getLocationInWindow(tempLoc);
        int y = tempLoc[1];
        refView.getLocationInWindow(tempLoc);
        y = y - tempLoc[1];
        return y;
    }

    /**
     * 获取View相对Window的Rect
     *
     * @param view
     * @return
     */
    public static Rect getViewRectInWindow(@Nullable View view) {
        if (view == null) return null;
        view.getLocationInWindow(tempLoc);
        final int l = tempLoc[0];
        final int t = tempLoc[1];
        return new Rect(l, t, l + view.getWidth(), t + view.getHeight());
    }

    /**
     * View在Window中的位置，和指定的Rect是否有重叠
     *
     * @return
     */
    public static boolean isViewIntersectRect(@Nullable View view, @Nullable Rect rect) {
        return view != null && rect != null && Rect.intersects(getViewRectInWindow(view), rect);
    }

    public static boolean isInScreen(View view) {
        return view != null && isInScreen(view, view.getContext());
    }

    /**
     * 判断View是否在屏幕内
     *
     * @return
     */
    public static boolean isInScreen(View view, Context context) {
        if (view == null || context == null) {
            return false;
        }
        // 判断view先是区域和屏幕区域是否有交集 有交集则说明需要曝光
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        int viewTop = view.getTop();
        int viewBottom = view.getBottom();
        int viewLeft = view.getLeft();
        int viewRight = view.getRight();

        // view在屏幕上的左上角坐标
        int[] locationOnScreen = new int[2];
        view.getLocationOnScreen(locationOnScreen);
        // view在屏幕上的rect
        Rect viewVisibleRect = new Rect(locationOnScreen[0], locationOnScreen[1], viewRight - viewLeft + locationOnScreen[0], viewBottom - viewTop + locationOnScreen[1]);
        // 屏幕rect
        Rect screenRect = new Rect(0, 0, screenWidth, screenHeight);
        // 屏幕rect是否包含view rect
        return screenRect.intersect(viewVisibleRect);

    }

    /**
     * 修改View的Padding。传入负数表示不改变，0和正数表示新的dp值。
     */
    public static void setPaddingInDp(View view, int left, int top, int right, int bottom) {
        if (view == null) return;
        left = left < 0 ? view.getPaddingLeft() : DisplayUtils.dp2px(view.getContext(), left);
        top = top < 0 ? view.getPaddingTop() : DisplayUtils.dp2px(view.getContext(), top);
        right = right < 0 ? view.getPaddingRight() : DisplayUtils.dp2px(view.getContext(), right);
        bottom = bottom < 0 ? view.getPaddingBottom() : DisplayUtils.dp2px(view.getContext(), bottom);
        view.setPadding(left, top, right, bottom);
    }

    /**
     * 代码设置RatingBar的资源。仅支持设备Android系统版本大于等于 {@link Build.VERSION_CODES#LOLLIPOP}
     *
     * @param ratingBar
     * @param normalResId   未选中时的图标
     * @param selectedResId 选中时的图标
     */
    public static void setRatingBar(RatingBar ratingBar, @DrawableRes int normalResId, @DrawableRes int selectedResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && ratingBar != null) {
            Context context = ratingBar.getContext();
            LayerDrawable ld = (LayerDrawable) context.getResources().getDrawable(R.drawable.rating_placeholder);
            ld.setDrawableByLayerId(android.R.id.background, context.getResources().getDrawable(normalResId));
            ld.setDrawableByLayerId(android.R.id.secondaryProgress, context.getResources().getDrawable(normalResId));
            ld.setDrawableByLayerId(android.R.id.progress, context.getResources().getDrawable(selectedResId));
            ratingBar.setProgressDrawableTiled(ld);
        }
    }

    /**
     * 设置TextView的RightCompoundDrawable
     *
     * @param textView
     * @param drawable
     */
    public static void setRightCompoundDrawable(@NonNull TextView textView, @Nullable Drawable drawable) {
        //noinspection ConstantConditions
        if (textView != null) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            textView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 获取View相对Parent中的(x, y)坐标。View应该是Parent的直接或嵌套子View。
     *
     * @param view
     * @param parent
     * @return 如果View在Parent中，则返回数组；否则返回null
     */
    @Nullable
    public static int[] getRelativeLocation(View view, View parent) {
        if (view != null && parent != null) {
            int[] location = new int[]{0, 0};
            location[0] += view.getX();
            location[1] += view.getY();
            while (true) {
                final ViewParent viewParent = view.getParent();
                if (viewParent == parent) {
                    return location;
                }
                if (viewParent instanceof View) {
                    view = (View) viewParent;
                    location[0] += view.getX();
                    location[1] += view.getY();
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public static Drawable getDrawable(Context context, @DrawableRes int id) {
        if (context == null || id == 0) {
            return null;
        }
        try {
            Drawable drawable = context.getResources().getDrawable(id);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            return drawable;
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }

    public static void getHighlightSpannableString(final TextView textView, String content, String highlightDesc, final int highlightColor, final boolean isUnderLine, final boolean isBold) {
        SpannableString spStr = new SpannableString(content);

        spStr.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(highlightColor);
                ds.setUnderlineText(isUnderLine);
                ds.setFakeBoldText(isBold);
            }

            @Override
            public void onClick(@NonNull View widget) {
            }
        }, content.indexOf(highlightDesc), content.indexOf(highlightDesc) + highlightDesc.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spStr);
    }

    public static void setTextBold(TextView textView, boolean bold) {
        if (textView != null) {
            textView.setTypeface(bold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        }
    }

    /**
     * 为textview设置text,若text为空,则textview置为gone
     *
     * @param tv
     */
    public static void setTextAndFitVisibility(TextView tv, CharSequence text) {
        if (tv == null)
            return;
        if (TextUtils.isEmpty(text)) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }

    /**
     * 设置字体加粗
     *
     * @param isBold
     */
    public static void setTextViewBold(TextView tv, boolean isBold) {
        if (tv == null)
            return;
        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(isBold);
    }

    /**
     * 通过targetChild找到directChild
     * View嵌套关系：root --> direct --> xxx --> target
     *
     * @param root
     * @param target
     */
    public static View findDirectView(View root, View target) {
        return findDirectView(root, target, 10);
    }

    /**
     * 通过targetChild找到directChild
     * View嵌套关系：root --> direct --> xxx --> target
     *
     * @param root
     * @param target
     */
    public static View findDirectView(View root, View target, int maxIteration) {
        if (root == null || target == null || maxIteration <= 0) {
            return null;
        }
        View view = target;
        for (int i = 0; i < maxIteration; i++) { // 最多循环十次
            ViewParent viewParent = view.getParent();
            if (viewParent == root) {
                return view;
            }
            if (!(viewParent instanceof View)) {
                return null;
            }
            view = (View) viewParent;
        }
        return null;
    }

    public static void setMargin(View view, int l, int t, int r, int b) {
        if (view != null) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) lp;
                if (l > 0) {
                    params.leftMargin = l;
                }
                if (t > 0) {
                    params.topMargin = t;
                }
                if (r > 0) {
                    params.rightMargin = r;
                }
                if (b > 0) {
                    params.bottomMargin = b;
                }
            }
        }
    }
}

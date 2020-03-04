package com.paincker.common.demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.paincker.common.R;
import com.paincker.common.demo.widget.adapter.DemoListAdapter;
import com.paincker.common.demo.widget.adapter.DemoRecyclerAdapter;
import com.paincker.common.utils.ui.ColorUtils;
import com.paincker.common.utils.ui.DisplayUtils;

import java.util.Collection;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 快速创建Demo展示用的View
 * <p>
 * toXXX(): 设置成特定的View
 * xxx(): 创建特定的View
 */
public class DemoViewCreator {

    public static final String LONG_TEXT = generateLongText(20);
    public static final String VERY_LONG_TEXT = generateLongText(26 * 5);
    public static final int DEMO_LIST_ITEM_COUNT = 30;

    public static String generateLongText(int lines) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < lines; i++) {
            b.append("Content ").append((char) ('A' + i % 26));
            if (i < lines - 1) {
                b.append('\n');
            }
        }
        return b.toString();
    }

    public static GridView grid(Context context) {
        GridView view = new GridView(context);
        view.setNumColumns(3);
        view.setHorizontalSpacing(8);
        view.setVerticalSpacing(8);
        view.setPadding(5, 5, 5, 5);
        return view;
    }

    public static TextView bigText(ViewGroup parent, String text) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TextView textView = (TextView) inflater.inflate(R.layout.text, parent, false);
        textView.setText(text);
        return toBigText(textView);
    }

    public static TextView bigText(Context context, String text) {
        TextView textView = new TextView(context);
        textView.setText(text);
        return toBigText(textView);
    }

    public static TextView mediumText(ViewGroup parent, String text) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TextView textView = (TextView) inflater.inflate(R.layout.text, parent, false);
        textView.setText(text);
        return toMediumText(textView);
    }

    public static TextView mediumText(Context context, String text) {
        TextView textView = new TextView(context);
        textView.setText(text);
        return toMediumText(textView);
    }

    public static TextView smallText(ViewGroup parent, String text) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TextView textView = (TextView) inflater.inflate(R.layout.text, parent, false);
        textView.setText(text);
        return toSmallText(textView);
    }

    public static TextView smallText(Context context, String text) {
        TextView textView = new TextView(context);
        textView.setText(text);
        return toSmallText(textView);
    }

    public static TextView toLargeText(TextView textView) {
        return toText(textView, 30, ColorUtils.getRandomColor(), 50, 50);
    }

    public static TextView toBigText(TextView textView) {
        return toText(textView, 25, ColorUtils.getRandomColor(), 20, 20);
    }

    public static TextView toMediumText(TextView textView) {
        return toText(textView, 20, ColorUtils.getRandomColor(), 15, 15);
    }

    public static TextView toSmallText(TextView textView) {
        return toText(textView, 15, ColorUtils.getRandomLightColor(), 10, 10);
    }

    public static TextView toText(TextView textView, int textSpSize, int bgColor, int xPadding, int yPadding) {
        xPadding = DisplayUtils.dp2px(textView.getContext(), xPadding);
        yPadding = DisplayUtils.dp2px(textView.getContext(), yPadding);
        textView.setPadding(xPadding, yPadding, xPadding, yPadding);
        textView.setBackgroundColor(bgColor);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#333333"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSpSize);
        return textView;
    }

    public static TextView longText(Context context) {
        return longText(context, LONG_TEXT);
    }

    public static TextView longText(Context context, int lines) {
        return longText(context, generateLongText(lines));
    }

    public static TextView longText(Context context, String text) {
        TextView textView = new TextView(context);
        toLongText(textView, text);
        return textView;
    }

    public static TextView toLongText(TextView textView) {
        return toLongText(textView, LONG_TEXT);
    }

    public static TextView toLongText(TextView textView, int lines) {
        return toLongText(textView, generateLongText(lines));
    }

    public static TextView toLongText(TextView textView, String text) {
        textView.setText(text);
        toBigText(textView);
        textView.setLineSpacing(0, 3);
        int padding = DisplayUtils.dp2px(textView.getContext(), 30);
        textView.setPadding(padding, padding, padding, padding);
        return textView;
    }

    public static <T extends ViewGroup> T addLongTextContent(T viewGroup, String text) {
        viewGroup.addView(longText(viewGroup.getContext(), text));
        return viewGroup;
    }

    public static <T extends ViewGroup> T addLongTextContent(T viewGroup) {
        return addLongTextContent(viewGroup, LONG_TEXT);
    }

    public static ScrollView longTextScrollView(Context context, String text) {
        return addLongTextContent(new ScrollView(context), text);
    }

    public static ScrollView longTextScrollView(Context context) {
        return longTextScrollView(context, LONG_TEXT);
    }

    public static ListView textListView(Context context) {
        return textListView(context, DEMO_LIST_ITEM_COUNT);
    }

    public static ListView textListView(Context context, int itemCount) {
        return toTextListView(new ListView(context), itemCount);
    }

    public static ListView toTextListView(ListView listView) {
        return toTextListView(listView, DEMO_LIST_ITEM_COUNT);
    }

    public static ListView toTextListView(ListView listView, int itemCount) {
        listView.setAdapter(new DemoListAdapter().addTextItem(listView.getContext(), itemCount));
        return listView;
    }

    public static RecyclerView textRecyclerView(Context context) {
        return textRecyclerView(context, DEMO_LIST_ITEM_COUNT);
    }

    public static RecyclerView textRecyclerView(Context context, int itemCount) {
        return toTextRecyclerView(new RecyclerView(context), itemCount);
    }

    public static <T extends RecyclerView> T toTextRecyclerView(T recyclerView) {
        return toTextRecyclerView(recyclerView, DEMO_LIST_ITEM_COUNT);
    }

    public static <T extends RecyclerView> T toTextRecyclerView(T recyclerView, int itemCount) {
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new DemoRecyclerAdapter().addTextItem(context, itemCount));
        return recyclerView;
    }

    public static View createActivityEntryView(Context context, Class[] classes) {
        final LinearLayout linearLayout = getLinearLayout(context);
        final ScrollView scrollView = getScrollView(context, linearLayout);
        for (Class cls : classes) {
            addEntryView(context, linearLayout, cls);
        }
        return scrollView;
    }

    public static View createActivityEntryView(Context context, Collection<Class> classes) {
        final LinearLayout linearLayout = getLinearLayout(context);
        final ScrollView scrollView = getScrollView(context, linearLayout);
        for (Class cls : classes) {
            addEntryView(context, linearLayout, cls);
        }
        return scrollView;
    }

    @NonNull
    private static ScrollView getScrollView(Context context, LinearLayout container) {
        final ScrollView scrollView = new ScrollView(context);
        final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scrollView.addView(container, params);
        return scrollView;
    }

    @NonNull
    private static LinearLayout getLinearLayout(Context context) {
        final LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        return container;
    }

    private static void addEntryView(final Context context, ViewGroup container, final Class cls) {
        if (cls == null) {
            View view = new View(context);
            view.setMinimumHeight(DisplayUtils.dp2px(context, 20));
            container.addView(view);
        } else {
            Button view = new Button(context);
            view.setText(cls.getSimpleName());
            view.setAllCaps(false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, cls));
                }
            });
            container.addView(view);
        }
    }
}

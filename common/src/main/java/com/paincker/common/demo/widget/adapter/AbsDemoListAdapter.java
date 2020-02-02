package com.paincker.common.demo.widget.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 *
 */
public abstract class AbsDemoListAdapter<T> extends BaseAdapter {

    @NonNull
    protected final ArrayList<T> mData = new ArrayList<>();

    public AbsDemoListAdapter() {
    }

    public AbsDemoListAdapter(@Nullable List<? extends T> data) {
        setData(data);
    }

    @NonNull
    public ArrayList<T> getData() {
        return mData;
    }

    @MainThread
    public void setData(@Nullable List<? extends T> data) {
        if (data != mData) {
            mData.clear();
            if (data != null && !data.isEmpty()) {
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }
    }

    @MainThread
    public void addData(@Nullable List<? extends T> data) {
        if (data != null && !data.isEmpty()) {
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    @MainThread
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected void onBindViewHolder(DemoViewHolder<T> holder, int position) {
        holder.setData(getItem(position), position);
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DemoViewHolder<T> holder;
        if (convertView == null) {
            holder = onCreateViewHolder(parent, getItemViewType(position));
            convertView = holder.mRoot;
            convertView.setTag(holder);
        } else {
            holder = (DemoViewHolder<T>) convertView.getTag();
        }
        onBindViewHolder(holder, position);
        return convertView;
    }

    @NonNull
    protected abstract DemoViewHolder<T> onCreateViewHolder(View parent, int itemViewType);
}

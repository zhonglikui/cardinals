package com.zhong.cardinals.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2015/10/30.
 * BaseAdapter的父类
 */
public abstract class BaseListAdapter<T> extends BaseAdapter implements ParentAdapter<T> {

    public static final int INVALID_ITEM_VIEW_ID = -1;
    private AppCompatActivity mActivity;
    private List<T> mList;
    private int itemViewId;


    public BaseListAdapter(AppCompatActivity activity, int itemViewId) {
        this.mActivity = activity;
        this.itemViewId = itemViewId;
        mList = new ArrayList<>();
    }

    @Override
    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public List<T> getListAll() {
        return mList;
    }

    @Override
    public void addList(List<T> list) {
        if (list != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public void addItem(T item) {
        if (item != null) {
            mList.add(item);
            notifyDataSetChanged();
        }
    }

    @Override
    public void addItem(int index, T item) {
        if (item != null && index >= 0) {
            mList.add(index, item);
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeItem(T item) {
        if (item != null && !mList.isEmpty()) {
            mList.remove(item);
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeItem(int index) {
        if (index >= 0 && !mList.isEmpty()) {
            mList.remove(index);
            notifyDataSetChanged();
        }
    }

    @Override
    public void clear() {
        if (!mList.isEmpty()) {
            mList.clear();
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (itemViewId != INVALID_ITEM_VIEW_ID) {

            ListViewHolder holder = ListViewHolder.get(mActivity, convertView, itemViewId);
            getConverView(position, holder, getItem(position));
            return holder.getConvertView();
        } else {
            return null;
        }
    }

    protected abstract void getConverView(int position, ListViewHolder holder, T item);
}

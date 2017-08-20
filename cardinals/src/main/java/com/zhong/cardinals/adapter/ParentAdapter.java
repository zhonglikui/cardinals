package com.zhong.cardinals.adapter;

import android.app.Activity;

import java.util.List;

/**
 * 此接口提取了ListViewAdapter和RecycleViewAdapter共有的方法
 * Created by Mr.zhong on 2017/8/20.
 */

interface ParentAdapter<T> {
    Activity getActivity();

    List<T> getListAll();

    void addList(List<T> list);

    void addItem(T item);

    void addItem(int index, T item);

    void removeItem(T item);

    void removeItem(int index);

    void clear();
}

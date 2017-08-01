package com.zhong.cardinals.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhong on 2015/10/30.
 * BaseAdapter的父类
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    public static final int INVALID_ITEM_VIEW_ID = -1;
    private final int INVALID_INDEX_VALUES = -1;
    private Activity mActivity;
    private List<T> mList;
    private int itemViewId;


    public BaseListAdapter(Activity activity, int itemViewId) {
        this.mActivity = activity;
        this.itemViewId = itemViewId;
    }

    /**
     * 获取调用当前adapter的Context
     *
     * @return Activity
     */
    protected Activity getActivity() {
        return mActivity;
    }

    /**
     * 向ListView中添加数据
     *
     * @param list List数据
     */
    public void addListData(List<T> list) {
        if (list != null) {
            if (mList != null) {
                mList.addAll(list);
            } else {
                mList = list;
            }

            notifyDataSetChanged();
        }

    }

    /**
     * 向list中添加item
     */
    public void addItem(T obj) {
        if (obj != null) {
            mList.add(obj);
            notifyDataSetChanged();
        }
    }

    /**
     * 向list中的指定位置添加item
     *
     * @param index 索引位置
     * @param obj  单个数据对象
     */
    public void addItem(int index, T obj) {
        if (obj != null && index != INVALID_INDEX_VALUES && mList != null) {
            mList.add(index, obj);
            notifyDataSetChanged();
        }

    }

    /**
     * 删除list中的item
     *
     * @param obj 待删除的数据对象
     */
    public void deleteItem(T obj) {
        if (obj != null && !mList.isEmpty()) {
            mList.remove(obj);
            notifyDataSetChanged();
        }

    }

    /**
     * 删除list中指定位置的item
     *
     * @param index int型的索引值
     */
    public void deleteItem(int index) {
        if (index != INVALID_INDEX_VALUES && !mList.isEmpty()) {
            mList.remove(index);
            notifyDataSetChanged();
        }
    }

    /**
     * 清空list
     */
    public void clear() {
        if (mList != null) {
            mList.clear();
            notifyDataSetChanged();

        }
    }

    /**
     * @return 返回Adapter中的List数据
     */
    public List<T> getAll() {

        return mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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

            ViewHolder holder = ViewHolder.get(mActivity, convertView, itemViewId);
            getConverView(position, holder, getItem(position));
            return holder.getConvertView();
        } else {
            return null;
        }
    }

    protected abstract void getConverView(int position, ViewHolder holder, T item);
}

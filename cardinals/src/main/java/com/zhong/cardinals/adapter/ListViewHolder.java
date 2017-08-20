package com.zhong.cardinals.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by zhong on 2015/10/12.
 * BaseListAdapter的辅助工具类
 */
class ListViewHolder {
    private final View mConvertView;
    private final SparseArray<View> mViews;

    /**
     * 初始化ViewHolder
     *
     * @param context       Context
     * @param convertViewId item的layout
     */
    private ListViewHolder(Context context, int convertViewId) {
        mViews = new SparseArray<>();
        mConvertView = View.inflate(context, convertViewId, null);
        mConvertView.setTag(this);
    }

    /**
     * 获取ViewHolder对象
     *
     * @param convertView layout
     * @return ViewHolder
     */
    static ListViewHolder get(Context context, View convertView, int converViewId) {
        if (convertView == null) {
            return new ListViewHolder(context, converViewId);
        } else {
            return (ListViewHolder) convertView.getTag();
        }
    }

    /**
     * 通过控件的id获取相对应的控件
     *
     * @param viewId 需要查找的View的id
     * @param <T>    View的类型
     * @return 查找的结果
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * @return 返回convertView
     */
    View getConvertView() {
        return mConvertView;
    }

}

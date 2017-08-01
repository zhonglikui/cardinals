package com.zhong.cardinals.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhong on 2017/4/1.
 */

public class RecycleViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    public RecycleViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public static RecycleViewHolder get(Context context, ViewGroup parent, int layoutId) {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        RecycleViewHolder holder = new RecycleViewHolder(context, itemView, parent);
        return holder;
    }

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
    public View getConvertView() {
        return mConvertView;
    }
}

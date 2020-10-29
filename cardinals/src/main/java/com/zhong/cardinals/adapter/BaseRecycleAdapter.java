package com.zhong.cardinals.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2017/4/1.
 * RecycleView的Adapter使用的父类
 */

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> implements ParentAdapter<T> {
    protected onItemClickListener onItemClickListener;
    protected onItemLongClickListener onItemLongClickListener;
    private AppCompatActivity mActivity;
    private int mLayoutId;
    private List<T> mList;

    public BaseRecycleAdapter(AppCompatActivity activity, int layoutId) {
        this.mActivity = activity;
        this.mLayoutId = layoutId;
        this.mList = new ArrayList<>();
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
            notifyItemInserted(mList.size() - 1);
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
            notifyItemRemoved(index);
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
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecycleViewHolder.get(mActivity, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(final RecycleViewHolder holder, final int position) {
        convert(holder.getLayoutPosition(), holder, mList.get(position));
        //item点击事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(v, position);
                }
            });

        }
        //item长按事件
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemLongClickListener.onItemLongClick(v, position);
                    return false;
                }
            });

        }
    }

    public abstract void convert(int position, RecycleViewHolder holder, T item);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public T getItem(int position) {
        return mList.get(position);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public void setOnItemLongClickListener(onItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface onItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}

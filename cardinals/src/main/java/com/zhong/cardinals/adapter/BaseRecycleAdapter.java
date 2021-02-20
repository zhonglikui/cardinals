package com.zhong.cardinals.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2017/4/1.
 * RecycleView的Adapter使用的父类
 */

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> implements ParentAdapter<T> {
    private onItemClickListener onItemClickListener;
    private onItemLongClickListener onItemLongClickListener;
    private Activity mActivity;
    private int mLayoutId;
    private List<T> mList = new ArrayList<>();

    public BaseRecycleAdapter(Activity activity, int layoutId) {
        this.mActivity = activity;
        this.mLayoutId = layoutId;
    }

    @Override
    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public List<T> getList() {
        return mList;
    }

    @Override
    public void addList(List<T> list) {
        if (list != null && list.size() > 0) {
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
    public void insertItem(int index, T item) {
        if (item != null && index >= 0) {
            mList.add(index, item);
            notifyItemInserted(index);
        }
    }

    @Override
    public void removeItem(T item) {
        if (item != null && !mList.isEmpty() && mList.contains(item)) {
            int index = mList.indexOf(item);
            mList.remove(index);
            notifyItemRemoved(index);
        }

    }

    @Override
    public void removeItem(int index) {
        if (index >= 0 && !mList.isEmpty() && index < mList.size()) {
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

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RecycleViewHolder.get(mActivity, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleViewHolder holder, final int position) {
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

    protected abstract void convert(int position, RecycleViewHolder holder, T item);

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

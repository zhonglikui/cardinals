package com.zhong.cardinals.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2017/4/1.
 * RecycleView的Adapter使用的父类
 */

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {
    protected Activity mActivity;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected onItemClickListener onItemClickListener;
    protected onItemLongClickListener onItemLongClickListener;

    public BaseRecycleAdapter(Activity activity, int layoutId) {
        this.mActivity = activity;
        this.mLayoutId = layoutId;

        mInflater = LayoutInflater.from(activity);
        mDatas = new ArrayList<>();
    }

    /**
     * @return 返回Adapter中的List数据
     */
    public List<T> getAll() {

        return mDatas;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public void addList(List<T> list) {
        if (list != null) {

            mDatas.addAll(list);
           /* for (int i = 0; i < mDatas.size(); i++) {
                //notifyItemChanged(i);
                notifyItemInserted(i);
            }*/
            notifyDataSetChanged();

        }

    }

    public void addItem(T item) {
        if (item != null) {
            mDatas.add(item);
            notifyItemInserted(mDatas.size() - 1);
        }
    }

    /**
     * 向list中的指定位置添加item
     *
     * @param index 索引值
     * @param obj  对象
     */
    public void addItem(int index, T obj) {
        if (obj != null && mDatas != null) {
            mDatas.add(index, obj);
            notifyDataSetChanged();
        }

    }

    /**
     * 删除list中的item
     *
     * @param obj 待删除的索引值
     */
    public void deleteItem(T obj) {
        if (obj != null && !mDatas.isEmpty()) {
            mDatas.remove(obj);
            notifyDataSetChanged();
        }

    }

    /**
     * 删除list中指定位置的item
     *
     * @param index 索引值
     */
    public void deleteItem(int index) {
        if (!mDatas.isEmpty()) {
            mDatas.remove(index);
            notifyItemRemoved(index);
            notifyItemRangeRemoved(index, mDatas.size());
        }
    }

    /**
     * 清空list
     */
    public void clear() {
        if (mDatas != null) {
            mDatas.clear();
            notifyDataSetChanged();

        }
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleViewHolder viewHolder = RecycleViewHolder.get(mActivity, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecycleViewHolder holder, final int position) {
        convert(holder.getLayoutPosition(), holder, mDatas.get(position));
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

    public abstract void convert(int position, RecycleViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public T getItem(int position) {
        return mDatas.get(position);
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

package com.zhong.cardinals.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.zhong.cardinals.R;


/**
 * Created by zhong on 2017/3/23.
 */
public class LoadListView extends ListView implements AbsListView.OnScrollListener {
    View footer;// 底部布局；
    int totalItemCount;// 总数量；
    int lastVisibleItem;// 最后一个可见的item；
    boolean isLoading;// 正在加载；
    onLoadListerner iLoadListener;
    boolean isLoadFinish;

    public LoadListView(Context context) {
        this(context, null);

    }

    public LoadListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 添加底部加载提示布局到listview
     *
     * @param context Context
     */
    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.footer_layout, null);
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.d("onScrollStateChanged", "totalItemCount = " + totalItemCount +
                " lastVisibleItem = " + lastVisibleItem + " scrollState = " + scrollState);
        if (totalItemCount + getHeaderViewsCount() + getFooterViewsCount() == lastVisibleItem && scrollState == SCROLL_STATE_IDLE) {

            if (!isLoading) {

                isLoading = true;
                footer.findViewById(R.id.load_layout).setVisibility(View.VISIBLE);
                // 加载更多

                if (iLoadListener != null && !isLoadFinish) {

                    iLoadListener.onLoad();
                } else {
                    //所有内容加载完毕
                    loadComplete(true);
                }

            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount - getFooterViewsCount() - getHeaderViewsCount();
        Log.d("onScroll", "first=" + firstVisibleItem + ";visible=" + visibleItemCount + ";count=" + totalItemCount + ";lastVisible=" + lastVisibleItem + ";footerCount=" + getFooterViewsCount());
    }

    /**
     * 加载完毕
     */
    public void loadComplete(boolean isFinish) {
        isLoading = false;
        isLoadFinish = isFinish;
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
    }

    public void setOnLoadListener(onLoadListerner listerner) {
        this.iLoadListener = listerner;
    }

    //加载更多
    public interface onLoadListerner {
        void onLoad();
    }
}

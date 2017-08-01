package com.zhong.cardinals.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zhong on 2017/4/10.
 * 不需要左右滑动的ViewPager
 */

public class NoScrollableViewPager extends ViewPager {
    private boolean isScrollable = false;


    public NoScrollableViewPager(Context context) {
        super(context);
    }

    public NoScrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return isScrollable && super.onTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return isScrollable && super.onInterceptTouchEvent(ev);


    }
}

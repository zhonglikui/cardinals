package com.zhong.cardinals.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by zhong on 2017/3/10.
 */

public abstract class BasePresenter<T> {
    protected Reference<T> viewRef;//view接口类型的弱引用

    public void attachView(T view) {
        viewRef = new WeakReference<T>(view);//建立关联
    }

    protected T getView() {
        return viewRef.get();
    }

    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}

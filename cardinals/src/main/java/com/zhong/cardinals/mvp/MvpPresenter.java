package com.zhong.cardinals.mvp;

/**
 * Created by Mr.zhong on 2017/9/19.
 */

public abstract class MvpPresenter<V extends MvpView> {
    private V view;

    public V getView() {
        return view;
    }

    //绑定
    void attachView(V view) {
        this.view = view;
    }

    //解绑
    void detachView() {
        view = null;
    }

}

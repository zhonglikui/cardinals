package com.zhong.cardinals.base;

/**
 * Created by zhong on 2017/3/10.
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> /*extends BaseEmptyFragment*/ {
    protected T presenter;

  /*  @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter();
    }

    protected abstract T createPresenter();
    //  protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }*/
}

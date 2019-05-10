package com.zhong.cardinals.mvp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.zhong.cardinals.R;


/**
 * Created by zhong on 2018/1/15.
 */

public abstract class MvpDialog2Fragment<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatDialogFragment implements MvpView {
    private P presenter;
    private View view;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Dialog dialog = getDialog();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        if (lp != null) {

            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setWindowAnimations(R.style.DialogBottom);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = createView();
        }
        if (view == null) {
            throw new NullPointerException("view 不能为空");
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter 不能为空");
        }
        presenter.attachView((V) this);
    }


    protected abstract View createView();

    protected abstract P createPresenter();

    protected P getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }


}

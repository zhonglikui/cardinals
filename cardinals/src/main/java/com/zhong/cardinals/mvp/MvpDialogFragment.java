package com.zhong.cardinals.mvp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.zhong.cardinals.R;


/**
 * Created by zhong on 2018/1/15.
 */

public abstract class MvpDialogFragment<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatDialogFragment implements MvpView {
    private P presenter;
    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(null);
        AlertDialog dialog = builder.show();
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
        if (view == null) {
            view = createView();
        }
        if (view == null) {
            throw new NullPointerException("view 不能为空");
        }
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter 不能为空");
        }


        dialog.setContentView(view);
        presenter.attachView((V) this);
        return dialog;
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

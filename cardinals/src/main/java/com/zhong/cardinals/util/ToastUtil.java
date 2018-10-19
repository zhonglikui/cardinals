package com.zhong.cardinals.util;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.zhong.cardinals.App;


/**
 * Created by zhong on 2016/7/26.
 */
public class ToastUtil {
    private static Toast toast;

    public static void showShort(CharSequence charSequence) {
        //Toast.makeText(DevLib.getApp(), charSequence, Toast.LENGTH_SHORT).show();
        showToast(charSequence, Toast.LENGTH_SHORT);
    }

    public static void showShort(@StringRes int resId) {
        //Toast.makeText(App.getInstance().getContext(), App.getInstance().getString(resId), Toast.LENGTH_SHORT).show();
        showToast(resId, Toast.LENGTH_SHORT);
    }

    public static void showLong(CharSequence charSequence) {
        //Toast.makeText(App.getInstance().getContext(), charSequence, Toast.LENGTH_LONG).show();
        showToast(charSequence, Toast.LENGTH_LONG);
    }

    public static void showLong(@StringRes int resId) {
        //Toast.makeText(App.getInstance().getContext(), App.getInstance().getString(resId), Toast.LENGTH_LONG).show();
        showToast(resId, Toast.LENGTH_LONG);
    }

    private static void showToast(CharSequence charSequence, int duration) {
        if (toast == null) {
            toast = Toast.makeText(App.getInstance().getContext(), charSequence, duration);
        } else {
            toast.setText(charSequence);
        }
        toast.show();
    }

    private static void showToast(@StringRes int resId, int duration) {
        if (toast == null) {
            toast = Toast.makeText(App.getInstance().getContext(), resId, duration);
        } else {
            toast.setText(resId);
        }
        toast.show();
    }
}

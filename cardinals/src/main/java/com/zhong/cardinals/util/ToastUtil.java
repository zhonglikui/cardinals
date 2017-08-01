package com.zhong.cardinals.util;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.zhong.cardinals.App;


/**
 * Created by zhong on 2016/7/26.
 */
public class ToastUtil {
    public static void showShort(CharSequence charSequence) {
        Toast.makeText(App.getInstance().getContext(), charSequence, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(@StringRes int resId) {
        Toast.makeText(App.getInstance().getContext(), App.getInstance().getString(resId), Toast.LENGTH_SHORT).show();
    }

    public static void showLong(CharSequence charSequence) {
        Toast.makeText(App.getInstance().getContext(), charSequence, Toast.LENGTH_LONG).show();
    }

    public static void showLong(@StringRes int resId) {
        Toast.makeText(App.getInstance().getContext(), App.getInstance().getString(resId), Toast.LENGTH_LONG).show();
    }
}

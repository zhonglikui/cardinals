package com.zhong.cardinals.manager;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by zhong on 2017/2/25.
 * 控制键盘显示和隐藏的工具类
 */

public class KeyboardManager {
    private KeyboardManager() {
    }

    /**
     * 显示软键盘
     *
     * @param view 用来绑定显示键盘的View
     */
    public static void showKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);

    }

    /**
     * 隐藏软键盘
     *
     * @param view 用来绑定隐藏键盘的View
     */
    public static void hideKeyboard(Context context, View view) {
        if (null != context && null != view) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

package com.zhong.cardinals.util;

import android.text.TextPaint;
import android.widget.TextView;

/**
 * Created by zhong on 2018/3/21.
 */

public class Typeface {
    /**
     * 字体加粗
     *
     * @param textView
     */
    public static void bold(TextView textView) {
        if (textView != null) {
            TextPaint paint = textView.getPaint();
            paint.setFakeBoldText(true);
        }
    }
}

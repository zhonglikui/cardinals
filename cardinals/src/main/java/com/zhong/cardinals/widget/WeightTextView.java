package com.zhong.cardinals.widget;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zhong on 2017/11/27.
 */

public class WeightTextView extends android.support.v7.widget.AppCompatTextView {
    private Context context;
    private int viewWidth;
    private int viewHight;
    private int oneWidth;
    private int oneHight;
    private int num;
    private Paint p;
    private String str;
    private String[] texts;
    private int textSize = 15;

    public WeightTextView(Context context) {
        this(context, null);
    }

    public WeightTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeightTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewWidth = getWidth();
        viewHight = getHeight();
    }
}

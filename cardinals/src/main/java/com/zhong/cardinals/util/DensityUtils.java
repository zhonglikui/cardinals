package com.zhong.cardinals.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by zhong on 2017/2/9.
 * 屏幕显示相关单位转换的工具类
 */

public class DensityUtils {

    /**
     * dp转px
     *
     * @param context Context对象
     * @param dpVal 需要被转化的值单位dp
     * @return 转化后的值单位px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources()
                .getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context Context对象
     * @param spVal  需要被转化的值单位sp
     * @return 转化后的值单位px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources()
                .getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context Context对象
     * @param pxVal 需要被转化的值，单位px
     * @return 转化后的值，单位dp
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context Context对象
     * @param pxVal 需要被转化的值，单位px
     * @return 转化后的值，单位sp
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}

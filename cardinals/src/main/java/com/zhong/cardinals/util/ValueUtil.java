package com.zhong.cardinals.util;

/**
 * Created by zhong on 2018/3/6.
 */

public class ValueUtil {
    /**
     * 能够被整除
     */
    public static boolean isDivisible(double value1, double value2) {
        int v1 = (int) (value1 * 100);
        int v2 = (int) (value2 * 100);
        // Logger.d("v1="+v1+" ; v2="+v2+" ; result="+(v1%v2));
        return v1 % v2 == 0;
    }
}

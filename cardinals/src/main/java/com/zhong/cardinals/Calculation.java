package com.zhong.cardinals;

import java.math.BigDecimal;

/**
 * @author zhong
 * 由于浮点数计算会有精度问题，所以使用此工具类来减少误差
 */
public class Calculation {
    /**
     * 乘法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static float multiply(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.multiply(b2).floatValue();
    }

    public static int multiplyToInt(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.multiply(b2).intValue();
    }

    public static int multiplyToUpInt(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.multiply(b2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static long multiplyToLong(long l1, long l2) {
        BigDecimal b1 = new BigDecimal(Long.toString(l1));
        BigDecimal b2 = new BigDecimal(Long.toString(l2));
        return b1.multiply(b2).longValue();
    }


    /**
     * 除法取整数
     *
     * @param v1
     * @param v2
     * @return
     */
    public static int divToInt(float v1, float v2) {
        if (v1 == 0 || v2 == 0) {
            return 0;
        }
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.divideToIntegralValue(b2).intValue();
    }

    /**
     * 除法取小数
     *
     * @param v1
     * @param v2
     * @return
     */
    public static float divToFloat(float v1, float v2) {
        return divToFloat(v1, v2, 2);
    }

    /**
     * 除法取小数
     *
     * @param v1
     * @param v2
     * @param scale 小数的位数
     * @return
     */
    public static float divToFloat(float v1, float v2, int scale) {
        if (v1 == 0 || v2 == 0) {
            return 0;
        }
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 除法取float类型的数据
     *
     * @param l1
     * @param l2
     * @return
     */
    public static long divToLong(long l1, long l2) {
        if (l1 == 0 || l2 == 0) {
            return 0;
        }
        BigDecimal bd1 = new BigDecimal(l1);
        BigDecimal bd2 = new BigDecimal(l2);
        return bd1.divide(bd2).longValue();
    }

    /**
     * 减法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static float sub(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.subtract(b2).floatValue();
    }

    public static float add(float f1, float f2) {
        BigDecimal v1 = new BigDecimal(Float.toString(f1));
        BigDecimal v2 = new BigDecimal(Float.toString(f2));
        return v1.add(v2).floatValue();
    }


}

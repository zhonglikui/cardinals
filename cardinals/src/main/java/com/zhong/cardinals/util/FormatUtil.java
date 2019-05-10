package com.zhong.cardinals.util;

import java.text.DecimalFormat;

/**
 * Created by zhong on 2017/11/22.
 */

public class FormatUtil {
    public static final String PATTERN_0 = "#";
    public static final String PATTERN_1 = "#.#";
    public static final String PATTERN_2 = "#.##";

    public static String formatDouble(String pattern, double value) {
        return new DecimalFormat(pattern).format(value);
    }

    public static String formatFloat(String pattern, float value) {
        return new DecimalFormat(pattern).format(value);
    }

    public static String format(String pattern, double value) {
        String fDouble = formatDouble(pattern, value);
        return ReportUtil.subZeroAndDot(fDouble);
    }
}

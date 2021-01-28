package com.zhong.cardinals.util;

/**
 * Created by zhong on 2017/11/22.
 */

public class FormatUtil {
    public static final String D0 = "%.0f";
    public static final String D1 = "%.1f";
    public static final String D2 = "%.2f";
    public static final String D3 = "%.3f";
    public static final String D4 = "%.3f";
    public static final String PN0 = "%+.0f";
    public static final String PN1 = "%+.1f";
    public static final String PN2 = "%+.2f";
    public static final String PN3 = "%+.3f";
    public static final String PN4 = "%+.4f";

    /**
     * @param num    数字
     * @param format 格式化模板
     * @return 去掉小数点后面0的字符串
     */
    public static String withoutZero(float num, String format) {
        String outPut = String.format(format, num);
        return withoutZero(outPut);
    }

    /**
     *
     * @param ouput
     * @return 去掉小数点后面0的字符串
     */
    public static String withoutZero(String ouput) {
        while (ouput.length() > 0) {
            if (ouput.endsWith("0") && ouput.contains(".")) {
                ouput = ouput.substring(0, ouput.length() - 1);
            } else if (ouput.endsWith(".")) {
                ouput = ouput.substring(0, ouput.length() - 1);
            } else {
                break;
            }
        }
        return ouput;
    }
}

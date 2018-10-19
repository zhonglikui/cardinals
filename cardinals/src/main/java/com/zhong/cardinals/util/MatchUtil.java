package com.zhong.cardinals.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by Mr.zhong on 2017/8/25.
 */

public class MatchUtil {
    /**
     * 验证手机号（简单）
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    /**
     * 验证手机号（精确）
     * <p>
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
     * <p>联通：130、131、132、145、155、156、175、176、185、186、166
     * <p>电信：133、153、173、177、180、181、189、199
     * <p>全球星：1349
     * <p>虚拟运营商：170
     */
    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,3,5-8])|(18[0-9])|(19[8,9])|(147))\\d{8}$";
    /**
     * 验证座机号,正确格式：xxx/xxxx-xxxxxxx/xxxxxxxx/
     */
    public static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    /**
     * 验证邮箱
     */
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 验证url
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?";
    /**
     * 验证汉字
     */
    public static final String REGEX_CHZ = "^[\\u4e00-\\u9fa5]+$";
    /**
     * 验证用户名,取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
     */
    public static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    /**
     * 验证IP地址
     */
    public static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)"; //If u want more please visit http://toutiao.com/i6231678548520731137/ /**

    private MatchUtil() {
    }

    /**
     * @param regexType 正则表达式字符串类型
     * @param string    要匹配的类型
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean isMatch(String string, String regexType) {
        return !TextUtils.isEmpty(string) && Pattern.matches(regexType, string);
    }
}

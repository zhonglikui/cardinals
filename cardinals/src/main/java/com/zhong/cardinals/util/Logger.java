package com.zhong.cardinals.util;

import android.util.Log;

import com.zhong.cardinals.Cardinals;


/**
 * Created by zhong on 2017/3/10.
 * Android中Log显示的工具类
 */
public class Logger {


    private static boolean isShowLog;
    private static String mTag;

    static {
        isShowLog = Cardinals.getInstance().isShowLog();
        mTag = Cardinals.getInstance().getDefaultLogTag();
    }


    public static void i(String msg) {
        if (isShowLog) {
            Log.i(mTag, msg);
        }
    }

    public static void d(String msg) {
        if (isShowLog) {
            Log.d(mTag, msg);
        }
    }

    public static void e(String msg) {
        if (isShowLog) {
            Log.e(mTag, msg);
        }
    }


    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isShowLog) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isShowLog) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isShowLog) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg, Throwable throwable) {
        if (isShowLog) {
            Log.e(mTag, msg, throwable);
        }
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (isShowLog) {
            Log.e(tag, msg, throwable);
        }
    }

}

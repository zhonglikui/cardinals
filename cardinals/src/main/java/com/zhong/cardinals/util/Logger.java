package com.zhong.cardinals.util;

import android.util.Log;

import com.zhong.cardinals.App;


/**
 * Created by zhong on 2017/3/10.
 * Android中Log显示的工具类
 */
public class Logger {

    private static final String TAG = "cardinals";
    public static boolean isDebug = App.getInstance().isDebug();

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }


    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg, Throwable throwable) {
        if (isDebug) {
            Log.e(TAG, msg, throwable);
        }
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (isDebug) {
            Log.e(tag, msg, throwable);
        }
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        Logger.isDebug = isDebug;
    }
}

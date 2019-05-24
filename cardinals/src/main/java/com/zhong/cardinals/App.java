package com.zhong.cardinals;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by zhong on 2017/3/10.
 */

public class App {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private Context mContext;
    private boolean isDebug;


    private App() {
    }

    public static App getInstance() {
        return Single.instance;
    }

    protected void init(boolean isDebug, Application application) {
        this.mContext = application.getApplicationContext();
        this.isDebug = isDebug;
    }


    public boolean isDebug() {
        return isDebug;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public Context getContext() {
        return mContext;
    }


    public Resources getResources() {
        return getContext().getResources();
    }

    public String getString(@StringRes int stringId) {
        return getResources().getString(stringId);
    }

    public Drawable getDrawable(@DrawableRes int resId) {
        return getResources().getDrawable(resId);
    }

    public int getColor(@ColorRes int colorId) {
        return getResources().getColor(colorId);
    }

    private static class Single {
        private final static App instance = new App();
    }

}

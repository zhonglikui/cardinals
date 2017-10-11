package com.zhong.cardinals;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by zhong on 2017/3/10.
 */

public class App {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean isDebug;
    private Context mContext;

    private App() {
    }

    public static App getInstance() {
        return Single.instance;
    }

    public void init(Context context) {
        this.mContext = context;
        this.isDebug = true;
    }
    public void init(Context context, boolean isDebug) {
        this.mContext = context;
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

    private static class Single {
        private final static App instance = new App();
    }
}

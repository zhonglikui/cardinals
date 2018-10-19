package com.zhong.cardinals;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.zhong.cardinals.net.NetInterface;

/**
 * Created by zhong on 2017/3/10.
 */

public class App {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean isDebug;
    private Context mContext;
    private NetInterface netInterface;

    private App() {
    }

    public static App getInstance() {
        return Single.instance;
    }

    public void init(Context context) {
        this.init(context, true);
    }

    public void init(Context context, boolean isDebug) {
        this.init(context, isDebug, null);
    }

    public void init(Context context, boolean isDebug, NetInterface netInterface) {
        this.mContext = context;
        this.isDebug = isDebug;
        this.netInterface = netInterface;
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

    public NetInterface getNetInterface() {
        return netInterface;
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

    public interface Net {
        void callback();
    }
}

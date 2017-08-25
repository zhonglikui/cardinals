package com.zhong.cardinals.sample;


import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.zhong.cardinals.App;
import com.zhong.cardinals.net.NetWorkClient;

/**
 * Created by zhong on 2017/8/2.
 */

public class BaseApplication extends MultiDexApplication {
    private static final String HOST = "http://likui.me";

    @Override
    public void onCreate() {
        super.onCreate();
        App.getInstance().init(this, true);
        NetWorkClient.init(HOST);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }
}

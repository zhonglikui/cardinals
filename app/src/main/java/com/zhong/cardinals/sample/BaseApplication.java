package com.zhong.cardinals.sample;

import android.app.Application;

import com.zhong.cardinals.App;
import com.zhong.cardinals.net.NetWorkClient;

/**
 * Created by zhong on 2017/8/2.
 */

public class BaseApplication extends Application {
    private static final String HOST = "http://likui.me";

    @Override
    public void onCreate() {
        super.onCreate();
        App.getInstance().init(this, true);
        NetWorkClient.init(HOST);
    }
}

package com.zhong.cardinals.sample;


import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;

import com.zhong.cardinals.Builder;
import com.zhong.cardinals.Cardinals;
import com.zhong.cardinals.net.NetInterface;

/**
 * Created by zhong on 2017/8/2.
 */

public class BaseApplication extends MultiDexApplication {
    private static final String HOST = "http://host/";

    @Override
    public void onCreate() {
        super.onCreate();
        Builder builder = new Builder();
        builder.setDefaultLogTag("zhong")
                .setNoProxy(false)
                .setShowLog(true)
                .setHost(HOST)
                .setDebug(true)
                .setNetInterface(new NetInterface() {
                    @Override
                    public void onResult(int code) {
                        //收到系统自定义的http状态码
                    }
                });

        Cardinals.init(builder, this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }
}

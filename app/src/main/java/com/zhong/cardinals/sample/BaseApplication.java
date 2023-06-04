package com.zhong.cardinals.sample;


import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDexApplication;

import com.zhong.cardinals.Cardinals;
import com.zhong.cardinals.ConfigBuilder;
import com.zhong.cardinals.net.NetInterface;

/**
 * Created by zhong on 2017/8/2.
 */

public class BaseApplication extends MultiDexApplication {
    private static final String HOST = "http://host/path?param";

    @Override
    public void onCreate() {
        super.onCreate();
        ConfigBuilder builder = new ConfigBuilder();
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
        if (!BuildConfig.DEBUG) {
            MyCrashHandler mycrashHandler = new MyCrashHandler();

            Thread.setDefaultUncaughtExceptionHandler(mycrashHandler);
        }
    }
    public class MyCrashHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            if (e != null) {
//                LogUtils.e("-----------------------------------------" + e.getMessage());
            }

//            UMCrash.generateCustomLog(e, "CustomException");


        }
    }
}

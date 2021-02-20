package com.zhong.cardinals;

import android.app.Application;

import com.zhong.cardinals.net.NetInterface;
import com.zhong.cardinals.net.NetWorkClient;

public class Cardinals {
    private static boolean isDebug;
    private static boolean isShowLog;
    private static boolean isNoProxy;
    private static String defaultLogTag;
    private static String host;
    private static NetInterface netInterface;

    public static void init(ConfigBuilder builder, Application application) {
        isDebug = builder.isDebug;
        isShowLog = builder.isShowLog;
        isNoProxy = builder.isNoProxy;
        defaultLogTag = builder.defaultLogTag;
        netInterface = builder.netInterface;
        host = builder.host;
        NetWorkClient.init(host);
        App.getInstance().init(isDebug, application);
    }

    private Cardinals() {

    }


    private static class Single {
        private final static Cardinals instance = new Cardinals();
    }

    public static Cardinals getInstance() {
        return Cardinals.Single.instance;
    }


    public boolean isShowLog() {
        return isShowLog && isDebug;
    }

    public boolean isNoProxy() {
        return isNoProxy;
    }

    public NetInterface getNetInterface() {
        return netInterface;
    }

    public String getDefaultLogTag() {
        return defaultLogTag;
    }


}

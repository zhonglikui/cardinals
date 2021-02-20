package com.zhong.cardinals;

import com.zhong.cardinals.net.NetInterface;

public class ConfigBuilder {

    private static final String DEFAULT_TAG = "cardinals";
    protected boolean isDebug;
    protected boolean isShowLog;
    protected boolean isNoProxy;
    protected String defaultLogTag;
    protected String host;
    protected NetInterface netInterface;

    public ConfigBuilder() {
        isDebug = true;
        isShowLog = true;
        isNoProxy = false;
        defaultLogTag = DEFAULT_TAG;
    }


    public ConfigBuilder setNetInterface(NetInterface netInterface) {
        this.netInterface = netInterface;
        return this;
    }

    public ConfigBuilder setDebug(boolean debug) {
        this.isDebug = debug;
        return this;
    }

    public ConfigBuilder setShowLog(boolean showLog) {
        this.isShowLog = showLog;
        return this;
    }

    public ConfigBuilder setNoProxy(boolean noProxy) {
        this.isNoProxy = noProxy;
        return this;
    }

    public ConfigBuilder setDefaultLogTag(String defaultLogTag) {
        this.defaultLogTag = defaultLogTag;
        return this;
    }

    public ConfigBuilder setHost(String host) {
        this.host = host;
        return this;
    }
}

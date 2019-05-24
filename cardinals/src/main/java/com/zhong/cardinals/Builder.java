package com.zhong.cardinals;

import com.zhong.cardinals.net.NetInterface;

public class Builder {

    private static final String DEFAULT_TAG = "cardinals";
    protected boolean isDebug;
    protected boolean isShowLog;
    protected boolean isNoProxy;
    protected String defaultLogTag;
    protected String host;
    protected NetInterface netInterface;

    public Builder() {
        isDebug = true;
        isShowLog = true;
        isNoProxy = false;
        defaultLogTag = DEFAULT_TAG;
    }


    public Builder setNetInterface(NetInterface netInterface) {
        this.netInterface = netInterface;
        return this;
    }

    public Builder setDebug(boolean debug) {
        this.isDebug = debug;
        return this;
    }

    public Builder setShowLog(boolean showLog) {
        this.isShowLog = showLog;
        return this;
    }

    public Builder setNoProxy(boolean noProxy) {
        this.isNoProxy = noProxy;
        return this;
    }

    public Builder setDefaultLogTag(String defaultLogTag) {
        this.defaultLogTag = defaultLogTag;
        return this;
    }

    public Builder setHost(String host) {
        this.host = host;
        return this;
    }
}

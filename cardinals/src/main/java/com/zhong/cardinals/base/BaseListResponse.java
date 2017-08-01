package com.zhong.cardinals.base;

import java.util.List;

/**
 * Created by zhong on 2017/2/6.
 */

public class BaseListResponse<T> {
    private int retcode = -1;//0
    private String retmsg;//"success"
    private List<T> data;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

package com.zhong.cardinals.base;

/**
 * Created by zhong on 2017/2/6.
 */

public class BaseResponse<T> {
    private int retcode = -101;//0
    private String retmsg;//"success"
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

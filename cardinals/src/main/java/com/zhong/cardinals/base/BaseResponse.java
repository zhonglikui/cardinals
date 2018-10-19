package com.zhong.cardinals.base;

/**
 * Created by zhong on 2017/2/6.
 */

public class BaseResponse<T> extends BaseData {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
